package com.gotoque.torotask.vo;

import java.io.Serializable;

public class IntegrantesVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String idusuario;
	String idtarea;
	
	public String getIdtarea() {
		return idtarea;
	}
	public void setIdtarea(String idtarea) {
		this.idtarea = idtarea;
	}
	public String getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(String idusuario) {
		this.idusuario = idusuario;
	}
	
	
	
}
