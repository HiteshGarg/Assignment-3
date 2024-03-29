package com.nagarro.training.assignment3.main;

import com.nagarro.training.assignment3.csvService.ProcessCsvFiles;
import com.nagarro.training.assignment3.customException.NewCustomException;
import com.nagarro.training.assignment3.userInterface.UserInteractor;

public class ProgramLauncher {


	public static void main(String[] args) {

		try {
		/*
		 * Initializes the class that perform all csv files related operations
		 */
		ProcessCsvFiles launcher = new ProcessCsvFiles();
		launcher.initiateThreadClass();

		/*
		 * This will interact with user. Will take Input , validate it further
		 * and Finally Search for the flights
		 */

		UserInteractor interactor = new UserInteractor();
			interactor.userInput();		
		} catch (NewCustomException exception) {

			exception.printMessage();
		}

	}

}
