package com.saprigrat.ui.interfaces;

import com.saprigrat.entities.Usuario;
import com.vaadin.ui.Component;

public interface FormParcela extends Component
{
	public void init (Usuario user);
	public void inicializar ();
	public void quitarListeners ();
	public void setParcela (int idParcela, String padron);
	public void setResponsable (String responsable);
	public void setProductor (String productor);
}
