package com.gotoque.torotask.vo;

import java.io.Serializable;

public class UsuarioVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -724375680409422788L;

	String idUsuario;
	String nombre;
	String apellidoPaterno;
	String apellidoMaterno;
	String correo;
	String anexo;
	String cargo;
	String clave;
	PerfilVO perfilVO;
	EstadosVO estadoVO;
	int tareasTotal = 0;
	int tareasRealizadas = 0;
	int tareasActivas = 0;
	int tareasAtrazadas = 0;
	
	public int getTareasActivas() {
		return tareasActivas;
	}

	public EstadosVO getEstadoVO() {
		return estadoVO;
	}

	public void setEstadoVO(EstadosVO estadoVO) {
		this.estadoVO = estadoVO;
	}

	public void setTareasActivas(int tareasActivas) {
		this.tareasActivas = tareasActivas;
	}

	public int getTareasAtrazadas() {
		return tareasAtrazadas;
	}

	public void setTareasAtrazadas(int tareasAtrazadas) {
		this.tareasAtrazadas = tareasAtrazadas;
	}

	public int getTareasRealizadas() {
		return tareasRealizadas;
	}

	public void setTareasRealizadas(int tareasRealizadas) {
		this.tareasRealizadas = tareasRealizadas;
	}

	public int getTareasTotal() {
		return tareasTotal;
	}

	public void setTareasTotal(int tareasTotal) {
		this.tareasTotal = tareasTotal;
	}
	
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public PerfilVO getPerfilVO() {
		return perfilVO;
	}

	public void setPerfilVO(PerfilVO perfilVO) {
		this.perfilVO = perfilVO;
	}

	public String getAnexo() {
		return anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
