package com.example.myavaadin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "jurusan")
@Data
public class Jurusan{
	@Id
	@Column(name = "id_jurusan", nullable = false)
	private Long idjurusan;
	@Column(name="nama_jurusan")
	private String namaJurusan;
	@Column(name="deskripsi")
	private String deskripsi;
}
