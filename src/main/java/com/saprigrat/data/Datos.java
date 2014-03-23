package com.saprigrat.data;

import java.math.BigDecimal;
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
		return cons.getRegistro("login", new Object[]{user, pass});
	}
	
	//region Técnicos
	public String registrarTecnico(LinkedList<Object> valores)
	{
		return ins.insertarRegistro("tecnico", valores);
	}

	public String modificarTecnico(LinkedList<Object> valores)
	{
		return ins.modificarRegistro("tecnico", valores);
	}

	public LinkedList<Object> getTecnico(int idTecnico)
	{
		return cons.getRegistro("tecnico", new Object[]{idTecnico});
	}
	
	public LinkedList<Object[]> getTecnicos(String currResp, int tipoPersona)
	{
		return cons.getTabla("tecnicos", new Object[]{currResp,tipoPersona});
	}
	
	public LinkedList<String> getRespTecnicos(String curr, int tipoUusuario, int tipo)
	{
		return cons.getListado("tecnicosResp", new Object[]{curr,tipoUusuario,tipo});
	}
	//endregion
	
	//region Productores
	public String registrarProductor(LinkedList<Object> valores)
	{
		return ins.insertarRegistro("productor", valores);
	}

	public String modificarProductor(LinkedList<Object> valores)
	{
		return ins.modificarRegistro("productor", valores);
	}

	public LinkedList<Object> getProductor(int idProductor)
	{
		return cons.getRegistro("productor", new Object[]{idProductor});
	}
	
	public LinkedList<Object[]> getProductores(String CURRresp)
	{
		return cons.getTabla("productores", new Object[]{CURRresp});
	}
	
	public LinkedList<String> getRespProductores(String curr)
	{
		return cons.getListado("productoresResp", new Object[]{curr});
	}
	//endregion
	
	//region Aprovechamientos
	public String registrarAprovechamiento(LinkedList<Object> valores)
	{
		return ins.insertarRegistro("aprovechamiento", valores);
	}

	public String modificarAprovechamiento(LinkedList<Object> valores)
	{
		return ins.modificarRegistro("aprovechamiento", valores);
	}

	public LinkedList<Object> getAprovechamiento(int idAprovechamiento)//String padron)
	{
		LinkedList<Object> aprovechamiento = cons.getRegistro("aprovechamiento", new Object[]{idAprovechamiento/*padron*/});
		aprovechamiento = bigDecimalToDouble(aprovechamiento);//.set(4, Double.parseDouble((BigDecimal)aprovechamiento.get(4) + "") + "");
		return aprovechamiento;
	}
	
	public LinkedList<Object[]> getAprovechamientos(String currResp)
	{
		LinkedList<Object[]> aprovechamientos = cons.getTabla("aprovechamientos", new Object[]{currResp});
		aprovechamientos = bigDecimalToDoubleTbl(aprovechamientos);
//		for(int i = 1; i<aprovechamientos.size(); i++)
//		{
//			Object[] aprovechamiento = aprovechamientos.get(i);
//			aprovechamiento[3] = Double.parseDouble((BigDecimal)aprovechamiento[3] + "");
//		}
		return aprovechamientos;
	}
	
	public LinkedList<String> getCtasPadron(String currProd)
	{
		return cons.getListado("ctasPadron", new Object[]{currProd});
	}
	//endregion
	
	//region Parcelas
	public String registrarParcela(LinkedList<Object> valores)
	{
		return ins.insertarRegistro("parcela", valores);
	}

	public String modificarParcela(LinkedList<Object> valores)
	{
		return ins.modificarRegistro("parcela", valores);
	}

	public LinkedList<Object> getParcela(int idParcela)
	{
		LinkedList<Object> parcela = cons.getRegistro("parcela", new Object[]{idParcela});
		parcela = bigDecimalToDouble(parcela);
		return parcela;
	}
	
	public LinkedList<Object[]> getParcelas(String CURRtec)
	{
		LinkedList<Object[]> parcelas = cons.getTabla("parcelas", new Object[]{CURRtec});
		parcelas = bigDecimalToDoubleTbl(parcelas);
		return parcelas;
	}
	
	public LinkedList<String> getCtasPadronParcelas(String CURRprod)
	{
		return cons.getListado("ctasPadronParcelas", new Object[]{CURRprod});
	}
	//endregion
	
	//region Regadores
	public String registrarRegador(LinkedList<Object> valores)
	{
		return ins.insertarRegistro("regador", valores);
	}
	
	public String modificarRegador(LinkedList<Object> valores)
	{
		return ins.modificarRegistro("regador", valores);
	}

	public LinkedList<Object> getRegador(int idRegador)
	{
		LinkedList<Object> parcela = cons.getRegistro("regador", new Object[]{idRegador});
		parcela = bigDecimalToDouble(parcela);
		return parcela;
	}
	
	public LinkedList<Object[]> getRegadores(String CURRresp)
	{
		return cons.getTabla("regadores", new Object[]{CURRresp});
	}
	
	public LinkedList<String> getRespRegadores(String padronParcela)
	{
		return cons.getListado("regadoresResp", new Object[]{padronParcela});
	}
	//endregion
	
	//region Riegos
	public String registrarRiego(LinkedList<Object> valores)
	{
		return ins.insertarRegistro("riego", valores);
	}
	
	public String modificarRiego(LinkedList<Object> valores)
	{
		return ins.modificarRegistro("riego", valores);
	}

	public LinkedList<Object> getRiego(int idRegador)
	{
		LinkedList<Object> parcela = cons.getRegistro("riego", new Object[]{idRegador});
		parcela = bigDecimalToDouble(parcela);
		return parcela;
	}
	
	public LinkedList<Object[]> getRiegos(String CURRresp)
	{
		return cons.getTabla("riegos", new Object[]{CURRresp});
	}
	//endregion
	
	//region Pruebas de Riego
	public String registrarPruebaRiego(LinkedList<Object> valores)
	{
		return ins.insertarRegistro("pruebaRiego", valores);
	}
	
	public String modificarPruebaRiego(LinkedList<Object> valores)
	{
		return ins.modificarRegistro("pruebaRiego", valores);
	}

	public LinkedList<Object> getPruebaRiego(int idPrueba, int seccion)
	{
		String[]secciones = {"", "LecturaInicial", "Caracteristicas", "Aforo"};
		LinkedList<Object> parcela = cons.getRegistro("pruebaRiego" + secciones[seccion], new Object[]{idPrueba});
		parcela = bigDecimalToDouble(parcela);
		return parcela;
	}
	
	public LinkedList<Object[]> getPruebasRiego(String CURRresp)
	{
		return cons.getTabla("pruebasRiego", new Object[]{CURRresp});
	}
	//endregion
	
	
	//region Conversión de tipos
	private LinkedList<Object> bigDecimalToDouble(LinkedList<Object> lista)
	{
		for(int i=0; i<lista.size(); i++)
			if(lista.get(i) instanceof BigDecimal)
				lista.set(i, Double.parseDouble((BigDecimal)lista.get(i) + "") + "");
		return lista;
	}
	
	private LinkedList<Object[]> bigDecimalToDoubleTbl(LinkedList<Object[]> lista)
	{
		for(int i = 1; i<lista.size(); i++)
		{
			Object[] parcela = lista.get(i);
			for(int j=0; j<parcela.length; j++)
				if(parcela[j] instanceof BigDecimal)
					parcela[j] = Double.parseDouble((BigDecimal)parcela[j] + "");
		}
		return lista;
	}
	//endregion

	
	//region Listados
	public LinkedList<String> getEstados()
	{
		return cons.getListado("estados", new Object[]{});
	}
	
	public LinkedList<String> getMunicipios(String estado)
	{
		return cons.getListado("municipios", new Object[]{estado});
	}
	public LinkedList<String> getFormacion()
	{
		return cons.getListado("formacion", new Object[]{});
	}
	
	public LinkedList<String> getAbastecimiento()
	{
		return cons.getListado("abastecimiento", new Object[]{});
	}
	
	public LinkedList<String> getTenencia()
	{
		return cons.getListado("tenencia", new Object[]{});
	}
	
	public LinkedList<String> getOrgCuenca()
	{
		return cons.getListado("orgCuenca", new Object[]{});
	}
	
	public LinkedList<String> getDtoRiego()
	{
		return cons.getListado("dtoRiego", new Object[]{});
	}
	
//	public LinkedList<String> getDtoRiego(String organismo)
//	{
//		return cons.getListado("dtoRiego", new Object[]{organismo});
//	}
	
	public LinkedList<String> getModRiego(String distrito)
	{
		return cons.getListado("modRiego", new Object[]{distrito});
	}
	
	public LinkedList<String> getSRL(String distrito)
	{
		return cons.getListado("SRL", new Object[]{distrito});
	}
	
	public LinkedList<String> getTipoSuelo()
	{
		return cons.getListado("tipoSuelo", new Object[]{});
	}
	
	public LinkedList<String> getTipoRiego()
	{
		return cons.getListado("tipoRiego", new Object[]{});
	}
	
	public LinkedList<String> getEtapaFenologica()
	{
		return cons.getListado("etapaFenologica", new Object[]{});
	}
	//endregion
	
	
	
	


	
	
	
}