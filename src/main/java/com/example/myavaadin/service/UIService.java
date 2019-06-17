package com.example.myavaadin.service;

import com.example.myavaadin.MyUI;
import com.example.myavaadin.events.NavigationEvent;


public class UIService{
	
	public void postNavigationEvent(Object source, String target) {
		MyUI.getCurrent().getEventbus().post(new NavigationEvent(source, target));
	}

	public boolean isUserAnonymous() {
		return MyUI.getCurrent().isUserAnonymous();
	}

}
