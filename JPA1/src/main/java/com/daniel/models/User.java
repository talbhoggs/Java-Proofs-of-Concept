package com.daniel.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="girls")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String email;


	private String name;


	public User() {
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	

}