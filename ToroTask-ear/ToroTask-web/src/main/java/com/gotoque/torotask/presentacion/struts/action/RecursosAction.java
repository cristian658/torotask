package com.gotoque.torotask.presentacion.struts.action;

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

public class RecursosAction extends DispatchAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		BDWFMotorConsulta oBDWFMotorConsulta;
		Vector tareas = new Vector();
		Vector proyectos = new Vector();
		Hashtable hParameters = new Hashtable();
		try {
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			tareas = oBDWFMotorConsulta.getConsultaRecursos(hParameters);
			JSONArray tarea = new JSONArray(tareas);
			proyectos = oBDWFMotorConsulta.getConsultaProyectos(hParameters);
			JSONArray proyecto = new JSONArray(proyectos);
			
			request.setAttribute("proyectos",proyecto );
			request.setAttribute("tareas",tarea);
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute("error", mensage);
			return mapping.findForward("error");

		}		
		return mapping.findForward("continuar");
	}
	
	

}
