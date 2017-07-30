package com.flight.model;

import java.math.BigDecimal;

/**
 * Airline
 * 
 * @author alex
 *
 */
public class Airline {

	/**
	 * IATA Code
	 */
	private String iataCode;
	
	/**
	 * Name of the airline
	 */
	private String name;
	
	/**
	 * Fixed price to infants
	 */
	private BigDecimal infantPrice;
	
	public Airline(String iataCode, String name, BigDecimal infantPrice) {
		super();
		this.iataCode = iataCode;
		this.name = name;
		this.infantPrice = infantPrice;
	}

	/**
	 * IATA Code
	 * @return
	 */
	public String getIataCode() {
		return iataCode;
	}

	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}

	/**
	 * Name of the airline
	 * @return
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Fixed price to infants
	 * @return
	 */
	public BigDecimal getInfantPrice() {
		return infantPrice;
	}

	public void setInfantPrice(BigDecimal infantPrice) {
		this.infantPrice = infantPrice;
	}
	
	
}
