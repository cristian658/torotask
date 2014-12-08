/*
 * Created on 31-05-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.gotoque.torotask.negocio.workflow.ejb;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import com.gotoque.torotask.integracion.jdbc.dao.MotorConsultaDAO;
import com.gotoque.torotask.vo.CalendarioVO;
import com.gotoque.torotask.vo.HorarioVO;
import com.gotoque.torotask.vo.ProyectoVO;
import com.gotoque.torotask.vo.TareaVO;
import com.gotoque.torotask.vo.UsuarioVO;

public class WFMotorConsultaBean implements SessionBean {

	private static final String CMD_GET_CONSULTA_TAREAS = "GET_CONSULTA_TAREAS";
	private static final String CMD_GET_INSERTA_TAREAS = "GET_INSERTA_TAREAS";
	private static final String CMD_GET_ELIMINA_TAREAS = "GET_ELIMINA_TAREAS";
	private static final String CMD_GET_ACTUALIZA_TAREAS = "GET_ACTUALIZA_TAREAS";
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
	private static final String CMD_GET_CONSULTA_REPORTE_TAREA = "GET_CONSULTA_REPORTE_TAREA";
	private static final String CMD_SET_INSERTA_PROYECTO = "SET_INSERTA_PROYECTO";
	private static final String CMD_GET_CONSULTA_GANTT = "GET_CONSULTA_GANTT";
	private static final String CMD_GET_CONSULTA_ID_TAREA = "GET_CONSULTA_ID_TAREA";
	private static final String CMD_GET_CONSULTA_ID_PROYECTO = "GET_CONSULTA_ID_PROYECTO";
	private static final String CMD_GET_CONSULTA_RECURSOS = "GET_CONSULTA_RECURSOS";
	private static final String CMD_GET_CONSULTA_CARGOS = "GET_CONSULTA_CARGOS";
	
	private static final String CMD_GET_CONSULTA_USUARIOS = "GET_CONSULTA_USUARIOS";
	private static final String CMD_GET_ACTUALIZA_USUARIOS = "GET_ACTUALIZA_USUARIOS";
	private static final String CMD_GET_ELIMINA_USUARIOS = "GET_ELIMINA_USUARIOS";
	private static final String CMD_GET_CREA_USUARIOS = "GET_CREA_USUARIOS";
	
	private static final String CMD_GET_CONSULTA_REPORTE_USUARIO = "GET_CONSULTA_REPORTE_USUARIO";
	private static final String CMD_GET_CONSULTA_CARGO_USUARIO = "GET_CONSULTA_CARGO_USUARIO";
	
	private static final String CMD_SET_ACTUALIZA_EQUIPO_TRABAJO = "SET_ACTUALIZA_EQUIPO_TRABAJO";
	private static final String CMD_SET_ELIMINA_EQUIPO_TRABAJO = "SET_ELIMINA_EQUIPO_TRABAJO";
	private static final String CMD_GET_CONSULTA_EQUIPO = "GET_CONSULTA_EQUIPO";

	private static final String CMD_GET_CONSULTA_FERIADOS = "GET_CONSULTA_FERIADOS";
	private static final String CMD_GET_ACTUALIZA_FERIADOS = "GET_ACTUALIZA_FERIADOS";
	private static final String CMD_GET_CREA_FERIADOS = "GET_CREA_FERIADOS";
	
	private static final String CMD_GET_CONSULTA_PARAMETROS = "GET_CONSULTA_PARAMETROS";
	private static final String CMD_GET_ACTUALIZA_PARAMETROS = "GET_ACTUALIZA_PARAMETROS";
	private static final String CMD_GET_CREA_PARAMETROS = "GET_CREA_PARAMETROS";
	
	private static final String CMD_SET_SUBIR_IMAGEN = "SET_SUBIR_IMAGEN";
	
	private static final String CMD_GET_CONSULTA_ID_USUARIO = "GET_CONSULTA_ID_USUARIO";
	private static final String CMD_GET_CONSULTA_CORREO = "GET_CONSULTA_CORREO";

	private static final String CMD_GET_CONSULTA_HORARIO = "GET_CONSULTA_HORARIO";
	
	private static final String CMD_GET_CONSULTA_ACCESO = "GET_CONSULTA_ACCESO";
	
	/** The session context */
	private SessionContext context;

	public WFMotorConsultaBean() {
		super();
	}

	public void setSessionContext(SessionContext newContext)
			throws EJBException {
		context = newContext;
	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	public Vector executeQuery(Hashtable pParameters, String pCommandService)
			throws Exception {
		Vector Resultado = new Vector();

		if (pCommandService.equals(CMD_GET_CONSULTA_TAREAS)) {
			Resultado = getConsultaTareas(pParameters);
		} else if (pCommandService.equals(CMD_GET_INSERTA_TAREAS)) {
			Resultado = getInsertaTareas(pParameters);
		} else if (pCommandService.equals(CMD_GET_ACTUALIZA_TAREAS)) {
			Resultado = getActualizaTareas(pParameters);
		} else if (pCommandService.equals(CMD_GET_ELIMINA_TAREAS)) {
			Resultado = getEliminaTareas(pParameters);
		} else if (pCommandService.equals(CMD_GET_INSERTAR_INTEGRANTES_X_TAREA)) {
			Resultado = setInsertaIntegrantexTarea(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_INTEGRANTES_X_TAREA)) {
			Resultado = getConsultaIntegrantexTarea(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_INGENIEROS)) {
			Resultado = getConsultarIngenieros(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_ESTADOS)) {
			Resultado = getConsultarEstados(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_COMENTARIOS)) {
			Resultado = getConsultarComentarios(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_ADJUNTOS)) {
			Resultado = getConsultarAdjuntos(pParameters);
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
		} else if (pCommandService.equals(CMD_SET_INSERTA_PROYECTO)) {
			Resultado = setInsertaProyecto(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_GANTT)) {
			Resultado = getConsultaGantt(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_ID_TAREA)) {
			Resultado = getConsultarIdTarea(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_ID_PROYECTO)) {
			Resultado = getConsultarIdProyecto(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_RECURSOS)) {
			Resultado = getConsultaRecursos(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_USUARIOS)) {
			Resultado = getConsultaUsuarios(pParameters);
		} else if (pCommandService.equals(CMD_GET_ACTUALIZA_USUARIOS)) {
			Resultado = getActualizaUsuarios(pParameters);
		} else if (pCommandService.equals(CMD_GET_ELIMINA_USUARIOS)) {
			Resultado = getEliminaUsuarios(pParameters);
		} else if (pCommandService.equals(CMD_GET_CREA_USUARIOS)) {
			Resultado = getCreaUsuarios(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_CARGOS)) {
			Resultado = getConsultaCargos(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_REPORTE_USUARIO)) {
			Resultado = getConsultarReporteUsuario(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_CARGO_USUARIO)) {
			Resultado = getConsultarCargoUsuario(pParameters);
		} else if (pCommandService.equals(CMD_SET_ACTUALIZA_EQUIPO_TRABAJO)) {
			Resultado = setActualizaEquipo(pParameters);
		} else if (pCommandService.equals(CMD_SET_ELIMINA_EQUIPO_TRABAJO)) {
			Resultado = setEliminaEquipo(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_EQUIPO)) {
			Resultado = getConsultaEquipo(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_FERIADOS)) {
			Resultado = getConsultaFeriados(pParameters);
		} else if (pCommandService.equals(CMD_GET_ACTUALIZA_FERIADOS)) {
			Resultado = getActualizaFeriados(pParameters);
		} else if (pCommandService.equals(CMD_GET_CREA_FERIADOS)) {
			Resultado = getCreaFeriados(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_PARAMETROS)) {
			Resultado = getConsultaParametros(pParameters);
		} else if (pCommandService.equals(CMD_GET_ACTUALIZA_PARAMETROS)) {
			Resultado = getActualizaParametros(pParameters);
		} else if (pCommandService.equals(CMD_GET_CREA_PARAMETROS)) {
			Resultado = getCreaParametros(pParameters);
		} else if (pCommandService.equals(CMD_SET_SUBIR_IMAGEN)) {
			Resultado = setSubirImagen(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_ID_USUARIO)) {
			Resultado = getConsultarIdUsuario(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_CORREO)) {
			Resultado = getConsultarCorreo(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_HORARIO)) {
			Resultado = getConsultarHorario(pParameters);
		} else if (pCommandService.equals(CMD_GET_CONSULTA_ACCESO)) {
			Resultado = getConsultarAcceso(pParameters);
		}
		return Resultado;
	}
	
	private Vector getConsultarAcceso(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "GET_CONSULTA_ACCESO";
			Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getConsultarHorario(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "GET_CONSULTA_HORARIO";
			Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getConsultarCorreo(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "GET_CONSULTA_CORREO";
			Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);

		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getConsultarIdUsuario(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "GET_CONSULTA_ID_USUARIO";
			Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);

		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector setSubirImagen(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "SET_SUBIR_IMAGEN";
			Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);

		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getConsultaParametros(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "GET_CONSULTA_PARAMETROS";
			Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);

		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}
	
	private Vector getCreaParametros(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "GET_CREA_PARAMETROS";
			Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);

		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getActualizaParametros(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "GET_ACTUALIZA_PARAMETROS";
			Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);

		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getCreaFeriados(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			Resultado = new MotorConsultaDAO().executeQuery("GET_CREA_FERIADOS",pParameters);				
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getActualizaFeriados(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			Resultado = new MotorConsultaDAO().executeQuery("GET_ACTUALIZA_FERIADOS",pParameters);				
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getConsultaFeriados(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			Resultado = new MotorConsultaDAO().executeQuery("GET_CONSULTA_FERIADOS",pParameters);				
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getConsultaEquipo(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			Resultado = new MotorConsultaDAO().executeQuery("GET_CONSULTA_EQUIPO",pParameters);				
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector setActualizaEquipo(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			Resultado = new MotorConsultaDAO().executeQuery("SET_ELIMINA_EQUIPO_TRABAJO",pParameters);				
			String analistas = (String)pParameters.get("analistas");
			for (int i = 0; i < analistas.split(",").length; i++) {
				pParameters.put("analista",analistas.split(",")[i]);
				Resultado = new MotorConsultaDAO().executeQuery("SET_ACTUALIZA_EQUIPO_TRABAJO",pParameters);				
			}
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector setEliminaEquipo(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "SET_ELIMINA_EQUIPO_TRABAJO";
			Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}	
	
	private Vector getConsultarCargoUsuario(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "GET_CONSULTA_CARGO_USUARIO";
			Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getConsultarReporteUsuario(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "GET_CONSULTA_REPORTE_USUARIO";
			Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);

		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getCreaUsuarios(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "GET_CREA_USUARIOS";
			Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);

		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getEliminaUsuarios(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "GET_ELIMINA_USUARIOS";
			Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);

		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getActualizaUsuarios(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "GET_ACTUALIZA_USUARIOS";
			String clave = (String)pParameters.get("clave");
			Resultado = new MotorConsultaDAO().executeQuery("GET_CONSULTA_USUARIOS",pParameters);
			UsuarioVO usuarioVO = (UsuarioVO)Resultado.get(0);
			String oldClave = usuarioVO.getClave();
			if(clave.equals(oldClave)){
				Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);				
			}else{
				String newClave = MotorConsultaDAO.getMd5(clave);
				pParameters.put("clave",newClave);
				Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);
			}
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getConsultaCargos(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "GET_CONSULTA_CARGOS";
			Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);

		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getConsultaUsuarios(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "GET_CONSULTA_USUARIOS";
			Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getConsultaRecursos(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			
			Vector recursos = new MotorConsultaDAO().executeQuery("GET_CONSULTA_EQUIPO",pParameters);
			for (int i = 0; i < recursos.size(); i++) {
				UsuarioVO usuarioVO = (UsuarioVO)recursos.get(i);
				String idAnalista = usuarioVO.getIdUsuario();
				pParameters.put("analista",idAnalista);
				Vector reporte = new MotorConsultaDAO().executeQuery("GET_CONSULTA_RECURSOS",pParameters);				
				Resultado.add((UsuarioVO)reporte.get(0));
			}
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getConsultarIdProyecto(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "GET_CONSULTA_ID_PROYECTO";
			Resultado = new MotorConsultaDAO().executeQuery(comando,
					pParameters);

		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getConsultarIdTarea(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {

			ProyectoVO proyectoVO = null;
			Vector vIntegrantes = null;
			Vector vComentario = null;
			Vector vAdjuntos = null;
			UsuarioVO usuarioVO = null;

			Vector vTarea = new MotorConsultaDAO().executeQuery("GET_CONSULTA_ID_TAREA", pParameters);
			TareaVO tareaVO = (TareaVO) vTarea.get(0);

			pParameters.put("idproyecto", tareaVO.getIdproyecto());
			pParameters.put("idusuario", tareaVO.getIdUsuario());

			Vector vProyecto = new MotorConsultaDAO().executeQuery("GET_CONSULTA_ID_PROYECTO", pParameters);
			if (vProyecto.size() > 0)
				proyectoVO = (ProyectoVO) vProyecto.get(0);

			vIntegrantes = new MotorConsultaDAO().executeQuery("GET_CONSULTA_INTEGRANTES_X_TAREA", pParameters);

			vComentario = new MotorConsultaDAO().executeQuery("GET_CONSULTA_COMENTARIOS", pParameters);

			vAdjuntos = new MotorConsultaDAO().executeQuery("GET_CONSULTA_ADJUNTOS", pParameters);

			Vector vUsuario = new MotorConsultaDAO().executeQuery("GET_CONSULTA_ID_USUARIO", pParameters);
			usuarioVO = (UsuarioVO) vUsuario.get(0);

			tareaVO.setProyectoVO(proyectoVO);
			tareaVO.setIntegrantes(vIntegrantes);
			tareaVO.setUsuarioVO(usuarioVO);
			tareaVO.setAdjuntos(vAdjuntos);
			tareaVO.setComentarios(vComentario);
			Resultado.addElement(vTarea);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getConsultaGantt(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			Vector vProyectos = new MotorConsultaDAO().executeQuery("GET_CONSULTA_GANTT_PROYECTOS", pParameters);
			Vector vTareas = new MotorConsultaDAO().executeQuery("GET_CONSULTA_GANTT_TAREAS", pParameters);
			Date date = new Date();
			int id = 0;
			for (int i = 0; i < vProyectos.size(); i++) {
				TareaVO proyectoVO = (TareaVO) vProyectos.get(i);
				proyectoVO.setId(id++);
				proyectoVO.setSummary("true");
				proyectoVO.setExpanded("true");
				Resultado.add(proyectoVO);
			}
			for (int i = 0; i < vTareas.size(); i++) {
				TareaVO tareaVO = (TareaVO) vTareas.get(i);
				tareaVO.setId(id++);
				int diasTareas = tareaVO.getDiasTareas();
				int totalDias = tareaVO.getTotalDias();
				String fechainicio = tareaVO.getFecha_inicio();
				String fechaTermino = tareaVO.getFechaTerminoEstimada();
				if (totalDias > 0) {
					tareaVO.setPorcentaje(0d);// diasTareas.doubleValue()/totalDias.doubleValue());
				}
				tareaVO.setSummary("false");
				tareaVO.setExpanded("true");
				Resultado.add(tareaVO);
			}

		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector setInsertaProyecto(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "SET_INSERTA_PROYECTO";
			Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);

		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getConsultaReporteTarea(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {

			String id = (String) pParameters.get("id");
			if (!id.equals("")) {
				Resultado = new MotorConsultaDAO().executeQuery("GET_CONSULTA_REPORTE_PROYECTO",pParameters);
			} else {
				Resultado = new MotorConsultaDAO().executeQuery("GET_CONSULTA_REPORTE_TAREAS",pParameters);
			}

			Vector horario = new MotorConsultaDAO().executeQuery("GET_CONSULTA_HORARIO",pParameters);
			
			Vector feriados = new MotorConsultaDAO().executeQuery("GET_CONSULTA_FERIADOS",pParameters);	

			for (int i = 0; i < Resultado.size(); i++) {
				TareaVO tareaVO = (TareaVO) Resultado.get(i);

				String fechaInicio = tareaVO.getFecha_inicio();
				String fechaTerminoEstimada = tareaVO.getFechaTerminoEstimada();
				String fechaTerminoReal = tareaVO.getFechaTerminoReal();

				String cierreEstimado = getHorasNoLaborales(fechaInicio,fechaTerminoEstimada);
				String cierreReal = getHorasNoLaborales(fechaInicio,fechaTerminoReal);
				String totalEstimado = getHorasLaborales(fechaInicio,fechaTerminoEstimada,horario,feriados);
				String totalReal = getHorasLaborales(fechaInicio,fechaTerminoReal,horario,feriados);
				tareaVO.setCierreDiasEstimada(cierreEstimado);
				tareaVO.setCierreDiasReal(cierreReal);
				tareaVO.setTotalDiasEstimada(totalEstimado);
				tareaVO.setTotalDiasReal(totalReal);
			}

		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getConsultaIntegrantes(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "GET_CONSULTA_INTEGRANTES";
			Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getConsultaProyectos(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "GET_CONSULTA_PROYECTOS";
			Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getDescargarAdjunto(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "GET_DESCARGAR_AJUNTO";
			Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector setInsertaAdjunto(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "SET_INSERTA_AJUNTO";
			Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector setInsertaComentario(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "SET_INSERTA_COMENTARIO";
			Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getConsultarComentarios(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "GET_CONSULTA_COMENTARIOS";
			Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getConsultarAdjuntos(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "GET_CONSULTA_ADJUNTOS";
			Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getConsultarIngenieros(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "GET_CONSULTA_INGENIEROS";
			Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getConsultarEstados(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "GET_CONSULTA_ESTADOS";
			Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector setInsertaIntegrantexTarea(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {

			Resultado = new MotorConsultaDAO().executeQuery("GET_ELIMINA_INTEGRANTES_X_TAREA", pParameters);

			String array = (String) pParameters.get("integrantes");
			String idtarea = (String) pParameters.get("idtarea");
			String[] integrantes = array.split(",");
			for (int i = 0; i < integrantes.length; i++) {
				Hashtable parametros = new Hashtable();
				parametros.put("integrante", integrantes[i]);
				parametros.put("idtarea", idtarea);
				Resultado = new MotorConsultaDAO().executeQuery("GET_INSERTAR_INTEGRANTES_X_TAREA", parametros);
			}

		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getConsultaIntegrantexTarea(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "GET_CONSULTA_INTEGRANTES_X_TAREA";
			Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getInsertaTareas(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "GET_INSERTA_TAREAS";
			Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getActualizaTareas(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "GET_ACTUALIZA_TAREAS";
			Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getEliminaTareas(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			String comando = "GET_ELIMINA_TAREAS";
			Resultado = new MotorConsultaDAO().executeQuery(comando,pParameters);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	private Vector getConsultaTareas(Hashtable pParameters) {
		Vector Resultado = new Vector();
		try {
			
			String idPerfil = (String)pParameters.get("idPerfil");
			String idUsuario = (String)pParameters.get("idUsuario");
			
			if(idPerfil.equals("1")){
				Resultado = new MotorConsultaDAO().executeQuery("GET_CONSULTA_TAREA_IDUSUARIO",pParameters);
			}else if(idPerfil.equals("2")){
				pParameters.put("idUsuario",idUsuario);
				Resultado = new MotorConsultaDAO().executeQuery("GET_CONSULTA_TAREAS",pParameters);				
			}else if(idPerfil.equals("3")){
				Resultado = new MotorConsultaDAO().executeQuery("GET_CONSULTA_REPORTE_TAREAS",pParameters);
			}	
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Resultado;
	}

	public static String getHorasNoLaborales(String fechaInicio,String fechaTermino) {

		Calendar fechaInicial = new GregorianCalendar();
		Calendar fechaFinal = new GregorianCalendar();
		String formato = "yyyy-MM-dd HH:mm";
		
		fechaInicial.setTime(getFormatoHorasLaborales(fechaInicio, formato));
		fechaFinal.setTime(getFormatoHorasLaborales(fechaTermino, formato));

		int horas = 0;
		while (fechaInicial.before(fechaFinal)) {
			horas++;
			fechaInicial.add(Calendar.HOUR, 1);
		}
		return horas + "";
	}
	
	public static String getHorasLaborales(String fechaInicio,String fechaTermino,Vector horarios,Vector feriados) {

		Calendar fechaInicial = new GregorianCalendar();
		Calendar fechaFinal = new GregorianCalendar();
		Calendar inicio1 = new GregorianCalendar();
		Calendar inicio2 = new GregorianCalendar();
		Calendar termino1 = new GregorianCalendar();
		Calendar termino2 = new GregorianCalendar();
		fechaInicial.setTime(getFormatoHorasLaborales(fechaInicio, "yyyy-MM-dd HH:mm"));
		fechaFinal.setTime(getFormatoHorasLaborales(fechaTermino, "yyyy-MM-dd HH:mm"));
		
		//todas las horas trabajadas.
		
		double horas = 0;
			while (fechaInicial.before(fechaFinal)) {
				String dia = fechaInicial.get(Calendar.DAY_OF_WEEK)+"";
				if(!esFeriado(fechaInicial,feriados)){
					for (int i = 0; i < horarios.size(); i++) {
						HorarioVO horarioVO = (HorarioVO)horarios.get(i);
				    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						inicio1.setTime(getFormatoHorasLaborales(format.format(fechaInicial.getTime()) + " " + horarioVO.getInicio1(),"yyyy-MM-dd HH:mm"));
						inicio2.setTime(getFormatoHorasLaborales(format.format(fechaInicial.getTime()) + " " + horarioVO.getInicio2(),"yyyy-MM-dd HH:mm"));
						termino1.setTime(getFormatoHorasLaborales(format.format(fechaInicial.getTime()) + " " + horarioVO.getTermino1(),"yyyy-MM-dd HH:mm"));
						termino2.setTime(getFormatoHorasLaborales(format.format(fechaInicial.getTime()) + " " + horarioVO.getTermino2(),"yyyy-MM-dd HH:mm"));
						
						if(horarioVO.getIdDia().equals(dia)){
							if(fechaInicial.before(inicio1) && fechaFinal.after(termino1)){
								horas = horas + fechasDiferenciaEnHoras(inicio1.getTime(),termino1.getTime());								
							}else if(fechaInicial.after(inicio1) && fechaFinal.after(termino1)){
								horas = horas + fechasDiferenciaEnHoras(fechaInicial.getTime(),termino1.getTime());								
							}else if(fechaInicial.after(inicio1) && fechaFinal.before(termino1)){
								horas = horas + fechasDiferenciaEnHoras(fechaInicial.getTime(),fechaFinal.getTime());								
							}else if(fechaInicial.before(inicio1) && fechaFinal.before(termino1)){
								horas = horas + fechasDiferenciaEnHoras(inicio1.getTime(),fechaFinal.getTime());								
							}
							if(fechaInicial.before(inicio2) && fechaFinal.after(termino2)){
								horas = horas + fechasDiferenciaEnHoras(inicio2.getTime(),termino2.getTime());								
							}else if(fechaInicial.after(inicio2) && fechaFinal.after(termino2)){
								horas = horas + fechasDiferenciaEnHoras(fechaInicial.getTime(),termino2.getTime());								
							}else if(fechaInicial.after(inicio2) && fechaFinal.before(termino2)){
								horas = horas + fechasDiferenciaEnHoras(fechaInicial.getTime(),fechaFinal.getTime());								
							}else if(fechaInicial.before(inicio2) && fechaFinal.before(termino2)){
								horas = horas + fechasDiferenciaEnHoras(inicio2.getTime(),fechaFinal.getTime());								
							}
						}
					}
				}
				System.out.println("fecha de proceso : " + new Date(fechaInicial.getTimeInMillis())+ " horas trabajadas : " + horas);
				fechaInicial.add(Calendar.HOUR, 24);
			}
		return horas+"";
	}

	private static boolean esFeriado(Calendar fechaInicial,Vector feriados) {
		//feriados asociados
		String formato = "yyyy-MM-dd";
		Calendar fechaFeriado = new GregorianCalendar();

		for (int i = 0; i < feriados.size(); i++) {
			CalendarioVO calendarioVO = (CalendarioVO)feriados.get(i);
			String feriado = calendarioVO.getFecha();
			fechaFeriado.setTime(getFormatoHorasLaborales(feriado, formato));
			if(fechaInicial.equals(fechaFeriado)){
				System.out.println("feriado fecha : " + new Date(fechaInicial.getTimeInMillis()));
				return true;						
			}
		}
		return false;
	}

	private static Date getFormatoHorasLaborales(String fecha,String formato) {

		SimpleDateFormat entrada = new SimpleDateFormat(formato);
		Date resultado = null;
		try {
			long long1 = entrada.parse(fecha).getTime();
			resultado = new Date(long1);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return resultado;
	}
	
	public static double fechasDiferenciaEnHoras(Date fechaInicial, Date fechaFinal) {

		long fechaInicialMs = fechaInicial.getTime();
		long fechaFinalMs = fechaFinal.getTime();
		double diferencia = fechaFinalMs - fechaInicialMs;
		double horas = diferencia / (1000 * 60 * 60);
		if(horas < 0)horas = 0;
		return horas;
		}


}
