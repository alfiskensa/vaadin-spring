package com.example.myavaadin.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_mahasiswa database table.
 * 
 */
@Entity
@Table(name="user_mahasiswa")
@NamedQuery(name="UserMahasiswa.findAll", query="SELECT u FROM UserMahasiswa u")
public class UserMahasiswa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String password;

	private String role;

	private String username;

	//bi-directional many-to-one association to Mahasiswa
	@ManyToOne
	@JoinColumn(name="id_mahasiswa")
	private Mahasiswa mahasiswa;

	public UserMahasiswa() {
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

	public Mahasiswa getMahasiswa() {
		return this.mahasiswa;
	}

	public void setMahasiswa(Mahasiswa mahasiswa) {
		this.mahasiswa = mahasiswa;
	}

}