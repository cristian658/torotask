package com.gotoque.torotask.integracion.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

import com.gotoque.torotask.vo.AdjuntoVO;
import com.gotoque.torotask.vo.ComentarioVO;
import com.gotoque.torotask.vo.EstadosVO;
import com.gotoque.torotask.vo.ParametroVO;
import com.gotoque.torotask.vo.ProyectoVO;
import com.gotoque.torotask.vo.TareaVO;
import com.gotoque.torotask.vo.UsuarioVO;

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
	private static final String CMD_GET_CONSULTA_PARAMETROS = "GET_CONSULTA_PARAMETROS";

	private static final String CMD_GET_CONSULTA_REPORTE_TAREA = "GET_CONSULTA_REPORTE_TAREAS";
	private static final String CMD_GET_CONSULTA_REPORTE_TAREA_ACTIVAS = "GET_CONSULTA_REPORTE_TAREAS_ACTIVAS";
	private static final String CMD_GET_CONSULTA_REPORTE_TAREA_CERRADAS = "GET_CONSULTA_REPORTE_TAREAS_CERRADAS";
	private static final String CMD_GET_CONSULTA_REPORTE_PROYECTO = "GET_CONSULTA_REPORTE_PROYECTO";
	private static final String CMD_GET_CONSULTA_REPORTE = "GET_CONSULTA_REPORTE";
	private static final String CMD_GET_CONSULTA_ID_USUARIO = "GET_CONSULTA_ID_USUARIO";
	
	public Vector executeQuery(String pCommandService, Hashtable pParameters){
		Vector Resultado = new Vector();
		if (pCommandService.equals(CMD_GET_CONSULTA_TAREAS)) {
			Resultado = getConsultaTareas(pParameters);
		}else if(pCommandService.equals(CMD_GET_INSERTA_TAREAS)){ 
			Resultado=getInsertaTareas(pParameters);
		}else if(pCommandService.equals(CMD_GET_ACTUALIZA_TAREAS)){ 
			Resultado=getActualizaTareas(pParameters);
		}else if(pCommandService.equals(CMD_GET_ELIMINA_TAREAS)){ 
			Resultado=getEliminaTareas(pParameters);
		}else if(pCommandService.equals(CMD_GET_ELIMINA_INTEGRANTES_X_TAREA)){ 
			Resultado=setEliminaIntegrantexTarea(pParameters);
		}else if(pCommandService.equals(CMD_GET_INSERTAR_INTEGRANTES_X_TAREA)){ 
			Resultado=setInsertaIntegrantexTarea(pParameters);
		}else if(pCommandService.equals(CMD_GET_CONSULTA_INTEGRANTES_X_TAREA)){ 
			Resultado=setConsultaIntegrantexTarea(pParameters);
		}else if(pCommandService.equals(CMD_GET_CONSULTA_INGENIEROS)){ 
			Resultado=getConsultarIngenieros(pParameters);
		}else if(pCommandService.equals(CMD_GET_CONSULTA_ESTADOS)){ 
			Resultado=getConsultarEstados(pParameters);
		}else if(pCommandService.equals(CMD_GET_CONSULTA_COMENTARIOS)){ 
			Resultado=getConsultaComentarios(pParameters);
		}else if(pCommandService.equals(CMD_GET_CONSULTA_ADJUNTOS)){ 
			Resultado=getConsultaAdjuntos(pParameters);
		}else if(pCommandService.equals(CMD_SET_INSERTA_COMENTARIO)){ 
			Resultado=setInsertaComentario(pParameters);
		}else if(pCommandService.equals(CMD_SET_INSERTA_AJUNTO)){ 
			Resultado=setInsertaAdjunto(pParameters);
		}else if(pCommandService.equals(CMD_GET_DESCARGAR_AJUNTO)){ 
			Resultado=getDescargarAdjunto(pParameters);
		}else if(pCommandService.equals(CMD_GET_CONSULTA_PROYECTOS)){ 
			Resultado=getConsultaProyectos(pParameters);
		}else if(pCommandService.equals(CMD_GET_CONSULTA_INTEGRANTES)){ 
			Resultado=getConsultaIntegrantes(pParameters);
		}else if(pCommandService.equals(CMD_GET_CONSULTA_REPORTE_TAREA)){ 
			Resultado=getConsultaReporteTarea(pParameters);
		}else if(pCommandService.equals(CMD_GET_CONSULTA_REPORTE_TAREA_ACTIVAS)){ 
			Resultado=getConsultaReporteTareaActivas(pParameters);
		}else if(pCommandService.equals(CMD_GET_CONSULTA_REPORTE_TAREA_CERRADAS)){ 
			Resultado=getConsultaReporteTareaCerradas(pParameters);
		}else if(pCommandService.equals(CMD_GET_CONSULTA_REPORTE_PROYECTO)){ 
			Resultado=getConsultaReporteProyecto(pParameters);
		}else if(pCommandService.equals(CMD_GET_CONSULTA_REPORTE)){ 
			Resultado=getConsultaReporte(pParameters);
		}else if(pCommandService.equals(CMD_SET_INSERTA_PROYECTO)){ 
			Resultado=setInsertaProyecto(pParameters);
		}else if(pCommandService.equals(CMD_GET_CONSULTA_GANTT_PROYECTOS)){ 
			Resultado=getConsultaGanttProyectos(pParameters);
		}else if(pCommandService.equals(CMD_GET_CONSULTA_GANTT_TAREAS)){ 
			Resultado=getConsultaGanttTareas(pParameters);
		}else if(pCommandService.equals(CMD_GET_CONSULTA_ID_TAREA)){ 
			Resultado=getConsultarIdTarea(pParameters);
		}else if(pCommandService.equals(CMD_GET_CONSULTA_ID_PROYECTO)){ 
			Resultado=getConsultarIdProyecto(pParameters);
		}else if(pCommandService.equals(CMD_CMD_GET_CONSULTA_RECURSOS)){ 
			Resultado=getConsultaRecursos(pParameters);
		}else if(pCommandService.equals(CMD_GET_CONSULTA_PARAMETROS)){ 
			Resultado=getConsultaParametros(pParameters);
		}else if(pCommandService.equals(CMD_GET_CONSULTA_ID_USUARIO)){ 
			Resultado=getConsultarIdUsuario(pParameters);
		}	
		return Resultado; 
	}

	private Vector getConsultaParametros(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String idproyecto = (String)parameters.get("proyecto");
			String sql = "SELECT * FROM parametros";
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, idproyecto);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
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
		}finally {
			JDBCConnectionFactory.closeSession(conex);
        }
		return vResult;
	}

	private Vector getConsultaRecursos(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String sql = "SELECT idusuario,correo,concat(nombre,' ',appaterno,' ',apmaterno) as nombre,anexo,"+
			" (select cargo from cargo c where c.idcargo = u.idcargo) as cargo,"+
			" (SELECT count(*) as cantidad FROM integrantesxtarea i inner join tareas t on i.idtarea = t.idtarea where i.idusuario = u.idusuario group by i.idusuario) as total,"+
			" (SELECT count(*) as cantidad FROM integrantesxtarea i inner join tareas t on i.idtarea = t.idtarea where i.idusuario = u.idusuario and t.estado = 1 group by i.idusuario) as realizadas,"+
			" (SELECT count(*) as cantidad FROM integrantesxtarea i inner join tareas t on i.idtarea = t.idtarea where i.idusuario = u.idusuario and t.estado = 0 group by i.idusuario) as activas,"+
			" (SELECT count(*) as cantidad FROM integrantesxtarea i inner join tareas t on i.idtarea = t.idtarea where i.idusuario = u.idusuario and t.fecha_termino_estimada < now() group by i.idusuario) as atrazadas"+
			" FROM usuarios u";
			PreparedStatement statement = conex.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
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
		}finally {
			JDBCConnectionFactory.closeSession(conex);
        }
		return vResult;
	}

	private Vector getConsultarIdProyecto(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String idproyecto = (String)parameters.get("idproyecto");
			String sql = "SELECT * FROM proyectos where idproyecto = ?";
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, idproyecto);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				ProyectoVO proyectoVO = new ProyectoVO();
				proyectoVO.setIdproyectos(resultSet.getString(1));
				proyectoVO.setProyecto(resultSet.getString(2));
				vResult.addElement(proyectoVO);
			}
			
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		}finally {
			JDBCConnectionFactory.closeSession(conex);
        }
		return vResult;
	}

	private Vector getConsultarIdTarea(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String idtarea = (String)parameters.get("idtarea");
			String sql = "SELECT t.idtarea, t.tarea, t.descripcion,t.idproyecto," +
					" DATE_FORMAT(t.fecha_inicio,'%Y-%m-%d %k:%i') as fecha_inicio," +
					" DATE_FORMAT(t.fecha_termino_estimada,'%Y-%m-%d %k:%i') as fecha_estimada," +
					" DATE_FORMAT(t.fecha_termino_real,'%Y-%m-%d %k:%i') as fecha_cierre,t.idusuario," +
					" t.estado,t.descripcion,(select correo from usuarios where idusuario = t.idusuario) as usuario,t.idusuario" +
					" FROM tareas t where t.estado = 0 and idtarea = ?";
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
		}finally {
			JDBCConnectionFactory.closeSession(conex);
        }
		return vResult;
	}

	private Vector getConsultaGanttProyectos(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String sql = "SELECT p.idproyecto,proyecto,DATE_FORMAT(min(t.fecha_inicio),'%Y-%m-%d %k:%i') as fechainicio,DATE_FORMAT(max(t.fecha_termino_estimada),'%Y-%m-%d %k:%i') as fecha_termino " +
					"FROM tareas t " +
					"inner join proyectos p " +
					"on t.idproyecto = p.idproyecto " +
					"group by p.idproyecto " +
					"limit 1";
			PreparedStatement statement = conex.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
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
		}finally {
			JDBCConnectionFactory.closeSession(conex);
        }
		return vResult;
	}

	private Vector getConsultaGanttTareas(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String sql = "SELECT t.idtarea,t.tarea,DATE_FORMAT(t.fecha_inicio,'%Y-%m-%d %k:%i') as fechainicio,DATE_FORMAT(t.fecha_termino_estimada,'%Y-%m-%d %k:%i') as fecha_termino,DATEDIFF(fecha_termino_estimada,fecha_inicio) as total_dias,DATEDIFF(now(),fecha_inicio) as dias_tarea,idproyecto" +
					" FROM tareas t";
			PreparedStatement statement = conex.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
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
		}finally {
			JDBCConnectionFactory.closeSession(conex);
        }
		return vResult;
	}

	private Vector setInsertaProyecto(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		String proyecto = (String)parameters.get("proyecto");
		try {
			String sql = "insert into proyectos(proyecto)values(?)";
			PreparedStatement statement = conex.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, proyecto);
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if(resultSet.next())
				vResult.addElement(new Integer(resultSet.getInt(1)));
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		}finally {
			JDBCConnectionFactory.closeSession(conex);
        }
		return vResult;
	}

	private Vector getConsultaReporteTarea(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String inicio = (String)parameters.get("inicio");
			String termino = (String)parameters.get("termino");
			String sql = "SELECT t.idtarea, t.tarea, t.descripcion, t.idproyecto," +
					" DATE_FORMAT(t.fecha_inicio,'%Y-%m-%d %k:%i') as inicio," +
					" DATE_FORMAT(t.fecha_termino_estimada,'%Y-%m-%d %k:%i') as termino," +
					" DATE_FORMAT(t.fecha_termino_real,'%Y-%m-%d %k:%i') as cierre," +
					" t.idusuario, t.estado, (select p.proyecto from proyectos p where p.idproyecto = t.idproyecto) as proyecto," +
					" DATEDIFF(fecha_termino_estimada,fecha_inicio)as diff_1,DATEDIFF(fecha_termino_real,fecha_inicio)as diff_2," +
					" workdaydiff(fecha_termino_estimada,fecha_inicio)as diff_3,workdaydiff(fecha_termino_real,fecha_inicio)as diff_4" +
					" FROM tareas t" +
					" where DATE_FORMAT(t.fecha_inicio,'%Y%m%d') >= ? and DATE_FORMAT(t.fecha_inicio,'%Y%m%d') <= ?";
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, inicio);
			statement.setString(2, termino);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				TareaVO tareaVO = new TareaVO();
				tareaVO.setIdtarea(resultSet.getString(1));
				tareaVO.setTarea(resultSet.getString(2));
				tareaVO.setDescripcion(resultSet.getString(3));
				tareaVO.setIdproyecto(resultSet.getString(4));
				tareaVO.setFecha_inicio(resultSet.getString(5));
				tareaVO.setFechaTerminoEstimada(resultSet.getString(6));
				tareaVO.setFechaTerminoReal(resultSet.getString(7));
				tareaVO.setEstado(resultSet.getString(9));
				tareaVO.setProyecto(resultSet.getString(10)+"");
				tareaVO.setCierreDiasEstimada(resultSet.getString(11));
				tareaVO.setCierreDiasReal(resultSet.getString(12));
				tareaVO.setTotalDiasEstimada(resultSet.getString(13));
				tareaVO.setTotalDiasReal(resultSet.getString(14));
				vResult.addElement(tareaVO);
			}
			
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		}finally {
			JDBCConnectionFactory.closeSession(conex);
        }
		return vResult;
	}

	private Vector getConsultaReporteTareaActivas(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String inicio = (String)parameters.get("inicio");
			String termino = (String)parameters.get("termino");
			String sql = "SELECT t.idtarea, t.tarea, t.descripcion, t.idproyecto," +
					" DATE_FORMAT(t.fecha_inicio,'%Y-%m-%d %k:%i') as inicio," +
					" DATE_FORMAT(t.fecha_termino_estimada,'%Y-%m-%d %k:%i') as termino," +
					" DATE_FORMAT(t.fecha_termino_real,'%Y-%m-%d %k:%i') as cierre," +
					" t.idusuario, t.estado, (select p.proyecto from proyectos p where p.idproyecto = t.idproyecto) as proyecto," +
					" DATEDIFF(fecha_termino_estimada,fecha_inicio)as diff_1,DATEDIFF(fecha_termino_real,fecha_inicio)as diff_2," +
					" workdaydiff(fecha_termino_estimada,fecha_inicio)as diff_1,workdaydiff(fecha_termino_real,fecha_inicio)as diff_2" +
					" FROM tareas t" +
					" where DATE_FORMAT(t.fecha_inicio,'%Y%m%d') >= ? and DATE_FORMAT(t.fecha_inicio,'%Y%m%d') <= ? and t.estado = 0";
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, inicio);
			statement.setString(2, termino);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				TareaVO tareaVO = new TareaVO();
				tareaVO.setIdtarea(resultSet.getString(1));
				tareaVO.setTarea(resultSet.getString(2));
				tareaVO.setDescripcion(resultSet.getString(3));
				tareaVO.setIdproyecto(resultSet.getString(4));
				tareaVO.setFecha_inicio(resultSet.getString(5));
				tareaVO.setFechaTerminoEstimada(resultSet.getString(6));
				tareaVO.setFechaTerminoReal(resultSet.getString(7));
				tareaVO.setEstado(resultSet.getString(9));
				tareaVO.setProyecto(resultSet.getString(10)+"");
				tareaVO.setCierreDiasEstimada(resultSet.getString(11));
				tareaVO.setCierreDiasReal(resultSet.getString(12));
				tareaVO.setTotalDiasEstimada(resultSet.getString(13));
				tareaVO.setTotalDiasReal(resultSet.getString(14));
				vResult.addElement(tareaVO);
			}
			
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		}finally {
			JDBCConnectionFactory.closeSession(conex);
        }
		return vResult;
	}
	
	
	private Vector getConsultaReporteTareaCerradas(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String inicio = (String)parameters.get("inicio");
			String termino = (String)parameters.get("termino");
			String sql = "SELECT t.idtarea, t.tarea, t.descripcion, t.idproyecto," +
					" DATE_FORMAT(t.fecha_inicio,'%Y-%m-%d %k:%i') as inicio," +
					" DATE_FORMAT(t.fecha_termino_estimada,'%Y-%m-%d %k:%i') as termino," +
					" DATE_FORMAT(t.fecha_termino_real,'%Y-%m-%d %k:%i') as cierre," +
					" t.idusuario, t.estado, (select p.proyecto from proyectos p where p.idproyecto = t.idproyecto) as proyecto," +
					" DATEDIFF(fecha_termino_estimada,fecha_inicio)as diff_1,DATEDIFF(fecha_termino_real,fecha_inicio)as diff_2," +
					" workdaydiff(fecha_termino_estimada,fecha_inicio)as diff_1,workdaydiff(fecha_termino_real,fecha_inicio)as diff_2" +
					" FROM tareas t" +
					" where DATE_FORMAT(t.fecha_inicio,'%Y%m%d') >= ? and DATE_FORMAT(t.fecha_inicio,'%Y%m%d') <= ?  and t.estado = 1";
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, inicio);
			statement.setString(2, termino);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				TareaVO tareaVO = new TareaVO();
				tareaVO.setIdtarea(resultSet.getString(1));
				tareaVO.setTarea(resultSet.getString(2));
				tareaVO.setDescripcion(resultSet.getString(3));
				tareaVO.setIdproyecto(resultSet.getString(4));
				tareaVO.setFecha_inicio(resultSet.getString(5));
				tareaVO.setFechaTerminoEstimada(resultSet.getString(6));
				tareaVO.setFechaTerminoReal(resultSet.getString(7));
				tareaVO.setEstado(resultSet.getString(9));
				tareaVO.setProyecto(resultSet.getString(10)+"");
				tareaVO.setCierreDiasEstimada(resultSet.getString(11));
				tareaVO.setCierreDiasReal(resultSet.getString(12));
				tareaVO.setTotalDiasEstimada(resultSet.getString(13));
				tareaVO.setTotalDiasReal(resultSet.getString(14));
				vResult.addElement(tareaVO);
			}
			
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		}finally {
			JDBCConnectionFactory.closeSession(conex);
        }
		return vResult;
	}
	
	private Vector getConsultaReporteProyecto(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String inicio = (String)parameters.get("inicio");
			String termino = (String)parameters.get("termino");
			String idProyecto = (String)parameters.get("id");
			String sql = "SELECT t.idtarea, t.tarea, t.descripcion, t.idproyecto," +
					" DATE_FORMAT(t.fecha_inicio,'%Y-%m-%d %k:%i') as inicio," +
					" DATE_FORMAT(t.fecha_termino_estimada,'%Y-%m-%d %k:%i') as termino," +
					" DATE_FORMAT(t.fecha_termino_real,'%Y-%m-%d %k:%i') as cierre," +
					" t.idusuario, t.estado, (select p.proyecto from proyectos p where p.idproyecto = t.idproyecto) as proyecto," +
					" DATEDIFF(fecha_termino_estimada,fecha_inicio)as diff_1,DATEDIFF(fecha_termino_real,fecha_inicio)as diff_2," +
					" workdaydiff(fecha_termino_estimada,fecha_inicio)as diff_1,workdaydiff(fecha_termino_real,fecha_inicio)as diff_2" +
					" FROM tareas t" +
					" where DATE_FORMAT(t.fecha_inicio,'%Y%m%d') >= ? and DATE_FORMAT(t.fecha_inicio,'%Y%m%d') <= ? and t.idproyecto = ?";
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, inicio);
			statement.setString(2, termino);
			statement.setString(3, idProyecto);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				TareaVO tareaVO = new TareaVO();
				tareaVO.setIdtarea(resultSet.getString(1));
				tareaVO.setTarea(resultSet.getString(2));
				tareaVO.setDescripcion(resultSet.getString(3));
				tareaVO.setIdproyecto(resultSet.getString(4));
				tareaVO.setFecha_inicio(resultSet.getString(5));
				tareaVO.setFechaTerminoEstimada(resultSet.getString(6));
				tareaVO.setFechaTerminoReal(resultSet.getString(7));
				tareaVO.setEstado(resultSet.getString(9));
				tareaVO.setProyecto(resultSet.getString(10)+"");
				tareaVO.setCierreDiasEstimada(resultSet.getString(11));
				tareaVO.setCierreDiasReal(resultSet.getString(12));
				tareaVO.setTotalDiasEstimada(resultSet.getString(13));
				tareaVO.setTotalDiasReal(resultSet.getString(14));
				vResult.addElement(tareaVO);
			}
			
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		}finally {
			JDBCConnectionFactory.closeSession(conex);
        }
		return vResult;
	}
	
	private Vector getConsultaReporte(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String inicio = (String)parameters.get("inicio");
			String termino = (String)parameters.get("termino");
			String sql = "SELECT t.idtarea, t.tarea, t.descripcion, t.idproyecto," +
					" DATE_FORMAT(t.fecha_inicio,'%Y-%m-%d %k:%i') as inicio," +
					" DATE_FORMAT(t.fecha_termino_estimada,'%Y-%m-%d %k:%i') as termino," +
					" DATE_FORMAT(t.fecha_termino_real,'%Y-%m-%d %k:%i') as cierre," +
					" t.idusuario, t.estado, (select p.proyecto from proyectos p where p.idproyecto = t.idproyecto) as proyecto," +
					" DATEDIFF(fecha_termino_estimada,fecha_inicio)as diff_1,DATEDIFF(fecha_termino_real,fecha_inicio)as diff_2," +
					" workdaydiff(fecha_termino_estimada,fecha_inicio)as diff_1,workdaydiff(fecha_termino_real,fecha_inicio)as diff_2" +
					" FROM tareas t" +
					" where DATE_FORMAT(t.fecha_inicio,'%Y%m%d') >= ? and DATE_FORMAT(t.fecha_inicio,'%Y%m%d') <= ?";
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, inicio);
			statement.setString(2, termino);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				TareaVO tareaVO = new TareaVO();
				tareaVO.setIdtarea(resultSet.getString(1));
				tareaVO.setTarea(resultSet.getString(2));
				tareaVO.setDescripcion(resultSet.getString(3));
				tareaVO.setIdproyecto(resultSet.getString(4));
				tareaVO.setFecha_inicio(resultSet.getString(5));
				tareaVO.setFechaTerminoEstimada(resultSet.getString(6));
				tareaVO.setFechaTerminoReal(resultSet.getString(7));
				tareaVO.setEstado(resultSet.getString(9));
				tareaVO.setProyecto(resultSet.getString(10)+"");
				tareaVO.setCierreDiasEstimada(resultSet.getString(11));
				tareaVO.setCierreDiasReal(resultSet.getString(12));
				tareaVO.setTotalDiasEstimada(resultSet.getString(13));
				tareaVO.setTotalDiasReal(resultSet.getString(14));
				vResult.addElement(tareaVO);
			}
			
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		}finally {
			JDBCConnectionFactory.closeSession(conex);
        }
		return vResult;
	}
	
	private Vector getConsultaIntegrantes(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String sql = "SELECT idusuario,correo FROM usuarios";
			PreparedStatement statement = conex.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
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
			while(resultSet.next()) {
				ProyectoVO proyectoVO = new ProyectoVO();
				proyectoVO.setIdproyectos(resultSet.getString(1));
				proyectoVO.setProyecto(resultSet.getString(2));
				vResult.addElement(proyectoVO);
			}
			
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		}finally {
			JDBCConnectionFactory.closeSession(conex);
        }
		return vResult;
	}

	private Vector getDescargarAdjunto(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String idadjunto = (String)parameters.get("idadjunto");
			String sql = "SELECT nombre,contentType FROM adjuntos where idadjunto = ?";
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, idadjunto);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				String adjunto = resultSet.getString(1) + "#" + resultSet.getString(2);
				vResult.addElement(adjunto);
			}
			
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		}finally {
			JDBCConnectionFactory.closeSession(conex);
        }
		return vResult;
	}

	private Vector setInsertaAdjunto(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		String nombre = (String)parameters.get("nombre");
		Integer size = (Integer)parameters.get("size");
		String idtarea = (String)parameters.get("idtarea");
		String idusuario = (String)parameters.get("idUsuario");
		String contentType = (String)parameters.get("contentType");
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
			if(resultSet.next())
				vResult.addElement(new Integer(resultSet.getInt(1)));
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		}finally {
			JDBCConnectionFactory.closeSession(conex);
        }
		return vResult;
	}

	private Vector setInsertaComentario(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		String idtarea = (String)parameters.get("idtarea");
		String idusuario = (String)parameters.get("idUsuario");
		String comentario = (String)parameters.get("comentario");
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
		}finally {
			JDBCConnectionFactory.closeSession(conex);
        }
		return vResult;
	}

	private Vector getConsultarIngenieros(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			//String idtarea = (String)parameters.get("idtarea");
			String sql = "select * from usuarios";
			PreparedStatement statement = conex.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				EstadosVO estadosVO = new EstadosVO();
				estadosVO.setId(resultSet.getString(1));
				estadosVO.setText(resultSet.getString(2));
				vResult.addElement(estadosVO);
			}		
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		}finally {
			JDBCConnectionFactory.closeSession(conex);
        }
		return vResult;
	}

	private Vector getConsultarIdUsuario(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String idusuario = (String)parameters.get("idusuario");
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
		}finally {
			JDBCConnectionFactory.closeSession(conex);
        }
		return vResult;
	}
	
	private Vector getConsultarEstados(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			//String idtarea = (String)parameters.get("idtarea");
			String sql = "select * from estados";
			PreparedStatement statement = conex.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				EstadosVO estadosVO = new EstadosVO();
				estadosVO.setId(resultSet.getString(1));
				estadosVO.setText(resultSet.getString(2));
				vResult.addElement(estadosVO);
			}
			
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		}finally {
			JDBCConnectionFactory.closeSession(conex);
        }
		return vResult;
	}

	private Vector setInsertaIntegrantexTarea(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		String idtarea = (String)parameters.get("idtarea");
		String integrante = (String)parameters.get("integrante");
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
		}finally {
			JDBCConnectionFactory.closeSession(conex);
        }
		return vResult;
	}

	private Vector setEliminaIntegrantexTarea(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		String idtarea = (String)parameters.get("idtarea");
		String integrante = (String)parameters.get("integrante");
		try {
			String sql = "delete from integrantesxtarea where idtarea = ?";
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, idtarea);
			statement.executeUpdate();
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		}finally {
			JDBCConnectionFactory.closeSession(conex);
        }
		return vResult;
	}

	private Vector getConsultaTareas(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String sql = "SELECT t.idtarea, t.tarea, t.descripcion,t.idproyecto,DATE_FORMAT(t.fecha_inicio,'%Y/%m/%d %k:%i') as inicio,DATE_FORMAT(t.fecha_termino_estimada ,'%Y/%m/%d %k:%i') as termino,DATE_FORMAT(t.fecha_termino_real ,'%Y/%m/%d %k:%i') as cierre,t.idusuario, t.estado FROM tareas t where t.estado = 0";
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
		}finally {
			JDBCConnectionFactory.closeSession(conex);
        }
		return vResult;
	}	
	
	
	private Vector setConsultaIntegrantexTarea(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String idtarea = (String)parameters.get("idtarea");
			String sql = "SELECT t.idusuario,correo,nombre,appaterno,apmaterno,anexo " +
					" FROM integrantesxtarea t inner join usuarios b" +
					" on t.idusuario = b.idusuario where idtarea = ?";
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
		}finally {
			JDBCConnectionFactory.closeSession(conex);
        }
		return vResult;
	}
	
	
	private Vector getConsultaAdjuntos(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String idtarea = (String)parameters.get("idtarea");
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
		}finally {
			JDBCConnectionFactory.closeSession(conex);
        }
		return vResult;
	}
	
	private Vector getConsultaComentarios(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String idtarea = (String)parameters.get("idtarea");
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
		}finally {
			JDBCConnectionFactory.closeSession(conex);
        }
		return vResult;
	}
	
	private Vector getInsertaTareas(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		String tarea = (String)parameters.get("tarea");
		String idproyecto = (String)parameters.get("idproyecto");
		String descripcion = (String)parameters.get("descripcion");
		String fechaInicio = (String)parameters.get("fechaInicio");
		String fechaTermino = (String)parameters.get("fechaTermino");
		String idUsuario = (String)parameters.get("idUsuario");
		try {
			String sql = "insert into tareas(tarea,idproyecto,descripcion,fecha_inicio,fecha_termino_estimada,fecha_termino_real,idusuario)values(" +
					"'"+tarea+"'," +
					"'"+idproyecto+"'," +
					"'"+descripcion+"'," +
					"'"+getFormatoFecha(fechaInicio)+"'," +
					"'"+getFormatoFecha(fechaTermino)+"'," +
					"'"+getFormatoFecha(fechaTermino)+"'," +
					"'"+idUsuario+"')";
			PreparedStatement statement = conex.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if(resultSet.next())
				vResult.addElement(new Integer(resultSet.getInt(1)));
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		}finally {
			JDBCConnectionFactory.closeSession(conex);
        }
		return vResult;
	}

	private Vector getActualizaTareas(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String idtarea = (String)parameters.get("idtarea");
			String sql = "update tareas set fecha_Termino_real= now(),estado = 1 where idtarea = ?";
			PreparedStatement statement = conex.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, idtarea);
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if(resultSet.next())
				vResult.addElement(new Integer(resultSet.getInt(1)));
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		}finally {
			JDBCConnectionFactory.closeSession(conex);
        }
		return vResult;
	}

	private Vector getEliminaTareas(Hashtable parameters) {
		Vector vResult = new Vector();
		java.sql.Connection conex = JDBCConnectionFactory.getConnection();
		try {
			String estado = (String)parameters.get("estado");
			String idtarea = (String)parameters.get("idtarea");
			String sql = "update tareas set visible = ? where idtarea = ?";
			PreparedStatement statement = conex.prepareStatement(sql);
			statement.setString(1, estado);
			statement.setString(2, idtarea);
			
		} catch (Exception E) {
			System.out.println(E.getMessage());
			System.out.println(E.getCause());
			E.printStackTrace();
		}finally {
			JDBCConnectionFactory.closeSession(conex);
        }
		return vResult;
	}
 


	
	private String getFormatoFecha(String fecha){
		
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