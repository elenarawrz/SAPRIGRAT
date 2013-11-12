package com.saprigrat.ui.forms;

import com.saprigrat.ui.interfaces.Formulario;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ParcelaInfo extends CustomComponent implements Formulario
{
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Button btnAccion;
	@AutoGenerated
	private Panel panObservaciones;
	@AutoGenerated
	private AbsoluteLayout absLObservaciones;
	@AutoGenerated
	private TextArea txtObservaciones;
	@AutoGenerated
	private Panel panCoordsParcela;
	@AutoGenerated
	private HorizontalLayout horLCoordsParcela;
	@AutoGenerated
	private Panel panOeste;
	@AutoGenerated
	private AbsoluteLayout absLOeste;
	@AutoGenerated
	private TextField txtOesteY;
	@AutoGenerated
	private TextField txtOesteX;
	@AutoGenerated
	private Panel panEste;
	@AutoGenerated
	private AbsoluteLayout absLEste;
	@AutoGenerated
	private TextField txtEsteY;
	@AutoGenerated
	private TextField txtEsteX;
	@AutoGenerated
	private Panel panSur;
	@AutoGenerated
	private AbsoluteLayout absLSur;
	@AutoGenerated
	private TextField txtSurY;
	@AutoGenerated
	private TextField txtSurX;
	@AutoGenerated
	private Panel panNorte;
	@AutoGenerated
	private AbsoluteLayout absLNorte;
	@AutoGenerated
	private TextField txtNorteY;
	@AutoGenerated
	private TextField txtNorteX;
	@AutoGenerated
	private Panel panPtoAforo;
	@AutoGenerated
	private AbsoluteLayout absLPtoAforo;
	@AutoGenerated
	private TextField txtAforoY;
	@AutoGenerated
	private TextField txtAforoX;
	@AutoGenerated
	private ComboBox cmbMetAforado;
	@AutoGenerated
	private Panel panCosechaAnt;
	@AutoGenerated
	private AbsoluteLayout absLCosechaAnt;
	@AutoGenerated
	private Label lblRend;
	@AutoGenerated
	private TextField txtRendimientoAnt;
	@AutoGenerated
	private Label lblVolAnt;
	@AutoGenerated
	private TextField txtVolumenAnt;
	@AutoGenerated
	private TextField txtVariedadAnt;
	@AutoGenerated
	private TextField txtCultivoAnt;
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
	private Panel panGenerales;
	@AutoGenerated
	private AbsoluteLayout absLGenerales;
	@AutoGenerated
	private Upload uplFoto;
	@AutoGenerated
	private Embedded imgFoto;
	@AutoGenerated
	private TextField txtCultivo;
	@AutoGenerated
	private TextField txtVariedad;
	@AutoGenerated
	private Label lblSuperficie;
	@AutoGenerated
	private TextField txtSuperficie;
	@AutoGenerated
	private ComboBox cmbCiclo;
	@AutoGenerated
	private Label lblVolumen;
	@AutoGenerated
	private TextField txtVolumen;
	@AutoGenerated
	private ComboBox cmbTenencia;
	@AutoGenerated
	private Label lblCuota;
	@AutoGenerated
	private TextField txtCuota;
	@AutoGenerated
	private ComboBox cmbFAbast;
	@AutoGenerated
	private TextField txtNoSubctaPadron;
	@AutoGenerated
	private TextField txtNoCtaPadron;
	@AutoGenerated
	private TextField txtNoLote;
	@AutoGenerated
	private Label lblEncabezado;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public ParcelaInfo()
	{
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		cmbFAbast.setTextInputAllowed(false);
		cmbCiclo.setTextInputAllowed(false);
		cmbMetAforado.setTextInputAllowed(false);
	}

	@Override
	public void setTipo(int tipo)
	{
		if(tipo == 1)
		{
			lblEncabezado.setValue("Agregar Datos de Parcela");
			btnAccion.setCaption("Agregar");
		}
		else
		{
			lblEncabezado.setValue("Modificar Datos de Parcela");
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
		
		// lblEncabezado
		lblEncabezado = new Label();
		lblEncabezado.setImmediate(false);
		lblEncabezado.setWidth("-1px");
		lblEncabezado.setHeight("-1px");
		lblEncabezado.setValue("Label");
		mainLayout.addComponent(lblEncabezado);
		mainLayout.setComponentAlignment(lblEncabezado, new Alignment(6));
		
		// panGenerales
		panGenerales = buildPanGenerales();
		mainLayout.addComponent(panGenerales);
		
		// panDistribucion
		panDistribucion = buildPanDistribucion();
		mainLayout.addComponent(panDistribucion);
		
		// panCosechaAnt
		panCosechaAnt = buildPanCosechaAnt();
		mainLayout.addComponent(panCosechaAnt);
		
		// panPtoAforo
		panPtoAforo = buildPanPtoAforo();
		mainLayout.addComponent(panPtoAforo);
		
		// panCoordsParcela
		panCoordsParcela = buildPanCoordsParcela();
		mainLayout.addComponent(panCoordsParcela);
		
		// panObservaciones
		panObservaciones = buildPanObservaciones();
		mainLayout.addComponent(panObservaciones);
		
		// btnAccion
		btnAccion = new Button();
		btnAccion.setCaption("Button");
		btnAccion.setImmediate(true);
		btnAccion.setWidth("120px");
		btnAccion.setHeight("-1px");
		mainLayout.addComponent(btnAccion);
		mainLayout.setComponentAlignment(btnAccion, new Alignment(34));
		
		return mainLayout;
	}

	@AutoGenerated
	private Panel buildPanGenerales() {
		// common part: create layout
		panGenerales = new Panel();
		panGenerales.setCaption("Datos Generales");
		panGenerales.setImmediate(false);
		panGenerales.setWidth("721px");
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
		absLGenerales.setHeight("245px");
		
		// txtNoLote
		txtNoLote = new TextField();
		txtNoLote.setCaption("No. de Lote");
		txtNoLote.setImmediate(false);
		txtNoLote.setWidth("160px");
		txtNoLote.setHeight("25px");
		absLGenerales.addComponent(txtNoLote, "top:28.0px;left:10.0px;");
		
		// txtNoCtaPadron
		txtNoCtaPadron = new TextField();
		txtNoCtaPadron.setCaption("No. de Cta. del Padr�n");
		txtNoCtaPadron.setImmediate(false);
		txtNoCtaPadron.setWidth("130px");
		txtNoCtaPadron.setHeight("25px");
		absLGenerales.addComponent(txtNoCtaPadron, "top:26.0px;left:190.0px;");
		
		// txtNoSubctaPadron
		txtNoSubctaPadron = new TextField();
		txtNoSubctaPadron.setCaption("No. de Subcta.");
		txtNoSubctaPadron.setImmediate(false);
		txtNoSubctaPadron.setWidth("90px");
		txtNoSubctaPadron.setHeight("25px");
		absLGenerales.addComponent(txtNoSubctaPadron,
				"top:28.0px;left:340.0px;");
		
		// cmbFAbast
		cmbFAbast = new ComboBox();
		cmbFAbast.setCaption("Fuente de Abastecimiento");
		cmbFAbast.setImmediate(false);
		cmbFAbast.setWidth("240px");
		cmbFAbast.setHeight("25px");
		absLGenerales.addComponent(cmbFAbast, "top:73.0px;left:10.0px;");
		
		// txtCuota
		txtCuota = new TextField();
		txtCuota.setCaption("Cuota");
		txtCuota.setImmediate(false);
		txtCuota.setWidth("90px");
		txtCuota.setHeight("25px");
		absLGenerales.addComponent(txtCuota, "top:73.0px;left:270.0px;");
		
		// lblCuota
		lblCuota = new Label();
		lblCuota.setImmediate(false);
		lblCuota.setWidth("-1px");
		lblCuota.setHeight("-1px");
		lblCuota.setValue("$/ha");
		absLGenerales.addComponent(lblCuota, "top:77.0px;left:365.0px;");
		
		// cmbTenencia
		cmbTenencia = new ComboBox();
		cmbTenencia.setCaption("Tenencia de la Tierra");
		cmbTenencia.setImmediate(false);
		cmbTenencia.setWidth("240px");
		cmbTenencia.setHeight("25px");
		absLGenerales.addComponent(cmbTenencia, "top:118.0px;left:10.0px;");
		
		// txtVolumen
		txtVolumen = new TextField();
		txtVolumen.setCaption("Volumen Asignado");
		txtVolumen.setImmediate(false);
		txtVolumen.setWidth("90px");
		txtVolumen.setHeight("25px");
		absLGenerales.addComponent(txtVolumen, "top:118.0px;left:270.0px;");
		
		// lblVolumen
		lblVolumen = new Label();
		lblVolumen.setImmediate(false);
		lblVolumen.setWidth("-1px");
		lblVolumen.setHeight("-1px");
		lblVolumen.setValue("millar m�/ha");
		absLGenerales.addComponent(lblVolumen, "top:122.0px;left:365.0px;");
		
		// cmbCiclo
		cmbCiclo = new ComboBox();
		cmbCiclo.setCaption("Ciclo");
		cmbCiclo.setImmediate(false);
		cmbCiclo.setWidth("240px");
		cmbCiclo.setHeight("25px");
		absLGenerales.addComponent(cmbCiclo, "top:163.0px;left:10.0px;");
		
		// txtSuperficie
		txtSuperficie = new TextField();
		txtSuperficie.setCaption("Sup. Sembrada");
		txtSuperficie.setImmediate(false);
		txtSuperficie.setWidth("90px");
		txtSuperficie.setHeight("25px");
		absLGenerales.addComponent(txtSuperficie, "top:163.0px;left:270.0px;");
		
		// lblSuperficie
		lblSuperficie = new Label();
		lblSuperficie.setImmediate(false);
		lblSuperficie.setWidth("-1px");
		lblSuperficie.setHeight("-1px");
		lblSuperficie.setValue("ha");
		absLGenerales.addComponent(lblSuperficie, "top:167.0px;left:365.0px;");
		
		// txtVariedad
		txtVariedad = new TextField();
		txtVariedad.setCaption("Variedad");
		txtVariedad.setImmediate(false);
		txtVariedad.setWidth("190px");
		txtVariedad.setHeight("25px");
		absLGenerales.addComponent(txtVariedad, "top:208.0px;left:10.0px;");
		
		// txtCultivo
		txtCultivo = new TextField();
		txtCultivo.setCaption("Cultivo");
		txtCultivo.setImmediate(false);
		txtCultivo.setWidth("210px");
		txtCultivo.setHeight("25px");
		absLGenerales.addComponent(txtCultivo, "top:208.0px;left:220.0px;");
		
		// imgFoto
		imgFoto = new Embedded();
		imgFoto.setCaption("Foto (click para seleccionar)");
		imgFoto.setImmediate(false);
		imgFoto.setWidth("-1px");
		imgFoto.setHeight("200px");
		imgFoto.setSource(new ThemeResource("img/imgUnavailable.jpg"));
		imgFoto.setType(1);
		imgFoto.setMimeType("image/png");
		absLGenerales.addComponent(imgFoto, "top:28.0px;right:10.0px;");
		
		// uplFoto
		uplFoto = new Upload();
		uplFoto.setCaption("Foto");
		uplFoto.setEnabled(false);
		uplFoto.setImmediate(false);
		uplFoto.setVisible(false);
		uplFoto.setWidth("-1px");
		uplFoto.setHeight("27px");
		absLGenerales.addComponent(uplFoto, "top:123.0px;right:10.0px;");
		
		return absLGenerales;
	}

	@AutoGenerated
	private Panel buildPanDistribucion() {
		// common part: create layout
		panDistribucion = new Panel();
		panDistribucion.setCaption("Red de Distribuci�n");
		panDistribucion.setImmediate(false);
		panDistribucion.setWidth("721px");
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
		txtPrincipal.setWidth("220px");
		txtPrincipal.setHeight("25px");
		absLDistribucion.addComponent(txtPrincipal, "top:28.0px;left:10.0px;");
		
		// txtLateral
		txtLateral = new TextField();
		txtLateral.setCaption("Lateral");
		txtLateral.setImmediate(false);
		txtLateral.setWidth("220px");
		txtLateral.setHeight("25px");
		absLDistribucion.addComponent(txtLateral, "top:28.0px;left:250.0px;");
		
		// txtSublateral
		txtSublateral = new TextField();
		txtSublateral.setCaption("Sublateral");
		txtSublateral.setImmediate(false);
		txtSublateral.setWidth("220px");
		txtSublateral.setHeight("25px");
		absLDistribucion
				.addComponent(txtSublateral, "top:28.0px;left:490.0px;");
		
		// txtRamal
		txtRamal = new TextField();
		txtRamal.setCaption("Ramal");
		txtRamal.setImmediate(false);
		txtRamal.setWidth("220px");
		txtRamal.setHeight("25px");
		absLDistribucion.addComponent(txtRamal, "top:73.0px;left:10.0px;");
		
		// txtSubramal
		txtSubramal = new TextField();
		txtSubramal.setCaption("Subramal");
		txtSubramal.setImmediate(false);
		txtSubramal.setWidth("220px");
		txtSubramal.setHeight("25px");
		absLDistribucion.addComponent(txtSubramal, "top:73.0px;left:250.0px;");
		
		// txtRegadera
		txtRegadera = new TextField();
		txtRegadera.setCaption("Regadera");
		txtRegadera.setImmediate(false);
		txtRegadera.setWidth("220px");
		txtRegadera.setHeight("25px");
		absLDistribucion.addComponent(txtRegadera, "top:73.0px;left:490.0px;");
		
		return absLDistribucion;
	}

	@AutoGenerated
	private Panel buildPanCosechaAnt() {
		// common part: create layout
		panCosechaAnt = new Panel();
		panCosechaAnt.setCaption("Cosecha Anterior");
		panCosechaAnt.setImmediate(false);
		panCosechaAnt.setWidth("481px");
		panCosechaAnt.setHeight("-1px");
		
		// absLCosechaAnt
		absLCosechaAnt = buildAbsLCosechaAnt();
		panCosechaAnt.setContent(absLCosechaAnt);
		
		return panCosechaAnt;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLCosechaAnt() {
		// common part: create layout
		absLCosechaAnt = new AbsoluteLayout();
		absLCosechaAnt.setImmediate(false);
		absLCosechaAnt.setWidth("100.0%");
		absLCosechaAnt.setHeight("110px");
		
		// txtCultivoAnt
		txtCultivoAnt = new TextField();
		txtCultivoAnt.setCaption("Cultivo Anterior");
		txtCultivoAnt.setImmediate(false);
		txtCultivoAnt.setWidth("220px");
		txtCultivoAnt.setHeight("25px");
		absLCosechaAnt.addComponent(txtCultivoAnt, "top:28.0px;left:10.0px;");
		
		// txtVariedadAnt
		txtVariedadAnt = new TextField();
		txtVariedadAnt.setCaption("Variedad Anterior");
		txtVariedadAnt.setImmediate(false);
		txtVariedadAnt.setWidth("220px");
		txtVariedadAnt.setHeight("25px");
		absLCosechaAnt.addComponent(txtVariedadAnt, "top:28.0px;left:250.0px;");
		
		// txtVolumenAnt
		txtVolumenAnt = new TextField();
		txtVolumenAnt.setCaption("Volumen Anterior Utilizado");
		txtVolumenAnt.setImmediate(false);
		txtVolumenAnt.setWidth("150px");
		txtVolumenAnt.setHeight("25px");
		absLCosechaAnt.addComponent(txtVolumenAnt, "top:73.0px;left:10.0px;");
		
		// lblVolAnt
		lblVolAnt = new Label();
		lblVolAnt.setImmediate(false);
		lblVolAnt.setWidth("-1px");
		lblVolAnt.setHeight("-1px");
		lblVolAnt.setValue("millar m�/ha");
		absLCosechaAnt.addComponent(lblVolAnt, "top:77.0px;left:165.0px;");
		
		// txtRendimientoAnt
		txtRendimientoAnt = new TextField();
		txtRendimientoAnt.setCaption("Rendimiento Anterior");
		txtRendimientoAnt.setImmediate(false);
		txtRendimientoAnt.setWidth("150px");
		txtRendimientoAnt.setHeight("25px");
		absLCosechaAnt.addComponent(txtRendimientoAnt,
				"top:73.0px;left:250.0px;");
		
		// lblRend
		lblRend = new Label();
		lblRend.setImmediate(false);
		lblRend.setWidth("-1px");
		lblRend.setHeight("-1px");
		lblRend.setValue("ton/ha");
		absLCosechaAnt.addComponent(lblRend, "top:77.0px;left:405.0px;");
		
		return absLCosechaAnt;
	}

	@AutoGenerated
	private Panel buildPanPtoAforo() {
		// common part: create layout
		panPtoAforo = new Panel();
		panPtoAforo.setCaption("Punto de Aforo");
		panPtoAforo.setImmediate(false);
		panPtoAforo.setWidth("601px");
		panPtoAforo.setHeight("-1px");
		
		// absLPtoAforo
		absLPtoAforo = buildAbsLPtoAforo();
		panPtoAforo.setContent(absLPtoAforo);
		
		return panPtoAforo;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLPtoAforo() {
		// common part: create layout
		absLPtoAforo = new AbsoluteLayout();
		absLPtoAforo.setImmediate(false);
		absLPtoAforo.setWidth("100.0%");
		absLPtoAforo.setHeight("65px");
		
		// cmbMetAforado
		cmbMetAforado = new ComboBox();
		cmbMetAforado.setCaption("M�todo de Aforado");
		cmbMetAforado.setImmediate(false);
		cmbMetAforado.setWidth("240px");
		cmbMetAforado.setHeight("25px");
		absLPtoAforo.addComponent(cmbMetAforado, "top:28.0px;left:10.0px;");
		
		// txtAforoX
		txtAforoX = new TextField();
		txtAforoX.setCaption("Coordenada X");
		txtAforoX.setImmediate(false);
		txtAforoX.setWidth("150px");
		txtAforoX.setHeight("25px");
		absLPtoAforo.addComponent(txtAforoX, "top:28.0px;left:270.0px;");
		
		// txtAforoY
		txtAforoY = new TextField();
		txtAforoY.setCaption("Coordenada Y");
		txtAforoY.setImmediate(false);
		txtAforoY.setWidth("150px");
		txtAforoY.setHeight("25px");
		absLPtoAforo.addComponent(txtAforoY, "top:28.0px;left:440.0px;");
		
		return absLPtoAforo;
	}

	@AutoGenerated
	private Panel buildPanCoordsParcela() {
		// common part: create layout
		panCoordsParcela = new Panel();
		panCoordsParcela.setCaption("Coordenadas de la Parcela");
		panCoordsParcela.setImmediate(false);
		panCoordsParcela.setWidth("721px");
		panCoordsParcela.setHeight("-1px");
		
		// horLCoordsParcela
		horLCoordsParcela = buildHorLCoordsParcela();
		panCoordsParcela.setContent(horLCoordsParcela);
		
		return panCoordsParcela;
	}

	@AutoGenerated
	private HorizontalLayout buildHorLCoordsParcela() {
		// common part: create layout
		horLCoordsParcela = new HorizontalLayout();
		horLCoordsParcela.setImmediate(false);
		horLCoordsParcela.setWidth("100.0%");
		horLCoordsParcela.setHeight("-1px");
		horLCoordsParcela.setMargin(false);
		horLCoordsParcela.setSpacing(true);
		
		// panNorte
		panNorte = buildPanNorte();
		horLCoordsParcela.addComponent(panNorte);
		
		// panSur
		panSur = buildPanSur();
		horLCoordsParcela.addComponent(panSur);
		
		// panEste
		panEste = buildPanEste();
		horLCoordsParcela.addComponent(panEste);
		
		// panOeste
		panOeste = buildPanOeste();
		horLCoordsParcela.addComponent(panOeste);
		
		return horLCoordsParcela;
	}

	@AutoGenerated
	private Panel buildPanNorte() {
		// common part: create layout
		panNorte = new Panel();
		panNorte.setCaption("Punto Norte");
		panNorte.setImmediate(false);
		panNorte.setWidth("-1px");
		panNorte.setHeight("-1px");
		
		// absLNorte
		absLNorte = buildAbsLNorte();
		panNorte.setContent(absLNorte);
		
		return panNorte;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLNorte() {
		// common part: create layout
		absLNorte = new AbsoluteLayout();
		absLNorte.setImmediate(false);
		absLNorte.setWidth("171px");
		absLNorte.setHeight("110px");
		
		// txtNorteX
		txtNorteX = new TextField();
		txtNorteX.setCaption("Coordenada X");
		txtNorteX.setImmediate(false);
		txtNorteX.setWidth("150px");
		txtNorteX.setHeight("25px");
		absLNorte.addComponent(txtNorteX, "top:28.0px;left:10.0px;");
		
		// txtNorteY
		txtNorteY = new TextField();
		txtNorteY.setCaption("Coordenada Y");
		txtNorteY.setImmediate(false);
		txtNorteY.setWidth("150px");
		txtNorteY.setHeight("25px");
		absLNorte.addComponent(txtNorteY, "top:73.0px;left:10.0px;");
		
		return absLNorte;
	}

	@AutoGenerated
	private Panel buildPanSur() {
		// common part: create layout
		panSur = new Panel();
		panSur.setCaption("Punto Sur");
		panSur.setImmediate(false);
		panSur.setWidth("-1px");
		panSur.setHeight("-1px");
		
		// absLSur
		absLSur = buildAbsLSur();
		panSur.setContent(absLSur);
		
		return panSur;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLSur() {
		// common part: create layout
		absLSur = new AbsoluteLayout();
		absLSur.setImmediate(false);
		absLSur.setWidth("171px");
		absLSur.setHeight("110px");
		
		// txtSurX
		txtSurX = new TextField();
		txtSurX.setCaption("Coordenada X");
		txtSurX.setImmediate(false);
		txtSurX.setWidth("150px");
		txtSurX.setHeight("25px");
		absLSur.addComponent(txtSurX, "top:28.0px;left:10.0px;");
		
		// txtSurY
		txtSurY = new TextField();
		txtSurY.setCaption("Coordenada Y");
		txtSurY.setImmediate(false);
		txtSurY.setWidth("150px");
		txtSurY.setHeight("25px");
		absLSur.addComponent(txtSurY, "top:73.0px;left:10.0px;");
		
		return absLSur;
	}

	@AutoGenerated
	private Panel buildPanEste() {
		// common part: create layout
		panEste = new Panel();
		panEste.setCaption("Punto Este");
		panEste.setImmediate(false);
		panEste.setWidth("-1px");
		panEste.setHeight("-1px");
		
		// absLEste
		absLEste = buildAbsLEste();
		panEste.setContent(absLEste);
		
		return panEste;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLEste() {
		// common part: create layout
		absLEste = new AbsoluteLayout();
		absLEste.setImmediate(false);
		absLEste.setWidth("171px");
		absLEste.setHeight("110px");
		
		// txtEsteX
		txtEsteX = new TextField();
		txtEsteX.setCaption("Coordenada X");
		txtEsteX.setImmediate(false);
		txtEsteX.setWidth("150px");
		txtEsteX.setHeight("25px");
		absLEste.addComponent(txtEsteX, "top:28.0px;left:10.0px;");
		
		// txtEsteY
		txtEsteY = new TextField();
		txtEsteY.setCaption("Coordenada Y");
		txtEsteY.setImmediate(false);
		txtEsteY.setWidth("150px");
		txtEsteY.setHeight("25px");
		absLEste.addComponent(txtEsteY, "top:73.0px;left:10.0px;");
		
		return absLEste;
	}

	@AutoGenerated
	private Panel buildPanOeste() {
		// common part: create layout
		panOeste = new Panel();
		panOeste.setCaption("Punto Oeste");
		panOeste.setImmediate(false);
		panOeste.setWidth("-1px");
		panOeste.setHeight("-1px");
		
		// absLOeste
		absLOeste = buildAbsLOeste();
		panOeste.setContent(absLOeste);
		
		return panOeste;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLOeste() {
		// common part: create layout
		absLOeste = new AbsoluteLayout();
		absLOeste.setImmediate(false);
		absLOeste.setWidth("171px");
		absLOeste.setHeight("110px");
		
		// txtOesteX
		txtOesteX = new TextField();
		txtOesteX.setCaption("Coordenada X");
		txtOesteX.setImmediate(false);
		txtOesteX.setWidth("150px");
		txtOesteX.setHeight("25px");
		absLOeste.addComponent(txtOesteX, "top:28.0px;left:10.0px;");
		
		// txtOesteY
		txtOesteY = new TextField();
		txtOesteY.setCaption("Coordenada Y");
		txtOesteY.setImmediate(false);
		txtOesteY.setWidth("150px");
		txtOesteY.setHeight("25px");
		absLOeste.addComponent(txtOesteY, "top:73.0px;left:10.0px;");
		
		return absLOeste;
	}

	@AutoGenerated
	private Panel buildPanObservaciones() {
		// common part: create layout
		panObservaciones = new Panel();
		panObservaciones.setCaption("Observaciones");
		panObservaciones.setImmediate(false);
		panObservaciones.setWidth("721px");
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
		txtObservaciones.setWidth("700px");
		txtObservaciones.setHeight("100px");
		absLObservaciones.addComponent(txtObservaciones,
				"top:10.0px;left:10.0px;");
		
		return absLObservaciones;
	}
}