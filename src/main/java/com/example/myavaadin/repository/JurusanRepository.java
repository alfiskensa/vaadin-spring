package com.example.myavaadin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myavaadin.model.Jurusan;

@Repository
public interface JurusanRepository extends JpaRepository<Jurusan, Long> {
	
	List<Jurusan> findAllByNamaJurusanContainingIgnoreCase(String nama_jurusan);


}
