package com.flight.model;

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
	private Float infantPrice;
	
	public Airline(String iataCode, String name, Float infantPrice) {
		super();
		this.code = iataCode;
		this.name = name;
		this.infantPrice = infantPrice;
	}
	
	public Airline(String iataCode, String name, int infantPrice) {
		this(iataCode, name,  Float.valueOf(infantPrice));
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
	public Float getInfantPrice() {
		return infantPrice;
	}

	public void setInfantPrice(Float infantPrice) {
		this.infantPrice = infantPrice;
	}

	@Override
	public String toString() {
		return "Airline [code=" + code + ", name=" + name + "]";
	}
	
	
}
