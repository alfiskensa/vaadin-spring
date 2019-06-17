package com.example.myavaadin.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the user_dosen database table.
 * 
 */
@Entity
@Table(name="user_dosen")
@NamedQuery(name="UserDosen.findAll", query="SELECT u FROM UserDosen u")
public class UserDosen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String password;

	private String role;

	private String username;

	//bi-directional many-to-one association to Dosen
	@ManyToOne
	@JoinColumn(name="id_dosen")
	private Dosen dosen;

	public UserDosen() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Dosen getDosen() {
		return this.dosen;
	}

	public void setDosen(Dosen dosen) {
		this.dosen = dosen;
	}

}