package com.saprigrat.data;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.LinkedList;


public class Datos
{
	InterfazConsulta cons;
	InterfazInsercion ins;
	InterfazEliminacion elim;
	public Datos()
	{
		cons = new InterfazConsulta();
		ins = new InterfazInsercion();
		elim = new InterfazEliminacion();
	}
	
	public LinkedList<Object> autenticacion(String user, String pass)
	{
		return cons.getRegistro("login", new Object[]{user, pass});
	}
	
	//region Administradores
	public String registrarAdministrador(LinkedList<Object> valores)
	{
		return ins.insertarRegistro("administrador", valores);
	}

	public String modificarAdministrador(LinkedList<Object> valores)
	{
		return ins.modificarRegistro("administrador", valores);
	}

	public String eliminarAdministrador(int idAdministrador, String CURR)
	{
		return elim.eliminarRegistro("administrador", new Object[]{idAdministrador, CURR});
	}

	public LinkedList<Object> getAdministrador(int idAdministrador)
	{
		return cons.getRegistro("administrador", new Object[]{idAdministrador});
	}
	
	public LinkedList<Object[]> getAdministradores(int tipoPersona)
	{
		return cons.getTabla("administradores", new Object[]{tipoPersona});
	}

	public int getIdAdministradorGeneral(int tipoPersona)
	{
		return cons.getId("administradorId", new Object[]{tipoPersona});
	}
	//endregion
	
	//region Técnicos
	public String registrarTecnico(LinkedList<Object> valores)
	{
		return ins.insertarRegistro("tecnico", valores);
	}

	public String modificarTecnico(LinkedList<Object> valores)
	{
		return ins.modificarRegistro("tecnico", valores);
	}

	public String eliminarTecnico(int idTecnico, String CURR, int tipoEliminar)
	{
		return elim.eliminarRegistro("tecnico", new Object[]{idTecnico, CURR, tipoEliminar});
	}

	public LinkedList<Object> getTecnico(int idTecnico)
	{
		LinkedList<Object> resultado = cons.getRegistro("tecnico", new Object[]{idTecnico});
		resultado = bigDecimalToDoubleList(resultado);
		return resultado;
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

	public String eliminarProductor(int idProductor)
	{
		return elim.eliminarRegistro("productor", new Object[]{idProductor});
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
	
	//region Parcelas
	public String registrarParcela(LinkedList<Object> valores)
	{
		return ins.insertarRegistro("parcela", valores);
	}

	public String modificarParcela(LinkedList<Object> valores)
	{
		return ins.modificarRegistro("parcela", valores);
	}

	public String eliminarParcela(int idParcela, boolean modoCascade)
	{
		return elim.eliminarRegistro("parcela", new Object[]{ idParcela, modoCascade });
	}

	public LinkedList<Object> getParcela(int idParcela)
	{
		LinkedList<Object> parcela = cons.getRegistro("parcela", new Object[]{idParcela});
		parcela = bigDecimalToDoubleList(parcela);//.set(4, Double.parseDouble((BigDecimal)aprovechamiento.get(4) + "") + "");
		return parcela;
	}

	public LinkedList<Object[]> getListaArrendatarios(int idParcela)
	{
		LinkedList<Object[]> listaArrendatarios = cons.getTabla("parcelaArrendatariosLista", new Object[]{idParcela});
		listaArrendatarios = bigDecimalToDoubleArr(listaArrendatarios);
		listaArrendatarios.remove();
		return listaArrendatarios;
	}
	
	public LinkedList<Object[]> getParcelas(String currResp)
	{
		LinkedList<Object[]> parcelas = cons.getTabla("parcelas", new Object[]{currResp});
		parcelas = bigDecimalToDoubleArr(parcelas);
		return parcelas;
	}
	
	public LinkedList<Object[]> getParcelasByResponsable(String currResp)
	{
		LinkedList<Object[]> parcelas = cons.getTabla("parcelasByResponsable", new Object[]{currResp});
		parcelas = bigDecimalToDoubleArr(parcelas);
		return parcelas;
	}
	
	public LinkedList<Object[]> getParcelasByProductor(String currProd)
	{
		LinkedList<Object[]> parcelas = cons.getTabla("parcelasByProductor", new Object[]{currProd});
		parcelas = bigDecimalToDoubleArr(parcelas);
		return parcelas;
	}
	
	public LinkedList<String> getCtasPadron(String currProd)
	{
		return cons.getListado("ctasPadron", new Object[]{currProd});
	}
	
	public int getIdParcela(String padron)
	{
		return cons.getId("parcelaId", new Object[]{padron});
	}
	//endregion
	
	//region Caracterización Parcela
	public String registrarCaracterizacion(LinkedList<Object> valores)
	{
		return ins.insertarRegistro("caracterizacionParcela", valores);
	}

	public String modificarCaracterizacion(LinkedList<Object> valores)
	{
		return ins.modificarRegistro("caracterizacionParcela", valores);
	}

	public LinkedList<Object> getCaracterizacion(int idCaracterizacion)
	{
		LinkedList<Object> parcela = cons.getRegistro("caracterizacionParcela", new Object[]{idCaracterizacion});
		parcela = bigDecimalToDoubleList(parcela);
		return parcela;
	}
	
	public LinkedList<Object[]> getCaracterizaciones(String CURRtec)
	{
		LinkedList<Object[]> caracterizaciones = cons.getTabla("caracterizacionParcelas", new Object[]{CURRtec});
		caracterizaciones = bigDecimalToDoubleArr(caracterizaciones);
		return caracterizaciones;
	}
	
	public int getIdCaracterizacionByParcela(int idParcela)
	{
		return cons.getId("caracterizacionParcelaId", new Object[]{idParcela});
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
		LinkedList<Object> regador = cons.getRegistro("regador", new Object[]{idRegador});
		regador = bigDecimalToDoubleList(regador);
		return regador;
	}
	
	public LinkedList<Object[]> getRegadores(String CURRresp)
	{
		return cons.getTabla("regadores", new Object[]{CURRresp});
	}
	
	public LinkedList<String> getRespRegadores(String padronParcela)
	{
		return cons.getListado("regadoresResp", new Object[]{padronParcela});
	}
	
	public LinkedList<Object[]> getRegadoresByParcela(int idParcela)
	{
		return cons.getTabla("regadoresByParcela", new Object[]{idParcela});
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

	public LinkedList<Object> getRiego(int idRiego)
	{
		LinkedList<Object> riego = cons.getRegistro("riego", new Object[]{idRiego});
		riego = bigDecimalToDoubleList(riego);
		return riego;
	}

	public LinkedList<Object[]> getListaRiego(int idRiego)
	{
		LinkedList<Object[]> listaRiego = cons.getTabla("riegoLista", new Object[]{idRiego});
		listaRiego = bigDecimalToDoubleArr(listaRiego);
		listaRiego.remove();
		return listaRiego;
	}
	
	public LinkedList<Object[]> getRiegos(String CURRresp)
	{
		return cons.getTabla("riegos", new Object[]{CURRresp});
	}
	
	public LinkedList<Object[]> getRiegosByParcela(int idParcela)
	{
		return cons.getTabla("riegosByParcela", new Object[]{idParcela});
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
		parcela = bigDecimalToDoubleList(parcela);
		return parcela;
	}
	
	public LinkedList<Object[]> getPruebasRiego(String CURRresp)
	{
		return cons.getTabla("pruebasRiego", new Object[]{CURRresp});
	}
	//endregion
	
	//region Reportes de Visita
	public String registrarReporte(LinkedList<Object> valores)
	{
		return ins.insertarRegistro("reporteVisita", valores);
	}

	public String modificarReporte(LinkedList<Object> valores)
	{
		return ins.modificarRegistro("reporteVisita", valores);
	}

	public LinkedList<Object> getReporte(int idReporte)
	{
		LinkedList<Object> reporte = cons.getRegistro("reporteVisita", new Object[]{idReporte});
		reporte = bigDecimalToDoubleList(reporte);
		return reporte;
	}
	
	public LinkedList<Object[]> getReportes(String CURRtec)
	{
		LinkedList<Object[]> reporte = cons.getTabla("reportesVisita", new Object[]{CURRtec});
		reporte = bigDecimalToDoubleArr(reporte);
		return reporte;
	}
	
	public LinkedList<Object[]> getReportesByParcela(int idParcela)
	{
		return cons.getTabla("reportesVisitaByParcela", new Object[]{idParcela});
	}
	//endregion
	
	//region Reportes
	public int getCantRiegos()
	{
		return cons.getId("getCantRiegos", new Object[]{});
	}
	
	public LinkedList<String> getCultivos()
	{
		return cons.getListado("getCultivos", new Object[]{});
	}
	
	public ResultSet getReporteLaminas(String filtrado)
	{
		return cons.getReporte("rptLaminasRendimiento", new Object[]{filtrado});
	}
	
	public ResultSet getReporteCultivos(String filtrado)
	{
		return cons.getReporte("rptCultivos", new Object[]{filtrado});
	}
	//endregion
	
	//region Resultados
	public LinkedList<Object> getResultadoGeneral(String orgCuenca, String dtoRiego, String modRiego, String currST, String currRT)
	{
		LinkedList<Object> resultado = cons.getRegistro("resultadoGeneral", new Object[]{ orgCuenca, dtoRiego, modRiego, currST, currRT });
		resultado = bigDecimalToDoubleList(resultado);
		return resultado;
	}
	
	public LinkedList<Object> getResultadoProduccion(String orgCuenca, String dtoRiego, String modRiego, String currST, String currRT, String cultivo)
	{
		LinkedList<Object> resultado = cons.getRegistro("resultadoProduccion", new Object[]{ orgCuenca, dtoRiego, modRiego, currST, currRT, cultivo });
		resultado = bigDecimalToDoubleList(resultado);
		return resultado;
	}
	
	public LinkedList<Object> getResultadoHidrometrico(String orgCuenca, String dtoRiego, String modRiego, String currST, String currRT, String cultivo)
	{
		LinkedList<Object> resultado = cons.getRegistro("resultadoHidrometrico", new Object[]{ orgCuenca, dtoRiego, modRiego, currST, currRT, cultivo });
		resultado = bigDecimalToDoubleList(resultado);
		return resultado;
	}
	
	public LinkedList<Object> getResultadoIndicadores(String orgCuenca, String dtoRiego, String modRiego, String currST, String currRT)
	{
		LinkedList<Object> resultado = cons.getRegistro("resultadoIndicadores", new Object[]{ orgCuenca, dtoRiego, modRiego, currST, currRT });
		resultado = bigDecimalToDoubleList(resultado);
		return resultado;
	}
	
	public LinkedList<Object[]> getIndicadoresRendimiento(String orgCuenca, String dtoRiego, String modRiego, String currST, String currRT)
	{
		return cons.getTabla("resultadoSubReporteIndicadores", new Object[]{ orgCuenca, dtoRiego, modRiego, currST, currRT });
	}
	
	public ResultSet getResultadoSubReporteIndicadores(String orgCuenca, String dtoRiego, String modRiego, String currST, String currRT)
	{
		return cons.getReporte("resultadoSubReporteIndicadores", new Object[]{ orgCuenca, dtoRiego, modRiego, currST, currRT });
	}
	
	public ResultSet getResultadoSubReporte(String orgCuenca, String dtoRiego, String modRiego, String currST, String currRT, String cultivo)
	{
		return cons.getReporte("resultadoSubReporte", new Object[]{ orgCuenca, dtoRiego, modRiego, currST, currRT, cultivo });
	}
	
//	public LinkedList<String> getOC(String currRT, String currST, String dtoRiego)
//	{
//		return cons.getListado("oc", new Object[]{ currRT, currST, dtoRiego });
//	}
//	
//	public LinkedList<String> getDR(String currRT, String currST, String orgCuenca)
//	{
//		return cons.getListado("dr", new Object[]{ currRT, currST, orgCuenca });
//	}
//	
//	public LinkedList<String> getST(String currRT, String dtoRiego, String orgCuenca)
//	{
//		return cons.getListado("st", new Object[]{ currRT, dtoRiego, orgCuenca });
//	}
//	
//	public LinkedList<String> getRT(String currST, String dtoRiego, String orgCuenca)
//	{
//		return cons.getListado("rt", new Object[]{ currST, dtoRiego, orgCuenca });
//	}
	
	public LinkedList<String> getDRByOC(String orgCuenca)
	{
		return cons.getListado("drByOC", new Object[]{ orgCuenca });
	}
	
	public LinkedList<String> getSTByDR(String dtoRiego)
	{
		return cons.getListado("stByDR", new Object[]{ dtoRiego });
	}
	
	public LinkedList<String> getRTByDR(String dtoRiego)
	{
		return cons.getListado("rtByDR", new Object[]{ dtoRiego });
	}
	
	public LinkedList<String> getSTByMR(String modRiego)
	{
		return cons.getListado("stByMR", new Object[]{ modRiego });
	}
	
	public LinkedList<String> getRTByMR(String modRiego)
	{
		return cons.getListado("rtByMR", new Object[]{ modRiego });
	}
	
	public LinkedList<String> getRTByST(String currST)
	{
		return cons.getListado("rtByST", new Object[]{ currST });
	}
	
	public LinkedList<String> getRTBySTyDR(String currST, String dtoRiego)
	{
		return cons.getListado("rtBySTyDR", new Object[]{ currST, dtoRiego });
	}
	
	public LinkedList<String> getTecnicosByTipo(int tipo)
	{
		return cons.getListado("tecnicosByTipo", new Object[]{ tipo });
	}
	
	public LinkedList<String> getCultivosByDR(String dtoRiego)
	{
		return cons.getListado("cultivosByDR", new Object[]{ dtoRiego });
	}
	
	public LinkedList<String> getCultivosByMR(String modRiego)
	{
		return cons.getListado("cultivosByMR", new Object[]{ modRiego });
	}
	
	public LinkedList<String> getCultivosByST(String currST)
	{
		return cons.getListado("cultivosByST", new Object[]{ currST });
	}
	
	public LinkedList<String> getCultivosByRT(String currRT)
	{
		return cons.getListado("cultivosByRT", new Object[]{ currRT });
	}
	//endregion
	
	//region Conversión de tipos
	private LinkedList<Object> bigDecimalToDoubleList(LinkedList<Object> lista)
	{
		for(int i=0; i<lista.size(); i++)
			if(lista.get(i) instanceof BigDecimal)
				lista.set(i, Double.parseDouble((BigDecimal)lista.get(i) + ""));
		return lista;
	}
	
	private LinkedList<Object[]> bigDecimalToDoubleArr(LinkedList<Object[]> lista)
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
	
	public LinkedList<String> getLicenciaturas()
	{
		return cons.getListado("formacion", new Object[]{});
	}
	
	public LinkedList<String> getInstituciones()
	{
		return cons.getListado("instituciones", new Object[]{});
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
	
	public LinkedList<String> getDtoRiego(String organismo)
	{
		return cons.getListado("dtoRiego", new Object[]{ organismo });
	}
	
	public LinkedList<String> getModuloRiego()
	{
		return cons.getListado("modRiego", new Object[]{});
	}
	
	public LinkedList<String> getModuloRiego(String distrito)
	{
		return cons.getListado("modRiego", new Object[]{ distrito });
	}
	
	public LinkedList<String> getAllSRL()
	{
		return cons.getListado("allSRL", new Object[]{});
	}
	
	public LinkedList<String> getSRL(String distrito)
	{
		return cons.getListado("SRL", new Object[]{ distrito });
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
	
	public LinkedList<String> getPropiedadParcela()
	{
		return cons.getListado("propiedadParcela", new Object[]{});
	}
	
	public LinkedList<String> getDatosRiegoDisponibles()
	{
		return cons.getListado("datosRiegoDisponibles", new Object[]{});
	}
	//endregion
	
	
	
	


	
	
	
}