package com.saprigrat.ui.forms.parcelas;

import com.saprigrat.data.Datos;
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
	private Panel panDetTecnicos;
	@AutoGenerated
	private VerticalLayout verLDetTecnicos;
	@AutoGenerated
	private AbsoluteLayout absLDetTecnicos3;
	@AutoGenerated
	private ComboBox cmbModuloR;
	@AutoGenerated
	private AbsoluteLayout absLDetTecnicos2;
	@AutoGenerated
	private ComboBox cmbSRL;
	@AutoGenerated
	private AbsoluteLayout absLDetTecnicos1;
	@AutoGenerated
	private TextField txtUdeRiego;
	@AutoGenerated
	private ComboBox cmbDtoR;
	@AutoGenerated
	private ComboBox cmbOrgCuenca;
	@AutoGenerated
	private AbsoluteLayout absLGeneralesFoto;
	@AutoGenerated
	private Foto panFoto;
	@AutoGenerated
	private Panel panGenerales;
	@AutoGenerated
	private AbsoluteLayout absLGenerales;
	@AutoGenerated
	private Label lblSuperficie;
	@AutoGenerated
	private TextField txtSuperficie;
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
		
		llenarListas();
	}
	
	private void llenarListas()
	{
		//Llenar la lista de fuentes de abastecimiento
		cmbFAbast.setNewItemsAllowed(false);
		cmbFAbast.addItem("Presa de almacenamiento");
		cmbFAbast.addItem("Presa derivadora");
		cmbFAbast.addItem("Bordo de almacenamiento");
		cmbFAbast.addItem("Toma directa de r�o o arroyo");
		cmbFAbast.addItem("Dren");
		cmbFAbast.addItem("Pozo profundo");
		cmbFAbast.addItem("Noria");
		cmbFAbast.addItem("Otros");
		//Llenar la lista de tenencia de la tierra
		cmbTenencia.setNewItemsAllowed(false);
		cmbTenencia.addItem("Ejidal");
		cmbTenencia.addItem("Peque�a propiedad");
		cmbTenencia.addItem("Comunal");
		cmbTenencia.addItem("Otras");
		//Llenar la lista de organismos de cuenca
		cmbOrgCuenca.setNewItemsAllowed(false);
		cmbOrgCuenca.addItem("I Pen�nsula de Baja California");
		cmbOrgCuenca.addItem("II Noroeste");
		cmbOrgCuenca.addItem("III Pac�fico Norte");
		cmbOrgCuenca.addItem("IV Balsas");
		cmbOrgCuenca.addItem("V Pac�fico Sur");
		cmbOrgCuenca.addItem("VI R�o Bravo");
		cmbOrgCuenca.addItem("VII Cuencas Centrales del Norte");
		cmbOrgCuenca.addItem("VIII Lerma Santiago Pac�fico");
		cmbOrgCuenca.addItem("IX Golfo Norte");
		cmbOrgCuenca.addItem("X Golfo Centro");
		cmbOrgCuenca.addItem("XI Frontera del Sur");
		cmbOrgCuenca.addItem("XII Pen�nsula de Yucat�n");
		cmbOrgCuenca.addItem("XIII Aguas del Valle de M�xico");
	}

	@Override
	public void setTipo(int tipo)
	{
		if(tipo == 1)
		{
			verLDatos.setVisible(true);
		}
		else
		{
			verLDatos.setVisible(false);
			btnAccion.setCaption("Modificar");
		}
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
		
		// absLGeneralesFoto
		absLGeneralesFoto = buildAbsLGeneralesFoto();
		verLDatos.addComponent(absLGeneralesFoto);
		
		// panDetTecnicos
		panDetTecnicos = buildPanDetTecnicos();
		verLDatos.addComponent(panDetTecnicos);
		
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
		txtNoLote.setWidth("100px");
		txtNoLote.setHeight("25px");
		absLGenerales.addComponent(txtNoLote, "top:73.0px;left:10.0px;");
		
		// txtNoCtaPadron
		txtNoCtaPadron = new TextField();
		txtNoCtaPadron.setCaption("N�m. de Cta. del Padr�n");
		txtNoCtaPadron.setImmediate(false);
		txtNoCtaPadron.setWidth("140px");
		txtNoCtaPadron.setHeight("25px");
		absLGenerales.addComponent(txtNoCtaPadron, "top:28.0px;left:10.0px;");
		
		// txtNoSubctaPadron
		txtNoSubctaPadron = new TextField();
		txtNoSubctaPadron.setCaption("N�m. de Subcta.");
		txtNoSubctaPadron.setImmediate(false);
		txtNoSubctaPadron.setWidth("100px");
		txtNoSubctaPadron.setHeight("25px");
		absLGenerales.addComponent(txtNoSubctaPadron,
				"top:28.0px;left:170.0px;");
		
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
		
		// txtSuperficie
		txtSuperficie = new TextField();
		txtSuperficie.setCaption("Superficie");
		txtSuperficie.setImmediate(false);
		txtSuperficie.setWidth("120px");
		txtSuperficie.setHeight("25px");
		absLGenerales.addComponent(txtSuperficie, "top:73.0px;left:130.0px;");
		
		// lblSuperficie
		lblSuperficie = new Label();
		lblSuperficie.setImmediate(false);
		lblSuperficie.setWidth("-1px");
		lblSuperficie.setHeight("-1px");
		lblSuperficie.setValue("ha");
		absLGenerales.addComponent(lblSuperficie, "top:77.0px;left:255.0px;");
		
		return absLGenerales;
	}

	@AutoGenerated
	private Panel buildPanDetTecnicos() {
		// common part: create layout
		panDetTecnicos = new Panel();
		panDetTecnicos.setImmediate(false);
		panDetTecnicos.setWidth("601px");
		panDetTecnicos.setHeight("-1px");
		
		// verLDetTecnicos
		verLDetTecnicos = buildVerLDetTecnicos();
		panDetTecnicos.setContent(verLDetTecnicos);
		
		return panDetTecnicos;
	}

	@AutoGenerated
	private VerticalLayout buildVerLDetTecnicos() {
		// common part: create layout
		verLDetTecnicos = new VerticalLayout();
		verLDetTecnicos.setImmediate(false);
		verLDetTecnicos.setWidth("100.0%");
		verLDetTecnicos.setHeight("100.0%");
		verLDetTecnicos.setMargin(false);
		
		// absLDetTecnicos1
		absLDetTecnicos1 = buildAbsLDetTecnicos1();
		verLDetTecnicos.addComponent(absLDetTecnicos1);
		
		// absLDetTecnicos2
		absLDetTecnicos2 = buildAbsLDetTecnicos2();
		verLDetTecnicos.addComponent(absLDetTecnicos2);
		
		// absLDetTecnicos3
		absLDetTecnicos3 = buildAbsLDetTecnicos3();
		verLDetTecnicos.addComponent(absLDetTecnicos3);
		
		return verLDetTecnicos;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLDetTecnicos1() {
		// common part: create layout
		absLDetTecnicos1 = new AbsoluteLayout();
		absLDetTecnicos1.setImmediate(false);
		absLDetTecnicos1.setWidth("100.0%");
		absLDetTecnicos1.setHeight("55px");
		
		// cmbOrgCuenca
		cmbOrgCuenca = new ComboBox();
		cmbOrgCuenca.setCaption("Organismo de Cuenca");
		cmbOrgCuenca.setImmediate(false);
		cmbOrgCuenca.setWidth("270px");
		cmbOrgCuenca.setHeight("25px");
		absLDetTecnicos1.addComponent(cmbOrgCuenca, "top:28.0px;left:10.0px;");
		
		// cmbDtoR
		cmbDtoR = new ComboBox();
		cmbDtoR.setCaption("Distrito de Riego");
		cmbDtoR.setImmediate(false);
		cmbDtoR.setWidth("120px");
		cmbDtoR.setHeight("25px");
		absLDetTecnicos1.addComponent(cmbDtoR, "top:28.0px;left:300.0px;");
		
		// txtUdeRiego
		txtUdeRiego = new TextField();
		txtUdeRiego.setCaption("Unidad de Riego");
		txtUdeRiego.setImmediate(false);
		txtUdeRiego.setWidth("-1px");
		txtUdeRiego.setHeight("25px");
		absLDetTecnicos1.addComponent(txtUdeRiego, "top:28.0px;left:440.0px;");
		
		return absLDetTecnicos1;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLDetTecnicos2() {
		// common part: create layout
		absLDetTecnicos2 = new AbsoluteLayout();
		absLDetTecnicos2.setImmediate(false);
		absLDetTecnicos2.setWidth("100.0%");
		absLDetTecnicos2.setHeight("45px");
		
		// cmbSRL
		cmbSRL = new ComboBox();
		cmbSRL.setCaption("S.R.L.");
		cmbSRL.setImmediate(true);
		cmbSRL.setWidth("580px");
		cmbSRL.setHeight("25px");
		absLDetTecnicos2.addComponent(cmbSRL, "top:18.0px;left:10.0px;");
		
		return absLDetTecnicos2;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLDetTecnicos3() {
		// common part: create layout
		absLDetTecnicos3 = new AbsoluteLayout();
		absLDetTecnicos3.setImmediate(false);
		absLDetTecnicos3.setWidth("100.0%");
		absLDetTecnicos3.setHeight("55px");
		
		// cmbModuloR
		cmbModuloR = new ComboBox();
		cmbModuloR.setCaption("M�dulo de Riego");
		cmbModuloR.setImmediate(false);
		cmbModuloR.setWidth("580px");
		cmbModuloR.setHeight("25px");
		absLDetTecnicos3.addComponent(cmbModuloR, "top:18.0px;left:10.0px;");
		
		return absLDetTecnicos3;
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