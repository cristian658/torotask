package com.gotoque.torotask.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class ProyectoVO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2177454218207447662L;
	String id;
	String idproyectos;
	String proyecto;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdproyectos() {
		return idproyectos;
	}
	public void setIdproyectos(String idproyectos) {
		this.idproyectos = idproyectos;
	}
	public String getProyecto() {
		return proyecto;
	}
	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}
	
}
