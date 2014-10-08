package com.saprigrat.enums;

public enum SectorReporte
{
	/**
	 * <i>Organismo de Cuenca</i>
	 */
	ORGANISMO_CUENCA(0),
	/**
	 * <i>Distrito de Riego</i>
	 */
	DISTRITO_RIEGO(1),
	/**
	 * <i>Módulo de Riego</i>
	 */
 	MODULO_RIEGO(2),
	/**
	 * <i>Unidad de Riego</i>
	 */
	UNIDAD_RIEGO(3),
	/**
	 * <i>Estado</i>
	 */
	ESTADO(4);
	
	private int value;

	private SectorReporte(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return this.value;
	}
	
	@Override
	public String toString()
	{
		String tipo = "";
		switch (this)
		{
			case ORGANISMO_CUENCA: tipo = "Organismo de Cuenca"; break;
			case DISTRITO_RIEGO: tipo = "Distrito de Riego"; break;
			case MODULO_RIEGO: tipo = "Módulo de Riego"; break;
			case UNIDAD_RIEGO: tipo = "Unidad de Riego"; break;
			case ESTADO: tipo = "Estado"; break;
		}
		return tipo;
	}
	
	public String getPlural()
	{
		String tipo = "";
		switch (this)
		{
			case ORGANISMO_CUENCA: tipo = "Organismos de Cuenca"; break;
			case DISTRITO_RIEGO: tipo = "Distritos de Riego"; break;
			case MODULO_RIEGO: tipo = "Módulos de Riego"; break;
			case UNIDAD_RIEGO: tipo = "Unidades de Riego"; break;
			case ESTADO: tipo = "Estados"; break;
		}
		return tipo;
	}
}