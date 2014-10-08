package com.daniel.models;

import java.util.*;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GenerationType;

import org.hibernate.annotations.*;

@Entity
@Table(name = "HOUSES")
public class House {

	@Id
	@Column(name = "HOUSE_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String address;

	@OneToMany
	@JoinColumn(name = "HOUSE_ID")
	@Cascade(CascadeType.ALL)
	private List<Resident> lr;

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

	public List<Resident> getLr() {
		return lr;
	}

	public void setLr(List<Resident> lr) {
		this.lr = lr;
	}

}
