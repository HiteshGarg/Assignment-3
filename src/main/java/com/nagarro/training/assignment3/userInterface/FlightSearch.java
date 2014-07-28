/**
 * 
 */
package com.nagarro.training.assignment3.userInterface;

import java.util.Iterator;
import java.util.List;

import com.nagarro.training.assignment3.customException.NewCustomException;
import com.nagarro.training.assignment3.dao.FlightDaoHibernate;
import com.nagarro.training.assignment3.dto.FlightDTO;
import com.nagarro.training.assignment3.flight.Flight;

/**
 * @author hiteshgarg
 * This class is used to retrieve a List of all the flights based 
 * on the departure and arrival location entered by the user which 
 * is further filtered by using the flight date and flight class.
 */
public class FlightSearch {

	public List<Flight> searchUserFlight(FlightDTO dto) {

		List<Flight> flights = null;
		String DepArrKey = (dto.getDepLoc() + dto.getArrLoc()).toUpperCase();
		try{
		flights = FlightDaoHibernate.getDepArrivalFlights(DepArrKey);
		}
		catch(NewCustomException exception){
			exception.printMessage();
		}
		if(flights!=null){
		Iterator<Flight> iterator = flights.iterator();
		while (iterator.hasNext()) {
			Flight flight = iterator.next();
			if (!((dto.getFlightDate().before(flight.getValidTill())) && (flight
					.getFlightClass().contains((CharSequence) dto
					.getFlightClass().toUpperCase())))) {
				iterator.remove();
			}
		}
		}
		return flights;
	}
}
