package com.flight.exceptions;

public class DepartureDateNotValid extends FlightException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8751390531227135665L;

	public DepartureDateNotValid() {
	}

	public DepartureDateNotValid(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public DepartureDateNotValid(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DepartureDateNotValid(String arg0) {
		super(arg0);
	}

	public DepartureDateNotValid(Throwable arg0) {
		super(arg0);
	}

}
