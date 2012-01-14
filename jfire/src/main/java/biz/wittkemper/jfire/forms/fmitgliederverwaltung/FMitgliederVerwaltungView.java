package biz.wittkemper.jfire.forms.fmitgliederverwaltung;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.beans.BeanAdapter;
import com.jgoodies.binding.value.Trigger;
import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.validation.ValidationResultModel;

import biz.wittkemper.jfire.data.entity.Mitglied;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;
import javax.swing.UIManager;
import javax.swing.JToolBar;
import java.awt.event.ActionEvent;

public class FMitgliederVerwaltungView extends JInternalFrame {
	/**
	 * 
	 */
	MitgliedModel model = new MitgliedModel();

	private static final long serialVersionUID = -8498808806506779920L;
	private JButton btnbeenden;
	private JButton btnSave;
	private JButton btnSuche;
	public PresentationModel<MitgliedModel> pmodel;
	ValueModel name;
	ValueModel vorname;
	ValueModel strasse;
	ValueModel plz;
	ValueModel ort;
	public Trigger trigger;
	private JTabbedPane tabbedPane;
	private JPanel stammdaten = new JPanel();
	private JPanel panel;
	private JLabel lblStrasse;
	private JLabel lblPlz;
	private JTextField iStrasse;
	private JTextField iPlz;
	private JLabel lblOrt;
	private JTextField iOrt;
	private JToolBar toolBar;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the frame.
	 * 
	 * @throws PropertyVetoException
	 */
	public FMitgliederVerwaltungView() throws PropertyVetoException {

		setMaximizable(true);
		setIcon(true);
		setTitle("Mitgliederverwaltung");
		setResizable(false);
		setBounds(100, 100, 893, 555);
		putClientProperty("JInternalFrame.isPalette", true);
		this.trigger = new Trigger();
		pmodel = new PresentationModel<MitgliedModel>(this.model, this.trigger);

		name = pmodel.getBufferedModel("name");
		vorname = pmodel.getBufferedModel("vorname");
		strasse = pmodel.getBufferedModel("strasseNr");
		plz = pmodel.getBufferedModel("plz");
		ort = pmodel.getBufferedModel("ort");

		btnbeenden = new JButton("beenden");

		btnSave = new JButton("speichern");

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		panel = new JPanel();
		panel.setBorder(UIManager.getBorder("EditorPane.border"));

		iOrt = new JTextField();
		iOrt.setColumns(10);

		iStrasse = new JTextField();
		iStrasse.setColumns(10);

		iPlz = new JTextField();
		iPlz.setColumns(10);
		Bindings.bind(iStrasse, strasse);
//		Bindings.bind(iPlz, plz);
		Bindings.bind(iOrt, ort);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 857, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(205)
							.addComponent(btnSave)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnbeenden))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE)
							.addGap(12)))
					.addGap(14))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(100)
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnbeenden)
						.addComponent(btnSave))
					.addGap(6))
		);
		
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(27, Short.MAX_VALUE))
		);
		
		textField = new JTextField();
		toolBar.add(textField);
		textField.setColumns(10);
		textField.setMaximumSize(new Dimension(150, toolBar.getPreferredSize().height)); 
		
		btnSuche = new JButton("suche");

		toolBar.add(btnSuche);
		panel.setLayout(gl_panel);
		stammdaten.setBorder(UIManager.getBorder("EditorPane.border"));

		tabbedPane.addTab("Adresse", null, stammdaten, null);

		lblStrasse = new JLabel("Strasse");

		lblPlz = new JLabel("Plz.:");

		lblOrt = new JLabel("Ort:");
		
		JLabel label = new JLabel("Vorname:");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel label_1 = new JLabel("Name:");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);

		GroupLayout gl_stammdaten = new GroupLayout(stammdaten);
		gl_stammdaten.setHorizontalGroup(
			gl_stammdaten.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_stammdaten.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_stammdaten.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_stammdaten.createSequentialGroup()
							.addGroup(gl_stammdaten.createParallelGroup(Alignment.LEADING)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStrasse))
							.addGap(12)
							.addGroup(gl_stammdaten.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(Alignment.LEADING, gl_stammdaten.createSequentialGroup()
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.LEADING, gl_stammdaten.createParallelGroup(Alignment.TRAILING)
									.addGroup(Alignment.LEADING, gl_stammdaten.createSequentialGroup()
										.addComponent(iPlz, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblOrt)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(iOrt, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED))
									.addComponent(iStrasse, GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE))))
						.addComponent(lblPlz))
					.addContainerGap(445, Short.MAX_VALUE))
		);
		gl_stammdaten.setVerticalGroup(
			gl_stammdaten.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_stammdaten.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_stammdaten.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_stammdaten.createSequentialGroup()
							.addGap(2)
							.addComponent(label))
						.addGroup(gl_stammdaten.createParallelGroup(Alignment.BASELINE)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_1)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_stammdaten.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStrasse)
						.addComponent(iStrasse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_stammdaten.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_stammdaten.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblPlz)
							.addComponent(iPlz, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblOrt))
						.addComponent(iOrt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(220, Short.MAX_VALUE))
		);
		stammdaten.setLayout(gl_stammdaten);
		getContentPane().setLayout(groupLayout);


	}

	protected void setSeachListener(ActionListener al){
		btnSuche.addActionListener(al);
		btnSuche.setActionCommand("seach");
	}
	protected void setSaveListener(ActionListener al) {
		btnSave.addActionListener(al);
	}

	protected void setBeendenListener(ActionListener al) {
		btnbeenden.addActionListener(al);
	}

	public MitgliedModel getModel() {
		return model;
	}

	public void setModel(MitgliedModel model) {
		this.model = model;
	}
}
