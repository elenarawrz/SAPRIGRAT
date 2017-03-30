package com.saprigrat.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;

public class InterfazInsercion
{
	private ConexionBD conexion;
	public InterfazInsercion()
	{
		conexion = new ConexionBD();
	}

	public String insertarRegistro(String tabla, LinkedList<Object> valores)
	{
		Connection con = conexion.openConexion();
		CallableStatement cs = null;
		String curr = "";
		try
		{
			String vars = "";
			for(int i = 0; i<valores.size(); i++)
				vars +="?,";
			cs = con.prepareCall("{? = call insert" + tabla + " (" + vars.substring(0, vars.length() - 1) + ")}");
			cs.registerOutParameter(1, Types.VARCHAR);
			for(int i = 0; i<valores.size(); i++)
				if(valores.get(i) != null)
				{
					String clase = valores.get(i).getClass().toString();
					if(clase.indexOf("lang.String") != -1)
						cs.setString(i + 2, (String)valores.get(i));
					else
						if(clase.indexOf("lang.Integer") != -1)
							cs.setInt(i + 2, (Integer)valores.get(i));
						else
							if(clase.indexOf("lang.Boolean") != -1)
								cs.setBoolean(i + 2, (Boolean)valores.get(i));
							else
								if(clase.indexOf("sql.Date") != -1)
									cs.setDate(i + 2, (Date)valores.get(i));
								else
									System.out.println(clase);
				}
				else
					cs.setString(i + 2, null);
			cs.execute();
			curr = cs.getString(1);
			System.out.println(curr);
			conexion.closeConexion(con);
		}
		catch (SQLException e)
		{
			System.out.println("Error al insertar el registro.");
			e.printStackTrace();
		}
		return curr;
	}
	
	
	
	

	public boolean modificar(String entidad, String claveUnica, LinkedList<Object> valores)
	{
		Connection con = conexion.openConexion();
		String  sql = "UPDATE " + entidad + " SET ";
		for(int i = 0; i<valores.size()-1; i++)
			sql += valores.remove(i) + "=?,";
		sql = sql.substring(0, sql.length() - 1) + " WHERE " + claveUnica + "=?";
		int result = conexion.queryUpdate(con, sql, valores);
		conexion.closeConexion(con);
		return result!=-1;
	}
}