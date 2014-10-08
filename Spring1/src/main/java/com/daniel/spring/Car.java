package com.daniel.spring;

public class Car {
	
	private String make;
	private Integer year;
	
	public Car() {
		
	}
	
	public Car(String make, Integer year) {
		
		this.make = make;
		this.year = year;
	}

	@Override
	public String toString() {
		return "Car [make=" + make + ", year=" + year + "]";
	}
	
	public void onCreate() {
		System.out.println("Car object has been created and initialized: " + this.make + " " + this.year);
	}
	
	public void onDestroy() {
		System.out.println("Car object has been destroyed");
	}
	
	public static Car getInstanceOfCar(String make, Integer year) {
		Car newCar = new Car(make, year);
		return newCar;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	

}
