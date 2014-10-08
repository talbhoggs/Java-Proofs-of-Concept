package com.daniel;

import java.io.IOException;


import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainPage
 */
public class MainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Airline airline1;   
	
	@Inject
	private AirplaneInformation fi;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		System.out.println("Created an airline with this airplane: " + 	airline1.getAirplane1().getType());
		System.out.println("The max passengers is: " + airline1.getAirplane1().getMaxPassengers()); // It's more interesting because the 
		// max passenger is injected. 
		System.out.println("Given the current fuel cost, the flight fuel cost is : $" + airline1.getAirplane1().getFlightFuelPrice()); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
