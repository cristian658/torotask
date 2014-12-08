package com.gotoque.torotask.presentacion.struts.action;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

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
import com.gotoque.torotask.presentacion.struts.Utilidades.JSONObject;
import com.gotoque.torotask.presentacion.struts.Utilidades.Utilidades;
import com.gotoque.torotask.presentacion.struts.form.MantenedorForm;

public class MantenedorAction extends DispatchAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		return mapping.findForward("continuar");
	}

	public ActionForward getConsultaUsuarios(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	
			BDWFMotorConsulta oBDWFMotorConsulta;
			Vector ingenieros = new Vector();
			Hashtable hParameters = new Hashtable();
			try {
				oBDWFMotorConsulta = new BDWFMotorConsulta();
				ingenieros = oBDWFMotorConsulta.getConsultarUsuarios(hParameters);
				PrintWriter out = response.getWriter();
				JSONObject object = new JSONObject();
				object.put("usuarios", ingenieros);
				object.put("total", ingenieros.size());
				out.print(object);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				String mensage = e.getMessage();
				request.setAttribute(Messages.getString("CalendarioAction.62"), mensage); //$NON-NLS-1$
				return null;
	
			}
	}
	
	public ActionForward getActualizaUsuarios(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	
				BDWFMotorConsulta oBDWFMotorConsulta;
				Vector ingenieros = new Vector();
				Hashtable hParameters = new Hashtable();
				MantenedorForm mantenedorForm = (MantenedorForm) form;

				try {
					String param = request.getParameter("models");
					JSONArray vParametros = new JSONArray(param);
					JSONObject parametros = vParametros.getJSONObject(0);
					String idUsuario = parametros.getString("idUsuario");
					String correo = parametros.getString("correo");
					String nombre = parametros.getString("nombre");
					String apellidoPaterno = parametros.getString("apellidoPaterno");
					String apellidoMaterno = parametros.getString("apellidoMaterno");
					String anexo = parametros.getString("anexo");
					String clave = parametros.getString("clave");
					
					JSONObject perfilVO = parametros.getJSONObject("perfilVO");
					String idPerfil = perfilVO.getInt("idPerfil")+"";
					
					JSONObject estadoVO = parametros.getJSONObject("estadoVO");
					String idEstado = estadoVO.getInt("idEstado")+"";

					hParameters.put("correo",correo);
					hParameters.put("nombre",nombre);
					hParameters.put("apellidoPaterno",apellidoPaterno);
					hParameters.put("apellidoMaterno",apellidoMaterno);
					hParameters.put("idPerfil",idPerfil);
					hParameters.put("anexo",anexo);
					hParameters.put("clave",clave);
					hParameters.put("idUsuario",idUsuario);
					hParameters.put("idEstado",idEstado);
					oBDWFMotorConsulta = new BDWFMotorConsulta();
					ingenieros = oBDWFMotorConsulta.getActualizaUsuarios(hParameters);
				
					Vector adjuntos = (Vector)request.getSession().getAttribute("imagenUsuario");
					if(adjuntos!=null){
						String idIngeniero = ingenieros.get(0).toString();
						Hashtable adjunto = (Hashtable)adjuntos.get(0);
						adjunto.put("idIngeniero", idIngeniero);
						setSubirImagen(adjunto);
					}
					PrintWriter out = response.getWriter();
					JSONArray array = new JSONArray(ingenieros);
					out.print(array);
					return null;
				} catch (Exception err) {
					String mensage = err.getMessage();
					request.setAttribute(Messages.getString("CalendarioAction.62"), mensage); //$NON-NLS-1$
					return null;
		
				}
	}
	
	private void setSubirImagen(Hashtable hParameters){
		BDWFMotorConsulta oBDWFMotorConsulta;
		try {
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			Vector ingenieros = oBDWFMotorConsulta.setSubirImagen(hParameters);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public ActionForward getEliminaUsuarios(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	
				BDWFMotorConsulta oBDWFMotorConsulta;
				Vector ingenieros = new Vector();
				Hashtable hParameters = new Hashtable();
				try {
					String param = request.getParameter("models");
					JSONArray vParametros = new JSONArray(param);
					JSONObject parametros = vParametros.getJSONObject(0);
					String idUsuario = parametros.getString("idUsuario");
					
					hParameters.put("idUsuario",idUsuario);
					oBDWFMotorConsulta = new BDWFMotorConsulta();
					ingenieros = oBDWFMotorConsulta.getEliminaUsuarios(hParameters);
					PrintWriter out = response.getWriter();
					JSONArray array = new JSONArray(ingenieros);
					out.print(array);
					return null;
				} catch (Exception e) {
					e.printStackTrace();
					String mensage = e.getMessage();
					request.setAttribute(Messages.getString("CalendarioAction.62"), mensage); //$NON-NLS-1$
					return null;
		
				}		
	}
	
	public ActionForward getCreaUsuarios(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	
			BDWFMotorConsulta oBDWFMotorConsulta;
			Vector ingenieros = new Vector();
			Hashtable hParameters = new Hashtable();
			try {
				String param = request.getParameter("models");
				JSONArray vParametros = new JSONArray(param);
				JSONObject parametros = vParametros.getJSONObject(0);
				String correo = parametros.getString("correo");
				String nombre = parametros.getString("nombre");
				String apellidoPaterno = parametros.getString("apellidoPaterno");
				String apellidoMaterno = parametros.getString("apellidoMaterno");
				JSONObject perfilVO = parametros.getJSONObject("perfilVO");
				String idPerfil = perfilVO.getInt("idPerfil")+"";
				JSONObject estadoVO = parametros.getJSONObject("estadoVO");
				String idEstado = estadoVO.getInt("idEstado")+"";
				String anexo = parametros.getString("anexo");
				String clave = parametros.getString("clave");
				
				hParameters.put("correo",correo);
				hParameters.put("nombre",nombre);
				hParameters.put("apellidoPaterno",apellidoPaterno);
				hParameters.put("apellidoMaterno",apellidoMaterno);
				hParameters.put("idPerfil",idPerfil);
				hParameters.put("anexo",anexo);
				hParameters.put("clave",clave);
				hParameters.put("idEstado",idEstado);
				
				oBDWFMotorConsulta = new BDWFMotorConsulta();
				ingenieros = oBDWFMotorConsulta.getCreaUsuarios(hParameters);
				
				Vector adjuntos = (Vector)request.getSession().getAttribute("imagenUsuario");
				if(adjuntos!=null){
					String idUsuario = ingenieros.get(0).toString();
					Hashtable adjunto = (Hashtable)adjuntos.get(0);
					adjunto.put("idUsuario", idUsuario);
					setSubirImagen(adjunto);
				}
				
				
				PrintWriter out = response.getWriter();
				JSONArray array = new JSONArray(ingenieros);
				out.print(array);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				String mensage = e.getMessage();
				request.setAttribute(Messages.getString("CalendarioAction.62"), mensage); //$NON-NLS-1$
				return null;
	
			}
	}

	public ActionForward getConsultaCargos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	
			BDWFMotorConsulta oBDWFMotorConsulta;
			Vector ingenieros = new Vector();
			Hashtable hParameters = new Hashtable();
			try {
				oBDWFMotorConsulta = new BDWFMotorConsulta();
				ingenieros = oBDWFMotorConsulta.getConsultaCargos(hParameters);
				PrintWriter out = response.getWriter();
				JSONArray array = new JSONArray(ingenieros);
				out.print(array);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				String mensage = e.getMessage();
				request.setAttribute(Messages.getString("CalendarioAction.62"), mensage); //$NON-NLS-1$
				return null;
	
			}
	}
	
	public ActionForward getConsultaEstados(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	
			BDWFMotorConsulta oBDWFMotorConsulta;
			Vector ingenieros = new Vector();
			Hashtable hParameters = new Hashtable();
			try {
				oBDWFMotorConsulta = new BDWFMotorConsulta();
				ingenieros = oBDWFMotorConsulta.getConsultaEstados(hParameters);
				PrintWriter out = response.getWriter();
				JSONArray array = new JSONArray(ingenieros);
				out.print(array);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				String mensage = e.getMessage();
				request.setAttribute(Messages.getString("CalendarioAction.62"), mensage); //$NON-NLS-1$
				return null;
	
			}
	}
	
	public ActionForward getConsultaCargoUsuarios(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	
			BDWFMotorConsulta oBDWFMotorConsulta;
			Vector ingenieros = new Vector();
			Hashtable hParameters = new Hashtable();
			try {
				String idPerfil = request.getParameter("idPerfil");
				oBDWFMotorConsulta = new BDWFMotorConsulta();
				hParameters.put("idPerfil",idPerfil);
				ingenieros = oBDWFMotorConsulta.getConsultaCargoUsuarios(hParameters);
				PrintWriter out = response.getWriter();
				JSONArray array = new JSONArray(ingenieros);
				out.print(array);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				String mensage = e.getMessage();
				request.setAttribute(Messages.getString("CalendarioAction.62"), mensage); //$NON-NLS-1$
				return null;
	
			}
	}	
	
	public ActionForward setActualizaEquipo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	
			BDWFMotorConsulta oBDWFMotorConsulta;
			Vector ingenieros = new Vector();
			Hashtable hParameters = new Hashtable();
			try {
				String jefe = request.getParameter("jefe");
				String analistas = request.getParameter("analistas");
				oBDWFMotorConsulta = new BDWFMotorConsulta();
				hParameters.put("jefe",jefe);
				hParameters.put("analistas",analistas);
				ingenieros = oBDWFMotorConsulta.setActualizaEquipo(hParameters);
				PrintWriter out = response.getWriter();
				JSONArray array = new JSONArray(ingenieros);
				out.print(array);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				String mensage = e.getMessage();
				request.setAttribute(Messages.getString("CalendarioAction.62"), mensage); //$NON-NLS-1$
				return null;
	
			}
	}	
	
	public ActionForward setEliminaEquipo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	
			BDWFMotorConsulta oBDWFMotorConsulta;
			Vector ingenieros = new Vector();
			Hashtable hParameters = new Hashtable();
			try {
				String jefe = request.getParameter("jefe");
				String analistas = request.getParameter("analistas");
				oBDWFMotorConsulta = new BDWFMotorConsulta();
				hParameters.put("jefe",jefe);
				hParameters.put("analistas",analistas);
				ingenieros = oBDWFMotorConsulta.setEliminaEquipo(hParameters);
				PrintWriter out = response.getWriter();
				JSONArray array = new JSONArray(ingenieros);
				out.print(array);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				String mensage = e.getMessage();
				request.setAttribute(Messages.getString("CalendarioAction.62"), mensage); //$NON-NLS-1$
				return null;
	
			}
	}	
	
	public ActionForward getConsultaEquipo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	
			BDWFMotorConsulta oBDWFMotorConsulta;
			Vector integrantes = new Vector();
			Hashtable hParameters = new Hashtable();
			try {
				String idJefe = request.getParameter("idJefe");
				oBDWFMotorConsulta = new BDWFMotorConsulta();
				hParameters.put("idJefe",idJefe);
				integrantes = oBDWFMotorConsulta.getConsultaEquipo(hParameters);
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
				request.setAttribute(Messages.getString("CalendarioAction.62"), mensage); //$NON-NLS-1$
				return null;
	
			}
	}	
	
	public ActionForward getConsultaFeriados(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	
			BDWFMotorConsulta oBDWFMotorConsulta;
			Vector feriados = new Vector();
			Hashtable hParameters = new Hashtable();
			try {
				oBDWFMotorConsulta = new BDWFMotorConsulta();
				feriados = oBDWFMotorConsulta.getConsultaFeriados(hParameters);
				PrintWriter out = response.getWriter();
				JSONObject object = new JSONObject();
				object.put("feriados", feriados);
				object.put("total", feriados.size());
				out.print(object);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				String mensage = e.getMessage();
				request.setAttribute(Messages.getString("CalendarioAction.62"), mensage); //$NON-NLS-1$
				return null;
	
			}
	}
	
	public ActionForward getActualizaFeriados(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	
			BDWFMotorConsulta oBDWFMotorConsulta;
			Vector feriados = new Vector();
			Hashtable hParameters = new Hashtable();
			try {
				String param = request.getParameter("models");
				JSONArray vParametros = new JSONArray(param);
				JSONObject parametros = vParametros.getJSONObject(0);
				String fecha = parametros.getString("fecha");
				String motivo = parametros.getString("motivo");
				String idFeriado = parametros.getString("idCalendario");
				JSONObject estadoVO = parametros.getJSONObject("estadosVO");
				String idEstado = estadoVO.getInt("idEstado")+"";
				hParameters.put("fecha",fecha.substring(0,10));
				hParameters.put("motivo",motivo);
				hParameters.put("idEstado",idEstado);
				hParameters.put("idFeriado",idFeriado);
				oBDWFMotorConsulta = new BDWFMotorConsulta();
				feriados = oBDWFMotorConsulta.getActualizaFeriados(hParameters);
				PrintWriter out = response.getWriter();
				JSONObject object = new JSONObject();
				object.put("feriados", feriados);
				object.put("total", feriados.size());
				out.print(object);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				String mensage = e.getMessage();
				request.setAttribute(Messages.getString("CalendarioAction.62"), mensage); //$NON-NLS-1$
				return null;
	
			}
	}
	
	public ActionForward getCreaFeriados(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	
			BDWFMotorConsulta oBDWFMotorConsulta;
			Vector feriados = new Vector();
			Hashtable hParameters = new Hashtable();
			try {
				oBDWFMotorConsulta = new BDWFMotorConsulta();
				String param = request.getParameter("models");
				JSONArray vParametros = new JSONArray(param);
				JSONObject parametros = vParametros.getJSONObject(0);
				String fecha = parametros.getString("fecha");
				String motivo = parametros.getString("motivo");
				JSONObject estadoVO = parametros.getJSONObject("estadosVO");
				String idEstado = estadoVO.getInt("idEstado")+"";
				hParameters.put("fecha",fecha.substring(0,10));
				hParameters.put("motivo",motivo);
				hParameters.put("idEstado",idEstado);
				feriados = oBDWFMotorConsulta.getCreaFeriados(hParameters);
				PrintWriter out = response.getWriter();
				JSONObject object = new JSONObject();
				object.put("feriados", feriados);
				object.put("total", feriados.size());
				out.print(object);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				String mensage = e.getMessage();
				request.setAttribute(Messages.getString("CalendarioAction.62"), mensage); //$NON-NLS-1$
				return null;
	
			}
	}
	
	
	public ActionForward getConsultaCorreo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	
			BDWFMotorConsulta oBDWFMotorConsulta;
			Vector usuario = new Vector();
			Hashtable hParameters = new Hashtable();
			try {
				oBDWFMotorConsulta = new BDWFMotorConsulta();
				String id = request.getParameter("id");
				String correo = request.getParameter("correo");
				hParameters.put("id",id);
				hParameters.put("correo",correo);
				usuario = oBDWFMotorConsulta.getConsultaCorreo(hParameters);
				PrintWriter out = response.getWriter();
				JSONArray usuarios = new JSONArray(usuario);
				if(usuario.size()==0){
					out.print("true");					
				}else{
					out.print("false");					
				}
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				String mensage = e.getMessage();
				request.setAttribute(Messages.getString("CalendarioAction.62"), mensage); //$NON-NLS-1$
				return null;
	
			}
	}

	public ActionForward getConsultaParametros(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	
			BDWFMotorConsulta oBDWFMotorConsulta;
			Vector feriados = new Vector();
			Hashtable hParameters = new Hashtable();
			try {
				oBDWFMotorConsulta = new BDWFMotorConsulta();
				feriados = oBDWFMotorConsulta.getConsultaParametros(hParameters);
				PrintWriter out = response.getWriter();
				JSONObject object = new JSONObject();
				object.put("parametros", feriados);
				object.put("total", feriados.size());
				out.print(object);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				String mensage = e.getMessage();
				request.setAttribute(Messages.getString("CalendarioAction.62"), mensage); //$NON-NLS-1$
				return null;
	
			}
	}
	
	public ActionForward getConsultaHorarios(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	
			BDWFMotorConsulta oBDWFMotorConsulta;
			Vector horarios = new Vector();
			Hashtable hParameters = new Hashtable();
			try {
				oBDWFMotorConsulta = new BDWFMotorConsulta();
				horarios = oBDWFMotorConsulta.getConsultaHorarios(hParameters);
				PrintWriter out = response.getWriter();
				JSONObject object = new JSONObject();
				object.put("horarios", horarios);
				object.put("total", horarios.size());
				out.print(object);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				String mensage = e.getMessage();
				request.setAttribute(Messages.getString("CalendarioAction.62"), mensage); //$NON-NLS-1$
				return null;
	
			}
	}
	
	public ActionForward getActualizaHorarios(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	
			BDWFMotorConsulta oBDWFMotorConsulta;
			Vector horarios = new Vector();
			Hashtable hParameters = new Hashtable();
			try {
				oBDWFMotorConsulta = new BDWFMotorConsulta();
				horarios = oBDWFMotorConsulta.getConsultaHorarios(hParameters);
				PrintWriter out = response.getWriter();
				JSONObject object = new JSONObject();
				object.put("horarios", horarios);
				object.put("total", horarios.size());
				out.print(object);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				String mensage = e.getMessage();
				request.setAttribute(Messages.getString("CalendarioAction.62"), mensage); //$NON-NLS-1$
				return null;
	
			}
	}	
	
	public ActionForward getActualizaParametros(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	
			BDWFMotorConsulta oBDWFMotorConsulta;
			Vector feriados = new Vector();
			Hashtable hParameters = new Hashtable();
			try {
				String param = request.getParameter("models");
				JSONArray vParametros = new JSONArray(param);
				JSONObject parametros = vParametros.getJSONObject(0);
				String idParametro = parametros.getString("idParametro");
				String parametro = parametros.getString("parametro");
				String glosa = parametros.getString("glosa");
				hParameters.put("idParametro",idParametro);
				hParameters.put("parametro",parametro);
				hParameters.put("glosa",glosa);
				oBDWFMotorConsulta = new BDWFMotorConsulta();
				feriados = oBDWFMotorConsulta.getActualizaParametros(hParameters);
				PrintWriter out = response.getWriter();
				JSONObject object = new JSONObject();
				object.put("parametros", feriados);
				object.put("total", feriados.size());
				Utilidades.getCargaParametrosSession(request);
				out.print(object);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				String mensage = e.getMessage();
				request.setAttribute(Messages.getString("CalendarioAction.62"), mensage); //$NON-NLS-1$
				return null;
	
			}
	}
	
	public ActionForward getCreaParametros(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	
			BDWFMotorConsulta oBDWFMotorConsulta;
			Vector feriados = new Vector();
			Hashtable hParameters = new Hashtable();
			try {
				oBDWFMotorConsulta = new BDWFMotorConsulta();
				String param = request.getParameter("models");
				JSONArray vParametros = new JSONArray(param);
				JSONObject parametros = vParametros.getJSONObject(0);
				String parametro = parametros.getString("parametro");
				String glosa = parametros.getString("glosa");
				hParameters.put("parametro",parametro);
				hParameters.put("glosa",glosa);
				feriados = oBDWFMotorConsulta.getCreaParametros(hParameters);
				PrintWriter out = response.getWriter();
				JSONObject object = new JSONObject();
				object.put("parametros", feriados);
				object.put("total", feriados.size());
				Utilidades.getCargaParametrosSession(request);
				out.print(object);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				String mensage = e.getMessage();
				request.setAttribute(Messages.getString("CalendarioAction.62"), mensage); //$NON-NLS-1$
				return null;
	
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

		MantenedorForm mantenedorForm = (MantenedorForm) form;

		Enumeration e = null;
		FormFile file = null;
		Hashtable h = null;
		Vector vector = new Vector();
		CommonsMultipartRequestHandler cmrh = (CommonsMultipartRequestHandler) mantenedorForm.getMultipartRequestHandler();
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
			request.getSession().setAttribute("imagenUsuario", vector); //$NON-NLS-1$
		} catch (NullPointerException ex) {
			System.out.println(ex.getStackTrace());
		}
		return null;
	}


/*
 	      String encodedBytes = encoder.encodeBuffer("JavaTips.net".getBytes());
	      System.out.println("encodedBytes " + encodedBytes);
	      byte[] decodedBytes = decoder.decodeBuffer(encodedBytes);
	      System.out.println("decodedBytes " + new String(decodedBytes));
*/

}
