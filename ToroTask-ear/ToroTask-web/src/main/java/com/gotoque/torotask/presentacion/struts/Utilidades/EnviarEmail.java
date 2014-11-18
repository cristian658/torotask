package com.gotoque.torotask.presentacion.struts.Utilidades;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.gotoque.torotask.presentacion.delegate.BDWFMotorConsulta;
import com.gotoque.torotask.vo.TareaVO;
import com.gotoque.torotask.vo.UsuarioVO;

/**
 *
 * @author cristian
 */
public class EnviarEmail extends Thread{

    String idTarea = "";

	public EnviarEmail(String name) {
		super(name);
		this.idTarea = name;
		// TODO Auto-generated constructor stub
	}

	public void run() {
    	
        String subject = "Nueva tarea asignada";
        String to = "";
        final String from = "torotask@gmail.com";
        final String pass = "torotask123456";
        
    	try {
    		
            Properties properties = new Properties();
            Hashtable hParameters = new Hashtable();
            hParameters.put("idtarea",idTarea);
            Vector vTarea = (Vector)new BDWFMotorConsulta().getConsultarIdTarea(hParameters);
            
            JSONObject tareaVO = new JSONArray(vTarea).getJSONArray(0).getJSONObject(0);
            JSONObject usuarioVO = tareaVO.getJSONObject("usuarioVO");
            JSONArray integrantes = tareaVO.getJSONArray("integrantes");
            	
            	for (int i = 0; i < integrantes.length() ; i++) {
            		JSONObject integrantesVO = (JSONObject)integrantes.getJSONObject(i);
	                
            		
            		to = integrantesVO.getString("correo");
            		
            		String body = "Estimado "+ integrantesVO.getString("nombre") +"\n\n"
	                + " Tiene una nueva tarea asignada por: " + usuarioVO.getString("nombre") + " " + usuarioVO.getString("apellidoPaterno") + ", con fecha de inicio " + tareaVO.getString("fecha_inicio") + " \n"
	                + " * Para mas informacion favor ingrese al sistema. \n\n"
	                + " Saludos.\n\n"
	                + " Toro-Task \n"
	                + " The Evolution of Software\n\n"
	                + "* Favor no contestar este correo";
	
		            properties.put("mail.smtp.auth", "true");
		            properties.put("mail.smtp.host", "smtp.gmail.com");
		            properties.put("mail.smtp.port", "587");
		            properties.put("mail.smtp.starttls.enable", "true");
		            properties.put("mail.smtp.quitwait", "false");
		            properties.put("mail.debug", "false");
		            properties.put("mail.smtp.protocol", "smtp");
		            Session session = Session.getInstance(properties, 
		                    new javax.mail.Authenticator() {
		                        protected PasswordAuthentication
		                        getPasswordAuthentication() {
		                            return new PasswordAuthentication(from, pass);
		                        }
		                    });
		            Message message = new MimeMessage(session);
		            message.setFrom(new InternetAddress(from));
		            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		            message.setSubject(subject);
		            message.setText(body);
		            Transport.send(message);
		            System.out.println("Correo enviado : idtarea " + tareaVO.getString("idtarea"));
				
            	}
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
    

}
