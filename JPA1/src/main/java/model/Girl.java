package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the girls database table.
 * 
 */
@Entity
@Table(name="girls")
@NamedQuery(name="Girl.findAll", query="SELECT g FROM Girl g")
public class Girl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String email;

	private String name;

	public Girl() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}