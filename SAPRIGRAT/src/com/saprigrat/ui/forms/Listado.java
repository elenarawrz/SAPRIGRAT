package com.saprigrat.ui.forms;

import java.util.LinkedList;

import com.saprigrat.data.Datos;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class Listado extends CustomComponent
{
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Panel panResultados;
	@AutoGenerated
	private VerticalLayout verLResultados;
	@AutoGenerated
	private Table tblResultados;
	@AutoGenerated
	private Label lblResultados;
	private Datos datos;
	private AbstractField<String> campo;
	public int VER = 1;
	public int MODIFICAR = 2;
	
	public Listado(int tabla, Object[] restricciones)
	{
		inicializar(tabla, restricciones);
	}

	public void inicializar(int tabla, Object[] restricciones)
	{
		buildMainLayout();
		setCompositionRoot(mainLayout);
		datos = new Datos();
		//Obtener la lista
		LinkedList<Object[]> resultados = datos.getTabla(tabla, restricciones);
		//Crear encabezados de columnas
		if(resultados.size() > 1)
		{
			setHeaders((String[]) resultados.remove(), resultados.peek());
			while(!resultados.isEmpty())
				tblResultados.addItem(resultados.remove(), null);
			
			tblResultados.setSelectable(true);
			lblResultados.setValue("Resultados: " + tblResultados.size());
		}
	}

	private void setHeaders(String[] header, Object[] tipo)
	{
		for(int i=0; i<header.length; i++)
			if(tipo[i]!=null)
				tblResultados.addContainerProperty(header[i], tipo[i].getClass(), null);
	}

	public void setTipo(int tipo)
	{
		if(tipo == MODIFICAR)
			tblResultados.addItemClickListener(clickListener());
	}

	private ItemClickListener clickListener()
	{
		return  new ItemClickListener()
				{
					@Override
					public void itemClick(ItemClickEvent event)
					{
						if(event.isDoubleClick())
						{
							String currSel = tblResultados.getItemCaption(event.getItem());
							currSel = currSel.substring(0, currSel.indexOf(' '));
							campo.setValue(currSel);
						}
					}
				};
	}
	
	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("-1px");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(true);
		
		// top-level component properties
		setWidth("-1px");
		setHeight("-1px");
		
		// lblResultados
		lblResultados = new Label();
		lblResultados.setImmediate(false);
		lblResultados.setWidth("-1px");
		lblResultados.setHeight("-1px");
		lblResultados.setValue("0 Registros");
		mainLayout.addComponent(lblResultados);
		
		// panResultados
		panResultados = buildPanResultados();
		mainLayout.addComponent(panResultados);
		
		return mainLayout;
	}

	@AutoGenerated
	private Panel buildPanResultados() {
		// common part: create layout
		panResultados = new Panel();
		panResultados.setImmediate(false);
		panResultados.setWidth("601px");
		panResultados.setHeight("-1px");
		
		// verLResultados
		verLResultados = buildVerLResultados();
		panResultados.setContent(verLResultados);
		
		return panResultados;
	}

	@AutoGenerated
	private VerticalLayout buildVerLResultados() {
		// common part: create layout
		verLResultados = new VerticalLayout();
		verLResultados.setImmediate(false);
		verLResultados.setWidth("100.0%");
		verLResultados.setHeight("-1px");
		verLResultados.setMargin(false);
		
		// tblResultados
		tblResultados = new Table();
		tblResultados.setImmediate(false);
		tblResultados.setWidth("100.0%");
		tblResultados.setHeight("300px");
		verLResultados.addComponent(tblResultados);
		
		return verLResultados;
	}
}
