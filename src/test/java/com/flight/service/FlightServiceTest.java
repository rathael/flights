package com.flight.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.flight.model.Flight;

/**
 * Test of {@link FlightService}
 * 
 * @author alex
 *
 */
public class FlightServiceTest {

	private FlightService flightService;

	private static AirportService airportService;

	private static AirlineService airlineService;

	@BeforeClass
	public static void beforeClass() {
		airportService = new AirportService();
		airlineService = new AirlineService();
	}

	@Before
	public void before() {
		flightService = new FlightService();
		flightService.setAirlineService(airlineService);
		flightService.setAirportService(airportService);
	}

	/**
	 * Test load flights from list
	 */
	@Test
	public void loadFlightsTest() {
		Flight flight = null;
		List<Flight> flightList = new ArrayList<>();

		flight = new Flight("IB2818", airportService.getAirport("CPH"), airportService.getAirport("FRA"),
				airlineService.getAirline("IB"), 186);
		flightList.add(flight);

		flight = new Flight("IB8482", airportService.getAirport("CDG"), airportService.getAirport("MAD"),
				airlineService.getAirline("IB"), 295);
		flightList.add(flight);

		flightService.loadData(flightList);

		for (Flight item : flightService.getDatabase().values()) {
			System.out.println(item);
		}

		Assert.assertEquals("Size of list", flightList.size(), flightService.getDatabase().keySet().size());

	}

	/**
	 * Test load flights from file
	 */
	@Test
	public void loadDataFromfileTest() {
		String filename = "/flights.csv";
		File file = new File(this.getClass().getResource(filename).getFile());

		flightService.loadDataFromfile(file);

		for (Flight item : flightService.getDatabase().values()) {
			System.out.println(item);
		}

		Assert.assertEquals("Size of list", 89, flightService.getDatabase().keySet().size());
	}


}
