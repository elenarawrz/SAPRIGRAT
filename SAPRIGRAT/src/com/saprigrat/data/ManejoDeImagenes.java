package com.saprigrat.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.vaadin.server.FileResource;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

@SuppressWarnings("serial")
public class ManejoDeImagenes implements Receiver, SucceededListener
{
	public File img;
	Image contenedor;
	public ManejoDeImagenes(Image cont)
	{
		contenedor = cont;
	}
	@Override
	public void uploadSucceeded(SucceededEvent event)
	{
		contenedor.setSource(new FileResource(img));
		contenedor.setAlternateText(img.getAbsolutePath());
	}

	@Override
	public OutputStream receiveUpload(String filename, String mimeType)
	{
		FileOutputStream fos = null;
		try
		{
			//crear el directorio
			img = File.createTempFile("saprigrat", ".tmp");
			fos = new FileOutputStream(img);
		}
		catch (final java.io.IOException e)
		{
			Notification.show("No se pudo abrir la imagen.", Notification.Type.ERROR_MESSAGE);
            return null;
        }
        return fos;
	}
}