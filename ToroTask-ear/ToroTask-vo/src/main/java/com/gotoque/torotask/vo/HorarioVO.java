package com.gotoque.torotask.vo;

import java.io.Serializable;

public class HorarioVO implements Serializable {

	private static final long serialVersionUID = -7421008979231234842L;
	
	
	String idDia;
	String dia;
	String inicio1;
	String termino1;
	String inicio2;
	String termino2;
	EstadosVO estadoVO;
	
	
	public String getIdDia() {
		return idDia;
	}
	public void setIdDia(String idDia) {
		this.idDia = idDia;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getInicio1() {
		return inicio1;
	}
	public void setInicio1(String inicio1) {
		this.inicio1 = inicio1;
	}
	public String getInicio2() {
		return inicio2;
	}
	public void setInicio2(String inicio2) {
		this.inicio2 = inicio2;
	}
	public String getTermino1() {
		return termino1;
	}
	public void setTermino1(String termino1) {
		this.termino1 = termino1;
	}
	public String getTermino2() {
		return termino2;
	}
	public void setTermino2(String termino2) {
		this.termino2 = termino2;
	}
	public EstadosVO getEstadoVO() {
		return estadoVO;
	}
	public void setEstadoVO(EstadosVO estadoVO) {
		this.estadoVO = estadoVO;
	}

	
	
		
	
}
