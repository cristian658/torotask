package com.gotoque.torotask.presentacion.struts.action;

import java.io.PrintWriter;
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

public class ReportesAction extends DispatchAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		BDWFMotorConsulta oBDWFMotorConsulta;
		Vector proyectos = new Vector();
		Hashtable hParameters = new Hashtable();

		try {

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String fechaInicio = dateFormat.format(sumarRestarDiasFecha(new Date(), -90)).toString();
			String fechaTermino = dateFormat.format(new Date()).toString();
			String tipo = request.getParameter("tipo");
			String id = request.getParameter("id");

			if (tipo == null)tipo = "";
			if (id == null)id = "";

			request.setAttribute("fechaInicio", fechaInicio);
			request.setAttribute("fechaTermino", fechaTermino);
			request.setAttribute("tipo", tipo);
			request.setAttribute("id", id);

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

		return mapping.findForward("continuar");
	}

	public ActionForward getConsultarReporteTarea(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {   

		BDWFMotorConsulta oBDWFMotorConsulta;
		Vector tareas = new Vector();
		Hashtable hParameters = new Hashtable();
		try {
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			String inicio = request.getParameter("inicio");
			String termino = request.getParameter("termino");
			String tipo = request.getParameter("tipo");
			String id = request.getParameter("id");

			if (tipo == null)tipo = "";
			if (id == null)id = "";

			if (inicio == null || termino == null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				inicio = dateFormat.format(sumarRestarDiasFecha(new Date(), -3)).toString();
				termino = dateFormat.format(new Date()).toString();
			} else {
				inicio = getFormatoFechaYYYYMMDD(inicio);
				termino = getFormatoFechaYYYYMMDD(termino);
			}
			hParameters.put("inicio", inicio);
			hParameters.put("termino", termino);
			hParameters.put("tipo", tipo);
			hParameters.put("id", id);
			tareas = oBDWFMotorConsulta.getConsultarReporteTarea(hParameters);
			PrintWriter out = response.getWriter();
			JSONObject object = new JSONObject();
			object.put("tareas", tareas);
			object.put("total", tareas.size());
			request.getSession().setAttribute("reporteTarea", object);
			out.print(object);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute("error", mensage);
			return mapping.findForward("error");
		}
	}
	
	
	private Date sumarRestarDiasFecha(Date fecha, int meses) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.MONTH, meses);

		return calendar.getTime();
	}

	private String getFormatoFechaYYYYMMDD(String fecha) {

		SimpleDateFormat entrada = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat salida = new SimpleDateFormat("yyyyMMdd");
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
