package com.saprigrat.ui.forms.reportes;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import com.saprigrat.data.Utilerias;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.ImageBanner;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.constants.Stretching;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;

public class ReporteDinamico
{
	public JasperPrint getReport(LinkedList<String[]> columns, String title, String subtitle, JRResultSetDataSource dataSource)
	{
		Utilerias u = new Utilerias();
		try
		{
			DynamicReport dynamicReport = getReport(columns, title, subtitle);
			JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dynamicReport, new ClassicLayoutManager(), dataSource);
			return jp;
		}
		catch (ColumnBuilderException e) { u.notificar("columnbuilder", null, u.MSJ_ERROR); }
		catch (JRException e) {
			u.notificar("jrex", null, u.MSJ_ERROR); }
		catch (ClassNotFoundException e) { u.notificar("classnotfound", null, u.MSJ_ERROR); }
		return null;
	}

	private Style createHeaderStyle()
	{
		StyleBuilder sb = new StyleBuilder(true);
		sb.setFont(Font.VERDANA_SMALL_BOLD);
		sb.setBorder(Border.THIN());
		//sb.setBorderBottom(Border.PEN_2_POINT());
		sb.setBorderColor(Color.BLACK);
		sb.setBackgroundColor(new Color(21, 33, 85));
		sb.setTextColor(Color.WHITE);
		sb.setHorizontalAlign(HorizontalAlign.CENTER);
		sb.setVerticalAlign(VerticalAlign.MIDDLE);
		sb.setTransparency(Transparency.OPAQUE);
		sb.setStretching(Stretching.RELATIVE_TO_TALLEST_OBJECT);
		sb.setPadding(5);
		return sb.build();
	}

	private Style createDetailTextStyle()
	{
		StyleBuilder sb = new StyleBuilder(true);
		sb.setFont(Font.VERDANA_SMALL);
		sb.setTextColor(Color.BLACK);
		sb.setHorizontalAlign(HorizontalAlign.LEFT);
		sb.setVerticalAlign(VerticalAlign.MIDDLE);
		sb.setStretching(Stretching.RELATIVE_TO_TALLEST_OBJECT);
		sb.setPaddingLeft(5);
		sb.setPaddingRight(5);
		sb.setPaddingTop(2);
		sb.setPaddingBottom(2);
		return sb.build();
	}

	private Style createDetailNumberStyle()
	{
		StyleBuilder sb = new StyleBuilder(true);
		sb.setFont(Font.VERDANA_SMALL);
		sb.setTextColor(Color.BLACK);
		sb.setHorizontalAlign(HorizontalAlign.RIGHT);
		sb.setVerticalAlign(VerticalAlign.MIDDLE);
		sb.setStretching(Stretching.RELATIVE_TO_TALLEST_OBJECT);
		sb.setPaddingLeft(5);
		sb.setPaddingRight(5);
		sb.setPaddingTop(2);
		sb.setPaddingBottom(2);
		return sb.build();
	}

	private Style createOddRowStyle()
	{
		StyleBuilder oddRowStyle = new StyleBuilder(true);
		//oddRowStyle.setBorder(Border.NO_BORDER());
		oddRowStyle.setBackgroundColor(new Color(230,230,230));
		oddRowStyle.setTransparency(Transparency.OPAQUE);
		return oddRowStyle.build();
	}

	@SuppressWarnings("rawtypes")
	private AbstractColumn createColumn(String property, String title, Class type, int width, Style headerStyle, Style detailStyle, boolean isNumber) throws ColumnBuilderException
	{
		ColumnBuilder columnState = ColumnBuilder.getNew()
												  .setColumnProperty(property, type.getName())
												  .setTitle(title)
												  .setWidth(width)
												  .setStyle(detailStyle)
												  .setHeaderStyle(headerStyle);
		if(isNumber)
			columnState.setPattern("#,##0.00");
		
		return columnState.build();
	}

	private DynamicReport getReport(LinkedList<String[]> columns, String title, String subtitle) throws ColumnBuilderException, ClassNotFoundException
	{
		DynamicReportBuilder report = new DynamicReportBuilder();
		Style headerStyle = createHeaderStyle();
		Style detailTextStyle = createDetailTextStyle();
		Style detailNumberStyle = createDetailNumberStyle();
		int widthCol = Page.Page_Letter_Landscape().getWidth() / columns.size();
		
		for(String[]col : columns)
		{
			if(col[2].equals("num"))
				report.addColumn(createColumn(col[0], col[1], Double.class, widthCol, headerStyle, detailNumberStyle, true));
			else
				report.addColumn(createColumn(col[0], col[1], String.class, widthCol, headerStyle, detailTextStyle, false));
		}
		
		Style titleStyle = new StyleBuilder(true).setHorizontalAlign(HorizontalAlign.CENTER)
												 .setFont(new Font(20, Font._FONT_GEORGIA, true))
												 .setPadding(10).build(),
			  subTitleStyle = new StyleBuilder(true).setHorizontalAlign(HorizontalAlign.CENTER)
			  										.setFont(new Font(Font.MEDIUM, Font._FONT_GEORGIA, true))
			  										.setPadding(10).build(),
			  footerStyle = new StyleBuilder(true).setFont(new Font(Font.SMALL, Font._FONT_GEORGIA, false)).build();
		
		report.setPageSizeAndOrientation(Page.Page_Letter_Landscape())
			  .setUseFullPageWidth(true)
			  .setMargins(30, 30, 30, 30)
			  .setPrintBackgroundOnOddRows(true)
			  .setOddRowBackgroundStyle(createOddRowStyle())
			  .setWhenResourceMissingThrowException()
			  .setTitle(title)
			  .setTitleStyle(titleStyle)
			  .setSubtitle(subtitle)
			  .setSubtitleStyle(subTitleStyle)
			  .setFooterVariablesHeight(80)
			  .addAutoText(new SimpleDateFormat("'Fecha de emisión:'  dd 'de' MMMM 'de' yyyy',' hh:mm:ss").format(new Date()), AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_LEFT, 300, footerStyle)
			  .addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_RIGHT, 300, 25, footerStyle)
			  .addFirstPageImageBanner("C:\\Users\\avalle\\Dropbox\\Negocios\\Proyectos Freelance\\SAPRIGRAT\\Resources\\Imágenes\\Logos\\Grandes\\RIGRAT v3.jpg", 110, 50, ImageBanner.ALIGN_RIGHT);
		
		return report.build();
	}
}