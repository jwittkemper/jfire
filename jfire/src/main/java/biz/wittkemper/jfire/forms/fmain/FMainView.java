package biz.wittkemper.jfire.forms.fmain;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

import org.jdesktop.swingx.JXStatusBar;

import biz.wittkemper.jfire.utils.FrameUtils;
import biz.wittkemper.jfire.utils.IconService;
import biz.wittkemper.jfire.utils.IconService.ICONSERVICE;

/**
 * 
 * @author joerg
 *
 */
public class FMainView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2863693692877441385L;
	IconService iconService = new IconService();
	FrameUtils frameUtils = new FrameUtils();
	JMenuBar menuBar;
	JMenu mnDatei;
	JMenuItem mntmBeenden;
	JMenu mnVerwaltung;
	JMenu mnListen;
	JMenu mnDaten;
	JMenuItem mnDatenExport;
	JMenuItem mnDatenImport;
	JMenu mnTelefonListen;
	JMenuItem mntnUebungsListe;
	JMenuItem mntnEinsatzListeL;
	JMenuItem mntnEinsatzListeR;
	JMenuItem mntmMitgliederverwaltung;
	JMenuItem mntmMitgliederFoerderverein;
	JMenuItem mntnTelefonlisteaktive;
	JMenuItem mntnTelefonlistereserve;
	JMenu mntnJubilaeum;
	JMenuItem mntnDienstjubilaeum;
	JMenu mnAdressListen;
	JDesktopPane pane = new MDIDesktopPane();
	JXStatusBar statusBar = new JXStatusBar();
	JLabel lbMitglieder = new JLabel();
	JMenu mnAnwesenheiten;
	JMenuItem mnAdressliste;
	
	JMenu mnStammdaten;
	JMenuItem mnLehrgang;
	JMenuItem mnFueherschein;
	
	/**
	 * Create the frame.
	 */
	public FMainView() {
		setResizable(true);

		Image img = iconService.getImage(ICONSERVICE.main);
		if (img!= null)
			super.setIconImage(img);
		initForm();
	}

	public void setMitgliederMeldung(String text) {
		this.lbMitglieder.setText(text);
	}

	private void initForm() {
		setTitle("JFIRE");
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnDatei = new JMenu("Datei");
		menuBar.add(mnDatei);

		mnDaten = new JMenu("Daten");
		mnDatenExport = new JMenuItem("Export");
		mnDatenExport.setActionCommand("datenexport");
		mnDatenImport = new JMenuItem("Importieren");
		mnDatenImport.setActionCommand("datenimport");
		mnDaten.add(mnDatenImport);
		mnDaten.add(mnDatenExport);
		mnDatei.add(mnDaten);

		mntmBeenden = new JMenuItem("beenden");
		mnDatei.add(mntmBeenden);

		mnVerwaltung = new JMenu("Verwaltung");
		menuBar.add(mnVerwaltung);

		mntmMitgliederverwaltung = new JMenuItem("Mitgliederverwaltung");
		mnVerwaltung.add(mntmMitgliederverwaltung);

		mnListen = new JMenu("Listen");
		menuBar.add(mnListen);

		mnAnwesenheiten = new JMenu("Anwesenheitslisten");
		mnListen.add(mnAnwesenheiten);

		mntnUebungsListe = new JMenuItem("Anwesenheit Übung");
		mnAnwesenheiten.add(mntnUebungsListe);
		mntnUebungsListe.setActionCommand("anwesenheit");

		mntnEinsatzListeL = new JMenuItem("Anwesenheit Einsatz (Löschzug)");
		mnAnwesenheiten.add(mntnEinsatzListeL);
		mntnEinsatzListeL.setActionCommand("anwesenheitEinsatzL");

		mntnEinsatzListeR = new JMenuItem(
				"Anwesenheit Einsatz (Rettungsdienst)");
		mnAnwesenheiten.add(mntnEinsatzListeR);
		mntnEinsatzListeR.setActionCommand("anwesenheitEinsatzR");

		mnTelefonListen = new JMenu("Telefonlisten");
		mnListen.add(mnTelefonListen);

		mntnTelefonlisteaktive = new JMenuItem("Telefonliste (Löschzug)");
		mntnTelefonlisteaktive.setActionCommand("telefonlisteactive");
		mnTelefonListen.add(mntnTelefonlisteaktive);

		mntnTelefonlistereserve = new JMenuItem("Telefonliste (Reservezug)");
		mntnTelefonlistereserve.setActionCommand("telefonlisteareserve");
		mnTelefonListen.add(mntnTelefonlistereserve);

		mntnJubilaeum = new JMenu("Jubil\u00E4en");
		mnListen.add(mntnJubilaeum);
		mntnDienstjubilaeum = new JMenuItem("Dienstjubil\u00E4en");
		mntnDienstjubilaeum.setActionCommand("dienstjubilaeum");
		mntnJubilaeum.add(mntnDienstjubilaeum);

		mnAdressListen = new JMenu("Addresslisten");

		mnListen.add(mnAdressListen);

		mnAdressliste = new JMenuItem("Adressliste (öffentlich)");
		mnAdressliste.setActionCommand("adresslisten-all");
		mnAdressListen.add(mnAdressliste);

		mntmMitgliederFoerderverein = new JMenuItem(
				"Adresslisten (Förderverein)");
		mntmMitgliederFoerderverein.setActionCommand("adresslisten-export");
		mnAdressListen.add(mntmMitgliederFoerderverein);

		mnStammdaten = new JMenu("Einstellungen");
		mnLehrgang = new JMenuItem("Lehrgänge");
		
		mnFueherschein = new JMenuItem("Führerscheine");
		
		mnStammdaten.add(mnLehrgang);
		mnStammdaten.add(mnFueherschein);
		
		menuBar.add(mnStammdaten);
		
		frameUtils.MaximiseFrame(this);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(pane, "Center");
		getContentPane().add(statusBar, BorderLayout.SOUTH);
		statusBar.add(lbMitglieder);
	}

	public void addFrame(JInternalFrame iframe) {
		pane.add(iframe);
		pane.getDesktopManager().maximizeFrame(iframe);
		try {
			iframe.setSelected(true);
			iframe.setMaximum(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void setListenListener(ActionListener al) {
		mntmMitgliederFoerderverein.addActionListener(al);
		mntnTelefonlisteaktive.addActionListener(al);
		mntnTelefonlistereserve.addActionListener(al);
		mntnUebungsListe.addActionListener(al);
		mntnDienstjubilaeum.addActionListener(al);
		mntnEinsatzListeL.addActionListener(al);
		mntnEinsatzListeR.addActionListener(al);
		mnDatenImport.addActionListener(al);
		mnDatenExport.addActionListener(al);
		mnAdressliste.addActionListener(al);
	}

	protected void setBeendenListener(ActionListener al) {
		mntmBeenden.addActionListener(al);
	}

	protected void setMitgliederListener(ActionListener al) {
		mntmMitgliederverwaltung.addActionListener(al);
	}
}
