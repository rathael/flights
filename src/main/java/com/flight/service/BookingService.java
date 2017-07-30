package com.flight.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.flight.exceptions.FlightNotFoundException;
import com.flight.model.Airport;
import com.flight.model.BookFlight;
import com.flight.model.Flight;
import com.flight.model.Passenger;

public class BookingService {

	private AirportService airportService;
	
	public BookingService() {
		
	}
	
	public List<BookFlight> findFlights(Date departureDate, String originAirportCode, String destinationAirportCode,
			List<Passenger> passangerList) throws FlightNotFoundException {

		List<BookFlight> bookFlightList = new ArrayList<>();
		BookFlight bookFlight = null;
		
		Airport origin = airportService.getAirports().get(originAirportCode);
		Airport destination = airportService.getAirport(destinationAirportCode);
		
		List<Flight> flights = origin.getFlightsTo().get(destination);
		
		if (flights == null || flights.size() == 0) {
			throw new FlightNotFoundException();
		}
		
		for (Flight flight : flights) {
			bookFlight = new BookFlight(flight, new BigDecimal(0));
			bookFlightList.add(bookFlight);
		}
		
		return bookFlightList;
		
	}

	public AirportService getAirportService() {
		return airportService;
	}

	public void setAirportService(AirportService airportService) {
		this.airportService = airportService;
	}
}
