package biz.wittkemper.jfire.forms.fmitgliedersearch;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import biz.wittkemper.jfire.data.entity.Mitglied;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.list.SelectionInList;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Action;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JScrollPane;

public class FmitgliederSearrch extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1240923465730333571L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblSuchbegriff;
	private JTextField textField;
	JButton cancelButton;
	JButton okButton;
	ExamplePresentationModel model;
	private JScrollPane scrollPane;
	private JXTable table;

	/**
	 * Create the dialog.
	 */
	public FmitgliederSearrch(ExamplePresentationModel pm) {
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

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblSuchbegriff)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
							.addContainerGap())
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSuchbegriff)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 421, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		table = new JXTable(pm.getTableModel());
		table.setHighlighters(HighlighterFactory.createAlternateStriping());
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
	
	public String getSearchText(){
		return this.textField.getText();
	}
}
