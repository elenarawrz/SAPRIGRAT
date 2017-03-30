package com.saprigrat.enums;

public enum TipoResultado
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
	 * <i>Supervisor Técnico</i>
	 */
	SUPERVISOR_TECNICO(3),
	/**
	 * <i>Responsable Técnico</i>
	 */
	RESPONSABLE_TECNICO(4),
	/**
	 * <i>General</i>
	 */
	GENERAL(5),
	/**
	 * <i>Producción</i>
	 */
	PRODUCCION(6),
	/**
	 * <i>Hidrométrico</i>
	 */
	HIDROMETRICO(7),
	/**
	 * <i>Indicadores</i>
	 */
	INDICADORES(8);
	
	private int value;

	private TipoResultado(int value)
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
			case SUPERVISOR_TECNICO: tipo = "Supervisor Técnico"; break;
			case RESPONSABLE_TECNICO: tipo = "Responsable Técnico"; break;
			case GENERAL: tipo = "General"; break;
			case PRODUCCION: tipo = "Produccion"; break;
			case HIDROMETRICO: tipo = "Hidrometrico"; break;
			case INDICADORES: tipo = "Indicadores"; break;
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
			case SUPERVISOR_TECNICO: tipo = "Supervisor Técnico"; break;
			case RESPONSABLE_TECNICO: tipo = "Responsable Técnico"; break;
		}
		return tipo;
	}
}