package com.daniel.models;

import java.util.*;

public class Girl {
	
	private int id = 0;
	private String name;
	private Set<Toy> toys;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Toy> getToys() {
		return toys;
	}
	public void setToys(Set<Toy> toys) {
		this.toys = toys;
	}
	
	
	
}
