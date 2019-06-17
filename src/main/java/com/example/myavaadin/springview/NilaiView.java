package com.example.myavaadin.springview;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.myavaadin.design.NilaiDesign;
import com.example.myavaadin.model.Mahasiswa;
import com.example.myavaadin.model.Matakuliah;
import com.example.myavaadin.model.Nilai;
import com.example.myavaadin.service.DataService;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

@SpringView(name = NilaiView.VIEW_NAME)
@SuppressWarnings("serial")
public class NilaiView extends NilaiDesign implements View{
	
	public static final String VIEW_NAME = "Nilai";
	
	private final Binder<Nilai> binder = new BeanValidationBinder<>(Nilai.class);
	
	@Autowired
	DataService service;
	
	public Nilai currentNilai;
	
	@PostConstruct
	void init() {
		update.addClickListener(event -> onSave());
    	cancel.addClickListener(event -> onCancel());
    	list.asSingleSelect().addValueChangeListener(event -> rowSelected(list.asSingleSelect().getValue()));
    	selectMhs.addValueChangeListener(e -> {
    		populateGrid(selectMhs.getValue());
    		resetForm();
    	});
	}


	@Override
	public void enter(ViewChangeEvent event) {
		tampilanAwal();
	}
	
	public void tampilanAwal() {
		setForm();
		setGrid();
		populateSelectMhs();
	}
	
	public void setGrid() {
		list.removeAllColumns();
		list.addColumn(p -> p.getMatakuliah().getNamaMk()).setCaption("Nama Matakuliah");
		list.addColumn(Nilai::getNilai).setCaption("Nilai");
		
	}
	
	public void populateSelectMhs() {
		selectMhs.setItemCaptionGenerator(Mahasiswa::getNamaMahasiswa);
		selectMhs.setItems(service.getAllMahasiswa());
	}
	
	public void populateGrid(Mahasiswa mahasiswa) {
		list.setItems(service.findNilaiByMahasiswa(mahasiswa));
	}
	
	public void setForm() {
		matakuliah.setItemCaptionGenerator(Matakuliah::getNamaMk);
		matakuliah.setItems(service.getAllMatakuliah());
		binder.forField(matakuliah).bind("matakuliah");
    	binder.forField(nilai).bind("nilai");
	}
	
	public void setUpData(Nilai nilai) {
		currentNilai = nilai;
		binder.readBean(currentNilai);
	}
	
	public void showError(String msg) {
		Notification.show(msg, Type.ERROR_MESSAGE);
	}

	public void showSaveNotification(String msg) {
        Notification.show(msg, Type.TRAY_NOTIFICATION);
    }
	
	public void edit(Nilai nilai) {
		setUpData(nilai);
	}
	
	public void rowSelected(Nilai nilai) {
		edit(nilai);
	}
	
	public void onSave() {
		try {
			binder.writeBean(currentNilai);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		showSaveNotification(currentNilai.getMahasiswa().getNamaMahasiswa() + ", "
				+ currentNilai.getMatakuliah().getNamaMk() + " (" + currentNilai.getNilai() + ")" + "updated");
		service.saveNilai(currentNilai);
		populateGrid(currentNilai.getMahasiswa());
		resetForm();
	}
	
	
	public void onCancel() {
		resetForm();
	}
	
	
	public void resetForm() {
		matakuliah.clear();
		nilai.clear();
		list.getSelectionModel().deselectAll();
	}
	

}
