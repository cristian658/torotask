package com.gotoque.torotask.presentacion.struts.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.CommonsMultipartRequestHandler;
import org.apache.struts.upload.FormFile;

import com.gotoque.torotask.presentacion.delegate.BDWFMotorConsulta;
import com.gotoque.torotask.presentacion.struts.Utilidades.JSONArray;
import com.gotoque.torotask.presentacion.struts.Utilidades.JSONException;
import com.gotoque.torotask.presentacion.struts.Utilidades.JSONObject;
import com.gotoque.torotask.presentacion.struts.Utilidades.Utilidades;
import com.gotoque.torotask.presentacion.struts.form.CalendarioForm;

public class CalendarioAction extends DispatchAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		SimpleDateFormat dateFormat = new SimpleDateFormat(Messages.getString("CalendarioAction.1")); //$NON-NLS-1$
		String fechaHoy = dateFormat.format(new Date()).toString();
		
		request.setAttribute(Messages.getString("CalendarioAction.2"), fechaHoy); //$NON-NLS-1$
		request.setAttribute(Messages.getString("CalendarioAction.3"), fechaHoy + Messages.getString("CalendarioAction.4")); //$NON-NLS-1$ //$NON-NLS-2$
		request.setAttribute(Messages.getString("CalendarioAction.5"), fechaHoy + Messages.getString("CalendarioAction.6")); //$NON-NLS-1$ //$NON-NLS-2$

		BDWFMotorConsulta oBDWFMotorConsulta;
		Vector proyectos = new Vector();
		Hashtable hParameters = new Hashtable();
		try {

			oBDWFMotorConsulta = new BDWFMotorConsulta();
			proyectos = oBDWFMotorConsulta.getConsultaProyectos(hParameters);
			JSONArray object = new JSONArray(proyectos);
			request.setAttribute(Messages.getString("CalendarioAction.7"), object); //$NON-NLS-1$
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute(Messages.getString("CalendarioAction.8"), mensage); //$NON-NLS-1$
			return mapping.findForward(Messages.getString("CalendarioAction.9")); //$NON-NLS-1$
		}
		
		String inicioLaboral = (String)request.getSession().getAttribute("inicioLaboral");
		String terminoLaboral = (String)request.getSession().getAttribute("terminoLaboral");		
		request.setAttribute("fechaHoy", getFormatoFecha());
		request.setAttribute("inicioLaboral", getFormatoFecha()+ " " + inicioLaboral);
		request.setAttribute("terminoLaboral", getFormatoFecha()+ " " + terminoLaboral);
		return mapping.findForward(Messages.getString("CalendarioAction.10")); //$NON-NLS-1$
	}
	
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		this.unspecified(mapping, form, request, response);
		return mapping.findForward(Messages.getString("CalendarioAction.10")); //$NON-NLS-1$
	}

	private String getFormatoFecha() {
		SimpleDateFormat salida = new SimpleDateFormat("yyyy-MM-dd");
		String resultado = "";
		try {
			Date dia = new Date();
			long long1 = dia.getTime();
			Date date = new Date(long1);
			resultado = salida.format(date);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return resultado;
	}	
	
	public ActionForward getConsultarTarea(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BDWFMotorConsulta oBDWFMotorConsulta;
		Vector tareas = new Vector();
		Hashtable hParameters = new Hashtable();
		try {
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			JSONObject usuario = (JSONObject)request.getSession().getAttribute("usuario");
			JSONObject perfilVO = usuario.getJSONObject("perfilVO");
			String idUsuario = usuario.getString("idUsuario");
			String idPerfil = perfilVO.getString("idPerfil");
			hParameters.put("idUsuario",idUsuario);
			hParameters.put("idPerfil",idPerfil);
			tareas = oBDWFMotorConsulta.getConsultarTarea(hParameters);
			PrintWriter out = response.getWriter();
			JSONObject object = new JSONObject();
			object.put(Messages.getString("CalendarioAction.11"), tareas); //$NON-NLS-1$
			out.print(object);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute(Messages.getString("CalendarioAction.12"), mensage); //$NON-NLS-1$
			return mapping.findForward(Messages.getString("CalendarioAction.13")); //$NON-NLS-1$

		}
	}

	public ActionForward getConsultarIdTarea(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String idtarea = request.getParameter(Messages.getString("CalendarioAction.14")); //$NON-NLS-1$

		BDWFMotorConsulta oBDWFMotorConsulta;
		Vector tareas = new Vector();
		Hashtable hParameters = new Hashtable();
		hParameters.put(Messages.getString("CalendarioAction.15"), idtarea); //$NON-NLS-1$
		try {
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			tareas = oBDWFMotorConsulta.getConsultarIdTarea(hParameters);
			PrintWriter out = response.getWriter();
			JSONObject object = new JSONObject();
			object.put(Messages.getString("CalendarioAction.16"), tareas); //$NON-NLS-1$
			out.print(object);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute(Messages.getString("CalendarioAction.17"), mensage); //$NON-NLS-1$
			return mapping.findForward(Messages.getString("CalendarioAction.18")); //$NON-NLS-1$
		}
	}

	public ActionForward setActualizaTarea(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		BDWFMotorConsulta oBDWFMotorConsulta;
		Vector tareas = new Vector();
		Vector proyectos = new Vector();
		
		JSONObject usuario = (JSONObject)request.getSession().getAttribute("usuario");
		JSONObject perfilVO = usuario.getJSONObject("perfilVO");
		String idUsuario = usuario.getString("idUsuario");

		
		Hashtable hParameters = new Hashtable();
		try {
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			String idTarea = request.getParameter(Messages.getString("CalendarioAction.19")); //$NON-NLS-1$
			String idproyecto = request.getParameter(Messages.getString("CalendarioAction.20")); //$NON-NLS-1$
			String tarea = request.getParameter(Messages.getString("CalendarioAction.21")); //$NON-NLS-1$
			String estado = request.getParameter(Messages.getString("CalendarioAction.22")); //$NON-NLS-1$
			String descripcion = request.getParameter(Messages.getString("CalendarioAction.23")); //$NON-NLS-1$
			String comentario = request.getParameter(Messages.getString("CalendarioAction.24")); //$NON-NLS-1$
			String fechaInicio = request.getParameter(Messages.getString("CalendarioAction.25")); //$NON-NLS-1$
			String fechaTermino = request.getParameter(Messages.getString("CalendarioAction.26")); //$NON-NLS-1$
			String integrantes = request.getParameter(Messages.getString("CalendarioAction.27")); //$NON-NLS-1$

			hParameters.put(Messages.getString("CalendarioAction.28"), idTarea); //$NON-NLS-1$
			hParameters.put(Messages.getString("CalendarioAction.29"), tarea); //$NON-NLS-1$
			hParameters.put(Messages.getString("CalendarioAction.30"), estado); //$NON-NLS-1$
			hParameters.put(Messages.getString("CalendarioAction.31"), descripcion); //$NON-NLS-1$
			hParameters.put(Messages.getString("CalendarioAction.32"), fechaInicio); //$NON-NLS-1$
			hParameters.put(Messages.getString("CalendarioAction.33"), fechaTermino); //$NON-NLS-1$
			hParameters.put(Messages.getString("CalendarioAction.34"), fechaTermino); //$NON-NLS-1$
			hParameters.put(Messages.getString("CalendarioAction.35"), comentario); //$NON-NLS-1$
			hParameters.put(Messages.getString("CalendarioAction.36"), idUsuario); //$NON-NLS-1$
			hParameters.put(Messages.getString("CalendarioAction.37"), integrantes); //$NON-NLS-1$
			hParameters.put(Messages.getString("CalendarioAction.38"), idproyecto); //$NON-NLS-1$

			if (!isNumeric(idproyecto)) {
				proyectos = oBDWFMotorConsulta.setInsertaProyecto(hParameters);
				idproyecto = proyectos.get(0).toString();
				hParameters.put(Messages.getString("CalendarioAction.39"), idproyecto); //$NON-NLS-1$
			}

			if (idTarea.equals(Messages.getString("CalendarioAction.40"))) { //$NON-NLS-1$
				tareas = oBDWFMotorConsulta.setInsertaTarea(hParameters);
				Utilidades.setEnvioCorreoNuevo(idTarea);
				idTarea = tareas.get(0).toString();
				hParameters.put(Messages.getString("CalendarioAction.41"), idTarea); //$NON-NLS-1$
			} else {
				if (estado.equals(Messages.getString("CalendarioAction.42"))) { //$NON-NLS-1$
					tareas = oBDWFMotorConsulta.setActualizaTarea(hParameters);
					Utilidades.setEnvioCorreoActualizacion(idTarea);
				}else{
					tareas = oBDWFMotorConsulta.setEliminaTarea(hParameters);					
					Utilidades.setEnvioCorreoCierre(idTarea);
				}
			}
			if (!comentario.equals(Messages.getString("CalendarioAction.43"))) { //$NON-NLS-1$
				oBDWFMotorConsulta.setInsertaComentario(hParameters);
			}

			oBDWFMotorConsulta.setInsertaIntegrantexTarea(hParameters);

			Vector adjuntos = (Vector) request.getSession().getAttribute(Messages.getString("CalendarioAction.44")); //$NON-NLS-1$
			if (adjuntos != null) {
				for (int i = 0; i < adjuntos.size(); i++) {
					Hashtable archivo = (Hashtable) adjuntos.get(i);
					String nombre = (String) archivo.get(Messages.getString("CalendarioAction.45")); //$NON-NLS-1$
					Integer size = (Integer) archivo.get(Messages.getString("CalendarioAction.46")); //$NON-NLS-1$
					String contentType = (String) archivo.get(Messages.getString("CalendarioAction.47")); //$NON-NLS-1$
					byte[] fileData = (byte[]) archivo.get(Messages.getString("CalendarioAction.48")); //$NON-NLS-1$
					if (adjuntos != null) {
						try {
							hParameters.put(Messages.getString("CalendarioAction.49"), nombre); //$NON-NLS-1$
							hParameters.put(Messages.getString("CalendarioAction.50"), size); //$NON-NLS-1$
							hParameters.put(Messages.getString("CalendarioAction.51"), idTarea); //$NON-NLS-1$
							hParameters.put(Messages.getString("CalendarioAction.52"), idUsuario); //$NON-NLS-1$
							hParameters.put(Messages.getString("CalendarioAction.53"), contentType); //$NON-NLS-1$
							Vector data = oBDWFMotorConsulta.setInsertaAdjuntos(hParameters);
							String iddata = data.get(0).toString();
							String path = (String)request.getSession().getAttribute("rutaDescarga") + iddata; //$NON-NLS-1$
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
			request.getSession().setAttribute(Messages.getString("CalendarioAction.55"), null); //$NON-NLS-1$
			PrintWriter out = response.getWriter();
			JSONObject object = new JSONObject();
			object.put(Messages.getString("CalendarioAction.56"), tareas); //$NON-NLS-1$
			out.print(object);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute(Messages.getString("CalendarioAction.57"), mensage); //$NON-NLS-1$
			request.getSession().setAttribute(Messages.getString("CalendarioAction.58"), null); //$NON-NLS-1$
			return mapping.findForward(Messages.getString("CalendarioAction.59")); //$NON-NLS-1$
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
			request.setAttribute(Messages.getString("CalendarioAction.60"), mensage); //$NON-NLS-1$
			return mapping.findForward(Messages.getString("CalendarioAction.61")); //$NON-NLS-1$

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
			request.setAttribute(Messages.getString("CalendarioAction.62"), mensage); //$NON-NLS-1$
			return mapping.findForward(Messages.getString("CalendarioAction.63")); //$NON-NLS-1$

		}
	}

	public ActionForward getConsultaIntegrantes(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		JSONObject usuario = (JSONObject)request.getSession().getAttribute("usuario");
		if(usuario==null)
			return null;			
		String idJefe = "0";
		try {
			idJefe = usuario.getString("idUsuario");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String idTarea = request.getParameter("idtarea");
		BDWFMotorConsulta oBDWFMotorConsulta;
		Vector ingenieros = new Vector();
		Hashtable hParameters = new Hashtable();
		try {
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			hParameters.put("idJefe", idJefe);
			ingenieros = oBDWFMotorConsulta.getConsultaEquipo(hParameters);
			PrintWriter out = response.getWriter();
			JSONArray Array = new JSONArray(ingenieros);
			out.print(Array);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute(Messages.getString("CalendarioAction.64"), mensage); //$NON-NLS-1$
			return mapping.findForward(Messages.getString("CalendarioAction.65")); //$NON-NLS-1$

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
			request.setAttribute(Messages.getString("CalendarioAction.66"), mensage); //$NON-NLS-1$
			return mapping.findForward(Messages.getString("CalendarioAction.67")); //$NON-NLS-1$

		}
	}

	public ActionForward getConsultaIntegrantesxTarea(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BDWFMotorConsulta oBDWFMotorConsulta;
		Vector integrantes = new Vector();
		Hashtable hParameters = new Hashtable();
		String idtarea = request.getParameter(Messages.getString("CalendarioAction.68")); //$NON-NLS-1$
		try {
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			hParameters.put(Messages.getString("CalendarioAction.69"), idtarea); //$NON-NLS-1$
			integrantes = oBDWFMotorConsulta.getConsultaIntegrantesxTarea(hParameters);
			PrintWriter out = response.getWriter();
			JSONArray array = new JSONArray(integrantes);
			Vector vector = new Vector();
			for (int i = 0; i < array.length(); i++) {
				vector.add(array.getJSONObject(i).get(Messages.getString("CalendarioAction.70"))); //$NON-NLS-1$
			}
			out.print(vector);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute(Messages.getString("CalendarioAction.71"), mensage); //$NON-NLS-1$
			return mapping.findForward(Messages.getString("CalendarioAction.72")); //$NON-NLS-1$

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
			String idtarea = request.getParameter(Messages.getString("CalendarioAction.73")); //$NON-NLS-1$
			hParameters.put(Messages.getString("CalendarioAction.74"), idtarea); //$NON-NLS-1$
			adjuntos = oBDWFMotorConsulta.getConsultarAdjuntos(hParameters);
			PrintWriter out = response.getWriter();
			JSONArray object = new JSONArray(adjuntos);
			out.print(object);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute(Messages.getString("CalendarioAction.75"), mensage); //$NON-NLS-1$
			return mapping.findForward(Messages.getString("CalendarioAction.76")); //$NON-NLS-1$

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
			String idtarea = request.getParameter(Messages.getString("CalendarioAction.77")); //$NON-NLS-1$
			hParameters.put(Messages.getString("CalendarioAction.78"), idtarea); //$NON-NLS-1$
			comentarios = oBDWFMotorConsulta.getConsultarComentarios(hParameters);
			PrintWriter out = response.getWriter();
			JSONObject object = new JSONObject();
			object.put(Messages.getString("CalendarioAction.79"), comentarios); //$NON-NLS-1$
			object.put(Messages.getString("CalendarioAction.80"), comentarios.size()); //$NON-NLS-1$
			out.print(object);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute(Messages.getString("CalendarioAction.81"), mensage); //$NON-NLS-1$
			return mapping.findForward(Messages.getString("CalendarioAction.82")); //$NON-NLS-1$

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
			object.put(Messages.getString("CalendarioAction.83"), estados); //$NON-NLS-1$
			out.print(object);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute(Messages.getString("CalendarioAction.84"), mensage); //$NON-NLS-1$
			return mapping.findForward(Messages.getString("CalendarioAction.85")); //$NON-NLS-1$

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
			object.put(Messages.getString("CalendarioAction.86"), tareas); //$NON-NLS-1$
			out.print(object);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute(Messages.getString("CalendarioAction.87"), mensage); //$NON-NLS-1$
			return mapping.findForward(Messages.getString("CalendarioAction.88")); //$NON-NLS-1$

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

		CalendarioForm calendarioForm = (CalendarioForm) form;

		Enumeration e = null;
		FormFile file = null;
		Hashtable h = null;
		Vector vector = new Vector();
		CommonsMultipartRequestHandler cmrh = (CommonsMultipartRequestHandler) calendarioForm.getMultipartRequestHandler();
		h = cmrh.getFileElements();
		try {
			e = h.keys();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				file = (FormFile) h.get(key);
				System.out.println(Messages.getString("CalendarioAction.89") + key + Messages.getString("CalendarioAction.90") + file.getFileName() + Messages.getString("CalendarioAction.91") + file.getFileSize()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				Hashtable adjuntos = new Hashtable();
				adjuntos.put(Messages.getString("CalendarioAction.92"), file.getFileName()); //$NON-NLS-1$
				adjuntos.put(Messages.getString("CalendarioAction.93"), new Integer(file.getFileSize())); //$NON-NLS-1$
				adjuntos.put(Messages.getString("CalendarioAction.94"), file.getContentType()); //$NON-NLS-1$
				adjuntos.put(Messages.getString("CalendarioAction.95"), file.getFileData()); //$NON-NLS-1$
				vector.add(adjuntos);
			}
			request.getSession().setAttribute(Messages.getString("CalendarioAction.96"), vector); //$NON-NLS-1$

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
			String idDescarga = request.getParameter(Messages.getString("CalendarioAction.97")); //$NON-NLS-1$
			hParameters.put(Messages.getString("CalendarioAction.98"), idDescarga); //$NON-NLS-1$
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			adjuntos = oBDWFMotorConsulta.getDescargarAdjunto(hParameters);

			for (int i = 0; i < adjuntos.size(); i++) {

				String nombre = adjuntos.get(i).toString().split(Messages.getString("CalendarioAction.99"))[0]; //$NON-NLS-1$
				String content = adjuntos.get(i).toString().split(Messages.getString("CalendarioAction.100"))[1]; //$NON-NLS-1$
				response.setContentType(content);
				response.setHeader(Messages.getString("CalendarioAction.101"), Messages.getString("CalendarioAction.102") + nombre + Messages.getString("CalendarioAction.103")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				String ruta = (String)request.getSession().getAttribute("rutaDescarga") + idDescarga;
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
			request.setAttribute(Messages.getString("CalendarioAction.105"), mensage); //$NON-NLS-1$
			return mapping.findForward(Messages.getString("CalendarioAction.106")); //$NON-NLS-1$

		}
		return null;
	}

	public ActionForward setDeleteUpLoad(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

	private static boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

}
