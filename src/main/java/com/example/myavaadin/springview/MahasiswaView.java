package com.example.myavaadin.springview;

import java.time.ZoneId;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.myavaadin.model.Jurusan;
import com.example.myavaadin.design.MahasiswaDesign;
import com.example.myavaadin.model.Mahasiswa;
import com.example.myavaadin.repository.JurusanRepository;
import com.example.myavaadin.service.DataService;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.LocalDateToDateConverter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
@SpringView(name = MahasiswaView.VIEW_NAME)
@SuppressWarnings("serial")
public class MahasiswaView extends MahasiswaDesign implements View {
	
	public static final String VIEW_NAME = "Mahasiswa";
	
	private final Binder<Mahasiswa> binder = new BeanValidationBinder<>(Mahasiswa.class);
	
	@Autowired
	DataService service;
	
	public Mahasiswa currentMahasiswa;
	
	MahasiswaDetailView mahasiswaDetailV;
	
	Panel pnl;
	
	@PostConstruct
	void init() {
		update.addClickListener(event -> onSave());
    	cancel.addClickListener(event -> onCancel());
    	delete.addClickListener(event -> onDelete());
    	add.addClickListener(event -> newMahasiswa());
    	btn_v_matkul.addClickListener(event -> detailMahasiswa());
    	list.asSingleSelect().addValueChangeListener(event -> {
    		rowSelected(list.asSingleSelect().getValue());
    		btn_v_matkul.setEnabled(true);    		
    	});
	}


	@Override
	public void enter(ViewChangeEvent event) {
		tampilanAwal();
	}
	
	public void tampilanAwal() {
		setForm();
		setGrid();
		populateGrid();
	}
	
	public void setGrid() {
		list.removeAllColumns();
		list.addColumn(Mahasiswa::getNim).setCaption("NIM");
		list.addColumn(Mahasiswa::getNamaMahasiswa).setCaption("Nama Mahasiswa");
		list.addColumn(p -> p.getJurusan().getNamaJurusan()).setCaption("Jurusan");
		list.addColumn(Mahasiswa::getTglLahir).setCaption("Tanggal Lahir");
		list.addColumn(Mahasiswa::getNoTelp).setCaption("No Telp");
		list.addColumn(Mahasiswa::getAlamat).setCaption("Alamat");
		
	}
	
	public void populateGrid() {
		list.setItems(service.getAllMahasiswa());
	}
	
	public void setForm() {
		binder.forField(nim).bind("nim");
    	binder.forField(namaMahasiswa).bind("namaMahasiswa");
    	jurusan.setItemCaptionGenerator(Jurusan::getNamaJurusan);
    	jurusan.setItems(service.getAllJurusan());
    	binder.forField(jurusan).bind("jurusan");
    	binder.forField(tglLahir).withConverter(new LocalDateToDateConverter(ZoneId.systemDefault()))
		.bind("tglLahir");
    	binder.forField(notelp).bind("noTelp");
    	binder.forField(alamat).bind("alamat");
    
	}
	
	public void setUpData(Mahasiswa mahasiswa) {
		currentMahasiswa = mahasiswa;
		if (currentMahasiswa != null) {
            binder.readBean(currentMahasiswa);
        } else {
            binder.removeBean();
        }
	}
	
	public void showError(String msg) {
		Notification.show(msg, Type.ERROR_MESSAGE);
	}

	public void showSaveNotification(String msg) {
        Notification.show(msg, Type.TRAY_NOTIFICATION);
    }
	
	public void edit(Mahasiswa mahasiswa) {
		setUpData(mahasiswa);
	}
	
	public void rowSelected(Mahasiswa mahasiswa) {
		edit(mahasiswa);
	}
	
	public void onSave() {
		if (binder.writeBeanIfValid(currentMahasiswa)) {
			showSaveNotification(currentMahasiswa.getNamaMahasiswa() + " ("
	                + currentMahasiswa.getNim()+ ") updated");
			service.saveMahasiswa(currentMahasiswa);
			resetForm();
			populateGrid();
		}
	}
	
	public void onDelete() {
		service.deleteMahasiswa(currentMahasiswa.getIdMahasiswa());
		showSaveNotification(currentMahasiswa.getNamaMahasiswa() + " ("
                + currentMahasiswa.getNim() + ") removed");
		resetForm();
	}
	
	public void onCancel() {
		resetForm();
	}
	
	public void newMahasiswa() {
		resetForm();
		edit(new Mahasiswa());
	}
	
	public void detailMahasiswa() {
		this.mahasiswaDetailV = new MahasiswaDetailView();
		this.mahasiswaDetailV.setParam(this, this.currentMahasiswa);
		Panel p = (Panel) getParent();
		p.setContent(this.mahasiswaDetailV);
		pnl = p;
	}
	
	public void resetForm() {
		nim.clear();
		namaMahasiswa.clear();
		jurusan.clear();
		tglLahir.clear();
		notelp.clear();
		alamat.clear();
		nim.focus();
		list.getSelectionModel().deselectAll();
		btn_v_matkul.setEnabled(false);
	}
	
}
