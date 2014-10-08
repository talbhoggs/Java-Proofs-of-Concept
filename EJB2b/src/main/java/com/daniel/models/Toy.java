package com.daniel.models;

import java.io.Serializable;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="TOYS_ANNOTATED")
public class Toy  implements Serializable {

	@Id
	@Column(name="TOY_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id = 0;
	private String name;
	
	//@ManyToMany(
	//        cascade = {CascadeType.ALL},
	//        mappedBy = "toys",
	//        targetEntity = com.daniel.models.Girl.class
	//)
	@ManyToMany(
			targetEntity=com.daniel.models.Girl.class,
			cascade=(CascadeType.ALL)
	)
	@JoinTable(
		name="GIRLS_TOYS_ANNOTATED",
		joinColumns=@JoinColumn(name="TOY_ID"),
		inverseJoinColumns=@JoinColumn(name="GIRL_ID")
    )
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
