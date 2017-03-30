package com.saprigrat.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;

import com.vaadin.data.util.sqlcontainer.SQLContainer;

public class InterfazConsulta
{
	private ConexionBD conexion;
	public InterfazConsulta()
	{
		conexion = new ConexionBD();
	}
	
	public LinkedList<Object[]> getTabla(String tabla, Object[] restricciones)
	{
		Connection con = conexion.openConexion();
		CallableStatement cs = null;
		LinkedList<Object[]> listado = new LinkedList<Object[]>();
//		String curr = "";
		try
		{
			con.setAutoCommit(false);
			String vars = "";
			for(int i = 0; i<restricciones.length; i++)
				vars +="?,";
			if(!vars.isEmpty())
				vars = vars.substring(0, vars.length() - 1);
			cs = con.prepareCall("{? = call get" + tabla + " (" + vars + ")}");
			cs.registerOutParameter(1, Types.OTHER);
			
			for(int i = 0; i<restricciones.length; i++)
				if(restricciones[i]!=null)
					if(restricciones[i] instanceof String)
						cs.setString(i + 2, (String)restricciones[i]);
					else
						if(restricciones[i] instanceof Integer)
							cs.setInt(i + 2, (Integer)restricciones[i]);
						else
							if(restricciones[i] instanceof Boolean)
								cs.setBoolean(i + 2, (Boolean)restricciones[i]);
							else
								if(restricciones[i] instanceof Date)
									cs.setDate(i + 2, (Date)restricciones[i]);
								else
									System.out.println(restricciones[i].getClass().toString());
			cs.execute();
			ResultSet rs = (ResultSet) cs.getObject(1);
			
			int cols = rs.getMetaData().getColumnCount();
			String[] encabezados = new String[cols];
			for(int i = 1; i<=cols; i++)
				encabezados[i-1] = rs.getMetaData().getColumnName(i);
			listado.add(encabezados);
			
			while (rs.next())
			{
				Object[] res = new Object[cols];
				for(int i=0; i<cols; i++)
					res[i] = rs.getObject(i + 1);
				listado.add(res);
	        }
			
	        rs.close();
			conexion.closeConexion(con);
		}
		catch (SQLException e)
		{
			System.out.println("Error al obtener el contenido de la tabla.");
			e.printStackTrace();
		}
		
		return listado;
	}
	
	public LinkedList<String> getResponsablesDirectos(String tabla, String estado)
	{
		Connection con = conexion.openConexion();
		CallableStatement cs = null;
		LinkedList<String> listado = new LinkedList<String>();
		try
		{
			con.setAutoCommit(false);
			cs = con.prepareCall("{? = call get" + tabla + " (?,?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, tabla);
			cs.setString(3, estado);
			cs.execute();
			
			ResultSet rs = (ResultSet) cs.getObject(1);
			while (rs.next())
			{
				String responsable = "";
				for(int i=1, cols = rs.getMetaData().getColumnCount(); i<=cols; i++)
					responsable += rs.getString(i) + " ";
				listado.add(responsable.substring(0, responsable.length() - 1));
	        }
			
	        rs.close();
			conexion.closeConexion(con);
		}
		catch (SQLException e)
		{
			System.out.println("Error al obtener el listado.");
			e.printStackTrace();
		}
		
		return listado;
	}
	
	
	
	
	
	public LinkedList<Object[]> getBusqueda(String columnas, String parametro, String busqueda)
	{
		Connection con = conexion.openConexion();
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
			conexion.closeConexion(con);
		}
		catch (SQLException e)
		{
			System.out.println("Error al obtener resultados de búsqueda.");
			e.printStackTrace();
		}
		return resultado;
	}
	
	
	
	
	
	public LinkedList<String> getEstados()
	{
		Connection con = conexion.openConexion();
		String sql = "SELECT DISTINCT estado FROM municipios ORDER BY estado";
		ResultSet rs = conexion.querySelect(con, sql);
		
		LinkedList<String> edos = new LinkedList<String>();
		try
		{
			while (rs.next())
				edos.add(rs.getString(1));
			conexion.closeConexion(con);
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
		Connection con = conexion.openConexion();
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
			conexion.closeConexion(con);
		}
		catch (SQLException e)
		{
			System.out.println("Error al obtener " + entidad + ".");
			e.printStackTrace();
		}
		return vals;
	}
	
	public SQLContainer getSQLContainer(String tabla)
	{
		return conexion.getSQLContainer(tabla);
	}
}