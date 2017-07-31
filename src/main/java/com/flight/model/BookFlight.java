package com.flight.model;

public class BookFlight {

	private Flight flight;
	
	private Float totalCost;
	
	public BookFlight(Flight flight, Float totalCost) {
		this.flight = flight;
		this.totalCost = totalCost;
	}

	public Float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Float totalCost) {
		this.totalCost = totalCost;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	@Override
	public String toString() {
		return "BookFlight [flight=" + flight + ", totalCost=" + totalCost + "]";
	}

}
