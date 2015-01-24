package biz.wittkemper.jfire.data.dao;

import java.util.Properties;

import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import biz.wittkemper.jfire.forms.ferror.FError;
import biz.wittkemper.jfire.utils.SystemUtils;

public class HibernateSession {
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
			createDB = "create";
			initDB = true;
		}

		Properties properties = new Properties();
		properties.setProperty("hibernate.connection.url", "jdbc:derby:"
				+ systemUtils.getDBPfad() + ";create=true");

		sessionFactory = new AnnotationConfiguration()
				.addProperties(properties)
				.setProperty("hibernate.connection.username", "")
				.setProperty("hibernate.connection.password", "")
				.setProperty("hibernate.connection.driver_class",
						"org.apache.derby.jdbc.EmbeddedDriver")
				.setProperty("hibernate.dialect",
						"org.hibernate.dialect.DerbyDialect")
				.setProperty("transaction.factory_class",
						"org.hibernate.transaction.JDBCTransactionFactory")
				.setProperty("hibernate.cache.provider_class",
						"org.hibernate.cache.HashtableCacheProvider")
				.setProperty("hibernate.query.substitutions",
						"true 1, false 0, yes 'Y', no 'N'")
				.setProperty("hibernate.hbm2ddl.auto", createDB)
				// .setProperty("hibernate.hbm2ddl.auto", "create")
				.setProperty("hibernate.show_sql", "false")
				.setProperty("hibernate.format_sql", "true")
				.setProperty("hibernate.current_session_context_class",
						"thread")

				.addAnnotatedClass(
						biz.wittkemper.jfire.data.entity.Mitglied.class)
				.addAnnotatedClass(
						biz.wittkemper.jfire.data.entity.MitgliedStatus.class)
				.addAnnotatedClass(
						biz.wittkemper.jfire.data.entity.FoerderMitglied.class)
				.addAnnotatedClass(
						biz.wittkemper.jfire.data.entity.Parameter.class)
				.addAnnotatedClass(
						biz.wittkemper.jfire.data.entity.Anrede.class)
				.addAnnotatedClass(
						biz.wittkemper.jfire.data.entity.Material.class)
				.addAnnotatedClass(
						biz.wittkemper.jfire.data.entity.MaterialTyp.class)
				.addAnnotatedClass(
						biz.wittkemper.jfire.data.entity.MelderTyp.class)
				.addAnnotatedClass(
						biz.wittkemper.jfire.data.entity.Melder.class)
				.addAnnotatedClass(
						biz.wittkemper.jfire.data.entity.MelderSchleifen.class)
				.addAnnotatedClass(
						biz.wittkemper.jfire.data.entity.Fuehrerschein.class)
				.addAnnotatedClass(
						biz.wittkemper.jfire.data.entity.Mitglied_Fuehrerschein.class)
				.addAnnotatedClass(
						biz.wittkemper.jfire.data.entity.MitgliedFuehrerscheinID.class)
				.buildSessionFactory();
		if (initDB) {
			try {
				systemUtils.initDB();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				systemUtils.checkDB();
			} catch (JDBCException e) {
				FError fError = new FError(e);
				fError.setModal(true);
				fError.setVisible(true);
				System.exit(0);

			} catch (Exception e) {
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
	 * following property <property
	 * name="current_session_context_class">thread</property> This would return
	 * the current open session or if this does not exist, will create a new
	 * session
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

	public static Session beginTransaction() throws Exception{
		Session hibernateSession = HibernateSession.getCurrentSession();
		if (!hibernateSession.getTransaction().isActive()) {
			hibernateSession.beginTransaction();
		}
		return hibernateSession;
	}

	public static void commitTransaction() {
		if (HibernateSession.getCurrentSession().getTransaction().isActive()) {
			HibernateSession.getCurrentSession().getTransaction().commit();
		}
	}

	public static void rollbackTransaction() {
		HibernateSession.getCurrentSession().getTransaction().rollback();
	}
}
