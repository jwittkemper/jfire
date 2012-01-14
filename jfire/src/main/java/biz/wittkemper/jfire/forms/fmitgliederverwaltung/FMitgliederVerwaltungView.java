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
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.beans.BeanAdapter;
import com.jgoodies.binding.value.Trigger;
import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.validation.ValidationResultModel;
import com.sun.xml.internal.org.jvnet.staxex.NamespaceContextEx.Binding;

import biz.wittkemper.jfire.data.entity.Mitglied;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;
import javax.swing.UIManager;
import javax.swing.JToolBar;

import org.hibernate.cfg.ImprovedNamingStrategy;

import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class FMitgliederVerwaltungView extends JInternalFrame {
	/**
	 * 
	 */
	MitgliedModel model = new MitgliedModel();

	private static final long serialVersionUID = -8498808806506779920L;
	public PresentationModel<MitgliedModel> pmodel;
	ValueModel name;
	ValueModel vorname;
	ValueModel strasse;
	ValueModel plz;
	ValueModel ort;
	public Trigger trigger;
	private JToolBar TBMain;
	private JPanel panel;
	JButton btnSave;
	JButton btnbeenden;
	private JTextField Iname = new JTextField();
	private JLabel lblVorname;
	private JTextField IVorname;
	private JLabel lblStrasse;
	private JTextField IStrasse;
	private JLabel lblPlz;
	private JTextField IPlz;
	private JLabel lblWohnort;
	private JTextField Iort;

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
		Iname.setColumns(10);
		Bindings.bind(Iname, name);
		
		IVorname = new JTextField();
		IVorname.setColumns(10);
		Bindings.bind(IVorname,vorname);
		
		IStrasse = new JTextField();
		IStrasse.setColumns(10);
		
		Bindings.bind(IStrasse,strasse);
		
		
		Iort = new JTextField();
		Iort.setColumns(10);
		Bindings.bind(Iort, ort);
		
		IPlz = new JTextField();
		IPlz.setColumns(10);
//		Bindings.bind(IPlz, plz);
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		TBMain = new JToolBar();
		getContentPane().add(TBMain, BorderLayout.NORTH);
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		btnbeenden = new JButton("beenden");
		btnSave = new JButton("speichern");
		
		JTabbedPane tbStammdaten = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(tbStammdaten, GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnSave)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnbeenden)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(tbStammdaten, GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnbeenden)
						.addComponent(btnSave))
					.addContainerGap())
		);
		
		JPanel panel_1 = new JPanel();
		tbStammdaten.addTab("Stammdaten" +"", null, panel_1, null);
		
		JLabel lblName = new JLabel("Name:");
		
		lblVorname = new JLabel("Vorname:");
		
		
		
		lblStrasse = new JLabel("Strasse:");
		
		lblPlz = new JLabel("Plz.:");
		
		
		
		lblWohnort = new JLabel("Wohnort:");
		
		
		
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblName)
							.addGap(27)
							.addComponent(Iname, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblVorname)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(IVorname, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStrasse)
								.addComponent(lblPlz))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(IPlz, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblWohnort))
								.addComponent(IStrasse, 162, 162, 162))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(Iort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(355, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(Iname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblVorname)
							.addComponent(IVorname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblName))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStrasse)
						.addComponent(IStrasse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPlz)
						.addComponent(IPlz, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWohnort)
						.addComponent(Iort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(315, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);


	}

	protected void setSeachListener(ActionListener al) {
//		btnSuche.addActionListener(al);
//		btnSuche.setActionCommand("seach");
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
