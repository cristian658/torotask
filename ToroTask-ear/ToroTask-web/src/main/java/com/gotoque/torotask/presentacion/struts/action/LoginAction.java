/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gotoque.torotask.presentacion.struts.action;

import com.gotoque.torotask.presentacion.delegate.BDWFMotorConsulta;
import com.gotoque.torotask.presentacion.struts.Utilidades.JSONArray;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author cristian
 */
public class LoginAction extends DispatchAction {
    
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		
		return mapping.findForward("continuar");
	}
    
}
