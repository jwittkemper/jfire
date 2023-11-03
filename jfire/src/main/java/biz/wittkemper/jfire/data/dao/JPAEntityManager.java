package biz.wittkemper.jfire.data.dao;

import java.util.Properties;

import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import biz.wittkemper.jfire.forms.ferror.FError;
import biz.wittkemper.jfire.utils.SystemUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transaction;

public class JPAEntityManager {

    private static final Logger log = LoggerFactory.getLogger(JPAEntityManager.class);
    private static SystemUtils systemUtils = new SystemUtils();
	private static EntityManagerFactory emf;
    private static EntityManager entityManager;
    
	static {
		boolean initDB = false;
		if (!systemUtils.getDBAvailable()) {
			log.info("Database not found at init.");
			initDB = true;
		}
		String pro ="jdbc:derby:";
		pro += systemUtils.getDBPfad()+";create=true";
		
    	Properties props = new Properties();
    	props.setProperty("jakarta.persistence.jdbc.url", pro);
		emf = Persistence.createEntityManagerFactory("jpa-conf", props);
		entityManager = emf.createEntityManager();
		
		if (initDB) {
			try {
				systemUtils.initDB();
			} catch (Exception e) {
				log.error("Error at DB init:", e);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				systemUtils.checkDB();
			} catch (JDBCException e) {
				log.error("Fehler beim init.", e);
				FError fError = new FError(e);
				fError.setModal(true);
				fError.setVisible(true);
				System.exit(0);

			} catch (Exception e) {
				log.error("Fehler beim init.", e);
				FError fError = new FError(e);
				fError.setModal(true);
				fError.setVisible(true);
				System.exit(0);
			}
		}
	}
	
	public static EntityManager getInstance() {
		return entityManager;
	}
	
	public static EntityManager getCurrentEntityManager() {
		return entityManager;
	}
	
	public static void close() {
		if (entityManager!=null) {
			entityManager.clear();
			entityManager.close();
		}
	}
	
	public static EntityTransaction getTransaction() {
		return entityManager.getTransaction();
	}
}
