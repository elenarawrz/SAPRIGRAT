package com.saprigrat.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.saprigrat.data.AdvancedFileDownloader.AdvancedDownloaderListener;
import com.saprigrat.data.AdvancedFileDownloader.DownloaderEvent;
import com.saprigrat.data.Utilerias;
import com.saprigrat.enums.Formato;
import com.saprigrat.enums.TipoResultado;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;

public class InterfazReportes
{
	public void getReport(TipoResultado tipoReporte, Map<String, Object> parametros, ResultSet resultset, final Formato formato, Label lbl) 
	{
		try
		{
			String filePath = System.getProperty("user.home") + "/RptTmp/reporte" + tipoReporte + new SimpleDateFormat("ddMMyyHHmmss").format(new Date()) + formato;
			String rptPath = System.getProperty("user.home") + "/RptTemplates/";

			if(resultset != null)
				parametros.put("subreportDS", new JRResultSetDataSource(resultset));
			
			compilarReporte(rptPath + (tipoReporte != TipoResultado.INDICADORES ? "rptDetalle" : "rptIndicadoresRendimiento"));
			compilarReporte(rptPath += "rpt" + tipoReporte);
			
			//JRResultSetDataSource dataSource = resultset != null ? new JRResultSetDataSource(resultset) : null;
			final JasperPrint jasperPrint = JasperFillManager.fillReport(rptPath + ".jasper", parametros);//, dataSource);
			if(jasperPrint != null)
			{
				{
					File f = new File("tmp");
					if(!f.isDirectory()) f.mkdir();
				}
				
				switch(formato)
				{
					case PDF:
						@SuppressWarnings("serial")
						StreamSource source = new StreamSource()
						{
							public InputStream getStream()
							{
								try { return new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jasperPrint)); }
								catch(Exception e) { return null; }
							}
						};
						JasperExportManager.exportReportToPdfFile(jasperPrint, filePath);
						StreamResource resource = new StreamResource(source, filePath);
						Embedded e = new Embedded("", resource);
						e.setType(Embedded.TYPE_BROWSER);
						e.setMimeType("application/pdf");
						e.setSizeFull();
						UI.getCurrent().addWindow(getWindow(e));
						break;
						
					case EXCEL:
						JRXlsExporter exporterXLS = new JRXlsExporter();
						exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
						exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, filePath);
						exporterXLS.exportReport();
						break;
						
					case WORD:
						JRDocxExporter exporterDOCX = new JRDocxExporter();
						exporterDOCX.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
						exporterDOCX.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, filePath);
						exporterDOCX.exportReport();
						break;
				}
				lbl.setValue(filePath);
			}
		}
		catch (Exception e)
		{
			new Utilerias().msjReporteNoGenerado();
			e.printStackTrace();
		}
	}
	
	private void compilarReporte(String path) throws JRException
	{
		File rpt = new File(path + ".jasper");
		//if(!(rpt.exists() && !rpt.isDirectory()))
			JasperCompileManager.compileReportToFile(path + ".jrxml", path + ".jasper");
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
}
