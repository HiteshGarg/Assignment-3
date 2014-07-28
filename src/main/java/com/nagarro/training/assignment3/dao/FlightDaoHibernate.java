package com.nagarro.training.assignment3.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nagarro.training.assignment3.customException.NewCustomException;
import com.nagarro.training.assignment3.flight.Flight;
import com.nagarro.training.assignment3.flight.Location;
import com.nagarro.training.assignment3.utilities.HibernateUtil;

/**
 * @author hiteshgarg This class is used as a Dao to perform database operations
 *         using the hibernate technology
 */
public class FlightDaoHibernate {

	/*
	 * This method receives the location String and find the related object and
	 * return the object of Location Class
	 */

	private static Location getLocation(String location)
			throws NewCustomException {

		Session session = null;
		Transaction tx = null;
		Location loc = null;
		try {
			session = HibernateUtil.createSessionFactory().openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Location.class);
			criteria.add(Restrictions.eq("location", location));
			List<Location> locList = criteria.list();

			if (locList.size() == 0) {
				loc = null;
			} else {
				loc = locList.get(0);
			}
			tx.commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			throw new NewCustomException("Error in Finding Location");
		} finally {
			session.close();
		}
		return loc;
	}

	/*
	 * This method returns the list of Flights based on the arrival and
	 * departure location input by the user
	 */
	public static List<Flight> getDepArrivalFlights(String DepArrKey)
			throws NewCustomException {

		List<Flight> SearchedFlights = null;
		Location arrLoc = getLocation(DepArrKey.substring(0, 3));
		Location depLoc = getLocation(DepArrKey.substring(3));

		if ((arrLoc != null) && (depLoc != null)) {
			SearchedFlights = new ArrayList<Flight>();
			Transaction tx = null;
			Session session = null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				tx = session.beginTransaction();

				Criteria criteria = session.createCriteria(Flight.class);
				criteria.add(Restrictions.eq("depLoc", depLoc));
				criteria.add(Restrictions.eq("arrLoc", arrLoc));
				SearchedFlights = criteria.list();

			} catch (HibernateException exception) {
				if (tx != null)
					tx.rollback();
				throw new NewCustomException("Error in Finding Location");

			} finally {
				session.close();
			}
		}
		return SearchedFlights;
	}
}
