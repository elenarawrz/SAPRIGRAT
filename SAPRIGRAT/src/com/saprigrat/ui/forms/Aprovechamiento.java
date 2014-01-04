package com.saprigrat.ui.forms;

import com.saprigrat.data.Datos;
import com.saprigrat.ui.components.Busqueda;
import com.saprigrat.ui.components.Foto;
import com.saprigrat.ui.interfaces.Formulario;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class Aprovechamiento extends CustomComponent implements Formulario
{
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private VerticalLayout verLDatos;
	@AutoGenerated
	private Button btnAccion;
	@AutoGenerated
	private Panel panObservaciones;
	@AutoGenerated
	private AbsoluteLayout absLObservaciones;
	@AutoGenerated
	private TextArea txtObservaciones;
	@AutoGenerated
	private Panel panCoordenadas;
	@AutoGenerated
	private AbsoluteLayout absLCoordenadas;
	@AutoGenerated
	private TextField txtOesteY;
	@AutoGenerated
	private TextField txtOesteX;
	@AutoGenerated
	private Label lblOeste;
	@AutoGenerated
	private TextField txtEsteY;
	@AutoGenerated
	private TextField txtEsteX;
	@AutoGenerated
	private Label lblEste;
	@AutoGenerated
	private TextField txtSurY;
	@AutoGenerated
	private TextField txtSurX;
	@AutoGenerated
	private Label lblSur;
	@AutoGenerated
	private TextField txtNorteY;
	@AutoGenerated
	private TextField txtNorteX;
	@AutoGenerated
	private Label lblNorte;
	@AutoGenerated
	private Panel panDistribucion;
	@AutoGenerated
	private AbsoluteLayout absLDistribucion;
	@AutoGenerated
	private TextField txtRegadera;
	@AutoGenerated
	private TextField txtSubramal;
	@AutoGenerated
	private TextField txtRamal;
	@AutoGenerated
	private TextField txtSublateral;
	@AutoGenerated
	private TextField txtLateral;
	@AutoGenerated
	private TextField txtPrincipal;
	@AutoGenerated
	private AbsoluteLayout absLGeneralesFoto;
	@AutoGenerated
	private Foto panFoto;
	@AutoGenerated
	private Panel panGenerales;
	@AutoGenerated
	private AbsoluteLayout absLGenerales;
	@AutoGenerated
	private ComboBox cmbTenencia;
	@AutoGenerated
	private ComboBox cmbFAbast;
	@AutoGenerated
	private TextField txtNoSubctaPadron;
	@AutoGenerated
	private TextField txtNoCtaPadron;
	@AutoGenerated
	private TextField txtNoLote;
	@AutoGenerated
	private Panel panCURR;
	@AutoGenerated
	private AbsoluteLayout absLCURR;
	@AutoGenerated
	private TextField txtCURR;
	@AutoGenerated
	private Busqueda panBusqueda;
	private Datos datos;
	public Aprovechamiento()
	{
		inicializar();
	}

	@Override
	public void inicializar()
	{
		buildMainLayout();
		setCompositionRoot(mainLayout);
		datos = new Datos();
		//Deshabilitar la edici�n de CURR
		txtCURR.setEnabled(false);
	}

	@Override
	public void setTipo(int tipo)
	{
		if(tipo == 1)
		{
			panBusqueda.setVisible(false);
			verLDatos.setVisible(true);
		}
		else
		{
			panBusqueda.setListado(new String[]{"CURR", "Nombre"}, txtCURR);
			panBusqueda.setVisible(true);
			verLDatos.setVisible(false);
			btnAccion.setCaption("Modificar");
		}
	}

	@Override
	public int getEntidad()
	{
		return 2;
	}
	
	@Override
	public Object[] getRestricciones()
	{
		return new Object[]{};
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
		
		// verLDatos
		verLDatos = buildVerLDatos();
		mainLayout.addComponent(verLDatos);
		
		return mainLayout;
	}

	@AutoGenerated
	private VerticalLayout buildVerLDatos() {
		// common part: create layout
		verLDatos = new VerticalLayout();
		verLDatos.setImmediate(false);
		verLDatos.setWidth("-1px");
		verLDatos.setHeight("-1px");
		verLDatos.setMargin(false);
		verLDatos.setSpacing(true);
		
		// panBusqueda
		panBusqueda = new Busqueda();
		panBusqueda.setImmediate(false);
		panBusqueda.setWidth("100.0%");
		panBusqueda.setHeight("-1px");
		verLDatos.addComponent(panBusqueda);
		
		// panCURR
		panCURR = buildPanCURR();
		verLDatos.addComponent(panCURR);
		
		// absLGeneralesFoto
		absLGeneralesFoto = buildAbsLGeneralesFoto();
		verLDatos.addComponent(absLGeneralesFoto);
		
		// panDistribucion
		panDistribucion = buildPanDistribucion();
		verLDatos.addComponent(panDistribucion);
		
		// panCoordenadas
		panCoordenadas = buildPanCoordenadas();
		verLDatos.addComponent(panCoordenadas);
		
		// panObservaciones
		panObservaciones = buildPanObservaciones();
		verLDatos.addComponent(panObservaciones);
		
		// btnAccion
		btnAccion = new Button();
		btnAccion.setCaption("Agregar");
		btnAccion.setImmediate(true);
		btnAccion.setWidth("120px");
		btnAccion.setHeight("-1px");
		verLDatos.addComponent(btnAccion);
		verLDatos.setComponentAlignment(btnAccion, new Alignment(34));
		
		return verLDatos;
	}

	@AutoGenerated
	private Panel buildPanCURR() {
		// common part: create layout
		panCURR = new Panel();
		panCURR.setCaption("CURR (Clave Unica de Registro de RIGRAT)");
		panCURR.setImmediate(false);
		panCURR.setWidth("281px");
		panCURR.setHeight("-1px");
		
		// absLCURR
		absLCURR = buildAbsLCURR();
		panCURR.setContent(absLCURR);
		
		return panCURR;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLCURR() {
		// common part: create layout
		absLCURR = new AbsoluteLayout();
		absLCURR.setImmediate(false);
		absLCURR.setWidth("100.0%");
		absLCURR.setHeight("45px");
		
		// txtCURR
		txtCURR = new TextField();
		txtCURR.setImmediate(true);
		txtCURR.setWidth("260px");
		txtCURR.setHeight("25px");
		absLCURR.addComponent(txtCURR, "top:10.0px;left:10.0px;");
		
		return absLCURR;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLGeneralesFoto() {
		// common part: create layout
		absLGeneralesFoto = new AbsoluteLayout();
		absLGeneralesFoto.setImmediate(false);
		absLGeneralesFoto.setWidth("100.0%");
		absLGeneralesFoto.setHeight("221px");
		
		// panGenerales
		panGenerales = buildPanGenerales();
		absLGeneralesFoto.addComponent(panGenerales, "top:0.0px;left:0.0px;");
		
		// panFoto
		panFoto = new Foto();
		panFoto.setImmediate(false);
		panFoto.setWidth("-1px");
		panFoto.setHeight("-1px");
		absLGeneralesFoto.addComponent(panFoto, "top:0.0px;right:0.0px;");
		
		return absLGeneralesFoto;
	}

	@AutoGenerated
	private Panel buildPanGenerales() {
		// common part: create layout
		panGenerales = new Panel();
		panGenerales.setCaption("Datos Generales");
		panGenerales.setImmediate(false);
		panGenerales.setWidth("281px");
		panGenerales.setHeight("-1px");
		
		// absLGenerales
		absLGenerales = buildAbsLGenerales();
		panGenerales.setContent(absLGenerales);
		
		return panGenerales;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLGenerales() {
		// common part: create layout
		absLGenerales = new AbsoluteLayout();
		absLGenerales.setImmediate(false);
		absLGenerales.setWidth("100.0%");
		absLGenerales.setHeight("200px");
		
		// txtNoLote
		txtNoLote = new TextField();
		txtNoLote.setCaption("N�m. de Lote");
		txtNoLote.setImmediate(false);
		txtNoLote.setWidth("260px");
		txtNoLote.setHeight("25px");
		absLGenerales.addComponent(txtNoLote, "top:28.0px;left:10.0px;");
		
		// txtNoCtaPadron
		txtNoCtaPadron = new TextField();
		txtNoCtaPadron.setCaption("N�m. de Cta. del Padr�n");
		txtNoCtaPadron.setImmediate(false);
		txtNoCtaPadron.setWidth("140px");
		txtNoCtaPadron.setHeight("25px");
		absLGenerales.addComponent(txtNoCtaPadron, "top:73.0px;left:10.0px;");
		
		// txtNoSubctaPadron
		txtNoSubctaPadron = new TextField();
		txtNoSubctaPadron.setCaption("N�m. de Subcta.");
		txtNoSubctaPadron.setImmediate(false);
		txtNoSubctaPadron.setWidth("100px");
		txtNoSubctaPadron.setHeight("25px");
		absLGenerales.addComponent(txtNoSubctaPadron,
				"top:73.0px;left:170.0px;");
		
		// cmbFAbast
		cmbFAbast = new ComboBox();
		cmbFAbast.setCaption("Fuente de Abastecimiento");
		cmbFAbast.setImmediate(false);
		cmbFAbast.setWidth("260px");
		cmbFAbast.setHeight("25px");
		absLGenerales.addComponent(cmbFAbast, "top:118.0px;left:10.0px;");
		
		// cmbTenencia
		cmbTenencia = new ComboBox();
		cmbTenencia.setCaption("Tenencia de la Tierra");
		cmbTenencia.setImmediate(false);
		cmbTenencia.setWidth("260px");
		cmbTenencia.setHeight("25px");
		absLGenerales.addComponent(cmbTenencia, "top:163.0px;left:10.0px;");
		
		return absLGenerales;
	}

	@AutoGenerated
	private Panel buildPanDistribucion() {
		// common part: create layout
		panDistribucion = new Panel();
		panDistribucion.setCaption("Red de Distribuci�n");
		panDistribucion.setImmediate(false);
		panDistribucion.setWidth("601px");
		panDistribucion.setHeight("-1px");
		
		// absLDistribucion
		absLDistribucion = buildAbsLDistribucion();
		panDistribucion.setContent(absLDistribucion);
		
		return panDistribucion;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLDistribucion() {
		// common part: create layout
		absLDistribucion = new AbsoluteLayout();
		absLDistribucion.setImmediate(false);
		absLDistribucion.setWidth("100.0%");
		absLDistribucion.setHeight("110px");
		
		// txtPrincipal
		txtPrincipal = new TextField();
		txtPrincipal.setCaption("Principal");
		txtPrincipal.setImmediate(false);
		txtPrincipal.setWidth("180px");
		txtPrincipal.setHeight("25px");
		absLDistribucion.addComponent(txtPrincipal, "top:28.0px;left:10.0px;");
		
		// txtLateral
		txtLateral = new TextField();
		txtLateral.setCaption("Lateral");
		txtLateral.setImmediate(false);
		txtLateral.setWidth("180px");
		txtLateral.setHeight("25px");
		absLDistribucion.addComponent(txtLateral, "top:28.0px;left:210.0px;");
		
		// txtSublateral
		txtSublateral = new TextField();
		txtSublateral.setCaption("Sublateral");
		txtSublateral.setImmediate(false);
		txtSublateral.setWidth("180px");
		txtSublateral.setHeight("25px");
		absLDistribucion
				.addComponent(txtSublateral, "top:28.0px;left:410.0px;");
		
		// txtRamal
		txtRamal = new TextField();
		txtRamal.setCaption("Ramal");
		txtRamal.setImmediate(false);
		txtRamal.setWidth("180px");
		txtRamal.setHeight("25px");
		absLDistribucion.addComponent(txtRamal, "top:73.0px;left:10.0px;");
		
		// txtSubramal
		txtSubramal = new TextField();
		txtSubramal.setCaption("Subramal");
		txtSubramal.setImmediate(false);
		txtSubramal.setWidth("180px");
		txtSubramal.setHeight("25px");
		absLDistribucion.addComponent(txtSubramal, "top:73.0px;left:210.0px;");
		
		// txtRegadera
		txtRegadera = new TextField();
		txtRegadera.setCaption("Regadera");
		txtRegadera.setImmediate(false);
		txtRegadera.setWidth("180px");
		txtRegadera.setHeight("25px");
		absLDistribucion.addComponent(txtRegadera, "top:73.0px;left:410.0px;");
		
		return absLDistribucion;
	}

	@AutoGenerated
	private Panel buildPanCoordenadas() {
		// common part: create layout
		panCoordenadas = new Panel();
		panCoordenadas.setCaption("Coordenadas");
		panCoordenadas.setImmediate(false);
		panCoordenadas.setWidth("531px");
		panCoordenadas.setHeight("-1px");
		
		// absLCoordenadas
		absLCoordenadas = buildAbsLCoordenadas();
		panCoordenadas.setContent(absLCoordenadas);
		
		return panCoordenadas;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLCoordenadas() {
		// common part: create layout
		absLCoordenadas = new AbsoluteLayout();
		absLCoordenadas.setImmediate(false);
		absLCoordenadas.setWidth("100.0%");
		absLCoordenadas.setHeight("200px");
		
		// lblNorte
		lblNorte = new Label();
		lblNorte.setImmediate(false);
		lblNorte.setWidth("-1px");
		lblNorte.setHeight("-1px");
		lblNorte.setValue("Punto Norte:");
		absLCoordenadas.addComponent(lblNorte, "top:12.0px;left:10.0px;");
		
		// txtNorteX
		txtNorteX = new TextField();
		txtNorteX.setCaption("Coordenada X");
		txtNorteX.setImmediate(false);
		txtNorteX.setWidth("200px");
		txtNorteX.setHeight("25px");
		absLCoordenadas.addComponent(txtNorteX, "top:28.0px;left:100.0px;");
		
		// txtNorteY
		txtNorteY = new TextField();
		txtNorteY.setCaption("Coordenada Y");
		txtNorteY.setImmediate(false);
		txtNorteY.setWidth("200px");
		txtNorteY.setHeight("25px");
		absLCoordenadas.addComponent(txtNorteY, "top:28.0px;left:320.0px;");
		
		// lblSur
		lblSur = new Label();
		lblSur.setImmediate(false);
		lblSur.setWidth("-1px");
		lblSur.setHeight("-1px");
		lblSur.setValue("Punto Sur:");
		absLCoordenadas.addComponent(lblSur, "top:57.0px;left:10.0px;");
		
		// txtSurX
		txtSurX = new TextField();
		txtSurX.setCaption("Coordenada X");
		txtSurX.setImmediate(false);
		txtSurX.setWidth("200px");
		txtSurX.setHeight("25px");
		absLCoordenadas.addComponent(txtSurX, "top:73.0px;left:100.0px;");
		
		// txtSurY
		txtSurY = new TextField();
		txtSurY.setCaption("Coordenada Y");
		txtSurY.setImmediate(false);
		txtSurY.setWidth("200px");
		txtSurY.setHeight("25px");
		absLCoordenadas.addComponent(txtSurY, "top:73.0px;left:320.0px;");
		
		// lblEste
		lblEste = new Label();
		lblEste.setImmediate(false);
		lblEste.setWidth("-1px");
		lblEste.setHeight("-1px");
		lblEste.setValue("Punto Este:");
		absLCoordenadas.addComponent(lblEste, "top:102.0px;left:10.0px;");
		
		// txtEsteX
		txtEsteX = new TextField();
		txtEsteX.setCaption("Coordenada X");
		txtEsteX.setImmediate(false);
		txtEsteX.setWidth("200px");
		txtEsteX.setHeight("25px");
		absLCoordenadas.addComponent(txtEsteX, "top:118.0px;left:100.0px;");
		
		// txtEsteY
		txtEsteY = new TextField();
		txtEsteY.setCaption("Coordenada Y");
		txtEsteY.setImmediate(false);
		txtEsteY.setWidth("200px");
		txtEsteY.setHeight("25px");
		absLCoordenadas.addComponent(txtEsteY, "top:118.0px;left:320.0px;");
		
		// lblOeste
		lblOeste = new Label();
		lblOeste.setImmediate(false);
		lblOeste.setWidth("-1px");
		lblOeste.setHeight("-1px");
		lblOeste.setValue("Punto Oeste:");
		absLCoordenadas.addComponent(lblOeste, "top:147.0px;left:10.0px;");
		
		// txtOesteX
		txtOesteX = new TextField();
		txtOesteX.setCaption("Coordenada X");
		txtOesteX.setImmediate(false);
		txtOesteX.setWidth("200px");
		txtOesteX.setHeight("25px");
		absLCoordenadas.addComponent(txtOesteX, "top:163.0px;left:100.0px;");
		
		// txtOesteY
		txtOesteY = new TextField();
		txtOesteY.setCaption("Coordenada Y");
		txtOesteY.setImmediate(false);
		txtOesteY.setWidth("200px");
		txtOesteY.setHeight("25px");
		absLCoordenadas.addComponent(txtOesteY, "top:163.0px;left:320.0px;");
		
		return absLCoordenadas;
	}

	@AutoGenerated
	private Panel buildPanObservaciones() {
		// common part: create layout
		panObservaciones = new Panel();
		panObservaciones.setCaption("Observaciones");
		panObservaciones.setImmediate(false);
		panObservaciones.setWidth("601px");
		panObservaciones.setHeight("-1px");
		
		// absLObservaciones
		absLObservaciones = buildAbsLObservaciones();
		panObservaciones.setContent(absLObservaciones);
		
		return panObservaciones;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLObservaciones() {
		// common part: create layout
		absLObservaciones = new AbsoluteLayout();
		absLObservaciones.setImmediate(false);
		absLObservaciones.setWidth("100.0%");
		absLObservaciones.setHeight("120px");
		
		// txtObservaciones
		txtObservaciones = new TextArea();
		txtObservaciones.setImmediate(false);
		txtObservaciones.setWidth("580px");
		txtObservaciones.setHeight("100px");
		absLObservaciones.addComponent(txtObservaciones,
				"top:10.0px;left:10.0px;");
		
		return absLObservaciones;
	}
}