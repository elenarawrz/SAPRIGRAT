package com.saprigrat.data;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Locale;

import org.joda.time.LocalDate;
import org.vaadin.addons.tuningdatefield.TuningDateField;

import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;

public class Utilerias
{
	public Notification.Type MSJ_ERROR = Notification.Type.ERROR_MESSAGE,
							 MSJ_ADVERTENCIA = Notification.Type.WARNING_MESSAGE,
							 MSJ_BANDEJA = Notification.Type.TRAY_NOTIFICATION,
							 MSJ_INFORMATIVO = Notification.Type.HUMANIZED_MESSAGE;
	
	public void setupCombo(ComboBox cmb, LinkedList<String> lista, String placeholder, FilteringMode filtrado, boolean permiteNulo, boolean permiteInput)
	{
		if(lista != null)
			llenarCombo(cmb, lista);
		if(placeholder != null)
			cmb.setInputPrompt(placeholder);
		if(filtrado != null)
			cmb.setFilteringMode(filtrado);
		cmb.setNullSelectionAllowed(permiteNulo);
		cmb.setTextInputAllowed(permiteInput);
	}
	
	/**
	 * Método que llena un combo con tantos elementos como existan en la lista proporcionada
	 * @param cmb
	 *            : el combo a llenar
	 * @param elementos
	 *            : elementos pertenecientes a la lista
	 */
	public void llenarCombo(ComboBox cmb, LinkedList<String>elementos)
	{
		cmb.removeAllItems();
		for(String elemento : elementos)
			if(elemento != null)
				cmb.addItem(elemento);
	}
	
	/**
	 * Método que llena un combo con un solo elemento, dicho combo tendrá esa opción seleccionada y sin opción de editar
	 * @param cmb
	 *            : el combo a llenar
	 * @param item
	 *            : único item a agregar a la lista
	 */
	public void llenarCombo(ComboBox cmb, String item)
	{
		cmb.removeAllItems();
		if(!item.isEmpty())
		{
			cmb.addItem(item);
			cmb.select(item);
		}
		cmb.setEnabled(false);
	}
	
	public void setupCalendario(TuningDateField cal, boolean readOnly)
	{
		cal.setWeekendDisabled(false);
		cal.setDateTextReadOnly(readOnly);
		cal.setLocale(getLocale());
	}
	
	public Locale getLocale()
	{
		return new Locale("es", "MX");
	}
	
	public Date fechaUtilToSQL(java.util.Date fechaUtil)
	{
		if(fechaUtil == null)
			return null;
		else
			return new Date(fechaUtil.getTime());
	}
	
	public java.util.Date fechaSQLToUtil(Date fechaSQL)
	{
		if(fechaSQL == null)
			return null;
		else
			return new java.util.Date(fechaSQL.getTime());
	}
	
	public LocalDate fechaSQLToLocalDate(Date fecha)
	{
		if(fecha == null)
			return null;
		else
			return new LocalDate(fecha.getTime());
	}
	
	public Double stringToDouble(String str)
	{
		if(str.isEmpty())
			return null;
		else
			return Double.parseDouble(str);
	}
	
	public String doubleToString(Object dbl)
	{
		if(dbl == null || dbl == "")
			return "";
		else
			return decimalFormat((Double)dbl);
	}
	
	public Double getDouble(Object dbl)
	{
		if(dbl == null || dbl == "")
			return 0D;
		else
			return (Double)dbl;
	}
	
	public Long stringToLong(String str)
	{
		if(str.isEmpty())
			return null;
		else
			return Long.parseLong(str);
	}
	
	public String longToString(Object lng)
	{
		if(lng == null || lng == "")
			return "";
		else
			return Long.toString((Long)lng);
	}
	
	public String decimalFormat(Double dbl)
	{
		return new DecimalFormat("#,##0.00").format(dbl);
	}
	
	public String llenarTabla(Table tbl, LinkedList<Object[]> rows, String elemento)
	{
		int rowCount = rows.size() - 1;
		tbl.removeAllItems();
		if(!rows.isEmpty())
		{
			setHeaders(tbl, (String[]) rows.remove());
			while(!rows.isEmpty())
				tbl.addItem(rows.remove(), null);
		}
		else
		{
			//Notification.show("No se encontró ningún " + elemento, Notification.Type.HUMANIZED_MESSAGE);
			rowCount = 0;
		}
		if (tbl.containsId("ID"))
			tbl.setColumnCollapsed("ID",true);
		return rowCount + (rowCount != 1 ? " registros encontrados" : " registro encontrado");
	}
													
	private void setHeaders(Table tbl, String[] encabezado)
	{
		String[] ids = tbl.getColumnHeaders();
		for(Object col : ids)
			tbl.removeContainerProperty(col);
		for(int i=0; i<encabezado.length; i++)
			tbl.addContainerProperty(encabezado[i], Object.class, null);
	}
	
	public RegexpValidator getRegExDecimal()
	{
		return new RegexpValidator("^[0-9]+(\\.[0-9]+)?$", "Debe ser un valor numérico");
	}
	
	public RegexpValidator getRegExEntero()
	{
		return new RegexpValidator("^[0-9]+$", "Debe ser un valor numérico entero");
	}
	
	public RegexpValidator getRegExEmail()
	{
		return new RegexpValidator( "^[0-9a-zA-Z\\._-]+@[0-9a-zA-Z_-]+(\\.[0-9a-zA-Z_-]+)+$",
									"Debe cumplir con el formato 'usuario@dominio.com'");
	}
	
	public RegexpValidator getRegExCP()
	{
		return new RegexpValidator("^[0-9][0-9][0-9][0-9][0-9]$", "Debe ser un número de 5 dígitos");
	}
	
	public RegexpValidator getRegExPass()
	{
		return new RegexpValidator("^[0-9a-zA-Z]+$", "La contraseña solo puede contener números y letras");
	}
	
	public String setDecimalFormat(String formato, int numero)
	{
		return new DecimalFormat(formato).format(numero);
	}
	
	/**
     * Muestra una notificación con el texto y formato especificados 
     * @param notificacion
     *            : es el mensaje a mostrar.
     * @param field
     *            : es el campo en el que se pondrá el foco al cerrar la notificación, puede ser null.
     * @param tipoMensaje
     *            : es el tipo de notificación a mostrar, puede ser <i>MSJ_ERROR</i>,
     *            <i>MSJ_ADVERTENCIA</i>, <i>MSJ_BANDEJA</i> o <i>MSJ_INFORMATIVO</i>
     */
	@SuppressWarnings("rawtypes")
	public boolean notificar(String notificacion, AbstractField field, Notification.Type tipoMensaje)
	{
		Notification.show(notificacion, tipoMensaje);
		if(field != null)
			field.focus();
		return false;
	}
	
	/**
     * Muestra el siguiente mensaje en bandeja:
     *  <b>"<i>X</i> se guardó correctamente"</b>
     * 
     * @param elemento
     *            : es el elemento que se está guardando, este valor reemplazaría la X.
     * @param msjExtra
     *            : es algún mensaje extra que se quiera concatenar al final, puede ser una cadena vacía.
     */
	public void msjGuardado(String elemento, String msjExtra)
	{
		msjCorrecto(elemento + " se guardó", msjExtra);
	}
	
	/**
     * Muestra el siguiente mensaje de advertencia:
     * <b>"Se guardó la información del <i>X</i>, pero sus credenciales para ingresar al sistema no, comuníquese con el administrador"</b>
     * 
     * @param elemento
     *            : es el tipo de persona que se está guardando, este valor reemplazaría la X.
     */
	public void msjGuardadoSinLogin(String elemento)
	{
		notificar("Se guardó la información del " + elemento + ", pero sus credenciales para ingresar al sistema no, comuníquese con el administrador", null, MSJ_ADVERTENCIA);
	}
	
	/**
     * Muestra el siguiente mensaje de error:
     * <b>"Error al guardar <i>X</i>, comuníquese con el administrador"</b>
     * 
     * @param elemento
     *            : es el elemento que se está guardando, este valor reemplazaría la X.
     */
	public void msjNoGuardado(String elemento)
	{
		msjErrorTecnico("guardar " + elemento);
	}

	/**
     * Muestra el siguiente mensaje de error:
     * <b>"No se pudo guardar porque el <i>X</i> proporcionado ya se encuentra registrado"</b>
     * 
     * @param elementoRepetido
     *            : es el elemento que no permite guardar por estar repetido, este valor reemplazaría la X.
     */
	public void msjNoGuardadoRepetido(String elementoRepetido)
	{
		msjErrorRepetido("guardar", elementoRepetido);
	}

	/**
     * Muestra el siguiente mensaje en bandeja:
     *  <b>"<i>X</i> se modificó correctamente"</b>
     * 
     * @param elemento
     *            : es el elemento que se está modificando, este valor reemplazaría la X.
     */
	public void msjModificado(String elemento)
	{
		msjCorrecto(elemento + " se modificó", "");
	}
	
	/**
     * Muestra el siguiente mensaje de error:
     * <b>"Error al modificar <i>X</i>, comuníquese con el administrador"</b>
     * 
     * @param elemento
     *            : es el elemento que se está modificando, este valor reemplazaría la X.
     */
	public void msjNoModificado(String elemento)
	{
		msjErrorTecnico("modificar " + elemento);
	}
	
	/**
     * Muestra el siguiente mensaje de error:
     * <b>"No se pudo modificar porque el <i>X</i> proporcionado ya se encuentra registrado"</b>
     * 
     * @param elementoRepetido
     *            : es el elemento que no permite modificar por estar repetido, este valor reemplazaría la X.
     */
	public void msjNoModificadoRepetido(String elementoRepetido)
	{
		msjErrorRepetido("modificar", elementoRepetido);
	}

	/**
     * Muestra el siguiente mensaje en bandeja:
     *  <b>"<i>X</i> se eliminó correctamente"</b>
     * 
     * @param elemento
     *            : es el elemento que se está eliminando, este valor reemplazaría la X.
     */
	public void msjEliminado(String elemento)
	{
		msjCorrecto(elemento + " se eliminó", "");
	}
	
	/**
     * Muestra el siguiente mensaje de error:
     * <b>"Error al eliminar <i>X</i>, comuníquese con el administrador"</b>
     * 
     * @param elemento
     *            : es el elemento que se está eliminando, este valor reemplazaría la X.
     */
	public void msjNoEliminado(String elemento)
	{
		msjErrorTecnico("eliminar " + elemento);
	}
	
	/**
     * Muestra el siguiente mensaje de error:
     * <b>"Error al obtener el registro, comuníquese con el administrador"</b>
     */
	public void msjRegistroNoObtenido()
	{
		notificar("Error al obtener el registro, comuníquese con el administrador", null, MSJ_ERROR);
	}
	
	/**
     * Muestra el siguiente mensaje de error:
     * <b>"Ocurrió un error al intentar generar el reporte"</b>
     */
	public void msjReporteNoGenerado()
	{
		notificar("Ocurrió un error al intentar generar el reporte", null, MSJ_ERROR);
	}
	
	/**
     * Muestra el siguiente mensaje de advertencia:
     * <b>"No ha seleccionado ningún <i>X</i> a eliminar"</b>
     * 
     * @param elemento
     *            : es el elemento que se pretende eliminar, este valor reemplazaría la X.
     */
	public void msjNoSeleccionEliminar(String elemento)
	{
		notificar("No ha seleccionado ning" + (elemento.equalsIgnoreCase("parcela") ? "una " : "ún ") + elemento + " a eliminar", null, MSJ_ADVERTENCIA);
	}
	
	/**
     * Muestra el siguiente mensaje de advertencia:
     * <b>"Debe seleccionar un filtro para continuar"</b>
     */
	public void msjNoSeleccionFiltros()
	{
		notificar("Debe seleccionar un filtro para continuar", null, MSJ_ADVERTENCIA);
	}
	
	private void msjCorrecto(String accion, String msjExtra)
	{
		notificar(accion + " correctamente" + msjExtra, null, MSJ_BANDEJA);
	}
	
	private void msjErrorRepetido(String accion, String elemento)
	{
		notificar("No se pudo " + accion + " porque el " + elemento + " proporcionado ya se encuentra registrado", null, MSJ_ERROR);
	}
	
	private void msjErrorTecnico(String accion)
	{
		notificar("Error al " + accion + ", comuníquese con el administrador", null, MSJ_ERROR);
	}
}