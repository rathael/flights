package com.flight.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.flight.enums.PassengerType;
import com.flight.exceptions.DepartureDateNotValid;
import com.flight.exceptions.FlightNotFoundException;
import com.flight.model.Airport;
import com.flight.model.BookFlight;
import com.flight.model.Flight;
import com.flight.model.Passenger;

/**
 * Main logic of the search of flights
 * 
 * @author alex
 *
 */
public class BookingService {

	/**
	 * Provides airports information
	 */
	private AirportService airportService;

	public BookingService() {

	}

	/**
	 * Find Flights
	 * 
	 * @param departureDate
	 * @param originAirportCode
	 * @param destinationAirportCode
	 * @param passangerList
	 * @return
	 * @throws FlightNotFoundException
	 * @throws DepartureDateNotValid
	 */
	public List<BookFlight> findFlights(Date departureDate, String originAirportCode, String destinationAirportCode,
			List<Passenger> passangerList) throws FlightNotFoundException, DepartureDateNotValid {

		List<BookFlight> bookFlightList = new ArrayList<>();
		BookFlight bookFlight = null;

		Airport origin = airportService.getAirports().get(originAirportCode);
		Airport destination = airportService.getAirport(destinationAirportCode);

		List<Flight> flights = origin.getFlightsTo().get(destination);

		if (flights == null || flights.size() == 0) {
			throw new FlightNotFoundException("No flights available");
		}

		for (Flight flight : flights) {
			Float flightCost = calculateFlightCost(flight, departureDate, passangerList);

			bookFlight = new BookFlight(flight, flightCost);
			bookFlightList.add(bookFlight);
		}

		return bookFlightList;
	}

	/**
	 * Calculate the flight cost per passenger
	 * 
	 * @param flight
	 * @param departureDate
	 * @param passangerList
	 * @return
	 * @throws DepartureDateNotValid
	 */
	public Float calculateFlightCost(Flight flight, Date departureDate, List<Passenger> passangerList)
			throws DepartureDateNotValid {
		Float flightCost = 0f;

		Float rateByDepartureDate = calculateRateByDepartureDate(departureDate);

		for (Passenger passenger : passangerList) {
			Float flightCostPerPassenger = costPerPassengerType(flight, passenger.getType(), rateByDepartureDate);

			flightCost += flightCostPerPassenger;
		}

		flightCost = new BigDecimal(flightCost).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();

		return flightCost;
	}

	/**
	 * Calculate the rate (discount) per passenger type
	 * <p>
	 * | passenger type | price |<br>
	 * |----------------|------------------------------------------------------------------------------------------------|<br>
	 * | adult | full price (i.e. price resulting from the *days to departure
	 * date* rule) |<br>
	 * | child | 33% discount of the price calculated according to the *days to
	 * departure date* rule |<br>
	 * | infant | fixed price depending on the airline. Rule *days to departure
	 * date* is not applied for infants |<br>
	 * 
	 * @param passengerType
	 * @return
	 */
	public Float calculateRateByPassengerType(PassengerType passengerType) {
		Float rate = null;

		switch (passengerType) {
		case ADULT:
			rate = 1f;
			break;

		case CHILD:
			rate = 0.67f;
			break;

		default:
			rate = 1f;
			break;
		}

		return rate;
	}

	/**
	 * Calculate the flight cost per passenger type
	 * 
	 * @param flight
	 * @param passengerType
	 * @param rateByDepartureDate
	 * @return
	 */
	public Float costPerPassengerType(Flight flight, PassengerType passengerType, Float rateByDepartureDate) {
		Float cost = null;

		switch (passengerType) {
		case ADULT:
		case CHILD:
			cost = flight.getBasePrice() * rateByDepartureDate * calculateRateByPassengerType(passengerType);
			break;

		case INFANT:
			cost = flight.getAirline().getInfantPrice();
			break;

		default:
			cost = 0f;
			break;
		}

		return cost;
	}

	/**
	 * Calculate rate by departure date
	 * 
	 * @param departureDate
	 * @return
	 * @throws DepartureDateNotValid
	 */
	public Float calculateRateByDepartureDate(Date departureDate) throws DepartureDateNotValid {
		Float rate = null;

		Calendar now = new GregorianCalendar();
		now.set(Calendar.HOUR, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);

		int daysBetweenDates = calculateDaysBetweenDates(now.getTime(), departureDate);

		if (daysBetweenDates >= 31) {
			rate = 0.8f;
		} else if (daysBetweenDates <= 30 && daysBetweenDates >= 16) {
			rate = 1f;
		} else if (daysBetweenDates <= 15 && daysBetweenDates >= 3) {
			rate = 1.2f;
		} else if (daysBetweenDates <= 2 && daysBetweenDates >= 0) {
			rate = 1.5f;
		} else {
			throw new DepartureDateNotValid();
		}

		return rate;
	}

	/**
	 * Calculate days between dates
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public int calculateDaysBetweenDates(Date d1, Date d2) {
		int days = (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));

		// System.out.println("date 1=" + d1 + ", date 2=" + d2 + ",
		// days="+((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24)));

		return days;
	}

	public AirportService getAirportService() {
		return airportService;
	}

	public void setAirportService(AirportService airportService) {
		this.airportService = airportService;
	}
}
