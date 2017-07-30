package com.flight.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flight.model.Airline;
import com.flight.model.Airport;
import com.flight.model.Flight;

/**
 * Flight search service
 * 
 * @author alex
 *
 */
public class FlightService {

	/**
	 * Flights database
	 */
	private Map<String, Flight> database = new HashMap<>();

	private AirlineService airlineService;

	private AirportService airportService;

	public FlightService() {

	}

	public FlightService(AirlineService airlineService, AirportService airportService) {
		this.airlineService = airlineService;
		this.airportService = airportService;
	}

	/**
	 * Load flights to the database
	 */
	public void loadData(List<Flight> itemList) {

		// Add flight to the database if not exists
		for (Flight item : itemList) {
			Flight itemFound = database.get(item.getCode());
			if (itemFound == null) {
				database.put(item.getCode(), item);
			}
		}
	}

	public Map<String, Flight> getDatabase() {
		return database;
	}

	public void setDatabase(Map<String, Flight> database) {
		this.database = database;
	}

	public Flight createFlight(String code, String originAirportCode, String destinationAirportCode, String airlineCode,
			String basePrice) {
		Airport origin = airportService.getAirport(originAirportCode);
		Airport destination = airportService.getAirport(destinationAirportCode);
		Airline airline = airlineService.getAirline(airlineCode);

		if (origin == null) {
			throw new IllegalArgumentException("Origin airport not valid");
		}

		if (destination == null) {
			throw new IllegalArgumentException("Destination airport not valid");
		}

		if (airline == null) {
			throw new IllegalArgumentException("Airline not valid");
		}

		return new Flight(code, origin, destination, airline, basePrice);
	}

	public void loadDataFromfile(File file) {
		loadData(readDataFromFile(file));
	}

	public List<Flight> readDataFromFile(File file) {
		List<Flight> flightList = new ArrayList<>();

		BufferedReader br = null;
		String line = "";
		String separator = ",";

		try {
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				String[] flightInfo = line.split(separator);

				String airlineCode = flightInfo[2].substring(0, 2);
				Flight flight = createFlight(flightInfo[2], flightInfo[0], flightInfo[1], airlineCode, flightInfo[3]);

				flightList.add(flight);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return flightList;
	}
	
	public AirlineService getAirlineService() {
		return airlineService;
	}

	public void setAirlineService(AirlineService airlineService) {
		this.airlineService = airlineService;
	}

	public AirportService getAirportService() {
		return airportService;
	}

	public void setAirportService(AirportService airportService) {
		this.airportService = airportService;
	}
}
