package com.daniel.models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the simple_users database table.
 * 
 */
@Entity
@Table(name = "simple_users")
@NamedQuery(name = "SimpleUser.findAll", query = "SELECT s FROM SimpleUser s")
public class SimpleUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int age;

	private String name;

	public SimpleUser() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}