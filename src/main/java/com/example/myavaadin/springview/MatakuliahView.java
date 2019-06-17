package com.example.myavaadin.springview;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.myavaadin.design.MatakuliahDesign;
import com.example.myavaadin.model.Jurusan;
import com.example.myavaadin.model.Mahasiswa;
import com.example.myavaadin.model.Matakuliah;
import com.example.myavaadin.service.DataService;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.LocalDateToDateConverter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

@SuppressWarnings("serial")
@SpringView(name = MatakuliahView.VIEW_NAME)
public class MatakuliahView extends MatakuliahDesign implements View {
	public static final String VIEW_NAME = "Matakuliah";
	
private final Binder<Matakuliah> binder = new BeanValidationBinder<>(Matakuliah.class);
	
	@Autowired
	DataService service;
	
	public Matakuliah currentMk;
	
	@PostConstruct
	void init() {
		update.addClickListener(event -> onSave());
    	cancel.addClickListener(event -> onCancel());
    	delete.addClickListener(event -> onDelete());
    	add.addClickListener(event -> newMatakuliah());
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
		list.addColumn(Matakuliah::getNamaMk).setCaption("Nama Matakuliah");
		list.addColumn(Matakuliah::getSks).setCaption("SKS");
		
	}
	
	public void populateGrid() {
		list.setItems(service.getAllMatakuliah());
	}
	
	public void setForm() {
		binder.forField(namaMk).bind("namaMk");
		sks.setItems(Arrays.asList(1,2,3,4,5));
    	binder.forField(sks).bind("sks");
	}
	
	public void setUpData(Matakuliah mk) {
		currentMk = mk;
		if (currentMk != null) {
            binder.readBean(currentMk);
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
	
	public void edit(Matakuliah mk) {
		setUpData(mk);
	}
	
	public void rowSelected(Matakuliah mk) {
		edit(mk);
	}
	
	public void onSave() {
		if (binder.writeBeanIfValid(currentMk)) {
			showSaveNotification(currentMk.getNamaMk() + " ("
	                + currentMk.getIdMk()+ ") updated");
			service.saveMatakuliah(currentMk);
			resetForm();
			populateGrid();
		}
	}
	
	public void onDelete() {
		service.deleteMatakuliah(currentMk.getIdMk());
		showSaveNotification(currentMk.getNamaMk() + " ("
                + currentMk.getIdMk() + ") removed");
		resetForm();
	}
	
	public void onCancel() {
		resetForm();
	}
	
public void newMatakuliah() {
		resetForm();
		edit(new Matakuliah());
	}
	
	public void resetForm() {
		namaMk.clear();
		sks.clear();
		namaMk.focus();
		list.getSelectionModel().deselectAll();
	}
	
}
