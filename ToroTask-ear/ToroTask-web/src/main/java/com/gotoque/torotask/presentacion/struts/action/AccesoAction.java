package com.gotoque.torotask.presentacion.struts.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.gotoque.torotask.presentacion.delegate.BDWFMotorConsulta;
import com.gotoque.torotask.presentacion.struts.Utilidades.JSONArray;
import com.gotoque.torotask.presentacion.struts.Utilidades.JSONObject;
import com.gotoque.torotask.presentacion.struts.Utilidades.Utilidades;
import com.gotoque.torotask.vo.UsuarioVO;

public class AccesoAction extends DispatchAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("mensaje", "");
		return mapping.findForward("index");

	}
	
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		BDWFMotorConsulta oBDWFMotorConsulta;
		Hashtable hParameters = new Hashtable();
		try {
			
			JSONArray object = null;
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			
			String correo = request.getParameter("correoTT");
			String clave = request.getParameter("claveTT");
			
			hParameters.put("correo", correo);
			hParameters.put("clave", clave);
			Vector usuario = oBDWFMotorConsulta.getConsultarAcceso(hParameters);
			
			if(usuario.size()>0){
				object = new JSONArray(usuario);
				JSONObject jsonUsuario = object.getJSONObject(0);
				request.getSession().setAttribute("usuario",jsonUsuario);
				hParameters.put("idJefe", jsonUsuario.getString("idUsuario"));
				Vector equipo = oBDWFMotorConsulta.getConsultaEquipo(hParameters);
				object = new JSONArray(equipo);
				request.getSession().setAttribute("equipo", object);
				
				Utilidades.getCargaParametrosSession(request);
				return mapping.findForward("continuar");
			}else{
				request.getSession().setAttribute("usuario",null);
				request.setAttribute("mensaje", "Usuario o clave incorrecto.");
				return mapping.findForward("index");
			}
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute("mensaje", "Error al validar el acceso");
			request.getSession().setAttribute("usuario",null);
			return mapping.findForward("error");

		}
	}
	
	public ActionForward setCerrarSession(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute("usuario",null);
		return mapping.findForward("index");	
	}
		
	public ActionForward calendario(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		BDWFMotorConsulta oBDWFMotorConsulta;
		Vector proyectos = new Vector();
		Hashtable hParameters = new Hashtable();

		try {
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			proyectos = oBDWFMotorConsulta.getConsultaProyectos(hParameters);
			JSONArray object = new JSONArray(proyectos);
			request.setAttribute("proyectos", object);
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute("error", mensage);
			return mapping.findForward("error");
		}
		return mapping.findForward("calendario");
	}

	public ActionForward reporte(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		BDWFMotorConsulta oBDWFMotorConsulta;
		Vector proyectos = new Vector();
		Hashtable hParameters = new Hashtable();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {

			String fechaInicio = dateFormat.format(
					sumarRestarDiasFecha(new Date(), -90)).toString();
			String fechaTermino = dateFormat.format(new Date()).toString();

			request.setAttribute("fechaInicio", fechaInicio);
			request.setAttribute("fechaTermino", fechaTermino);

			oBDWFMotorConsulta = new BDWFMotorConsulta();
			proyectos = oBDWFMotorConsulta.getConsultaProyectos(hParameters);
			JSONArray object = new JSONArray(proyectos);
			request.setAttribute("proyectos", object);
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute("error", mensage);
			return mapping.findForward("error");
		}

		return mapping.findForward("reporte");
	}

	private Date sumarRestarDiasFecha(Date fecha, int dias) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.DAY_OF_YEAR, dias);

		return calendar.getTime();
	}

	public ActionForward getMenu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		BDWFMotorConsulta oBDWFMotorConsulta;
		Vector equipo = new Vector();
		Hashtable hParameters = new Hashtable();

		try {
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			JSONObject usuario = (JSONObject)request.getSession().getAttribute("usuario");

			hParameters.put("idJefe", usuario.getString("idUsuario"));
			equipo = oBDWFMotorConsulta.getConsultaEquipo(hParameters);
			JSONArray object = new JSONArray(equipo);
			request.setAttribute("equipo", object);			
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute("error", mensage);
			return mapping.findForward("error");
		}
		return mapping.findForward("menu");
	}
	
}
