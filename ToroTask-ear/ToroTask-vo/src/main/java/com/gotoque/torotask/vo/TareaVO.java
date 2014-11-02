package com.gotoque.torotask.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class TareaVO implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6707274218352999691L;
	Integer Id;
	String idtarea;
	String tarea;
	String descripcion;
	String idproyecto;
	String fecha_inicio;
	String fechaTerminoEstimada;
	String fechaTerminoReal;
	ArrayList usuarioVO;
	String estado;
	Integer totalDias = 0;
	Integer diasTareas = 0;
	Double porcentaje = 0D;
	String Summary;
	String Expanded;
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getExpanded() {
		return Expanded;
	}
	public void setExpanded(String expanded) {
		Expanded = expanded;
	}
	public Double getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}
	public String getSummary() {
		return Summary;
	}
	public void setSummary(String summary) {
		Summary = summary;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getFecha_inicio() {
		return fecha_inicio;
	}
	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	public String getFechaTerminoEstimada() {
		return fechaTerminoEstimada;
	}
	public void setFechaTerminoEstimada(String fechaTerminoEstimada) {
		this.fechaTerminoEstimada = fechaTerminoEstimada;
	}
	public String getFechaTerminoReal() {
		return fechaTerminoReal;
	}
	public void setFechaTerminoReal(String fechaTerminoReal) {
		this.fechaTerminoReal = fechaTerminoReal;
	}
	public String getIdtarea() {
		return idtarea;
	}
	public void setIdtarea(String idtarea) {
		this.idtarea = idtarea;
	}
	public String getIdproyecto() {
		return idproyecto;
	}
	public void setIdproyecto(String idproyecto) {
		this.idproyecto = idproyecto;
	}
	public String getTarea() {
		return tarea;
	}
	public void setTarea(String tarea) {
		this.tarea = tarea;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public ArrayList getUsuarioVO() {
		return usuarioVO;
	}
	public void setUsuarioVO(ArrayList usuarioVO) {
		this.usuarioVO = usuarioVO;
	}
	public Integer getDiasTareas() {
		return diasTareas;
	}
	public void setDiasTareas(Integer diasTareas) {
		if(diasTareas!=null){
			this.diasTareas = diasTareas;
		}
	}
	public Integer getTotalDias() {
		return totalDias;
	}
	public void setTotalDias(Integer totalDias) {
		if(totalDias!=null){
			this.totalDias = totalDias;
		}
	}
	
}
