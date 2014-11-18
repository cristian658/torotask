package com.gotoque.torotask.vo;

import java.io.Serializable;
import java.util.Date;

public class ComentarioVO implements Serializable{

	private static final long serialVersionUID = 4509607877442286330L;
	
		String idComentario;
		String comentario;
		String fecha;
		String usuario;
		String idtarea;
		
		public String getComentario() {
			return comentario;
		}
		public void setComentario(String comentario) {
			this.comentario = comentario;
		}
		public String getFecha() {
			return fecha;
		}
		public void setFecha(String fecha) {
			this.fecha = fecha;
		}
		public String getIdComentario() {
			return idComentario;
		}
		public void setIdComentario(String idComentario) {
			this.idComentario = idComentario;
		}
		public String getIdtarea() {
			return idtarea;
		}
		public void setIdtarea(String idtarea) {
			this.idtarea = idtarea;
		}
		public String getUsuario() {
			return usuario;
		}
		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}

		
	
}
