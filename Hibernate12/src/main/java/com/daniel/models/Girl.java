package com.daniel.models;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="GIRLS_ANNOTATED")
public class Girl implements Serializable {
	
	@Id
	@Column(name="GIRL_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id = 0;
	private String name;
	
	@ManyToMany(
			targetEntity=com.daniel.models.Toy.class,
			cascade=(CascadeType.ALL)
	)
	@JoinTable(
		name="GIRLS_TOYS_ANNOTATED",
		joinColumns=@JoinColumn(name="GIRL_ID"),
		inverseJoinColumns=@JoinColumn(name="TOY_ID")
    )
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
