package com.daniel;

import javax.inject.*;

public class Airline {
	
	@Inject
	private Airplane airplane1;

	public Airplane getAirplane1() {
		return airplane1;
	}

	public void setAirplane1(Airplane airplane1) {
		this.airplane1 = airplane1;
	}
	
	

}
