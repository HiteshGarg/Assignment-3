/**
 * @author hiteshgarg
 * This class is used to delcare various constants that are used at various locations in the project.
 */

package com.nagarro.training.assignment3.constants;

public class Constants {

	public static final String WELCOME_MESSAGE = "Welcome to Flight Search program."
			+ " Please Enter the Details as Asked.";
	public static final String ENTER_DEPARTURE_LOCATION = "Departure Location : ";
	public static final String ENTER_ARRIVAL_LOCATION = "Arrival Location : ";
	public static final String ENTER_FLIGHT_DATE = "Flight Date(Format dd-mm-yyyy) : ";
	public static final String ENTER_FLIGHT_CLASS = "Flight Class(E = Economy or B = Business) : ";
	public static final String ENTER_OUTPUT_PREFERENCES = "Output Preferences(For Fare press 1  "
			+ "for fare and flight duration press 2) : ";
	public static final String INCORRECT_LOCATION_ERROR = "Please enter location in correct format"
			+ "(Use 3 digit standard code format)";
	public static final String INCORRECT_CLASS_ERROR = "Please enter Proper class";
	public static final String INCORRECT_PREFERENCE_ERROR = "Please enter preference from the given options only";
	public static final String INCORRECT_DATE_ERROR = "Please enter date in correct format";
	public static final String CSV_FILES_URL = "AirlinesCsvFiles";
	public static final String CSV_SPLIT_DELIMITTER = "\\|";
	
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost/flight_search2";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "Passw0rd";
	public static final String INSERT_FLIGHT = "insert into flight_data(flight_no,valid_till,flight_time,flight_duration,fare,seat_avail,flight_class,dep_loc,arr_loc) values(?,?,?,?,?,?,?,?,?)";
	public static final String SEARCH_LOCATION= "select id from flight_location where location = ?";
	public static final String ADD_LOCATION = "INSERT into flight_location(location) values(?)";
	public static final String SEARCH_FLIGHT = "select * from flight_data where flight_no = ? and flight_class = ?";
}
