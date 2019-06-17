package com.example.myavaadin.springview;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.myavaadin.MyUI;
import com.vaadin.navigator.View;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;


public abstract class AbstractView extends Panel implements View {

	private Logger LOG = LoggerFactory.getLogger(AbstractView.class);

	private VerticalLayout layout;

	public AbstractView() {
		setSizeFull();
		layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		setContent(layout);
	}

	public void addComponent(Component c) {
		layout.addComponent(c);
	}

	protected void registerWithEventbus() {
		MyUI.getCurrent().getEventbus().register(this);
	}

	@PreDestroy
	public void destroy() {
		LOG.debug("About to destroy {}", getClass().getName());
		MyUI.getCurrent().getEventbus().unregister(this);
	}

}
