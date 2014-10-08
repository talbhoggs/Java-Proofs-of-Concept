package com.daniel.spring;

public class Person {
	
	private String name;
	private Integer age;
	private String gender;
	private Car car;
	
	public Person() {
		
	}

	public Person(String name, Integer age) {
		this.name = name;
		this.age = age;
	}
	
	public Person(String name, Integer age, String gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", gender=" + gender
				+ ", car=" + car + "]";
	}
	
	public void onCreate() {
		System.out.println("Person object has been created and initialized");
	}
	
	public void onDestroy() {
		System.out.println("Person object has been removed (no harm done)");
	}
	
	public static Person getInstanceOfPerson(String name, Integer age) {
		Person newPerson = new Person(name, age);
		return newPerson;
	}

	

}
