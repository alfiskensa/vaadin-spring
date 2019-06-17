package com.example.myavaadin.springview;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.myavaadin.design.MahasiswaDetailDesign;
import com.example.myavaadin.model.Mahasiswa;
import com.example.myavaadin.model.Matakuliah;
import com.example.myavaadin.service.DataService;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

@SuppressWarnings("serial")
@SpringView(name = "DetailMahasiswa")
public class MahasiswaDetailView extends MahasiswaDetailDesign implements View{
	
	private final Binder<Mahasiswa> binder = new BeanValidationBinder<>(Mahasiswa.class);
	
	public Matakuliah currentMk;
	
	public Mahasiswa currentMhs;
	
	MahasiswaView mahasiswaV;
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	public void setParam(MahasiswaView m, Mahasiswa mhs) {
		this.mahasiswaV = m;
		this.currentMhs = mhs;
		initialDisplay();
	}
	

	void initialDisplay() {
		tampilanAwal();
		settingListener();
	}
	
	void settingListener() {
		update.addClickListener(event -> onSave());
    	cancel.addClickListener(event -> onCancel());
    	delete.addClickListener(event -> onDelete());
    	back.addClickListener(event -> kembali());
    	list.asSingleSelect().addValueChangeListener(event -> {
    		rowSelected(list.asSingleSelect().getValue());
    		matakuliah.setEnabled(false);
    		delete.setEnabled(true);
    	});
    	matakuliah.addValueChangeListener(event -> {
    		update.setEnabled(true);
    		this.currentMk = matakuliah.getValue();
    	});
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
		list.setItems(this.currentMhs.getMatakuliah());
	}
	
	public void setForm() {
		matakuliah.setItemCaptionGenerator(Matakuliah::getNamaMk);
		matakuliah.setItems(this.mahasiswaV.service.getAllMatakuliah());
		binder.forField(matakuliah).withConverter(new MyConverter()).bind("matakuliah");
		
		
		namaMahasiswa.setValue(currentMhs.getNamaMahasiswa());
		nim.setValue(currentMhs.getNim());
		jurusan.setValue(currentMhs.getJurusan().getNamaJurusan());
	}
	
	public void setUpData() {
		Set<Matakuliah> mat = new HashSet<>();
		mat.add(currentMk);
		Mahasiswa m = new Mahasiswa();
		m.setMatakuliah(mat);
		binder.readBean(m);
	}
	
	public void showError(String msg) {
		Notification.show(msg, Type.ERROR_MESSAGE);
	}

	public void showSaveNotification(String msg) {
        Notification.show(msg, Type.TRAY_NOTIFICATION);
    }
	
	public void rowSelected(Matakuliah mk) {
		this.currentMk = mk;
		setUpData();
	}
	
	public void onSave() {
		showSaveNotification(this.currentMk.getNamaMk() +
				" (" + this.currentMk.getIdMk() + ") updated");

		Set<Matakuliah> mks = new HashSet<>(currentMhs.getMatakuliah());
		mks.add(currentMk);
		currentMhs.setMatakuliah(mks);
		this.mahasiswaV.service.saveMahasiswa(currentMhs);

		resetForm();
		populateGrid();
	}
	
	public void onDelete() {
		Set<Matakuliah> mk = currentMhs.getMatakuliah();
		mk.remove(currentMk);
		currentMhs.setMatakuliah(mk);
		this.mahasiswaV.service.saveMahasiswa(currentMhs);
		showSaveNotification(currentMk.getNamaMk() + " ("
                + currentMk.getIdMk() + ") removed");
		resetForm();
		populateGrid();
	}
	
	public void onCancel() {
		resetForm();
	}
	
	public void kembali() {
		this.mahasiswaV.pnl.setContent(this.mahasiswaV);
	}

	
	public void resetForm() {
		matakuliah.clear();
		matakuliah.setEnabled(true);
		update.setEnabled(false);
		delete.setEnabled(false);
		list.getSelectionModel().deselectAll();
	}
	
	public Set<Matakuliah> convert(Matakuliah mat) {
		Set<Matakuliah> matakuliah = new HashSet<>(Arrays.asList(mat));
		return matakuliah; 
	}
	
}

class MyConverter implements Converter<Matakuliah, Set<Matakuliah>> {

	@Override
	public Result<Set<Matakuliah>> convertToModel(Matakuliah value, ValueContext context) {
		// TODO Auto-generated method stub
		Set <Matakuliah> matakuliah = new HashSet<>(Arrays.asList(value));
		return Result.ok(matakuliah);
	}

	@Override
	public Matakuliah convertToPresentation(Set<Matakuliah> value, ValueContext context) {
		// TODO Auto-generated method stub
		List<Matakuliah> matlist = new ArrayList<>(value);
		Matakuliah m = matlist.get(0);
		return m;
	}
   
}
