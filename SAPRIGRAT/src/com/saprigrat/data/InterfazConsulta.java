package com.saprigrat.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import org.apache.commons.lang.StringUtils;

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
		String sql = "SELECT DISTINCT estado FROM \"infoAdministrativa\".municipios ORDER BY estado";
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
	
	public LinkedList<String> getMunicipios(String edo)
	{
		String sql = "SELECT municipio FROM \"infoAdministrativa\".municipios WHERE estado=?";
		ResultSet rs = conexion.querySelect(con, sql, edo);
		
		LinkedList<String> mpios = new LinkedList<String>();
		try
		{
			while (rs.next())
				mpios.add(rs.getString(1));
		}
		catch (SQLException e)
		{
			System.out.println("Error al obtener estados.");
			e.printStackTrace();
		}
		return mpios;
	}
	
	public LinkedList<String[]> getBusqueda(String columnas, String parametro, String busqueda)
	{
		if(columnas.indexOf("Nombre") != -1)
			columnas += ",apPat,apMat";
		String sql = "SELECT " + columnas + " FROM \"infoAdministrativa\".participantes WHERE " + parametro + " LIKE ?";
		if(parametro.equals("Nombre"))
			sql += " OR apPat LIKE ? OR apMat LIKE ?";
		ResultSet rs = conexion.querySelect(con, sql, "%" + busqueda + "%");
		
		LinkedList<String[]> resultado = new LinkedList<String[]>();
		try
		{
			int cols = StringUtils.split(columnas, ",").length;
			while (rs.next())
			{
				String[] res = new String[cols];
				for(int i=0; i<cols; i++)
					res[i] = rs.getString(i);
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
}