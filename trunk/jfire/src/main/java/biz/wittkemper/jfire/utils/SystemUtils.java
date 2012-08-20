package biz.wittkemper.jfire.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.hibernate.JDBCException;

import biz.wittkemper.jfire.data.dao.DAOFactory;
import biz.wittkemper.jfire.data.dao.HibernateSession;
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

			try {
				newConfigFile();
				String pfad = getDBPfad() + File.separator + "seg0";
				File file = new File(pfad);

				if (file.exists()) {
					lreturn = true;
				}
			} catch (Exception e) {
				lreturn = false;
			}
		}

		return lreturn;
	}

	public String getPropertyValue(String name) {
		String lvalue = loadConfigFile().getProperty(name);
		if (lvalue == null) {
			lvalue = "";
		}
		return lvalue.trim();
	}

	public void savePropertyValue(Properties value) throws IOException {
		storeProperty(value);
	}

	public void savePropertyValue(String name, String value) throws IOException {

		Properties properties = loadConfigFile();
		properties.put(name, value);

		storeProperty(properties);

	}

	private void storeProperty(Properties properties) throws IOException {
		File propertyFile = getPropertieFile();
		FileOutputStream outputStream = new FileOutputStream(propertyFile);
		properties.save(outputStream, "jfire konfiguration");
		outputStream.close();
	}

	public String getDBPfad() {
		return getPropertyValue("DBPFAD");
	}

	private boolean newConfigFile() throws IOException {

		Properties properties = new Properties();
		properties.put("DBPFAD", getDBPFAD());
		storeProperty(properties);

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

		HibernateSession.beginTransaction();
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
		dbversion.setValue("4");

		DAOFactory.getInstance().getParameterDAO().save(dbtyp);
		DAOFactory.getInstance().getParameterDAO().save(dbversion);

		HibernateSession.commitTransaction();
	}

	public void checkDB() throws JDBCException, Exception {
		switch (ParameterUtils.getDBVersion()) {
		case 1:
			setVersion2();
		case 2:
			setVersion3();
		case 3:
			setVersion4();
		}
	}

	private void setVersion4() {
		HibernateSession.beginTransaction();
		try {
			String sql = " CREATE TABLE APP.MATERIAL";
			sql += "( TYPE varchar(31) NOT NULL, ";
			sql += "  ID bigint PRIMARY KEY NOT NULL,";
			sql += "  UEBERNAHME timestamp,";
			sql += "  SERIENNUMMER varchar(255),";
			sql += "   MASTERID bigint, ";
			sql += "  MATERIALTYP_ID bigint	)";
			executeSQL(sql);

			sql = " CREATE TABLE APP.MATERIALTYP ";
			sql += "(  TYPE varchar(31) NOT NULL,";
			sql += "    ID bigint PRIMARY KEY NOT NULL,";
			sql += "    BEZEICHNUNG varchar(255),";
			sql += "     MASTERID bigint, ";
			sql += "    HERSTELLER varchar(255))";
			executeSQL(sql);

			sql = " ALTER TABLE APP.MATERIAL ";
			sql += " ADD CONSTRAINT FK15ADC9476241992E ";
			sql += " FOREIGN KEY (MATERIALTYP_ID)";
			sql += " REFERENCES APP.MATERIALTYP(ID) ";
			executeSQL(sql);

			sql = "CREATE INDEX SQL120820102234880 ON APP.MATERIAL(MATERIALTYP_ID)";
			executeSQL(sql);

			sql = "CREATE UNIQUE INDEX SQL120820102234770 ON APP.MATERIAL(ID)";
			executeSQL(sql);

			setDBVersion(4);
			HibernateSession.commitTransaction();
		} catch (Exception e) {
			HibernateSession.rollbackTransaction();
			JOptionPane.showConfirmDialog(null,
					"Update fehlgeschlagen!\n" + e.getMessage(),
					"Fehler beim Update", JOptionPane.ERROR_MESSAGE
							+ JOptionPane.OK_OPTION);
		}

	}

	private void setVersion3() {

		HibernateSession.beginTransaction();

		try {
			String sql = "";
			sql = "ALTER TABLE MITGLIED add edit SMALLINT NOT NULL DEFAULT 0";
			executeSQL(sql);

			setDBVersion(3);
			HibernateSession.commitTransaction();
		} catch (SQLException e) {
			HibernateSession.rollbackTransaction();
			JOptionPane.showConfirmDialog(null,
					"Update fehlgeschlagen!\n" + e.getMessage(),
					"Fehler beim Update", JOptionPane.ERROR_MESSAGE
							+ JOptionPane.OK_OPTION);
		}
	}

	private void setDBVersion(int value) throws SQLException {
		String sql;
		sql = "Update Parameter set VALUE = '" + value
				+ "' WHERE BEZEICHNUNG = 'DBVERSION'";
		executeSQL(sql);
	}

	private void setVersion2() {

		HibernateSession.beginTransaction();

		try {
			String sql = "";
			sql = "ALTER TABLE MITGLIED add rettungsdienst SMALLINT NOT NULL DEFAULT 0";
			executeSQL(sql);
			setDBVersion(2);
			HibernateSession.commitTransaction();
		} catch (SQLException e) {
			HibernateSession.rollbackTransaction();
			JOptionPane.showConfirmDialog(null,
					"Update fehlgeschlagen!\n" + e.getMessage(),
					"Fehler beim Update", JOptionPane.ERROR_MESSAGE
							+ JOptionPane.OK_OPTION);
		}

	}

	private void executeSQL(String sql) throws SQLException {

		Connection con = HibernateSession.getInstance().getCurrentSession()
				.connection();

		Statement st = con.createStatement();
		st.execute(sql);

	}
}
