package biz.wittkemper.jfire.forms.dienstjubilaeum;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FDienstjubilaeum extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5712775429209877420L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblJahrInDem;
	private JComboBox cbJahr;
	private boolean abbruch = true;
	/**
	 * Create the dialog.
	 */
	public FDienstjubilaeum() {
		setResizable(false);
		setTitle("Auswahl");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 307, 188);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.WEST);
		
		lblJahrInDem = new JLabel("Jahr in dem das Jübiläum sein wird:");
		cbJahr = new JComboBox();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblJahrInDem))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(87)
							.addComponent(cbJahr, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(52, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(27)
					.addComponent(lblJahrInDem)
					.addGap(33)
					.addComponent(cbJahr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(44, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						abbruch=false;
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
					public void actionPerformed(ActionEvent e) {
						abbruch=true;
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		fuelleJahre();
	}

	public String getJahr(){
		return cbJahr.getSelectedItem().toString();
	}
	private void fuelleJahre() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cbJahr.removeAllItems();
		for(int i = -2;i< 5;i++){
			String value = Integer.toString(cal.get(Calendar.YEAR)+i);
			cbJahr.addItem(value);
		}
		cbJahr.setSelectedIndex(2);
	}
	public boolean getAbruch(){
		return abbruch ;
	}
}
