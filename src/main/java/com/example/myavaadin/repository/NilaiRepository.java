package com.example.myavaadin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myavaadin.model.Mahasiswa;
import com.example.myavaadin.model.Nilai;
import java.util.List;

public interface NilaiRepository extends JpaRepository<Nilai, Long>{
	
	List<Nilai> findByMahasiswa(Mahasiswa mahasiswa);
}
