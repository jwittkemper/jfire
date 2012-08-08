package biz.wittkemper.jfire.forms.fanwesenheit;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.JXDatePicker;

import biz.wittkemper.jfire.utils.DateUtils;

public class FAnwesenheit extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblDatum;
	private JXDatePicker datePicker;
	private JLabel lblThemaDesDienst;
	private JTextField IUebungsthema;
	private JLabel lblbungsleiter;
	private JTextField IUebungsleiter;
	private boolean closeOK = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FAnwesenheit dialog = new FAnwesenheit();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FAnwesenheit() {
		setTitle("Dateneingabe Übungsdienst:");
		setBounds(100, 100, 450, 199);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		lblDatum = new JLabel("Datum:");

		datePicker = new JXDatePicker();
		datePicker.setDate(new Date());
		lblThemaDesDienst = new JLabel("Übungsthema:");

		IUebungsthema = new JTextField();
		IUebungsthema.setColumns(10);

		lblbungsleiter = new JLabel("Übungsleiter:");

		IUebungsleiter = new JTextField();
		IUebungsleiter.setColumns(10);
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
														.addComponent(lblDatum)
														.addComponent(
																lblThemaDesDienst)
														.addComponent(
																lblbungsleiter))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																datePicker,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																IUebungsthema,
																GroupLayout.DEFAULT_SIZE,
																216,
																Short.MAX_VALUE)
														.addComponent(
																IUebungsleiter,
																GroupLayout.PREFERRED_SIZE,
																284,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));
		gl_contentPanel
				.setVerticalGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPanel
										.createSequentialGroup()
										.addGap(16)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(lblDatum)
														.addComponent(
																datePicker,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblThemaDesDienst)
														.addComponent(
																IUebungsthema,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																lblbungsleiter)
														.addComponent(
																IUebungsleiter,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(122, Short.MAX_VALUE)));
		gl_contentPanel.linkSize(SwingConstants.VERTICAL, new Component[] {
				lblDatum, datePicker, lblThemaDesDienst, IUebungsthema,
				lblbungsleiter, IUebungsleiter });
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						closeOK = true;
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
						closeOK = false;
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public boolean isCloseOK() {
		return closeOK;
	}

	public Map getMapValues() {
		Map map = new HashMap();

		map.put("DATUM", DateUtils.getCurDateString(datePicker.getDate()));

		if (IUebungsthema.getText().length() > 0) {
			map.put("THEMA", IUebungsthema.getText());
		}
		if (IUebungsleiter.getText().length() > 0) {
			map.put("LEITUNG", IUebungsleiter.getText());
		}

		return map;
	}
}
