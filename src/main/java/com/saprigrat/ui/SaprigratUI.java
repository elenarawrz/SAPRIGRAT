package com.saprigrat.ui;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("saprigrat")
@PreserveOnRefresh
public class SaprigratUI extends UI
{
	public static class Servlet extends VaadinServlet
	{
	}

	@Override
	protected void init(VaadinRequest request)
	{
		getPage().setTitle("SAPRIGRAT");
		setContent(new LoginView(this));
	}
}