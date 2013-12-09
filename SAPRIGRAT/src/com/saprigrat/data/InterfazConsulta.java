package com.saprigrat.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.vaadin.data.util.sqlcontainer.SQLContainer;

public class InterfazConsulta
{
	private ConexionBD conexion;
	private Connection con;
	public InterfazConsulta()
	{
		conexion = new ConexionBD();
		con = conexion.openConexion();
	}
	
	public LinkedList<String> getEstados()
	{
		String sql = "SELECT DISTINCT estado FROM municipios ORDER BY estado";
		ResultSet rs = conexion.querySelect(con, sql);
		
		LinkedList<String> edos = new LinkedList<String>();
		try
		{
			while (rs.next())
				edos.add(rs.getString(1));
		}
		catch (SQLException e)
		{
			System.out.println("Error al obtener estados.");
			e.printStackTrace();
		}
		return edos;
	}
	
	public LinkedList<Object> getValores(String columnas, String entidad, String parametro, String valor)
	{
		String sql = "SELECT " + columnas + " FROM " + entidad + " WHERE " + parametro + "=?";
		ResultSet rs = conexion.querySelect(con, sql, valor, 1);
		
		LinkedList<Object> vals = new LinkedList<Object>();
		try
		{
			while (rs.next())
			{
				int cols = rs.getMetaData().getColumnCount();
				for(int i = 1; i<=cols; i++)
					vals.add(rs.getObject(i));
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error al obtener " + entidad + ".");
			e.printStackTrace();
		}
		return vals;
	}
	
	public LinkedList<Object[]> getBusqueda(String columnas, String parametro, String busqueda)
	{
		if(columnas.indexOf("Nombre") != -1)
			columnas += "apPat,apMat";
		String sql = "SELECT " + columnas + " FROM personas WHERE LOWER(" + parametro + ") LIKE LOWER(?)";
		int rep = 1;
		if(parametro.equals("Nombre"))
		{
			sql += " OR LOWER(apPat) LIKE LOWER(?) OR LOWER(apMat) LIKE LOWER(?)";
			rep = 3;
		}
		ResultSet rs = conexion.querySelect(con, sql, "%" + busqueda + "%", rep);
		
		LinkedList<Object[]> resultado = new LinkedList<Object[]>();
		try
		{
			int cols = columnas.split(",").length;
			while (rs.next())
			{
				Object[] res = new Object[cols];
				for(int i=0; i<cols; i++)
					res[i] = rs.getObject(i + 1);
				resultado.add(res);
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error al obtener resultados de búsqueda.");
			e.printStackTrace();
		}
		return resultado;
	}

	public int getConteo(String entidad, String parametro1, String parametro2, int valorInt, String valorStr)
	{
		String sql = "SELECT COUNT(*) FROM " + entidad + " WHERE " + parametro1 + "=?" + " AND " + parametro2 + "=?";
		ResultSet rs = conexion.querySelect(con, sql, valorInt, valorStr);
		int cont = 0;
		try
		{
			rs.next();
			cont = rs.getInt(1);
		}
		catch (SQLException e)
		{
			System.out.println("Error al realizar conteo.");
			e.printStackTrace();
		}
		return cont;
	}
	
	public SQLContainer getSQLContainer(String tabla)
	{
		return conexion.getSQLContainer(tabla);
	}
}