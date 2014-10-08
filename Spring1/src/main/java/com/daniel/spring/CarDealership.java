package com.daniel.spring;

import java.util.*;

public class CarDealership {
	
	private String dealershipName;
	private List<Car> stock;
	private Car bestCar;
	private Map<String, String> people = new HashMap<String, String>();
	public Map<String, Person> people2 = new HashMap<String, Person>();
	
	public CarDealership(String name, List<Car> stock) {
		
		this.dealershipName = name;
		this.stock = stock;
		
	}
	
	public void setPeople(Map<String, String> workers) {
		this.people = workers;
	}
	
	public void setPeople2(Map<String, Person> workers) {
		this.people2 = workers;
	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Dealership name is: " + this.dealershipName + "\n");
		sb.append("Cars in the dealership: \n");
		
		for(Car car: this.stock) {
			sb.append(car + "\n");
		}
		
		sb.append("The dealership workers are:\n");
		sb.append(people+"\n");
		
		return sb.toString();
		
	}

	public Car getBestCar() {
		return bestCar;
	}

	public void setBestCar(Car bestCar) {
		this.bestCar = bestCar;
	}
	
	

}
