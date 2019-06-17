package com.example.myavaadin.springview;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.navigator.ViewDisplay;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.myavaadin.MyUI;
import com.example.myavaadin.design.Main;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;

@SpringView(name = "")
@SuppressWarnings("serial")
public class MainView extends Main implements View{
	private static final String STYLE_SELECTED = "selected";
	
	@Autowired
	SpringViewProvider viewProvider;
	
	Panel p;
	
	@Override
	public void enter(ViewChangeEvent event) {
		p = new Panel();
		p.setVisible(false);
		p.setHeightUndefined();
		contentBody.addComponent(p);
		
		try {
			// Setting mahasiswa view
			MahasiswaView v = (MahasiswaView) viewProvider.getView(MahasiswaView.VIEW_NAME);
			viewTitle.setValue(MahasiswaView.VIEW_NAME);
			setView(v);
			v.enter(null);
			} catch (Exception ex) {};
	}
	
	public MainView() {
		mahasiswa.addClickListener(e -> navigateView(MahasiswaView.VIEW_NAME));
		jurusan.addClickListener(e -> navigateView(JurusanView.VIEW_NAME));
		matakuliah.addClickListener(e -> navigateView(MatakuliahView.VIEW_NAME));
		nilai.addClickListener(e -> navigateView(NilaiView.VIEW_NAME));
	}
	
	public void setView(View view) {
		Component c = (Component) view;
		p.setContent(c);
		p.setVisible(true);
		p.setHeight("100%");
		p.addStyleNames("content-section padding");
	}
	
	public void navigateView(String viewName) {
		View v = viewProvider.getView(viewName);
		MainView mv = (MainView) getUI().getContent();
		viewTitle.setValue(viewName);
		mv.setView(v);
		v.enter(null);
	}
	
}
