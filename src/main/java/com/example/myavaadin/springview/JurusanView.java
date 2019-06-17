package com.example.myavaadin.springview;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.myavaadin.design.JurusanDesign;
import com.example.myavaadin.model.Jurusan;
import com.example.myavaadin.model.Matakuliah;
import com.example.myavaadin.service.DataService;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

@SuppressWarnings("serial")
@SpringView(name = JurusanView.VIEW_NAME)
public class JurusanView extends JurusanDesign implements View {
	public static final String VIEW_NAME = "Jurusan";
	
	private final Binder<Jurusan> binder = new BeanValidationBinder<>(Jurusan.class);
	
	@Autowired
	DataService service;
	
	public Jurusan currentJur;
	
	@PostConstruct
	void init() {
		update.addClickListener(event -> onSave());
    	cancel.addClickListener(event -> onCancel());
    	delete.addClickListener(event -> onDelete());
    	add.addClickListener(event -> newJurusan());
    	list.asSingleSelect().addValueChangeListener(event -> rowSelected(list.asSingleSelect().getValue()));
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
		list.addColumn(Jurusan::getNamaJurusan).setCaption("Nama Jurusan");
		list.addColumn(Jurusan::getDeskripsi).setCaption("Deskripi");
		
	}
	
	public void populateGrid() {
		list.setItems(service.getAllJurusan());
	}
	
	public void setForm() {
		binder.forField(namaJurusan).bind("namaJurusan");
		binder.forField(deskripsi).bind("deskripsi");
		
	}
	
	public void setUpData(Jurusan jur) {
		currentJur = jur;
		if (currentJur != null) {
            binder.readBean(currentJur);
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
	
	public void edit(Jurusan jur) {
		setUpData(jur);
	}
	
	public void rowSelected(Jurusan jur) {
		edit(jur);
	}
	
	public void onSave() {
		if (binder.writeBeanIfValid(currentJur)) {
			showSaveNotification(currentJur.getNamaJurusan()+ " ("
	                + currentJur.getIdjurusan()+ ") updated");
			service.saveJurusan(currentJur);
			resetForm();
			populateGrid();
		}
	}
	
	public void onDelete() {
		service.deleteMatakuliah(currentJur.getIdjurusan());
		showSaveNotification(currentJur.getNamaJurusan() + " ("
                + currentJur.getIdjurusan()+ ") removed");
		resetForm();
	}
	
	public void onCancel() {
		resetForm();
	}
	
public void newJurusan() {
		resetForm();
		edit(new Jurusan());
	}
	
	public void resetForm() {
		namaJurusan.clear();;
		deskripsi.clear();
		namaJurusan.focus();
		list.getSelectionModel().deselectAll();
	}
	
}
