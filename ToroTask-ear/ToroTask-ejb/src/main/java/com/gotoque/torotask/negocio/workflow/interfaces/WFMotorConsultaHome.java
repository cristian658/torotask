/*
 * Generated by XDoclet - Do not edit!
 */
package com.gotoque.torotask.negocio.workflow.interfaces;

/**
 * Home interface for WFMotorConsulta.
 * 
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface WFMotorConsultaHome extends javax.ejb.EJBHome {
	public static final String COMP_NAME = "java:comp/env/ejb/WFToroTask";

	public static final String JNDI_NAME = "ejb/WFToroTask";

	public WFMotorConsulta create() throws javax.ejb.CreateException,
			java.rmi.RemoteException;

}
