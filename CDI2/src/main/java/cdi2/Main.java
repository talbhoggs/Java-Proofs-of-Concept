package cdi2;

import java.io.IOException;

import javax.enterprise.inject.*;
import javax.inject.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/**
 * Servlet implementation class Main
 */
@WebServlet("/CDIDemo")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
	
	//This won't work because it won't be able to decide whether to inject the family car or the sports car.
	//@Inject
	//private Car myCar;
	
	@Inject
	@FamilyCarQualifier
	private Car myFamilyCar;
	
	@Inject
	@SportsCarQualifier
	private Car mySportsCar;
	
	//@Any will inject all the different car types that implement the Car interface. Then you can iterate through the resulting array like data structure.
	@Inject
	@Any
	private Instance<Car> allTheDifferentCars;
	
	@Inject
	@Any
	private Instance<GreeterLocal> greeters;
	
	private Integer counter = 0;
	
    public Main() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//greeter.greet();
		
		System.out.println(myFamilyCar);
		System.out.println(mySportsCar);
		
		for(Car someCar : allTheDifferentCars) {
			System.out.println("Iterating through allTheDifferentCars: " + someCar);
		}
		
		counter++;
		
		//System.out.println(counter);
		System.out.println("Iterating through greeters: ");
		
		for(GreeterLocal greeter : greeters) {
			if(counter == 1) {
				greeter.setName("Daniel");
			}

			System.out.println("The counter is: " + counter + ", when the counter is 1 both greeters will remember the name, but when the counter is greter than 1 only one greeter will remember your name, while the other greeter will forget it.");
			greeter.greet();
		}
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
