package com.saprigrat.ui.forms.parcelas;

import java.sql.Date;
import java.util.LinkedList;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.vaadin.addons.tuningdatefield.TuningDateField;

import com.saprigrat.data.Datos;
import com.saprigrat.data.Usuario;
import com.saprigrat.ui.interfaces.Formulario;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutAction.ModifierKey;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class Regador extends CustomComponent implements Formulario
{
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

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
	private HorizontalLayout horLGeneralesDetalles;
	@AutoGenerated
	private Panel panDetalles;
	@AutoGenerated
	private AbsoluteLayout absLDetalles;
	@AutoGenerated
	private CheckBox chkCapacitacion;
	@AutoGenerated
	private TuningDateField datIngreso;
	@AutoGenerated
	private Panel panGenerales;
	@AutoGenerated
	private VerticalLayout verLGenerales;
	@AutoGenerated
	private AbsoluteLayout absLGenerales2;
	@AutoGenerated
	private TextField txtApM;
	@AutoGenerated
	private TextField txtApP;
	@AutoGenerated
	private AbsoluteLayout absLGenerales1;
	@AutoGenerated
	private TextField txtNombre;
	@AutoGenerated
	private Table tblListado;
	@AutoGenerated
	private Panel panParcela;
	@AutoGenerated
	private AbsoluteLayout absLParcela;
	@AutoGenerated
	private ComboBox cmbParcela;
	private Usuario usuario;
	private Datos datos;
	private String parcelaAnterior, nombreAnterior;
	
	private ClickListener agregarListener = new ClickListener()
											{
												@Override
												public void buttonClick(ClickEvent event)
												{
													if(validar())
													{
														LinkedList<Object> valores = new LinkedList<Object>();
														valores = llenarListaDeParametros(valores);
														valores.add(4, getFecha(datIngreso));
														
														if(datos.registrarRegador(valores))
														{
															Notification.show("Registro a�adido correctamente.", Notification.Type.TRAY_NOTIFICATION);
															//Se cambia a modo de modificaci�n
															setIdAnterior((String)valores.peek());
															setTipo(MODO_MODIFICAR);
														}
														else
															Notification.show("Error al a�adir registro, comun�quese con el administrador.", Notification.Type.ERROR_MESSAGE);
													}
												}
												
												private Date getFecha(TuningDateField calendario)
												{
													if(calendario.getValue() == null)
														return null;
													else
														return new Date(calendario.getLocalDate().toDate().getTime());
												}
											};
	private ClickListener modificarListener =   new ClickListener()
												{
													@Override
													public void buttonClick(ClickEvent event)
													{
														if(validar())
														{
															LinkedList<Object> valores = new LinkedList<Object>();
															valores.add(parcelaAnterior);
															valores.add(nombreAnterior);
															valores = llenarListaDeParametros(valores);
															
															if(datos.modificarRegador(valores))
															{
																Notification.show("Registro modificado correctamente.", Notification.Type.TRAY_NOTIFICATION);
																setIdAnterior((String)valores.get(2));
															}
														}
													}
												};
	private ValueChangeListener llenarTablaListener =   new ValueChangeListener()
														{
															@Override
															public void valueChange(ValueChangeEvent event)
															{
																tblListado.removeAllItems();
																//Obtener la lista
																LinkedList<Object[]> resultados = datos.getRegadores(((String)cmbParcela.getValue()).split(" ")[0]);
																//Crear encabezados de columnas
																setHeaders((String[]) resultados.remove());
																//Llenar la lista o mostrar un mensaje dependiendo de si se encontraron elementos o no
																if(!resultados.isEmpty())
																	while(!resultados.isEmpty())
																		tblListado.addItem(resultados.remove(), null);
																else
																	Notification.show("No se encontr� ning�n resultado.", Notification.Type.HUMANIZED_MESSAGE);
															}
													
															private void setHeaders(String[] encabezado)
															{
																for(int i=0; i<encabezado.length; i++)
																	tblListado.addContainerProperty(encabezado[i], Object.class, null);
															}
														};
	private ItemClickListener llenarFormListener =  new ItemClickListener()
													{
														@Override
														public void itemClick(ItemClickEvent event)
														{
															if(event.isDoubleClick())
															{
																Item item = event.getItem();
																txtNombre.setValue((String)item.getItemProperty("Nombre").getValue());
																txtApP.setValue((String)item.getItemProperty("Ap. Paterno").getValue());
																txtApM.setValue((String)item.getItemProperty("Ap. Materno").getValue());
																datIngreso.setLocalDate(setFecha((Date)item.getItemProperty("Fecha de Ingreso").getValue()));
																chkCapacitacion.setValue(item.getItemProperty("Capacitaci�n Recibida").getValue().equals("Si"));
												
																setIdAnterior(((String)cmbParcela.getValue()).split(" ")[0]);
																setTipo(MODO_MODIFICAR);
															}
														}
														
														private LocalDate setFecha(Date fecha)
														{
															if(fecha == null)
																return null;
															else
																return new LocalDate(fecha.getTime());
														}
													};
	private ClickListener nuevoListener =   new ClickListener()
											{
												@Override
												public void buttonClick(ClickEvent event)
												{
													inicializar();
													setTipo(MODO_AGREGAR);
												}
											};
	private ClickListener listadoListener = new ClickListener()
											{
												@Override
												public void buttonClick(ClickEvent event)
												{
													inicializar();
													setTipo(MODO_LISTA);
												}
											};
			
	public Regador(Usuario user)
	{
		usuario = user;
//		inicializar();
	}

	@Override
	public void inicializar()
	{
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		datos = new Datos();

		cmbParcela.setNullSelectionAllowed(false);
		cmbParcela.setFilteringMode(FilteringMode.CONTAINS);
		cmbParcela.setInputPrompt("Busque por nombre o CURR.");
		//Configurar la funcionalidad de selecci�n en tabla
		tblListado.setSelectable(true);
		tblListado.addItemClickListener(llenarFormListener);
		//Configurar el formato de fecha mostrado
		datIngreso.setWeekendDisabled(false);
		datIngreso.setDateTimeFormatter(DateTimeFormat.longDate());
		datIngreso.setDateTextReadOnly(true);
		
		btnNuevo.addClickListener(nuevoListener);
		btnVolver.addClickListener(listadoListener);
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
			cmbParcela.addValueChangeListener(llenarTablaListener);
			setParcela(usuario.getTipo());
			mostrarTabla(true);
			btnGuardar.removeClickShortcut();
			btnGuardar.setVisible(false);
			btnVolver.removeClickShortcut();
			btnVolver.setVisible(false);
			btnNuevo.setCaption("Agregar Nuevo");
		}
		else
		{
			cmbParcela.removeValueChangeListener(llenarTablaListener);
			mostrarTabla(false);
			btnGuardar.setClickShortcut(KeyCode.G, ModifierKey.ALT);
			btnGuardar.setVisible(true);
			btnVolver.setClickShortcut(KeyCode.V, ModifierKey.ALT);
			btnVolver.setVisible(true);
			
			if(modoOperacion == MODO_AGREGAR)
			{
				elementosIdHabilitados(true);
				btnGuardar.removeClickListener(modificarListener);
				btnGuardar.addClickListener(agregarListener);
				btnGuardar.setCaption("Guardar");
				btnNuevo.setClickShortcut(KeyCode.L, ModifierKey.ALT);
				btnNuevo.setCaption("Limpiar");
			}
			else
			{
				elementosIdHabilitados(false);
				btnGuardar.removeClickListener(agregarListener);
				btnGuardar.addClickListener(modificarListener);
				btnGuardar.setCaption("Guardar cambios");
				btnNuevo.setCaption("Agregar Nuevo");
			}
		}
	}
	
	private void setParcela(int tipoAgregando)
	{
		LinkedList<String> lista = datos.getParcelas(usuario.getCURR(), tipoAgregando);
		if(!lista.isEmpty())
		{
			for(String parcela : lista)
				cmbParcela.addItem(parcela);
			cmbParcela.setFilteringMode(FilteringMode.CONTAINS);
		}
		else
			{
				Embedded imgErr = new Embedded();
				imgErr.setSource(new ThemeResource("img/err/respDirecto.png"));
				imgErr.setType(1);
				imgErr.setMimeType("image/png");
				mainLayout.removeAllComponents();
				mainLayout.addComponent(imgErr);
			}
	}

	private void mostrarTabla(boolean estado)
	{
		tblListado.setVisible(estado);
		verLDatos.setVisible(!estado);
	}
	
	private void elementosIdHabilitados(boolean habilitado)
	{
		if(usuario.getTipo() != usuario.ADMINISTRADOR)
			cmbParcela.setEnabled(habilitado);
		datIngreso.setEnabled(habilitado);
	}
	
	private void setValidaciones()
	{
		txtNombre.setMaxLength(40);
		txtApP.setMaxLength(20);
		txtApM.setMaxLength(20);
	}
	
	private boolean validar()
	{
		if(cmbParcela.getValue() == null)
			return notificar("Se requiere especificar la parcela a la que se asigna", cmbParcela);
		if(txtNombre.getValue().isEmpty())
			return notificar("Se requiere especificar el nombre", txtNombre);
		if(txtApP.getValue().isEmpty() || txtApM.getValue().isEmpty())
			return notificar("Se requiere especificar ambos apellidos", txtApP);
		if(datIngreso.getValue() == null)
			return notificar("Se requiere especificar la fecha de ingreso", datIngreso);
		
		return true;
	}
	
	@SuppressWarnings("rawtypes")
	private boolean notificar(String notificacion, AbstractField field)
	{
		Notification.show(notificacion, Notification.Type.ERROR_MESSAGE);
		field.focus();
		return false;
	}

	private LinkedList<Object> llenarListaDeParametros(LinkedList<Object> valores)
	{
		valores.add(((String)cmbParcela.getValue()).split(" ")[0]);
		valores.add(txtNombre.getValue());
		valores.add(txtApP.getValue());
		valores.add(txtApM.getValue());
		valores.add(chkCapacitacion.getValue());
		return valores;
	}
	
	private void setIdAnterior(String parcelaAnt)
	{
		parcelaAnterior = parcelaAnt;
		nombreAnterior = txtNombre.getValue() + txtApP.getValue() + txtApM.getValue();
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
		
		// panParcela
		panParcela = buildPanParcela();
		mainLayout.addComponent(panParcela);
		
		// tblListado
		tblListado = new Table();
		tblListado.setImmediate(false);
		tblListado.setWidth("535px");
		tblListado.setHeight("300px");
		mainLayout.addComponent(tblListado);
		
		// verLDatos
		verLDatos = buildVerLDatos();
		mainLayout.addComponent(verLDatos);
		
		// absLBotones
		absLBotones = buildAbsLBotones();
		mainLayout.addComponent(absLBotones);
		mainLayout.setComponentAlignment(absLBotones, new Alignment(10));
		
		return mainLayout;
	}

	@AutoGenerated
	private Panel buildPanParcela() {
		// common part: create layout
		panParcela = new Panel();
		panParcela.setCaption("Parcela");
		panParcela.setImmediate(false);
		panParcela.setWidth("331px");
		panParcela.setHeight("-1px");
		
		// absLParcela
		absLParcela = buildAbsLParcela();
		panParcela.setContent(absLParcela);
		
		return panParcela;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLParcela() {
		// common part: create layout
		absLParcela = new AbsoluteLayout();
		absLParcela.setImmediate(false);
		absLParcela.setWidth("100.0%");
		absLParcela.setHeight("45px");
		
		// cmbParcela
		cmbParcela = new ComboBox();
		cmbParcela.setImmediate(true);
		cmbParcela.setWidth("310px");
		cmbParcela.setHeight("25px");
		absLParcela.addComponent(cmbParcela, "top:10.0px;left:10.0px;");
		
		return absLParcela;
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
		
		// horLGeneralesDetalles
		horLGeneralesDetalles = buildHorLGeneralesDetalles();
		verLDatos.addComponent(horLGeneralesDetalles);
		
		return verLDatos;
	}

	@AutoGenerated
	private HorizontalLayout buildHorLGeneralesDetalles() {
		// common part: create layout
		horLGeneralesDetalles = new HorizontalLayout();
		horLGeneralesDetalles.setImmediate(false);
		horLGeneralesDetalles.setWidth("535px");
		horLGeneralesDetalles.setHeight("-1px");
		horLGeneralesDetalles.setMargin(false);
		
		// panGenerales
		panGenerales = buildPanGenerales();
		horLGeneralesDetalles.addComponent(panGenerales);
		
		// panDetalles
		panDetalles = buildPanDetalles();
		horLGeneralesDetalles.addComponent(panDetalles);
		horLGeneralesDetalles.setComponentAlignment(panDetalles, new Alignment(
				6));
		
		return horLGeneralesDetalles;
	}

	@AutoGenerated
	private Panel buildPanGenerales() {
		// common part: create layout
		panGenerales = new Panel();
		panGenerales.setCaption("Datos Generales");
		panGenerales.setImmediate(false);
		panGenerales.setWidth("301px");
		panGenerales.setHeight("-1px");
		
		// verLGenerales
		verLGenerales = buildVerLGenerales();
		panGenerales.setContent(verLGenerales);
		
		return panGenerales;
	}

	@AutoGenerated
	private VerticalLayout buildVerLGenerales() {
		// common part: create layout
		verLGenerales = new VerticalLayout();
		verLGenerales.setImmediate(false);
		verLGenerales.setWidth("100.0%");
		verLGenerales.setHeight("-1px");
		verLGenerales.setMargin(false);
		
		// absLGenerales1
		absLGenerales1 = buildAbsLGenerales1();
		verLGenerales.addComponent(absLGenerales1);
		
		// absLGenerales2
		absLGenerales2 = buildAbsLGenerales2();
		verLGenerales.addComponent(absLGenerales2);
		
		return verLGenerales;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLGenerales1() {
		// common part: create layout
		absLGenerales1 = new AbsoluteLayout();
		absLGenerales1.setImmediate(false);
		absLGenerales1.setWidth("100.0%");
		absLGenerales1.setHeight("55px");
		
		// txtNombre
		txtNombre = new TextField();
		txtNombre.setCaption("Nombre");
		txtNombre.setImmediate(false);
		txtNombre.setWidth("280px");
		txtNombre.setHeight("25px");
		txtNombre.setInvalidAllowed(false);
		absLGenerales1.addComponent(txtNombre, "top:28.0px;left:10.0px;");
		
		return absLGenerales1;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLGenerales2() {
		// common part: create layout
		absLGenerales2 = new AbsoluteLayout();
		absLGenerales2.setImmediate(false);
		absLGenerales2.setWidth("100.0%");
		absLGenerales2.setHeight("55px");
		
		// txtApP
		txtApP = new TextField();
		txtApP.setCaption("Apellido Paterno");
		txtApP.setImmediate(false);
		txtApP.setWidth("130px");
		txtApP.setHeight("25px");
		absLGenerales2.addComponent(txtApP, "top:18.0px;left:10.0px;");
		
		// txtApM
		txtApM = new TextField();
		txtApM.setCaption("Apellido Materno");
		txtApM.setImmediate(false);
		txtApM.setWidth("130px");
		txtApM.setHeight("25px");
		absLGenerales2.addComponent(txtApM, "top:18.0px;left:160.0px;");
		
		return absLGenerales2;
	}

	@AutoGenerated
	private Panel buildPanDetalles() {
		// common part: create layout
		panDetalles = new Panel();
		panDetalles.setCaption("Detalles del Programa");
		panDetalles.setImmediate(false);
		panDetalles.setWidth("221px");
		panDetalles.setHeight("-1px");
		
		// absLDetalles
		absLDetalles = buildAbsLDetalles();
		panDetalles.setContent(absLDetalles);
		
		return panDetalles;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLDetalles() {
		// common part: create layout
		absLDetalles = new AbsoluteLayout();
		absLDetalles.setImmediate(false);
		absLDetalles.setWidth("100.0%");
		absLDetalles.setHeight("110px");
		
		// datIngreso
		datIngreso = new TuningDateField();
		datIngreso.setCaption("Fecha de Ingreso al Programa");
		datIngreso.setImmediate(false);
		datIngreso.setWidth("200px");
		datIngreso.setHeight("25px");
		absLDetalles.addComponent(datIngreso, "top:28.0px;left:10.0px;");
		
		// chkCapacitacion
		chkCapacitacion = new CheckBox();
		chkCapacitacion.setCaption("Capacitaci�n Recibida");
		chkCapacitacion.setImmediate(false);
		chkCapacitacion.setWidth("-1px");
		chkCapacitacion.setHeight("-1px");
		absLDetalles.addComponent(chkCapacitacion, "top:70.0px;left:10.0px;");
		
		return absLDetalles;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLBotones() {
		// common part: create layout
		absLBotones = new AbsoluteLayout();
		absLBotones.setImmediate(false);
		absLBotones.setWidth("535px");
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