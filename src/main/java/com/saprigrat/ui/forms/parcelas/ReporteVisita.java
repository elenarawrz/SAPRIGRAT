package com.saprigrat.ui.forms.parcelas;

import java.sql.Date;
import java.util.LinkedList;

import org.vaadin.dialogs.ConfirmDialog;

import com.saprigrat.entities.Usuario;
import com.saprigrat.enums.TipoUsuario;
import com.saprigrat.ui.interfaces.FormParcela;
import com.saprigrat.ui.interfaces.Formulario;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutAction.ModifierKey;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ReporteVisita extends CustomComponent implements Formulario, FormParcela
{
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
	//region controles autogenerados
	
	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private AbsoluteLayout absLBotones;
	@AutoGenerated
	private HorizontalLayout horLBotones;
	@AutoGenerated
	private Button btnVolver;
	@AutoGenerated
	private Button btnNuevo;
	@AutoGenerated
	private Button btnGuardar;
	@AutoGenerated
	private VerticalLayout verLDatos;
	@AutoGenerated
	private Panel panRecomendaciones;
	@AutoGenerated
	private AbsoluteLayout absLRecomendaciones;
	@AutoGenerated
	private TextArea txtRecomendaciones;
	@AutoGenerated
	private Panel panObservaciones;
	@AutoGenerated
	private AbsoluteLayout absLObservaciones;
	@AutoGenerated
	private TextArea txtObservaciones;
	@AutoGenerated
	private Panel panDetalles;
	@AutoGenerated
	private VerticalLayout verLDetalles;
	@AutoGenerated
	private AbsoluteLayout absLDetalles3;
	@AutoGenerated
	private TextField txtVariedad;
	@AutoGenerated
	private TextField txtCultivo;
	@AutoGenerated
	private AbsoluteLayout absLDetalles2;
	@AutoGenerated
	private TextField txtFenologia;
	@AutoGenerated
	private Label lblSuperficie;
	@AutoGenerated
	private TextField txtSuperficie;
	@AutoGenerated
	private TextField txtNumVisita;
	@AutoGenerated
	private AbsoluteLayout absLDetalles1;
	@AutoGenerated
	private DateField datFecha;
	@AutoGenerated
	private ComboBox cmbParcela;
	@AutoGenerated
	private VerticalLayout verLListado;
	@AutoGenerated
	private Label lblRowCount;
	@AutoGenerated
	private Table tblListado;
	@AutoGenerated
	private HorizontalLayout horLRespProd;
	@AutoGenerated
	private Panel panProductor;
	@AutoGenerated
	private AbsoluteLayout absLProductor;
	@AutoGenerated
	private ComboBox cmbProductor;
	@AutoGenerated
	private Panel panResponsable;
	@AutoGenerated
	private AbsoluteLayout absLResponsable;
	@AutoGenerated
	private ComboBox cmbResponsable;
	
	//endregion
	private Usuario usuario;
	private int idReporte, idParcela;

	//region eventos
	private ClickListener guardarListener = new ClickListener()
			{
				@Override
				public void buttonClick(ClickEvent event)
				{
					if(validar())
					{
						LinkedList<Object> valores = llenarListaDeParametros();
						if(btnGuardar.getCaption().equals("Guardar"))
						{
							String status = datos.registrarReporte(valores);
							if(!status.isEmpty() && status.indexOf("err") == -1)
							{
								u.msjGuardado("El reporte de visita", "");
								//Se cambia a modo de modificación
								setTipo(MODO_MODIFICAR);
								idReporte = Integer.parseInt(status);
							}
							else
								if(status.indexOf("Rep") != -1)
									u.msjNoGuardadoRepetido("número de visita");
								else
									u.msjNoGuardado("reporte de visita");
						}
						else
						{
							valores.add(0, idReporte);
							
							String status = datos.modificarReporte(valores);
							if(!status.isEmpty() && status.indexOf("err") == -1)
								u.msjModificado("El reporte de visita");
							else
								if(status.indexOf("Rep") != -1)
									u.msjNoModificadoRepetido("número de visita");
								else
									u.msjNoModificado("reporte de visita");
						}
					}
				}
			};
	private ItemClickListener llenarFormListener = new ItemClickListener()
			{
				@Override
				public void itemClick(ItemClickEvent event)
				{
					if(event.isDoubleClick())
					{
						idReporte = (Integer)event.getItem().getItemProperty("ID").getValue();
						LinkedList<Object> valores = datos.getReporte(idReporte);
						if(!valores.isEmpty())
						{
							setTipo(MODO_MODIFICAR);
							
							u.llenarCombo(cmbProductor, datos.getRespProductores(((String)cmbResponsable.getValue()).split(" ")[0]));
							cmbProductor.setValue(valores.remove());
							u.llenarCombo(cmbParcela, datos.getCtasPadron(((String)cmbProductor.getValue()).split(" ")[0]));
							cmbParcela.setValue(valores.remove());
							datFecha.setValue(u.fechaSQLToUtil((Date)valores.remove()));
							txtNumVisita.setValue(valores.remove() + "");
							txtSuperficie.setValue(valores.remove() + "");
							txtFenologia.setValue((String)valores.remove());
							txtCultivo.setValue((String)valores.remove());
							txtVariedad.setValue((String)valores.remove());
							txtObservaciones.setValue((String)valores.remove());
							txtRecomendaciones.setValue((String)valores.remove());
						}
						else
							u.msjRegistroNoObtenido();
					}
				}
			};
	private ValueChangeListener llenarTablaOProductoresListener = new ValueChangeListener()
			{
				@Override
				public void valueChange(ValueChangeEvent event)
				{
					String curr = ((String)cmbResponsable.getValue()).split(" ")[0];
					if(verLListado.isVisible())
						lblRowCount.setValue(u.llenarTabla(tblListado, horLRespProd.isVisible() ? datos.getReportes(curr)
																								: datos.getReportesByParcela(idParcela), "reporte de visita"));
					else
					{
						cmbParcela.removeAllItems();
						u.llenarCombo(cmbProductor, datos.getRespProductores(curr));
					}
				}
			};
	private ClickListener nuevoListener = new ClickListener()
			{
				@Override
				public void buttonClick(ClickEvent event)
				{
					if(horLRespProd.isVisible())
					{
						if(!verLListado.isVisible())
							showDialog(MODO_AGREGAR);
						else
							resetForm(MODO_AGREGAR);
					}
					else
					{
						idReporte = 0;
						datFecha.setValue(null);
						txtNumVisita.setValue("");
						txtSuperficie.setValue("");
						txtFenologia.setValue("");
						txtCultivo.setValue("");
						txtVariedad.setValue("");
						txtObservaciones.setValue("");
						txtRecomendaciones.setValue("");
						
						setTipo(MODO_AGREGAR);
					}
				}
			};
	private ClickListener volverListener = new ClickListener()
			{
				@Override
				public void buttonClick(ClickEvent event)
				{
					if(horLRespProd.isVisible())
						showDialog(MODO_LISTA);
					else
						setTipo(MODO_LISTA);
					llenarTablaByParcela();
				}
			};
	private ValueChangeListener llenarParcelasListener = new ValueChangeListener()
			{
				@Override
				public void valueChange(ValueChangeEvent event)
				{
					if(cmbProductor.size() != 0)
					{
						u.llenarCombo(cmbParcela, datos.getCtasPadron(((String)cmbProductor.getValue()).split(" ")[0]));
						if(cmbParcela.size() == 0)
							u.notificar("No se podrá agregar el registro ya que este " + TipoUsuario.PRODUCTOR + " no está relacionado con ninguna parcela.", null, u.MSJ_ERROR);
					}
					else
						u.notificar("No se podrá agregar el registro ya que este " + TipoUsuario.RESPONSABLE_TECNICO + " no tiene asignado a ningún " + TipoUsuario.PRODUCTOR, null, u.MSJ_ERROR);
				}
			};
	//endregion
	
	public ReporteVisita() {}
	public ReporteVisita(Usuario user) { init(user); }

	@Override
	public void init(Usuario user)
	{
		usuario = user;
	}
	
	@Override
	public void inicializar()
	{
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		//Combos
		u.setupCombo(cmbResponsable, null, "Busque por nombre o CURR.", FilteringMode.CONTAINS, false, true);
		u.setupCombo(cmbProductor, null, "Busque por nombre o CURR.", FilteringMode.CONTAINS, false, true);
		u.setupCombo(cmbParcela, null, "Busque por número de cuenta de padrón.", null, false, true);
		cmbResponsable.addValueChangeListener(llenarTablaOProductoresListener);
		cmbProductor.addValueChangeListener(llenarParcelasListener);
		//Calendarios
		datFecha.setDateFormat("dd-MM-yyyy");
		//Botones
		btnNuevo.addClickListener(nuevoListener);
		btnVolver.addClickListener(volverListener);
		btnGuardar.addClickListener(guardarListener);
		//Tablas
		tblListado.setSelectable(true);
		tblListado.setSortEnabled(true);
		tblListado.setColumnCollapsingAllowed(true);
		tblListado.addItemClickListener(llenarFormListener);
		
		//Inicializar en modo lista
		setTipo(MODO_LISTA);
		//Agregar las validaciones necesarias
		setValidaciones();
	}

	@Override
	public void setTipo(int modoOperacion)
	{
		btnNuevo.setClickShortcut(KeyCode.N, ModifierKey.ALT);
		if(modoOperacion == MODO_LISTA)
		{
			setResponsableTecnico(usuario.getTipo());
			mostrarTabla(true);
			btnGuardar.removeClickShortcut();
			btnGuardar.setVisible(false);
			btnVolver.removeClickShortcut();
			btnVolver.setVisible(false);
			btnNuevo.setCaption("Agregar Nuevo");
		}
		else
		{
			mostrarTabla(false);
			btnGuardar.setClickShortcut(KeyCode.G, ModifierKey.ALT);
			btnGuardar.setVisible(true);
			btnVolver.setClickShortcut(KeyCode.V, ModifierKey.ALT);
			btnVolver.setVisible(true);
			
			if(modoOperacion == MODO_AGREGAR)
			{
				elementosIdHabilitados(true);
				btnGuardar.setCaption("Guardar");
				btnNuevo.setClickShortcut(KeyCode.L, ModifierKey.ALT);
				btnNuevo.setCaption("Limpiar");
			}
			else
			{
				elementosIdHabilitados(false);
				btnGuardar.setCaption("Guardar cambios");
				btnNuevo.setCaption("Agregar Nuevo");
			}
		}
	}
	
	@Override
	public void quitarListeners ()
	{
		cmbResponsable.removeValueChangeListener(llenarTablaOProductoresListener);
		cmbProductor.removeValueChangeListener(llenarParcelasListener);
		
		horLRespProd.setVisible(false);
		cmbParcela.setVisible(false);
		absLDetalles1.removeComponent(datFecha);
		absLDetalles1.addComponent(datFecha, "top:28.0px;left:10.0px;");
	}

	@Override
	public void setParcela (int idParcela, String padron)
	{
		u.llenarCombo(cmbParcela, padron);
		this.idParcela = idParcela;
		llenarTablaByParcela();
	}

	@Override
	public void setResponsable (String responsable)
	{
		u.llenarCombo(cmbResponsable, responsable);
	}

	@Override
	public void setProductor (String productor)
	{
		u.llenarCombo(cmbProductor, productor);
	}
	
	private void setResponsableTecnico(int tipoAgregando)
	{
		if(horLRespProd.isVisible())
		{
			if(tipoAgregando != usuario.RESPONSABLE)
				u.llenarCombo(cmbResponsable, datos.getRespTecnicos(usuario.getCURR(), tipoAgregando, usuario.RESPONSABLE));
			else
				u.llenarCombo(cmbResponsable, usuario.getCURR() + " " + usuario.getNombre() + " " +
											  usuario.getApPaterno() + " " + usuario.getApMaterno());
			if(cmbResponsable.size() == 0)
			{
				mainLayout.removeAllComponents();
				u.notificar("Imposible registrar reporte, se requiere que exista al menos una parcela", null, u.MSJ_ERROR);
			}
		}
	}

	private void llenarTablaByParcela ()
	{
		if(idParcela > 0)
			lblRowCount.setValue(u.llenarTabla(tblListado, datos.getReportesByParcela(idParcela), "reporte de visita"));
		else
		{
			lblRowCount.setValue(u.llenarTabla(tblListado, new LinkedList<Object[]>(), "reporte de visita"));
			setTipo(MODO_LISTA);
		}
	}

	private void mostrarTabla(boolean estado)
	{
		panProductor.setVisible(!estado);
		verLListado.setVisible(estado);
		verLDatos.setVisible(!estado);
	}
	
	private void elementosIdHabilitados(boolean habilitado)
	{
		cmbResponsable.setEnabled(habilitado);
		cmbProductor.setEnabled(habilitado);
		cmbParcela.setEnabled(habilitado);
	}

	private void setValidaciones()
	{
		RegexpValidator esDecimal = u.getRegExDecimal(),
						esEntero = u.getRegExEntero();
		txtNumVisita.addValidator(esEntero);
		txtSuperficie.addValidator(esDecimal);
	}
	
	private boolean validar()
	{
		if(cmbResponsable.getValue() == null)
			return u.notificar("Se requiere especificar el responsable técnico", cmbResponsable, u.MSJ_ERROR);
		if(cmbProductor.getValue() == null)
			return u.notificar("Se requiere especificar el usuario", cmbProductor, u.MSJ_ERROR);
		if(cmbParcela.getValue() == null)
			return u.notificar("Se requiere especificar la parcela que se está visitando", cmbParcela, u.MSJ_ERROR);

		if(datFecha.getValue() == null)
			return u.notificar("Se requiere especificar la fecha de visita", datFecha, u.MSJ_ERROR);
		if(txtNumVisita.getValue().isEmpty() || !txtNumVisita.isValid())
			return u.notificar("Se requiere especificar un valor numérico estríctamente entero para el número de visita", txtNumVisita, u.MSJ_ERROR);
		if(txtSuperficie.getValue().isEmpty() || !txtSuperficie.isValid())
			return u.notificar("Se requiere especificar un valor estríctamente numérico para la superficie", txtSuperficie, u.MSJ_ERROR);
		if(txtFenologia.getValue().isEmpty())
			return u.notificar("Se requiere especificar la etapa fenológica", txtFenologia, u.MSJ_ERROR);
		if(txtCultivo.getValue().isEmpty())
			return u.notificar("Se requiere especificar el cultivo", txtCultivo, u.MSJ_ERROR);
		if(txtVariedad.getValue().isEmpty())
			return u.notificar("Se requiere especificar la variedad", txtVariedad, u.MSJ_ERROR);
		
		return true;
	}
	
	private LinkedList<Object> llenarListaDeParametros()
	{
		LinkedList<Object> valores = new LinkedList<Object>();
		valores.add(cmbParcela.getValue());
		valores.add(u.fechaUtilToSQL(datFecha.getValue()));
		valores.add(Integer.parseInt(txtNumVisita.getValue()));
		valores.add(Double.parseDouble(txtSuperficie.getValue()));
		valores.add(txtFenologia.getValue());
		valores.add(txtCultivo.getValue());
		valores.add(txtVariedad.getValue());
		valores.add(txtObservaciones.getValue());
		valores.add(txtRecomendaciones.getValue());
		return valores;
	}
	
	private void showDialog(final int modo)
	{
		ConfirmDialog.show( this.getUI(),
							"Advertencia:",
							"Perderá todos los cambios realizados hasta el momento.\n¿Desea continuar?",
							"Si", "No",
							new ConfirmDialog.Listener()
							{
								public void onClose(ConfirmDialog dialog)
								{
									if (dialog.isConfirmed())
										resetForm(modo);
								}
							});
	}
	
	private void resetForm(int modo)
	{
		Object respSelected = cmbResponsable.getValue();
		inicializar();
		setTipo(modo);
		cmbResponsable.setValue(respSelected);
	}
	//region UI

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
		
		// horLRespProd
		horLRespProd = buildHorLRespProd();
		mainLayout.addComponent(horLRespProd);
		
		// verLListado
		verLListado = buildVerLListado();
		mainLayout.addComponent(verLListado);
		
		// verLDatos
		verLDatos = buildVerLDatos();
		mainLayout.addComponent(verLDatos);
		
		// absLBotones
		absLBotones = buildAbsLBotones();
		mainLayout.addComponent(absLBotones);
		
		return mainLayout;
	}

	@AutoGenerated
	private HorizontalLayout buildHorLRespProd() {
		// common part: create layout
		horLRespProd = new HorizontalLayout();
		horLRespProd.setImmediate(false);
		horLRespProd.setWidth("801px");
		horLRespProd.setHeight("-1px");
		horLRespProd.setMargin(false);
		
		// panResponsable
		panResponsable = buildPanResponsable();
		horLRespProd.addComponent(panResponsable);
		
		// panProductor
		panProductor = buildPanProductor();
		horLRespProd.addComponent(panProductor);
		horLRespProd.setComponentAlignment(panProductor, new Alignment(6));
		
		return horLRespProd;
	}

	@AutoGenerated
	private Panel buildPanResponsable() {
		// common part: create layout
		panResponsable = new Panel();
		panResponsable.setCaption("Responsable Técnico");
		panResponsable.setImmediate(false);
		panResponsable.setWidth("396px");
		panResponsable.setHeight("-1px");
		
		// absLResponsable
		absLResponsable = buildAbsLResponsable();
		panResponsable.setContent(absLResponsable);
		
		return panResponsable;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLResponsable() {
		// common part: create layout
		absLResponsable = new AbsoluteLayout();
		absLResponsable.setImmediate(false);
		absLResponsable.setWidth("100.0%");
		absLResponsable.setHeight("45px");
		
		// cmbResponsable
		cmbResponsable = new ComboBox();
		cmbResponsable.setImmediate(true);
		cmbResponsable.setWidth("375px");
		cmbResponsable.setHeight("25px");
		absLResponsable.addComponent(cmbResponsable, "top:10.0px;left:10.0px;");
		
		return absLResponsable;
	}

	@AutoGenerated
	private Panel buildPanProductor() {
		// common part: create layout
		panProductor = new Panel();
		panProductor.setCaption("Usuario");
		panProductor.setImmediate(false);
		panProductor.setWidth("396px");
		panProductor.setHeight("-1px");
		
		// absLProductor
		absLProductor = buildAbsLProductor();
		panProductor.setContent(absLProductor);
		
		return panProductor;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLProductor() {
		// common part: create layout
		absLProductor = new AbsoluteLayout();
		absLProductor.setImmediate(false);
		absLProductor.setWidth("100.0%");
		absLProductor.setHeight("45px");
		
		// cmbProductor
		cmbProductor = new ComboBox();
		cmbProductor.setImmediate(true);
		cmbProductor.setWidth("375px");
		cmbProductor.setHeight("25px");
		absLProductor.addComponent(cmbProductor, "top:10.0px;left:10.0px;");
		
		return absLProductor;
	}
	
	@AutoGenerated
	private VerticalLayout buildVerLListado() {
		// common part: create layout
		verLListado = new VerticalLayout();
		verLListado.setImmediate(false);
		verLListado.setWidth("-1px");
		verLListado.setHeight("-1px");
		verLListado.setMargin(false);
		
		// tblListado
		tblListado = new Table();
		tblListado
				.setCaption("* Dar doble click sobre un elemento para modificarlo");
		tblListado.setImmediate(false);
		tblListado.setWidth("801px");
		tblListado.setHeight("300px");
		verLListado.addComponent(tblListado);
		
		// label_1
		lblRowCount = new Label();
		lblRowCount.setImmediate(false);
		lblRowCount.setWidth("-1px");
		lblRowCount.setHeight("-1px");
		lblRowCount.setValue("0 registros encontrados");
		verLListado.addComponent(lblRowCount);
		
		return verLListado;
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
		
		// panDetalles
		panDetalles = buildPanDetalles();
		verLDatos.addComponent(panDetalles);
		
		// panObservaciones
		panObservaciones = buildPanObservaciones();
		verLDatos.addComponent(panObservaciones);
		
		// panRecomendaciones
		panRecomendaciones = buildPanRecomendaciones();
		verLDatos.addComponent(panRecomendaciones);
		
		return verLDatos;
	}

	@AutoGenerated
	private Panel buildPanDetalles() {
		// common part: create layout
		panDetalles = new Panel();
		panDetalles.setCaption("Detalles del Riego");
		panDetalles.setImmediate(false);
		panDetalles.setWidth("801px");
		panDetalles.setHeight("-1px");
		
		// verLDetalles
		verLDetalles = buildVerLDetalles();
		panDetalles.setContent(verLDetalles);
		
		return panDetalles;
	}

	@AutoGenerated
	private VerticalLayout buildVerLDetalles() {
		// common part: create layout
		verLDetalles = new VerticalLayout();
		verLDetalles.setImmediate(false);
		verLDetalles.setWidth("100.0%");
		verLDetalles.setHeight("-1px");
		verLDetalles.setMargin(false);
		
		// absLDetalles1
		absLDetalles1 = buildAbsLDetalles1();
		verLDetalles.addComponent(absLDetalles1);
		
		// absLDetalles2
		absLDetalles2 = buildAbsLDetalles2();
		verLDetalles.addComponent(absLDetalles2);
		
		// absLDetalles3
		absLDetalles3 = buildAbsLDetalles3();
		verLDetalles.addComponent(absLDetalles3);
		
		return verLDetalles;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLDetalles1() {
		// common part: create layout
		absLDetalles1 = new AbsoluteLayout();
		absLDetalles1.setImmediate(false);
		absLDetalles1.setWidth("100.0%");
		absLDetalles1.setHeight("55px");
		
		// cmbParcela
		cmbParcela = new ComboBox();
		cmbParcela.setCaption("Parcela (Núm. de Cta. / Subcta. de Padrón)");
		cmbParcela.setImmediate(true);
		cmbParcela.setWidth("390px");
		cmbParcela.setHeight("25px");
		absLDetalles1.addComponent(cmbParcela, "top:28.0px;left:10.0px;");
		
		// datFecha
		datFecha = new DateField();
		datFecha.setCaption("Fecha de Visita");
		datFecha.setImmediate(false);
		datFecha.setWidth("370px");
		datFecha.setHeight("25px");
		absLDetalles1.addComponent(datFecha, "top:28.0px;left:420.0px;");
		
		return absLDetalles1;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLDetalles2() {
		// common part: create layout
		absLDetalles2 = new AbsoluteLayout();
		absLDetalles2.setImmediate(false);
		absLDetalles2.setWidth("100.0%");
		absLDetalles2.setHeight("45px");
		
		// txtNumVisita
		txtNumVisita = new TextField();
		txtNumVisita.setCaption("Núm. de Visita");
		txtNumVisita.setImmediate(true);
		txtNumVisita.setWidth("150px");
		txtNumVisita.setHeight("25px");
		absLDetalles2.addComponent(txtNumVisita, "top:18.0px;left:10.0px;");
		
		// txtSuperficie
		txtSuperficie = new TextField();
		txtSuperficie.setCaption("Superficie");
		txtSuperficie.setImmediate(true);
		txtSuperficie.setWidth("190px");
		txtSuperficie.setHeight("25px");
		absLDetalles2.addComponent(txtSuperficie, "top:18.0px;left:180.0px;");
		
		// lblSuperficie
		lblSuperficie = new Label();
		lblSuperficie.setImmediate(false);
		lblSuperficie.setWidth("-1px");
		lblSuperficie.setHeight("-1px");
		lblSuperficie.setValue("ha");
		absLDetalles2.addComponent(lblSuperficie, "top:22.0px;left:375.0px;");
		
		// txtFenologia
		txtFenologia = new TextField();
		txtFenologia.setCaption("Fenología");
		txtFenologia.setImmediate(false);
		txtFenologia.setWidth("380px");
		txtFenologia.setHeight("25px");
		absLDetalles2.addComponent(txtFenologia, "top:18.0px;left:410.0px;");
		
		return absLDetalles2;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLDetalles3() {
		// common part: create layout
		absLDetalles3 = new AbsoluteLayout();
		absLDetalles3.setImmediate(false);
		absLDetalles3.setWidth("100.0%");
		absLDetalles3.setHeight("55px");
		
		// txtCultivo
		txtCultivo = new TextField();
		txtCultivo.setCaption("Cultivo");
		txtCultivo.setImmediate(false);
		txtCultivo.setWidth("380px");
		txtCultivo.setHeight("25px");
		absLDetalles3.addComponent(txtCultivo, "top:18.0px;left:10.0px;");
		
		// txtVariedad
		txtVariedad = new TextField();
		txtVariedad.setCaption("Variedad");
		txtVariedad.setImmediate(false);
		txtVariedad.setWidth("380px");
		txtVariedad.setHeight("25px");
		absLDetalles3.addComponent(txtVariedad, "top:18.0px;left:410.0px;");
		
		return absLDetalles3;
	}

	@AutoGenerated
	private Panel buildPanObservaciones() {
		// common part: create layout
		panObservaciones = new Panel();
		panObservaciones.setCaption("Hallazgos y observaciones");
		panObservaciones.setImmediate(false);
		panObservaciones.setWidth("801px");
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
		txtObservaciones.setWidth("780px");
		txtObservaciones.setHeight("100px");
		absLObservaciones.addComponent(txtObservaciones,
				"top:10.0px;left:10.0px;");
		
		return absLObservaciones;
	}

	@AutoGenerated
	private Panel buildPanRecomendaciones() {
		// common part: create layout
		panRecomendaciones = new Panel();
		panRecomendaciones.setCaption("Recomendaciones");
		panRecomendaciones.setImmediate(false);
		panRecomendaciones.setWidth("801px");
		panRecomendaciones.setHeight("-1px");
		
		// absLRecomendaciones
		absLRecomendaciones = buildAbsLRecomendaciones();
		panRecomendaciones.setContent(absLRecomendaciones);
		
		return panRecomendaciones;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLRecomendaciones() {
		// common part: create layout
		absLRecomendaciones = new AbsoluteLayout();
		absLRecomendaciones.setImmediate(false);
		absLRecomendaciones.setWidth("100.0%");
		absLRecomendaciones.setHeight("120px");
		
		// txtRecomendaciones
		txtRecomendaciones = new TextArea();
		txtRecomendaciones.setImmediate(false);
		txtRecomendaciones.setWidth("780px");
		txtRecomendaciones.setHeight("100px");
		absLRecomendaciones.addComponent(txtRecomendaciones,
				"top:10.0px;left:10.0px;");
		
		return absLRecomendaciones;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLBotones() {
		// common part: create layout
		absLBotones = new AbsoluteLayout();
		absLBotones.setImmediate(false);
		absLBotones.setWidth("801px");
		absLBotones.setHeight("30px");
		
		// btnGuardar
		btnGuardar = new Button();
		btnGuardar.setCaption("Guardar");
		btnGuardar.setImmediate(true);
		btnGuardar.setWidth("-1px");
		btnGuardar.setHeight("-1px");
		absLBotones.addComponent(btnGuardar, "right:0.0px;bottom:0.0px;");
		
		// horLBotones
		horLBotones = buildHorLBotones();
		absLBotones.addComponent(horLBotones, "bottom:0.0px;left:0.0px;");
		
		return absLBotones;
	}

	@AutoGenerated
	private HorizontalLayout buildHorLBotones() {
		// common part: create layout
		horLBotones = new HorizontalLayout();
		horLBotones.setImmediate(false);
		horLBotones.setWidth("-1px");
		horLBotones.setHeight("-1px");
		horLBotones.setMargin(false);
		horLBotones.setSpacing(true);
		
		// btnNuevo
		btnNuevo = new Button();
		btnNuevo.setCaption("Agregar Nuevo");
		btnNuevo.setImmediate(true);
		btnNuevo.setWidth("-1px");
		btnNuevo.setHeight("-1px");
		horLBotones.addComponent(btnNuevo);
		
		// btnVolver
		btnVolver = new Button();
		btnVolver.setCaption("Volver");
		btnVolver.setImmediate(true);
		btnVolver.setWidth("-1px");
		btnVolver.setHeight("-1px");
		horLBotones.addComponent(btnVolver);
		
		return horLBotones;
	}

	//endregion
}
