package com.saprigrat.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

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
		ResultSet rs = conexion.querySelect(con, sql, edo, 1);
		
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
			columnas += "apPat,apMat";
		String sql = "SELECT " + columnas + " FROM \"infoAdministrativa\".personas WHERE LOWER(" + parametro + ") LIKE LOWER(?)";
		int rep = 1;
		if(parametro.equals("Nombre"))
		{
			sql += " OR LOWER(apPat) LIKE LOWER(?) OR LOWER(apMat) LIKE LOWER(?)";
			rep = 3;
		}
		ResultSet rs = conexion.querySelect(con, sql, "%" + busqueda + "%", rep);
		
		LinkedList<String[]> resultado = new LinkedList<String[]>();
		try
		{
			int cols = columnas.split(",").length;
			while (rs.next())
			{
				String[] res = new String[cols];
				for(int i=0; i<cols; i++)
					res[i] = rs.getString(i + 1);
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
		String sql = "SELECT COUNT(*) FROM \"infoAdministrativa\"." + entidad + " WHERE " + parametro1 + "=?" + " AND " + parametro2 + "=?";
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
}