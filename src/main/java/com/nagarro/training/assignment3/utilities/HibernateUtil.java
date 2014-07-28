/**
 * 
 */
package com.nagarro.training.assignment3.utilities;

import org.hibernate.SessionFactory;
import org.hibernate.SessionFactoryObserver;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.nagarro.training.assignment3.flight.Flight;
import com.nagarro.training.assignment3.flight.Location;

/**
 * @author hiteshgarg
 * 
 */
public class HibernateUtil {
	/**
	 * Session Factory reference
	 */
	private static SessionFactory sessionFactory;
	/**
	 * Service Registry
	 */
	private static ServiceRegistry serviceRegistry;

	/**
	 * @return
	 */
	public static SessionFactory createSessionFactory() {

		if (sessionFactory == null) {
			Configuration configuration = new Configuration().configure(); // configuration
																			// from
																			// hibernate.cfg.xml
			configuration.addAnnotatedClass(Flight.class);
			configuration.addAnnotatedClass(Location.class);

			StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();

			serviceRegistryBuilder.applySettings(configuration.getProperties());
			serviceRegistry = serviceRegistryBuilder.build();
			configuration
					.setSessionFactoryObserver(new SessionFactoryObserver() {
						/**
* 
*/
						private static final long serialVersionUID = 1L;

						public void sessionFactoryCreated(SessionFactory factory) {
						}

						public void sessionFactoryClosed(SessionFactory factory) {
							((StandardServiceRegistryImpl) serviceRegistry)
									.destroy();
						}
					});
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}

		return sessionFactory;
	}

	/**
	 * @return
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Releases resources
	 */
	public static void closeSessionFactory() {
		if (sessionFactory != null && !sessionFactory.isClosed())
			sessionFactory.close();
	}

}
