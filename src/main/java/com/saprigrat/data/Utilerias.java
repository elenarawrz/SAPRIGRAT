package com.saprigrat.data;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.LinkedList;

import org.joda.time.LocalDate;

import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;

public class Utilerias
{
	public void setupCombo(ComboBox cmb, LinkedList<String> lista, String placeholder, FilteringMode filtrado, boolean permiteNulo)
	{
		if(lista != null)
			llenarCombo(cmb, lista);
		if(placeholder != null)
			cmb.setInputPrompt(placeholder);
		if(filtrado != null)
			cmb.setFilteringMode(filtrado);
		cmb.setNullSelectionAllowed(permiteNulo);
	}
	
	public void llenarCombo(ComboBox cmb, LinkedList<String>elementos)
	{
		cmb.removeAllItems();
		for(String elemento : elementos)
			cmb.addItem(elemento);
	}
	
	public Date fechaUtilToSQL(java.util.Date fechaUtil)
	{
		if(fechaUtil == null)
			return null;
		else
			return new Date(fechaUtil.getTime());
	}
	
	public java.util.Date fechaSQLToUtil(Date fechaSQL)
	{
		if(fechaSQL == null)
			return null;
		else
			return new java.util.Date(fechaSQL.getTime());
	}
	
	public LocalDate fechaSQLToLocalDate(Date fecha)
	{
		if(fecha == null)
			return null;
		else
			return new LocalDate(fecha.getTime());
	}
	
	public void llenarTabla(Table tbl, LinkedList<Object[]> rows)
	{
		tbl.removeAllItems();
		setHeaders(tbl, (String[]) rows.remove());
		if(!rows.isEmpty())
			while(!rows.isEmpty())
				tbl.addItem(rows.remove(), null);
		else
			Notification.show("No se encontró ningún resultado.", Notification.Type.HUMANIZED_MESSAGE);
		tbl.setColumnCollapsed("ID",true);
	}
													
	private void setHeaders(Table tbl, String[] encabezado)
	{
		for(int i=0; i<encabezado.length; i++)
			tbl.addContainerProperty(encabezado[i], Object.class, null);
	}
	
	public RegexpValidator getRegExDecimal()
	{
		return new RegexpValidator("^[0-9]+(\\.[0-9]+)?$", "Debe ser un valor numérico");
	}
	
	public RegexpValidator getRegExEmail()
	{
		return new RegexpValidator( "^[0-9a-zA-Z\\._-]+@[0-9a-zA-Z_-]+(\\.[0-9a-zA-Z_-]+)+$",
									"Debe cumplir con el formato 'usuario@dominio.com'");
	}
	
	public RegexpValidator getRegExCP()
	{
		return new RegexpValidator("^[0-9][0-9][0-9][0-9][0-9]$", "Debe ser un número de 5 dígitos");
	}
	
	public RegexpValidator getRegExPass()
	{
		return new RegexpValidator("^[0-9a-zA-Z]+$", "La contraseña solo puede contener números y letras");
	}
	
	public String setDecimalFormat(String formato, int numero)
	{
		return new DecimalFormat(formato).format(numero);
	}
}