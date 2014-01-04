package com.saprigrat.data;

import java.util.LinkedList;

import com.vaadin.data.util.sqlcontainer.SQLContainer;


public class Datos
{
	InterfazConsulta cons;
	InterfazInsercion ins;
	public Datos()
	{
		cons = new InterfazConsulta();
		ins = new InterfazInsercion();
	}

	public String registrarPersona(LinkedList<Object> valores)
	{
		return ins.insertarRegistro("Persona", valores);
	}
	
	public LinkedList<Object[]> getTabla(int tabla, Object[] restricciones)
	{
		String[] tablas = {"Personas", "Parcelas", "Aprovechamiento", "Pozos", "Pruebas"};
		return cons.getTabla(tablas[tabla], restricciones);
	}
	
	public LinkedList<String> getTecnicos(String estado)
	{
		return cons.getResponsablesDirectos("Tecnicos", estado);
	}
	
	public LinkedList<String> getProductores(String estado)
	{
		return cons.getResponsablesDirectos("Productores", estado);
	}
	
	
	
	
	
	public LinkedList<Object[]> getBusqueda(String columnas, String parametro, String busqueda)
	{
		return cons.getBusqueda(columnas, parametro, busqueda);
	}
	
	public LinkedList<String> getEstados()
	{
		return cons.getEstados();
	}
	
	public LinkedList<Object> getMunicipios(String edo)
	{
		return cons.getValores("municipio", "municipios", "estado", edo);
	}
	
	public LinkedList<Object> getRegistro(String curr)
	{
		return cons.getValores("*", "personas", "CURR", curr);
	}

	public boolean modificar(String entidad, LinkedList<Object> valores)
	{
		return ins.modificar(entidad, "CURR", valores);
	}
	
	public SQLContainer getContainer(String tabla)
	{
		return cons.getSQLContainer(tabla);
	}
}