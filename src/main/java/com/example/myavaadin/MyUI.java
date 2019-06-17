package com.example.myavaadin;

import javax.servlet.annotation.WebServlet;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.myavaadin.springview.MainView;
import com.google.common.eventbus.EventBus;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@SuppressWarnings("serial")
@Theme("mytheme")
@SpringUI
@SpringViewDisplay
public class MyUI extends UI implements ViewDisplay{
	
	MainView mainview;
	
	private EventBus eventbus;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
       
    }
    
    public static MyUI getCurrent() {
        return (MyUI) UI.getCurrent();
    }

	@Override
	public void showView(View view) {
		// TODO Auto-generated method stub
		setContent((Component) view);
		
	}
	
	public EventBus getEventbus() {
		return eventbus;
	}
	
	public boolean isUserAnonymous() {
        return SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken;
    }


}
