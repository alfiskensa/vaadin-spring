package com.example.myavaadin.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.myavaadin.model.Jurusan;
import com.example.myavaadin.model.Mahasiswa;
import com.example.myavaadin.model.Matakuliah;
@Repository
public interface MahasiswaRepository extends JpaRepository<Mahasiswa, Long> {
	
	 Optional<Mahasiswa> findByNim(String nim);
	 List<Mahasiswa> findByJurusan(Jurusan jurusan);
	 //List<Mahasiswa> findByMatakuliah(Matakuliah matakuliah);
	 List<Mahasiswa> findDistinctByNamaMahasiswaContainingIgnoreCase(
			 String nama_mahasiswa, Pageable page);
	 
	 @Modifying
	 @Query("Update Mahasiswa m SET m.matakuliah=:mk WHERE m.idMahasiswa=:id")
	 void updateMkMhs(@Param("id") Long id, @Param("mk") Set<Matakuliah> mk);

}
