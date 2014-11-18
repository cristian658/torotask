/*
 * Created on 31-05-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.gotoque.torotask.presentacion.delegate;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import com.gotoque.torotask.negocio.workflow.interfaces.WFMotorConsulta;
import com.gotoque.torotask.negocio.workflow.interfaces.WFMotorConsultaHome;

public class BDWFMotorConsulta {
	
	private WFMotorConsultaHome WFMotorConsultaHome;
	
	private final String JNDI_NAME = "ejb/WFToroTask";
	
	public BDWFMotorConsulta() throws Exception{
		Context context = null;
		try{ 
	    	Properties p = new Properties();
	    	p.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
	    	p.setProperty(Context.PROVIDER_URL,"localhost:1099");
	    	p.setProperty(Context.URL_PKG_PREFIXES,"org.jboss.naningrg.jnp.interfaces");
	    	context = new InitialContext(p);
	    	Object object = context.lookup(JNDI_NAME);
	    	WFMotorConsultaHome = (WFMotorConsultaHome)PortableRemoteObject.narrow(object, WFMotorConsultaHome.class);	    	
		}catch(Exception E){		
			E.printStackTrace(); 
		} 
	}
	
	private WFMotorConsulta crearEJB() throws Exception{
		WFMotorConsulta oWFMotorConsulta=null;		
		try{
			oWFMotorConsulta = WFMotorConsultaHome.create();
		}catch(Exception E){
			E.printStackTrace();
		}		
		return oWFMotorConsulta;
	}
	
	public Vector getConsultarTarea(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_TAREAS";
		try{
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		}catch(Exception E){
			E.printStackTrace();
		}			
		return Result;
	}

	public Vector setInsertaTarea(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_INSERTA_TAREAS";
		try{
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		}catch(Exception E){
			E.printStackTrace();
		}			
		return Result;
	}

	public Vector setActualizaTarea(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_ACTUALIZA_TAREAS";
		try{
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		}catch(Exception E){
			E.printStackTrace();
		}			
		return Result;
	}

	public Vector setEliminaTarea(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_ELIMINA_TAREAS";
		try{
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		}catch(Exception E){
			E.printStackTrace();
		}			
		return Result;
	}

	public Vector setInsertaIntegrantexTarea(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_INSERTAR_INTEGRANTES_X_TAREA";
		try{
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		}catch(Exception E){
			E.printStackTrace();
		}			
		return Result;	
	}

	public Vector getConsultarIngenieros(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_INGENIEROS";
		try{
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		}catch(Exception E){
			E.printStackTrace();
		}			
		return Result;	
	}

	public Vector getConsultarEstados(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_ESTADOS";
		try{
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		}catch(Exception E){
			E.printStackTrace();
		}			
		return Result;	
	}

	public Vector getConsultarComentarios(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_COMENTARIOS";
		try{
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		}catch(Exception E){
			E.printStackTrace();
		}			
		return Result;	
	}

	public Vector getConsultarAdjuntos(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_ADJUNTOS";
		try{
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		}catch(Exception E){
			E.printStackTrace();
		}			
		return Result;	
	}

	public Vector setInsertaComentario(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "SET_INSERTA_COMENTARIO";
		try{
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		}catch(Exception E){
			E.printStackTrace();
		}			
		return Result;	
	}

	public Vector setInsertaAdjuntos(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "SET_INSERTA_AJUNTO";
		try{
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		}catch(Exception E){
			E.printStackTrace();
		}			
		return Result;	
	}

	public Vector getDescargarAdjunto(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_DESCARGAR_AJUNTO";
		try{
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		}catch(Exception E){
			E.printStackTrace();
		}			
		return Result;	
	}

	public Vector getConsultaProyectos(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_PROYECTOS";
		try{
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		}catch(Exception E){
			E.printStackTrace();
		}			
		return Result;	
	}

	public Vector getConsultaIntegrantesxTarea(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_INTEGRANTES_X_TAREA";
		try{
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		}catch(Exception E){
			E.printStackTrace();
		}			
		return Result;	
	}

	public Vector getConsultaIntegrantes(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_INTEGRANTES";
		try{
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		}catch(Exception E){
			E.printStackTrace();
		}			
		return Result;	
	}

	public Vector getConsultarReporteTarea(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_REPORTE_TAREA";
		try{
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		}catch(Exception E){
			E.printStackTrace();
		}			
		return Result;	
	}

	public Vector setInsertaProyecto(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "SET_INSERTA_PROYECTO";
		try{
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		}catch(Exception E){
			E.printStackTrace();
		}			
		return Result;	
	}

	public Vector getConsultarGantt(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_GANTT";
		try{
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		}catch(Exception E){
			E.printStackTrace();
		}			
		return Result;	
	}

	public Vector getConsultarIdTarea(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_ID_TAREA";
		try{
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		}catch(Exception E){
			E.printStackTrace();
		}			
		return Result;	
	}

	public Vector setConsultaIdProyecto(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_ID_PROYECTO";
		try{
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		}catch(Exception E){
			E.printStackTrace();
		}			
		return Result;	
	}

	public Vector getConsultaRecursos(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_RECURSOS";
		try{
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		}catch(Exception E){
			E.printStackTrace();
		}			
		return Result;	
	}

	public Vector getConsultaParametros(Hashtable parameters) {
		Vector Result = new Vector();
		final String pNameService = "GET_CONSULTA_PARAMETROS";
		try{
			WFMotorConsulta oWFMotorConsulta = crearEJB();
			Result = oWFMotorConsulta.executeQuery(parameters, pNameService);
		}catch(Exception E){
			E.printStackTrace();
		}			
		return Result;	
	}

}
