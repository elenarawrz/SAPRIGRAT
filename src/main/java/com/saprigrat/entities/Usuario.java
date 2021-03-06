package com.saprigrat.entities;

public class Usuario
{
	private int tipo,
				idTecnico;
	private String  CURR,
					nombre,
					apPaterno,
					apMaterno,
					estado;
	
	public int  ADMINISTRADOR_GENERAL = -1,
				ADMINISTRADOR = 0,
				SUPERVISOR = 1,
				RESPONSABLE = 2,
				PRODUCTOR = 3,
				REGADOR = 4;
	
	public int getTipo()
	{
		return tipo;
	}
	
	public void setTipo(int tipo)
	{
		this.tipo = tipo;
	}

	public int getIdTecnico()
	{
		return idTecnico;
	}
	
	public void setIdTecnico(int idTecnico)
	{
		this.idTecnico = idTecnico;
	}

	public String getCURR()
	{
		return CURR;
	}
	
	public void setCURR(String CURR)
	{
		this.CURR = CURR;
	}

	public String getNombre()
	{
		return nombre;
	}
	
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public String getApPaterno()
	{
		return apPaterno;
	}
	
	public void setApPaterno(String apPaterno)
	{
		this.apPaterno = apPaterno;
	}

	public String getApMaterno()
	{
		return apMaterno;
	}
	
	public void setApMaterno(String apMaterno)
	{
		this.apMaterno = apMaterno;
	}

	public String getEstado()
	{
		return estado;
	}
	
	public void setEstado(String estado)
	{
		this.estado = estado;
	}
}