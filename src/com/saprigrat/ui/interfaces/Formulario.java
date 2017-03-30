package com.saprigrat.ui.interfaces;

import com.vaadin.ui.Component;

public interface Formulario extends Component
{
	final int MODO_LISTA = 0;
	final int MODO_AGREGAR = 1;
	final int MODO_MODIFICAR = 2;

	public void setTipo (int tipo);
	public void inicializar ();
}
