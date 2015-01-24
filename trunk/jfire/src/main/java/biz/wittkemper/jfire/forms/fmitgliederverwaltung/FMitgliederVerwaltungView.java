package biz.wittkemper.jfire.forms.fmitgliederverwaltung;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.beans.PropertyVetoException;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import biz.wittkemper.jfire.data.entity.Anrede;
import biz.wittkemper.jfire.data.entity.MitgliedStatus;
import biz.wittkemper.jfire.forms.fmitgliederverwaltung.FMitgliederVerwaltungController.EDITMODE;
import biz.wittkemper.jfire.utils.IconService;
import biz.wittkemper.jfire.utils.IconService.ICONSERVICE;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.adapter.ComboBoxAdapter;
import com.jgoodies.binding.value.ConverterFactory;
import com.jgoodies.binding.value.Trigger;
import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.validation.view.ValidationComponentUtils;

public class FMitgliederVerwaltungView extends JInternalFrame {
	/**
	 * 
	 */
	MitgliedModel model = new MitgliedModel();
	boolean masterdb = false;
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
	ValueModel foerderEintritt;
	ValueModel anrede;
	ValueModel rettungsdienst;
	ValueModel datenfreigabe;

	public Trigger trigger;
	private final JToolBar TBMain;
	private final JPanel panel;
	JButton btnSave;
	JButton btnbeenden;
	private final JTextField Iname = new JTextField();
	private final JTextField IVorname;
	private final JLabel lblStrasse;
	private final JTextField IStrasse;
	private final JTextField IPlz;
	private final JLabel lblWohnort;
	private final JTextField Iort;
	private final JTextField ISearch;
	JButton bsearch;
	private final JPanel tb_phone;
	private final JLabel lblTelefonnummer;
	private final JLabel lblFestnetzprivat;
	private final JLabel lblHandyprivat;
	private final JLabel lblDienstlich;
	private final JTextField IFestnetz;
	private final JTextField IHandy;
	private final JTextField IDienstl;
	private final JLabel lblEmail;
	private final JTextField Iemail;
	private final JXDatePicker datePicker;
	private final JLabel lblGeburtsdatum;
	private final JPanel pnDienstlich;
	private final JLabel lblMitgliedSeit;
	private final JXDatePicker IEintritt;
	private final JLabel lblMitgliedstatus;
	private final JComboBox cbStatus;
	private final JButton btnLeft;
	private final JButton btnRight;
	private final JButton btnEdit;
	private final JButton btnNew;
	private final JButton btnDel;
	private final JButton btnFoerderMitglied;
	private final JLabel lbMitglied;
	private final JPanel tpFoerderMitglied;
	private final JPanel tpFuehrerscheine;
	private final JLabel lblMitgliedSeit_1;
	private final JXDatePicker dPFoerderMitglied;
	private final JTabbedPane tbStammdaten;
	private final JComboBox cbAnrede;
	private final JLabel lblAnrede;
	private final JCheckBox cbRettungsdienst;
	private final JCheckBox cbDatenfreigabe;
	private final JXTable tbFuehrerscheine;
	private final JLabel lblFuehrerscheine;
	private final JToolBar toolBarFueherschein;
	private final JButton btnNewFuehrerschein;
	private final JButton btnEditFuehrerschein;
	private final JButton btnDelFuehrerschein;

	public void setNewUserActice(boolean value) {
		btnNew.setEnabled(value);
		if (value == false) {
			masterdb = false;
			btnNew.setToolTipText("Neue Mitglieser können nur auf dem Master eingefügt werden");
		} else {
			masterdb = true;
			btnNew.setToolTipText("Neues Mitglied einfügen.");
		}
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public FMitgliederVerwaltungView() throws Exception {
		setName("Mitgliederverwaltung");
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
		foerderEintritt = pmodel.getBufferedModel("foerderMitgliedSeit");
		anrede = pmodel.getBufferedModel("anrede");

		rettungsdienst = pmodel.getBufferedModel("rettungsdienst");
		datenfreigabe = pmodel.getBufferedModel("datenfreigabe");

		List<MitgliedStatus> gueltigStatus = model.getMitgliedStatuse();
		List<Anrede> anreden = model.getAnreden();

		getContentPane().setLayout(new BorderLayout(0, 0));

		TBMain = new JToolBar();
		getContentPane().add(TBMain, BorderLayout.NORTH);

		ISearch = new JTextField();
		TBMain.add(ISearch);
		ISearch.setColumns(10);
		ISearch.setMaximumSize(new Dimension(120,
				TBMain.getPreferredSize().height));

		bsearch = new JButton("");
		bsearch.setIcon(iconService.getButtonIcon(ICONSERVICE.search));
		bsearch.setMaximumSize(new Dimension(23, 23));
		TBMain.add(bsearch);

		TBMain.addSeparator();
		TBMain.addSeparator();

		btnLeft = new JButton("");
		btnLeft.setToolTipText("ein Mitglied zurück");
		btnLeft.setIcon(iconService.getButtonIcon(ICONSERVICE.left));
		btnLeft.setMaximumSize(new Dimension(23, 23));
		TBMain.add(btnLeft);

		btnRight = new JButton("");
		btnRight.setToolTipText("nächstes Mitglies");
		btnRight.setIcon(iconService.getButtonIcon(ICONSERVICE.right));
		btnRight.setMaximumSize(new Dimension(23, 23));
		TBMain.add(btnRight);
		TBMain.addSeparator();

		btnNew = new JButton();
		btnNew.setToolTipText("Neues Mitglied einfügen");
		btnNew.setIcon(iconService.getButtonIcon(ICONSERVICE.newuser));
		btnNew.setMaximumSize(new Dimension(23, 23));
		TBMain.add(btnNew);

		btnEdit = new JButton();
		btnEdit.setToolTipText("Mitglied bearbeiten");
		btnEdit.setIcon(iconService.getButtonIcon(ICONSERVICE.edituser));
		btnEdit.setMaximumSize(new Dimension(23, 23));
		TBMain.add(btnEdit);

		btnDel = new JButton();
		btnDel.setToolTipText("Mitglied löschen");
		btnDel.setIcon(iconService.getButtonIcon(ICONSERVICE.delete));
		btnDel.setMaximumSize(new Dimension(23, 23));
		TBMain.add(btnDel);

		TBMain.addSeparator();

		btnFoerderMitglied = new JButton();
		btnFoerderMitglied.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFoerderMitglied.setToolTipText("Eintritt Förderverein");
		btnFoerderMitglied.setIcon(iconService
				.getButtonIcon(ICONSERVICE.eintrittFoerderverein));
		btnFoerderMitglied.setMaximumSize(new Dimension(23, 23));
		TBMain.add(btnFoerderMitglied);

		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);

		btnbeenden = new JButton("beenden");
		btnSave = new JButton("speichern");

		tbStammdaten = new JTabbedPane(JTabbedPane.TOP);

		lbMitglied = new JLabel(" ");
		lbMitglied.setFont(new Font("Dialog", Font.BOLD, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														Alignment.TRAILING,
														gl_panel.createSequentialGroup()
																.addContainerGap()
																.addComponent(
																		tbStammdaten,
																		GroupLayout.DEFAULT_SIZE,
																		859,
																		Short.MAX_VALUE))
												.addGroup(
														gl_panel.createSequentialGroup()
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
														gl_panel.createSequentialGroup()
																.addContainerGap()
																.addComponent(
																		lbMitglied,
																		GroupLayout.PREFERRED_SIZE,
																		335,
																		GroupLayout.PREFERRED_SIZE)))
								.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(
				Alignment.TRAILING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGap(6)
								.addComponent(lbMitglied)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(tbStammdaten,
										GroupLayout.DEFAULT_SIZE, 418,
										Short.MAX_VALUE)
								.addGap(18)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(btnbeenden)
												.addComponent(btnSave))
								.addContainerGap()));

		Iname.setColumns(10);
		Bindings.bind(Iname, name);
		ValidationComponentUtils.setMandatory(Iname, true);
		ValidationComponentUtils.setMessageKey(Iname, "form.name");

		IVorname = new JTextField();
		IVorname.setColumns(10);
		Bindings.bind(IVorname, vorname);

		IStrasse = new JTextField();
		IStrasse.setColumns(10);

		Bindings.bind(IStrasse, strasse);

		Iort = new JTextField();
		Iort.setColumns(10);
		Bindings.bind(Iort, ort);

		IPlz = new JTextField();
		IPlz.setColumns(10);
		Bindings.bind(IPlz, ConverterFactory.createStringConverter(plz,
				new DecimalFormat("0")));

		JPanel panel_1 = new JPanel();
		tbStammdaten.addTab("Stammdaten" + "", null, panel_1, null);
		JLabel lblName = new JLabel("Name:");

		lblStrasse = new JLabel("Strasse:");

		lblWohnort = new JLabel("Wohnort:");

		tb_phone = new JPanel();
		tb_phone.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));

		lblEmail = new JLabel("E-Mail:");

		Iemail = new JTextField();
		Iemail.setColumns(10);
		Bindings.bind(Iemail, email);

		datePicker = new JXDatePicker();
		Bindings.bind(datePicker, "date", gebDatum);
		lblGeburtsdatum = new JLabel("Geburtstag:");

		pnDienstlich = new JPanel();
		pnDienstlich.setBorder(new BevelBorder(BevelBorder.RAISED, null, null,
				null, null));

		lblAnrede = new JLabel("Anrede");
		cbAnrede = new JComboBox(new ComboBoxAdapter<Anrede>(anreden, anrede));

		cbRettungsdienst = new JCheckBox("Mitglied Rettungsdienst");
		Bindings.bind(cbRettungsdienst, rettungsdienst);

		cbDatenfreigabe = new JCheckBox("Datenfreigabe erteilt");
		Bindings.bind(cbDatenfreigabe, datenfreigabe);

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1
				.setHorizontalGroup(gl_panel_1
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel_1
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_panel_1
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_panel_1
																		.createSequentialGroup()
																		.addComponent(
																				pnDienstlich,
																				GroupLayout.DEFAULT_SIZE,
																				415,
																				Short.MAX_VALUE)
																		.addGap(434))
														.addGroup(
																gl_panel_1
																		.createSequentialGroup()
																		.addGroup(
																				gl_panel_1
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblAnrede)
																						.addGroup(
																								gl_panel_1
																										.createSequentialGroup()
																										.addGroup(
																												gl_panel_1
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addComponent(
																																lblName)
																														.addComponent(
																																lblStrasse)
																														.addComponent(
																																lblWohnort,
																																GroupLayout.PREFERRED_SIZE,
																																79,
																																GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																lblGeburtsdatum,
																																GroupLayout.PREFERRED_SIZE,
																																100,
																																GroupLayout.PREFERRED_SIZE))
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addGroup(
																												gl_panel_1
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addComponent(
																																IStrasse,
																																162,
																																162,
																																162)
																														.addGroup(
																																gl_panel_1
																																		.createSequentialGroup()
																																		.addComponent(
																																				IPlz,
																																				GroupLayout.PREFERRED_SIZE,
																																				61,
																																				GroupLayout.PREFERRED_SIZE)
																																		.addPreferredGap(
																																				ComponentPlacement.RELATED)
																																		.addComponent(
																																				Iort,
																																				GroupLayout.PREFERRED_SIZE,
																																				GroupLayout.DEFAULT_SIZE,
																																				GroupLayout.PREFERRED_SIZE))
																														.addGroup(
																																gl_panel_1
																																		.createSequentialGroup()
																																		.addGroup(
																																				gl_panel_1
																																						.createParallelGroup(
																																								Alignment.TRAILING,
																																								false)
																																						.addComponent(
																																								cbAnrede,
																																								Alignment.LEADING,
																																								0,
																																								GroupLayout.DEFAULT_SIZE,
																																								Short.MAX_VALUE)
																																						.addComponent(
																																								IVorname,
																																								Alignment.LEADING,
																																								GroupLayout.PREFERRED_SIZE,
																																								128,
																																								GroupLayout.PREFERRED_SIZE))
																																		.addPreferredGap(
																																				ComponentPlacement.RELATED)
																																		.addComponent(
																																				Iname,
																																				GroupLayout.PREFERRED_SIZE,
																																				162,
																																				GroupLayout.PREFERRED_SIZE))
																														.addComponent(
																																Iemail,
																																GroupLayout.PREFERRED_SIZE,
																																213,
																																GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																datePicker,
																																GroupLayout.PREFERRED_SIZE,
																																GroupLayout.DEFAULT_SIZE,
																																GroupLayout.PREFERRED_SIZE)))
																						.addComponent(
																								lblEmail))
																		.addGap(91)
																		.addGroup(
																				gl_panel_1
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								cbRettungsdienst)
																						.addComponent(
																								tb_phone,
																								GroupLayout.PREFERRED_SIZE,
																								338,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								cbDatenfreigabe,
																								GroupLayout.PREFERRED_SIZE,
																								196,
																								GroupLayout.PREFERRED_SIZE))
																		.addContainerGap(
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)))));
		gl_panel_1
				.setVerticalGroup(gl_panel_1
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel_1
										.createSequentialGroup()
										.addGap(20)
										.addGroup(
												gl_panel_1
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																gl_panel_1
																		.createParallelGroup(
																				Alignment.BASELINE)
																		.addComponent(
																				datePicker,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				lblGeburtsdatum))
														.addGroup(
																gl_panel_1
																		.createSequentialGroup()
																		.addGroup(
																				gl_panel_1
																						.createParallelGroup(
																								Alignment.LEADING,
																								false)
																						.addComponent(
																								tb_phone,
																								GroupLayout.PREFERRED_SIZE,
																								125,
																								GroupLayout.PREFERRED_SIZE)
																						.addGroup(
																								gl_panel_1
																										.createSequentialGroup()
																										.addGroup(
																												gl_panel_1
																														.createParallelGroup(
																																Alignment.BASELINE)
																														.addComponent(
																																cbAnrede,
																																GroupLayout.PREFERRED_SIZE,
																																GroupLayout.DEFAULT_SIZE,
																																GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																lblAnrede))
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addGroup(
																												gl_panel_1
																														.createParallelGroup(
																																Alignment.BASELINE)
																														.addComponent(
																																lblName)
																														.addComponent(
																																IVorname,
																																GroupLayout.PREFERRED_SIZE,
																																GroupLayout.DEFAULT_SIZE,
																																GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																Iname,
																																GroupLayout.PREFERRED_SIZE,
																																GroupLayout.DEFAULT_SIZE,
																																GroupLayout.PREFERRED_SIZE))
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addGroup(
																												gl_panel_1
																														.createParallelGroup(
																																Alignment.BASELINE)
																														.addComponent(
																																lblStrasse)
																														.addComponent(
																																IStrasse,
																																GroupLayout.PREFERRED_SIZE,
																																GroupLayout.DEFAULT_SIZE,
																																GroupLayout.PREFERRED_SIZE))
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addGroup(
																												gl_panel_1
																														.createParallelGroup(
																																Alignment.BASELINE)
																														.addComponent(
																																IPlz,
																																GroupLayout.PREFERRED_SIZE,
																																GroupLayout.DEFAULT_SIZE,
																																GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																Iort,
																																GroupLayout.PREFERRED_SIZE,
																																GroupLayout.DEFAULT_SIZE,
																																GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																lblWohnort))
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addGroup(
																												gl_panel_1
																														.createParallelGroup(
																																Alignment.BASELINE)
																														.addComponent(
																																lblEmail)
																														.addComponent(
																																Iemail,
																																GroupLayout.PREFERRED_SIZE,
																																GroupLayout.DEFAULT_SIZE,
																																GroupLayout.PREFERRED_SIZE))))
																		.addGap(18)
																		.addComponent(
																				cbRettungsdienst)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(cbDatenfreigabe)
										.addPreferredGap(
												ComponentPlacement.RELATED, 91,
												Short.MAX_VALUE)
										.addComponent(pnDienstlich,
												GroupLayout.PREFERRED_SIZE, 77,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));

		lblMitgliedSeit = new JLabel("Mitglied seit: ");

		IEintritt = new JXDatePicker();
		Bindings.bind(IEintritt, "date", eintritt);

		lblMitgliedstatus = new JLabel("Mitgliedstatus ");

		cbStatus = new JComboBox(new ComboBoxAdapter<MitgliedStatus>(
				gueltigStatus, status));

		GroupLayout gl_pnDienstlich = new GroupLayout(pnDienstlich);
		gl_pnDienstlich.setHorizontalGroup(gl_pnDienstlich.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_pnDienstlich
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_pnDienstlich
										.createParallelGroup(Alignment.LEADING)
										.addComponent(lblMitgliedstatus)
										.addComponent(lblMitgliedSeit))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_pnDienstlich
										.createParallelGroup(Alignment.LEADING,
												false)
										.addComponent(IEintritt,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(cbStatus, 0, 169,
												Short.MAX_VALUE))
						.addContainerGap(104, Short.MAX_VALUE)));
		gl_pnDienstlich
				.setVerticalGroup(gl_pnDienstlich
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_pnDienstlich
										.createSequentialGroup()
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												gl_pnDienstlich
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblMitgliedstatus)
														.addComponent(
																cbStatus,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_pnDienstlich
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																IEintritt,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblMitgliedSeit))
										.addContainerGap()));
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
		Bindings.bind(IHandy, handy);

		IDienstl = new JTextField();
		IDienstl.setColumns(10);
		Bindings.bind(IDienstl, dienstlich);

		GroupLayout gl_tb_phone = new GroupLayout(tb_phone);
		gl_tb_phone
				.setHorizontalGroup(gl_tb_phone
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_tb_phone
										.createSequentialGroup()
										.addGroup(
												gl_tb_phone
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_tb_phone
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				gl_tb_phone
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblFestnetzprivat)
																						.addComponent(
																								lblDienstlich,
																								GroupLayout.PREFERRED_SIZE,
																								123,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblHandyprivat,
																								GroupLayout.PREFERRED_SIZE,
																								123,
																								GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				gl_tb_phone
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								IFestnetz,
																								GroupLayout.PREFERRED_SIZE,
																								151,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								IHandy,
																								GroupLayout.PREFERRED_SIZE,
																								151,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								IDienstl,
																								GroupLayout.PREFERRED_SIZE,
																								151,
																								GroupLayout.PREFERRED_SIZE)))
														.addComponent(
																lblTelefonnummer))
										.addGap(36)));
		gl_tb_phone
				.setVerticalGroup(gl_tb_phone
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_tb_phone
										.createSequentialGroup()
										.addComponent(lblTelefonnummer)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_tb_phone
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblFestnetzprivat)
														.addComponent(
																IFestnetz,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_tb_phone
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblHandyprivat)
														.addComponent(
																IHandy,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_tb_phone
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblDienstlich)
														.addComponent(
																IDienstl,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(25, Short.MAX_VALUE)));
		tb_phone.setLayout(gl_tb_phone);
		panel_1.setLayout(gl_panel_1);

		tpFuehrerscheine = new JPanel();
		tbStammdaten.addTab("Führerscheine", null, tpFuehrerscheine, null);

		tbFuehrerscheine = new JXTable(model.getTableModel());
		tbFuehrerscheine.setHighlighters(HighlighterFactory
				.createAlternateStriping());
		tbFuehrerscheine
				.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		lblFuehrerscheine = new JLabel("Führerscheine");

		toolBarFueherschein = new JToolBar();
		toolBarFueherschein.setFloatable(false);
		GroupLayout gl_tpFuehrerscheine = new GroupLayout(tpFuehrerscheine);
		gl_tpFuehrerscheine
				.setHorizontalGroup(gl_tpFuehrerscheine
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_tpFuehrerscheine
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_tpFuehrerscheine
														.createParallelGroup(
																Alignment.LEADING,
																false)
														.addComponent(
																tbFuehrerscheine,
																GroupLayout.PREFERRED_SIZE,
																286,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																gl_tpFuehrerscheine
																		.createSequentialGroup()
																		.addComponent(
																				lblFuehrerscheine)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				toolBarFueherschein,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(563, Short.MAX_VALUE)));
		gl_tpFuehrerscheine
				.setVerticalGroup(gl_tpFuehrerscheine
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_tpFuehrerscheine
										.createSequentialGroup()
										.addGap(42)
										.addGroup(
												gl_tpFuehrerscheine
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																lblFuehrerscheine)
														.addComponent(
																toolBarFueherschein,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(tbFuehrerscheine,
												GroupLayout.PREFERRED_SIZE,
												238, GroupLayout.PREFERRED_SIZE)
										.addContainerGap(82, Short.MAX_VALUE)));

		btnNewFuehrerschein = new JButton();
		btnNewFuehrerschein.setIcon(iconService
				.getButtonIcon(ICONSERVICE.newuser));
		btnNewFuehrerschein.setToolTipText("Führerschein hinzufügen");
		btnEditFuehrerschein = new JButton();
		btnEditFuehrerschein.setIcon(iconService
				.getButtonIcon(ICONSERVICE.edituser));
		btnEditFuehrerschein.setToolTipText("Führerschein bearbeiten");
		btnDelFuehrerschein = new JButton();
		btnDelFuehrerschein.setIcon(iconService
				.getButtonIcon(ICONSERVICE.delete));
		btnDelFuehrerschein.setToolTipText("Führerschein entfernen");

		toolBarFueherschein.add(btnNewFuehrerschein);
		toolBarFueherschein.add(btnEditFuehrerschein);
		toolBarFueherschein.add(btnDelFuehrerschein);

		tpFuehrerscheine.setLayout(gl_tpFuehrerscheine);
		tpFoerderMitglied = new JPanel();
		tbStammdaten.addTab("Förderverein", null, tpFoerderMitglied, null);

		lblMitgliedSeit_1 = new JLabel("Mitglied seit: ");

		dPFoerderMitglied = new JXDatePicker();
		GroupLayout gl_tpFoerderMitglied = new GroupLayout(tpFoerderMitglied);
		gl_tpFoerderMitglied.setHorizontalGroup(gl_tpFoerderMitglied
				.createParallelGroup(Alignment.LEADING).addGroup(
						gl_tpFoerderMitglied
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblMitgliedSeit_1)
								.addGap(4)
								.addComponent(dPFoerderMitglied,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(623, Short.MAX_VALUE)));
		gl_tpFoerderMitglied
				.setVerticalGroup(gl_tpFoerderMitglied
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_tpFoerderMitglied
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_tpFoerderMitglied
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblMitgliedSeit_1)
														.addComponent(
																dPFoerderMitglied,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(362, Short.MAX_VALUE)));
		tpFoerderMitglied.setLayout(gl_tpFoerderMitglied);

		Bindings.bind(dPFoerderMitglied, "date", foerderEintritt);
		panel.setLayout(gl_panel);
		tbStammdaten.setSelectedIndex(0);

	}

	protected void setSeachListener(ActionListener al) {
		bsearch.addActionListener(al);
		bsearch.setActionCommand("search");
		btnLeft.addActionListener(al);
		btnLeft.setActionCommand("left");
		btnRight.addActionListener(al);
		btnRight.setActionCommand("right");
		btnNew.addActionListener(al);
		btnNew.setActionCommand("new");
		btnEdit.addActionListener(al);
		btnEdit.setActionCommand("edit");
		btnFoerderMitglied.addActionListener(al);
		btnFoerderMitglied.setActionCommand("newFoerdermitglied");
	}

	protected void setSaveListener(ActionListener al) {
		btnSave.addActionListener(al);
	}

	protected void setBeendenListener(ActionListener al) {
		btnbeenden.addActionListener(al);
	}

	protected void setSeachKeyListener(KeyListener listener) {
		ISearch.addKeyListener(listener);
	}

	protected void setDeleteListener(ActionListener listener) {
		btnDel.addActionListener(listener);
	}

	public MitgliedModel getModel() {
		return this.model;
	}

	public void setModel(MitgliedModel model) {
		this.model = model;
		tbStammdaten.setSelectedIndex(0);
		model.getTableModel().fireTableDataChanged();
	}

	public String getSearchText() {
		String lsearch = ISearch.getText();
		ISearch.setText("");
		return lsearch;
	}

	public void setMitgliedLabel(String text) {
		lbMitglied.setText(text);
	}

	public void SetFoerderVerein(boolean value) {
		tbStammdaten.setEnabledAt(2, value);
		dPFoerderMitglied.setVisible(value);
		btnFoerderMitglied.setVisible(!value);

	}

	public void setNewFoerderMitglied() {
		tbStammdaten.setEnabledAt(2, true);
		tbStammdaten.setSelectedIndex(2);
		dPFoerderMitglied.setEnabled(true);
		dPFoerderMitglied.setDate(model.getEintritt());

	}

	public void enableImput(boolean value) {

		IDienstl.setEnabled(value);
		IEintritt.setEnabled(value);
		Iemail.setEnabled(value);
		IFestnetz.setEnabled(value);
		IHandy.setEnabled(value);
		Iname.setEnabled(value);
		Iort.setEnabled(value);
		IPlz.setEnabled(value);
		IVorname.setEnabled(value);
		IStrasse.setEnabled(value);
		datePicker.setEnabled(value);
		cbStatus.setEnabled(value);
		btnSave.setEnabled(value);
		if (model.getFoerderMitglied() != null) {
			dPFoerderMitglied.setEnabled(value);
		} else {
			dPFoerderMitglied.setEnabled(false);
		}
		cbAnrede.setEnabled(value);
		cbRettungsdienst.setEnabled(value);
		cbDatenfreigabe.setEnabled(value);
		tpFuehrerscheine.setEnabled(value);
		toolBarFueherschein.setEnabled(value);
		if (value == false) {
			tbStammdaten.setSelectedIndex(0);
		}
	}

	public void setToolbar(EDITMODE value) {

		switch (value) {
		case NONE:
			bsearch.setEnabled(true);
			btnLeft.setEnabled(true);
			btnRight.setEnabled(true);
			ISearch.setEnabled(true);
			if (masterdb) {
				btnNew.setEnabled(true);
			}
			btnEdit.setEnabled(true);
			btnDel.setEnabled(false);
			btnFoerderMitglied.setEnabled(false);
			break;
		case EDIT:
			bsearch.setEnabled(false);
			btnLeft.setEnabled(false);
			btnRight.setEnabled(false);
			ISearch.setEnabled(false);
			btnNew.setEnabled(false);
			btnEdit.setEnabled(false);
			btnDel.setEnabled(true);
			btnFoerderMitglied.setEnabled(true);
			break;
		case NEW:
			bsearch.setEnabled(false);
			btnLeft.setEnabled(false);
			btnRight.setEnabled(false);
			ISearch.setEnabled(false);
			btnNew.setEnabled(false);
			btnEdit.setEnabled(false);
			btnDel.setEnabled(false);
			btnFoerderMitglied.setEnabled(true);
			break;
		}
	}
}
