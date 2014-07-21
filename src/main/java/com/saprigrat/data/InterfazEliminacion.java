package com.saprigrat.data;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class InterfazEliminacion
{
	private ConexionBD conexion;
	public InterfazEliminacion()
	{
		conexion = new ConexionBD();
	}

	public String eliminarRegistro(String tabla, Object[] valores)
	{
		String status = "";
		try
		{
			Connection con = conexion.openConexion();
			
			String vars = "";
			for(int i = 0; i<valores.length; i++)
				vars +="?,";
			if(!vars.isEmpty())
				vars = vars.substring(0, vars.length() - 1);
			CallableStatement cs = con.prepareCall("{? = call " + tabla + "Delete (" + vars + ")}");
			
			cs.registerOutParameter(1, Types.VARCHAR);
			setInParameters(cs, valores);
			cs.execute();
			
			Object returnValue = cs.getObject(1);
			
			cs.close();
			conexion.closeConexion(con);
			
			status = (String) returnValue;
			
		}
		catch (SQLException e)
		{
			System.out.println("Error al eliminar el registro.");
			conexion.errorlog(e);//e.printStackTrace();
		}
		return status;
	}
	
	private Object setInParameters(CallableStatement cs, Object[] valores) throws SQLException
	{
		for(int i = 0; i<valores.length; i++)
		{
			Object valor = valores[i];
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
							if(valor instanceof Double)
								cs.setBigDecimal(i + 2, new BigDecimal((Double)valor));
							else
								System.out.println(valor.getClass().toString());
			else
				cs.setString(i + 2, null);
		}
		
		return cs;
	}
}