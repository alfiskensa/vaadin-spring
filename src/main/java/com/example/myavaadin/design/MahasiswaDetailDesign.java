package com.example.myavaadin.design;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;

/** 
 * !! DO NOT EDIT THIS FILE !!
 * 
 * This class is generated by Vaadin Designer and will be overwritten.
 * 
 * Please make a subclass with logic and additional interfaces as needed,
 * e.g class LoginView extends LoginDesign implements View { }
 */
@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class MahasiswaDetailDesign extends VerticalLayout {
	protected Button back;
	protected ComboBox<com.example.myavaadin.model.Matakuliah> matakuliah;
	protected HorizontalLayout formButtonLayout;
	protected Button cancel;
	protected Button update;
	protected Button delete;
	protected Grid<com.example.myavaadin.model.Matakuliah> list;
	protected CssLayout formLayout;
	protected TextField nim;
	protected TextField namaMahasiswa;
	protected TextField jurusan;

	public MahasiswaDetailDesign() {
		Design.read(this);
	}
}
