package com.flight.service;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.flight.enums.PassengerType;
import com.flight.exceptions.FlightNotFoundException;
import com.flight.model.BookFlight;
import com.flight.model.Flight;
import com.flight.model.Passenger;

public class BookingServiceTest {

	private BookingService bookingService;

	private static FlightService flightService;

	private static AirportService airportService;

	private static AirlineService airlineService;

	@BeforeClass
	public static void beforeClass() {
		airportService = new AirportService();
		airlineService = new AirlineService();
		flightService = new FlightService();

		flightService.setAirlineService(airlineService);
		flightService.setAirportService(airportService);

		String filename = "/flights.csv";
		File file = new File(BookingServiceTest.class.getResource(filename).getFile());
		flightService.loadDataFromfile(file);

		List<Flight> flightList = new ArrayList<>();
		for (Flight flight : flightService.getDatabase().values()) {
			flightList.add(flight);
		}
		airportService.loadFromFlights(flightList);
	}

	@Before
	public void before() {
		bookingService = new BookingService();
		bookingService.setAirportService(airportService);
	}

	/**
	 * 1 adult, 31 days to the departure date, flying AMS -> FRA
	 * <p>
	 * flights:
	 * <ul>
	 * <li>TK2372, 157.6 €
	 * <li>TK2659, 198.4 €
	 * <li>LH5909, 90.4 €
	 * </ul>
	 * 
	 * @throws FlightNotFoundException
	 */
	@Test
	public void searchFlightTest1() throws FlightNotFoundException {

		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DAY_OF_MONTH, 31);

		Date departureDate = calendar.getTime();
		String originAirportCode = "AMS";
		String destinationAirportCode = "FRA";
		List<Passenger> passangerList = new ArrayList<>();
		passangerList.add(new Passenger(PassengerType.ADULT));

		List<BookFlight> flightsFound = bookingService.findFlights(departureDate, originAirportCode,
				destinationAirportCode, passangerList);

		Assert.assertNotNull("Flights not found", flightsFound);
		Assert.assertEquals("Number of flights", 3, flightsFound.size());

		for (BookFlight bookFlight : flightsFound) {
			System.out.println(bookFlight);
			Assert.assertNotNull("No se encontro destino", bookFlight.getFlight());
			Assert.assertNotNull("No se encontro costo total", bookFlight.getTotalCost());
			
			String expectedCode = null;
			BigDecimal expectedTotalCost = null;
			if ("LH5909".equals(bookFlight.getFlight().getCode())) {
				expectedCode = "LH5909";
				expectedTotalCost = BigDecimal.valueOf(90.4);
			} else if ("TK2659".equals(bookFlight.getFlight().getCode())) {
				expectedCode = "TK2659";
				expectedTotalCost = BigDecimal.valueOf(198.4);
			} else if ("TK2372".equals(bookFlight.getFlight().getCode())) {
				expectedCode = "TK2372";
				expectedTotalCost = BigDecimal.valueOf(157.6);
			} else {
				Assert.fail("Flight code not valid");
			}
			
			Assert.assertEquals("Flight code not valid", expectedCode, bookFlight.getFlight().getCode());
			Assert.assertEquals("Flight cost not valid", expectedTotalCost, bookFlight.getTotalCost());
		}

	}

}
