package com.daniel.models;

import java.util.*;


import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;

import org.hibernate.annotations.*;

@Entity
@Table(name="MANSIONS_ANNOTATED")
public class Mansion {

	@Id
	@Column(name="MANSION_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String address;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ARISTOCRAT_ID")
	private Aristocrat a;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Aristocrat getA() {
		return a;
	}

	public void setA(Aristocrat a) {
		this.a = a;
	}

	

}
