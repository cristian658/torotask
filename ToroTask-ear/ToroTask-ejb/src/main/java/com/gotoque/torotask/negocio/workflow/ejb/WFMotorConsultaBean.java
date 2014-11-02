/*
 * Created on 31-05-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.gotoque.torotask.negocio.workflow.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import com.gotoque.torotask.integracion.jdbc.dao.MotorConsultaDAO;
import com.gotoque.torotask.negocio.Utilidades;
import com.gotoque.torotask.vo.ProyectoVO;
import com.gotoque.torotask.vo.TareaVO;

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

    public Vector executeQuery(Hashtable pParameters, String pCommandService) throws Exception {
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
        }

        return Resultado;
    }

    private Vector getConsultaGantt(Hashtable pParameters) {
        Vector Resultado = new Vector();
        try {
            Vector vProyectos = new MotorConsultaDAO().executeQuery("GET_CONSULTA_GANTT_PROYECTOS", pParameters);
            Vector vTareas = new MotorConsultaDAO().executeQuery("GET_CONSULTA_GANTT_TAREAS", pParameters);

            int id = 0;
            for (int i = 0; i < vProyectos.size(); i++) {
                TareaVO proyectoVO = (TareaVO) vProyectos.get(i);
                proyectoVO.setId(new Integer(id++));
                proyectoVO.setSummary("true");
                proyectoVO.setExpanded("true");
                Resultado.add(proyectoVO);
            }
            for (int i = 0; i < vTareas.size(); i++) {
                TareaVO tareaVO = (TareaVO) vTareas.get(i);
                tareaVO.setId(new Integer(id++));
                Integer diasTareas = tareaVO.getDiasTareas();
                Integer totalDias = tareaVO.getTotalDias();
                String fechainicio = tareaVO.getFecha_inicio();
                String fechaTermino = tareaVO.getFechaTerminoEstimada();
                if (totalDias.intValue() > 0) {
                    double uno = diasTareas.doubleValue();
                    double dos = totalDias.doubleValue();
                    Double total = new Double(uno / dos);
                    tareaVO.setPorcentaje(total);
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
            Resultado = new MotorConsultaDAO().executeQuery(comando, pParameters);

        } catch (Exception E) {
            E.printStackTrace();
        }
        return Resultado;
    }

    private Vector getConsultaReporteTarea(Hashtable pParameters) {
        Vector Resultado = new Vector();
        try {
            String comando = "GET_CONSULTA_REPORTE_TAREA";
            Resultado = new MotorConsultaDAO().executeQuery(comando, pParameters);

        } catch (Exception E) {
            E.printStackTrace();
        }
        return Resultado;
    }

    private Vector getConsultaIntegrantes(Hashtable pParameters) {
        Vector Resultado = new Vector();
        try {
            String comando = "GET_CONSULTA_INTEGRANTES";
            Resultado = new MotorConsultaDAO().executeQuery(comando, pParameters);

        } catch (Exception E) {
            E.printStackTrace();
        }
        return Resultado;
    }

    private Vector getConsultaProyectos(Hashtable pParameters) {
        Vector Resultado = new Vector();
        try {
            String comando = "GET_CONSULTA_PROYECTOS";
            Vector vResultado = new MotorConsultaDAO().executeQuery(comando, pParameters);

            for (int i = 0; i < vResultado.size(); i++) {
                ProyectoVO proyectoVO = (ProyectoVO) vResultado.get(i);
                String id = proyectoVO.getIdproyectos();
                String proyecto = proyectoVO.getProyecto();
                proyectoVO.setProyecto("P-" + id + ":" + proyecto);
                Resultado.add(proyectoVO.getProyecto());
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
        return Resultado;
    }

    private Vector getDescargarAdjunto(Hashtable pParameters) {
        Vector Resultado = new Vector();
        try {
            String comando = "GET_DESCARGAR_AJUNTO";
            Resultado = new MotorConsultaDAO().executeQuery(comando, pParameters);

        } catch (Exception E) {
            E.printStackTrace();
        }
        return Resultado;
    }

    private Vector setInsertaAdjunto(Hashtable pParameters) {
        Vector Resultado = new Vector();
        try {
            String comando = "SET_INSERTA_AJUNTO";
            Resultado = new MotorConsultaDAO().executeQuery(comando, pParameters);

        } catch (Exception E) {
            E.printStackTrace();
        }
        return Resultado;
    }

    private Vector setInsertaComentario(Hashtable pParameters) {
        Vector Resultado = new Vector();
        try {
            String comando = "SET_INSERTA_COMENTARIO";
            Resultado = new MotorConsultaDAO().executeQuery(comando, pParameters);

        } catch (Exception E) {
            E.printStackTrace();
        }
        return Resultado;
    }

    private Vector getConsultarComentarios(Hashtable pParameters) {
        Vector Resultado = new Vector();
        try {
            String comando = "GET_CONSULTA_COMENTARIOS";
            Resultado = new MotorConsultaDAO().executeQuery(comando, pParameters);

        } catch (Exception E) {
            E.printStackTrace();
        }
        return Resultado;
    }

    private Vector getConsultarAdjuntos(Hashtable pParameters) {
        Vector Resultado = new Vector();
        try {
            String comando = "GET_CONSULTA_ADJUNTOS";
            Resultado = new MotorConsultaDAO().executeQuery(comando, pParameters);

        } catch (Exception E) {
            E.printStackTrace();
        }
        return Resultado;
    }

    private Vector getConsultarIngenieros(Hashtable pParameters) {
        Vector Resultado = new Vector();
        try {
            String comando = "GET_CONSULTA_INGENIEROS";
            Resultado = new MotorConsultaDAO().executeQuery(comando, pParameters);

        } catch (Exception E) {
            E.printStackTrace();
        }
        return Resultado;
    }

    private Vector getConsultarEstados(Hashtable pParameters) {
        Vector Resultado = new Vector();
        try {
            String comando = "GET_CONSULTA_ESTADOS";
            Resultado = new MotorConsultaDAO().executeQuery(comando, pParameters);

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
            Resultado = new MotorConsultaDAO().executeQuery(comando, pParameters);
        } catch (Exception E) {
            E.printStackTrace();
        }
        return Resultado;
    }

    private Vector getInsertaTareas(Hashtable pParameters) {
        Vector Resultado = new Vector();
        try {
            String comando = "GET_INSERTA_TAREAS";
            Resultado = new MotorConsultaDAO().executeQuery(comando, pParameters);

        } catch (Exception E) {
            E.printStackTrace();
        }
        return Resultado;
    }

    private Vector getActualizaTareas(Hashtable pParameters) {
        Vector Resultado = new Vector();
        try {
            String comando = "GET_ACTUALIZA_TAREAS";
            Resultado = new MotorConsultaDAO().executeQuery(comando, pParameters);

        } catch (Exception E) {
            E.printStackTrace();
        }
        return Resultado;
    }

    private Vector getEliminaTareas(Hashtable pParameters) {
        Vector Resultado = new Vector();
        try {
            String comando = "GET_ELIMINA_TAREAS";
            Resultado = new MotorConsultaDAO().executeQuery(comando, pParameters);

        } catch (Exception E) {
            E.printStackTrace();
        }
        return Resultado;
    }

    private Vector getConsultaTareas(Hashtable pParameters) {
        Vector Resultado = new Vector();
        try {
            String comando = "GET_CONSULTA_TAREAS";
            Resultado = new MotorConsultaDAO().executeQuery(comando, pParameters);

        } catch (Exception E) {
            E.printStackTrace();
        }
        return Resultado;
    }
}
