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
	
	public LinkedList<Object[]> getBusqueda(String columnas, String parametro, String busqueda)
	{
		return cons.getBusqueda(columnas, parametro, busqueda);
	}

	public int getContadorEstatal(int tipo, String edo)
	{
		return cons.getConteo("personas", "tipo", "estado", tipo, edo);
	}

	public boolean registrar(String entidad, LinkedList<Object> valores)
	{
		return ins.registrar(entidad, valores);
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