package biz.wittkemper.jfire.forms.feinsatzliste;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import biz.wittkemper.jfire.utils.DateUtils;

public class FEinsatzliste extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JTextField txtAusdruckVonEinsatzlisten;
	private final JComboBox comboBox;
	private final JLabel lblJahr;
	private final JLabel lblBlatt;
	private final JComboBox cbBlatt;

	private boolean cancel;
	private final JLabel lblAuswahl;

	public boolean getCancel() {
		return cancel;
	}

	public String getJahr() {
		return comboBox.getSelectedItem().toString();
	}

	public EinsatzBlatt getBlatt() {
		return (EinsatzBlatt) cbBlatt.getSelectedItem();
	}

	/**
	 * Create the dialog.
	 */
	public FEinsatzliste() {
		setResizable(false);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		txtAusdruckVonEinsatzlisten = new JTextField();
		txtAusdruckVonEinsatzlisten.setBackground(Color.ORANGE);
		txtAusdruckVonEinsatzlisten.setText("Ausdruck von Einsatzlisten");
		txtAusdruckVonEinsatzlisten.setColumns(1);
		txtAusdruckVonEinsatzlisten.setEnabled(true);
		txtAusdruckVonEinsatzlisten.setEditable(false);
		txtAusdruckVonEinsatzlisten.setHorizontalAlignment(JTextField.CENTER);

		comboBox = new JComboBox();
		int jahr = DateUtils.getJahr();
		comboBox.addItem(jahr - 1);
		comboBox.addItem(jahr);
		comboBox.addItem(jahr + 1);
		comboBox.addItem(jahr + 2);
		comboBox.setSelectedIndex(1);

		lblJahr = new JLabel("Jahr");
		lblBlatt = new JLabel("Auswahl:");
		cbBlatt = new JComboBox();
		fillcbcbBlatt(cbBlatt);
		lblAuswahl = new JLabel("Auswahl:");

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel
				.setHorizontalGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addComponent(txtAusdruckVonEinsatzlisten,
								GroupLayout.PREFERRED_SIZE, 445,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(
								gl_contentPanel
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																lblJahr,
																GroupLayout.PREFERRED_SIZE,
																72,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblAuswahl))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																cbBlatt,
																GroupLayout.PREFERRED_SIZE,
																182,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																comboBox,
																GroupLayout.PREFERRED_SIZE,
																78,
																GroupLayout.PREFERRED_SIZE))
										.addGap(161)));
		gl_contentPanel
				.setVerticalGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPanel
										.createSequentialGroup()
										.addGap(40)
										.addComponent(
												txtAusdruckVonEinsatzlisten,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(42)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblJahr)
														.addComponent(
																comboBox,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblAuswahl)
														.addComponent(
																cbBlatt,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(94, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						cancel = false;
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						cancel = true;
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void fillcbcbBlatt(JComboBox cbBlatt2) {
		EinsatzBlatt blatt1 = new EinsatzBlatt("Blatt 1 (  1 -  20)", 1);
		EinsatzBlatt blatt2 = new EinsatzBlatt("Blatt 2 ( 21 -  40)", 21);
		EinsatzBlatt blatt3 = new EinsatzBlatt("Blatt 3 ( 41 -  60)", 41);
		EinsatzBlatt blatt4 = new EinsatzBlatt("Blatt 4 ( 61 -  80)", 61);
		EinsatzBlatt blatt5 = new EinsatzBlatt("Blatt 5 ( 81 - 100)", 81);
		EinsatzBlatt blatt6 = new EinsatzBlatt("Blatt 6 (101 - 120)", 101);
		EinsatzBlatt blatt7 = new EinsatzBlatt("Blatt 7 (121 - 140)", 121);
		EinsatzBlatt blatt8 = new EinsatzBlatt("Blatt 8 (141 - 160)", 141);
		EinsatzBlatt blatt9 = new EinsatzBlatt("Blatt 9  (161 - 180)", 161);
		EinsatzBlatt blatt10 = new EinsatzBlatt("Blatt 10 (181 - 200)", 181);

		cbBlatt2.removeAllItems();
		cbBlatt2.addItem(blatt1);
		cbBlatt2.addItem(blatt2);
		cbBlatt2.addItem(blatt3);
		cbBlatt2.addItem(blatt4);
		cbBlatt2.addItem(blatt5);
		cbBlatt2.addItem(blatt6);
		cbBlatt2.addItem(blatt7);
		cbBlatt2.addItem(blatt8);
		cbBlatt2.addItem(blatt9);
		cbBlatt2.addItem(blatt10);
	}
}
