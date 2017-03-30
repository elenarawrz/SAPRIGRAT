package com.saprigrat.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.vaadin.ui.Notification;

public class ConexionBD
{
	//region credenciales jelastic
	private String  driver = "org.postgresql.Driver",
					url = "jdbc:postgresql://postgres-saprigratEnv.jelastic.servint.net/saprigrat",
					user = "webadmin",
					pass = "adminsaprigrat";
	//endRegion
	
	//region credenciales openshift
//	private String  driver = "org.postgresql.Driver",
//			url = "jdbc:postgresql://$OPENSHIFT_POSTGRESQL_DB_HOST:$OPENSHIFT_POSTGRESQL_DB_PORT/saprigrat",
//			user = "adminpupzwb7",
//			pass = "nzdcv13WHMju";
	//endRegion
	
	//region credenciales localhost
//	private String  driver = "org.postgresql.Driver",
//			url = "jdbc:postgresql://localhost:5432/saprigrat",
//			user = "postgres",
//			pass = "admin";
	//endRegion
	
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
	
	public void notificar(StackTraceElement[] st)
	{
		String s = "";
		for(int i=0; i<st.length; i++)
			s += st[i].toString() + "\n";
		Notification.show(s, Notification.Type.ERROR_MESSAGE);
	}
}