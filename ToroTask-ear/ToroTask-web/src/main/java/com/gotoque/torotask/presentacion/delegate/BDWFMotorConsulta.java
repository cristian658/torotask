/*
 * Created on 31-05-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.gotoque.torotask.presentacion.delegate;

import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import com.gotoque.torotask.negocio.workflow.interfaces.WFMotorConsulta;
import com.gotoque.torotask.negocio.workflow.interfaces.WFMotorConsultaHome;

public class BDWFMotorConsulta {

	private WFMotorConsultaHome WFMotorConsultaHome;

	private final String JNDI_NAME = "ejb/WFToroTask";

	public BDWFMotorConsulta() throws Exception {
		Context context = null;
		try {
			Properties p = new Properties();
			p.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
			p.setProperty(Context.PROVIDER_URL, "localhost:1099");
			p.setProperty(Context.URL_PKG_PREFIXES,"org.jboss.naningrg.jnp.interfaces");
			context = new InitialContext(p);
			Object object = context.lookup(JNDI_NAME);
			WFMotorConsultaHome = (WFMotorConsultaHome) PortableRemoteObject.narrow(object, WFMotorConsultaHome.class);
		} catch (Exception E) {
			E.printStackTrace();
		}
	}

	private WFMotorConsulta crearEJB() throws Exception {
		WFMotorConsulta oWFMotorConsulta = null;
		try {
			oWFMotorConsulta = WFMotorConsultaHome.create();
		} catch (Exception E) {
			E.printStackTrace();
		}
		return oWFMotorConsulta;
	}

	public Vector getConsultarTarea(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_TAREAS";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector setInsertaTarea(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_INSERTA_TAREAS";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector setActualizaTarea(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_ACTUALIZA_TAREAS";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector setEliminaTarea(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_ELIMINA_TAREAS";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector setInsertaIntegrantexTarea(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_INSERTAR_INTEGRANTES_X_TAREA";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getConsultarIngenieros(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_INGENIEROS";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getConsultarEstados(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_ESTADOS";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getConsultarComentarios(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_COMENTARIOS";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getConsultarAdjuntos(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_ADJUNTOS";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector setInsertaComentario(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "SET_INSERTA_COMENTARIO";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector setInsertaAdjuntos(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "SET_INSERTA_AJUNTO";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getDescargarAdjunto(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_DESCARGAR_AJUNTO";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getConsultaProyectos(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_PROYECTOS";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getConsultaIntegrantesxTarea(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_INTEGRANTES_X_TAREA";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getConsultaIntegrantes(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_INTEGRANTES";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getConsultarReporteTarea(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_REPORTE_TAREA";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector setInsertaProyecto(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "SET_INSERTA_PROYECTO";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getConsultarGantt(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_GANTT";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getConsultarIdTarea(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_ID_TAREA";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector setConsultaIdProyecto(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_ID_PROYECTO";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getConsultaRecursos(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_RECURSOS";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getConsultarUsuarios(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_USUARIOS";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getConsultaCargos(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_CARGOS";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getActualizaUsuarios(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_ACTUALIZA_USUARIOS";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getEliminaUsuarios(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_ELIMINA_USUARIOS";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getCreaUsuarios(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CREA_USUARIOS";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getConsultaEstados(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_ESTADOS";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getConsultarReporteUsuario(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_REPORTE_USUARIO";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getConsultaCargoUsuarios(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_CARGO_USUARIO";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector setActualizaEquipo(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "SET_ACTUALIZA_EQUIPO_TRABAJO";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}
	
	public Vector setEliminaEquipo(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "SET_ELIMINA_EQUIPO_TRABAJO";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getConsultaEquipo(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_EQUIPO";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getConsultaFeriados(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_FERIADOS";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getActualizaFeriados(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_ACTUALIZA_FERIADOS";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getCreaFeriados(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CREA_FERIADOS";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getConsultaParametros(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_PARAMETROS";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}
	
	public Vector getCreaParametros(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CREA_PARAMETROS";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getActualizaParametros(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_ACTUALIZA_PARAMETROS";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector setSubirImagen(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "SET_SUBIR_IMAGEN";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getConsultarIdUsuario(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_ID_USUARIO";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getConsultaCorreo(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_CORREO";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getConsultaHorarios(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_HORARIO";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

	public Vector getConsultarAcceso(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_ACCESO";
		try {
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return Result;
	}

}
