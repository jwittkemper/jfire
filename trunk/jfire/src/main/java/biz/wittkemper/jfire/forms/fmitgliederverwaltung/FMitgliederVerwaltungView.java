package biz.wittkemper.jfire.forms.fmitgliederverwaltung;

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

public class FMitgliederVerwaltungView extends JInternalFrame {
	/**
	 * 
	 */
	MitgliedModel model = new MitgliedModel();

	private static final long serialVersionUID = -8498808806506779920L;
	private JButton btnbeenden;
	private JButton btnSave;
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
	private JTextField searchField;
	private JTextField iVorname;
	private JTextField iName;
	private JLabel lblStrasse;
	private JLabel lblPlz;
	private JTextField iStrasse;
	private JTextField iPlz;
	private JLabel lblOrt;
	private JTextField iOrt;

	/**
	 * Create the frame.
	 * 
	 * @throws PropertyVetoException
	 */
	public FMitgliederVerwaltungView() throws PropertyVetoException {

		setMaximizable(true);
		setIcon(true);
		setTitle("Mitgliederverwaltung");
		setResizable(true);
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

		JLabel label = new JLabel("Vorname:");

		iVorname = new JTextField();
		iVorname.setColumns(10);

		JLabel label_1 = new JLabel("Name:");

		iName = new JTextField();
		iName.setColumns(10);

		iOrt = new JTextField();
		iOrt.setColumns(10);

		iStrasse = new JTextField();
		iStrasse.setColumns(10);

		iPlz = new JTextField();
		iPlz.setColumns(10);
		
		Bindings.bind(iVorname, vorname);
		Bindings.bind(iName, name);
		Bindings.bind(iStrasse, strasse);
//		Bindings.bind(iPlz, plz);
		Bindings.bind(iOrt, ort);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap(
																				664,
																				Short.MAX_VALUE)
																		.addComponent(
																				btnSave)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				btnbeenden))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				label,
																				GroupLayout.PREFERRED_SIZE,
																				68,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(12)
																		.addComponent(
																				iVorname,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(12)
																		.addComponent(
																				label_1,
																				GroupLayout.PREFERRED_SIZE,
																				45,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(12)
																		.addComponent(
																				iName,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																Alignment.TRAILING,
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				tabbedPane,
																				GroupLayout.PREFERRED_SIZE,
																				851,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																Alignment.TRAILING,
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap(
																				14,
																				Short.MAX_VALUE)
																		.addComponent(
																				panel,
																				GroupLayout.PREFERRED_SIZE,
																				857,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addComponent(panel,
												GroupLayout.PREFERRED_SIZE, 46,
												GroupLayout.PREFERRED_SIZE)
										.addGap(39)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(2)
																		.addComponent(
																				label))
														.addComponent(
																iVorname,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(2)
																		.addComponent(
																				label_1))
														.addComponent(
																iName,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addComponent(tabbedPane,
												GroupLayout.DEFAULT_SIZE, 352,
												Short.MAX_VALUE)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																btnbeenden)
														.addComponent(btnSave))
										.addContainerGap()));

		searchField = new JTextField();
		searchField.setColumns(10);

		JButton btnSuchen = new JButton("suchen");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(searchField, GroupLayout.PREFERRED_SIZE,
								201, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnSuchen)
						.addContainerGap(547, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														searchField,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnSuchen))
								.addContainerGap(15, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		stammdaten.setBorder(UIManager.getBorder("EditorPane.border"));

		tabbedPane.addTab("Adresse", null, stammdaten, null);

		lblStrasse = new JLabel("Strasse");

		lblPlz = new JLabel("Plz.:");

		lblOrt = new JLabel("Ort:");

		GroupLayout gl_stammdaten = new GroupLayout(stammdaten);
		gl_stammdaten
				.setHorizontalGroup(gl_stammdaten
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_stammdaten
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_stammdaten
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																lblStrasse)
														.addComponent(lblPlz))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_stammdaten
														.createParallelGroup(
																Alignment.LEADING,
																false)
														.addGroup(
																gl_stammdaten
																		.createSequentialGroup()
																		.addComponent(
																				iPlz,
																				GroupLayout.PREFERRED_SIZE,
																				67,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				lblOrt)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				iOrt,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(iStrasse))
										.addContainerGap(528, Short.MAX_VALUE)));
		gl_stammdaten
				.setVerticalGroup(gl_stammdaten
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_stammdaten
										.createSequentialGroup()
										.addGap(22)
										.addGroup(
												gl_stammdaten
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblStrasse)
														.addComponent(
																iStrasse,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												gl_stammdaten
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblPlz)
														.addComponent(
																iPlz,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblOrt)
														.addComponent(
																iOrt,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(255, Short.MAX_VALUE)));
		stammdaten.setLayout(gl_stammdaten);
		getContentPane().setLayout(groupLayout);

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
