package com.saprigrat.enums;

public enum TipoReporte
{
	/**
	 * <i>Usuarios y Productores</i>
	 */
	USUARIOS_PRODUCTORES(0),
	/**
	 * <i>Laminas y Rendimiento</i>
	 */
	LAMINAS_RENDIMIENTO(1),
	/**
	 * <i>Cultivos</i>
	 */
	CULTIVOS(2);
	
	private int value;

	private TipoReporte(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return this.value;
	}
	
	public static TipoReporte getValue(String description)
	{
		if (description.equals("Reporte de Usuarios y Productores"))
			return USUARIOS_PRODUCTORES;
		else
			if (description.equals("Reporte de Laminas y Rendimiento"))
				return LAMINAS_RENDIMIENTO;
			else
				if (description.equals("Reporte de Cultivos"))
					return CULTIVOS;
				else
					return null;
	}
	
	@Override
	public String toString()
	{
		String tipo = "";
		switch (this)
		{
			case USUARIOS_PRODUCTORES: tipo = "Reporte de Usuarios y Productores"; break;
			case LAMINAS_RENDIMIENTO: tipo = "Reporte de Laminas y Rendimiento"; break;
			case CULTIVOS: tipo = "Reporte de Cultivos"; break;
		}
		return tipo;
	}
}