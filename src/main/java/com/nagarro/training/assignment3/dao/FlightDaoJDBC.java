/**
 * 
 */
package com.nagarro.training.assignment3.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import com.nagarro.training.assignment3.constants.Constants;
import com.nagarro.training.assignment3.customException.NewCustomException;
import com.nagarro.training.assignment3.flight.Flight;

/**
 * @author hiteshgarg This class is used as a Dao to store all the data related
 *         to flights in the database using the JDBC
 * 
 */
public class FlightDaoJDBC {

	/*
	 * This loads the driver once in the lifetime
	 */
	static {
		try {
			Class.forName(Constants.JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Connection object
	Connection conn = null;

	/*
	 * This method creates and return a connection object.
	 */
	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(Constants.DB_URL,
					Constants.USERNAME, Constants.PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/*
	 * This method recieves the location String and a set of flights related to
	 * that particular location and it inserts the flight data in the database
	 */
	public void insertFlightDataInDB(String location, Set<Flight> flightData)
			throws NewCustomException {

		if (location != null && flightData.size() != 0) {

			Integer arrLoc = getLocation(location.substring(0, 3));
			Integer depLoc = getLocation(location.substring(3));
			if (arrLoc == null) {
				setLocation(location.substring(0, 3));
				arrLoc = getLocation(location.substring(0, 3));
			}
			if (depLoc == null) {
				setLocation(location.substring(3));
				depLoc = getLocation(location.substring(3));
			}

			// Connection conn = null;
			PreparedStatement statement = null;
			for (Flight flight : flightData) {

				try {
					if (searchFlightInDb(flight)) {
						continue;
					}
					conn = getConnection();
					if (conn != null) {
						String sql = Constants.INSERT_FLIGHT;
						statement = conn.prepareStatement(sql);
						statement.setString(1, flight.getFlightNo());
						statement.setDate(2, new java.sql.Date(flight
								.getValidTill().getTime()));
						statement.setString(3, flight.getFlightTime());
						statement.setString(4, flight.getFlightDuration());
						statement.setInt(5, flight.getFare());
						statement.setString(6, flight.getSeatAvail());
						statement.setString(7, flight.getFlightClass());
						statement.setInt(8, depLoc);
						statement.setInt(9, arrLoc);
						statement.executeUpdate();
					}
				} catch (SQLException e) {
					e.printStackTrace();
					throw new NewCustomException(
							"Error in Adding Flight Data to the Database");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}

	/*
	 * This method search the database and returns true if the flight object
	 * exists in database and false otherwise.
	 */
	private boolean searchFlightInDb(Flight flight) throws NewCustomException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Boolean exist = false;

		try {
			conn = getConnection();
			if (conn != null) {
				statement = conn.prepareStatement(Constants.SEARCH_FLIGHT);
				statement.setString(1, flight.getFlightNo());
				statement.setString(2, flight.getFlightClass());
				resultSet = statement.executeQuery();
				if (resultSet.next()) {
					exist = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NewCustomException("Error in Searching Flight");
		} finally {
			if (resultSet != null)
				try {
					resultSet.close();
				} catch (SQLException ignore) {
				}
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ignore) {
				}
		}
		return exist;
	}

	/*
	 * This method receives the Location(Departure + Arrival) String and sets
	 * the String to Location Table
	 */
	private void setLocation(String location) throws NewCustomException {

		PreparedStatement statement = null;

		try {
			conn = getConnection();
			if (conn != null) {
				statement = conn.prepareStatement(Constants.ADD_LOCATION);
				statement.setString(1, location);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NewCustomException("Error in Setting Location");
		} finally {
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ignore) {
				}
		}
	}

	/*
	 * This method receives the location String and find the related object and
	 * return the object of Location Class
	 */
	private Integer getLocation(String location) throws NewCustomException {
		// Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Integer LocId = null;
		try {
			conn = getConnection();
			if (conn != null) {
				statement = conn.prepareStatement(Constants.SEARCH_LOCATION);
				statement.setString(1, location);
				resultSet = statement.executeQuery();
				if (resultSet.next()) {
					LocId = resultSet.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NewCustomException("Error in Finding Location");
		} finally {
			if (resultSet != null)
				try {
					resultSet.close();
				} catch (SQLException ignore) {
				}
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ignore) {
				}
		}
		return LocId;
	}

}
