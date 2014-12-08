package com.gotoque.torotask.presentacion.struts.Utilidades;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.gotoque.torotask.presentacion.delegate.BDWFMotorConsulta;
import com.gotoque.torotask.presentacion.struts.action.Messages;
import com.gotoque.torotask.vo.TareaVO;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class Utilidades {
	

	
	public static String getHorasLaborales(String fechaInicio,
			String fechaTermino) {

		Calendar fechaInicial = new GregorianCalendar();
		Calendar fechaFinal = new GregorianCalendar();

		fechaInicial.setTime(getFormatoHorasLaborales(fechaInicio));
		fechaFinal.setTime(getFormatoHorasLaborales(fechaTermino));

		int horas = 0;
		while (fechaInicial.before(fechaFinal)
				|| fechaInicial.equals(fechaFinal)) {
			if (fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
					&& fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
				horas++;
			}
			fechaInicial.add(Calendar.HOUR, 1);
		}
		return horas + "";
	}

	private static Date getFormatoHorasLaborales(String fecha) {

		SimpleDateFormat entrada = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date resultado = null;
		try {
			long long1 = entrada.parse(fecha).getTime();
			resultado = new Date(long1);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return resultado;
	}
	
	public static void getCargaParametrosSession(HttpServletRequest request) {
		BDWFMotorConsulta oBDWFMotorConsulta;
		Hashtable hParameters = new Hashtable();
		try {
			JSONArray object = null;
			oBDWFMotorConsulta = new BDWFMotorConsulta();
			Vector parametros = oBDWFMotorConsulta.getConsultaParametros(hParameters);
			object = new JSONArray(parametros);
			for (int i = 0; i < object.length(); i++) {
				JSONObject parametro = object.getJSONObject(i);
				if(parametro.getString("parametro").equals("rutaDescarga")){
					request.getSession().setAttribute("rutaDescarga", parametro.getString("glosa"));
				}else if(parametro.getString("parametro").equals("rutaFotos")){
					request.getSession().setAttribute("rutaFotos", parametro.getString("glosa"));
				}else if(parametro.getString("parametro").equals("sizeAdjunto")){
					request.getSession().setAttribute("sizeAdjunto", parametro.getString("glosa"));
				}else if(parametro.getString("parametro").equals("inicioLaboral")){
					request.getSession().setAttribute("inicioLaboral", parametro.getString("glosa"));
				}else if(parametro.getString("parametro").equals("terminoLaboral")){
					request.getSession().setAttribute("terminoLaboral", parametro.getString("glosa"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public static void setEnvioCorreoActualizacion(String idTarea) {
		
		Hashtable hParameters = new Hashtable();
		hParameters.put("idtarea", idTarea);
		Vector vTarea;
		try {
			vTarea = (Vector) new BDWFMotorConsulta().getConsultarIdTarea(hParameters);
			JSONObject tareaVO = new JSONArray(vTarea).getJSONArray(0).getJSONObject(0);
			String asunto = "Actualizacion de Tarea " + tareaVO.getJSONObject("tarea");
			JSONObject usuarioVO = tareaVO.getJSONObject("usuarioVO");
			JSONArray integrantes = tareaVO.getJSONArray("integrantes");
			for (int i = 0; i < integrantes.length(); i++) {
				JSONObject integrantesVO = (JSONObject) integrantes.getJSONObject(i);
				String para = integrantesVO.getString("correo");
				String body = "Estimado. \n"
						+ integrantesVO.getString("nombre")+ "\n\n"
						+ " La tarea asignada por: " + usuarioVO.getString("nombre") + " "+ usuarioVO.getString("apellidoPaterno")
						+ ", con fecha de inicio " + tareaVO.getString("fecha_inicio")+ " \n" 
						+ " a sido modificada."
						+ " * Para mas informacion favor ingrese al sistema. \n\n"
						+ " Saludos.\n\n" 
						+ " Toro-Task \n"
						+ " The Evolution of Software\n\n"
						+ "* Favor no contestar este correo";
				EnviarEmail enviarEmail = new EnviarEmail(asunto,para,body);
				enviarEmail.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public static void setEnvioCorreoNuevo(String idTarea) {
		Hashtable hParameters = new Hashtable();
		hParameters.put("idtarea", idTarea);
		Vector vTarea;
		try {
			vTarea = (Vector) new BDWFMotorConsulta().getConsultarIdTarea(hParameters);
			JSONObject tareaVO = new JSONArray(vTarea).getJSONArray(0).getJSONObject(0);
			String asunto = "Asignacion nueva tarea " + tareaVO.getJSONObject("tarea");
			JSONObject usuarioVO = tareaVO.getJSONObject("usuarioVO");
			JSONArray integrantes = tareaVO.getJSONArray("integrantes");
			for (int i = 0; i < integrantes.length(); i++) {
				JSONObject integrantesVO = (JSONObject) integrantes.getJSONObject(i);
				String para = integrantesVO.getString("correo");
				String body = "Estimado "
						+ integrantesVO.getString("nombre")+ "\n\n"
						+ " Tiene una nueva tarea asignada por: " + usuarioVO.getString("nombre") + " "+ usuarioVO.getString("apellidoPaterno")
						+ ", con fecha de inicio " + tareaVO.getString("fecha_inicio")+ " \n"
						+ " * Para mas informacion favor ingrese al sistema. \n\n"
						+ " Saludos.\n\n" 
						+ " Toro-Task \n"
						+ " The Evolution of Software\n\n"
						+ "* Favor no contestar este correo";
				EnviarEmail enviarEmail = new EnviarEmail(asunto,para,body);
				enviarEmail.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public static void setEnvioCorreoCierre(String idTarea) {
		Hashtable hParameters = new Hashtable();
		hParameters.put("idtarea", idTarea);
		Vector vTarea;
		try {
			vTarea = (Vector) new BDWFMotorConsulta().getConsultarIdTarea(hParameters);
			JSONObject tareaVO = new JSONArray(vTarea).getJSONArray(0).getJSONObject(0);
			String asunto = "Cierre de Tarea " + tareaVO.getJSONObject("tarea");
			JSONObject usuarioVO = tareaVO.getJSONObject("usuarioVO");
			JSONArray integrantes = tareaVO.getJSONArray("integrantes");
			for (int i = 0; i < integrantes.length(); i++) {
				JSONObject integrantesVO = (JSONObject) integrantes.getJSONObject(i);
				String para = integrantesVO.getString("correo");
				String body = "Estimado "
						+ integrantesVO.getString("nombre")+ "\n\n"
						+ " La tarea asignada por: " + usuarioVO.getString("nombre") + " "+ usuarioVO.getString("apellidoPaterno")
						+ ", con fecha de inicio " + tareaVO.getString("fecha_inicio")+ " \n" 
						+ " a sido cerrada."
						+ " * Para mas informacion favor ingrese al sistema. \n\n"
						+ " Saludos.\n\n" + " Toro-Task \n"
						+ " The Evolution of Software\n\n"
						+ "* Favor no contestar este correo";
				EnviarEmail enviarEmail = new EnviarEmail(asunto,para,body);
				enviarEmail.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setEnvioCorreoUsuario(String idUsuario) {
		Hashtable hParameters = new Hashtable();
		hParameters.put("idusuario", idUsuario);
		Vector vUsuario;
		try {
			vUsuario = (Vector) new BDWFMotorConsulta().getConsultarIdUsuario(hParameters);
			JSONObject usuarioVO = new JSONArray(vUsuario).getJSONArray(0).getJSONObject(0);
			String asunto = "Creacion de cuenta en Toro-Task" + usuarioVO.getJSONObject("correo");
				String para = usuarioVO.getString("correo");
				String body = "Estimado " + usuarioVO.getString("nombre") + " "+ usuarioVO.getString("apellidoPaterno")
						+ " Se ha creado una cuenta asociado a su correo : "+ para +" \n" 
						+ " La clave de acceso es : " + usuarioVO.getString("clave") +"\n\n"
						+ " Saludos.\n\n"
						+ " Toro-Task \n"
						+ " The Evolution of Software\n\n"
						+ "* Favor no contestar este correo";
				EnviarEmail enviarEmail = new EnviarEmail(asunto,para,body);
				enviarEmail.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setEnvioCorreonuevaClave(String idUsuario) {
		Hashtable hParameters = new Hashtable();
		hParameters.put("idusuario", idUsuario);
		Vector vUsuario;
		try {
			vUsuario = (Vector) new BDWFMotorConsulta().getConsultarIdUsuario(hParameters);
			JSONObject usuarioVO = new JSONArray(vUsuario).getJSONArray(0).getJSONObject(0);
			String asunto = "Creacion de cuenta " + usuarioVO.getJSONObject("correo");
				String para = usuarioVO.getString("correo");
				String body = "Estimado " + usuarioVO.getString("nombre") + " "+ usuarioVO.getString("apellidoPaterno")
						+ " Se ha creado una cuenta asociado a su correo : "+ para +" \n" 
						+ " La clave de acceso es : " + usuarioVO.getString("clave") +"\n\n"
						+ " Saludos.\n\n"
						+ " Toro-Task \n"
						+ " The Evolution of Software\n\n"
						+ "* Favor no contestar este correo";
				EnviarEmail enviarEmail = new EnviarEmail(asunto,para,body);
				enviarEmail.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
