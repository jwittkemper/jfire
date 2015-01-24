package biz.wittkemper.jfire.forms.fmitgliedersearch;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import biz.wittkemper.jfire.data.entity.Mitglied;

/**
 * 
 * @author joerg
 *
 */
public class FmitgliederSearrch extends JDialog {

	private static final long serialVersionUID = 1240923465730333571L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblSuchbegriff;
	private JTextField textField;
	JButton cancelButton;
	JButton okButton;
	MitgliedSearchModel model;
	private JScrollPane scrollPane;
	private JXTable table;
	private JCheckBox chgeloeschteAnzeigen;

	/**
	 * Create the dialog.
	 * @param MitgliedSearchModel
	 */
	public FmitgliederSearrch(MitgliedSearchModel pm) {
		this.model = pm;
		setTitle("Mitgliedersuche");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setModal(true);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 450, 550);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		lblSuchbegriff = new JLabel("Suchbegriff:");

		textField = new JTextField();
		textField.setColumns(10);

		scrollPane = new JScrollPane();

		chgeloeschteAnzeigen = new JCheckBox("gelöschte Mitglieder anzeigen");

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel
				.setHorizontalGroup(gl_contentPanel
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_contentPanel
										.createSequentialGroup()
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				lblSuchbegriff)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				textField,
																				GroupLayout.PREFERRED_SIZE,
																				316,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				gl_contentPanel
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								chgeloeschteAnzeigen)
																						.addComponent(
																								scrollPane,
																								GroupLayout.DEFAULT_SIZE,
																								414,
																								Short.MAX_VALUE))))
										.addContainerGap()));
		gl_contentPanel
				.setVerticalGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPanel
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblSuchbegriff)
														.addComponent(
																textField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addComponent(scrollPane,
												GroupLayout.PREFERRED_SIZE,
												391, GroupLayout.PREFERRED_SIZE)
										.addGap(4)
										.addComponent(chgeloeschteAnzeigen)
										.addContainerGap()));

		table = new JXTable(pm.getTableModel());
		table.setHighlighters(HighlighterFactory.createAlternateStriping());
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		scrollPane.setViewportView(table);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");

				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public Mitglied getSelectedMitglied() {
		int zeile = table.getSelectedRow();

		if (zeile >= 0) {
			return model.getSelectedMitglied(zeile);
		}
		return null;
	}

	public void setMouseListener(MouseListener ml) {
		table.addMouseListener(ml);
	}

	public void setCancelListener(ActionListener al) {
		cancelButton.addActionListener(al);
	}

	public void setOKListener(ActionListener al) {
		okButton.addActionListener(al);
	}

	public void setSearchListener(KeyListener kl) {
		textField.addKeyListener(kl);
	}

	public void setSearchText(String text) {
		this.textField.setText(text);
	}

	public String getSearchText() {
		return this.textField.getText();
	}

	public boolean getGeloschteAnzeigen() {
		return chgeloeschteAnzeigen.isSelected();
	}
}
