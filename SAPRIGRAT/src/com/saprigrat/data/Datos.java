package com.saprigrat.data;

import java.util.LinkedList;

public class Datos
{
	public LinkedList<String> getEstados()
	{
		return new InterfazConsulta().getEstados();
	}
	
	public LinkedList<String> getMunicipios(String edo)
	{
		return new InterfazConsulta().getMunicipios(edo);
	}
	
	public LinkedList<String[]> getBusqueda(String columnas, String parametro, String busqueda)
	{
		return new InterfazConsulta().getBusqueda(columnas, parametro, busqueda);
	}

	public int getContadorEstatal(int tipo, String edo)
	{
		return new InterfazConsulta().getConteo("personas", "tipo", "estado", tipo, edo);
	}

	public boolean registrar(String entidad, LinkedList<Object> valores)
	{
		return new InterfazInsercion().registrar(entidad, valores);
	}

	public boolean modificar(String entidad, LinkedList<Object> valores)
	{
		return new InterfazInsercion().modificar(entidad, "CURR", valores);
	}
}