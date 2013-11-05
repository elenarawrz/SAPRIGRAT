package com.saprigrat.ui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("saprigrat")
public class SaprigratUI extends UI
{

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = SaprigratUI.class, widgetset = "com.saprigrat.ui.widgetset.SaprigratWidgetset")
	public static class Servlet extends VaadinServlet
	{
	}

	@Override
	protected void init(VaadinRequest request)
	{
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		/*setContent(layout);

		
		
		MenuBar menu = new MenuBar();
		menu.addItem("Supervisores", null);
		layout.addComponent(menu);
		
		supervisorInfo supInf = new supervisorInfo();
		layout.addComponent(supInf);*/
		setContent(new supervisorInfo());
	}

}