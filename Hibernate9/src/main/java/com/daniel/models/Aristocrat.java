package com.daniel.models;

import javax.persistence.*;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;


public class Aristocrat {


	private int id;
	private String name;
	private Mansion mansion;
	
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

	public Mansion getMansion() {
		return mansion;
	}

	public void setMansion(Mansion mansion) {
		this.mansion = mansion;
	}
	
	


}
