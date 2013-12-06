package com.saprigrat.ui.forms.pruebaderiego;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class Aforo extends CustomComponent
{
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private AbsoluteLayout absLLecturaFlujo;
	@AutoGenerated
	private Panel panSalida;
	@AutoGenerated
	private AbsoluteLayout absSalida;
	@AutoGenerated
	private Label lblSalida;
	@AutoGenerated
	private TextField txtSalida;
	@AutoGenerated
	private Panel panRegadera;
	@AutoGenerated
	private AbsoluteLayout absLRegadera;
	@AutoGenerated
	private Label lblRegadera;
	@AutoGenerated
	private TextField txtRegadera;
	@AutoGenerated
	private Panel panParcelas;
	@AutoGenerated
	private AbsoluteLayout absLParcelas;
	@AutoGenerated
	private Label lblGastoSurco;
	@AutoGenerated
	private TextField txtGastoSurco;
	@AutoGenerated
	private TextField txtNoSurcos;
	@AutoGenerated
	private Label lblVolAplicado;
	@AutoGenerated
	private TextField txtVolAplicado;
	@AutoGenerated
	private Label lblGastoComp;
	@AutoGenerated
	private TextField txtGastoComp;
	@AutoGenerated
	private TextField txtNoComp;
	@AutoGenerated
	private Table tblAforo;
	public Aforo()
	{
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		tblAforo.addContainerProperty("Surco", Integer.class, null);
		tblAforo.addContainerProperty("Tiempo (s)", Float.class, null);
		tblAforo.addContainerProperty("Volumen (l)", Float.class, null);
		tblAforo.addContainerProperty("Gasto (l/s)", Float.class, null);
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("-1px");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("-1px");
		setHeight("-1px");
		
		// panParcelas
		panParcelas = buildPanParcelas();
		mainLayout.addComponent(panParcelas);
		
		// absLLecturaFlujo
		absLLecturaFlujo = buildAbsLLecturaFlujo();
		mainLayout.addComponent(absLLecturaFlujo);
		
		return mainLayout;
	}

	@AutoGenerated
	private Panel buildPanParcelas() {
		// common part: create layout
		panParcelas = new Panel();
		panParcelas.setCaption("Aforo en las Parcelas Demostrativas");
		panParcelas.setImmediate(false);
		panParcelas.setWidth("501px");
		panParcelas.setHeight("-1px");
		
		// absLParcelas
		absLParcelas = buildAbsLParcelas();
		panParcelas.setContent(absLParcelas);
		
		return panParcelas;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLParcelas() {
		// common part: create layout
		absLParcelas = new AbsoluteLayout();
		absLParcelas.setImmediate(false);
		absLParcelas.setWidth("100.0%");
		absLParcelas.setHeight("233px");
		
		// tblAforo
		tblAforo = new Table();
		tblAforo.setImmediate(false);
		tblAforo.setWidth("480px");
		tblAforo.setHeight("123px");
		absLParcelas.addComponent(tblAforo, "top:10.0px;left:10.0px;");
		
		// txtNoComp
		txtNoComp = new TextField();
		txtNoComp.setCaption("N�m. de Compuertas");
		txtNoComp.setImmediate(false);
		txtNoComp.setWidth("120px");
		txtNoComp.setHeight("25px");
		absLParcelas.addComponent(txtNoComp, "top:153.0px;left:10.0px;");
		
		// txtGastoComp
		txtGastoComp = new TextField();
		txtGastoComp.setCaption("Gasto Prom. por Compuerta");
		txtGastoComp.setImmediate(false);
		txtGastoComp.setWidth("150px");
		txtGastoComp.setHeight("25px");
		absLParcelas.addComponent(txtGastoComp, "top:153.0px;left:150.0px;");
		
		// lblGastoComp
		lblGastoComp = new Label();
		lblGastoComp.setImmediate(false);
		lblGastoComp.setWidth("-1px");
		lblGastoComp.setHeight("-1px");
		lblGastoComp.setValue("l/s");
		absLParcelas.addComponent(lblGastoComp, "top:157.0px;left:305.0px;");
		
		// txtVolAplicado
		txtVolAplicado = new TextField();
		txtVolAplicado.setCaption("Volumen Total Aplicado");
		txtVolAplicado.setImmediate(false);
		txtVolAplicado.setWidth("130px");
		txtVolAplicado.setHeight("25px");
		absLParcelas.addComponent(txtVolAplicado, "top:153.0px;left:340.0px;");
		
		// lblVolAplicado
		lblVolAplicado = new Label();
		lblVolAplicado.setImmediate(false);
		lblVolAplicado.setWidth("-1px");
		lblVolAplicado.setHeight("-1px");
		lblVolAplicado.setValue("m�");
		absLParcelas.addComponent(lblVolAplicado, "top:157.0px;left:475.0px;");
		
		// txtNoSurcos
		txtNoSurcos = new TextField();
		txtNoSurcos.setCaption("N�m. de Surcos");
		txtNoSurcos.setImmediate(false);
		txtNoSurcos.setWidth("120px");
		txtNoSurcos.setHeight("25px");
		absLParcelas.addComponent(txtNoSurcos, "top:198.0px;left:10.0px;");
		
		// txtGastoSurco
		txtGastoSurco = new TextField();
		txtGastoSurco.setCaption("Gasto Prom. por Surco o Melga");
		txtGastoSurco.setImmediate(false);
		txtGastoSurco.setWidth("150px");
		txtGastoSurco.setHeight("25px");
		absLParcelas.addComponent(txtGastoSurco, "top:198.0px;left:150.0px;");
		
		// lblGastoSurco
		lblGastoSurco = new Label();
		lblGastoSurco.setImmediate(false);
		lblGastoSurco.setWidth("-1px");
		lblGastoSurco.setHeight("-1px");
		lblGastoSurco.setValue("l/s");
		absLParcelas.addComponent(lblGastoSurco, "top:201.0px;left:305.0px;");
		
		return absLParcelas;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLLecturaFlujo() {
		// common part: create layout
		absLLecturaFlujo = new AbsoluteLayout();
		absLLecturaFlujo.setImmediate(false);
		absLLecturaFlujo.setWidth("100.0%");
		absLLecturaFlujo.setHeight("66px");
		
		// panRegadera
		panRegadera = buildPanRegadera();
		absLLecturaFlujo.addComponent(panRegadera, "top:0.0px;left:0.0px;");
		
		// panSalida
		panSalida = buildPanSalida();
		absLLecturaFlujo.addComponent(panSalida, "top:0.0px;right:0.0px;");
		
		return absLLecturaFlujo;
	}

	@AutoGenerated
	private Panel buildPanRegadera() {
		// common part: create layout
		panRegadera = new Panel();
		panRegadera.setCaption("Aforo en la Regadera o Canal");
		panRegadera.setImmediate(false);
		panRegadera.setWidth("245px");
		panRegadera.setHeight("-1px");
		
		// absLRegadera
		absLRegadera = buildAbsLRegadera();
		panRegadera.setContent(absLRegadera);
		
		return panRegadera;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLRegadera() {
		// common part: create layout
		absLRegadera = new AbsoluteLayout();
		absLRegadera.setImmediate(false);
		absLRegadera.setWidth("100.0%");
		absLRegadera.setHeight("45px");
		
		// txtRegadera
		txtRegadera = new TextField();
		txtRegadera.setImmediate(false);
		txtRegadera.setWidth("205px");
		txtRegadera.setHeight("25px");
		absLRegadera.addComponent(txtRegadera, "top:10.0px;left:10.0px;");
		
		// lblRegadera
		lblRegadera = new Label();
		lblRegadera.setImmediate(false);
		lblRegadera.setWidth("-1px");
		lblRegadera.setHeight("-1px");
		lblRegadera.setValue("l/s");
		absLRegadera.addComponent(lblRegadera, "top:14.0px;left:220.0px;");
		
		return absLRegadera;
	}

	@AutoGenerated
	private Panel buildPanSalida() {
		// common part: create layout
		panSalida = new Panel();
		panSalida.setCaption("Aforo a la Salida");
		panSalida.setImmediate(false);
		panSalida.setWidth("245px");
		panSalida.setHeight("-1px");
		
		// absSalida
		absSalida = buildAbsSalida();
		panSalida.setContent(absSalida);
		
		return panSalida;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsSalida() {
		// common part: create layout
		absSalida = new AbsoluteLayout();
		absSalida.setImmediate(false);
		absSalida.setWidth("100.0%");
		absSalida.setHeight("45px");
		
		// txtSalida
		txtSalida = new TextField();
		txtSalida.setImmediate(false);
		txtSalida.setWidth("205px");
		txtSalida.setHeight("25px");
		absSalida.addComponent(txtSalida, "top:10.0px;left:10.0px;");
		
		// lblSalida
		lblSalida = new Label();
		lblSalida.setImmediate(false);
		lblSalida.setWidth("-1px");
		lblSalida.setHeight("-1px");
		lblSalida.setValue("l/s");
		absSalida.addComponent(lblSalida, "top:14.0px;left:220.0px;");
		
		return absSalida;
	}
}