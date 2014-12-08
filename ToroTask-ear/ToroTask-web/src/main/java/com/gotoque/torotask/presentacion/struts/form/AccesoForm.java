package com.gotoque.torotask.presentacion.struts.form;

import org.apache.struts.action.ActionForm;

public class AccesoForm extends ActionForm {

	private static final long serialVersionUID = 9075285230975663092L;
	String correo;
	String clave;
	
	
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	

}
