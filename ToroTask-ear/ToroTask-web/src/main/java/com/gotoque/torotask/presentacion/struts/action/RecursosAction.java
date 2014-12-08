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
import com.gotoque.torotask.presentacion.struts.Utilidades.JSONException;
import com.gotoque.torotask.presentacion.struts.Utilidades.JSONObject;

public class RecursosAction extends DispatchAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Calendar actual = Calendar.getInstance();
		actual.setTime(new Date());

		
		Calendar anterior = Calendar.getInstance();
		anterior.setTime(new Date());
		anterior.add(Calendar.MONTH, -3);
		
		String inicio = getNombreMes(anterior.get(Calendar.MONTH)+1)+ " " + anterior.get(Calendar.YEAR);
		String hoy = getNombreMes(actual.get(Calendar.MONTH)+1)+ " " + actual.get(Calendar.YEAR);
		request.setAttribute("inicio",inicio);
		request.setAttribute("termino",hoy);
		
		return mapping.findForward("continuar");
	}
	
	public ActionForward getrRecursosDetalle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		BDWFMotorConsulta oBDWFMotorConsulta;
		Vector tareas = new Vector();
		Hashtable hParameters = new Hashtable();
		
		String idPerfil = "0";
		String idUsuario = "0";
		JSONObject usuario = (JSONObject)request.getSession().getAttribute("usuario");
		if(usuario==null){
			return null;
		}else{
			idUsuario = usuario.getString("idUsuario");
			idPerfil = usuario.getJSONObject("perfilVO").getString("idPerfil");
		}
		
		try {
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			String inicio = request.getParameter("inicio");
			String termino = request.getParameter("termino");
			
			String fechaInicioAno = inicio.split(" ")[1];
			String fechaInicioMes = inicio.split(" ")[0];
			String fechaTerminoAno = termino.split(" ")[1];
			String fechaTerminoMes = termino.split(" ")[0];
				
			hParameters.put("inicio", fechaInicioAno + getNumeroMes(fechaInicioMes) + "01");
			hParameters.put("termino", fechaInicioAno + getNumeroMes(fechaTerminoMes) + "01");
			hParameters.put("idPerfil",idPerfil);
			hParameters.put("idJefe",idUsuario);
			tareas = oBDWFMotorConsulta.getConsultaRecursos(hParameters);
			JSONArray tarea = new JSONArray(tareas);
			request.setAttribute("recursos", tarea);
		} catch (Exception e) {
			e.printStackTrace();
			String mensage = e.getMessage();
			request.setAttribute("error", mensage);
			return mapping.findForward("error");

		}
		return mapping.findForward("detalle");
	}
	
	public ActionForward getConsultarReporteUsuario(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {   

		BDWFMotorConsulta oBDWFMotorConsulta;
		Vector tareas = new Vector();
		Hashtable hParameters = new Hashtable();
		try {
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			String idUsuario = request.getParameter("idUsuario");
			String categoria = request.getParameter("categoria");
			String inicio = request.getParameter("inicio");
			String termino = request.getParameter("termino");
			
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
			hParameters.put("idUsuario", idUsuario);
			hParameters.put("categoria", categoria);
			tareas = oBDWFMotorConsulta.getConsultarReporteUsuario(hParameters);
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
	
	private String getNumeroMes(String mes){
		String info = "";
		String meses[] = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
		for (int i = 0; i < meses.length; i++) {
			if(mes.equals(meses[i])){
				info = (i + 1) + "";
			}
		}
		if(info.length()<2)
			info = "0" + info;	
		return info;
	}

	private String getNombreMes(int mes){
		String meses[] = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
		return meses[mes-1];
	}
	
}
