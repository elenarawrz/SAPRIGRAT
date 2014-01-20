package com.saprigrat.data;

import java.util.LinkedList;


public class Datos
{
	InterfazConsulta cons;
	InterfazInsercion ins;
	public Datos()
	{
		cons = new InterfazConsulta();
		ins = new InterfazInsercion();
	}
	
	public LinkedList<Object> autenticacion(String user, String pass)
	{
		return cons.getRegistro("Login", new Object[]{user, pass});
	}

	public LinkedList<String> getEstados()
	{
		return cons.getListado("Estados", new Object[]{});
	}
	
	public LinkedList<String> getMunicipios(String estado)
	{
		return cons.getListado("Municipios", new Object[]{estado});
	}
	
	public String registrarTecnico(LinkedList<Object> valores)
	{
		return ins.insertarRegistro("Tecnico", valores);
	}

	public String modificarTecnico(LinkedList<Object> valores)
	{
		return ins.modificarRegistroStr("Tecnico", valores);
	}

	public LinkedList<Object> getTecnico(String curr)
	{
		return cons.getRegistro("Tecnico", new Object[]{curr});
	}
	
	public LinkedList<Object[]> getTecnicos(Object[] restricciones)
	{
		return cons.getTabla("Tecnicos", restricciones);
	}
	
	public LinkedList<String> getRespTecnicos(String curr, int tipoUusuario, int tipo)
	{
		return cons.getListado("RespTecnicos", new Object[]{curr,tipoUusuario,tipo});
	}
	
	public String registrarProductor(LinkedList<Object> valores)
	{
		return ins.insertarRegistro("Productor", valores);
	}

	public boolean modificarProductor(LinkedList<Object> valores)
	{
		return ins.modificarRegistro("Productor", valores);
	}

	public LinkedList<Object> getProductor(String curr)
	{
		return cons.getRegistro("Productor", new Object[]{curr});
	}
	
	public LinkedList<Object[]> getProductores(String CURRresp)
	{
		return cons.getTabla("Productores", new Object[]{CURRresp});
	}
	
	public LinkedList<String> getParcelas(String curr, int tipoUusuario)
	{
		return cons.getListado("Parcelas", new Object[]{curr,tipoUusuario});
	}
	
	public boolean registrarRegador(LinkedList<Object> valores)
	{
		return ins.insertarRegistro("Regador", valores).equals("si");
	}
	
	public boolean modificarRegador(LinkedList<Object> valores)
	{
		return ins.modificarRegistro("Regador", valores);
	}
	
	public LinkedList<Object[]> getRegadores(String CURRprod)
	{
		return cons.getTabla("Regadores", new Object[]{CURRprod});
	}
	
	
}