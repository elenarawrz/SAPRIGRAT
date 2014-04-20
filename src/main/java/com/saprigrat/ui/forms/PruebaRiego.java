package com.saprigrat.ui.forms;

import java.util.LinkedList;

import com.saprigrat.data.Datos;
import com.saprigrat.data.Usuario;
import com.saprigrat.ui.components.pruebaderiego.Aforo;
import com.saprigrat.ui.components.pruebaderiego.Caracteristicas;
import com.saprigrat.ui.components.pruebaderiego.LecturaInicial;
import com.saprigrat.ui.interfaces.Formulario;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutAction.ModifierKey;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

@SuppressWarnings("serial")
public class PruebaRiego extends CustomComponent implements Formulario
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
	private TabSheet tabsPrueba;
	@AutoGenerated
	private Aforo tabAforo;
	@AutoGenerated
	private Caracteristicas tabCaracteristicas;
	@AutoGenerated
	private LecturaInicial tabLectura;
	@AutoGenerated
	private HorizontalLayout horLParReg;
	@AutoGenerated
	private Panel panRegador;
	@AutoGenerated
	private AbsoluteLayout absLRegador;
	@AutoGenerated
	private ComboBox cmbRegador;
	@AutoGenerated
	private Panel panParcela;
	@AutoGenerated
	private AbsoluteLayout absLParcela;
	@AutoGenerated
	private ComboBox cmbParcela;
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
	private Usuario usuario;
	private Datos datos;
	private int idPrueba;
	
	private final int SECCION_GENERAL = 0,
					  SECCION_LECTURA_INICIAL = 1,
					  SECCION_CARACTERISTICAS = 2,
					  SECCION_AFORO = 3;
	
	
	private ClickListener agregarListener = new ClickListener()
											{
												@Override
												public void buttonClick(ClickEvent event)
												{
													if(validar())
													{
														LinkedList<Object> valores = llenarListaDeParametros();
														valores.add(0, cmbParcela.getValue());
														
														String status = datos.registrarPruebaRiego(valores);
														if(!status.isEmpty())
														{
															Notification.show("Registro añadido correctamente.", Notification.Type.TRAY_NOTIFICATION);
															//Se cambia a modo de modificación
															setTipo(MODO_MODIFICAR);
															idPrueba = Integer.parseInt(status);
														}
														else
															Notification.show("Error al añadir registro, comuníquese con el administrador.", Notification.Type.ERROR_MESSAGE);
													}
												}
											};
	private ClickListener modificarListener=new ClickListener()
											{
												@Override
												public void buttonClick(ClickEvent event)
												{
													if(validar())
													{
														LinkedList<Object> valores = llenarListaDeParametros();
														valores.add(0, idPrueba);
														
														if(!datos.modificarPruebaRiego(valores).isEmpty())
															Notification.show("Registro modificado correctamente.", Notification.Type.TRAY_NOTIFICATION);
														else
															Notification.show("Error al modificar registro, comuníquese con el administrador.", Notification.Type.ERROR_MESSAGE);
													}
												}
											};
	private ItemClickListener llenarFormListener =  new ItemClickListener()
													{
														@Override
														public void itemClick(ItemClickEvent event)
														{
															if(event.isDoubleClick())
															{
																idPrueba = (Integer)event.getItem().getItemProperty("ID").getValue();
																LinkedList<Object> valores = datos.getPruebaRiego(idPrueba, SECCION_GENERAL);
																if(!valores.isEmpty())
																{
																	setTipo(MODO_MODIFICAR);
																	
																	llenarLista(datos.getRespProductores(((String)cmbResponsable.getValue()).split(" ")[0]), cmbProductor);
																	cmbProductor.setValue(valores.remove());
																	llenarLista(datos.getCtasPadron(((String)cmbProductor.getValue()).split(" ")[0]), cmbParcela);
																	cmbParcela.setValue(valores.remove());
																	llenarLista(datos.getRespRegadores((String)cmbParcela.getValue()), cmbRegador);
																	cmbRegador.setValue(valores.remove());
																	
																	tabLectura.setValores(datos.getPruebaRiego(idPrueba, SECCION_LECTURA_INICIAL));
																	tabCaracteristicas.setValores(datos.getPruebaRiego(idPrueba, SECCION_CARACTERISTICAS));
																	tabAforo.setValores(datos.getPruebaRiego(idPrueba, SECCION_AFORO));
																}
																else
																	Notification.show("Error al obtener el registro, comuníquese con el administrador.", Notification.Type.ERROR_MESSAGE);
															}
														}
													};
	private ValueChangeListener llenarTablaListener=new ValueChangeListener()
													{
														@Override
														public void valueChange(ValueChangeEvent event)
														{
															tblListado.removeAllItems();
															//Obtener la lista
															LinkedList<Object[]> resultados = datos.getPruebasRiego(((String)cmbResponsable.getValue()).split(" ")[0]);
															//Crear encabezados de columnas
															setHeaders((String[]) resultados.remove());
															//Llenar la lista o mostrar un mensaje dependiendo de si se encontraron elementos o no
															if(!resultados.isEmpty())
																while(!resultados.isEmpty())
																	tblListado.addItem(resultados.remove(), null);
															else
																Notification.show("No se encontró ningún resultado.", Notification.Type.HUMANIZED_MESSAGE);
															tblListado.setColumnCollapsed("ID",true);
														}
												
														private void setHeaders(String[] encabezado)
														{
															for(int i=0; i<encabezado.length; i++)
																tblListado.addContainerProperty(encabezado[i], Object.class, null);
														}
													};
	private ClickListener nuevoListener=new ClickListener()
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
	private ValueChangeListener llenarProdsListener=new ValueChangeListener()
													{
														@Override
														public void valueChange(ValueChangeEvent event)
														{
															cmbParcela.removeAllItems();
															cmbProductor.removeValueChangeListener(llenarParcelasListener);
															llenarLista(datos.getRespProductores(((String)cmbResponsable.getValue()).split(" ")[0]), cmbProductor);
															if(cmbProductor.size() != 0)
																cmbProductor.addValueChangeListener(llenarParcelasListener);
															else
																Notification.show("No se podrá agregar el registro ya que este técnico no tiene asignado a ningún productor.", Notification.Type.ERROR_MESSAGE);
														}
													};
	private ValueChangeListener llenarParcelasListener =new ValueChangeListener()
														{
															@Override
															public void valueChange(ValueChangeEvent event)
															{
																llenarLista(datos.getCtasPadron(((String)cmbProductor.getValue()).split(" ")[0]), cmbParcela);
																if(cmbParcela.size() == 0)
																	Notification.show("No se podrá agregar el registro ya que este productor no está relacionado con ninguna parcela.", Notification.Type.ERROR_MESSAGE);
															}
														};
	private ValueChangeListener llenarRegadoresListener=new ValueChangeListener()
														{
															@Override
															public void valueChange(ValueChangeEvent event)
															{
																llenarLista(datos.getRespRegadores((String)cmbParcela.getValue()), cmbRegador);
																if(cmbParcela.size() == 0)
																	Notification.show("No se podrá agregar el registro ya que este productor no está relacionado con ningún regador.", Notification.Type.ERROR_MESSAGE);
															}
														};

	public PruebaRiego(Usuario user)
	{
		usuario = user;
		inicializar();
	}
	
	@Override
	public void inicializar()
	{
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		//Variables
		datos = new Datos();
		//Comboboxes
		setupCombo(cmbResponsable);
		setupCombo(cmbProductor);
		cmbProductor.addValueChangeListener(llenarParcelasListener);
		cmbParcela.setNullSelectionAllowed(false);
		cmbParcela.setInputPrompt("Busque por numero de cuenta de padrón.");
		cmbParcela.addValueChangeListener(llenarRegadoresListener);
		cmbRegador.setNullSelectionAllowed(false);
		cmbRegador.setFilteringMode(FilteringMode.CONTAINS);
		cmbRegador.setInputPrompt("Busque por nombre");
		//Botones
		btnNuevo.addClickListener(nuevoListener);
		btnVolver.addClickListener(listadoListener);
		//Tablas
		tblListado.setSelectable(true);
		tblListado.setSortEnabled(true);
		tblListado.setColumnCollapsingAllowed(true);
		tblListado.addItemClickListener(llenarFormListener);
		
		//Inicializar en modo lista
		setTipo(MODO_LISTA);
	}

	@Override
	public void setTipo(int modoOperacion)
	{
		btnNuevo.setClickShortcut(KeyCode.N, ModifierKey.ALT);
		if(modoOperacion == MODO_LISTA)
		{
			cmbResponsable.addValueChangeListener(llenarTablaListener);
			cmbResponsable.removeValueChangeListener(llenarProdsListener);
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
			cmbResponsable.removeValueChangeListener(llenarTablaListener);
			cmbResponsable.addValueChangeListener(llenarProdsListener);
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

	private void setResponsableTecnico(int tipoAgregando)
	{
		if(tipoAgregando == usuario.RESPONSABLE)
		{
			String item = usuario.getCURR() + " " + usuario.getNombre() + " " +
						  usuario.getApPaterno() + " " + usuario.getApMaterno();
			cmbResponsable.addItem(item);
			cmbResponsable.select(item);
			cmbResponsable.setEnabled(false);
		}
		else
		{
			llenarLista(datos.getRespTecnicos(usuario.getCURR(), tipoAgregando, usuario.RESPONSABLE), cmbResponsable);
			if(cmbResponsable.size() == 0)
			{
				mainLayout.removeAllComponents();
				Notification.show("Imposible registrar. Se requiere que exista en la base de datos al menos un productor a quien pueda ser asignada esta parcela.", Notification.Type.ERROR_MESSAGE);
			}
		}
	}

	private void mostrarTabla(boolean estado)
	{
		panProductor.setVisible(!estado);
		tblListado.setVisible(estado);
		verLDatos.setVisible(!estado);
	}
	
	private void elementosIdHabilitados(boolean habilitado)
	{
		cmbResponsable.setEnabled(habilitado);
		cmbProductor.setEnabled(habilitado);
		cmbParcela.setEnabled(habilitado);
	}
	
	private boolean validar()
	{
		if(cmbResponsable.getValue() == null)
			return notificar("Se requiere especificar el responsable técnico", cmbResponsable);
		if(cmbProductor.getValue() == null)
			return notificar("Se requiere especificar el usuario/productor", cmbProductor);
		if(cmbParcela.getValue() == null)
			return notificar("Se requiere especificar la parcela a la que se aplica la prueba de riego", cmbParcela);
		if(cmbRegador.getValue() == null)
			return notificar("Se requiere especificar el regador", cmbRegador);
		if(tabLectura.getValores().isEmpty() || tabCaracteristicas.getValores().isEmpty() || tabAforo.getValores().isEmpty())
			return false;
		
		return true;
	}
	
	@SuppressWarnings("rawtypes")
	private boolean notificar(String notificacion, AbstractField field)
	{
		Notification.show(notificacion, Notification.Type.ERROR_MESSAGE);
		field.focus();
		return false;
	}

	private LinkedList<Object> llenarListaDeParametros()
	{
		LinkedList<Object> valores = new LinkedList<Object>();
		valores.add(cmbParcela.getValue());
		valores.add(Integer.parseInt(((String)cmbRegador.getValue()).split(" ")[0]));
		valores.addAll(tabLectura.getValores());
		valores.addAll(tabCaracteristicas.getValores());
		valores.addAll(tabAforo.getValores());
		
		return valores;
	}
	
	private void llenarLista(LinkedList<String>elementos, ComboBox cmb)
	{
		cmb.removeAllItems();
		for(String elemento : elementos)
			cmb.addItem(elemento);
	}

	private void setupCombo(ComboBox cmb)
	{
		cmb.setNullSelectionAllowed(false);
		cmb.setFilteringMode(FilteringMode.CONTAINS);
		cmb.setInputPrompt("Busque por nombre o CURR.");
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
		
		// tblListado
		tblListado = new Table();
		tblListado
				.setCaption("* Dar doble click sobre un elemento para modificarlo");
		tblListado.setImmediate(false);
		tblListado.setWidth("601px");
		tblListado.setHeight("300px");
		mainLayout.addComponent(tblListado);
		
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
		horLRespProd.setWidth("601px");
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
		panResponsable.setWidth("296px");
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
		cmbResponsable.setWidth("275px");
		cmbResponsable.setHeight("25px");
		absLResponsable.addComponent(cmbResponsable, "top:10.0px;left:10.0px;");
		
		return absLResponsable;
	}

	@AutoGenerated
	private Panel buildPanProductor() {
		// common part: create layout
		panProductor = new Panel();
		panProductor.setCaption("Usuario/Productor");
		panProductor.setImmediate(false);
		panProductor.setWidth("296px");
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
		cmbProductor.setWidth("275px");
		cmbProductor.setHeight("25px");
		absLProductor.addComponent(cmbProductor, "top:10.0px;left:10.0px;");
		
		return absLProductor;
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
		
		// horLParReg
		horLParReg = buildHorLParReg();
		verLDatos.addComponent(horLParReg);
		
		// tabsPrueba
		tabsPrueba = buildTabsPrueba();
		verLDatos.addComponent(tabsPrueba);
		
		return verLDatos;
	}

	@AutoGenerated
	private HorizontalLayout buildHorLParReg() {
		// common part: create layout
		horLParReg = new HorizontalLayout();
		horLParReg.setImmediate(false);
		horLParReg.setWidth("601px");
		horLParReg.setHeight("-1px");
		horLParReg.setMargin(false);
		
		// panParcela
		panParcela = buildPanParcela();
		horLParReg.addComponent(panParcela);
		
		// panRegador
		panRegador = buildPanRegador();
		horLParReg.addComponent(panRegador);
		horLParReg.setComponentAlignment(panRegador, new Alignment(6));
		
		return horLParReg;
	}

	@AutoGenerated
	private Panel buildPanParcela() {
		// common part: create layout
		panParcela = new Panel();
		panParcela.setCaption("Parcela");
		panParcela.setImmediate(false);
		panParcela.setWidth("296px");
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
		cmbParcela.setWidth("275px");
		cmbParcela.setHeight("25px");
		absLParcela.addComponent(cmbParcela, "top:10.0px;left:10.0px;");
		
		return absLParcela;
	}

	@AutoGenerated
	private Panel buildPanRegador() {
		// common part: create layout
		panRegador = new Panel();
		panRegador.setCaption("Regador");
		panRegador.setImmediate(false);
		panRegador.setWidth("296px");
		panRegador.setHeight("-1px");
		
		// absLRegador
		absLRegador = buildAbsLRegador();
		panRegador.setContent(absLRegador);
		
		return panRegador;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLRegador() {
		// common part: create layout
		absLRegador = new AbsoluteLayout();
		absLRegador.setImmediate(false);
		absLRegador.setWidth("100.0%");
		absLRegador.setHeight("45px");
		
		// cmbRegador
		cmbRegador = new ComboBox();
		cmbRegador.setImmediate(true);
		cmbRegador.setWidth("275px");
		cmbRegador.setHeight("25px");
		absLRegador.addComponent(cmbRegador, "top:10.0px;left:10.0px;");
		
		return absLRegador;
	}

	@AutoGenerated
	private TabSheet buildTabsPrueba() {
		// common part: create layout
		tabsPrueba = new TabSheet();
		tabsPrueba.setImmediate(true);
		tabsPrueba.setWidth("601px");
		tabsPrueba.setHeight("-1px");
		
		// tabLectura
		tabLectura = new LecturaInicial();
		tabLectura.setImmediate(false);
		tabLectura.setWidth("-1px");
		tabLectura.setHeight("-1px");
		tabsPrueba.addTab(tabLectura, "Lectura Inicial", null);
		
		// tabCaracteristicas
		tabCaracteristicas = new Caracteristicas();
		tabCaracteristicas.setImmediate(false);
		tabCaracteristicas.setWidth("-1px");
		tabCaracteristicas.setHeight("-1px");
		tabsPrueba.addTab(tabCaracteristicas, "Características", null);
		
		// tabAforo
		tabAforo = new Aforo();
		tabAforo.setImmediate(false);
		tabAforo.setWidth("-1px");
		tabAforo.setHeight("-1px");
		tabsPrueba.addTab(tabAforo, "Aforo", null);
		
		return tabsPrueba;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLBotones() {
		// common part: create layout
		absLBotones = new AbsoluteLayout();
		absLBotones.setImmediate(false);
		absLBotones.setWidth("601px");
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
