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
	private String code;
	
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
		this.code = iataCode;
		this.name = name;
		this.infantPrice = infantPrice;
	}
	
	public Airline(String iataCode, String name, int infantPrice) {
		this(iataCode, name, new BigDecimal(infantPrice));
	}
	
	public Airline(String iataCode, String name, double infantPrice) {
		this(iataCode, name, new BigDecimal(infantPrice));
	}

	/**
	 * IATA Code
	 * @return
	 */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	@Override
	public String toString() {
		return "Airline [code=" + code + ", name=" + name + "]";
	}
	
	
}
