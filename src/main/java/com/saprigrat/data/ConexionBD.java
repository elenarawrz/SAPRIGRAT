package com.saprigrat.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.vaadin.ui.Notification;

public class ConexionBD
{
	public Connection openConexion()
	{
		Connection con = null;
		
		try
		{
//			String  host = "jdbc:postgresql://localhost:5432/saprigrat",
//					user = "postgres",
//					pass = "admin",
//					driver = "org.postgresql.Driver";
			
			Properties prop = new Properties();
	        prop.load(new FileInputStream(System.getProperty("user.home") + "/dbcreds.cfg"));
	        System.out.println("user.home: "+System.getProperty("user.home"));
	        String  host = prop.getProperty("host").toString().trim(),
			        user = prop.getProperty("user").toString().trim(),
			        pass = prop.getProperty("pass").toString().trim(),
			        driver = prop.getProperty("driver").toString().trim();
	        
			Class.forName(driver);
	        System.out.println( "--------------------------------------------------\n" +
	        					"DRIVER: " + driver);
			con = DriverManager.getConnection(host, user, pass);
	        System.out.println( "CONNECTION: " + con +
	        					"\n--------------------------------------------------");
		}
		catch (Exception e)
		{
			if(e instanceof FileNotFoundException)
				System.out.println("No se encontró el archivo de configuraciones en 'Home'.");
			if(e instanceof ClassNotFoundException)
				System.out.println("No se encontró el driver.");
			if(e instanceof SQLException)
				System.out.println("No se pudo crear la conexión.");
	        System.out.println("\n--------------------------------------------------");
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
	
	public void errorlog(SQLException sqle)
	{
		StringWriter sw = new StringWriter();
		sqle.printStackTrace(new PrintWriter(sw));
		String stackTrace = sw.toString();
		try {
			File log = new File("C:/saprigrat_error.log");
			FileWriter fw = new FileWriter(log);
			fw.write(stackTrace);
			fw.close();
		}
		catch (IOException ioe)
		{
			Notification.show(stackTrace, Notification.Type.ERROR_MESSAGE);
		}
	}
}