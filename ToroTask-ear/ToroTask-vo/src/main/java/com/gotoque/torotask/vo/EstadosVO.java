package com.gotoque.torotask.vo;

import java.io.Serializable;

public class EstadosVO implements Serializable {

	private static final long serialVersionUID = -2983051990076915253L;

	String id;
	String text;
	String estado;
	String idEstado;
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(String idEstado) {
		this.idEstado = idEstado;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
