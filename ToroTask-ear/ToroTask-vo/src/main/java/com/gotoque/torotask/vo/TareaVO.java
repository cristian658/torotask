package com.gotoque.torotask.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class TareaVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6707274218352999691L;

	int Id;

	String idtarea;

	String tarea;

	String descripcion;

	String idproyecto;

	String fecha_inicio;

	String fechaTerminoEstimada;

	String fechaTerminoReal;

	Vector integrantes;

	Vector comentarios;

	Vector adjuntos;

	ProyectoVO proyectoVO;

	UsuarioVO usuarioVO;

	String estado;

	int totalDias = 0;

	int diasTareas = 0;

	double porcentaje = 0D;

	String summary;

	String expanded;

	String usuario;

	String idUsuario;

	String proyecto = "";

	String cierreDiasEstimada;

	String cierreDiasReal;

	String totalDiasEstimada;

	String totalDiasReal;

	public String getCierreDiasEstimada() {
		return cierreDiasEstimada;
	}

	public void setCierreDiasEstimada(String cierreDiasEstimada) {
		this.cierreDiasEstimada = cierreDiasEstimada;
	}

	public String getCierreDiasReal() {
		return cierreDiasReal;
	}

	public void setCierreDiasReal(String cierreDiasReal) {
		this.cierreDiasReal = cierreDiasReal;
	}

	public String getExpanded() {
		return expanded;
	}

	public void setExpanded(String expanded) {
		this.expanded = expanded;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public UsuarioVO getUsuarioVO() {
		return usuarioVO;
	}

	public ProyectoVO getProyectoVO() {
		return proyectoVO;
	}

	public void setProyectoVO(ProyectoVO proyectoVO) {
		this.proyectoVO = proyectoVO;
	}

	public void setUsuarioVO(UsuarioVO usuarioVO) {
		this.usuarioVO = usuarioVO;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Vector getAdjuntos() {
		return adjuntos;
	}

	public void setAdjuntos(Vector adjuntos) {
		this.adjuntos = adjuntos;
	}

	public Vector getComentarios() {
		return comentarios;
	}

	public void setComentarios(Vector comentarios) {
		this.comentarios = comentarios;
	}

	public Vector getIntegrantes() {
		return integrantes;
	}

	public String getTotalDiasEstimada() {
		return totalDiasEstimada;
	}

	public void setTotalDiasEstimada(String totalDiasEstimada) {
		this.totalDiasEstimada = totalDiasEstimada;
	}

	public String getTotalDiasReal() {
		return totalDiasReal;
	}

	public void setTotalDiasReal(String totalDiasReal) {
		this.totalDiasReal = totalDiasReal;
	}

	public void setIntegrantes(Vector integrantes) {
		this.integrantes = integrantes;
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

	public int getDiasTareas() {
		return diasTareas;
	}

	public void setDiasTareas(int diasTareas) {
		this.diasTareas = diasTareas;
	}

	public double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}

	public int getTotalDias() {
		return totalDias;
	}

	public void setTotalDias(int totalDias) {
		this.totalDias = totalDias;
	}

	public String getProyecto() {
		return proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

}
