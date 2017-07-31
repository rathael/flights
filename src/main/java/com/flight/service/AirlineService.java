package com.flight.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flight.model.Airline;

/**
 * Airport Service
 * @author alex
 *
 */
public class AirlineService {

	/**
	 * Flights database
	 */
	private Map<String, Airline> database = new HashMap<>();
	
	public AirlineService(){
		loadDefaultData();
	}
	
	/**
	 * Load flights to the database 
	 */
	public void loadData(List<Airline> itemList) {
		
		// Add flight to the database if not exists
		for (Airline data : itemList) {
			Airline itemFound = database.get(data.getCode());
			if (itemFound == null) {
				database.put(data.getCode(), data);
			}
		}
	}	

	/**
	 * Load default aiport info
	 */
	private void loadDefaultData() {
		Airline item = null;
		List<Airline> itemList = new ArrayList<>();
		
		item = new Airline("IB", "Iberia", 10);
		itemList.add(item);
		
		item = new Airline("BA", "British Airways", 15);
		itemList.add(item);
		
		item = new Airline("LH", "Lufthansa", 7);
		itemList.add(item);
		
		item = new Airline("FR", "Ryanair", 20);
		itemList.add(item);
		
		item = new Airline("VY", "Vueling", 10);
		itemList.add(item);
		
		item = new Airline("TK", "Turkish Airlines", 5);
		itemList.add(item);
		
		item = new Airline("U2", "Easyjet", 19.90f);
		itemList.add(item);
		
		this.loadData(itemList);
	}
	
	public Airline getAirline(String code) {
		return database.get(code);
	}
}
