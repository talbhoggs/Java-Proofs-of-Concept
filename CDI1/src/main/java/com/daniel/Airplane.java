package com.daniel;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class Airplane {

	private String type = "Boeing 787";

	@MaxPassengers
	@Inject
	private Integer maxPassengers;

	@FlightFuelCost
	@Inject
	private Integer flightFuelPrice;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getMaxPassengers() {
		return maxPassengers;
	}

	public void setMaxPassengers(Integer maxPassengers) {
		this.maxPassengers = maxPassengers;
	}

	public Integer getFlightFuelPrice() {
		return flightFuelPrice;
	}

	public void setFlightFuelPrice(Integer flightFuelPrice) {
		this.flightFuelPrice = flightFuelPrice;
	}

}
