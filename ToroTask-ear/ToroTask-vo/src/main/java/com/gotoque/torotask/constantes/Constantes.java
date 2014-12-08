package com.gotoque.torotask.constantes;

import java.util.ResourceBundle;

public class Constantes {

	public static final String RESOURCE_APPLICATION;

	public static final String URL;

	public static final String DATABASE;

	public static final String DRIVER;

	public static final String USUARIO;

	public static final String CLAVE;

	public static final String REPOSITORIO;

	static {
		RESOURCE_APPLICATION = "TOROTASK";
		ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_APPLICATION);

		URL = bundle.getString("JDBCConnectionFactory.url");
		DATABASE = bundle.getString("JDBCConnectionFactory.database");
		DRIVER = bundle.getString("JDBCConnectionFactory.driver");
		USUARIO = bundle.getString("JDBCConnectionFactory.usuario");
		CLAVE = bundle.getString("JDBCConnectionFactory.clave");
		REPOSITORIO = bundle.getString("JDBCConnectionFactory.repositorio");
	}

}
