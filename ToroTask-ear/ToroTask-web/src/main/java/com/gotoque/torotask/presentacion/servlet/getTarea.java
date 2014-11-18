package com.gotoque.torotask.presentacion.servlet;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gotoque.torotask.presentacion.delegate.BDWFMotorConsulta;
import com.gotoque.torotask.presentacion.struts.Utilidades.JSONArray;

public class getTarea extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public getTarea() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			String online = request.getParameter("online");
			String idtarea = request.getParameter("id");
			Hashtable hParameters = new Hashtable();
			
			BDWFMotorConsulta oBDWFMotorConsulta;
			RequestDispatcher dispatcher = null;
			try {
				oBDWFMotorConsulta = new BDWFMotorConsulta();
				hParameters.put("idtarea", idtarea);
				Vector vectorTareas = (Vector)oBDWFMotorConsulta.getConsultarIdTarea(hParameters);
				Vector vectorComentarios = oBDWFMotorConsulta.getConsultarComentarios(hParameters);
				Vector vectorAdjuntos = oBDWFMotorConsulta.getConsultarAdjuntos(hParameters);
				Vector vectorIntegrantes = oBDWFMotorConsulta.getConsultaIntegrantesxTarea(hParameters);
				
				request.setAttribute("tarea", new JSONArray(vectorTareas).getJSONArray(0).getJSONObject(0));
				request.setAttribute("comentarios", new JSONArray(vectorComentarios));
				request.setAttribute("adjuntos", new JSONArray(vectorAdjuntos));
				request.setAttribute("integrantes", new JSONArray(vectorIntegrantes));
				if(online.equals("true")){
					dispatcher = getServletContext().getRequestDispatcher("/jsp/pdf/printTarea.jsp");				
				}else{
					dispatcher = getServletContext().getRequestDispatcher("/jsp/pdf/pdfTarea.jsp");				
				}
			} catch (Exception e) {
				e.printStackTrace();
				dispatcher = getServletContext().getRequestDispatcher("/jsp/error.jsp");				
			}

			dispatcher.forward(request, response);
			
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
	

}
