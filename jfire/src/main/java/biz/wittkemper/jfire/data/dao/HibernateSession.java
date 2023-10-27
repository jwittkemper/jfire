package biz.wittkemper.jfire.data.dao;

import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import biz.wittkemper.jfire.Start;
import biz.wittkemper.jfire.forms.ferror.FError;
import biz.wittkemper.jfire.utils.SystemUtils;

@SuppressWarnings("deprecation")
public class HibernateSession {

	private static final Logger log = LoggerFactory.getLogger(HibernateSession.class);
	private static SystemUtils systemUtils = new SystemUtils();

	private static SessionFactory sessionFactory;

	/**
	 * disable contructor to guaranty a single instance
	 */
	private HibernateSession() {
	}

	static {
		boolean initDB = false;
		String createDB = "none";
		if (!systemUtils.getDBAvailable()) {
			log.info("Database not found at init.");
			createDB = "create";
			initDB = true;
		}

		Properties properties = new Properties();
		properties.setProperty("hibernate.connection.url", "jdbc:derby:" + systemUtils.getDBPfad() + ";create=true");
		Configuration configuration = new Configuration();
		configuration.addProperties(properties);

		configuration.setProperty("hibernate.connection.username", "");
		configuration.setProperty("hibernate.connection.password", "");
		configuration.setProperty("hibernate.connection.driver_class", "org.apache.derby.jdbc.EmbeddedDriver");
		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
		configuration.setProperty("transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory");
		configuration.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.HashtableCacheProvider");
		configuration.setProperty("hibernate.query.substitutions", "true 1, false 0, yes 'Y', no 'N'");
		configuration.setProperty("hibernate.hbm2ddl.auto", createDB);
		// .setProperty("hibernate.hbm2ddl.auto", "create")
		configuration.setProperty("hibernate.show_sql", "true");
		configuration.setProperty("hibernate.format_sql", "true");
		configuration.setProperty("hibernate.current_session_context_class", "thread");

		configuration.addAnnotatedClass(biz.wittkemper.jfire.data.entity.Mitglied.class);
		configuration.addAnnotatedClass(biz.wittkemper.jfire.data.entity.MitgliedStatus.class);
		configuration.addAnnotatedClass(biz.wittkemper.jfire.data.entity.FoerderMitglied.class);
		configuration.addAnnotatedClass(biz.wittkemper.jfire.data.entity.Parameter.class);
		configuration.addAnnotatedClass(biz.wittkemper.jfire.data.entity.Anrede.class);
		configuration.addAnnotatedClass(biz.wittkemper.jfire.data.entity.Material.class);
		configuration.addAnnotatedClass(biz.wittkemper.jfire.data.entity.MaterialTyp.class);
		configuration.addAnnotatedClass(biz.wittkemper.jfire.data.entity.MelderTyp.class);
		configuration.addAnnotatedClass(biz.wittkemper.jfire.data.entity.Melder.class);
		configuration.addAnnotatedClass(biz.wittkemper.jfire.data.entity.MelderSchleifen.class);
		configuration.addAnnotatedClass(biz.wittkemper.jfire.data.entity.Fuehrerschein.class);
		configuration.addAnnotatedClass(biz.wittkemper.jfire.data.entity.Mitglied_Fuehrerschein.class);
		configuration.addAnnotatedClass(biz.wittkemper.jfire.data.entity.MitgliedFuehrerscheinID.class);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
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

	public static SessionFactory getInstance() {
		return sessionFactory;
	}

	/**
	 * Opens a session and will not bind it to a session context
	 * 
	 * @return the session
	 */
	public static Session openSession() {
		return sessionFactory.openSession();
	}

	/**
	 * Returns a session from the session context. If there is no session in the
	 * context it opens a session, stores it in the context and returns it. This
	 * factory is intended to be used with a hibernate.cfg.xml including the
	 * following property
	 * <property name="current_session_context_class">thread</property> This
	 * would return the current open session or if this does not exist, will
	 * create a new session
	 * 
	 * @return the session
	 */
	public static Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * closes the session factory
	 */
	public static void close() {
		if (sessionFactory != null)
			sessionFactory.close();
		sessionFactory = null;

	}

	public static Session beginTransaction() throws HibernateException {
		Session hibernateSession = HibernateSession.getCurrentSession();
		if (hibernateSession.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			hibernateSession.beginTransaction();
		}
		return hibernateSession;
	}

	public static void commitTransaction() {
		if (HibernateSession.getCurrentSession().getTransaction().getStatus() == TransactionStatus.ACTIVE) {
			HibernateSession.getCurrentSession().getTransaction().commit();
		}
	}

	public static void rollbackTransaction() {
		HibernateSession.getCurrentSession().getTransaction().rollback();
	}
}
