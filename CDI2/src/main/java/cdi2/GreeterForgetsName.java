package cdi2;

import javax.ejb.*;
import javax.enterprise.context.RequestScoped;

@RequestScoped
@Stateful(name = "GreeterForgets")
public class GreeterForgetsName implements GreeterLocal {

	private String name = "X";
	
	public GreeterForgetsName() {
		
	}
	
	public void greet() {
		System.out.println("Hello, " + name + " what kind of car would you like?");
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
}
