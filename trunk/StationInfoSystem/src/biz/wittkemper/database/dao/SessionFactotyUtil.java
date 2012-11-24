package biz.wittkemper.database.dao;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import biz.wittkemper.utils.Utils;
import biz.wittkemper.utils.Utils.STATIONPROP;

public class SessionFactotyUtil {
	Logger logger = LoggerFactory.getLogger(SessionFactotyUtil.class);
	private static SessionFactory sessionFactory;

	/**
	 * disable contructor to guaranty a single instance
	 */
	private SessionFactotyUtil() {
	}

	static {
		String db = Utils.getPropertie(STATIONPROP.DATABASE);

		Properties properties = new Properties();
		properties.setProperty("hibernate.connection.url",
				"jdbc:mysql://" + db.trim() + "/monitord");
		sessionFactory = new AnnotationConfiguration()
				.addProperties(properties)
				.setProperty("hibernate.connection.username", "monitord")
				.setProperty("hibernate.connection.password", "monitord")

				.addAnnotatedClass(biz.wittkemper.database.entity.Kennung.class)
				.addAnnotatedClass(
						biz.wittkemper.database.entity.Posac_status.class)
				.addAnnotatedClass(
						biz.wittkemper.database.entity.Monitord_POSAC.class)
				.addAnnotatedClass(
						biz.wittkemper.database.entity.Alarmierung.class)
				.addAnnotatedClass(
						biz.wittkemper.database.entity.KalenderTyp.class)
				.addAnnotatedClass(
						biz.wittkemper.database.entity.Kalender.class)
				.configure().buildSessionFactory();

	}

	public static SessionFactory getInstance() {
		return sessionFactory;
	}

	/**
	 * Opens a session and will not bind it to a session context
	 * 
	 * @return the session
	 */
	public Session openSession() {
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
	public Session getCurrentSession() {
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
}
