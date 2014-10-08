package com.daniel.models;

import java.util.*;

public class Toy {

	private int id = 0;
	private String name;
	private Set<Girl> girls;
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
	public Set<Girl> getGirls() {
		return girls;
	}
	public void setGirls(Set<Girl> girls) {
		this.girls = girls;
	}
	
	
	
}
