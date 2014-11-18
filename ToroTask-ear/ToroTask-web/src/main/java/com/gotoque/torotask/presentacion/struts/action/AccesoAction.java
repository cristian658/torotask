package com.gotoque.torotask.presentacion.struts.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.gotoque.torotask.presentacion.struts.Utilidades.JSONArray;
import com.gotoque.torotask.presentacion.struts.Utilidades.JSONException;
import com.gotoque.torotask.presentacion.struts.Utilidades.JSONObject;
import com.gotoque.torotask.presentacion.struts.form.CalendarioForm;
import com.gotoque.torotask.vo.AdjuntoVO;

public class AccesoAction extends DispatchAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		BDWFMotorConsulta oBDWFMotorConsulta;
		Vector parametros = new Vector();
		Hashtable hParameters = new Hashtable();
		try {
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			parametros = oBDWFMotorConsulta.getConsultaParametros(hParameters);
			JSONArray object = new JSONArray(parametros);
			request.getSession().setAttribute("parametros",object);
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute("error", mensage);
			return mapping.findForward("error");

		}		
		return mapping.findForward("continuar");
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
			request.setAttribute("proyectos",object);
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
			
			String fechaInicio = dateFormat.format(sumarRestarDiasFecha(new Date(), -90)).toString();
			String fechaTermino = dateFormat.format(new Date()).toString();

			request.setAttribute("fechaInicio", fechaInicio);
			request.setAttribute("fechaTermino", fechaTermino);

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
		
		return mapping.findForward("reporte");
	}
	
	private Date sumarRestarDiasFecha(Date fecha, int dias){
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.DAY_OF_YEAR,dias);
		
		return calendar.getTime();
	}

}
