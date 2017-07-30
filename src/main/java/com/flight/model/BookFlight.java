package com.flight.model;

import java.math.BigDecimal;

public class BookFlight {

	private Flight flight;
	
	private BigDecimal totalCost;
	
	public BookFlight(Flight flight, BigDecimal totalCost) {
		this.flight = flight;
		this.totalCost = totalCost;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

}
