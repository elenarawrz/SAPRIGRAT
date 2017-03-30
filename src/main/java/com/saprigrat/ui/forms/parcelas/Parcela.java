package com.saprigrat.ui.forms.parcelas;

import java.util.Collection;

import org.vaadin.dialogs.ConfirmDialog;

import com.saprigrat.entities.Usuario;
import com.saprigrat.enums.TipoUsuario;
import com.saprigrat.ui.interfaces.FormParcela;
import com.saprigrat.ui.interfaces.Formulario;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutAction.ModifierKey;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class Parcela extends CustomComponent implements Formulario
{
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
	//region controles autogenerados
	
	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private HorizontalLayout horLBotones;
	@AutoGenerated
	private Button btnVolver;
	@AutoGenerated
	private Button btnNuevo;
	@AutoGenerated
	private TabSheet tabs;
	@AutoGenerated
	private ReporteVisita tabReporteVisita;
	@AutoGenerated
	private Riego tabRiego;
	@AutoGenerated
	private Regador tabRegador;
	@AutoGenerated
	private CaracterizacionParcela tabCaracterizacion;
	@AutoGenerated
	private GeneralesParcela tabGenerales;
	@AutoGenerated
	private VerticalLayout verLListado;
	@AutoGenerated
	private Label lblRowCount;
	@AutoGenerated
	private Table tblListado;
	@AutoGenerated
	private HorizontalLayout horLParcela;
	@AutoGenerated
	private Label lblNota;
	@AutoGenerated
	private Panel panParcela;
	@AutoGenerated
	private AbsoluteLayout absLParcela;
	@AutoGenerated
	private TextField txtParcela;
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
	private int idParcela;
	private boolean guardandoNuevo; 

	//region eventos
	private ItemClickListener llenarFormListener = new ItemClickListener()
			{
				@Override
				public void itemClick(ItemClickEvent event)
				{
					if(event.isDoubleClick())
					{
						idParcela = (Integer)event.getItem().getItemProperty("ID").getValue();
						txtParcela.setValue(event.getItem().getItemProperty("Núm. Cta. Padrón").getValue() + " / " +
											event.getItem().getItemProperty("Núm. Subcta. Padrón").getValue());
						if(cmbProductor.getValue() == null)
						{
							Collection<?> list = cmbProductor.getItemIds();
							for (Object x : list)
							{
								String item = x.toString();
								if(item.substring(item.indexOf("|") + 2).equals(event.getItem().getItemProperty("Usuario").getValue()))
								{
									cmbProductor.setValue(item);
									break;
								}
							}
						}
						panParcela.setVisible(true);
						setTipo(MODO_MODIFICAR);
					}
				}
			};
	private ValueChangeListener llenarTablaByResponsableListener = new ValueChangeListener()
			{
				@Override
				public void valueChange(ValueChangeEvent event)
				{
					String currResp = ((String)cmbResponsable.getValue()).split(" ")[0];
					if(!guardandoNuevo)
					{
						lblRowCount.setValue(u.llenarTabla(tblListado, datos.getParcelasByResponsable(currResp), "registro de parcela"));
						if(!verLListado.isVisible())
							setTipo(MODO_LISTA);
					}
					
					u.llenarCombo(cmbProductor, datos.getRespProductores(currResp));
					
					tabGenerales.setResponsable(currResp);
					tabCaracterizacion.setResponsable(currResp);
					tabRegador.setResponsable(currResp);
					tabRiego.setResponsable(currResp);
					tabReporteVisita.setResponsable(currResp);
				}
			};
	private ValueChangeListener llenarTablaByProductorListener = new ValueChangeListener()
			{
				@Override
				public void valueChange(ValueChangeEvent event)
				{
					if(cmbProductor.getValue() != null)
					{
						String currProd = ((String)cmbProductor.getValue()).split(" ")[0];
						if(!guardandoNuevo)
						{
							if(cmbProductor.getValue() != null)
								lblRowCount.setValue(u.llenarTabla(tblListado, datos.getParcelasByProductor(currProd), "registro de parcela"));
							if(!verLListado.isVisible())
								setTipo(MODO_LISTA);
						}
						
						tabGenerales.setProductor(currProd);
						tabCaracterizacion.setProductor(currProd);
						tabRegador.setProductor(currProd);
						tabRiego.setProductor(currProd);
						tabReporteVisita.setProductor(currProd);
					}
				}
			};
	private ValueChangeListener cambiarParcelaListener = new ValueChangeListener()
			{
				@Override
				public void valueChange(ValueChangeEvent event)
				{
					String padron = txtParcela.getValue().trim();
					if(!padron.isEmpty())
					{
						idParcela = datos.getIdParcela(padron);
						tabCaracterizacion.setParcela(idParcela, padron);
						tabRegador.setParcela(idParcela, padron);
						tabRiego.setParcela(idParcela, padron);
						tabReporteVisita.setParcela(idParcela, padron);
					}
				}
			};
	private ClickListener nuevoListener = new ClickListener()
			{
				@Override
				public void buttonClick(ClickEvent event)
				{
					setTipo(MODO_AGREGAR);
					guardandoNuevo = true;
				}
			};
	private ClickListener volverEliminarListener = new ClickListener()
			{
				@Override
				public void buttonClick(ClickEvent event)
				{
					if(btnVolver.getCaption().equals("Volver"))
					{
						setTipo(MODO_LISTA);
						guardandoNuevo = false;
						if(cmbProductor.getValue() != null)
							cmbProductor.setValue(cmbProductor.getValue());
						else
							cmbResponsable.setValue(cmbResponsable.getValue());
					}
					else
					{
						if(tblListado.getValue() != null)
						{
							final UI ui = event.getButton().getUI();
							ConfirmDialog.show( ui, "Atención:",
												"Está a punto de eliminar una parcela" +
												" y esta acción no se puede deshacer. ¿Desea continuar?", "Si", "No",
												new ConfirmDialog.Listener()
												{
													public void onClose(ConfirmDialog dialog)
													{
														if (dialog.isConfirmed())
														{
															int rowSel = (Integer)tblListado.getContainerProperty((Integer)tblListado.getValue(), "ID").getValue();
															String status = datos.eliminarParcela(rowSel, false);
															if(!status.isEmpty() && status.indexOf("err") == -1)
															{
																lblRowCount.setValue(u.llenarTabla(tblListado, cmbProductor.getValue() != null
																								   ? datos.getParcelasByProductor(((String)cmbProductor.getValue()).split(" ")[0])
																								   : datos.getParcelasByResponsable(((String)cmbResponsable.getValue()).split(" ")[0]), "parcela"));
																u.msjEliminado("La parcela");
															}
															else
															{
																ConfirmDialog.show( ui, "Atención:",
																					"La parcela contiene datos de cultivos y/o riegos, y si se elimina estos no podrán recuperarse.\n" +
																					"¿Seguro que desea continuar?", "Si", "No",
																					new ConfirmDialog.Listener()
																					{
																						public void onClose(ConfirmDialog dialog)
																						{
																							if (dialog.isConfirmed())
																							{
																								int rowSel = (Integer)tblListado.getContainerProperty((Integer)tblListado.getValue(), "ID").getValue();
																								String status = datos.eliminarParcela(rowSel, true);
																								if(!status.isEmpty() && status.indexOf("err") == -1)
																								{
																									lblRowCount.setValue(u.llenarTabla(tblListado, cmbProductor.getValue() != null
																																	   ? datos.getParcelasByProductor(((String)cmbProductor.getValue()).split(" ")[0])
																																	   : datos.getParcelasByResponsable(((String)cmbResponsable.getValue()).split(" ")[0]), "parcela"));
																									u.msjEliminado("La parcela");
																								}
																								else
																									u.msjNoEliminado("parcela");
																							}
																						}
																					});
															}
														}
													}
												});
						}
						else
							u.msjNoSeleccionEliminar("parcela");
					}
				}
			};
	//endregion
	
	public Parcela(Usuario user)
	{
		usuario = user;
	}

	@Override
	public void inicializar()
	{
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		//Variables
		guardandoNuevo = false;
		//Comboboxes
		u.setupCombo(cmbResponsable, null, "Busque por nombre o CURR.", FilteringMode.CONTAINS, false, true);
		u.setupCombo(cmbProductor, null, "Busque por nombre o CURR.", FilteringMode.CONTAINS, false, true);
		cmbResponsable.addValueChangeListener(llenarTablaByResponsableListener);
		cmbProductor.addValueChangeListener(llenarTablaByProductorListener);
		//Labels
		lblNota.setContentMode(ContentMode.HTML);
		//Textfields
		txtParcela.setEnabled(false);
		txtParcela.addValueChangeListener(cambiarParcelaListener);
		//Tables
		tblListado.setSelectable(true);
		tblListado.setSortEnabled(true);
		tblListado.setColumnCollapsingAllowed(true);
		tblListado.addItemClickListener(llenarFormListener);
		//Tabs
		inicializarTab(tabGenerales);
		inicializarTab(tabCaracterizacion);
		inicializarTab(tabRegador);
		inicializarTab(tabRiego);
		inicializarTab(tabReporteVisita);
		tabGenerales.setTxtParcela(txtParcela);
		//Buttons
		btnVolver.addClickListener(volverEliminarListener);
		btnNuevo.addClickListener(nuevoListener);
		//Inicializar en modo lista
		setTipo(MODO_LISTA);
	}
	
	@Override
	public void setTipo(int modoOperacion)
	{
		if (modoOperacion == MODO_LISTA)
		{
			panParcela.setVisible(false);
			verLListado.setVisible(true);
			tabs.setVisible(false);
			btnVolver.setClickShortcut(KeyCode.E, ModifierKey.ALT);
			btnVolver.setCaption("Eliminar");
			if(cmbResponsable.size() == 0)
				setResponsableTecnico(usuario.getTipo());
		}
		else
		{
			btnVolver.setClickShortcut(KeyCode.V, ModifierKey.ALT);
			btnVolver.setCaption("Volver");
			String padron = "";
			if (modoOperacion == MODO_AGREGAR)
			{
				idParcela = 0;
				txtParcela.setValue("");
				panParcela.setVisible(false);
				
				tabGenerales.setupNuevo();
				tabCaracterizacion.setupNuevo();
				
				tabs.setSelectedTab(tabGenerales);
			}
			else
			{
				padron = txtParcela.getValue().trim();
			}
			
			tabGenerales.setParcela(idParcela, padron);
			tabCaracterizacion.setParcela(idParcela, padron);
			tabRegador.setParcela(idParcela, padron);
			tabRiego.setParcela(idParcela, padron);
			tabReporteVisita.setParcela(idParcela, padron);
			
			verLListado.setVisible(false);
			tabs.setVisible(true);
		}
	}

	private void inicializarTab(FormParcela tab)
	{
		tab.init(usuario);
		tab.inicializar();
		tab.quitarListeners();
	}

	private void setResponsableTecnico(int tipoAgregando)
	{
		if(tipoAgregando != usuario.RESPONSABLE)
			u.llenarCombo(cmbResponsable, datos.getRespTecnicos(usuario.getCURR(), tipoAgregando, usuario.RESPONSABLE));
		else
			u.llenarCombo(cmbResponsable, usuario.getCURR() + " " + usuario.getNombre() + " " +
										  usuario.getApPaterno() + " " + usuario.getApMaterno());
		if(cmbResponsable.size() == 0)
		{
			mainLayout.removeAllComponents();
			u.notificar("Imposible registrar parcela, se requiere que exista al menos un " + TipoUsuario.PRODUCTOR + " a quien pueda ser asignada", null, u.MSJ_ERROR);
		}
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
		
		// horLParcela
		horLParcela = buildHorLParcela();
		mainLayout.addComponent(horLParcela);
		
		// verLListado
		verLListado = buildVerLListado();
		mainLayout.addComponent(verLListado);
		
		// tabs
		tabs = buildTabs();
		mainLayout.addComponent(tabs);
		
		// horLBotones
		horLBotones = buildHorLBotones();
		mainLayout.addComponent(horLBotones);
		
		return mainLayout;
	}

	@AutoGenerated
	private HorizontalLayout buildHorLRespProd() {
		// common part: create layout
		horLRespProd = new HorizontalLayout();
		horLRespProd.setImmediate(false);
		horLRespProd.setWidth("100.0%");
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
	private HorizontalLayout buildHorLParcela() {
		// common part: create layout
		horLParcela = new HorizontalLayout();
		horLParcela.setImmediate(false);
		horLParcela.setWidth("100.0%");
		horLParcela.setHeight("-1px");
		horLParcela.setMargin(false);
		
		// panParcela
		panParcela = buildPanParcela();
		horLParcela.addComponent(panParcela);
		
		// lblNota
		lblNota = new Label();
		lblNota.setImmediate(false);
		lblNota.setWidth("-1px");
		lblNota.setHeight("-1px");
		lblNota.setValue("<i>NOTA: El filtrado por usuario es opcional</i>");
		horLParcela.addComponent(lblNota);
		horLParcela.setComponentAlignment(lblNota, new Alignment(6));
		
		return horLParcela;
	}

	@AutoGenerated
	private Panel buildPanParcela() {
		// common part: create layout
		panParcela = new Panel();
		panParcela.setCaption("Parcela (Núm. de Cta. / Subcta. de Padrón)");
		panParcela.setImmediate(false);
		panParcela.setWidth("396px");
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
		
		// txtParcela
		txtParcela = new TextField();
		txtParcela.setImmediate(false);
		txtParcela.setWidth("375px");
		txtParcela.setHeight("25px");
		absLParcela.addComponent(txtParcela, "top:10.0px;left:10.0px;");
		
		return absLParcela;
	}

	@AutoGenerated
	private VerticalLayout buildVerLListado() {
		// common part: create layout
		verLListado = new VerticalLayout();
		verLListado.setImmediate(false);
		verLListado.setWidth("801px");
		verLListado.setHeight("-1px");
		verLListado.setMargin(false);
		verLListado.setSpacing(true);
		
		// tblListado
		tblListado = new Table();
		tblListado
				.setCaption("* Dar doble click sobre un elemento para modificarlo");
		tblListado.setImmediate(false);
		tblListado.setWidth("801px");
		tblListado.setHeight("300px");
		verLListado.addComponent(tblListado);
		
		// lblRowCount
		lblRowCount = new Label();
		lblRowCount.setImmediate(false);
		lblRowCount.setWidth("-1px");
		lblRowCount.setHeight("-1px");
		lblRowCount.setValue("0 resultados encontrados");
		verLListado.addComponent(lblRowCount);
		
		return verLListado;
	}

	@AutoGenerated
	private TabSheet buildTabs() {
		// common part: create layout
		tabs = new TabSheet();
		tabs.setImmediate(true);
		tabs.setWidth("-1px");
		tabs.setHeight("-1px");
		
		// tabGenerales
		tabGenerales = new GeneralesParcela();
		tabGenerales.setImmediate(false);
		tabGenerales.setWidth("-1px");
		tabGenerales.setHeight("-1px");
		tabs.addTab(tabGenerales, "Datos Generales", null);
		
		// tabCaracterizacion
		tabCaracterizacion = new CaracterizacionParcela();
		tabCaracterizacion.setImmediate(false);
		tabCaracterizacion.setWidth("-1px");
		tabCaracterizacion.setHeight("-1px");
		tabs.addTab(tabCaracterizacion, "Caracterización", null);
		
		// tabRegador
		tabRegador = new Regador();
		tabRegador.setImmediate(false);
		tabRegador.setWidth("-1px");
		tabRegador.setHeight("-1px");
		tabs.addTab(tabRegador, "Regadores", null);
		
		// tabRiego
		tabRiego = new Riego();
		tabRiego.setImmediate(false);
		tabRiego.setWidth("-1px");
		tabRiego.setHeight("-1px");
		tabs.addTab(tabRiego, "Riegos", null);
		
		// tabReporteVisita
		tabReporteVisita = new ReporteVisita();
		tabReporteVisita.setImmediate(false);
		tabReporteVisita.setWidth("-1px");
		tabReporteVisita.setHeight("-1px");
		tabs.addTab(tabReporteVisita, "Reportes de Visita", null);
		
		return tabs;
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