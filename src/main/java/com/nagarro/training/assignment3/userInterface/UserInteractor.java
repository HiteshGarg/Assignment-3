package com.nagarro.training.assignment3.userInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;


import com.nagarro.training.assignment3.constants.Constants;
import com.nagarro.training.assignment3.customException.NewCustomException;
import com.nagarro.training.assignment3.dto.FlightDTO;
import com.nagarro.training.assignment3.flight.Flight;
import com.nagarro.training.assignment3.utilities.HibernateUtil;
import com.nagarro.training.assignment3.validators.StringDateConverter;
import com.nagarro.training.assignment3.validators.UserInputValidators;

/**
 * @author hiteshgarg
 * 
 * This class does all the operations related to user input, its validation 
 * and finally calls functions to search user flights and display  its output.
 */
public class UserInteractor {

	public void userInput() throws NewCustomException {
		FlightDTO dto;
		boolean validate = true;
		String input;
		String choice= "y";
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(System.in));
			do {
				dto = new FlightDTO();
				System.out.println(Constants.WELCOME_MESSAGE);

				do {
					System.out.println(Constants.ENTER_DEPARTURE_LOCATION);
					validate = UserInputValidators
							.locationValidator(input = reader.readLine());
				} while (!validate);
				dto.setDepLoc(input);

				do {
					System.out.println(Constants.ENTER_ARRIVAL_LOCATION);
					validate = UserInputValidators
							.locationValidator(input = reader.readLine());
				} while (!validate);
				dto.setArr_loc(input);

				do {
					System.out.println(Constants.ENTER_FLIGHT_DATE);
					input = reader.readLine();
					try{
					validate = UserInputValidators.dateValidator(input);
					}catch(NewCustomException exception){
						exception.printMessage();
						validate = false;
					}
				} while (!validate);
				
				try{
				Date date = StringDateConverter.StringToDateConvertor(input);
				dto.setFlightDate(date);
				}catch(NewCustomException exception){
					exception.printMessage();
					continue;
				}

				do {
					System.out.println(Constants.ENTER_FLIGHT_CLASS);
					validate = UserInputValidators
							.classValidator(input = reader.readLine());
				} while (!validate);
				dto.setFlightClass(input);

				do {
					System.out.println(Constants.ENTER_OUTPUT_PREFERENCES);
					validate = UserInputValidators
							.preferenceValidator(input = reader.readLine());
				} while (!validate);
				dto.setOutputPreferences(input);

				List<Flight> SearchedFlights = new FlightSearch().searchUserFlight(dto);
				
				Output.outputDisplay(SearchedFlights, dto);
				
				System.out.println("Want to search another flight(y/n)");
				choice = reader.readLine();
			} while (choice.equalsIgnoreCase("y"));
			reader.close();

		} catch (IOException e) {
			throw new NewCustomException("Unexpected error occured please try again");
		}finally{			
			HibernateUtil.closeSessionFactory();
			System.exit(0);
		}
	}
}
