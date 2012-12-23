package biz.wittkemper.jfire.forms.fmitgliederverwaltung;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.jdesktop.swingx.JXDatePicker;

import biz.wittkemper.jfire.data.entity.Mitglied.LOESCHGRUNG;

public class FMitgliedDelete extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5830281527156457274L;
	private final JPanel panel;
	private final JLabel lblAustrittsgrung;
	private final JComboBox cbGrund;
	private final JLabel lblAustrittsdatum;
	private final JXDatePicker datePicker;
	private final JButton btnSpeichern;
	private final JButton btnAbbechen;
	private boolean closeOK = false;

	public FMitgliedDelete() {
		setTitle("Mitglied löschen");
		setBounds(100, 100, 312, 300);

		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);

		lblAustrittsgrung = new JLabel("Austrittsgrund:");

		cbGrund = new JComboBox(LOESCHGRUNG.values());

		lblAustrittsdatum = new JLabel("Austrittsdatum:");

		datePicker = new JXDatePicker();
		datePicker.setDate(new Date());
		btnSpeichern = new JButton("speichern");
		btnSpeichern.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeOK = true;
				setVisible(false);
			}
		});

		btnAbbechen = new JButton("abbechen");
		btnAbbechen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeOK = true;
				setVisible(false);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.TRAILING)
												.addComponent(lblAustrittsdatum)
												.addComponent(lblAustrittsgrung))
								.addGap(18)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addComponent(
														cbGrund,
														GroupLayout.PREFERRED_SIZE,
														117,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														datePicker,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addContainerGap(188, Short.MAX_VALUE))
				.addGroup(
						Alignment.TRAILING,
						gl_panel.createSequentialGroup()
								.addContainerGap(194, Short.MAX_VALUE)
								.addComponent(btnSpeichern)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnAbbechen).addGap(16)));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGap(26)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblAustrittsgrung)
												.addComponent(
														cbGrund,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblAustrittsdatum)
												.addComponent(
														datePicker,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED,
										178, Short.MAX_VALUE)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(btnSpeichern)
												.addComponent(btnAbbechen))
								.addContainerGap()));
		panel.setLayout(gl_panel);

	}

	public LOESCHGRUNG getLoeschGrunf() {

		LOESCHGRUNG lg = (LOESCHGRUNG) cbGrund.getItemAt(cbGrund
				.getSelectedIndex());
		return lg;
	}

	public Date getAustittsDatum() {
		return datePicker.getDate();
	}

	public boolean isCloseOK() {
		return closeOK;
	}
}
