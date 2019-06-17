package com.example.myavaadin.springview;

import com.example.myavaadin.design.LoginDesign;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;

@SpringView(name = LoginView.VIEW_NAME)
public class LoginView extends LoginDesign implements View{
	public static final String VIEW_NAME = "login";
	
	 public static String loginPathForRequestedView(String requestedViewName) {
	        return VIEW_NAME + "/" + requestedViewName;
	    }
}
