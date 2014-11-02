package com.gotoque.torotask.integracion.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import com.gotoque.torotask.vo.AdjuntoVO;
import com.gotoque.torotask.vo.ComentarioVO;
import com.gotoque.torotask.vo.EstadosVO;
import com.gotoque.torotask.vo.IntegrantesVO;
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
    private static final String CMD_GET_CONSULTA_REPORTE_TAREA = "GET_CONSULTA_REPORTE_TAREA";
    private static final String CMD_SET_INSERTA_PROYECTO = "SET_INSERTA_PROYECTO";
    private static final String CMD_GET_CONSULTA_GANTT_PROYECTOS = "GET_CONSULTA_GANTT_PROYECTOS";
    private static final String CMD_GET_CONSULTA_GANTT_TAREAS = "GET_CONSULTA_GANTT_TAREAS";

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
        } else if (pCommandService.equals(CMD_SET_INSERTA_PROYECTO)) {
            Resultado = setInsertaProyecto(pParameters);
        } else if (pCommandService.equals(CMD_GET_CONSULTA_GANTT_PROYECTOS)) {
            Resultado = getConsultaGanttProyectos(pParameters);
        } else if (pCommandService.equals(CMD_GET_CONSULTA_GANTT_TAREAS)) {
            Resultado = getConsultaGanttTareas(pParameters);
        }
        return Resultado;
    }

    private Vector getConsultaGanttProyectos(Hashtable parameters) {
        Vector vResult = new Vector();
        java.sql.Connection conex = JDBCConnectionFactory.getConnection();
        try {
            String sql = "SELECT p.idproyecto,proyecto,DATE_FORMAT(min(t.fecha_inicio),'%Y-%m-%d %h:%m:%s %T') as fechainicio,DATE_FORMAT(max(t.fecha_termino_estimada),'%Y-%m-%d %h:%m:%s %T') as fecha_termino "
                    + "FROM tareas t "
                    + "inner join proyectos p "
                    + "on t.idproyecto = p.idproyecto "
                    + "where t.idproyecto = '1' "
                    + "group by p.idproyecto "
                    + "limit 1";
            PreparedStatement statement = conex.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TareaVO proyectoVO = new TareaVO();
                proyectoVO.setIdtarea(resultSet.getString(1));
                proyectoVO.setTarea(resultSet.getString(2));
                proyectoVO.setFecha_inicio(resultSet.getString(3));
                proyectoVO.setFechaTerminoEstimada(resultSet.getString(4));
                vResult.add(proyectoVO);
            }

        } catch (Exception E) {
            System.out.println(E.getMessage());
            System.out.println(E.getCause());
            E.printStackTrace();
        }
        return vResult;
    }

    private Vector getConsultaGanttTareas(Hashtable parameters) {
        Vector vResult = new Vector();
        java.sql.Connection conex = JDBCConnectionFactory.getConnection();
        try {
            String sql = "SELECT t.idtarea,t.tarea,DATE_FORMAT(t.fecha_inicio,'%Y-%m-%d %h:%m:%s %P') as fechainicio,DATE_FORMAT(t.fecha_termino_estimada,'%Y-%m-%d %h:%m:%s %P') as fecha_termino,DATEDIFF(fecha_termino_estimada,fecha_inicio) as total_dias,DATEDIFF(now(),fecha_inicio) as dias_tarea,idproyecto FROM tareas t where t.idproyecto = '1'";
            PreparedStatement statement = conex.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TareaVO tareaVO = new TareaVO();
                tareaVO.setIdtarea(resultSet.getString(1));
                tareaVO.setTarea(resultSet.getString(2));
                tareaVO.setFecha_inicio(resultSet.getString(3));
                tareaVO.setFechaTerminoEstimada(resultSet.getString(4));
                tareaVO.setTotalDias(new Integer(resultSet.getInt(5)));
                tareaVO.setDiasTareas(new Integer(resultSet.getInt(6)));
                tareaVO.setIdproyecto(resultSet.getString(7));
                vResult.add(tareaVO);
            }

        } catch (Exception E) {
            System.out.println(E.getMessage());
            System.out.println(E.getCause());
            E.printStackTrace();
        }
        return vResult;
    }

    private Vector setInsertaProyecto(Hashtable parameters) {
        Vector vResult = new Vector();
        java.sql.Connection conex = JDBCConnectionFactory.getConnection();
        String proyecto = (String) parameters.get("proyecto");
        try {
            String sql = "insert into proyectos(proyecto)values(?)";
            PreparedStatement statement = conex.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, proyecto);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                vResult.add(new Integer(resultSet.getInt(1)));
            }

        } catch (Exception E) {
            System.out.println(E.getMessage());
            System.out.println(E.getCause());
            E.printStackTrace();
        }
        return vResult;
    }

    private Vector getConsultaReporteTarea(Hashtable parameters) {
        Vector vResult = new Vector();
        java.sql.Connection conex = JDBCConnectionFactory.getConnection();
        try {
            String sql = "SELECT t.idtarea, t.tarea, t.descripcion, t.idproyecto, DATE_FORMAT(t.fecha_inicio,'%Y-%m-%d %h:%m:%s %p'),DATE_FORMAT(t.fecha_termino_estimada,'%Y-%m-%d %h:%m:%s %p'),DATE_FORMAT(t.fecha_termino_real,'%Y-%m-%d %h:%m:%s %p'),t.idusuario, t.estado FROM tareas t";
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
                tareaVO.setEstado(resultSet.getString(9));
                vResult.add(tareaVO);
            }

        } catch (Exception E) {
            System.out.println(E.getMessage());
            System.out.println(E.getCause());
            E.printStackTrace();
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
            while (resultSet.next()) {
                UsuarioVO usuarioVO = new UsuarioVO();
                usuarioVO.setIdUsuario(resultSet.getString(1));
                usuarioVO.setCorreo(resultSet.getString(2));
                vResult.add(usuarioVO);
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
                vResult.add(proyectoVO);
            }

        } catch (Exception E) {
            System.out.println(E.getMessage());
            System.out.println(E.getCause());
            E.printStackTrace();
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
                String adjunto = resultSet.getString(1) + "/" + resultSet.getString(2);
                vResult.add(adjunto);
            }

        } catch (Exception E) {
            System.out.println(E.getMessage());
            System.out.println(E.getCause());
            E.printStackTrace();
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
            PreparedStatement statement = conex.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, nombre);
            statement.setInt(2, size.intValue());
            statement.setString(3, idtarea);
            statement.setString(4, idusuario);
            statement.setString(5, contentType);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                vResult.add(new Integer(resultSet.getInt(1)));
            }

        } catch (Exception E) {
            System.out.println(E.getMessage());
            System.out.println(E.getCause());
            E.printStackTrace();
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
                vResult.add(estadosVO);
            }
        } catch (Exception E) {
            System.out.println(E.getMessage());
            System.out.println(E.getCause());
            E.printStackTrace();
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
                vResult.add(estadosVO);
            }

        } catch (Exception E) {
            System.out.println(E.getMessage());
            System.out.println(E.getCause());
            E.printStackTrace();
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
        }
        return vResult;
    }

    private Vector getConsultaTareas(Hashtable parameters) {
        Vector vResult = new Vector();
        java.sql.Connection conex = JDBCConnectionFactory.getConnection();
        try {
            String sql = "SELECT t.idtarea, t.tarea, t.descripcion,(select concat('P','-',p.idproyecto,':',p.proyecto) from proyectos p where p.idproyecto = t.idproyecto) as proyecto, DATE_FORMAT(t.fecha_inicio,'%Y-%m-%d %h:%m:%s %p'),DATE_FORMAT(t.fecha_termino_estimada,'%Y-%m-%d %h:%m:%s %p'),DATE_FORMAT(t.fecha_termino_real,'%Y-%m-%d %h:%m:%s %p'),t.idusuario, t.estado FROM tareas t where t.estado = 0";
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
                tareaVO.setEstado(resultSet.getString(9));
                vResult.add(tareaVO);
            }

        } catch (Exception E) {
            System.out.println(E.getMessage());
            System.out.println(E.getCause());
            E.printStackTrace();
        }
        return vResult;
    }

    private Vector setConsultaIntegrantexTarea(Hashtable parameters) {
        Vector vResult = new Vector();
        java.sql.Connection conex = JDBCConnectionFactory.getConnection();
        try {
            String idtarea = (String) parameters.get("idtarea");
            String sql = "SELECT * FROM integrantesxtarea where idtarea = ?";
            PreparedStatement statement = conex.prepareStatement(sql);
            statement.setString(1, idtarea);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                vResult.add(resultSet.getString(3));
            }

        } catch (Exception E) {
            System.out.println(E.getMessage());
            System.out.println(E.getCause());
            E.printStackTrace();
        }
        return vResult;
    }

    private Vector getConsultaAdjuntos(Hashtable parameters) {
        Vector vResult = new Vector();
        java.sql.Connection conex = JDBCConnectionFactory.getConnection();
        try {
            String idtarea = (String) parameters.get("idtarea");
            String sql = "SELECT concat(idadjunto,'-', nombre) as text, size, '.doc' as extension FROM adjuntos where idtarea = ?";
            PreparedStatement statement = conex.prepareStatement(sql);
            statement.setString(1, idtarea);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                AdjuntoVO adjuntoVO = new AdjuntoVO();
                adjuntoVO.setName(resultSet.getString(1));
                adjuntoVO.setSize(resultSet.getString(2));
                adjuntoVO.setExtension(resultSet.getString(3));
                vResult.add(adjuntoVO);
            }
        } catch (Exception E) {
            System.out.println(E.getMessage());
            System.out.println(E.getCause());
            E.printStackTrace();
        }
        return vResult;
    }

    private Vector getConsultaComentarios(Hashtable parameters) {
        Vector vResult = new Vector();
        java.sql.Connection conex = JDBCConnectionFactory.getConnection();
        try {
            String idtarea = (String) parameters.get("idtarea");
            String sql = "select * from comentario where idtarea = ?";
            PreparedStatement statement = conex.prepareStatement(sql);
            statement.setString(1, idtarea);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ComentarioVO comentarioVO = new ComentarioVO();
                comentarioVO.setIdComentario(resultSet.getString(1));
                comentarioVO.setComentario(resultSet.getString(2));
                comentarioVO.setFecha(resultSet.getDate(3));
                comentarioVO.setUsuario(resultSet.getString(4));
                vResult.add(comentarioVO);
            }

        } catch (Exception E) {
            System.out.println(E.getMessage());
            System.out.println(E.getCause());
            E.printStackTrace();
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
            String sql = "insert into tareas(tarea,idproyecto,descripcion,fecha_inicio,fecha_termino_estimada,idusuario)values("
                    + "'" + tarea + "',"
                    + "'" + idproyecto + "',"
                    + "'" + descripcion + "',"
                    + "'" + fechaInicio + "',"
                    + "'" + fechaTermino + "',"
                    + "'" + idUsuario + "')";
            PreparedStatement statement = conex.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                vResult.add(new Integer(resultSet.getInt(1)));
            }

        } catch (Exception E) {
            System.out.println(E.getMessage());
            System.out.println(E.getCause());
            E.printStackTrace();
        }
        return vResult;
    }

    private Vector getActualizaTareas(Hashtable parameters) {
        Vector vResult = new Vector();
        java.sql.Connection conex = JDBCConnectionFactory.getConnection();
        try {
            String idtarea = (String) parameters.get("idtarea");
            String sql = "update tareas set fecha_Termino_real= now(),estado = 1 where idtarea = ?";
            PreparedStatement statement = conex.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, idtarea);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                vResult.add(new Integer(resultSet.getInt(1)));
            }

        } catch (Exception E) {
            System.out.println(E.getMessage());
            System.out.println(E.getCause());
            E.printStackTrace();
        }
        return vResult;
    }

    private Vector getEliminaTareas(Hashtable parameters) {
        Vector vResult = new Vector();
        java.sql.Connection conex = JDBCConnectionFactory.getConnection();
        try {
            String estado = (String) parameters.get("estado");
            String idtarea = (String) parameters.get("idtarea");
            String sql = "update tareas set visible = ? where idtarea = ?";
            PreparedStatement statement = conex.prepareStatement(sql);
            statement.setString(1, estado);
            statement.setString(2, idtarea);

        } catch (Exception E) {
            System.out.println(E.getMessage());
            System.out.println(E.getCause());
            E.printStackTrace();
        }
        return vResult;
    }
}