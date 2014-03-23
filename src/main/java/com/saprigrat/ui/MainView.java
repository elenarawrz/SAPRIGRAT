package com.saprigrat.ui;

import com.saprigrat.data.Usuario;
import com.saprigrat.ui.forms.PruebaRiego;
import com.saprigrat.ui.forms.ReporteVisita;
import com.saprigrat.ui.forms.nivelesDeAdministracion.Productor;
import com.saprigrat.ui.forms.nivelesDeAdministracion.Tecnico;
import com.saprigrat.ui.forms.parcelas.Aprovechamiento;
import com.saprigrat.ui.forms.parcelas.Parcela;
import com.saprigrat.ui.forms.parcelas.Regador;
import com.saprigrat.ui.forms.parcelas.Riego;
import com.saprigrat.ui.interfaces.Formulario;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MainView extends CustomComponent
{
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Panel panBody;
	@AutoGenerated
	private HorizontalLayout horLBody;
	@AutoGenerated
	private AbsoluteLayout absLOpcionSel;
	@AutoGenerated
	private Label lblOpcionSel;
	@AutoGenerated
	private Panel panHead;
	@AutoGenerated
	private AbsoluteLayout absLHead;
	@AutoGenerated
	private MenuBar mbrMenu;
	@AutoGenerated
	private Embedded imgUsprottia;
	@AutoGenerated
	private Embedded imgUach;
	@AutoGenerated
	private Embedded imgConagua;
	@AutoGenerated
	private Embedded imgHeader;
	private Usuario usuario;
	
	public MainView(Usuario user)
	{
		buildMainLayout();
		setCompositionRoot(mainLayout);
		buildBtnLogout();
		
		usuario = user;
		imgHeader.addClickListener(	new ClickListener()
									{
										@Override
										public void click(ClickEvent event)
										{
											horLBody.removeAllComponents();
											lblOpcionSel.setValue("Inicio");
										}
									});
		setMenu();
	}
	private void buildBtnLogout()
	{
		// btnLogout
		Embedded btnLogout = new Embedded();
		btnLogout.setImmediate(false);
		btnLogout.setWidth("-1px");
		btnLogout.setHeight("16px");
		btnLogout.setSource(new ThemeResource("img/logoutB.png"));
		btnLogout.setType(1);
		btnLogout.setMimeType("image/png");
		absLHead.addComponent(btnLogout, "bottom:3.0px;right:5.0px;");
		
		btnLogout.addClickListener( new ClickListener()
									{
										@Override
										public void click(ClickEvent event)
										{
											usuario = null;
											mainLayout.removeAllComponents();
											mainLayout.addComponent(new LoginView());
										}
									});
	}
	
	private void setMenu()
	{
		MenuItem menu = mbrMenu.addItem("Niveles de Administración", null);
		if(usuario.getTipo() == usuario.ADMINISTRADOR)
		{
			menu.addItem("Administrador", com(new Tecnico(usuario.ADMINISTRADOR, usuario)));
			menu.addItem("Supervisor Técnico", com(new Tecnico(usuario.SUPERVISOR, usuario)));
		}
		if(usuario.getTipo() <= usuario.SUPERVISOR)
			menu.addItem("Responsable Técnico", com(new Tecnico(usuario.RESPONSABLE, usuario)));
		menu.addItem("Usuario/Productor", com(new Productor(usuario)));
		
		menu = mbrMenu.addItem("Parcelas", null);
		menu.addItem("Aprovechamiento", com(new Aprovechamiento(usuario)));
		menu.addItem("Parcela", com(new Parcela(usuario)));
		
		menu = mbrMenu.addItem("Riegos", null);
		menu.addItem("Riego", com(new Riego(usuario)));
		menu.addItem("Regador", com(new Regador(usuario)));
		
//		mbrMenu.addItem("Reporte de Visita", com(new ReporteVisita()));
//		mbrMenu.addItem("Pozo", com(new Pozo()));
//		mbrMenu.addItem("Prueba de Riego", com(new PruebaRiego(usuario)));
//		mbrMenu.addItem("Reportes", null);
	}
	
	Command com(final Formulario form)
	{
		return  new Command()
				{
					@Override
					public void menuSelected(MenuItem selectedItem)
					{
						setPath(selectedItem);
						form.inicializar();
						addComponente(form);
					}
	
					private void setPath(MenuItem selectedItem)
					{
						//Obtener elementos del path
						String path = "";
						for (MenuItem parent = selectedItem.getParent(); parent!=null; parent = parent.getParent())
							path = parent.getText() + "  >  " + path;
						//Establecer path
						lblOpcionSel.setValue(path + selectedItem.getText());
					}
	
					private void addComponente(Component form)
					{
						//Vaciar el contenido del panel principal
						horLBody.removeAllComponents();
						//Agregar nuevo contenido
						horLBody.addComponent(form);
						horLBody.setComponentAlignment(form, new Alignment(20));
					}
				};
	}
	//region UI

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("701px");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("701px");
		setHeight("-1px");
		
		// panHead
		panHead = buildPanHead();
		mainLayout.addComponent(panHead);
		mainLayout.setComponentAlignment(panHead, new Alignment(20));
		
		// absLOpcionSel
		absLOpcionSel = buildAbsLOpcionSel();
		mainLayout.addComponent(absLOpcionSel);
		mainLayout.setComponentAlignment(absLOpcionSel, new Alignment(48));
		
		// panBody
		panBody = buildPanBody();
		mainLayout.addComponent(panBody);
		mainLayout.setComponentAlignment(panBody, new Alignment(20));
		
		return mainLayout;
	}
	@AutoGenerated
	private Panel buildPanHead() {
		// common part: create layout
		panHead = new Panel();
		panHead.setImmediate(false);
		panHead.setWidth("700px");
		panHead.setHeight("-1px");
		
		// absLHead
		absLHead = buildAbsLHead();
		panHead.setContent(absLHead);
		
		return panHead;
	}
	@AutoGenerated
	private AbsoluteLayout buildAbsLHead() {
		// common part: create layout
		absLHead = new AbsoluteLayout();
		absLHead.setImmediate(false);
		absLHead.setWidth("100.0%");
		absLHead.setHeight("140px");
		
		// imgHeader
		imgHeader = new Embedded();
		imgHeader.setImmediate(false);
		imgHeader.setWidth("-1px");
		imgHeader.setHeight("80px");
		imgHeader.setSource(new ThemeResource("img/header.png"));
		imgHeader.setType(1);
		imgHeader.setMimeType("image/png");
		absLHead.addComponent(imgHeader, "top:20.0px;left:20.0px;");
		
		// imgConagua
		imgConagua = new Embedded();
		imgConagua.setImmediate(false);
		imgConagua.setDescription("Comisión Nacional del Agua");
		imgConagua.setWidth("-1px");
		imgConagua.setHeight("30px");
		imgConagua.setSource(new ThemeResource("img/logos/conagua.png"));
		imgConagua.setType(1);
		imgConagua.setMimeType("image/png");
		absLHead.addComponent(imgConagua, "top:20.0px;right:20.0px;");
		
		// imgUach
		imgUach = new Embedded();
		imgUach.setImmediate(false);
		imgUach.setDescription("Universidad Autónoma Chapingo");
		imgUach.setWidth("-1px");
		imgUach.setHeight("30px");
		imgUach.setSource(new ThemeResource("img/logos/uach.png"));
		imgUach.setType(1);
		imgUach.setMimeType("image/png");
		absLHead.addComponent(imgUach, "top:65.0px;right:110.0px;");
		
		// imgUsprottia
		imgUsprottia = new Embedded();
		imgUsprottia.setImmediate(false);
		imgUsprottia.setDescription("Unidad de Servicios Profesionales y Transferencia de Tecnología en Ingeniería Agrícola");
		imgUsprottia.setWidth("-1px");
		imgUsprottia.setHeight("30px");
		imgUsprottia.setSource(new ThemeResource("img/logos/usprottia.png"));
		imgUsprottia.setType(1);
		imgUsprottia.setMimeType("image/png");
		absLHead.addComponent(imgUsprottia, "top:65.0px;right:20.0px;");
		
		// mbrMenu
		mbrMenu = new MenuBar();
		mbrMenu.setImmediate(false);
		mbrMenu.setWidth("800px");
		mbrMenu.setHeight("-1px");
		absLHead.addComponent(mbrMenu, "bottom:0.0px;");
		
		return absLHead;
	}
	@AutoGenerated
	private AbsoluteLayout buildAbsLOpcionSel() {
		// common part: create layout
		absLOpcionSel = new AbsoluteLayout();
		absLOpcionSel.setImmediate(false);
		absLOpcionSel.setWidth("700px");
		absLOpcionSel.setHeight("22px");
		
		// lblOpcionSel
		lblOpcionSel = new Label();
		lblOpcionSel.setStyleName("lblOpcionSel");
		lblOpcionSel.setImmediate(false);
		lblOpcionSel.setWidth("-1px");
		lblOpcionSel.setHeight("-1px");
		lblOpcionSel.setValue("Inicio");
		absLOpcionSel.addComponent(lblOpcionSel, "top:3.0px;right:15.0px;");
		
		return absLOpcionSel;
	}
	@AutoGenerated
	private Panel buildPanBody() {
		// common part: create layout
		panBody = new Panel();
		panBody.setImmediate(false);
		panBody.setWidth("700px");
		panBody.setHeight("-1px");
		
		// horLBody
		horLBody = new HorizontalLayout();
		horLBody.setStyleName("contenido");
		horLBody.setImmediate(false);
		horLBody.setWidth("100.0%");
		horLBody.setHeight("480px");
		horLBody.setMargin(false);
		panBody.setContent(horLBody);
		
		return panBody;
	}

	//endregion
}
