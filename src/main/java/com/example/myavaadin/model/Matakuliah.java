package com.example.myavaadin.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="matakuliah")
@Data
public class Matakuliah {
	@Id
	@Column(name="id_mk", nullable = false)
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idMk;
	@Column(name="nama_mk", nullable = false)
	private String namaMk;
	@Column(name="sks", nullable = false)
	private int sks;
}
