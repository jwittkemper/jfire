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
	private final JComboBox comboBox_1;

	private boolean cancel;

	public boolean getCancel() {
		return cancel;
	}

	public String getJahr() {
		return comboBox.getSelectedItem().toString();
	}

	/**
	 * Create the dialog.
	 */
	public FEinsatzliste() {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
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

		comboBox_1 = new JComboBox();

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel
				.setHorizontalGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPanel
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																txtAusdruckVonEinsatzlisten,
																GroupLayout.DEFAULT_SIZE,
																428,
																Short.MAX_VALUE)
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addComponent(
																				lblJahr,
																				GroupLayout.PREFERRED_SIZE,
																				72,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addGroup(
																				gl_contentPanel
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								comboBox_1,
																								GroupLayout.PREFERRED_SIZE,
																								182,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								comboBox,
																								GroupLayout.PREFERRED_SIZE,
																								78,
																								GroupLayout.PREFERRED_SIZE))
																		.addGap(156)))));
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
										.addGap(44)
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
										.addGap(37)
										.addComponent(comboBox_1,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(67, Short.MAX_VALUE)));
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
}
