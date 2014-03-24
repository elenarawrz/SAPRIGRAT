package com.saprigrat.data;

import java.math.BigDecimal;
import java.sql.Array;
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
		String curr = "";
		try
		{
			curr = (String) getResultado(tabla + "Insert",
										 Types.VARCHAR,
										 valores);
		}
		catch (SQLException e)
		{
			System.out.println("Error al insertar el registro.");
			conexion.errorlog(e);//e.printStackTrace();
		}
		return curr;
	}
	
	public String modificarRegistro(String tabla, LinkedList<Object> valores)
	{
		String status = "";
		try
		{
			status = (String) getResultado( tabla + "Update",
											Types.VARCHAR,
											valores);
		}
		catch (SQLException e)
		{
			System.out.println("Error al modificar el registro.");
			conexion.errorlog(e);//e.printStackTrace();
		}
		return status;
	}
	
	private Object getResultado(String funcion, int returnType, LinkedList<Object> valores) throws SQLException
	{
		Connection con = conexion.openConexion();
		CallableStatement cs = null;
		
		String vars = "";
		for(int i = 0; i<valores.size(); i++)
			vars +="?,";
		if(!vars.isEmpty())
			vars = vars.substring(0, vars.length() - 1);
		
		cs = con.prepareCall("{? = call " + funcion + " (" + vars + ")}");
		cs.registerOutParameter(1, returnType);
		
		for(int i = 0; i<valores.size(); i++)
		{
			Object valor = valores.get(i);
			if(valor != null)
				if(valor instanceof String)
					cs.setString(i + 2, (String) valor);
				else
					if(valor instanceof Integer)
						cs.setInt(i + 2, (Integer) valor);
					else
						if(valor instanceof Boolean)
							cs.setBoolean(i + 2, (Boolean) valor);
						else
							if(valor instanceof Date)
								cs.setDate(i + 2, (Date) valor);
							else
								if(valor instanceof Double)
									cs.setBigDecimal(i + 2, new BigDecimal((Double)valor));
								else
									if(valor instanceof Long[])
										cs.setArray(i + 2, con.createArrayOf("decimal", (Long[]) valor));
									else
										if(valor instanceof Integer[])
											cs.setArray(i + 2, con.createArrayOf("int", (Integer[]) valor));
										else
											if(valor instanceof String[])
												cs.setArray(i + 2, con.createArrayOf("text", (String[]) valor));
											else
												System.out.println(valor.getClass().toString());
			else
				cs.setString(i + 2, null);
		}
		cs.execute();
		
		Object returnValue = cs.getObject(1);
		
		cs.close();
		conexion.closeConexion(con);
		
		return returnValue;
	}
}