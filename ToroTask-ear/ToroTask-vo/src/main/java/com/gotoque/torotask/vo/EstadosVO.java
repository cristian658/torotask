package com.gotoque.torotask.vo;

import java.io.Serializable;

public class EstadosVO implements Serializable{
	
	
	private static final long serialVersionUID = -2983051990076915253L;
	
	String id;
	String text;
	
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
