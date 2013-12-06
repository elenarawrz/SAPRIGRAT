package com.saprigrat.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	public ResultSet querySelect(Connection con, String sql, String criterio)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = con.prepareStatement(sql);
			ps.setString(1, criterio);
			rs = ps.executeQuery();
		}
		catch (SQLException e)
		{
			System.out.println("Error al realizar la consulta.");
			e.printStackTrace();
		}
		
		return rs;
	}
}