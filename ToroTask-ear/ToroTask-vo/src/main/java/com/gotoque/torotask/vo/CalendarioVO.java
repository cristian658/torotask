package com.gotoque.torotask.vo;

import java.io.Serializable;
import java.sql.Date;

public class CalendarioVO implements Serializable {

	String idCalendario;
	String fecha;
	String motivo;
	EstadosVO estadosVO;
	

	public EstadosVO getEstadosVO() {
		return estadosVO;
	}
	public void setEstadosVO(EstadosVO estadosVO) {
		this.estadosVO = estadosVO;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getIdCalendario() {
		return idCalendario;
	}
	public void setIdCalendario(String idCalendario) {
		this.idCalendario = idCalendario;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}


}
