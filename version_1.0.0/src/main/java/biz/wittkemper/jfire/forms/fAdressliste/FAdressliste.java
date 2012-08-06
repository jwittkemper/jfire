package biz.wittkemper.jfire.forms.fAdressliste;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import biz.wittkemper.jfire.service.report.ReportService.REPORTS;
import biz.wittkemper.jfire.service.report.ReportService.REPORTSAKTION;

public class FAdressliste extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6832718292672640747L;
	private JPanel panel;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnAktive;
	private JRadioButton rdbtnFrderverein;
	private JPanel panel_1;
	private JCheckBox chckbxAktive;
	private JCheckBox chckbxReservezug;
	private JTextField txtAdresslisten;
	private JButton btnCancel;
	private JButton btnExport;
	private JButton btnAnzeigen;
	private boolean closeOK = false;
	private REPORTSAKTION action;

	/**
	 * Create the frame.
	 */
	public FAdressliste() {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setResizable(false);

		setBounds(100, 100, 302, 300);

		panel = new JPanel();

		rdbtnAktive = new JRadioButton("Aktive");
		rdbtnAktive.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rdbtnAktive.isSelected() != true) {
					chckbxAktive.setSelected(false);
					chckbxReservezug.setSelected(false);
				}
			}
		});
		rdbtnAktive.setSelected(true);

		rdbtnFrderverein = new JRadioButton("Förderverein");

		panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));

		txtAdresslisten = new JTextField();
		txtAdresslisten.setHorizontalAlignment(SwingConstants.CENTER);
		txtAdresslisten.setBackground(new Color(255, 250, 205));
		txtAdresslisten.setEditable(false);
		txtAdresslisten.setText("Adresslisten");
		txtAdresslisten.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING)
																				.addComponent(
																						rdbtnAktive,
																						GroupLayout.PREFERRED_SIZE,
																						133,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						rdbtnFrderverein))
																.addGap(145))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		panel_1,
																		GroupLayout.PREFERRED_SIZE,
																		210,
																		GroupLayout.PREFERRED_SIZE)
																.addContainerGap(
																		68,
																		Short.MAX_VALUE))
												.addGroup(
														Alignment.TRAILING,
														gl_panel.createSequentialGroup()
																.addComponent(
																		txtAdresslisten,
																		GroupLayout.DEFAULT_SIZE,
																		278,
																		Short.MAX_VALUE)
																.addContainerGap()))));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup()
						.addComponent(txtAdresslisten,
								GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(25)
						.addComponent(rdbtnAktive)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 87,
								GroupLayout.PREFERRED_SIZE).addGap(26)
						.addComponent(rdbtnFrderverein)
						.addContainerGap(28, Short.MAX_VALUE)));

		chckbxAktive = new JCheckBox("Aktive");
		chckbxAktive.setSelected(true);

		chckbxReservezug = new JCheckBox("Reservezug");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel_1
						.createSequentialGroup()
						.addGap(37)
						.addGroup(
								gl_panel_1
										.createParallelGroup(Alignment.LEADING)
										.addComponent(chckbxAktive)
										.addComponent(chckbxReservezug))
						.addContainerGap(65, Short.MAX_VALUE)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel_1.createSequentialGroup().addContainerGap()
						.addComponent(chckbxAktive)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(chckbxReservezug)
						.addContainerGap(33, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		buttonGroup.add(rdbtnAktive);
		buttonGroup.add(rdbtnFrderverein);

		btnCancel = new JButton("cancel");
		btnCancel.setActionCommand("cancel");
		btnCancel.addActionListener(new ButtonListener());

		btnExport = new JButton("Export");
		btnExport.setActionCommand("export");
		btnExport.addActionListener(new ButtonListener());

		btnAnzeigen = new JButton("anzeigen");
		btnAnzeigen.setActionCommand("anzeigen");
		btnAnzeigen.addActionListener(new ButtonListener());

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																panel,
																GroupLayout.DEFAULT_SIZE,
																290,
																Short.MAX_VALUE)
														.addGroup(
																Alignment.TRAILING,
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				btnAnzeigen)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				btnExport)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				btnCancel)
																		.addContainerGap()))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.TRAILING)
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(panel, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addGap(18)
								.addGroup(
										groupLayout
												.createParallelGroup(
														Alignment.BASELINE)
												.addComponent(btnCancel)
												.addComponent(btnExport)
												.addComponent(btnAnzeigen))
								.addContainerGap()));
		getContentPane().setLayout(groupLayout);
	}

	public boolean isCloseOK() {
		return closeOK;
	}

	public Map getMap() {
		Map result = new HashMap();
		String status = "";

		if (rdbtnAktive.isSelected()) {
			if (chckbxAktive.isSelected()) {
				status = "1";
			}
			if (chckbxReservezug.isSelected()) {
				if (status.length() != 0) {
					status += ",2";
				} else {
					status = "2";
				}
			}
			result.put("mitgliedstatus", status);
		}
		return result;
	}

	public REPORTS getReport() {
		if (rdbtnAktive.isSelected()) {
			return REPORTS.MITGLIEDERAKTIVE;
		} else {
			return REPORTS.MITGLIEDERFOERDERVEREIN;
		}
	}

	public REPORTSAKTION getAktion() {
		return action;

	}

	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals("cancel")) {
				closeOK = false;
				setVisible(false);
			} else if (e.getActionCommand().equals("export")) {
				action = REPORTSAKTION.EXPORT;
				closeOK = true;
				setVisible(false);
			} else if (e.getActionCommand().equals("anzeigen")) {
				action = REPORTSAKTION.VIEW;
				closeOK = true;
				setVisible(false);
			}
		}

	}
}
