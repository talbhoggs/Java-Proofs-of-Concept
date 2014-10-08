package com.daniel.models;

import javax.persistence.*;

@Entity
@Table(name="JPA_MOVIES")
public class Movie {
	
	@Id
	@Column(name="EMPLOYEE_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id = 0;
	private String title = null;
	private String director = null;
	private String year = null;
	
	public Movie() {
		
	}
	
	public Movie(String t, String d, String y) {
		this.title = t;
		this.director = d;
		this.year = y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

}
