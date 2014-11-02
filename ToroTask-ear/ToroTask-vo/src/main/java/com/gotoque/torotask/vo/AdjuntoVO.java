package com.gotoque.torotask.vo;

import java.io.Serializable;
import java.sql.Date;

public class AdjuntoVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7421008979238234842L;
	String idadjunto;
	String name;
	String nombre;
	String size;
	String extension;
	String fecha;
	String contentType;
	String idtarea;
	
	public String getIdtarea() {
		return idtarea;
	}
	public void setIdtarea(String idtarea) {
		this.idtarea = idtarea;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getIdadjunto() {
		return idadjunto;
	}
	public void setIdadjunto(String idadjunto) {
		this.idadjunto = idadjunto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	
	
}
