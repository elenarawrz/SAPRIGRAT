package com.saprigrat.enums;

public enum TipoUsuario
{
	/**
	 * <i>Administrador General</i>
	 */
	ADMINISTRADOR_GENERAL(-1),
	/**
	 * <i>Administrador de Distrito</i>
	 */
	ADMINISTRADOR_DISTRITO(0),
	/**
	 * <i>Supervisor Técnico</i>
	 */
	SUPERVISOR_TECNICO(1),
	/**
	 * <i>Responsable Técnico</i>
	 */
	RESPONSABLE_TECNICO(2),
	/**
	 * <i>Usuario</i>
	 */
	PRODUCTOR(3),
	/**
	 * <i>Regador</i>
	 */
	REGADOR(4);
	
	private int value;

	private TipoUsuario(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return this.value;
	}
	
	public static TipoUsuario getValue(String description)
	{
		if (description.equals("Administrador General"))
			return ADMINISTRADOR_GENERAL;
		else
			if (description.equals("Administrador de Distrito"))
				return ADMINISTRADOR_DISTRITO;
			else
				if (description.equals("Supervisor Técnico"))
					return SUPERVISOR_TECNICO;
				else
					if (description.equals("Responsable Técnico"))
						return RESPONSABLE_TECNICO;
					else
						if (description.equals("Usuario"))
							return PRODUCTOR;
						else
							return REGADOR;
	}
	
	@Override
	public String toString()
	{
		String tipo = "";
		switch (this)
		{
			case ADMINISTRADOR_GENERAL: tipo = "Administrador General"; break;
			case ADMINISTRADOR_DISTRITO: tipo = "Administrador de Distrito"; break;
			case SUPERVISOR_TECNICO: tipo = "Supervisor Técnico"; break;
			case RESPONSABLE_TECNICO: tipo = "Responsable Técnico"; break;
			case PRODUCTOR: tipo = "Usuario"; break;
			case REGADOR: tipo = "Regador"; break;
		}
		return tipo;
	}
	
	public String getPlural()
	{
		String tipo = "";
		switch (this)
		{
			case ADMINISTRADOR_GENERAL: tipo = "Administradores Generales"; break;
			case ADMINISTRADOR_DISTRITO: tipo = "Administradores de Distrito"; break;
			case SUPERVISOR_TECNICO: tipo = "Supervisores Técnicos"; break;
			case RESPONSABLE_TECNICO: tipo = "Responsables Técnicos"; break;
			case PRODUCTOR: tipo = "Usuarios"; break;
			case REGADOR: tipo = "Regadores"; break;
		}
		return tipo;
	}
}