package com.daniel.spring;

public class CarFactory {
	
	public Car createCar(String make, Integer year) {
		System.out.println("The factory is hard at work in building a new car!");
		Car newCar = new Car(make, year);
		return newCar;
	}

}
