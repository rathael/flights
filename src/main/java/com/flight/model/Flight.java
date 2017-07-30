package com.flight.model;

import java.math.BigDecimal;

/**
 * Flight info
 * 
 * @author alex
 *
 */
public class Flight {

	/**
	 * Airport origin
	 */
	private Airport origin;

	/**
	 * Airport destination
	 */
	private Airport destination;

	/**
	 * Airline of the flight
	 */
	private Airline airline;

	/**
	 * Base price of the flight
	 */
	private BigDecimal basePrice;

	public Flight(Airport origin, Airport destination, Airline airline, BigDecimal basePrice) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.airline = airline;
		this.basePrice = basePrice;
	}

	public Airport getOrigin() {
		return origin;
	}

	public void setOrigin(Airport origin) {
		this.origin = origin;
	}

	public Airport getDestination() {
		return destination;
	}

	public void setDestination(Airport destination) {
		this.destination = destination;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	@Override
	public String toString() {
		return "Flight [origin=" + origin + ", destination=" + destination + ", airline=" + airline + ", basePrice="
				+ basePrice + "]";
	}
}
