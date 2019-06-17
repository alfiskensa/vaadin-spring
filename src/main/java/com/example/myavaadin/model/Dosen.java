package com.example.myavaadin.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the dosen database table.
 * 
 */
@Entity
@NamedQuery(name="Dosen.findAll", query="SELECT d FROM Dosen d")
public class Dosen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="nama_dosen")
	private String namaDosen;

	private String nidn;
	

	public Dosen() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNamaDosen() {
		return this.namaDosen;
	}

	public void setNamaDosen(String namaDosen) {
		this.namaDosen = namaDosen;
	}

	public String getNidn() {
		return this.nidn;
	}

	public void setNidn(String nidn) {
		this.nidn = nidn;
	}

}