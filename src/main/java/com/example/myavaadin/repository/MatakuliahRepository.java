package com.example.myavaadin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.myavaadin.model.Matakuliah;
@Repository
public interface MatakuliahRepository extends JpaRepository<Matakuliah, Long>{
	
}
