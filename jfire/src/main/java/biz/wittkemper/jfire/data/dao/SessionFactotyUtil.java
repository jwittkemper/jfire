package biz.wittkemper.jfire.data.dao;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class SessionFactotyUtil {

	private static SessionFactory sessionFactory;
	/**
	 * disable contructor to guaranty a single instance
	 */
	private SessionFactotyUtil() {
	}

	static{
				
		Properties properties = new Properties();
		properties.setProperty("hibernate.connection.url", "jdbc:derby:../jfire-Database;create=true");
	
		sessionFactory = new AnnotationConfiguration()
			.addProperties(properties)
			.setProperty("hibernate.connection.username", "")
			.setProperty("hibernate.connection.password", "")
			.setProperty("hibernate.connection.driver_class", "org.apache.derby.jdbc.EmbeddedDriver")
			.setProperty("hibernate.dialect", "org.hibernate.dialect.DerbyDialect")
			.setProperty("transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory")
			.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.HashtableCacheProvider")
			.setProperty("hibernate.query.substitutions", "true 1, false 0, yes 'Y', no 'N'")
			.setProperty("hibernate.hbm2ddl.auto", "none")
			.setProperty("hibernate.show_sql", "true")
			.setProperty("hibernate.format_sql", "true")
			.setProperty("hibernate.current_session_context_class", "thread")

			
			.addAnnotatedClass(biz.wittkemper.jfire.data.entity.Mitglied.class)
			.addAnnotatedClass(biz.wittkemper.jfire.data.entity.MitgliedStatus.class)
			.addAnnotatedClass(biz.wittkemper.jfire.data.entity.FoerderMitglied.class)
			.addAnnotatedClass(biz.wittkemper.jfire.data.entity.Parameter.class)
			.addAnnotatedClass(biz.wittkemper.jfire.data.entity.Anrede.class)
			.buildSessionFactory();
		
	}

	public static SessionFactory getInstance() {
		return sessionFactory;
	}

  /**
   * Opens a session and will not bind it to a session context
   * @return the session
   */
	public Session openSession() {
		return sessionFactory.openSession();
	}

	/**
   * Returns a session from the session context. If there is no session in the context it opens a session,
   * stores it in the context and returns it.
	 * This factory is intended to be used with a hibernate.cfg.xml
	 * including the following property <property
	 * name="current_session_context_class">thread</property> This would return
	 * the current open session or if this does not exist, will create a new
	 * session
	 * 
	 * @return the session
	 */
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

  /**
   * closes the session factory
   */
	public static void close(){
		if (sessionFactory != null)
			sessionFactory.close();
		sessionFactory = null;

	}
}
