package com.gotoque.torotask.presentacion.struts.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.CommonsMultipartRequestHandler;
import org.apache.struts.upload.FormFile;

import com.gotoque.torotask.presentacion.delegate.BDWFMotorConsulta;
import com.gotoque.torotask.presentacion.struts.Utilidades.EnviarEmail;
import com.gotoque.torotask.presentacion.struts.Utilidades.JSONArray;
import com.gotoque.torotask.presentacion.struts.Utilidades.JSONException;
import com.gotoque.torotask.presentacion.struts.Utilidades.JSONObject;
import com.gotoque.torotask.presentacion.struts.form.CalendarioForm;
import com.gotoque.torotask.vo.AdjuntoVO;
import com.gotoque.torotask.vo.ProyectoVO;

public class CalendarioAction extends DispatchAction {

	String idUsuario = "1";

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String fechaHoy = dateFormat.format(new Date()).toString();

		request.setAttribute("fechaHoy", fechaHoy);
		request.setAttribute("fechaStartTime", fechaHoy + " 08:00");
		request.setAttribute("fechaEndTime", fechaHoy + " 13:00");
		
		BDWFMotorConsulta oBDWFMotorConsulta;
		Vector proyectos = new Vector();
		Hashtable hParameters = new Hashtable();
		try {
			
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			proyectos = oBDWFMotorConsulta.getConsultaProyectos(hParameters);
			JSONArray object = new JSONArray(proyectos);
			request.setAttribute("proyectos",object);
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute("error", mensage);
			return mapping.findForward("error");
		}
		
		return mapping.findForward("continuar");
	}

	public ActionForward getConsultarTarea(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BDWFMotorConsulta oBDWFMotorConsulta;
		Vector tareas = new Vector();
		Hashtable hParameters = new Hashtable();
		try {
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			tareas = oBDWFMotorConsulta.getConsultarTarea(hParameters);
			PrintWriter out = response.getWriter();
			JSONObject object = new JSONObject();
			object.put("tareas", tareas);
			out.print(object);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute("error", mensage);
			return mapping.findForward("error");

		}
	}

	public ActionForward getConsultarIdTarea(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String idtarea = request.getParameter("id");

		BDWFMotorConsulta oBDWFMotorConsulta;
		Vector tareas = new Vector();
		Hashtable hParameters = new Hashtable();
		hParameters.put("idtarea", idtarea);
		try {
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			tareas = oBDWFMotorConsulta.getConsultarIdTarea(hParameters);
			PrintWriter out = response.getWriter();
			JSONObject object = new JSONObject();
			object.put("tareas", tareas);
			out.print(object);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute("error", mensage);
			return mapping.findForward("error");
		}
	}

	public ActionForward setActualizaTarea(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BDWFMotorConsulta oBDWFMotorConsulta;
		Vector tareas = new Vector();
		Vector proyectos = new Vector();

		Hashtable hParameters = new Hashtable();
		try {
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			String idtarea = request.getParameter("idtarea");
			String idproyecto = request.getParameter("idproyecto");
			String tarea = request.getParameter("tarea");
			String estado = request.getParameter("estado");
			String descripcion = request.getParameter("descripcion");
			String comentario = request.getParameter("comentario");
			String fechaInicio = request.getParameter("fechaInicio");
			String fechaTermino = request.getParameter("fechaTermino");
			String integrantes = request.getParameter("integrantes");

			hParameters.put("idtarea", idtarea);
			hParameters.put("tarea", tarea);
			hParameters.put("estado", estado);
			hParameters.put("descripcion", descripcion);
			hParameters.put("fechaInicio", fechaInicio);
			hParameters.put("fechaTermino", fechaTermino);
			hParameters.put("fechaTermino", fechaTermino);
			hParameters.put("comentario", comentario);
			hParameters.put("idUsuario", idUsuario);
			hParameters.put("integrantes", integrantes);
			hParameters.put("idproyecto", idproyecto);
			
			
			if (!isNumeric(idproyecto)) {
				proyectos = oBDWFMotorConsulta.setConsultaIdProyecto(hParameters);
				if (proyectos.size() > 0) {
					proyectos = oBDWFMotorConsulta.setInsertaProyecto(hParameters);
					idproyecto = proyectos.get(0).toString();
					hParameters.put("idproyecto", idproyecto);
				}
			}
			
			if (idtarea.equals("0")) {
				tareas = oBDWFMotorConsulta.setInsertaTarea(hParameters);
				idtarea = tareas.get(0).toString();
				hParameters.put("idtarea", idtarea);
			} else {
				if (estado.equals("on")) {
					tareas = oBDWFMotorConsulta.setActualizaTarea(hParameters);
				}
			}
			if (comentario != "") {
				oBDWFMotorConsulta.setInsertaComentario(hParameters);
			}

			oBDWFMotorConsulta.setInsertaIntegrantexTarea(hParameters);

			Vector adjuntos = (Vector) request.getSession().getAttribute("adjuntos");
			if (adjuntos != null) {
				for (int i = 0; i < adjuntos.size(); i++) {
					Hashtable archivo = (Hashtable) adjuntos.get(i);
					String nombre = (String) archivo.get("nombre");
					Integer size = (Integer) archivo.get("size");
					String contentType = (String) archivo.get("contentType");
					byte[] fileData = (byte[]) archivo.get("fileData");
					if (adjuntos != null) {
						try {
							hParameters.put("nombre", nombre);
							hParameters.put("size", size);
							hParameters.put("idtarea", idtarea);
							hParameters.put("idUsuario", idUsuario);
							hParameters.put("contentType", contentType);
							Vector data = oBDWFMotorConsulta.setInsertaAdjuntos(hParameters);
							String iddata = data.get(0).toString();
							String path = "c:\\torotask\\" + iddata;
							File f = new File(path);
							if (makeSureDirectoryExists(parent(f))) {
								FileOutputStream out = new FileOutputStream(f);
								out.write(fileData);
								out.flush();
								out.close();
							}
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
			EnviarEmail enviarEmail = new EnviarEmail(idtarea);
			enviarEmail.start();
			request.getSession().setAttribute("adjuntos", null);
			PrintWriter out = response.getWriter();
			JSONObject object = new JSONObject();
			object.put("tareas", tareas);
			out.print(object);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute("error", mensage);
			request.getSession().setAttribute("adjuntos", null);
			return mapping.findForward("error");
		}
	}

	public ActionForward getConsultarGantt(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BDWFMotorConsulta oBDWFMotorConsulta;
		Vector tareas = new Vector();
		Hashtable hParameters = new Hashtable();
		try {
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			tareas = oBDWFMotorConsulta.getConsultarGantt(hParameters);
			PrintWriter out = response.getWriter();
			JSONArray object = new JSONArray(tareas);
			out.print(object);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute("error", mensage);
			return mapping.findForward("error");

		}
	}

	public ActionForward getConsultarIngenieros(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BDWFMotorConsulta oBDWFMotorConsulta;
		Vector ingenieros = new Vector();
		Hashtable hParameters = new Hashtable();
		try {
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			ingenieros = oBDWFMotorConsulta.getConsultarIngenieros(hParameters);
			PrintWriter out = response.getWriter();
			JSONArray Array = new JSONArray(ingenieros);
			out.print(Array);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute("error", mensage);
			return mapping.findForward("error");

		}
	}

	public ActionForward getConsultaIntegrantes(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BDWFMotorConsulta oBDWFMotorConsulta;
		Vector ingenieros = new Vector();
		Hashtable hParameters = new Hashtable();
		try {
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			ingenieros = oBDWFMotorConsulta.getConsultaIntegrantes(hParameters);
			PrintWriter out = response.getWriter();
			JSONArray Array = new JSONArray(ingenieros);
			out.print(Array);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute("error", mensage);
			return mapping.findForward("error");

		}
	}

	public ActionForward getConsultaProyectos(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BDWFMotorConsulta oBDWFMotorConsulta;
		Vector tareas = new Vector();
		Hashtable hParameters = new Hashtable();
		try {
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			tareas = oBDWFMotorConsulta.getConsultaProyectos(hParameters);
			PrintWriter out = response.getWriter();
			JSONArray Array = new JSONArray(tareas);
			out.print(Array);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute("error", mensage);
			return mapping.findForward("error");

		}
	}

	public ActionForward getConsultaIntegrantesxTarea(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BDWFMotorConsulta oBDWFMotorConsulta;
		Vector integrantes = new Vector();
		Hashtable hParameters = new Hashtable();
		String idtarea = request.getParameter("idtarea");
		try {
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			hParameters.put("idtarea", idtarea);
			integrantes = oBDWFMotorConsulta
					.getConsultaIntegrantesxTarea(hParameters);
			PrintWriter out = response.getWriter();
			JSONArray array = new JSONArray(integrantes);
			Vector vector = new Vector();
			for (int i = 0; i < array.length(); i++) {
				vector.add(array.getJSONObject(i).get("idUsuario"));
			}
			out.print(vector);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute("error", mensage);
			return mapping.findForward("error");

		}
	}

	public ActionForward getConsultarAdjuntos(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BDWFMotorConsulta oBDWFMotorConsulta;
		Vector adjuntos = new Vector();
		Hashtable hParameters = new Hashtable();
		try {
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			String idtarea = request.getParameter("idtarea");
			hParameters.put("idtarea", idtarea);
			adjuntos = oBDWFMotorConsulta.getConsultarAdjuntos(hParameters);
			PrintWriter out = response.getWriter();
			JSONArray object = new JSONArray(adjuntos);
			out.print(object);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute("error", mensage);
			return mapping.findForward("error");

		}
	}

	public ActionForward getConsultarComentarios(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BDWFMotorConsulta oBDWFMotorConsulta;
		Vector comentarios = new Vector();
		Hashtable hParameters = new Hashtable();
		try {
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			String idtarea = request.getParameter("idtarea");
			hParameters.put("idtarea", idtarea);
			comentarios = oBDWFMotorConsulta
					.getConsultarComentarios(hParameters);
			PrintWriter out = response.getWriter();
			JSONObject object = new JSONObject();
			object.put("comentarios", comentarios);
			object.put("total", comentarios.size());
			out.print(object);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute("error", mensage);
			return mapping.findForward("error");

		}
	}

	public ActionForward getConsultarEstados(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BDWFMotorConsulta oBDWFMotorConsulta;
		Vector estados = new Vector();
		Hashtable hParameters = new Hashtable();
		try {
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			estados = oBDWFMotorConsulta.getConsultarEstados(hParameters);
			PrintWriter out = response.getWriter();
			JSONObject object = new JSONObject();
			object.put("estados", estados);
			out.print(object);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute("error", mensage);
			return mapping.findForward("error");

		}
	}

	public ActionForward setEliminaTarea(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BDWFMotorConsulta oBDWFMotorConsulta;
		Vector tareas = new Vector();
		Hashtable hParameters = new Hashtable();
		try {
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			tareas = oBDWFMotorConsulta.setEliminaTarea(hParameters);
			PrintWriter out = response.getWriter();
			JSONObject object = new JSONObject();
			object.put("tareas", tareas);
			out.print(object);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute("error", mensage);
			return mapping.findForward("error");

		}
	}

	private File parent(File f) {
		String dirname = f.getParent();
		if (dirname == null) {
			return new File(File.separator);
		}
		return new File(dirname);
	}

	private boolean makeSureDirectoryExists(File dir) {
		if (!dir.exists()) {
			if (makeSureDirectoryExists(parent(dir)))
				dir.mkdir();
			else
				return false;
		}
		return true;
	}

	public ActionForward setUpLoad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, IOException {

		CalendarioForm soporteForm = (CalendarioForm) form;

		Enumeration e = null;
		FormFile file = null;
		Hashtable h = null;
		Vector vector = new Vector();
		CommonsMultipartRequestHandler cmrh = (CommonsMultipartRequestHandler) soporteForm
				.getMultipartRequestHandler();
		h = cmrh.getFileElements();
		try {
			e = h.keys();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				file = (FormFile) h.get(key);
				System.out.println("Field: " + key + ", FileName: " + file.getFileName() + ", size: " + file.getFileSize());
				Hashtable adjuntos = new Hashtable();
				adjuntos.put("nombre", file.getFileName());
				adjuntos.put("size", new Integer(file.getFileSize()));
				adjuntos.put("contentType", file.getContentType());
				adjuntos.put("fileData", file.getFileData());
				vector.add(adjuntos);
			}
			request.getSession().setAttribute("adjuntos", vector);

		} catch (NullPointerException ex) {
			System.out.println(ex.getStackTrace());
		}
		return null;
	}

	public ActionForward descarga(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		BDWFMotorConsulta oBDWFMotorConsulta;
		Vector adjuntos = new Vector();
		Hashtable hParameters = new Hashtable();

		try {
			String idDescarga = request.getParameter("wsdl");
			hParameters.put("idadjunto", idDescarga);
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			adjuntos = oBDWFMotorConsulta.getDescargarAdjunto(hParameters);

			for (int i = 0; i < adjuntos.size(); i++) {

				String nombre = adjuntos.get(i).toString().split("#")[0];
				String content = adjuntos.get(i).toString().split("#")[1];
				response.setContentType(content);
				response.setHeader("Content-Disposition", "attachment;filename=\"" + nombre + "\"");
				String ruta = "c:\\torotask\\" + idDescarga;
				FileInputStream in = new FileInputStream(new File(ruta));
				ServletOutputStream out = response.getOutputStream();
				byte[] outputByte = new byte[4096];
				while (in.read(outputByte, 0, 4096) != -1) {
					out.write(outputByte, 0, 4096);
				}
				in.close();
				out.flush();
				out.close();
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute("error", mensage);
			return mapping.findForward("error");

		}
		return null;
	}

	public ActionForward setDeleteUpLoad(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}
	
	private static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
}
