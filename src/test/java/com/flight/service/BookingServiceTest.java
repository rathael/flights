package com.flight.service;

import java.io.File;
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
import com.flight.exceptions.DepartureDateNotValid;
import com.flight.exceptions.FlightNotFoundException;
import com.flight.model.Airline;
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
	 * @throws DepartureDateNotValid
	 */
	@Test
	public void searchFlightTest1() throws FlightNotFoundException, DepartureDateNotValid {

		System.out.println("Running searchFlightTest1 -->");
		System.out.println("1 adult, 31 days to the departure date, flying AMS -> FRA");
		System.out.println("flights expected:");
		System.out.println("TK2372, 157.6 €");
		System.out.println("TK2659, 198.4 €");
		System.out.println("LH5909, 90.4 €");
		
		Date departureDate = getDepartureDate(31);
		String originAirportCode = "AMS";
		String destinationAirportCode = "FRA";
		List<Passenger> passangerList = new ArrayList<>();
		passangerList.add(new Passenger(PassengerType.ADULT));

		List<BookFlight> flightsFound = bookingService.findFlights(departureDate, originAirportCode,
				destinationAirportCode, passangerList);

		Assert.assertNotNull("Flights not found", flightsFound);
		Assert.assertEquals("Number of flights", 3, flightsFound.size());

		System.out.println("Test results: ");
		
		for (BookFlight bookFlight : flightsFound) {
			System.out.println(bookFlight.getFlight().getCode() + ", " + bookFlight.getTotalCost() + " €");
			Assert.assertNotNull("No se encontro destino", bookFlight.getFlight());
			Assert.assertNotNull("No se encontro costo total", bookFlight.getTotalCost());

			String expectedCode = null;
			Float expectedTotalCost = null;
			if ("LH5909".equals(bookFlight.getFlight().getCode())) {
				expectedCode = "LH5909";
				expectedTotalCost = 90.4f;
			} else if ("TK2659".equals(bookFlight.getFlight().getCode())) {
				expectedCode = "TK2659";
				expectedTotalCost = 198.4f;
			} else if ("TK2372".equals(bookFlight.getFlight().getCode())) {
				expectedCode = "TK2372";
				expectedTotalCost = 157.6f;
			} else {
				Assert.fail("Flight code not valid");
			}

			Assert.assertEquals("Flight code not valid", expectedCode, bookFlight.getFlight().getCode());
			Assert.assertEquals("Flight cost not valid [" + expectedCode + "]", expectedTotalCost,
					bookFlight.getTotalCost());
		}

		System.out.println("Finish searchFlightTest1 <--");
	}

	/**
	 * 2 adults, 1 child, 1 infant, 15 days to the departure date, flying LHR ->
	 * IST
	 * <p>
	 * flights:
	 * <ul>
	 * <li>TK8891, 806 € (2 * (120% of 250) + 67% of (120% of 250) + 5)
	 * <li>LH1085, 481.19 € (2 * (120% of 148) + 67% of (120% of 148) + 7)
	 * </ul>
	 * 
	 * @throws FlightNotFoundException
	 * @throws DepartureDateNotValid
	 */
	@Test
	public void searchFlightTest2() throws FlightNotFoundException, DepartureDateNotValid {

		System.out.println("Running searchFlightTest2 -->");
		System.out.println("2 adults, 1 child, 1 infant, 15 days to the departure date, flying LHR -> IST");
		System.out.println("flights expected:");
		System.out.println("TK8891, 806 € (2 * (120% of 250) + 67% of (120% of 250) + 5)");
		System.out.println("LH1085, 481.19 € (2 * (120% of 148) + 67% of (120% of 148) + 7)");
		
		Date departureDate = getDepartureDate(15);
		String originAirportCode = "LHR";
		String destinationAirportCode = "IST";
		List<Passenger> passangerList = new ArrayList<>();
		passangerList.add(new Passenger(PassengerType.ADULT));
		passangerList.add(new Passenger(PassengerType.ADULT));
		passangerList.add(new Passenger(PassengerType.CHILD));
		passangerList.add(new Passenger(PassengerType.INFANT));

		List<BookFlight> flightsFound = bookingService.findFlights(departureDate, originAirportCode,
				destinationAirportCode, passangerList);

		Assert.assertNotNull("Flights not found", flightsFound);
		Assert.assertEquals("Number of flights", 2, flightsFound.size());

		System.out.println("Test results: ");
		
		for (BookFlight bookFlight : flightsFound) {
			System.out.println(bookFlight.getFlight().getCode() + ", " + bookFlight.getTotalCost() + " €");
			Assert.assertNotNull("No se encontro destino", bookFlight.getFlight());
			Assert.assertNotNull("No se encontro costo total", bookFlight.getTotalCost());

			String expectedCode = null;
			Float expectedTotalCost = null;
			if ("TK8891".equals(bookFlight.getFlight().getCode())) {
				expectedCode = "TK8891";
				expectedTotalCost = 806f;
			} else if ("LH1085".equals(bookFlight.getFlight().getCode())) {
				expectedCode = "LH1085";
				expectedTotalCost = 481.19f;
			} else {
				Assert.fail("Flight code not valid");
			}

			Assert.assertEquals("Flight code not valid", expectedCode, bookFlight.getFlight().getCode());
			Assert.assertEquals("Flight cost not valid [" + expectedCode + "]", expectedTotalCost,
					bookFlight.getTotalCost());
		}

		System.out.println("Finish searchFlightTest2 <--");
	}

	/**
	 * 1 adult, 2 children, 2 days to the departure date, flying BCN -> MAD
	 * <p>
	 * flights:
	 * <ul>
	 * <li>IB2171, 909.09 € (150% of 259 + 2 * 67% of (150% of 259))
	 * <li>LH5496, 1028.43 € (150% of 293 + 2 * 67% of (150% of 293))
	 * </ul>
	 * 
	 * @throws FlightNotFoundException
	 * @throws DepartureDateNotValid
	 */
	@Test
	public void searchFlightTest3() throws FlightNotFoundException, DepartureDateNotValid {

		System.out.println("Running searchFlightTest3 -->");
		System.out.println("1 adult, 2 children, 2 days to the departure date, flying BCN -> MAD");
		System.out.println("flights expected:");
		System.out.println("IB2171, 909.09 € (150% of 259 + 2 * 67% of (150% of 259))");
		System.out.println("LH5496, 1028.43 € (150% of 293 + 2 * 67% of (150% of 293))");
		
		Date departureDate = getDepartureDate(2);
		String originAirportCode = "BCN";
		String destinationAirportCode = "MAD";
		List<Passenger> passangerList = new ArrayList<>();
		passangerList.add(new Passenger(PassengerType.ADULT));
		passangerList.add(new Passenger(PassengerType.CHILD));
		passangerList.add(new Passenger(PassengerType.CHILD));

		List<BookFlight> flightsFound = bookingService.findFlights(departureDate, originAirportCode,
				destinationAirportCode, passangerList);

		Assert.assertNotNull("Flights not found", flightsFound);
		Assert.assertEquals("Number of flights", 2, flightsFound.size());

		System.out.println("Test results: ");
		
		for (BookFlight bookFlight : flightsFound) {
			System.out.println(bookFlight.getFlight().getCode() + ", " + bookFlight.getTotalCost() + " €");
			Assert.assertNotNull("No se encontro destino", bookFlight.getFlight());
			Assert.assertNotNull("No se encontro costo total", bookFlight.getTotalCost());

			String expectedCode = null;
			Float expectedTotalCost = null;
			if ("IB2171".equals(bookFlight.getFlight().getCode())) {
				expectedCode = "IB2171";
				expectedTotalCost = 909.09f;
			} else if ("LH5496".equals(bookFlight.getFlight().getCode())) {
				expectedCode = "LH5496";
				expectedTotalCost = 1028.43f;
			} else {
				Assert.fail("Flight code not valid");
			}

			Assert.assertEquals("Flight code not valid", expectedCode, bookFlight.getFlight().getCode());
			Assert.assertEquals("Flight cost not valid [" + expectedCode + "]", expectedTotalCost,
					bookFlight.getTotalCost());
		}

		System.out.println("Finish searchFlightTest3 <--");
	}

	/**
	 * CDG -> FRA
	 * <p>
	 * no flights available
	 * 
	 * @throws FlightNotFoundException
	 * @throws DepartureDateNotValid
	 */
	@Test
	public void searchFlightTest4() throws DepartureDateNotValid {

		System.out.println("Running searchFlightTest4 -->");
		System.out.println("CDG -> FRA");
		System.out.println("flights expected:");
		System.out.println("no flights available");
		
		Date departureDate = getDepartureDate(2);
		String originAirportCode = "CDG";
		String destinationAirportCode = "FRA";
		List<Passenger> passangerList = new ArrayList<>();
		passangerList.add(new Passenger(PassengerType.ADULT));
		passangerList.add(new Passenger(PassengerType.CHILD));
		passangerList.add(new Passenger(PassengerType.CHILD));

		try {
			bookingService.findFlights(departureDate, originAirportCode, destinationAirportCode, passangerList);

			Assert.fail("No flights expected");
		} catch (FlightNotFoundException e) {
			// do nothing
			System.out.println("Test results: ");
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Create a departure date for testing
	 * 
	 * @param days
	 * @return
	 */
	private Date getDepartureDate(int days) {
		Calendar departureDate = new GregorianCalendar();
		departureDate.add(Calendar.DAY_OF_MONTH, days);
		departureDate.set(Calendar.HOUR, 0);
		departureDate.set(Calendar.MINUTE, 0);
		departureDate.set(Calendar.SECOND, 0);
		departureDate.set(Calendar.MILLISECOND, 0);

		return departureDate.getTime();
	}

	/**
	 * Test rate by departure date
	 * 
	 * @throws DepartureDateNotValid
	 */
	@Test
	public void calculateRateByDepartureDateTest() throws DepartureDateNotValid {
		Float rate = null;
		Date departureDate = null;

		// 50 days
		departureDate = getDepartureDate(50);
		rate = bookingService.calculateRateByDepartureDate(departureDate);
		Assert.assertEquals("Rate not valid", 0.8f, rate.floatValue(), 0.01);

		// 31 days
		departureDate = getDepartureDate(31);
		rate = bookingService.calculateRateByDepartureDate(departureDate);
		Assert.assertEquals("Rate not valid", 0.8f, rate.floatValue(), 0.01);

		// 30 days
		departureDate = getDepartureDate(30);
		rate = bookingService.calculateRateByDepartureDate(departureDate);
		Assert.assertEquals("Rate not valid", 1f, rate.floatValue(), 0.01);

		// 25 days
		departureDate = getDepartureDate(25);
		rate = bookingService.calculateRateByDepartureDate(departureDate);
		Assert.assertEquals("Rate not valid", 1f, rate.floatValue(), 0.01);

		// 16 days
		departureDate = getDepartureDate(16);
		rate = bookingService.calculateRateByDepartureDate(departureDate);
		Assert.assertEquals("Rate not valid", 1f, rate.floatValue(), 0.01);

		// 15 days
		departureDate = getDepartureDate(15);
		rate = bookingService.calculateRateByDepartureDate(departureDate);
		Assert.assertEquals("Rate not valid", 1.2f, rate.floatValue(), 0.01);

		// 10 days
		departureDate = getDepartureDate(10);
		rate = bookingService.calculateRateByDepartureDate(departureDate);
		Assert.assertEquals("Rate not valid", 1.2f, rate.floatValue(), 0.01);

		// 3 days
		departureDate = getDepartureDate(3);
		rate = bookingService.calculateRateByDepartureDate(departureDate);
		Assert.assertEquals("Rate not valid", 1.2f, rate.floatValue(), 0.01);

		// 2 days
		departureDate = getDepartureDate(2);
		rate = bookingService.calculateRateByDepartureDate(departureDate);
		Assert.assertEquals("Rate not valid", 1.5f, rate.floatValue(), 0.01);

		// 0 days
		departureDate = getDepartureDate(0);
		rate = bookingService.calculateRateByDepartureDate(departureDate);
		Assert.assertEquals("Rate not valid", 1.5f, rate.floatValue(), 0.01);

		// -1 days
		try {
			departureDate = getDepartureDate(-1);
			rate = bookingService.calculateRateByDepartureDate(departureDate);
			Assert.fail("Rate not valid");
		} catch (DepartureDateNotValid e) {
			// do nothing
		}

	}

	/**
	 * 
	 */
	@Test
	public void calculateRateByPassengerTypeTest(){
		Float rate = null;
		
		rate = bookingService.calculateRateByPassengerType(PassengerType.ADULT);
		Assert.assertEquals("Rate not valid", 1f, rate, 0.01);
		
		rate = bookingService.calculateRateByPassengerType(PassengerType.CHILD);
		Assert.assertEquals("Rate not valid", 0.67f, rate, 0.01);
		
		rate = bookingService.calculateRateByPassengerType(PassengerType.INFANT);
		Assert.assertEquals("Rate not valid", 1F, rate, 0.01);
	}
	
	/**
	 * 
	 */
	@Test
	public void costPerPassengerTypeTest(){
		Airline airline = new Airline(null, null, 10);
		Flight flight = new Flight(null, null, null, airline, 50);
		Float rateByDepartureDate = 1f;
		Float cost = null;
		
		// Adult
		cost = bookingService.costPerPassengerType(flight, PassengerType.ADULT, rateByDepartureDate);
		
		Assert.assertNotNull("Cost is null", cost);
		Assert.assertEquals("Cost not valid", flight.getBasePrice(), cost, 0.01);
		
		// Child
		cost = bookingService.costPerPassengerType(flight, PassengerType.CHILD, rateByDepartureDate);
		
		Assert.assertNotNull("Cost is null", cost);
		Assert.assertEquals("Cost not valid", flight.getBasePrice() * 0.67, cost, 0.01);
		
		// Infant
		cost = bookingService.costPerPassengerType(flight, PassengerType.INFANT, rateByDepartureDate);
		
		Assert.assertNotNull("Cost is null", cost);
		Assert.assertEquals("Cost not valid", airline.getInfantPrice(), cost, 0.01);
		
	}
	
}
