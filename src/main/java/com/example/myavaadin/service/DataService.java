package com.example.myavaadin.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myavaadin.model.Jurusan;
import com.example.myavaadin.model.Mahasiswa;
import com.example.myavaadin.model.Matakuliah;
import com.example.myavaadin.model.Nilai;
import com.example.myavaadin.repository.JurusanRepository;
import com.example.myavaadin.repository.MahasiswaRepository;
import com.example.myavaadin.repository.MatakuliahRepository;
import com.example.myavaadin.repository.NilaiRepository;

@Service
@Transactional
public class DataService{
	
	@Autowired
	private MahasiswaRepository mahasiswaRepository;
	
	@Autowired
	private JurusanRepository jurusanRepository;
	
	@Autowired
	private MatakuliahRepository matakuliahRepository;
	
	@Autowired
	private NilaiRepository nilaiRepository;
	
	public Collection<Mahasiswa> getAllMahasiswa() {
        return mahasiswaRepository.findAll();
    }
	
	public Mahasiswa getMahasiswa(Long id_mahasiswa) {
	    return mahasiswaRepository.getOne(id_mahasiswa);
	}
	 
    public Mahasiswa saveMahasiswa(Mahasiswa mahasiswa) {
        return mahasiswaRepository.save(mahasiswa);
    }
    
    public void updateMahasiswa(long id, Set<Matakuliah> mk) {
    	mahasiswaRepository.updateMkMhs(id, mk);
    }

    public void deleteMahasiswa(Long id_mahasiswa) {
        mahasiswaRepository.deleteById(id_mahasiswa);
    }
    
    public List<Jurusan> getAllJurusan() {
        return jurusanRepository.findAll();
    }
    
    public Jurusan getJurusan(Long id) {
	    return jurusanRepository.getOne(id);
	}
	 
    public Jurusan saveJurusan(Jurusan jurusan) {
        return jurusanRepository.save(jurusan);
    }

    public void deleteJurusan(Long id) {
        jurusanRepository.deleteById(id);
    }
    
    public List<Matakuliah> getAllMatakuliah() {
        return matakuliahRepository.findAll();
    }
    
    public Matakuliah getMatakuliah(Long id) {
	    return matakuliahRepository.getOne(id);
	}
	 
    public Matakuliah saveMatakuliah(Matakuliah matakuliah) {
        return matakuliahRepository.save(matakuliah);
    }

    public void deleteMatakuliah(Long id) {
        matakuliahRepository.deleteById(id);
    }
    
    public List<Nilai> findNilaiByMahasiswa(Mahasiswa mahasiswa) {
    	return nilaiRepository.findByMahasiswa(mahasiswa);
    }
    
    public void saveNilai(Nilai nilai) {
    	nilaiRepository.save(nilai);
    }
  
    
    
}
