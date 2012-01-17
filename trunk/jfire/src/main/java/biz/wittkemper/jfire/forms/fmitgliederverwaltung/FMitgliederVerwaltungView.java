package biz.wittkemper.jfire.forms.fmitgliederverwaltung;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.adapter.ComboBoxAdapter;
import com.jgoodies.binding.beans.BeanAdapter;
import com.jgoodies.binding.value.ConverterFactory;
import com.jgoodies.binding.value.Trigger;
import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.validation.ValidationResultModel;
import com.sun.xml.internal.org.jvnet.staxex.NamespaceContextEx.Binding;

import biz.wittkemper.jfire.data.entity.Mitglied;
import biz.wittkemper.jfire.data.entity.MitgliedStatus;
import biz.wittkemper.jfire.data.validation.MitgliedValidator;
import biz.wittkemper.jfire.utils.IconService;
import biz.wittkemper.jfire.utils.IconService.ICONSERVICE;


import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;
import javax.swing.UIManager;
import javax.swing.JToolBar;

import org.hibernate.cfg.ImprovedNamingStrategy;

import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import org.jdesktop.swingx.JXDatePicker;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Component;

public class FMitgliederVerwaltungView extends JInternalFrame {
	/**
	 * 
	 */
	MitgliedModel model = new MitgliedModel();

	private static final long serialVersionUID = -8498808806506779920L;
	public PresentationModel<MitgliedModel> pmodel;
	IconService iconService = new IconService();
	
	ValueModel name;
	ValueModel vorname;
	ValueModel strasse;
	ValueModel plz;
	ValueModel ort;
	ValueModel festnetz;
	ValueModel handy;
	ValueModel dienstlich;
	ValueModel email;
	ValueModel gebDatum;
	ValueModel status;
	ValueModel eintritt;
	
	public Trigger trigger;
	private JToolBar TBMain;
	private JPanel panel;
	JButton btnSave;
	JButton btnbeenden;
	private JTextField Iname = new JTextField();
	private JTextField IVorname;
	private JLabel lblStrasse;
	private JTextField IStrasse;
	private JTextField IPlz;
	private JLabel lblWohnort;
	private JTextField Iort;
	private JTextField ISearch;
	JButton bsearch;
	private JPanel tb_phone;
	private JLabel lblTelefonnummer;
	private JLabel lblFestnetzprivat;
	private JLabel lblHandyprivat;
	private JLabel lblDienstlich;
	private JTextField IFestnetz;
	private JTextField IHandy;
	private JTextField IDienstl;
	private JLabel lblEmail;
	private JTextField Iemail;
	private JXDatePicker datePicker;
	private JLabel lblGeburtsdatum;
	private JPanel pnDienstlich;
	private JLabel lblMitgliedSeit;
	private JXDatePicker IEintritt;
	private JLabel lblMitgliedstatus;
	private JComboBox cbStatus;

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
		festnetz = pmodel.getBufferedModel("telefonPrivatFest");
		handy = pmodel.getBufferedModel("telefonPrivatMobil");
		dienstlich = pmodel.getBufferedModel("telefonDienst");
		email = pmodel.getBufferedModel("eMail");
		gebDatum = pmodel.getBufferedModel("gebDatum");
		status = pmodel.getBufferedModel("status");
		eintritt = pmodel.getBufferedModel("eintritt");
		
		List<MitgliedStatus> gueltigStatus = model.getMitgliedStatuse();
		
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
		Bindings.bind(IPlz, ConverterFactory.createStringConverter(plz, new DecimalFormat("0")));
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		TBMain = new JToolBar();
		getContentPane().add(TBMain, BorderLayout.NORTH);
		
		ISearch = new JTextField();
		TBMain.add(ISearch);
		ISearch.setColumns(10);
		ISearch.setMaximumSize(new Dimension(120, TBMain.getPreferredSize().height)); 
		
		bsearch = new JButton("");
		bsearch.setIcon(iconService.getButtonIcon(ICONSERVICE.search));
		TBMain.add(bsearch);
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		btnbeenden = new JButton("beenden");
		btnSave = new JButton("speichern");
		
		JTabbedPane tbStammdaten = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
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
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(tbStammdaten, GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnbeenden)
						.addComponent(btnSave))
					.addContainerGap())
		);
		
		JPanel panel_1 = new JPanel();
		tbStammdaten.addTab("Stammdaten" +"", null, panel_1, null);
		
		JLabel lblName = new JLabel("Name:");
		
		
		
		lblStrasse = new JLabel("Strasse:");
		
		
		
		lblWohnort = new JLabel("Wohnort:");
		
		tb_phone = new JPanel();
		tb_phone.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		lblEmail = new JLabel("E-Mail:");
		
		Iemail = new JTextField();
		Iemail.setColumns(10);
		Bindings.bind(Iemail, email);
		
		datePicker = new JXDatePicker();
		Bindings.bind((JComponent) datePicker,"date", gebDatum);
		lblGeburtsdatum = new JLabel("Geburtstag:");
		
		pnDienstlich = new JPanel();
		pnDienstlich.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblStrasse)
								.addComponent(lblWohnort, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblName)
								.addComponent(lblEmail)
								.addComponent(lblGeburtsdatum, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(IVorname, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(Iname, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(IPlz, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(Iort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addComponent(IStrasse, 162, 162, 162)
									.addComponent(Iemail))
								.addComponent(datePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(46)
							.addComponent(tb_phone, GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(50, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(pnDienstlich, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(434))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(tb_phone, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblName)
								.addComponent(Iname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(IVorname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblStrasse)
								.addComponent(IStrasse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblWohnort)
								.addComponent(IPlz, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(Iort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEmail)
								.addComponent(Iemail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblGeburtsdatum)
								.addComponent(datePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
					.addComponent(pnDienstlich, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		lblMitgliedSeit = new JLabel("Mitglied seit: ");
		
		IEintritt = new JXDatePicker();
		Bindings.bind((JComponent) IEintritt, "date", eintritt);
		
		lblMitgliedstatus = new JLabel("Mitgliedstatus ");
		
		cbStatus = new JComboBox(new ComboBoxAdapter<MitgliedStatus>(gueltigStatus, status));
		
		
		GroupLayout gl_pnDienstlich = new GroupLayout(pnDienstlich);
		gl_pnDienstlich.setHorizontalGroup(
			gl_pnDienstlich.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnDienstlich.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnDienstlich.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMitgliedstatus)
						.addComponent(lblMitgliedSeit))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnDienstlich.createParallelGroup(Alignment.LEADING, false)
						.addComponent(IEintritt, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(cbStatus, 0, 169, Short.MAX_VALUE))
					.addContainerGap(104, Short.MAX_VALUE))
		);
		gl_pnDienstlich.setVerticalGroup(
			gl_pnDienstlich.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnDienstlich.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_pnDienstlich.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMitgliedstatus)
						.addComponent(cbStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnDienstlich.createParallelGroup(Alignment.BASELINE)
						.addComponent(IEintritt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMitgliedSeit))
					.addContainerGap())
		);
		pnDienstlich.setLayout(gl_pnDienstlich);
		
		lblTelefonnummer = new JLabel("Telefonnummern:");
		
		lblFestnetzprivat = new JLabel("Festnetz (privat):");
		
		lblHandyprivat = new JLabel("Handy (privat):");
		
		lblDienstlich = new JLabel("Dienstlich:");
		
		IFestnetz = new JTextField();
		IFestnetz.setColumns(10);
		Bindings.bind(IFestnetz, festnetz);
		
		IHandy = new JTextField();
		IHandy.setColumns(10);
		Bindings.bind(IHandy,handy);
		
		IDienstl = new JTextField();
		IDienstl.setColumns(10);
		Bindings.bind(IDienstl, dienstlich);
		
		GroupLayout gl_tb_phone = new GroupLayout(tb_phone);
		gl_tb_phone.setHorizontalGroup(
			gl_tb_phone.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tb_phone.createSequentialGroup()
					.addGroup(gl_tb_phone.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_tb_phone.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_tb_phone.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFestnetzprivat)
								.addComponent(lblDienstlich, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblHandyprivat, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_tb_phone.createParallelGroup(Alignment.LEADING)
								.addComponent(IFestnetz, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
								.addComponent(IHandy, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
								.addComponent(IDienstl, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblTelefonnummer))
					.addGap(36))
		);
		gl_tb_phone.setVerticalGroup(
			gl_tb_phone.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tb_phone.createSequentialGroup()
					.addComponent(lblTelefonnummer)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_tb_phone.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFestnetzprivat)
						.addComponent(IFestnetz, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_tb_phone.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHandyprivat)
						.addComponent(IHandy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_tb_phone.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDienstlich)
						.addComponent(IDienstl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		tb_phone.setLayout(gl_tb_phone);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);


	}

	protected void setSeachListener(ActionListener al) {
		bsearch.addActionListener(al);
		bsearch.setActionCommand("seach");
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