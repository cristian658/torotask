package com.gotoque.torotask.negocio;

import java.util.Date;

public class Utilidades {
	
	public static String minFecha(String inicio, String termino){
		
		if(termino == null){
			return inicio;
		}
		Date fecha1 = new Date(inicio);
		Date fecha2 = new Date(termino);
		if(fecha1.before(fecha2)){
			return inicio;
		}else{
			return termino;
		}
	}
	
	public static String maxFecha(String inicio, String termino){
		if(termino == null){
			return inicio;
		}
		Date fecha1 = new Date(inicio);
		Date fecha2 = new Date(termino);
		if(!fecha1.before(fecha2)){
			return inicio;
		}else{
			return termino;
		}
	}

}
