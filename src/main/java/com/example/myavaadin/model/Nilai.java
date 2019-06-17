package com.example.myavaadin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "nilai")
@Data
public class Nilai {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_mahasiswa")
	private Mahasiswa mahasiswa;
	
	@ManyToOne
	@JoinColumn(name = "id_mk")
	private Matakuliah matakuliah;

	@Column(name = "nilai")
	private String nilai;

}
