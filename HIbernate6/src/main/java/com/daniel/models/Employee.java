package com.daniel.models;

import javax.persistence.*;
@Entity
@Table(name="TBL_EMPLOYEE")
public class Employee {

	@Id
	@Column(name="EMPLOYEE_ID")
	@GeneratedValue(strategy = GenerationType.AUTO) // From db, auto increment, without this, it will set your assigned value (value you
	// give) to the employeeId instance var (property). Then you have to manually verify employeeId uniqueness.
	private int employeeId = 0; // zero ignored
	private String name;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
