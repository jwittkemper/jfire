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
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import biz.wittkemper.jfire.Start;
import biz.wittkemper.jfire.data.dao.DAOFactory;
import biz.wittkemper.jfire.data.dao.JPAEntityManager;
import biz.wittkemper.jfire.data.entity.Anrede;
import biz.wittkemper.jfire.data.entity.MitgliedStatus;
import biz.wittkemper.jfire.data.entity.Parameter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.transaction.Transaction;

public class SystemUtils {
	
	private static final Logger log = LoggerFactory.getLogger(SystemUtils.class);
	
	final static String filename = "jfire.properties";

	public boolean getDBAvailable() {
		boolean lreturn = false;
		Properties configFile = loadConfigFile();

		if (configFile != null) {
			String pfad = getDBPfad() + File.separator + "seg0";
			File file = new File(pfad);
			try {
				if (file.exists()) {
					log.debug("Database found at: " + file.getPath());
					lreturn = true;
				}else {
					log.debug("No Database was found.");
					lreturn = false;
				}
			} catch (Exception e) {
				log.debug("No Database was found.");
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

	public void initDB() throws Exception {

		EntityTransaction tr = JPAEntityManager.getTransaction();
		tr.begin();
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
		dbversion.setValue("7");

		DAOFactory.getInstance().getParameterDAO().save(dbtyp);
		DAOFactory.getInstance().getParameterDAO().save(dbversion);

		tr.commit();
	}

	public void checkDB() throws JDBCException, Exception {
		switch (ParameterUtils.getDBVersion()) {
		case 1:
			setVersion2();
		case 2:
			setVersion3();
		case 3:
			setVersion4();
		case 4:
			setVersion5();
		case 5:
			setVersion6();
		case 6:
			setVersion7();
		}
	}

	private void setVersion7() throws Exception {
		EntityTransaction tr = JPAEntityManager.getTransaction();
		tr.begin();
		try {
			String sql = " CREATE TABLE APP.FUEHRERSCHEIN";
			sql += " ( ";
			sql += " FID bigint not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) ,";
			sql += " BEFRISTET smallint NOT NULL, ";
			sql += " BEZEICHNUNG varchar(3) ";
			sql += " ) ";
			executeSQL(sql);

			sql = "CREATE UNIQUE INDEX SQL121231122719600 ON APP.FUEHRERSCHEIN(FID)";
			executeSQL(sql);

			sql = " CREATE TABLE APP.MITGLIED_FUEHRERSCHEIN ";
			sql += " ( ";
			sql += " BEFRISTETBIS timestamp, ";
			sql += " FID bigint NOT NULL, ";
			sql += " ID bigint NOT NULL, ";
			sql += " CONSTRAINT SQL121231122721050 PRIMARY KEY (FID,ID)  ";
			sql += " )";
			executeSQL(sql);

			sql = " ALTER TABLE APP.MITGLIED_FUEHRERSCHEIN ";
			sql += " ADD CONSTRAINT FK556C2C4B1DD9CBF2 ";
			sql += " FOREIGN KEY (ID) ";
			sql += " REFERENCES APP.MITGLIED(ID) ";
			executeSQL(sql);

			sql = " ALTER TABLE APP.MITGLIED_FUEHRERSCHEIN ";
			sql += " ADD CONSTRAINT FK556C2C4B4158634 ";
			sql += " FOREIGN KEY (FID) ";
			sql += " REFERENCES APP.FUEHRERSCHEIN(FID) ";
			executeSQL(sql);

			executeSQL("CREATE INDEX SQL121231122722300 ON APP.MITGLIED_FUEHRERSCHEIN(FID)");
			executeSQL("CREATE INDEX SQL121231122722170 ON APP.MITGLIED_FUEHRERSCHEIN(ID)");

			sql = " CREATE UNIQUE INDEX SQL121231122721050 ON APP.MITGLIED_FUEHRERSCHEIN";
			sql += "( FID, ID) ";
			executeSQL(sql);

			executeSQL("INSERT INTO FUEHRERSCHEIN (BEZEICHNUNG, BEFRISTET) values ('2', 0)");
			executeSQL("INSERT INTO FUEHRERSCHEIN (BEZEICHNUNG, BEFRISTET) values ('3', 0)");
			executeSQL("INSERT INTO FUEHRERSCHEIN (BEZEICHNUNG, BEFRISTET) values ('B', 0)");
			executeSQL("INSERT INTO FUEHRERSCHEIN (BEZEICHNUNG, BEFRISTET) values ('BE', 0)");
			executeSQL("INSERT INTO FUEHRERSCHEIN (BEZEICHNUNG, BEFRISTET) values ('C', 1)");
			executeSQL("INSERT INTO FUEHRERSCHEIN (BEZEICHNUNG, BEFRISTET) values ('C1', 1)");
			executeSQL("INSERT INTO FUEHRERSCHEIN (BEZEICHNUNG, BEFRISTET) values ('CE', 1)");
			executeSQL("INSERT INTO FUEHRERSCHEIN (BEZEICHNUNG, BEFRISTET) values ('C1E', 1)");

			setDBVersion(7);

			tr.commit();
		} catch (Exception ex) {
			tr.rollback();
			JOptionPane.showConfirmDialog(null,
					"Update fehlgeschlagen!\n" + ex.getMessage(),
					"Fehler beim Update", JOptionPane.ERROR_MESSAGE
							+ JOptionPane.OK_OPTION);
		}
	}

	private void setVersion6() throws Exception {
		EntityTransaction tr = JPAEntityManager.getTransaction();
		tr.begin();
	
		try {

			String sql = "";
			sql = "ALTER TABLE MITGLIED add datenfreigabe SMALLINT NOT NULL DEFAULT 0";
			executeSQL(sql);

			setDBVersion(6);
			tr.commit();

		} catch (Exception e) {
			tr.rollback();
			JOptionPane.showConfirmDialog(null,
					"Update fehlgeschlagen!\n" + e.getMessage(),
					"Fehler beim Update", JOptionPane.ERROR_MESSAGE
							+ JOptionPane.OK_OPTION);
		}

	}

	private void setVersion5() throws Exception {
		EntityTransaction tr = JPAEntityManager.getTransaction();
		tr.begin();

		try {

			String sql = "";
			sql = "ALTER TABLE MITGLIED add loeschgrund SMALLINT";
			executeSQL(sql);

			sql = "ALTER TABLE MITGLIED add geloeschtAM timestamp";
			executeSQL(sql);

			setDBVersion(5);
			tr.commit();

		} catch (Exception e) {
			tr.rollback();
			JOptionPane.showConfirmDialog(null,
					"Update fehlgeschlagen!\n" + e.getMessage(),
					"Fehler beim Update", JOptionPane.ERROR_MESSAGE
							+ JOptionPane.OK_OPTION);
		}

	}

	private void setVersion4() throws Exception {
		EntityTransaction tr = JPAEntityManager.getTransaction();
		tr.begin();
		
		try {
			String sql = " CREATE TABLE APP.MATERIAL";
			sql += "( TYPE varchar(31) NOT NULL, ";
			sql += "  MATERIAL_ID bigint not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),";
			sql += "  UEBERNAHME timestamp,";
			sql += "  SERIENNUMMER varchar(255),";
			sql += "   MASTERID bigint, ";
			sql += "  MATERIALTYP_ID bigint	)";
			executeSQL(sql);

			sql = " CREATE TABLE APP.MATERIALTYP ";
			sql += "(  TYPE varchar(31) NOT NULL,";
			sql += "    ID bigint not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),";
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

			sql = "CREATE UNIQUE INDEX SQL120820102234770 ON APP.MATERIAL(MATERIAL_ID)";
			executeSQL(sql);

			sql = " CREATE TABLE APP.MELDERSCHLEIFEN ";
			sql += "(  ";
			sql += "    SCHLEIFE_ID bigint not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),";
			sql += "    BEZEICHNUNG varchar(20),";
			sql += "    MASTERID bigint, ";
			sql += "    RIC varchar(10))";
			executeSQL(sql);

			sql = " CREATE TABLE APP.MELDER_SCHLEIFEN ";
			sql += " ( MATERIAL_ID bigint NOT NULL, ";
			sql += " SCHLEIFE_ID bigint NOT NULL 	) ";
			executeSQL(sql);

			sql = "   ALTER TABLE APP.MELDER_SCHLEIFEN  ";
			sql += " ADD CONSTRAINT FK39376D9583C20911 ";
			sql += " FOREIGN KEY (SCHLEIFE_ID) ";
			sql += " REFERENCES APP.MELDERSCHLEIFEN(SCHLEIFE_ID) ";
			executeSQL(sql);

			sql = "   ALTER TABLE APP.MELDER_SCHLEIFEN ";
			sql += " ADD CONSTRAINT FK39376D95E59E7A5C ";
			sql += " FOREIGN KEY (MATERIAL_ID) ";
			sql += " REFERENCES APP.MATERIAL(MATERIAL_ID)";
			executeSQL(sql);

			sql = " CREATE INDEX SQL120820133221480 ON APP.MELDER_SCHLEIFEN(SCHLEIFE_ID)";
			executeSQL(sql);

			sql = "CREATE INDEX SQL120820133221490 ON APP.MELDER_SCHLEIFEN(MATERIAL_ID)";
			executeSQL(sql);

			sql = "CREATE UNIQUE INDEX SQL120820133221370 ON APP.MELDER_SCHLEIFEN(SCHLEIFE_ID)";
			executeSQL(sql);

			setDBVersion(4);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			JOptionPane.showConfirmDialog(null,
					"Update fehlgeschlagen!\n" + e.getMessage(),
					"Fehler beim Update", JOptionPane.ERROR_MESSAGE
							+ JOptionPane.OK_OPTION);
		}

	}

	private void setVersion3() throws Exception {
		EntityTransaction tr = JPAEntityManager.getTransaction();
		tr.begin();
		
		try {
			String sql = "";
			sql = "ALTER TABLE MITGLIED add edit SMALLINT NOT NULL DEFAULT 0";
			executeSQL(sql);

			setDBVersion(3);
			tr.commit();
		} catch (SQLException e) {
			tr.rollback();
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

	private void setVersion2() throws Exception {
		EntityTransaction tr = JPAEntityManager.getTransaction();
		tr.begin();

		try {
			String sql = "";
			sql = "ALTER TABLE MITGLIED add rettungsdienst SMALLINT NOT NULL DEFAULT 0";
			executeSQL(sql);
			setDBVersion(2);
			tr.commit();
		} catch (SQLException e) {
			tr.rollback();
			JOptionPane.showConfirmDialog(null,
					"Update fehlgeschlagen!\n" + e.getMessage(),
					"Fehler beim Update", JOptionPane.ERROR_MESSAGE
							+ JOptionPane.OK_OPTION);
		}

	}

	private void executeSQL(final String sql) throws SQLException {

		EntityManager em = JPAEntityManager.getCurrentEntityManager();
		Session session = em.unwrap(Session.class);

		session.doWork(new Work() {

			@Override
			public void execute(Connection con) throws SQLException {
				Statement st = con.createStatement();
				st.execute(sql);
			}
		});

	}
}
