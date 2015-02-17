package com.saprigrat.enums;

public enum Formato
{
	PDF(0),
	EXCEL(1),
 	WORD(2);
	
	private int value;

	private Formato(int value)
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
		String extension = "";
		switch (this)
		{
			case PDF: extension = ".pdf"; break;
			case EXCEL: extension = ".xls"; break;
			case WORD: extension = ".docx"; break;
		}
		return extension;
	}
}