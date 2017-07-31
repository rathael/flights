package com.flight.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flight.model.Airport;
import com.flight.model.Flight;

/**
 * Airport Service
 * 
 * @author alex
 *
 */
public class AirportService {

	/**
	 * Flights database
	 */
	private Map<String, Airport> airports = new HashMap<>();

	public AirportService() {
		loadDefaultData();
	}

	/**
	 * Load flights to the database
	 */
	public void loadData(List<Airport> itemList) {

		// Add flight to the database if not exists
		for (Airport data : itemList) {
			Airport itemFound = airports.get(data.getCode());
			if (itemFound == null) {
				airports.put(data.getCode(), data);
			}
		}
	}

	/**
	 * Load default aiport info
	 */
	private void loadDefaultData() {
		Airport item = null;
		List<Airport> itemList = new ArrayList<>();

		item = new Airport("MAD", "Madrid");
		itemList.add(item);

		item = new Airport("BCN", "Barcelona");
		itemList.add(item);

		item = new Airport("LHR", "London");
		itemList.add(item);

		item = new Airport("CDG", "Paris");
		itemList.add(item);

		item = new Airport("FRA", "Frakfurt");
		itemList.add(item);

		item = new Airport("IST", "Istanbul");
		itemList.add(item);

		item = new Airport("AMS", "Amsterdam");
		itemList.add(item);

		item = new Airport("FCO", "Rome");
		itemList.add(item);

		item = new Airport("CPH", "Copenhagen");
		itemList.add(item);

		this.loadData(itemList);
	}

	public Airport getAirport(String code) {
		return airports.get(code);
	}

	public Map<String, Airport> getAirports() {
		return airports;
	}

	public void setAirports(Map<String, Airport> airports) {
		this.airports = airports;
	}

	/**
	 * Load airports from flights and create the airport connections
	 * 
	 * @param flights
	 */
	public void loadFromFlights(List<Flight> flights) {

		// Create Airport List used in flights
		for (Flight flight : flights) {
			if (!airports.containsKey(flight.getOrigin().getCode())) {
				airports.put(flight.getOrigin().getCode(), flight.getOrigin());
			}

			if (!airports.containsKey(flight.getDestination().getCode())) {
				airports.put(flight.getDestination().getCode(), flight.getDestination());
			}
		}

		// Create direct connections
		for (Flight flight : flights) {
			Airport originAirport = airports.get(flight.getOrigin().getCode());
			Airport destinationAirport = airports.get(flight.getDestination().getCode());

			// go
			List<Flight> destinationFlights = originAirport.getFlightsTo().get(destinationAirport);
			if (destinationFlights == null) {
				destinationFlights = new ArrayList<>();
				destinationFlights.add(flight);
				originAirport.getFlightsTo().put(destinationAirport, destinationFlights);
			} else {
				destinationFlights.add(flight);
			}
		}
	}
}
