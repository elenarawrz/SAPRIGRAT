package com.saprigrat.data;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;

public class InterfazConsulta
{
	private ConexionBD conexion;
	public InterfazConsulta()
	{
		conexion = new ConexionBD();
	}
	
	public LinkedList<Object[]> getTabla(String tabla, Object[] valores)
	{
		LinkedList<Object[]> listado = new LinkedList<Object[]>();
		try
		{
			ResultSet rs = (ResultSet) getResult(tabla, Types.OTHER, valores);
			if(rs != null)
			{
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
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error al obtener el contenido de la tabla.");
			conexion.errorlog(e);//e.printStackTrace();
		}
		return listado;
	}
	
	public LinkedList<Object> getRegistro(String tabla, Object[] valores)
	{
		LinkedList<Object> listado = new LinkedList<Object>();
		try
		{
			ResultSet rs = (ResultSet) getResult(tabla, Types.OTHER, valores);
			if(rs != null)
			{
				while (rs.next())
					for(int i = 1, cols = rs.getMetaData().getColumnCount(); i<=cols; i++)
						listado.add(rs.getObject(i));
		        rs.close();
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error al obtener el registro.");
			conexion.errorlog(e);//e.printStackTrace();
		}
		return listado;
	}
	
	public LinkedList<String> getListado(String tabla, Object[] valores)
	{
		LinkedList<String> listado = new LinkedList<String>();
		try
		{
			ResultSet rs = (ResultSet) getResult(tabla, Types.OTHER, valores);
			if(rs != null)
			{
				while (rs.next())
					listado.add(rs.getString(1));
		        rs.close();
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error al obtener el listado.");
			conexion.errorlog(e);//e.printStackTrace();
		}
		return listado;
	}
	
	public ResultSet getReporte(String tabla, Object[] valores)
	{
		try
		{
			return (ResultSet) getResult(tabla, Types.OTHER, valores);
		}
		catch (SQLException e)
		{
			System.out.println("Error al obtener el listado.");
			conexion.errorlog(e);//e.printStackTrace();
			return null;
		}
	}
	
	public int getId(String tabla, Object[] valores)
	{
		int id = 0;
		try
		{
			Object result = getResult(tabla, Types.INTEGER, valores);
			if (result != null)
				id = (Integer)result;
		}
		catch (SQLException e)
		{
			System.out.println("Error al obtener el id.");
			conexion.errorlog(e);//e.printStackTrace();
		}
		return id;
	}
	
	private Object getResult(String tabla, int returns, Object[] valores) throws SQLException
	{
		Object result = null;
		Connection con = conexion.openConexion();
		if(con != null)
		{
			con.setAutoCommit(false);
			
			String vars = "";
			for(int i = 0; i<valores.length; i++)
				vars +="?,";
			if(!vars.isEmpty())
				vars = vars.substring(0, vars.length() - 1);
			
			CallableStatement cs = con.prepareCall("{? = call " + tabla + "Select (" + vars + ")}");
			cs.registerOutParameter(1, returns);
			for(int i = 0; i<valores.length; i++)
			{
				if(valores[i]!=null)
					if(valores[i] instanceof String)
						cs.setString(i + 2, (String) valores[i]);
					else
						if(valores[i] instanceof Integer)
							cs.setInt(i + 2, (Integer) valores[i]);
						else
							if(valores[i] instanceof Boolean)
								cs.setBoolean(i + 2, (Boolean) valores[i]);
							else
								if(valores[i] instanceof Date)
									cs.setDate(i + 2, (Date) valores[i]);
								else
									if(valores[i] instanceof Double)
										cs.setBigDecimal(i + 2, new BigDecimal((Double)valores[i]));
									else
										System.out.println(valores[i].getClass().toString());
				else
					cs.setString(i + 2, null);
			}
			cs.execute();
			
			result = cs.getObject(1);
			
			cs.close();
			conexion.closeConexion(con);
		}
		
		return result;
	}
}