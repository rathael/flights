package com.flight.model;

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
	private String iataCode;

	/**
	 * Name of the airport
	 */
	private String name;

	/**
	 * 
	 * @param iATACode
	 * @param name
	 */
	public Airport(String iataCode, String name) {
		super();
		this.iataCode = iataCode;
		this.name = name;
	}

	/**
	 * IATA Code
	 * 
	 * @return
	 */
	public String getIataCode() {
		return iataCode;
	}

	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
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
}
