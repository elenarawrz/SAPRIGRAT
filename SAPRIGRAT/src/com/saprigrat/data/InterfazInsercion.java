package com.saprigrat.data;

import java.sql.Connection;
import java.util.LinkedList;

public class InterfazInsercion
{
	private ConexionBD conexion;
	private Connection con;
	public InterfazInsercion()
	{
		conexion = new ConexionBD();
		con = conexion.openConexion();
	}

	public boolean registrar(String entidad, LinkedList<Object> valores)
	{
		String  sql = "INSERT INTO " + entidad + " (",
				vars = "";
		for(int i = 0; i<valores.size(); i++)
		{
			sql += valores.remove(i) + ",";
			vars += "?,";
		}
		sql = sql.substring(0, sql.length() - 1) + ") VALUES (" + vars.substring(0, vars.length() - 1) + ")";
		int result = conexion.queryInsert(con, sql, valores);
		return result!=-1;
	}

	public boolean modificar(String entidad, String claveUnica, LinkedList<Object> valores)
	{
		String  sql = "UPDATE " + entidad + " SET ";
		for(int i = 0; i<valores.size()-1; i++)
			sql += valores.remove(i) + "=?,";
		sql = sql.substring(0, sql.length() - 1) + " WHERE " + claveUnica + "=?";
		int result = conexion.queryUpdate(con, sql, valores);
		return result!=-1;
	}
}