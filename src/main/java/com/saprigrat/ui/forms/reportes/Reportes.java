package com.saprigrat.ui.forms.reportes;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import com.saprigrat.entities.Usuario;
import com.saprigrat.enums.SectorReporte;
import com.saprigrat.enums.TipoReporte;
import com.saprigrat.enums.TipoUsuario;
import com.saprigrat.ui.interfaces.Formulario;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class Reportes extends CustomComponent implements Formulario
{
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
	//region controles autogenerados
	
	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Button btnGenerar;
	@AutoGenerated
	private AbsoluteLayout absoluteLayout_4;
	@AutoGenerated
	private PopupDateField datRangoFin;
	@AutoGenerated
	private PopupDateField datRangoIni;
	@AutoGenerated
	private AbsoluteLayout absoluteLayout_3;
	@AutoGenerated
	private ComboBox cmbSector;
	@AutoGenerated
	private ComboBox cmbTipoSector;
	@AutoGenerated
	private AbsoluteLayout absoluteLayout_1;
	@AutoGenerated
	private ComboBox cmbTecnico;
	@AutoGenerated
	private ComboBox cmbNivel;
	@AutoGenerated
	private AbsoluteLayout absoluteLayout_2;
	@AutoGenerated
	private ComboBox cmbTipoReporte;
	//endregion
	private Usuario usuario;
	
	//region eventos
	private ClickListener generarListener = new ClickListener()
			{
				@Override
				public void buttonClick(ClickEvent event)
				{
					if(validar())
					{
						int colCount = 0;
						LinkedList<String[]> columns = new LinkedList<String[]>();
						String titulo = "", subtitulo = "", filtrado = "";
						ResultSet rows = null;
						
						TipoUsuario nivel = TipoUsuario.getValue(cmbNivel.getValue().toString());
						
						filtrado = "admin";
						columns.add(new String[]{ "nombre_admin", "Administrador de Distrito", "", "50" });
						if(nivel != TipoUsuario.ADMINISTRADOR_DISTRITO)
						{
							filtrado = "ST";
							columns.add(new String[]{ "nombre_ST", "Supervisor Técnico", "", "50" });
							if(nivel != TipoUsuario.SUPERVISOR_TECNICO)
							{
								filtrado = "RT";
								columns.add(new String[]{ "nombre_RT", "Responsable Técnico", "", "50" });
								if(nivel != TipoUsuario.RESPONSABLE_TECNICO)
								{
									filtrado = "prod";
									columns.add(new String[]{ "nombre_prod", "Usuario", "", "50" });
								}
							}
						}
						
						switch(TipoReporte.getValue(cmbTipoReporte.getValue().toString()))
						{
							case LAMINAS_RENDIMIENTO:
								 int cantRiegos = datos.getCantRiegos();
								 
								 columns.add(new String[]{ "supAtendida", "Superficie Atendida (ha)", "num", "20" });
								 for (int i = 1; i <= cantRiegos; i++)
									 columns.add(new String[]{ "riego_" + i, "Riego " + i + "° (cm)", "num", "25" });
								 columns.add(new String[]{ "sumLam", "Lámina Aplicada (cm)", "num", "25" });
								 columns.add(new String[]{ "volumen", "Volumen", "num", "30" });
								 columns.add(new String[]{ "promRend", "Rendimiento (t/ha)", "num", "20" });
								 
								 titulo = "Reporte de Láminas de Riego";
								 rows = datos.getReporteLaminas(filtrado);
								 break;
													  
							case CULTIVOS:
								 LinkedList<String> cultivos = datos.getCultivos();
								 
								 columns.add(new String[]{ "supAtendida", "Superficie Atendida (ha)", "num", "20" });
								 for (int i = 1; i <= cultivos.size(); i++)
									 columns.add(new String[]{ "cultivo_" + i, cultivos.get(i - 1) + " (ha)", "num", "25" });
								 
								 titulo = "Reporte de Cultivos";
								 rows = datos.getReporteCultivos(filtrado);
								 break;
						}
						
						colCount = columns.size();
						runReport(colCount, columns, titulo, subtitulo, rows);
					}
				}
			};
	//endregion

	public Reportes(Usuario user)
	{
		usuario = user;
	}

	@Override
	public void inicializar()
	{
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		cmbNivel.setTextInputAllowed(false);
		if(usuario.getTipo() != TipoUsuario.RESPONSABLE_TECNICO.getValue())
		{
			if(usuario.getTipo() != TipoUsuario.SUPERVISOR_TECNICO.getValue())
				cmbNivel.addItem(TipoUsuario.ADMINISTRADOR_DISTRITO.toString());
			cmbNivel.addItem(TipoUsuario.SUPERVISOR_TECNICO.toString());
		}
		cmbNivel.addItem(TipoUsuario.RESPONSABLE_TECNICO.toString());
		cmbNivel.addItem(TipoUsuario.PRODUCTOR.toString());
		
		cmbTipoReporte.setTextInputAllowed(false);
		for (TipoReporte tipo : TipoReporte.values())
		     cmbTipoReporte.addItem(tipo.toString());
		
		cmbTipoSector.setTextInputAllowed(false);
		for (SectorReporte sector : SectorReporte.values())
		     cmbTipoSector.addItem(sector.toString());
		

		btnGenerar.addClickListener(generarListener);
	}
	
	@Override
	public void setTipo(int modoOperacion)
	{
		
	}
	
	private boolean validar()
	{
		if(cmbTipoReporte.getValue() == null)
			return u.notificar("Se requiere especificar el tipo de reporte", cmbTipoReporte, u.MSJ_ERROR);
		if(cmbNivel.getValue() == null)
			return u.notificar("Se requiere especificar el nivel administrativo", cmbNivel, u.MSJ_ERROR);
		//if(cmbTipoSector.getValue() == null)
			//return u.notificar("Se requiere especificar el tipo de sector", cmbTipoSector, u.MSJ_ERROR);
		
		return true;
	}
	
	public void runReport(int columnCount, LinkedList<String[]> columns, String titulo, String subtitulo, ResultSet resultset) 
	{
		try
		{
			JRResultSetDataSource dataSource = new JRResultSetDataSource(resultset);
			final JasperPrint jasperPrint = new ReporteDinamico().getReport(columns, titulo, subtitulo, dataSource);
			if(jasperPrint != null)
			{
				StreamSource source = new StreamSource()
				{
					public InputStream getStream()
					{
						try { return new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jasperPrint)); }
						catch(Exception e) { return null; }
					}
				};
				StreamResource resource = new StreamResource(source, "reporte.pdf");
				Embedded e = new Embedded("", resource);
				e.setType(Embedded.TYPE_BROWSER);
				e.setMimeType("application/pdf");
				e.setSizeFull();
				
				UI.getCurrent().addWindow(getWindow(e));
			}
			//JasperExportManager.exportReportToPdfFile(jasperPrint, System.getProperty("user.home") + "\\RptLaminas.pdf");
		}
		catch (Exception e)
		{
			u.msjReporteNoGenerado();
		}
	}
	
	private Window getWindow(Embedded e)
	{
		Window w = new Window("Reporte SAPRIGRAT");
		w.setWidth("90%");
		w.setHeight("90%");
		w.setModal(true);
		w.center();
		w.setContent(e);
		return w;
	}
	//region UI

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("-1px");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("-1px");
		setHeight("-1px");
		
		// absoluteLayout_2
		absoluteLayout_2 = buildAbsoluteLayout_2();
		mainLayout.addComponent(absoluteLayout_2);
		
		// absoluteLayout_1
		absoluteLayout_1 = buildAbsoluteLayout_1();
		mainLayout.addComponent(absoluteLayout_1);
		
		// absoluteLayout_3
		absoluteLayout_3 = buildAbsoluteLayout_3();
		mainLayout.addComponent(absoluteLayout_3);
		
		// absoluteLayout_4
		absoluteLayout_4 = buildAbsoluteLayout_4();
		mainLayout.addComponent(absoluteLayout_4);
		
		// btnGenerar
		btnGenerar = new Button();
		btnGenerar.setCaption("Generar Reporte");
		btnGenerar.setImmediate(true);
		btnGenerar.setWidth("-1px");
		btnGenerar.setHeight("-1px");
		mainLayout.addComponent(btnGenerar);
		mainLayout.setComponentAlignment(btnGenerar, new Alignment(24));
		
		return mainLayout;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsoluteLayout_2() {
		// common part: create layout
		absoluteLayout_2 = new AbsoluteLayout();
		absoluteLayout_2.setImmediate(false);
		absoluteLayout_2.setWidth("701px");
		absoluteLayout_2.setHeight("55px");
		
		// cmbTipoReporte
		cmbTipoReporte = new ComboBox();
		cmbTipoReporte.setCaption("Tipo de Reporte");
		cmbTipoReporte.setImmediate(false);
		cmbTipoReporte.setWidth("400px");
		cmbTipoReporte.setHeight("25px");
		absoluteLayout_2
				.addComponent(cmbTipoReporte, "top:28.0px;left:10.0px;");
		
		return absoluteLayout_2;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsoluteLayout_1() {
		// common part: create layout
		absoluteLayout_1 = new AbsoluteLayout();
		absoluteLayout_1.setImmediate(false);
		absoluteLayout_1.setWidth("701px");
		absoluteLayout_1.setHeight("45px");
		
		// cmbNivel
		cmbNivel = new ComboBox();
		cmbNivel.setCaption("Nivel Administrativo");
		cmbNivel.setImmediate(false);
		cmbNivel.setWidth("250px");
		cmbNivel.setHeight("25px");
		absoluteLayout_1.addComponent(cmbNivel, "top:18.0px;left:10.0px;");
		
		// cmbTecnico
		cmbTecnico = new ComboBox();
		cmbTecnico.setCaption("Nombre (opcional)");
		cmbTecnico.setImmediate(false);
		cmbTecnico.setWidth("410px");
		cmbTecnico.setHeight("25px");
		absoluteLayout_1.addComponent(cmbTecnico, "top:18.0px;left:280.0px;");
		
		return absoluteLayout_1;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsoluteLayout_3() {
		// common part: create layout
		absoluteLayout_3 = new AbsoluteLayout();
		absoluteLayout_3.setEnabled(false);
		absoluteLayout_3.setImmediate(false);
		absoluteLayout_3.setVisible(false);
		absoluteLayout_3.setWidth("701px");
		absoluteLayout_3.setHeight("45px");
		
		// cmbTipoSector
		cmbTipoSector = new ComboBox();
		cmbTipoSector.setCaption("Sector");
		cmbTipoSector.setEnabled(false);
		cmbTipoSector.setImmediate(false);
		cmbTipoSector.setVisible(false);
		cmbTipoSector.setWidth("250px");
		cmbTipoSector.setHeight("25px");
		absoluteLayout_3.addComponent(cmbTipoSector, "top:18.0px;left:10.0px;");
		
		// cmbSector
		cmbSector = new ComboBox();
		cmbSector.setCaption("Nombre (opcional)");
		cmbSector.setEnabled(false);
		cmbSector.setImmediate(false);
		cmbSector.setVisible(false);
		cmbSector.setWidth("410px");
		cmbSector.setHeight("25px");
		absoluteLayout_3.addComponent(cmbSector, "top:18.0px;left:280.0px;");
		
		return absoluteLayout_3;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsoluteLayout_4() {
		// common part: create layout
		absoluteLayout_4 = new AbsoluteLayout();
		absoluteLayout_4.setEnabled(false);
		absoluteLayout_4.setImmediate(false);
		absoluteLayout_4.setVisible(false);
		absoluteLayout_4.setWidth("701px");
		absoluteLayout_4.setHeight("55px");
		
		// datRangoIni
		datRangoIni = new PopupDateField();
		datRangoIni.setCaption("Fecha Inicio");
		datRangoIni.setEnabled(false);
		datRangoIni.setImmediate(false);
		datRangoIni.setVisible(false);
		datRangoIni.setWidth("200px");
		datRangoIni.setHeight("25px");
		absoluteLayout_4.addComponent(datRangoIni, "top:18.0px;left:10.0px;");
		
		// datRangoFin
		datRangoFin = new PopupDateField();
		datRangoFin.setCaption("Fecha Fin");
		datRangoFin.setEnabled(false);
		datRangoFin.setImmediate(false);
		datRangoFin.setVisible(false);
		datRangoFin.setWidth("200px");
		datRangoFin.setHeight("25px");
		absoluteLayout_4.addComponent(datRangoFin, "top:18.0px;left:230.0px;");
		
		return absoluteLayout_4;
	}
	
	//endregion
}