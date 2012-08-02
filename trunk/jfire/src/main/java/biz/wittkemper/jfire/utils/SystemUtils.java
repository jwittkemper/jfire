package biz.wittkemper.jfire.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.hibernate.Transaction;

import biz.wittkemper.jfire.data.dao.DAOFactory;
import biz.wittkemper.jfire.data.dao.SessionFactotyUtil;
import biz.wittkemper.jfire.data.entity.Anrede;
import biz.wittkemper.jfire.data.entity.MitgliedStatus;
import biz.wittkemper.jfire.data.entity.Parameter;

public class SystemUtils {
	final static String filename = "jfire.properties";

	public boolean getDBAvailable() {
		boolean lreturn = false;
		Properties configFile = loadConfigFile();

		if (configFile != null) {
			String pfad = getDBPfad() + File.separator + "seg0";
			File file = new File(pfad);
			try {
				if (file.exists()) {
					lreturn = true;
				}
			} catch (Exception e) {
				lreturn = false;
			}

		} else {

			newConfigFile();
			String pfad = getDBPfad() + File.separator + "seg0";
			File file = new File(pfad);
			try {
				if (file.exists()) {
					lreturn = true;
				}
			} catch (Exception e) {
				lreturn = false;
			}
		}

		return lreturn;
	}

	public String getDBPfad() {
		return loadConfigFile().getProperty("DBPFAD");
	}

	private boolean newConfigFile() {

		Properties properties = new Properties();
		properties.put("DBPFAD", getDBPFAD());
		File propertyFile = getPropertieFile();
		try {
			FileOutputStream outputStream = new FileOutputStream(propertyFile);
			properties.save(outputStream, "jfire konfiguration");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public String getOpenFileDialog(String title, boolean directories_only,
			FileNameExtensionFilter filter) {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle(title);
		chooser.setAcceptAllFileFilterUsed(false);

		if (filter != null) {
			chooser.setFileFilter(filter);
		}
		chooser.setFileFilter(new FileNameExtensionFilter(
				"JFire Daten(*.jfire)", "jfire"));

		if (directories_only) {
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		} else {
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		}
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile().getAbsolutePath();
		} else {
			return "";
		}
	}

	private String getDBPFAD() {
		String dbPfad = "";

		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Wo befindet sich die Datenbank/Wo soll die Datenbank angelegt werden?");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			// dbPfad += chooser.getCurrentDirectory();
			dbPfad += chooser.getSelectedFile();
		} else {
			dbPfad = "";
		}
		return dbPfad;
	}

	private File getPropertieFile() {
		File file;
		URL url = getClass().getProtectionDomain().getCodeSource()
				.getLocation();

		file = new File(url.getPath() + filename);

		return file;
	}

	private Properties loadConfigFile() {
		Properties file = new Properties();

		try {
			File datafile = getPropertieFile();
			FileInputStream inputStream = new FileInputStream(datafile);
			file.load(inputStream);
		} catch (Exception e) {
			file = null;
		}

		return file;
	}

	public void initDB() {

		Anrede frau = new Anrede();
		frau.setAnrede("Frau");

		Anrede herr = new Anrede();
		herr.setAnrede("Herr");
		DAOFactory.getInstance().getAnredeDAO().save(frau);
		DAOFactory.getInstance().getAnredeDAO().save(herr);

		MitgliedStatus statusAL = new MitgliedStatus();
		statusAL.setBezeichnungLang("Aktiv Löschzug");
		statusAL.setBezeichnungKurz("AL");
		DAOFactory.getInstance().getMitgliedStatusDAO().save(statusAL);

		MitgliedStatus statusAR = new MitgliedStatus();
		statusAR.setBezeichnungLang("Aktiv Reservezug");
		statusAR.setBezeichnungKurz("AR");
		DAOFactory.getInstance().getMitgliedStatusDAO().save(statusAR);

		MitgliedStatus statusPV = new MitgliedStatus();
		statusPV.setBezeichnungKurz("PS");
		statusPV.setBezeichnungLang("Passives Mitglied");
		DAOFactory.getInstance().getMitgliedStatusDAO().save(statusPV);

		Parameter dbtyp = new Parameter();
		dbtyp.setBezeichnung("DBTYP");
		dbtyp.setValue("slavedb");

		Parameter dbversion = new Parameter();
		dbversion.setBezeichnung("DBVERSION");
		dbversion.setValue("2");

		DAOFactory.getInstance().getParameterDAO().save(dbtyp);
		DAOFactory.getInstance().getParameterDAO().save(dbversion);
	}

	public void checkDB() {
		switch (ParameterUtils.getDBVersion()) {
		case 1:
			setVersion2();
		}
	}

	private void setVersion2() {

		Transaction tx = SessionFactotyUtil.getInstance().getCurrentSession()
				.beginTransaction();

		try {
			String sql = "";
			sql = "ALTER TABLE MITGLIED add rettungsdienst SMALLINT NOT NULL DEFAULT 0";
			executeSQL(sql);
			sql = "Update Parameter set VALUE = '2' WHERE BEZEICHNUNG = 'DBVERSION'";
			executeSQL(sql);
			tx.commit();
		} catch (SQLException e) {
			tx.rollback();
			JOptionPane.showConfirmDialog(null,
					"Update fehlgeschlagen!\n" + e.getMessage(),
					"Fehler beim Update", JOptionPane.ERROR_MESSAGE
							+ JOptionPane.OK_OPTION);
		}

	}

	private void executeSQL(String sql) throws SQLException {

		Connection con = SessionFactotyUtil.getInstance().getCurrentSession()
				.connection();

		Statement st = con.createStatement();
		st.execute(sql);

	}
}
