package biz.wittkemper.jfire.forms.fmain;

import java.awt.Component;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;

import biz.wittkemper.jfire.data.dao.DAOFactory;
import biz.wittkemper.jfire.forms.fmitgliederverwaltung.FMitgliederVerwaltungController;
import biz.wittkemper.jfire.forms.fmitgliederverwaltung.FMitgliederVerwaltungView;
import biz.wittkemper.jfire.service.ReportService;
import biz.wittkemper.jfire.service.ReportService.REPORTS;
import biz.wittkemper.jfire.utils.FrameUtils;

public class FMainController {
	FrameUtils frameUtils = new FrameUtils();
//	FMitgliederVerwaltungController mitgliederVerwaltungController;
	FMainView view;

	public FMainController() {
		this.view = new FMainView();
		initListener();
		loadMitgliederMeldung();
	}

	private void loadMitgliederMeldung() {
		LoadData data = new LoadData();
		data.run();

	}

	private void initListener() {
		this.view.setBeendenListener(new BeendenListener());
		this.view.setMitgliederListener(new MitgliederListener());
		this.view.addWindowListener(new WindowList());
		this.view.setListenListener(new LitenListener());
		this.view.getContentPane().addHierarchyBoundsListener(new HierarchyBoundsListener() {
			
			@Override
			public void ancestorResized(HierarchyEvent e) {
				showView();
				
			}
			
			@Override
			public void ancestorMoved(HierarchyEvent e) {
				showView();
				
			}
		});
	}

	public void showView() {
		this.view.setSize(1024, 768);
		this.view.setVisible(true);
		this.view.setResizable(false);

	}

	private void AppClose() {
		if (JOptionPane.showConfirmDialog(view, "Wollen sie wirklich beenden?",
				"Frage", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
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

	class LitenListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("mitgliederfoerderverein")){
				try {
					ReportService.showReport(REPORTS.MITGLIEDERFOERDERVEREIN);
				} catch (JRException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(e.getActionCommand().equals("telefonlisteactive")){
				try {
					ReportService.showReport(REPORTS.TELEFONLISTEAKTIVE);
				} catch (JRException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if (e.getActionCommand().equals("telefonlisteareserve")){
				try {
					ReportService.showReport(REPORTS.TELEFONRESERVE);
				} catch (JRException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		
	}
	class MitgliederListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			for(Component jdesk: view.getContentPane().getComponents()){
				if (jdesk instanceof JDesktopPane){
					JDesktopPane dsk = (JDesktopPane) jdesk;
					for(JInternalFrame jint:  dsk.getAllFrames()){
						if (jint instanceof FMitgliederVerwaltungView){
							jint.setVisible(true);
							return;
						}
					}
				}
				System.out.println(jdesk.getName());
			}

			try {
				view.addFrame( new FMitgliederVerwaltungController().getFrame());
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			view.repaint();
		}
	}

	class LoadData extends Thread {
		public void run() {

			try {

				int aktive = 0;
				int reserve = 0;
				int passive = 0;

				aktive = DAOFactory.getInstance().getMitgliedDAO().getAktive();
				reserve = DAOFactory.getInstance().getMitgliedDAO()
						.getReserve();
				passive = DAOFactory.getInstance().getFoerderMitgliedDAO()
						.getAll();
				String text = "Aktive Mitglieder: " + aktive + "; ";
				text += "Reservezug: " + reserve + "; ";
				text += "Förderer: " + passive + " ";

				view.setMitgliederMeldung(text);
			} catch (Exception e) {
			}

		}
	}

}
