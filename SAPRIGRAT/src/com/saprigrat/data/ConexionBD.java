package com.saprigrat.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;

public class ConexionBD
{
	private String  driver = "org.postgresql.Driver",
					url = "jdbc:postgresql://localhost:5432/saprigrat",
					user = "postgres",
					pass = "admin";
	
	public Connection openConexion()
	{
		Connection con = null;
		
		try
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass);
		}
		catch (Exception e)
		{
			if(e.getClass().equals(ClassNotFoundException.class))
				System.out.println("No se encontró el driver.");
			if(e.getClass().equals(SQLException.class))
				System.out.println("No se pudo crear la conexión.");
			e.printStackTrace();
		}
		
		return con;
	}
	
	public void closeConexion(Connection con)
	{
		try
		{
			con.close();
		}
		catch (SQLException e)
		{
			System.out.println("Error al cerrar la conexión.");
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	public ResultSet querySelect(Connection con, String sql)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
		}
		catch (SQLException e)
		{
			System.out.println("Error al realizar la consulta.");
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public ResultSet querySelect(Connection con, String sql, String criterio, int repeticionParam)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = con.prepareStatement(sql);
			for(int i = 1; i<=repeticionParam; i++)
				ps.setString(i, criterio);
			rs = ps.executeQuery();
		}
		catch (SQLException e)
		{
			System.out.println("Error al realizar la consulta.");
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public ResultSet querySelect(Connection con, String sql, int criterioInt, String criterioStr)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = con.prepareStatement(sql);
			ps.setInt(1, criterioInt);
			ps.setString(2, criterioStr);
			rs = ps.executeQuery();
		}
		catch (SQLException e)
		{
			System.out.println("Error al realizar la consulta.");
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public int queryUpdate(Connection con, String sql, LinkedList<Object> valores)
	{
		PreparedStatement ps = null;
		int result = -1;
		try
		{
			ps = con.prepareStatement(sql);
			for(int i = 0; i<valores.size(); i++)
			{
				switch(i)
				{
					case 3: ps.setDate(i + 1, (Date)valores.get(i)); break;
					case 16: case 17: ps.setBoolean(i + 1, (Boolean)valores.get(i)); break;
					default: ps.setString(i + 1, (String)valores.get(i)); break;
				}
			}
			result = ps.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println("Error al modificar el registro.");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public SQLContainer getSQLContainer(String tabla)
	{
		SQLContainer sql = null;
		try
		{
			JDBCConnectionPool pool = new SimpleJDBCConnectionPool(driver, url, user, pass);
			TableQuery entidad = new TableQuery(tabla, pool);
			entidad.setVersionColumn("OPTLOCK");
			sql = new SQLContainer(entidad);
		}
		catch (SQLException e)
		{
			System.out.println("Error al conectarse con la base de datos.");
			e.printStackTrace();
		}
		return sql;
	}
}