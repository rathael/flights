package com.flight.service;

public class TestUtils {

	private static TestUtils instance;
	
	private TestUtils() {
		
	}
	
	public static TestUtils getInstance() {
		if (instance == null) {
			instance = new TestUtils();
		}
		
		return instance;
	}
	
	
}
