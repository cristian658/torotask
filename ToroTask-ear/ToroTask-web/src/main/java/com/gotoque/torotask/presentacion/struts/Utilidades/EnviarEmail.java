package com.gotoque.torotask.presentacion.struts.Utilidades;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviarEmail extends Thread {

	String para = "";
	String subject = "";
	String body = "";
	
	public EnviarEmail(String subject,String para,String body) {
		super(para);
		this.para = para;
		this.subject = subject;
		this.body = body;
	}

	public void run() {

		String subject = this.subject;
		String to = para;
		final String from = "torotask@gmail.com";
		final String pass = "torotask123456";

		try {
				Properties properties = new Properties();
				properties.put("mail.smtp.auth", "true");
				properties.put("mail.smtp.host", "smtp.gmail.com");
				properties.put("mail.smtp.port", "587");
				properties.put("mail.smtp.starttls.enable", "true");
				properties.put("mail.smtp.quitwait", "false");
				properties.put("mail.debug", "false");
				properties.put("mail.smtp.protocol", "smtpsasdasdasd");
				Session session = Session.getInstance(properties,new javax.mail.Authenticator(){
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(from, pass);
					}
				});
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(from));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
				message.setSubject(subject);
				message.setText(body);
				Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
