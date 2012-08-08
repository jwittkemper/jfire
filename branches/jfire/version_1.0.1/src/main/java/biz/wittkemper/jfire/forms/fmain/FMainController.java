package biz.wittkemper.jfire.forms.fmain;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.sf.jasperreports.engine.JRException;
import biz.wittkemper.jfire.data.dao.DAOFactory;
import biz.wittkemper.jfire.data.dao.HibernateSession;
import biz.wittkemper.jfire.forms.fAdressliste.FAdressliste;
import biz.wittkemper.jfire.forms.fanwesenheit.FAnwesenheit;
import biz.wittkemper.jfire.forms.fdienstjubilaeum.FDienstjubilaeum;
import biz.wittkemper.jfire.forms.feinsatzliste.FEinsatzliste;
import biz.wittkemper.jfire.forms.ferror.FError;
import biz.wittkemper.jfire.forms.fmitgliederverwaltung.FMitgliederVerwaltungController;
import biz.wittkemper.jfire.forms.fmitgliederverwaltung.FMitgliederVerwaltungView;
import biz.wittkemper.jfire.service.replication.ReplicationReadWorkFlow;
import biz.wittkemper.jfire.service.replication.ReplicationWriteWorkflow;
import biz.wittkemper.jfire.service.report.ReportService;
import biz.wittkemper.jfire.service.report.ReportService.REPORTS;
import biz.wittkemper.jfire.service.report.ReportService.REPORTSAKTION;
import biz.wittkemper.jfire.utils.DateUtils;
import biz.wittkemper.jfire.utils.FrameUtils;
import biz.wittkemper.jfire.utils.SystemUtils;

public class FMainController {
	FrameUtils frameUtils = new FrameUtils();
	SystemUtils systemUtils = new SystemUtils();

	FMainView view;

	public FMainController() {
		try {
			this.view = new FMainView();
			initListener();
			loadMitgliederMeldung();
		} catch (Exception e) {
			FError error = new FError(e);
			error.show(true);
		}
	}

	private void loadMitgliederMeldung() {

		HibernateSession.beginTransaction();
		LoadData data = new LoadData();
		data.run();
		HibernateSession.commitTransaction();

	}

	private void initListener() {
		this.view.setBeendenListener(new BeendenListener());
		this.view.setMitgliederListener(new MitgliederListener());
		this.view.addWindowListener(new WindowList());
		this.view.setListenListener(new LitenListener());
		this.view.getContentPane().addHierarchyBoundsListener(
				new HierarchyBoundsListener() {

					@Override
					public void ancestorResized(HierarchyEvent e) {
						// showView();

					}

					@Override
					public void ancestorMoved(HierarchyEvent e) {
						// showView();

					}
				});
	}

	public void showView() {
		String value = systemUtils.getPropertyValue("FMAIN");
		String values[] = value.split(";");
		System.out.println(values.length);
		if (values.length != 4) {
			this.view.setSize(1024, 768);

		} else {
			Dimension dimension = new Dimension();
			dimension.width = Integer.parseInt(values[0]);
			dimension.height = Integer.parseInt(values[1]);
			this.view.setSize(dimension);
			this.view.setLocation(Integer.parseInt(values[2]),
					Integer.parseInt(values[3]));
		}

		this.view.setVisible(true);
		this.view.setResizable(true);

		DateUtils utils = new DateUtils();
		utils.findGeburtstag(view);
	}

	private void AppClose() {
		if (JOptionPane.showConfirmDialog(view, "Wollen sie wirklich beenden?",
				"Frage", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			Dimension dimension = view.getSize();
			String value = dimension.width + ";" + dimension.height + ";"
					+ view.getLocationOnScreen().x + ";"
					+ view.getLocationOnScreen().y;
			try {
				systemUtils.savePropertyValue("FMAIN", value);
			} catch (IOException e) {
				FError error = new FError(e);
				error.show(true);
			}
			view.setVisible(false);
			System.exit(0);
		}
	}

	class WindowList implements WindowListener {

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosing(WindowEvent e) {
			AppClose();

		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub

		}

	}

	class BeendenListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			AppClose();
		}

	}

	class LitenListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("adresslisten")) {
				FAdressliste adressliste = new FAdressliste();
				adressliste.setModal(true);
				adressliste.setVisible(true);

				REPORTS rep = adressliste.getReport();
				Map map = adressliste.getMap();
				REPORTSAKTION action = adressliste.getAktion();
				if (adressliste.isCloseOK()) {
					try {
						ReportService.showReport(rep, map, action);
					} catch (JRException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} else if (e.getActionCommand().equals("telefonlisteactive")) {
				try {
					ReportService.showReport(REPORTS.TELEFONLISTEAKTIVE, null,
							null);
				} catch (JRException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getActionCommand().equals("telefonlisteareserve")) {
				try {
					ReportService
							.showReport(REPORTS.TELEFONRESERVE, null, null);
				} catch (JRException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getActionCommand().equals("anwesenheit")) {
				FAnwesenheit anwesenheit = new FAnwesenheit();
				anwesenheit.setModal(true);
				anwesenheit.setVisible(true);

				if (anwesenheit.isCloseOK() == true) {
					Map map = anwesenheit.getMapValues();
					try {
						ReportService.showReport(REPORTS.ANWESENHEIUEBUNG, map,
								null);
					} catch (JRException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			} else if (e.getActionCommand().equals("dienstjubilaeum")) {
				FDienstjubilaeum fd = new FDienstjubilaeum();
				fd.setModal(true);
				fd.setVisible(true);
				Map map = new HashMap();
				map.put("Jahr", fd.getJahr());
				try {
					ReportService
							.showReport(REPORTS.JUBILAEUMSLISTE, map, null);
				} catch (JRException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getActionCommand().equals("anwesenheitEinsatzL")) {
				FEinsatzliste einsatzliste = new FEinsatzliste();
				einsatzliste.setModal(true);
				einsatzliste.setVisible(true);
				if (einsatzliste.getCancel() == false) {
					System.out.println(einsatzliste.getJahr());
					Map map = new HashMap<String, Object>();
					map.put("jahr", einsatzliste.getJahr());
					map.put("typ", "(Löschzug)");
					map.put("whereand", "1=1");

					map.put("startwert", einsatzliste.getBlatt().getStartwert());
					try {
						ReportService.showReport(REPORTS.ANWESENHEITEINSATZ,
								map, null);
					} catch (JRException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} else if (e.getActionCommand().equals("anwesenheitEinsatzR")) {
				FEinsatzliste einsatzliste = new FEinsatzliste();
				einsatzliste.setModal(true);
				einsatzliste.setVisible(true);
				if (einsatzliste.getCancel() == false) {
					System.out.println(einsatzliste.getJahr());
					Map map = new HashMap<String, Object>();
					map.put("jahr", einsatzliste.getJahr());
					map.put("typ", "(Rettungsdienst)");
					map.put("whereand", "rettungsdienst=1");

					map.put("startwert", einsatzliste.getBlatt().getStartwert());
					try {
						ReportService.showReport(REPORTS.ANWESENHEITEINSATZ,
								map, null);
					} catch (JRException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} else if (e.getActionCommand().equals("datenexport")) {
				JFileChooser fc = new JFileChooser();

				fc.setFileFilter(new FileNameExtensionFilter(
						"JFire Daten(*.jfire)", "jfire"));
				String date = DateUtils.getCurDateString(new Date());

				fc.setSelectedFile(new File("daten_" + date + ".jfire"));
				int fcr = fc.showSaveDialog(view);
				if (fcr == JFileChooser.CANCEL_OPTION) {
					System.out.println("Datenexport, abbruch");
				} else {
					ReplicationWriteWorkflow workflow = new ReplicationWriteWorkflow();
					workflow.Excecute(fc.getSelectedFile(), view);
					System.out.println(fc.getSelectedFile());
				}

			} else if (e.getActionCommand().equals("datenimport")) {
				ReplicationReadWorkFlow readWorkFlow = new ReplicationReadWorkFlow();
				try {
					readWorkFlow.Excecute(view);
				} catch (Exception e1) {
					FError error = new FError(e1);
					error.show(true);
				}
				loadMitgliederMeldung();
			}

		}
	}

	class MitgliederListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			for (Component jdesk : view.getContentPane().getComponents()) {
				if (jdesk instanceof JDesktopPane) {
					JDesktopPane dsk = (JDesktopPane) jdesk;
					for (JInternalFrame jint : dsk.getAllFrames()) {
						if (jint instanceof FMitgliederVerwaltungView) {
							jint.setVisible(true);
							return;
						}
					}
				}
				System.out.println(jdesk.getName());
			}

			try {
				view.addFrame(new FMitgliederVerwaltungController().getFrame());
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			view.repaint();
		}
	}

	class LoadData extends Thread {
		@Override
		public void run() {

			int aktive = 0;
			int reserve = 0;
			int passive = 0;

			aktive = DAOFactory.getInstance().getMitgliedDAO().getAktive();
			reserve = DAOFactory.getInstance().getMitgliedDAO().getReserve();
			passive = DAOFactory.getInstance().getFoerderMitgliedDAO().getAll();
			String text = "Aktive Mitglieder: " + aktive + "; ";
			text += "Reservezug: " + reserve + "; ";
			text += "Förderer: " + passive + " ";

			view.setMitgliederMeldung(text);

		}
	}

}
