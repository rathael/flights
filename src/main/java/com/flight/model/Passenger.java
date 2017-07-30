package com.flight.model;

import com.flight.enums.PassangerType;

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
	private PassangerType type;
	
	public Passenger() {
		
	}

	/**
	 * Type of passanger
	 * @return
	 */
	public PassangerType getType() {
		return type;
	}

	/**
	 * Type of passanger
	 * @param type
	 */
	public void setType(PassangerType type) {
		this.type = type;
	}
}
