package biz.wittkemper.jfire.forms.fmain;

import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

import biz.wittkemper.jfire.utils.FrameUtils;

import org.jdesktop.swingx.JXStatusBar;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.BorderLayout;

public class FMainView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2863693692877441385L;

	FrameUtils frameUtils = new FrameUtils();
	JMenuBar menuBar;
	JMenu mnDatei;
	JMenuItem mntmBeenden;
	JMenu mnVerwaltung;
	JMenuItem mntmMitgliederverwaltung;
	JDesktopPane pane = new MDIDesktopPane();
	JXStatusBar statusBar = new JXStatusBar();
	JLabel lbMitglieder = new JLabel();
	/**
	 * Create the frame.
	 */
	public FMainView() {
		initForm();
	}

	public void setMitgliederMeldung(String text){
		this.lbMitglieder.setText(text);
	}
	private void initForm() {
		setTitle("StationManager");
		setBounds(100, 100, 972, 617);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnDatei = new JMenu("Datei");
		menuBar.add(mnDatei);

		mntmBeenden = new JMenuItem("beenden");
		mnDatei.add(mntmBeenden);

		mnVerwaltung = new JMenu("Verwaltung");
		menuBar.add(mnVerwaltung);

		mntmMitgliederverwaltung = new JMenuItem("Mitgliederverwaltung");
		mnVerwaltung.add(mntmMitgliederverwaltung);
		frameUtils.MaximiseFrame(this);
		setContentPane(pane);
		pane.setLayout(new BorderLayout(0, 0));
		
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


	protected void setBeendenListener(ActionListener al) {
		mntmBeenden.addActionListener(al);
	}

	protected void setMitgliederListener(ActionListener al) {
		mntmMitgliederverwaltung.addActionListener(al);
	}
}
