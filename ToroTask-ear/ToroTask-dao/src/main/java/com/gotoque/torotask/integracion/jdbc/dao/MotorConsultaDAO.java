package com.gotoque.torotask.integracion.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

import com.gotoque.torotask.vo.AdjuntoVO;
import com.gotoque.torotask.vo.CalendarioVO;
import com.gotoque.torotask.vo.ComentarioVO;
import com.gotoque.torotask.vo.EstadosVO;
import com.gotoque.torotask.vo.HorarioVO;
import com.gotoque.torotask.vo.ParametroVO;
import com.gotoque.torotask.vo.PerfilVO;
import com.gotoque.torotask.vo.ProyectoVO;
import com.gotoque.torotask.vo.TareaVO;
import com.gotoque.torotask.vo.UsuarioVO;
import java.util.Hashtable;

public class MotorConsultaDAO {

	private static final String CMD_GET_CONSULTA_TAREAS = "GET_CONSULTA_TAREAS";
	private static final String CMD_GET_INSERTA_TAREAS = "GET_INSERTA_TAREAS";
	private static final String CMD_GET_ELIMINA_TAREAS = "GET_ELIMINA_TAREAS";
	private static final String CMD_GET_ACTUALIZA_TAREAS = "GET_ACTUALIZA_TAREAS";
	private static final String CMD_GET_ELIMINA_INTEGRANTES_X_TAREA = "GET_ELIMINA_INTEGRANTES_X_TAREA";
	private static final String CMD_GET_INSERTAR_INTEGRANTES_X_TAREA = "GET_INSERTAR_INTEGRANTES_X_TAREA";
	private static final String CMD_GET_CONSULTA_INTEGRANTES_X_TAREA = "GET_CONSULTA_INTEGRANTES_X_TAREA";
	private static final String CMD_GET_CONSULTA_INGENIEROS = "GET_CONSULTA_INGENIEROS";
	private static final String CMD_GET_CONSULTA_ESTADOS = "GET_CONSULTA_ESTADOS";
	private static final String CMD_GET_CONSULTA_COMENTARIOS = "GET_CONSULTA_COMENTARIOS";
	private static final String CMD_GET_CONSULTA_ADJUNTOS = "GET_CONSULTA_ADJUNTOS";
	private static final String CMD_SET_INSERTA_COMENTARIO = "SET_INSERTA_COMENTARIO";
	private static final String CMD_SET_INSERTA_AJUNTO = "SET_INSERTA_AJUNTO";
	private static final String CMD_GET_DESCARGAR_AJUNTO = "GET_DESCARGAR_AJUNTO";
	private static final String CMD_GET_CONSULTA_PROYECTOS = "GET_CONSULTA_PROYECTOS";
	private static final String CMD_GET_CONSULTA_INTEGRANTES = "GET_CONSULTA_INTEGRANTES";
	private static final String CMD_SET_INSERTA_PROYECTO = "SET_INSERTA_PROYECTO";
	private static final String CMD_GET_CONSULTA_GANTT_PROYECTOS = "GET_CONSULTA_GANTT_PROYECTOS";
	private static final String CMD_GET_CONSULTA_GANTT_TAREAS = "GET_CONSULTA_GANTT_TAREAS";
	private static final String CMD_GET_CONSULTA_ID_TAREA = "GET_CONSULTA_ID_TAREA";
	private static final String CMD_GET_CONSULTA_ID_PROYECTO = "GET_CONSULTA_ID_PROYECTO";
	private static final String CMD_CMD_GET_CONSULTA_RECURSOS = "GET_CONSULTA_RECURSOS";
	private static final String CMD_GET_CONSULTA_REPORTE_TAREA = "GET_CONSULTA_REPORTE_TAREAS";
	private static final String CMD_GET_CONSULTA_REPORTE_PROYECTO = "GET_CONSULTA_REPORTE_PROYECTO";
	private static final String CMD_GET_CONSULTA_REPORTE = "GET_CONSULTA_REPORTE";
	private static final String CMD_GET_CONSULTA_ID_USUARIO = "GET_CONSULTA_ID_USUARIO";
	private static final String CMD_GET_CONSULTA_CARGOS = "GET_CONSULTA_CARGOS";
	private static final String CMD_GET_CONSULTA_REPORTE_USUARIO = "GET_CONSULTA_REPORTE_USUARIO";
	
	
	private static final String CMD_GET_CONSULTA_USUARIOS = "GET_CONSULTA_USUARIOS";
	private static final String CMD_GET_ACTUALIZA_USUARIOS = "GET_ACTUALIZA_USUARIOS";
	private static final String CMD_GET_ELIMINA_USUARIOS = "GET_ELIMINA_USUARIOS";
	private static final String CMD_GET_CREA_USUARIOS = "GET_CREA_USUARIOS";
	
	private static final String CMD_GET_CONSULTA_CARGO_USUARIO = "GET_CONSULTA_CARGO_USUARIO";
	private static final String CMD_SET_ACTUALIZA_EQUIPO_TRABAJO  ="SET_ACTUALIZA_EQUIPO_TRABAJO";
	private static final String CMD_SET_ELIMINA_EQUIPO_TRABAJO  ="SET_ELIMINA_EQUIPO_TRABAJO";
	private static final String CMD_GET_CONSULTA_EQUIPO = "GET_CONSULTA_EQUIPO";
	
	private static final String CMD_GET_CONSULTA_FERIADOS = "GET_CONSULTA_FERIADOS";
	private static final String CMD_GET_ACTUALIZA_FERIADOS = "GET_ACTUALIZA_FERIADOS";
	private static final String CMD_GET_CREA_FERIADOS = "GET_CREA_FERIADOS";
	
	private static final String CMD_GET_CONSULTA_PARAMETROS = "GET_CONSULTA_PARAMETROS";
	private static final String CMD_GET_CREA_PARAMETROS = "GET_CREA_PARAMETROS";
	private static final String CMD_GET_ACTUALIZA_PARAMETROS = "GET_ACTUALIZA_PARAMETROS";
	
	private static final String CMD_SET_SUBIR_IMAGEN = "SET_SUBIR_IMAGEN";
	private static final String CMD_GET_CONSULTA_CORREO  = "GET_CONSULTA_CORREO";
	
	private static final String CMD_GET_CONSULTA_HORARIO = "GET_CONSULTA_HORARIO";
	private static final String CMD_GET_CONSULTA_ACCESO = "GET_CONSULTA_ACCESO";
	private static final String CMD_GET_CONSULTA_TAREA_IDUSUARIO = "GET_CONSULTA_TAREA_IDUSUARIO";
	private static final String CMD_GET_CONSULTA_REPORTE_TAREAS = "GET_CONSULTA_REPORTE_TAREAS";
	
	public Vector executeQuery(String pCommandService, Hashtable pParameters) {
		Vector Resultado = new Vector();
		if (pCommandService.equals(CMD_GET_CONSULTA_TAREAS)) {
			Resultado = getConsultaTareas(pParameters);
		} else if (pCommandService.equals(CMD_GET_INSERTA_TAREAS)) {
			Resultado = getInsertaTareas(pParameters);
		} else if (pCommandService.equals(CMD_GET_ACTUALIZA_TAREAS)) {
			Resultado = getActualizaTareas(pParameters);
		} else if (pCommandService.equals(CMD_GET_ELIMINA_TAREAS)) {
			Resultado = getEliminaTareas(pParameters);
		} else if (pCommandService.equals(CMD_GET_ELIMINA_INTEGRANTES_X_TAREA)) {
			Resultado = setEliminaIntegrantexTarea(pParameters);
		} else if (pCommandService.equals(CMD_GET_INSERTAR_INTEGRANTES_X_TAREA)) {
			Resultado = setInsertaIntegrantexTarea(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_INTEGRANTES_X_TAREA)) {
			Resultado = setConsultaIntegrantexTarea(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_INGENIEROS)) {
			Resultado = getConsultarIngenieros(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_ESTADOS)) {
			Resultado = getConsultarEstados(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_COMENTARIOS)) {
			Resultado = getConsultaComentarios(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_ADJUNTOS)) {
			Resultado = getConsultaAdjuntos(pParameters);
		} else if (pCommandService.equals(CMD_SET_INSERTA_COMENTARIO)) {
			Resultado = setInsertaComentario(pParameters);
		} else if (pCommandService.equals(CMD_SET_INSERTA_AJUNTO)) {
			Resultado = setInsertaAdjunto(pParameters);
		} else if (pCommandService.equals(CMD_GET_DESCARGAR_AJUNTO)) {
			Resultado = getDescargarAdjunto(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_PROYECTOS)) {
			Resultado = getConsultaProyectos(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_INTEGRANTES)) {
			Resultado = getConsultaIntegrantes(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_REPORTE_TAREA)) {
			Resultado = getConsultaReporteTarea(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_REPORTE_PROYECTO)) {
			Resultado = getConsultaReporteProyecto(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_REPORTE)) {
			Resultado = getConsultaReporte(pParameters);
		} else if (pCommandService.equals(CMD_SET_INSERTA_PROYECTO)) {
			Resultado = setInsertaProyecto(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_GANTT_PROYECTOS)) {
			Resultado = getConsultaGanttProyectos(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_GANTT_TAREAS)) {
			Resultado = getConsultaGanttTareas(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_ID_TAREA)) {
			Resultado = getConsultarIdTarea(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_ID_PROYECTO)) {
			Resultado = getConsultarIdProyecto(pParameters);
		} else if (pCommandService.equals(CMD_CMD_GET_CONSULTA_RECURSOS)) {
			Resultado = getConsultaRecursos(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_ID_USUARIO)) {
			Resultado = getConsultarIdUsuario(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_USUARIOS)) {
			Resultado = getConsultarUsuarios(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_CARGOS)) {
			Resultado = getConsultarCargos(pParameters);
		} else if (pCommandService.equals(CMD_GET_ACTUALIZA_USUARIOS)) {
			Resultado = getActualizaUsuarios(pParameters);
		} else if (pCommandService.equals(CMD_GET_ELIMINA_USUARIOS)) {
			Resultado = getEliminaUsuarios(pParameters);
		} else if (pCommandService.equals(CMD_GET_CREA_USUARIOS)) {
			Resultado = getCreaUsuarios(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_REPORTE_USUARIO)) {
			Resultado = getConsultarReporteUsuario(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_CARGO_USUARIO)) {
			Resultado = getConsultarCargoUsuario(pParameters);
		} else if (pCommandService.equals(CMD_SET_ACTUALIZA_EQUIPO_TRABAJO)) {
			Resultado = setActualizaEquipo(pParameters);
		} else if (pCommandService.equals(CMD_SET_ELIMINA_EQUIPO_TRABAJO)) {
			Resultado = setEliminaEquipo(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_EQUIPO)) {
			Resultado = setConsultaEquipo(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_FERIADOS)) {
			Resultado = setConsultaFeriados(pParameters);
		} else if (pCommandService.equals(CMD_GET_ACTUALIZA_FERIADOS)) {
			Resultado = getActualizaFeriados(pParameters);
		} else if (pCommandService.equals(CMD_GET_CREA_FERIADOS)) {
			Resultado = getCreaFeriados(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_PARAMETROS)) {
			Resultado = getConsultaParametros(pParameters);
		} else if (pCommandService.equals(CMD_GET_CREA_PARAMETROS)) {
			Resultado = getCreaParametros(pParameters);
		} else if (pCommandService.equals(CMD_GET_ACTUALIZA_PARAMETROS)) {
			Resultado = getActualizaParametros(pParameters);
		} else if (pCommandService.equals(CMD_SET_SUBIR_IMAGEN)) {
			Resultado = setSubirImagen(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_CORREO)) {
			Resultado = getConsultaCorreo(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_HORARIO)) {
			Resultado = getConsultaHorario(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_ACCESO)) {
			Resultado = getConsultaAcceso(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_TAREA_IDUSUARIO)) {
			Resultado = getConsultaTareasIdusuario(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_REPORTE_TAREAS)) {
			Resultado = getConsultaReporteTareas(pParameters);
		}
		return Resultado;
	}

	private Vector getConsultaAcceso(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String correo = (String)parameters.get("correo");
			String clave = (String)parameters.get("clave");
			String sql = "select * from usuarios where correo ='"+correo+"' and clave = md5('" + clave + "')";	
			PreparedStatement statement = conex.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				UsuarioVO usuarioVO = new UsuarioVO();
				usuarioVO.setIdUsuario(resultSet.getString(1));
				usuarioVO.setNombre(resultSet.getString(2));
				usuarioVO.setApellidoPaterno(resultSet.getString(3));
				usuarioVO.setApellidoMaterno(resultSet.getString(4));
				usuarioVO.setCorreo(resultSet.getString(5));
				usuarioVO.setAnexo(resultSet.getString(6));
				usuarioVO.setCargo(resultSet.getString(7));
				usuarioVO.setClave(resultSet.getString(8));
				Hashtable param = new Hashtable();
				param.put("idCargo",usuarioVO.getCargo());
				PerfilVO vCargo = (PerfilVO)getConsultarIdCargos(param);
				usuarioVO.setPerfilVO(vCargo);
				param.put("idEstado",resultSet.getString(9));
				EstadosVO vEstado = (EstadosVO)getConsultarIdEstado(param);
				usuarioVO.setEstadoVO(vEstado);
				vResult.addElement(usuarioVO);
			}

		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector getConsultaHorario(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String sql = "SELECT * FROM horarios";
			PreparedStatement statement = conex.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				HorarioVO horarioVO = new HorarioVO();
				horarioVO.setIdDia(resultSet.getString(1));
				horarioVO.setDia(resultSet.getString(2));
				horarioVO.setInicio1(resultSet.getString(3));
				horarioVO.setTermino1(resultSet.getString(4));
				horarioVO.setInicio2(resultSet.getString(5));
				horarioVO.setTermino2(resultSet.getString(6));				
				parameters.put("idEstado",resultSet.getString(7));
				EstadosVO vEstado = (EstadosVO)getConsultarIdEstado(parameters);
				horarioVO.setEstadoVO(vEstado);
				vResult.addElement(horarioVO);
			}

		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	public static String getMd5(String parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String sql = "select md5(?)";	
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, parameters);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()){
				parameters = resultSet.getString(1);
			}
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return parameters;
	}

	private Vector getConsultaCorreo(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String id = (String)parameters.get("id");
			String correo = (String)parameters.get("correo");
			String sql = "";
			if(id.equals("")){
				sql = "select * from usuarios where correo ='"+correo+"'";
			}else{
				sql = "select * from usuarios where idUsuario <> '" + id + "' and correo ='"+correo+"'";	
			}			
			PreparedStatement statement = conex.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				UsuarioVO usuarioVO = new UsuarioVO();
				usuarioVO.setIdUsuario(resultSet.getString(1));
				usuarioVO.setNombre(resultSet.getString(2));
				usuarioVO.setApellidoPaterno(resultSet.getString(3));
				usuarioVO.setApellidoMaterno(resultSet.getString(4));
				usuarioVO.setCorreo(resultSet.getString(5));
				usuarioVO.setAnexo(resultSet.getString(6));
				usuarioVO.setCargo(resultSet.getString(7));
				usuarioVO.setClave(resultSet.getString(8));
				
				Hashtable param = new Hashtable();
				param.put("idCargo",usuarioVO.getCargo());
				PerfilVO vCargo = (PerfilVO)getConsultarIdCargos(param);
				usuarioVO.setPerfilVO(vCargo);
				param.put("idEstado",resultSet.getString(9));
				EstadosVO vEstado = (EstadosVO)getConsultarIdEstado(param);
				usuarioVO.setEstadoVO(vEstado);
				vResult.addElement(usuarioVO);
			}

		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector setSubirImagen(Hashtable pParameters) {
		Vector vResult = new Vector();
/* 
 		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		String contentType = (String) pParameters.get("contentType");
		String idUsuario = (String) pParameters.get("idUsuario");
		InputStream imagen = (FileInputStream)pParameters.get("fileData");
		try {
			String sql = "insert into fotos(contentType,idUsuario,imagen)values(?,?,?)";
			PreparedStatement statement = conex.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, contentType);
			statement.setString(2, idUsuario);
			statement.setBinaryStream(3, imagen, 2);
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next())
				vResult.addElement(new Integer(resultSet.getInt(1)));
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
*/
		return vResult;
	}

	private Vector getConsultaParametros(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String sql = "SELECT * FROM parametros";
			PreparedStatement statement = conex.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ParametroVO proyectoVO = new ParametroVO();
				proyectoVO.setIdParametro(resultSet.getString(1));
				proyectoVO.setParametro(resultSet.getString(2));
				proyectoVO.setGlosa(resultSet.getString(3));
				vResult.addElement(proyectoVO);
			}

		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}
	
	private Vector getCreaParametros(Hashtable pParameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		String parametro = (String) pParameters.get("parametro");
		String glosa = (String) pParameters.get("glosa");
		try {
			String sql = "insert into parametros(parametro,glosa)values(?,?)";
			PreparedStatement statement = conex.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, parametro);
			statement.setString(2, glosa);
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next())
				vResult.addElement(new Integer(resultSet.getInt(1)));
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector getActualizaParametros(Hashtable pParameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String idParametro = (String) pParameters.get("idParametro");
			String parametro = (String) pParameters.get("parametro");
			String glosa = (String) pParameters.get("glosa");
			String sql = "update parametros set parametro = ?,glosa = ? where idparametro = ?";
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, parametro);
			statement.setString(2, glosa);
			statement.setString(3, idParametro);
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next())
				vResult.addElement(new Integer(resultSet.getInt(1)));
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector getActualizaFeriados(Hashtable pParameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String fecha = (String) pParameters.get("fecha");
			String motivo = (String) pParameters.get("motivo");
			String idEstado = (String) pParameters.get("idEstado");
			String idFeriado = (String) pParameters.get("idFeriado");
			String sql = "update feriados set fecha = ?,motivo = ?,idestado = ? where idferiado = ?";
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, fecha);
			statement.setString(2, motivo);
			statement.setString(3, idEstado);
			statement.setString(4, idFeriado);
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next())
				vResult.addElement(new Integer(resultSet.getInt(1)));
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector getCreaFeriados(Hashtable pParameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		String fecha = (String) pParameters.get("fecha");
		String motivo = (String) pParameters.get("motivo");
		String idEstado = (String) pParameters.get("idEstado");
		try {
			String sql = "insert into feriados(fecha,motivo,idEstado)values(?,?,?)";
			PreparedStatement statement = conex.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, fecha);
			statement.setString(2, motivo);
			statement.setString(3, idEstado);
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next())
				vResult.addElement(new Integer(resultSet.getInt(1)));
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector setConsultaFeriados(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String sql = "select * from feriados order by fecha";
			PreparedStatement statement = conex.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				CalendarioVO calendarioVO = new CalendarioVO();
				calendarioVO.setIdCalendario(resultSet.getString(1));
				calendarioVO.setFecha(resultSet.getString(2));
				calendarioVO.setMotivo(resultSet.getString(3));
				parameters.put("idEstado",resultSet.getString(4));
				EstadosVO vEstado = (EstadosVO)getConsultarIdEstado(parameters);
				calendarioVO.setEstadosVO(vEstado);
				vResult.addElement(calendarioVO);
			}
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector setConsultaEquipo(Hashtable pParameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String idJefe = (String) pParameters.get("idJefe");
			String sql = "SELECT b.* FROM equipos a inner join usuarios b on a.analistas = b.idusuario where b.idestado = 1 and a.jefeproyectos = " + idJefe;
			PreparedStatement statement = conex.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				UsuarioVO usuarioVO = new UsuarioVO();
				usuarioVO.setIdUsuario(resultSet.getString(1));
				usuarioVO.setNombre(resultSet.getString(2));
				usuarioVO.setApellidoPaterno(resultSet.getString(3));
				usuarioVO.setApellidoMaterno(resultSet.getString(4));
				usuarioVO.setCorreo(resultSet.getString(5));
				usuarioVO.setAnexo(resultSet.getString(6));
				usuarioVO.setCargo(resultSet.getString(7));
				usuarioVO.setClave(resultSet.getString(8));
				
				Hashtable param = new Hashtable();
				param.put("idCargo",usuarioVO.getCargo());
				PerfilVO vCargo = (PerfilVO)getConsultarIdCargos(param);
				usuarioVO.setPerfilVO(vCargo);
				param.put("idEstado",resultSet.getString(9));
				EstadosVO vEstado = (EstadosVO)getConsultarIdEstado(param);
				usuarioVO.setEstadoVO(vEstado);
				vResult.addElement(usuarioVO);
			}
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector setActualizaEquipo(Hashtable pParameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		String jefe = (String) pParameters.get("jefe");
		String analista = (String) pParameters.get("analista");
		try {
			String sql = "insert into equipos(jefeproyectos,analistas)values(?,?)";
			PreparedStatement statement = conex.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, jefe);
			statement.setString(2, analista);
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next())
				vResult.addElement(new Integer(resultSet.getInt(1)));
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector setEliminaEquipo(Hashtable pParameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		String jefe = (String) pParameters.get("jefe");
		try {
			String sql = "delete from equipos where jefeproyectos = ?";
			PreparedStatement statement = conex.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, jefe);
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next())
				vResult.addElement(new Integer(resultSet.getInt(1)));
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}
	
	private Vector getConsultarCargoUsuario(Hashtable pParameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String idPerfil = (String) pParameters.get("idPerfil");
			String sql = "select * from usuarios where idestado = 1 and idcargo = " + idPerfil;
			PreparedStatement statement = conex.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				UsuarioVO usuarioVO = new UsuarioVO();
				usuarioVO.setIdUsuario(resultSet.getString(1));
				usuarioVO.setNombre(resultSet.getString(2));
				usuarioVO.setApellidoPaterno(resultSet.getString(3));
				usuarioVO.setApellidoMaterno(resultSet.getString(4));
				usuarioVO.setCorreo(resultSet.getString(5));
				usuarioVO.setAnexo(resultSet.getString(6));
				usuarioVO.setCargo(resultSet.getString(7));
				usuarioVO.setClave(resultSet.getString(8));
				
				Hashtable param = new Hashtable();
				param.put("idCargo",usuarioVO.getCargo());
				PerfilVO vCargo = (PerfilVO)getConsultarIdCargos(param);
				usuarioVO.setPerfilVO(vCargo);
				param.put("idEstado",resultSet.getString(9));
				EstadosVO vEstado = (EstadosVO)getConsultarIdEstado(param);
				usuarioVO.setEstadoVO(vEstado);
				vResult.addElement(usuarioVO);
			}
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector getConsultarReporteUsuario(Hashtable pParameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		String idUsuario = (String) pParameters.get("idUsuario");
		String categoria = (String) pParameters.get("categoria");
		String termino = (String) pParameters.get("termino");
		String inicio = (String) pParameters.get("inicio");
		try {
			
			String sql = "";
			if(categoria.equals("Activas"))
				sql = "SELECT b.* FROM torotask.integrantesxtarea a inner join tareas b on a.idtarea = b.idtarea where DATE_FORMAT(fecha_inicio,'%Y%m') >= '"+inicio.substring(0, 6)+"' and DATE_FORMAT(fecha_inicio,'%Y%m') <= '"+termino.substring(0, 6)+"' and b.estado = 1 and a.idusuario = " + idUsuario;
			if(categoria.equals("Realizadas"))
				sql = "SELECT b.* FROM torotask.integrantesxtarea a inner join tareas b on a.idtarea = b.idtarea where DATE_FORMAT(fecha_inicio,'%Y%m') >= '"+inicio.substring(0, 6)+"' and DATE_FORMAT(fecha_inicio,'%Y%m') <= '"+termino.substring(0, 6)+"' and b.estado = 2 and a.idusuario = " + idUsuario;
			if(categoria.equals("Atrazadas"))
				sql = "SELECT b.* FROM torotask.integrantesxtarea a inner join tareas b on a.idtarea = b.idtarea where DATE_FORMAT(fecha_inicio,'%Y%m') >= '"+inicio.substring(0, 6)+"' and fecha_inicio <= now() and a.idusuario = " + idUsuario;
				
			PreparedStatement statement = conex.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				TareaVO tareaVO = new TareaVO();
				tareaVO.setIdtarea(resultSet.getString(1));
				tareaVO.setTarea(resultSet.getString(2));
				tareaVO.setDescripcion(resultSet.getString(3));
				tareaVO.setIdproyecto(resultSet.getString(4));
				tareaVO.setFecha_inicio(resultSet.getString(5));
				tareaVO.setFechaTerminoEstimada(resultSet.getString(6));
				tareaVO.setFechaTerminoReal(resultSet.getString(7));
				tareaVO.setIdUsuario(resultSet.getString(8));
				tareaVO.setEstado(resultSet.getString(9));
				vResult.addElement(tareaVO);
			}
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector getCreaUsuarios(Hashtable pParameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		
		String nombre 		= (String) pParameters.get("nombre");
		String correo 		= (String) pParameters.get("correo");
		String appaterno 	= (String) pParameters.get("apellidoPaterno");
		String apmaterno	= (String) pParameters.get("apellidoMaterno");
		String anexo 		= (String) pParameters.get("anexo");
		String idcargo 		= (String) pParameters.get("idPerfil");
		String idEstado 	= (String) pParameters.get("idEstado");
		String clave 		= (String) pParameters.get("clave");
		try {
			String sql = "insert into usuarios(nombre,appaterno,apmaterno,correo,anexo,idcargo,clave,idestado)values(?,?,?,?,?,?,md5(?),?)";
			PreparedStatement statement = conex.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, nombre);
			statement.setString(2, appaterno);
			statement.setString(3, apmaterno);
			statement.setString(4, correo);
			statement.setString(5, anexo);
			statement.setString(6, idcargo);
			statement.setString(7, (clave));
			statement.setString(8, idEstado);
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next())
				vResult.addElement(new Integer(resultSet.getInt(1)));
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector getEliminaUsuarios(Hashtable pParameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		String idUsuario = (String) pParameters.get("idUsuario");
		try {
			String sql = "update usuarios set usuario_estado = 2 where idusuario = ?";
			PreparedStatement statement = conex.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, idUsuario);
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next())
				vResult.addElement(new Integer(resultSet.getInt(1)));
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector getActualizaUsuarios(Hashtable pParameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		String idUsuario = (String) pParameters.get("idUsuario");
		String nombre = (String) pParameters.get("nombre");
		String correo = (String) pParameters.get("correo");
		String appaterno = (String) pParameters.get("apellidoPaterno");
		String apmaterno = (String) pParameters.get("apellidoMaterno");
		String anexo = (String) pParameters.get("anexo");
		String idcargo = (String) pParameters.get("idPerfil");
		String clave = (String) pParameters.get("clave");
		String idEstado = (String) pParameters.get("idEstado");
		try {
			String sql = "update usuarios set nombre = ?,appaterno = ?,apmaterno = ?,correo = ?,anexo = ?,idcargo = ?,clave = ?,idestado = ? where idusuario = ?";
			PreparedStatement statement = conex.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, nombre);
			statement.setString(2, appaterno);
			statement.setString(3, apmaterno);
			statement.setString(4, correo);
			statement.setString(5, anexo);
			statement.setString(6, idcargo);
			statement.setString(7, clave);
			statement.setString(8, idEstado);
			statement.setString(9, idUsuario);

			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next())
				vResult.addElement(new Integer(resultSet.getInt(1)));
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}	
	
	private Vector getConsultarCargos(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String sql = "SELECT * FROM cargo";
			PreparedStatement statement = conex.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				PerfilVO perfilVO = new PerfilVO();
				perfilVO.setIdPerfil(resultSet.getString(1));
				perfilVO.setPerfil(resultSet.getString(2));
				vResult.addElement(perfilVO);
			}
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector getConsultaRecursos(Hashtable pParameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String termino = (String) pParameters.get("termino");
			String inicio = (String) pParameters.get("inicio");
			String jefe = (String) pParameters.get("idJefe");
			String analista = (String) pParameters.get("analista");
			
			String sql = "SELECT idusuario,correo,concat(nombre,' ',appaterno,' ',apmaterno) as nombre,anexo,"
					+ " (select cargo from cargo c where c.idcargo = u.idcargo) as cargo,"
					+ " (SELECT count(*) as cantidad FROM integrantesxtarea i inner join tareas t on i.idtarea = t.idtarea where i.idusuario = u.idusuario and t.idusuario = "+jefe+" and i.idusuario = "+analista+" and DATE_FORMAT(fecha_inicio,'%Y%m') >= '"+inicio.substring(0, 6)+"' and DATE_FORMAT(fecha_inicio,'%Y%m') <= '"+termino.substring(0, 6)+"' group by i.idusuario) as total,"
					+ " (SELECT count(*) as cantidad FROM integrantesxtarea i inner join tareas t on i.idtarea = t.idtarea where i.idusuario = u.idusuario and t.idusuario = "+jefe+" and i.idusuario = "+analista+" and t.estado = 2 and DATE_FORMAT(fecha_inicio,'%Y%m') >= '"+inicio.substring(0, 6)+"' and DATE_FORMAT(fecha_inicio,'%Y%m') <= '"+termino.substring(0, 6)+"' group by i.idusuario) as realizadas,"
					+ " (SELECT count(*) as cantidad FROM integrantesxtarea i inner join tareas t on i.idtarea = t.idtarea where i.idusuario = u.idusuario and t.idusuario = "+jefe+" and i.idusuario = "+analista+" and t.estado = 1 and DATE_FORMAT(fecha_inicio,'%Y%m') >= '"+inicio.substring(0, 6)+"' and DATE_FORMAT(fecha_inicio,'%Y%m') <= '"+termino.substring(0, 6)+"' group by i.idusuario) as activas,"
					+ " (SELECT count(*) as cantidad FROM integrantesxtarea i inner join tareas t on i.idtarea = t.idtarea where i.idusuario = u.idusuario and t.idusuario = "+jefe+" and i.idusuario = "+analista+" and DATE_FORMAT(fecha_inicio,'%Y%m') >= '"+inicio.substring(0, 6)+"' and t.fecha_termino_estimada < now() group by i.idusuario) as atrazadas"
					+ " FROM usuarios u where u.idusuario = " + analista;
			PreparedStatement statement = conex.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				UsuarioVO usuarioVO = new UsuarioVO();
				usuarioVO.setIdUsuario(resultSet.getString(1));
				usuarioVO.setCorreo(resultSet.getString(2));
				usuarioVO.setNombre(resultSet.getString(3));
				usuarioVO.setAnexo(resultSet.getString(4));
				usuarioVO.setCargo(resultSet.getString(5));
				usuarioVO.setTareasTotal(resultSet.getInt(6));
				usuarioVO.setTareasRealizadas(resultSet.getInt(7));
				usuarioVO.setTareasActivas(resultSet.getInt(8));
				usuarioVO.setTareasAtrazadas(resultSet.getInt(9));
				vResult.addElement(usuarioVO);
			}

		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector getConsultarIdProyecto(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String idproyecto = (String) parameters.get("idproyecto");
			String sql = "SELECT * FROM proyectos where idproyecto = ?";
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, idproyecto);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ProyectoVO proyectoVO = new ProyectoVO();
				proyectoVO.setIdproyectos(resultSet.getString(1));
				proyectoVO.setProyecto(resultSet.getString(2));
				vResult.addElement(proyectoVO);
			}

		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector getConsultarIdTarea(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String idtarea = (String) parameters.get("idtarea");
			String sql = "SELECT t.idtarea, t.tarea, t.descripcion,t.idproyecto,"
					+ " DATE_FORMAT(t.fecha_inicio,'%Y-%m-%d %k:%i') as fecha_inicio,"
					+ " DATE_FORMAT(t.fecha_termino_estimada,'%Y-%m-%d %k:%i') as fecha_estimada,"
					+ " DATE_FORMAT(t.fecha_termino_real,'%Y-%m-%d %k:%i') as fecha_cierre,t.idusuario,"
					+ " t.estado,t.descripcion,(select correo from usuarios where idusuario = t.idusuario) as usuario,t.idusuario"
					+ " FROM tareas t where t.estado = 1 and idtarea = ?";
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, idtarea);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				TareaVO tareaVO = new TareaVO();
				tareaVO.setIdtarea(resultSet.getString(1));
				tareaVO.setTarea(resultSet.getString(2));
				tareaVO.setDescripcion(resultSet.getString(3));
				tareaVO.setIdproyecto(resultSet.getString(4));
				tareaVO.setFecha_inicio(resultSet.getString(5));
				tareaVO.setFechaTerminoEstimada(resultSet.getString(6));
				tareaVO.setFechaTerminoReal(resultSet.getString(7));
				tareaVO.setEstado(resultSet.getString(9));
				tareaVO.setDescripcion(resultSet.getString(10));
				tareaVO.setUsuario(resultSet.getString(11));
				tareaVO.setIdUsuario(resultSet.getString(12));
				vResult.addElement(tareaVO);
			}

		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector getConsultaGanttProyectos(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String sql = "SELECT p.idproyecto,proyecto,DATE_FORMAT(min(t.fecha_inicio),'%Y-%m-%d %k:%i') as fechainicio,DATE_FORMAT(max(t.fecha_termino_estimada),'%Y-%m-%d %k:%i') as fecha_termino "
					+ "FROM tareas t "
					+ "inner join proyectos p "
					+ "on t.idproyecto = p.idproyecto "
					+ "group by p.idproyecto limit 1";
			PreparedStatement statement = conex.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				TareaVO proyectoVO = new TareaVO();
				proyectoVO.setIdtarea(resultSet.getString(1));
				proyectoVO.setTarea(resultSet.getString(2));
				proyectoVO.setFecha_inicio(resultSet.getString(3));
				proyectoVO.setFechaTerminoEstimada(resultSet.getString(4));
				vResult.addElement(proyectoVO);
			}

		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector getConsultaGanttTareas(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String sql = "SELECT t.idtarea,t.tarea,DATE_FORMAT(t.fecha_inicio,'%Y-%m-%d %k:%i') as fechainicio,DATE_FORMAT(t.fecha_termino_estimada,'%Y-%m-%d %k:%i') as fecha_termino,DATEDIFF(fecha_termino_estimada,fecha_inicio) as total_dias,DATEDIFF(now(),fecha_inicio) as dias_tarea,idproyecto"
					+ " FROM tareas t";
			PreparedStatement statement = conex.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				TareaVO tareaVO = new TareaVO();
				tareaVO.setIdtarea(resultSet.getString(1));
				tareaVO.setTarea(resultSet.getString(2));
				tareaVO.setFecha_inicio(resultSet.getString(3));
				tareaVO.setFechaTerminoEstimada(resultSet.getString(4));
				tareaVO.setTotalDias(resultSet.getInt(5));
				tareaVO.setDiasTareas(resultSet.getInt(6));
				tareaVO.setIdproyecto(resultSet.getString(7));
				vResult.addElement(tareaVO);
			}

		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector setInsertaProyecto(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		String proyecto = (String) parameters.get("idproyecto");
		try {
			String sql = "insert into proyectos(proyecto)values(?)";
			PreparedStatement statement = conex.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, proyecto);
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next())
				vResult.addElement(new Integer(resultSet.getInt(1)));
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector getConsultaReporteTarea(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String inicio = (String) parameters.get("inicio");
			String termino = (String) parameters.get("termino");
			String sql = "SELECT t.idtarea, t.tarea, t.descripcion, t.idproyecto,"
					+ " DATE_FORMAT(t.fecha_inicio,'%Y-%m-%d %k:%i') as inicio,"
					+ " DATE_FORMAT(t.fecha_termino_estimada,'%Y-%m-%d %k:%i') as termino,"
					+ " DATE_FORMAT(t.fecha_termino_real,'%Y-%m-%d %k:%i') as cierre,"
					+ " t.idusuario, t.estado, (select p.proyecto from proyectos p where p.idproyecto = t.idproyecto) as proyecto,"
					+ " DATEDIFF(fecha_termino_estimada,fecha_inicio)as diff_1,DATEDIFF(fecha_termino_real,fecha_inicio)as diff_2"
					+ " FROM tareas t"
					+ " where DATE_FORMAT(t.fecha_inicio,'%Y%m%d') >= ? and DATE_FORMAT(t.fecha_inicio,'%Y%m%d') <= ?";
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, inicio);
			statement.setString(2, termino);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				TareaVO tareaVO = new TareaVO();
				tareaVO.setIdtarea(resultSet.getString(1));
				tareaVO.setTarea(resultSet.getString(2));
				tareaVO.setDescripcion(resultSet.getString(3));
				tareaVO.setIdproyecto(resultSet.getString(4));
				tareaVO.setFecha_inicio(resultSet.getString(5));
				tareaVO.setFechaTerminoEstimada(resultSet.getString(6));
				tareaVO.setFechaTerminoReal(resultSet.getString(7));
				tareaVO.setEstado(resultSet.getString(9));
				tareaVO.setProyecto(resultSet.getString(10) + "");
				tareaVO.setCierreDiasEstimada(resultSet.getString(11));
				tareaVO.setCierreDiasReal(resultSet.getString(12));
				vResult.addElement(tareaVO);
			}

		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector getConsultaReporteProyecto(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String inicio = (String) parameters.get("inicio");
			String termino = (String) parameters.get("termino");
			String idProyecto = (String) parameters.get("id");
			String estado = (String) parameters.get("tipo");
			String sql = "SELECT t.idtarea, t.tarea, t.descripcion, t.idproyecto,"
					+ " DATE_FORMAT(t.fecha_inicio,'%Y-%m-%d %k:%i') as inicio,"
					+ " DATE_FORMAT(t.fecha_termino_estimada,'%Y-%m-%d %k:%i') as termino,"
					+ " DATE_FORMAT(t.fecha_termino_real,'%Y-%m-%d %k:%i') as cierre,"
					+ " t.idusuario, t.estado, (select p.proyecto from proyectos p where p.idproyecto = t.idproyecto) as proyecto,"
					+ " DATEDIFF(fecha_termino_estimada,fecha_inicio)as diff_1,DATEDIFF(fecha_termino_real,fecha_inicio)as diff_2"
					+ " FROM tareas t"
					+ " where DATE_FORMAT(t.fecha_inicio,'%Y%m%d') >= ? and DATE_FORMAT(t.fecha_inicio,'%Y%m%d') <= ? and t.idproyecto = ?";
					if(!estado.equals(""))
						sql = sql + " and t.estado = " + estado;

			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, inicio);
			statement.setString(2, termino);
			statement.setString(3, idProyecto);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				TareaVO tareaVO = new TareaVO();
				tareaVO.setIdtarea(resultSet.getString(1));
				tareaVO.setTarea(resultSet.getString(2));
				tareaVO.setDescripcion(resultSet.getString(3));
				tareaVO.setIdproyecto(resultSet.getString(4));
				tareaVO.setFecha_inicio(resultSet.getString(5));
				tareaVO.setFechaTerminoEstimada(resultSet.getString(6));
				tareaVO.setFechaTerminoReal(resultSet.getString(7));
				tareaVO.setEstado(resultSet.getString(9));
				tareaVO.setProyecto(resultSet.getString(10) + "");
				tareaVO.setCierreDiasEstimada(resultSet.getString(11));
				tareaVO.setCierreDiasReal(resultSet.getString(12));
				vResult.addElement(tareaVO);
			}

		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector getConsultaReporte(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String inicio = (String) parameters.get("inicio");
			String termino = (String) parameters.get("termino");
			String estado = (String) parameters.get("tipo");
			
			String sql = "SELECT t.idtarea, t.tarea, t.descripcion, t.idproyecto,"
					+ " DATE_FORMAT(t.fecha_inicio,'%Y-%m-%d %k:%i') as inicio,"
					+ " DATE_FORMAT(t.fecha_termino_estimada,'%Y-%m-%d %k:%i') as termino,"
					+ " DATE_FORMAT(t.fecha_termino_real,'%Y-%m-%d %k:%i') as cierre,"
					+ " t.idusuario, t.estado, (select p.proyecto from proyectos p where p.idproyecto = t.idproyecto) as proyecto,"
					+ " DATEDIFF(fecha_termino_estimada,fecha_inicio)as diff_1,DATEDIFF(fecha_termino_real,fecha_inicio)as diff_2"
					+ " FROM tareas t"
					+ " where DATE_FORMAT(t.fecha_inicio,'%Y%m%d') >= ? and DATE_FORMAT(t.fecha_inicio,'%Y%m%d') <= ?";
			if(!estado.equals(""))
				sql = sql + " and t.estado = " + estado;

			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, inicio);
			statement.setString(2, termino);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				TareaVO tareaVO = new TareaVO();
				tareaVO.setIdtarea(resultSet.getString(1));
				tareaVO.setTarea(resultSet.getString(2));
				tareaVO.setDescripcion(resultSet.getString(3));
				tareaVO.setIdproyecto(resultSet.getString(4));
				tareaVO.setFecha_inicio(resultSet.getString(5));
				tareaVO.setFechaTerminoEstimada(resultSet.getString(6));
				tareaVO.setFechaTerminoReal(resultSet.getString(7));
				tareaVO.setEstado(resultSet.getString(9));
				tareaVO.setProyecto(resultSet.getString(10) + "");
				tareaVO.setCierreDiasEstimada(resultSet.getString(11));
				tareaVO.setCierreDiasReal(resultSet.getString(12));
				vResult.addElement(tareaVO);
			}

		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector getConsultaIntegrantes(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String idTarea = (String)parameters.get("idtarea");
			String sql = "SELECT a.idusuario,correo FROM integrantesxtarea a inner join usuarios b on a.idusuario = b.idusuario where idtarea = " + idTarea;
			PreparedStatement statement = conex.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				UsuarioVO usuarioVO = new UsuarioVO();
				usuarioVO.setIdUsuario(resultSet.getString(1));
				usuarioVO.setCorreo(resultSet.getString(2));
				vResult.addElement(usuarioVO);
			}

		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		}
		return vResult;
	}

	private Vector getConsultaProyectos(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String sql = "SELECT * FROM proyectos";
			PreparedStatement statement = conex.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ProyectoVO proyectoVO = new ProyectoVO();
				proyectoVO.setIdproyectos(resultSet.getString(1));
				proyectoVO.setProyecto(resultSet.getString(2));
				vResult.addElement(proyectoVO);
			}

		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector getDescargarAdjunto(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String idadjunto = (String) parameters.get("idadjunto");
			String sql = "SELECT nombre,contentType FROM adjuntos where idadjunto = ?";
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, idadjunto);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				String adjunto = resultSet.getString(1) + "#"
						+ resultSet.getString(2);
				vResult.addElement(adjunto);
			}

		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector setInsertaAdjunto(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		String nombre = (String) parameters.get("nombre");
		Integer size = (Integer) parameters.get("size");
		String idtarea = (String) parameters.get("idtarea");
		String idusuario = (String) parameters.get("idUsuario");
		String contentType = (String) parameters.get("contentType");
		try {
			String sql = "insert into adjuntos(nombre,size,idtarea,idusuario,fecha,contentType)values(?,?,?,?,now(),?)";
			PreparedStatement statement = conex.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, nombre);
			statement.setInt(2, size.intValue());
			statement.setString(3, idtarea);
			statement.setString(4, idusuario);
			statement.setString(5, contentType);
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next())
				vResult.addElement(new Integer(resultSet.getInt(1)));
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector setInsertaComentario(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		String idtarea = (String) parameters.get("idtarea");
		String idusuario = (String) parameters.get("idUsuario");
		String comentario = (String) parameters.get("comentario");
		try {
			String sql = "insert into comentario(comentario,fecha,idtarea,idusuario)values(?,now(),?,?)";
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, comentario);
			statement.setString(2, idtarea);
			statement.setString(3, idusuario);
			statement.executeUpdate();
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector getConsultarUsuarios(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String sql = "select * from usuarios";
			PreparedStatement statement = conex.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				UsuarioVO usuarioVO = new UsuarioVO();
				usuarioVO.setIdUsuario(resultSet.getString(1));
				usuarioVO.setNombre(resultSet.getString(2));
				usuarioVO.setApellidoPaterno(resultSet.getString(3));
				usuarioVO.setApellidoMaterno(resultSet.getString(4));
				usuarioVO.setCorreo(resultSet.getString(5));
				usuarioVO.setAnexo(resultSet.getString(6));
				usuarioVO.setCargo(resultSet.getString(7));
				usuarioVO.setClave(resultSet.getString(8));
				Hashtable param = new Hashtable();
				param.put("idCargo",usuarioVO.getCargo());
				PerfilVO vCargo = (PerfilVO)getConsultarIdCargos(param); 
				usuarioVO.setPerfilVO(vCargo);
				param.put("idEstado",resultSet.getString(9));
				EstadosVO vEstado = (EstadosVO)getConsultarIdEstado(param);
				usuarioVO.setEstadoVO(vEstado);
				vResult.addElement(usuarioVO);
			}
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}
	
	private EstadosVO getConsultarIdEstado(Hashtable parameters) {
		EstadosVO estadosVO = new EstadosVO();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String idEstado = (String)parameters.get("idEstado");
			String sql = "SELECT * FROM estados where idestado = ?";
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1,idEstado);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				estadosVO.setIdEstado(resultSet.getString(1));
				estadosVO.setEstado(resultSet.getString(2));
			}
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return estadosVO;
	}

	private PerfilVO getConsultarIdCargos(Hashtable parameters) {
		PerfilVO perfilVO = new PerfilVO();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String idCargo = (String)parameters.get("idCargo");
			String sql = "SELECT * FROM cargo where idcargo = ?";
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1,idCargo);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				perfilVO.setIdPerfil(resultSet.getString(1));
				perfilVO.setPerfil(resultSet.getString(2));
			}
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return perfilVO;
	}	
	
	private Vector getConsultarIngenieros(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String sql = "select * from usuarios";
			PreparedStatement statement = conex.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				EstadosVO estadosVO = new EstadosVO();
				estadosVO.setIdEstado(resultSet.getString(1));
				estadosVO.setEstado(resultSet.getString(2));
				estadosVO.setId(estadosVO.getIdEstado());
				estadosVO.setText(estadosVO.getEstado());
				vResult.addElement(estadosVO);
			}
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector getConsultarIdUsuario(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String idusuario = (String) parameters.get("idusuario");
			String sql = "select * from usuarios where idusuario = ?";
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, idusuario);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				UsuarioVO usuarioVO = new UsuarioVO();
				usuarioVO.setIdUsuario(resultSet.getString(1));
				usuarioVO.setNombre(resultSet.getString(2));
				usuarioVO.setApellidoPaterno(resultSet.getString(3));
				usuarioVO.setApellidoMaterno(resultSet.getString(4));
				usuarioVO.setCorreo(resultSet.getString(5));
				usuarioVO.setAnexo(resultSet.getString(6));
				vResult.addElement(usuarioVO);
			}
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector getConsultarEstados(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			// String idtarea = (String)parameters.get("idtarea");
			String sql = "select * from estados";
			PreparedStatement statement = conex.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				EstadosVO estadosVO = new EstadosVO();
				estadosVO.setIdEstado(resultSet.getString(1));
				estadosVO.setEstado(resultSet.getString(2));
				estadosVO.setId(estadosVO.getIdEstado());
				estadosVO.setText(estadosVO.getEstado());
				vResult.addElement(estadosVO);
			}

		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector setInsertaIntegrantexTarea(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		String idtarea = (String) parameters.get("idtarea");
		String integrante = (String) parameters.get("integrante");
		try {
			String sql = "insert into integrantesxtarea(idtarea,idusuario)values(?,?)";
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, idtarea);
			statement.setString(2, integrante);
			statement.executeUpdate();
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector setEliminaIntegrantexTarea(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		String idtarea = (String) parameters.get("idtarea");
		String integrante = (String) parameters.get("integrante");
		try {
			String sql = "delete from integrantesxtarea where idtarea = ?";
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, idtarea);
			statement.executeUpdate();
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}
	
	private Vector getConsultaReporteTareas(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String idUsuario = (String)parameters.get("idUsuario");
			String sql = "SELECT t.idtarea, t.tarea, t.descripcion,t.idproyecto,DATE_FORMAT(t.fecha_inicio,'%Y/%m/%d %k:%i') as inicio,DATE_FORMAT(t.fecha_termino_estimada ,'%Y/%m/%d %k:%i') as termino,DATE_FORMAT(t.fecha_termino_real ,'%Y/%m/%d %k:%i') as cierre,t.idusuario, t.estado " +
					" FROM tareas t inner join integrantesxtarea i on t.idtarea = i.idtarea" +
					" where t.estado = 1";
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, idUsuario);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				TareaVO tareaVO = new TareaVO();
				tareaVO.setIdtarea(resultSet.getString(1));
				tareaVO.setTarea(resultSet.getString(2));
				tareaVO.setDescripcion(resultSet.getString(3));
				tareaVO.setIdproyecto(resultSet.getString(4));
				tareaVO.setFecha_inicio(resultSet.getString(5));
				tareaVO.setFechaTerminoEstimada(resultSet.getString(6));
				tareaVO.setFechaTerminoReal(resultSet.getString(7));
				tareaVO.setIdUsuario(resultSet.getString(8));
				tareaVO.setEstado(resultSet.getString(9));
				vResult.addElement(tareaVO);
			}

		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}
	
	private Vector getConsultaTareasIdusuario(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String idUsuario = (String)parameters.get("idUsuario");
			String sql = "SELECT t.idtarea, t.tarea, t.descripcion,t.idproyecto,DATE_FORMAT(t.fecha_inicio,'%Y/%m/%d %k:%i') as inicio,DATE_FORMAT(t.fecha_termino_estimada ,'%Y/%m/%d %k:%i') as termino,DATE_FORMAT(t.fecha_termino_real ,'%Y/%m/%d %k:%i') as cierre,t.idusuario, t.estado " +
					" FROM tareas t inner join integrantesxtarea i on t.idtarea = i.idtarea" +
					" where i.idusuario = ? and t.estado = 1";
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, idUsuario);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				TareaVO tareaVO = new TareaVO();
				tareaVO.setIdtarea(resultSet.getString(1));
				tareaVO.setTarea(resultSet.getString(2));
				tareaVO.setDescripcion(resultSet.getString(3));
				tareaVO.setIdproyecto(resultSet.getString(4));
				tareaVO.setFecha_inicio(resultSet.getString(5));
				tareaVO.setFechaTerminoEstimada(resultSet.getString(6));
				tareaVO.setFechaTerminoReal(resultSet.getString(7));
				tareaVO.setIdUsuario(resultSet.getString(8));
				tareaVO.setEstado(resultSet.getString(9));
				vResult.addElement(tareaVO);
			}

		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	
	private Vector getConsultaTareas(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String idUsuario = (String)parameters.get("idUsuario");
			String sql = "SELECT t.idtarea, t.tarea, t.descripcion,t.idproyecto,DATE_FORMAT(t.fecha_inicio,'%Y/%m/%d %k:%i') as inicio,DATE_FORMAT(t.fecha_termino_estimada ,'%Y/%m/%d %k:%i') as termino,DATE_FORMAT(t.fecha_termino_real ,'%Y/%m/%d %k:%i') as cierre,t.idusuario, t.estado " +
					"FROM tareas t where t.idusuario = ? and t.estado = 1";
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, idUsuario);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				TareaVO tareaVO = new TareaVO();
				tareaVO.setIdtarea(resultSet.getString(1));
				tareaVO.setTarea(resultSet.getString(2));
				tareaVO.setDescripcion(resultSet.getString(3));
				tareaVO.setIdproyecto(resultSet.getString(4));
				tareaVO.setFecha_inicio(resultSet.getString(5));
				tareaVO.setFechaTerminoEstimada(resultSet.getString(6));
				tareaVO.setFechaTerminoReal(resultSet.getString(7));
				tareaVO.setIdUsuario(resultSet.getString(8));
				tareaVO.setEstado(resultSet.getString(9));
				vResult.addElement(tareaVO);
			}

		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector setConsultaIntegrantexTarea(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String idtarea = (String) parameters.get("idtarea");
			String sql = "SELECT t.idusuario,correo,nombre,appaterno,apmaterno,anexo "
					+ " FROM integrantesxtarea t inner join usuarios b"
					+ " on t.idusuario = b.idusuario where idtarea = ?";
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, idtarea);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				UsuarioVO usuarioVO = new UsuarioVO();
				usuarioVO.setIdUsuario(resultSet.getString(1));
				usuarioVO.setCorreo(resultSet.getString(2));
				usuarioVO.setNombre(resultSet.getString(3));
				usuarioVO.setApellidoPaterno(resultSet.getString(4));
				usuarioVO.setApellidoMaterno(resultSet.getString(5));
				usuarioVO.setAnexo(resultSet.getString(6));
				vResult.addElement(usuarioVO);
			}

		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector getConsultaAdjuntos(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String idtarea = (String) parameters.get("idtarea");
			String sql = "SELECT concat(idadjunto,'-', nombre) as text, size, '.doc' as extension,(select correo from usuarios where idusuario = a.idusuario) as usuario,DATE_FORMAT(fecha,'%Y-%m-%d %h:%m:%s %p') as fecha FROM adjuntos a where idtarea = ?";
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, idtarea);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				AdjuntoVO adjuntoVO = new AdjuntoVO();
				adjuntoVO.setName(resultSet.getString(1));
				adjuntoVO.setSize(resultSet.getString(2));
				adjuntoVO.setExtension(resultSet.getString(3));
				adjuntoVO.setUsuario(resultSet.getString(4));
				adjuntoVO.setFecha(resultSet.getString(5));
				vResult.addElement(adjuntoVO);
			}
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector getConsultaComentarios(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String idtarea = (String) parameters.get("idtarea");
			String sql = "SELECT idcomentario,comentario,DATE_FORMAT(fecha,'%Y-%m-%d %h:%m:%s %p') as fecha,(select correo from usuarios where idusuario = c.idusuario) as usuario FROM comentario c where idtarea = ?";
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, idtarea);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ComentarioVO comentarioVO = new ComentarioVO();
				comentarioVO.setIdComentario(resultSet.getString(1));
				comentarioVO.setComentario(resultSet.getString(2));
				comentarioVO.setFecha(resultSet.getString(3));
				comentarioVO.setUsuario(resultSet.getString(4));
				vResult.addElement(comentarioVO);
			}

		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector getInsertaTareas(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		String tarea = (String) parameters.get("tarea");
		String idproyecto = (String) parameters.get("idproyecto");
		String descripcion = (String) parameters.get("descripcion");
		String fechaInicio = (String) parameters.get("fechaInicio");
		String fechaTermino = (String) parameters.get("fechaTermino");
		String idUsuario = (String) parameters.get("idUsuario");
		try {
			String sql = "insert into tareas(tarea,idproyecto,descripcion,fecha_inicio,fecha_termino_estimada,fecha_termino_real,estado,idusuario)values("
					+ "'"+ tarea+ "',"
					+ "'"+ idproyecto+ "',"
					+ "'"+ descripcion+ "',"
					+ "'"+ getFormatoFecha(fechaInicio)+ "',"
					+ "'"+ getFormatoFecha(fechaTermino)+ "',"
					+ "'"+ getFormatoFecha(fechaTermino)+ "',"
					+ "'1',"
					+ "'"+ idUsuario+ "')";
			PreparedStatement statement = conex.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next())
				vResult.addElement(new Integer(resultSet.getInt(1)));
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}
//tarea,idproyecto,descripcion,fecha_inicio,fecha_termino_estimada,fecha_termino_real,idusuario
	
	private Vector getActualizaTareas(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String idtarea = (String) parameters.get("idtarea");
			String tarea = (String) parameters.get("tarea");
			String descripcion = (String) parameters.get("descripcion");
			String idProyecto = (String) parameters.get("idproyecto");
			String fechaInicio = (String) parameters.get("fechaInicio");
			String fechaTermino = (String) parameters.get("fechaTermino");
			String sql = "update tareas set tarea = ?,idproyecto= ?,descripcion = ?,fecha_inicio = ?,fecha_termino_estimada = ?,fecha_termino_real = ? where idtarea = ?";
			PreparedStatement statement = conex.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, tarea);
			statement.setString(2, idProyecto);
			statement.setString(3, descripcion);
			statement.setString(4, getFormatoFecha(fechaInicio));
			statement.setString(5, getFormatoFecha(fechaTermino));
			statement.setString(6, getFormatoFecha(fechaTermino));
			statement.setString(7, idtarea);
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next())
				vResult.addElement(new Integer(resultSet.getInt(1)));
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private Vector getEliminaTareas(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String idtarea = (String) parameters.get("idtarea");
			String sql = "update tareas set estado = 2,fecha_termino_real = now() where idtarea = ?";
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, idtarea);
			statement.executeUpdate();
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		} finally {
			JDBCConnectionFactory.closeSession(conex);
		}
		return vResult;
	}

	private String getFormatoFecha(String fecha) {

		SimpleDateFormat entrada = new SimpleDateFormat("yyyy/MM/dd hh:mm a");
		SimpleDateFormat salida = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String resultado = "";
		try {
			long long1 = entrada.parse(fecha).getTime();
			Date date = new Date(long1);
			resultado = salida.format(date);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return resultado;
	}

}