package com.example.myavaadin.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "mahasiswa")
@Data
public class Mahasiswa {
	@Id
	@Column(name = "id_mahasiswa", nullable = false)
	private long idMahasiswa;
	
	@Column(name = "nim", nullable = false)
	private String nim;
	
	@Column(name = "nama_mahasiswa", nullable = false)
	private String namaMahasiswa;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "tgl_lahir", nullable = false)
	private Date tglLahir;
	
	@Column(name = "alamat")
	private String alamat;
	
	@Column(name = "no_telp")
	private String noTelp;
	
	@ManyToOne
	@JoinColumn(name = "id_jurusan", nullable = false)
	private Jurusan jurusan;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="nilai", joinColumns = {@JoinColumn(name="id_mahasiswa")}, 
	inverseJoinColumns ={@JoinColumn(name="id_mk")}) 
	private Set<Matakuliah> matakuliah;

}
