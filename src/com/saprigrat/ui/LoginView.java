package com.saprigrat.ui;

import java.util.LinkedList;

import com.saprigrat.data.Datos;
import com.saprigrat.data.Usuario;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;


@SuppressWarnings("serial")
public class LoginView extends CustomComponent
{

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private VerticalLayout verLFormulario;
	@AutoGenerated
	private AbsoluteLayout absLCredenciales;
	@AutoGenerated
	private Button btnGo;
	@AutoGenerated
	private PasswordField txtPass;
	@AutoGenerated
	private TextField txtUser;
	@AutoGenerated
	private Embedded imgLogo;
	private Usuario usuario;
	private Datos datos;
	
	public LoginView()
	{
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		usuario = new Usuario();
		datos = new Datos();
		
		txtUser.setValue("admin");
		txtPass.setValue("pass");
		btnGo.setClickShortcut(KeyCode.ENTER);
		btnGo.addClickListener( new ClickListener()
								{
									@Override
									public void buttonClick(ClickEvent event)
									{
										if(checkPass())
										{
											mainLayout.removeAllComponents();
											mainLayout.addComponent(new MainView(usuario));
											Notification.show("Bienvenido " + usuario.getNombre(), Notification.Type.TRAY_NOTIFICATION);
										}
										else
											Notification.show("El nombre de usuario no coincide con la contrase�a.", Notification.Type.WARNING_MESSAGE);
									}
								});
	}

	private boolean checkPass()
	{
		LinkedList<Object> info = datos.autenticacion(txtUser.getValue(), txtPass.getValue());
		if(!info.isEmpty())
		{
			usuario.setTipo((Integer)info.remove());
			usuario.setCURR((String)info.remove());
			usuario.setNombre((String)info.remove());
			usuario.setApPaterno((String)info.remove());
			usuario.setApMaterno((String)info.remove());
			usuario.setEstado((String)info.remove());
			return true;
		}
		return false;
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("-1px");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(false);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("-1px");
		setHeight("-1px");
		
		// verLFormulario
		verLFormulario = buildVerLFormulario();
		mainLayout.addComponent(verLFormulario);
		mainLayout.setComponentAlignment(verLFormulario, new Alignment(20));
		
		return mainLayout;
	}

	@AutoGenerated
	private VerticalLayout buildVerLFormulario() {
		// common part: create layout
		verLFormulario = new VerticalLayout();
		verLFormulario.setImmediate(false);
		verLFormulario.setWidth("-1px");
		verLFormulario.setHeight("-1px");
		verLFormulario.setMargin(true);
		verLFormulario.setSpacing(true);
		
		// imgLogo
		imgLogo = new Embedded();
		imgLogo.setImmediate(false);
		imgLogo.setWidth("350px");
		imgLogo.setHeight("80px");
		imgLogo.setSource(new ThemeResource("img/header.png"));
		imgLogo.setType(1);
		imgLogo.setMimeType("image/png");
		verLFormulario.addComponent(imgLogo);
		
		// absLCredenciales
		absLCredenciales = buildAbsLCredenciales();
		verLFormulario.addComponent(absLCredenciales);
		
		return verLFormulario;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsLCredenciales() {
		// common part: create layout
		absLCredenciales = new AbsoluteLayout();
		absLCredenciales.setImmediate(false);
		absLCredenciales.setWidth("350px");
		absLCredenciales.setHeight("160px");
		
		// txtUser
		txtUser = new TextField();
		txtUser.setCaption("Usuario");
		txtUser.setImmediate(false);
		txtUser.setWidth("300px");
		txtUser.setHeight("25px");
		absLCredenciales.addComponent(txtUser, "top:30.0px;left:20.0px;");
		
		// txtPass
		txtPass = new PasswordField();
		txtPass.setCaption("Contrase�a");
		txtPass.setImmediate(false);
		txtPass.setWidth("300px");
		txtPass.setHeight("25px");
		absLCredenciales.addComponent(txtPass, "top:75.0px;left:20.0px;");
		
		// btnGo
		btnGo = new Button();
		btnGo.setCaption("Ingresar");
		btnGo.setImmediate(true);
		btnGo.setWidth("80px");
		btnGo.setHeight("26px");
		absLCredenciales.addComponent(btnGo, "top:125.0px;left:130.0px;");
		
		return absLCredenciales;
	}

}