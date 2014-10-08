package com.daniel;

import java.io.Serializable;
import java.util.Random;

import javax.enterprise.context.*;
import javax.enterprise.inject.Produces;

@RequestScoped //ApplicationScoped for example would make only 1 instance of this until the server restarts, so the fuel cost will be
// the same for every request until the server restarts and a new random number is generated.
public class AirplaneInformation implements Serializable {
	
	@Produces @MaxPassengers Integer getMaxPassengers() {
		return 250;
	}
	
	@Produces @FlightFuelCost Integer getFlightFuelCost() {
		return 1000+(new Random()).nextInt(1001); // random cause fuel price varies 
	}

}
