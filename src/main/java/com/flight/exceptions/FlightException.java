package com.flight.exceptions;

public class FlightException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2649969475146237897L;

	public FlightException() {
		super();
	}

	public FlightException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public FlightException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public FlightException(String arg0) {
		super(arg0);
	}

	public FlightException(Throwable arg0) {
		super(arg0);
	}

}
