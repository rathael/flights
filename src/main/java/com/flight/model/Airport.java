package com.flight.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Airport Info
 * 
 * @author alex
 *
 */
public class Airport {

	/**
	 * IATA Code
	 */
	private String code;

	/**
	 * Name of the airport
	 */
	private String name;

	/**
	 * Destination airports from this airport
	 */
	private Map<Airport, List<Flight>> flightsTo = new HashMap<>();
	
	/**
	 * 
	 * @param iATACode
	 * @param name
	 */
	public Airport(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	/**
	 * IATA Code
	 * 
	 * @return
	 */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Name of the airport
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Airport [code=" + code + ", name=" + name + "]";
	}

	public Map<Airport, List<Flight>> getFlightsTo() {
		return flightsTo;
	}

	public void setFlightsTo(Map<Airport, List<Flight>> flightsTo) {
		this.flightsTo = flightsTo;
	}
}
