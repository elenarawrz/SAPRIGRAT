package com.saprigrat.ui.components.pruebaderiego;

import java.sql.Date;
import java.util.LinkedList;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class LecturaInicial extends CustomComponent
{
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Panel panConsumo;
	@AutoGenerated
	private AbsoluteLayout absLConsumo;
	@AutoGenerated
	private Label lblConsumoReal;
	@AutoGenerated
	private TextField txtConsumoReal;
	@AutoGenerated
	private Label lblTiempo;
	@AutoGenerated
	private TextField txtTiempo;
	@AutoGenerated
	private Label lblConsumo;
	@AutoGenerated
	private TextField txtConsumo;
	@AutoGenerated
	private TextField txtMultiplicador;
	@AutoGenerated
	private HorizontalLayout horLCFE;
	@AutoGenerated
	private Panel panLectCFE;
	@AutoGenerated
	private AbsoluteLayout absLLectCFE;
	@AutoGenerated
	private Label lblFinCFE;
	@AutoGenerated
	private TextField txtFinCFE;
	@AutoGenerated
	private Label lblIniCFE;
	@AutoGenerated
	private TextField txtIniCFE;
	@AutoGenerated
	private Panel panFecCFE;
	@AutoGenerated
	private AbsoluteLayout absLFecCFE;
	@AutoGenerated
	private DateField datFinCFE;
	@AutoGenerated
	private DateField datIniCFE;
	@AutoGenerated
	private HorizontalLayout horLFlujo;
	@AutoGenerated
	private Panel panLectFlujo;
	@AutoGenerated
	private AbsoluteLayout absLLectFlujo;
	@AutoGenerated
	private Label lblFinFlujo;
	@AutoGenerated
	private TextField txtFinFlujo;
	@AutoGenerated
	private Label lblIniFlujo;
	@AutoGenerated
	private TextField txtIniFlujo;
	@AutoGenerated
	private Panel panFecPrueba;
	@AutoGenerated
	private AbsoluteLayout absLFecPrueba;
	@AutoGenerated
	private DateField datFinPrueba;
	@AutoGenerated
	private DateField datIniPrueba;
	private ValueChangeListener calcularConsumoRealListener=new ValueChangeListener()
															{
																@Override
																public void valueChange(ValueChangeEvent event)
																{
																	if( !txtConsumo.getValue().isEmpty() &&
																		!txtTiempo.getValue().isEmpty() &&
																		txtConsumo.isValid() &&
																		txtTiempo.isValid())
																		txtConsumoReal.setValue((Double.parseDouble(txtConsumo.getValue()) *
																								 Integer.parseInt(txtTiempo.getValue()))
																								 + "");
																	else
																		txtConsumoReal.setValue("0.0");
																}
															};
	public LecturaInicial()
	{
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		setCalendario(datIniPrueba);
		setCalendario(datFinPrueba);
		setCalendario(datIniCFE);
		setCalendario(datFinCFE);
		txtConsumo.addValueChangeListener(calcularConsumoRealListener);
		txtTiempo.addValueChangeListener(calcularConsumoRealListener);
		txtConsumoReal.setValue("0.0");
		txtConsumoReal.setEnabled(false);
		
		setValidaciones();
	}
	
	public LinkedList<Object> getValores()
	{
		LinkedList<Object> lecturaInicial = new LinkedList<Object>();
		if(validar())
		{
			lecturaInicial.add(getFecha(datIniPrueba));
			lecturaInicial.add(getFecha(datFinPrueba));
			lecturaInicial.add(Double.parseDouble(txtIniFlujo.getValue()));
			lecturaInicial.add(Double.parseDouble(txtFinFlujo.getValue()));
			lecturaInicial.add(getFecha(datIniCFE));
			lecturaInicial.add(getFecha(datFinCFE));
			lecturaInicial.add(Double.parseDouble(txtIniCFE.getValue()));
			lecturaInicial.add(Double.parseDouble(txtFinCFE.getValue()));
			lecturaInicial.add(Double.parseDouble(txtMultiplicador.getValue()));
			lecturaInicial.add(Double.parseDouble(txtConsumo.getValue()));
			lecturaInicial.add(Integer.parseInt(txtTiempo.getValue()));
		}
		return lecturaInicial;
	}
	
	public void setValores(LinkedList<Object> lecturaInicial)
	{
		datIniPrueba.setValue(setFecha((Date)lecturaInicial.remove()));
		datFinPrueba.setValue(setFecha((Date)lecturaInicial.remove()));
		txtIniFlujo.setValue((String)lecturaInicial.remove());
		txtFinFlujo.setValue((String)lecturaInicial.remove());
		datIniCFE.setValue(setFecha((Date)lecturaInicial.remove()));
		datFinCFE.setValue(setFecha((Date)lecturaInicial.remove()));
		txtIniCFE.setValue((String)lecturaInicial.remove());
		txtFinCFE.setValue((String)lecturaInicial.remove());
		txtMultiplicador.setValue((String)lecturaInicial.remove());
		txtConsumo.setValue((String)lecturaInicial.remove());
		txtTiempo.setValue((String)lecturaInicial.remove());
		txtConsumoReal.setValue((String)lecturaInicial.remove());
	}

	private void setValidaciones()
	{
		RegexpValidator esDecimal = new RegexpValidator("^[0-9]+(\\.[0-9]+)?$", "Debe ser un valor numérico"),
						esEntero = new RegexpValidator("^[0-9]+$", "Debe ser un valor numérico entero");
		txtIniFlujo.addValidator(esDecimal);
		txtFinFlujo.addValidator(esDecimal);
		txtIniCFE.addValidator(esDecimal);
		txtFinCFE.addValidator(esDecimal);
		txtMultiplicador.addValidator(esDecimal);
		txtConsumo.addValidator(esDecimal);
		txtTiempo.addValidator(esEntero);
	}
	
	private boolean validar()
	{
		if(txtIniFlujo.getValue().isEmpty() || !txtIniFlujo.isValid())
			return notificar("Se requiere especificar un valor estríctamente numérico para la lectura inicial del medidor de flujo", txtIniFlujo);
		if(txtFinFlujo.getValue().isEmpty() || !txtFinFlujo.isValid())
			return notificar("Se requiere especificar un valor estríctamente numérico para la lectura final del medidor de flujo", txtFinFlujo);
		if(txtIniCFE.getValue().isEmpty() || !txtIniCFE.isValid())
			return notificar("Se requiere especificar un valor estríctamente numérico para la lectura inicial del medidor de CFE", txtIniCFE);
		if(txtFinCFE.getValue().isEmpty() || !txtFinCFE.isValid())
			return notificar("Se requiere especificar un valor estríctamente numérico para la lectura final del medidor de CFE", txtFinCFE);
		if(txtMultiplicador.getValue().isEmpty() || !txtMultiplicador.isValid())
			return notificar("Se requiere especificar un valor estríctamente numérico para el multiplicador", txtMultiplicador);
		if(txtConsumo.getValue().isEmpty() || !txtConsumo.isValid())
			return notificar("Se requiere especificar un valor estríctamente numérico para el consumo", txtConsumo);
		if(txtTiempo.getValue().isEmpty() || !txtTiempo.isValid())
			return notificar("Se requiere especificar un valor numérico estríctamente entero para el tiempo", txtTiempo);
		
		return true;
	}
	
	@SuppressWarnings("rawtypes")
	private boolean notificar(String notificacion, AbstractField field)
	{
		Notification.show(notificacion, Notification.Type.ERROR_MESSAGE);
		field.focus();
		return false;
	}
	
	private void setCalendario(DateField cal)
	{
		cal.setResolution(Resolution.MINUTE);
		cal.setValue(new java.util.Date());
		cal.setDateFormat("dd - MMM - yyyy, hh:mm a");
	}
	
	private Date getFecha(DateField calendario)
	{
		if(calendario.getValue() == null)
			return null;
		else
			return new Date(calendario.getValue().getTime());
	}
	
	private java.util.Date setFecha(Date fecha)
	{
		if(fecha == null)
			return null;
		else
			return new java.util.Date(fecha.getTime());
	}
	//region UI

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("581px");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("581px");
		setHeight("-1px");
		
		// horLFlujo
		horLFlujo = buildHorLFlujo();
		mainLayout.addComponent(horLFlujo);
		
		// horLCFE
		horLCFE = buildHorLCFE();
		mainLayout.addComponent(horLCFE);
		
		// panConsumo
		panConsumo = buildPanConsumo();
		mainLayout.addComponent(panConsumo);
		
		return mainLayout;
	}

	@AutoGenerated
	private HorizontalLayout buildHorLFlujo() {
		// common part: create layout
		horLFlujo = new HorizontalLayout();
		horLFlujo.setImmediate(false);
		horLFlujo.setWidth("100.0%");
		horLFlujo.setHeight("-1px");
		horLFlujo.setMargin(false);
		
		// panFecPrueba
		panFecPrueba = buildPanFecPrueba();
		horLFlujo.addComponent(panFecPrueba);
		
		// panLectFlujo
		panLectFlujo = buildPanLectFlujo();
		horLFlujo.addComponent(panLectFlujo);
		horLFlujo.setComponentAlignment(panLectFlujo, new Alignment(6));
		
		return horLFlujo;
	}

	@AutoGenerated
	private Panel buildPanFecPrueba() {
		// common part: create layout
		panFecPrueba = new Panel();
		panFecPrueba.setCaption("Fecha de la Prueba");
		panFecPrueba.setImmediate(false);
		panFecPrueba.setWidth("271px");
		panFecPrueba.setHeight("-1px");
		
		// absLFecPrueba
		absLFecPrueba = buildAbsLFecPrueba();
		panFecPrueba.setContent(absLFecPrueba);
		
		return panFecPrueba;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLFecPrueba() {
		// common part: create layout
		absLFecPrueba = new AbsoluteLayout();
		absLFecPrueba.setImmediate(false);
		absLFecPrueba.setWidth("100.0%");
		absLFecPrueba.setHeight("110px");
		
		// datIniPrueba
		datIniPrueba = new DateField();
		datIniPrueba.setCaption("Inicial");
		datIniPrueba.setImmediate(false);
		datIniPrueba.setWidth("250px");
		datIniPrueba.setHeight("25px");
		absLFecPrueba.addComponent(datIniPrueba, "top:28.0px;left:10.0px;");
		
		// datFinPrueba
		datFinPrueba = new DateField();
		datFinPrueba.setCaption("Final");
		datFinPrueba.setImmediate(false);
		datFinPrueba.setWidth("250px");
		datFinPrueba.setHeight("25px");
		absLFecPrueba.addComponent(datFinPrueba, "top:73.0px;left:10.0px;");
		
		return absLFecPrueba;
	}

	@AutoGenerated
	private Panel buildPanLectFlujo() {
		// common part: create layout
		panLectFlujo = new Panel();
		panLectFlujo.setCaption("Lecturas del Medidor de Flujo");
		panLectFlujo.setImmediate(false);
		panLectFlujo.setWidth("231px");
		panLectFlujo.setHeight("-1px");
		
		// absLLectFlujo
		absLLectFlujo = buildAbsLLectFlujo();
		panLectFlujo.setContent(absLLectFlujo);
		
		return panLectFlujo;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLLectFlujo() {
		// common part: create layout
		absLLectFlujo = new AbsoluteLayout();
		absLLectFlujo.setImmediate(false);
		absLLectFlujo.setWidth("100.0%");
		absLLectFlujo.setHeight("110px");
		
		// txtIniFlujo
		txtIniFlujo = new TextField();
		txtIniFlujo.setCaption("Inicial");
		txtIniFlujo.setImmediate(false);
		txtIniFlujo.setWidth("175px");
		txtIniFlujo.setHeight("25px");
		absLLectFlujo.addComponent(txtIniFlujo, "top:28.0px;left:10.0px;");
		
		// lblIniFlujo
		lblIniFlujo = new Label();
		lblIniFlujo.setImmediate(false);
		lblIniFlujo.setWidth("-1px");
		lblIniFlujo.setHeight("-1px");
		lblIniFlujo.setValue("L/s");
		absLLectFlujo.addComponent(lblIniFlujo, "top:32.0px;left:190.0px;");
		
		// txtFinFlujo
		txtFinFlujo = new TextField();
		txtFinFlujo.setCaption("Final");
		txtFinFlujo.setImmediate(false);
		txtFinFlujo.setWidth("175px");
		txtFinFlujo.setHeight("25px");
		absLLectFlujo.addComponent(txtFinFlujo, "top:73.0px;left:10.0px;");
		
		// lblFinFlujo
		lblFinFlujo = new Label();
		lblFinFlujo.setImmediate(false);
		lblFinFlujo.setWidth("-1px");
		lblFinFlujo.setHeight("-1px");
		lblFinFlujo.setValue("L/s");
		absLLectFlujo.addComponent(lblFinFlujo, "top:77.0px;left:190.0px;");
		
		return absLLectFlujo;
	}

	@AutoGenerated
	private HorizontalLayout buildHorLCFE() {
		// common part: create layout
		horLCFE = new HorizontalLayout();
		horLCFE.setImmediate(false);
		horLCFE.setWidth("100.0%");
		horLCFE.setHeight("-1px");
		horLCFE.setMargin(false);
		
		// panFecCFE
		panFecCFE = buildPanFecCFE();
		horLCFE.addComponent(panFecCFE);
		
		// panLectCFE
		panLectCFE = buildPanLectCFE();
		horLCFE.addComponent(panLectCFE);
		horLCFE.setComponentAlignment(panLectCFE, new Alignment(6));
		
		return horLCFE;
	}

	@AutoGenerated
	private Panel buildPanFecCFE() {
		// common part: create layout
		panFecCFE = new Panel();
		panFecCFE.setCaption("Fecha de Lectura del Medidor CFE");
		panFecCFE.setImmediate(false);
		panFecCFE.setWidth("271px");
		panFecCFE.setHeight("-1px");
		
		// absLFecCFE
		absLFecCFE = buildAbsLFecCFE();
		panFecCFE.setContent(absLFecCFE);
		
		return panFecCFE;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLFecCFE() {
		// common part: create layout
		absLFecCFE = new AbsoluteLayout();
		absLFecCFE.setImmediate(false);
		absLFecCFE.setWidth("100.0%");
		absLFecCFE.setHeight("110px");
		
		// datIniCFE
		datIniCFE = new DateField();
		datIniCFE.setCaption("Inicial");
		datIniCFE.setImmediate(false);
		datIniCFE.setWidth("250px");
		datIniCFE.setHeight("25px");
		absLFecCFE.addComponent(datIniCFE, "top:28.0px;left:10.0px;");
		
		// datFinCFE
		datFinCFE = new DateField();
		datFinCFE.setCaption("Final");
		datFinCFE.setImmediate(false);
		datFinCFE.setWidth("250px");
		datFinCFE.setHeight("25px");
		absLFecCFE.addComponent(datFinCFE, "top:73.0px;left:10.0px;");
		
		return absLFecCFE;
	}

	@AutoGenerated
	private Panel buildPanLectCFE() {
		// common part: create layout
		panLectCFE = new Panel();
		panLectCFE.setCaption("Lecturas del Medidor de CFE");
		panLectCFE.setImmediate(false);
		panLectCFE.setWidth("231px");
		panLectCFE.setHeight("-1px");
		
		// absLLectCFE
		absLLectCFE = buildAbsLLectCFE();
		panLectCFE.setContent(absLLectCFE);
		
		return panLectCFE;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLLectCFE() {
		// common part: create layout
		absLLectCFE = new AbsoluteLayout();
		absLLectCFE.setImmediate(false);
		absLLectCFE.setWidth("100.0%");
		absLLectCFE.setHeight("110px");
		
		// txtIniCFE
		txtIniCFE = new TextField();
		txtIniCFE.setCaption("Inicial");
		txtIniCFE.setImmediate(false);
		txtIniCFE.setWidth("175px");
		txtIniCFE.setHeight("25px");
		absLLectCFE.addComponent(txtIniCFE, "top:28.0px;left:10.0px;");
		
		// lblIniCFE
		lblIniCFE = new Label();
		lblIniCFE.setImmediate(false);
		lblIniCFE.setWidth("-1px");
		lblIniCFE.setHeight("-1px");
		lblIniCFE.setValue("kW.h");
		absLLectCFE.addComponent(lblIniCFE, "top:32.0px;left:190.0px;");
		
		// txtFinCFE
		txtFinCFE = new TextField();
		txtFinCFE.setCaption("Final");
		txtFinCFE.setImmediate(false);
		txtFinCFE.setWidth("175px");
		txtFinCFE.setHeight("25px");
		absLLectCFE.addComponent(txtFinCFE, "top:73.0px;left:10.0px;");
		
		// lblFinCFE
		lblFinCFE = new Label();
		lblFinCFE.setImmediate(false);
		lblFinCFE.setWidth("-1px");
		lblFinCFE.setHeight("-1px");
		lblFinCFE.setValue("kW.h");
		absLLectCFE.addComponent(lblFinCFE, "top:77.0px;left:190.0px;");
		
		return absLLectCFE;
	}

	@AutoGenerated
	private Panel buildPanConsumo() {
		// common part: create layout
		panConsumo = new Panel();
		panConsumo.setCaption("Consumo Real kW.h");
		panConsumo.setImmediate(false);
		panConsumo.setWidth("100.0%");
		panConsumo.setHeight("-1px");
		
		// absLConsumo
		absLConsumo = buildAbsLConsumo();
		panConsumo.setContent(absLConsumo);
		
		return panConsumo;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLConsumo() {
		// common part: create layout
		absLConsumo = new AbsoluteLayout();
		absLConsumo.setImmediate(false);
		absLConsumo.setWidth("100.0%");
		absLConsumo.setHeight("65px");
		
		// txtMultiplicador
		txtMultiplicador = new TextField();
		txtMultiplicador.setCaption("Multiplicador");
		txtMultiplicador.setImmediate(false);
		txtMultiplicador.setWidth("90px");
		txtMultiplicador.setHeight("25px");
		absLConsumo.addComponent(txtMultiplicador, "top:28.0px;left:10.0px;");
		
		// txtConsumo
		txtConsumo = new TextField();
		txtConsumo.setCaption("Consumo");
		txtConsumo.setImmediate(true);
		txtConsumo.setWidth("110px");
		txtConsumo.setHeight("25px");
		absLConsumo.addComponent(txtConsumo, "top:28.0px;left:120.0px;");
		
		// lblConsumo
		lblConsumo = new Label();
		lblConsumo.setImmediate(false);
		lblConsumo.setWidth("-1px");
		lblConsumo.setHeight("-1px");
		lblConsumo.setValue("kW");
		absLConsumo.addComponent(lblConsumo, "top:32.0px;left:235.0px;");
		
		// txtTiempo
		txtTiempo = new TextField();
		txtTiempo.setCaption("Tiempo");
		txtTiempo.setImmediate(true);
		txtTiempo.setWidth("90px");
		txtTiempo.setHeight("25px");
		absLConsumo.addComponent(txtTiempo, "top:28.0px;left:270.0px;");
		
		// lblTiempo
		lblTiempo = new Label();
		lblTiempo.setImmediate(false);
		lblTiempo.setWidth("-1px");
		lblTiempo.setHeight("-1px");
		lblTiempo.setValue("h");
		absLConsumo.addComponent(lblTiempo, "top:32.0px;left:365.0px;");
		
		// txtConsumoReal
		txtConsumoReal = new TextField();
		txtConsumoReal.setCaption("Consumo Real");
		txtConsumoReal.setImmediate(false);
		txtConsumoReal.setWidth("110px");
		txtConsumoReal.setHeight("25px");
		absLConsumo.addComponent(txtConsumoReal, "top:28.0px;left:390.0px;");
		
		// lblConsumoReal
		lblConsumoReal = new Label();
		lblConsumoReal.setImmediate(false);
		lblConsumoReal.setWidth("-1px");
		lblConsumoReal.setHeight("-1px");
		lblConsumoReal.setValue("kW.h");
		absLConsumo.addComponent(lblConsumoReal, "top:32.0px;left:505.0px;");
		
		return absLConsumo;
	}
	
	//endregion
}