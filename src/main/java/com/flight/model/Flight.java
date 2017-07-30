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
	 * Flight code
	 */
	private String code;
	
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

	/**
	 * 
	 * @param origin
	 * @param destination
	 * @param airline
	 * @param basePrice
	 */
	public Flight(String code, Airport origin, Airport destination, Airline airline, BigDecimal basePrice) {
		super();
		this.code = code;
		this.origin = origin;
		this.destination = destination;
		this.airline = airline;
		this.basePrice = basePrice;
	}
	
	public Flight(String code, Airport origin, Airport destination, Airline airline, int basePrice) {
		this(code, origin, destination, airline, new BigDecimal(basePrice));
	}

	public Flight(String code, Airport origin, Airport destination, Airline airline, String basePrice) {
		this(code, origin, destination, airline, new BigDecimal(basePrice));
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
		return "Flight [code=" + code + ", origin=" + origin + ", destination=" + destination + ", airline=" + airline
				+ ", basePrice=" + basePrice + "]";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
