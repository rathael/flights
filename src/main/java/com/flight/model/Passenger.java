package com.flight.model;

import com.flight.enums.PassengerType;

/**
 * Passanger info
 * 
 * @author alex
 *
 */
public class Passenger {

	/**
	 * Type of passanger
	 */
	private PassengerType type;
	
	public Passenger() {
		
	}

	public Passenger(PassengerType type) {
		super();
		this.type = type;
	}

	/**
	 * Type of passanger
	 * @return
	 */
	public PassengerType getType() {
		return type;
	}

	/**
	 * Type of passanger
	 * @param type
	 */
	public void setType(PassengerType type) {
		this.type = type;
	}
}
