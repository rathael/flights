package com.flight.exceptions;

public class FlightNotFoundException extends FlightException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1678248222166318056L;

	public FlightNotFoundException() {
	}

	public FlightNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public FlightNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public FlightNotFoundException(String arg0) {
		super(arg0);
	}

	public FlightNotFoundException(Throwable arg0) {
		super(arg0);
	}

}
