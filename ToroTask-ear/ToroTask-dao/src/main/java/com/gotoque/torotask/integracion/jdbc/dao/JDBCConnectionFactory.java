package com.gotoque.torotask.integracion.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnectionFactory {

	static String url = "jdbc:mysql://localhost:3306/";
	static String dbName = "torotask";
	static String driver = "com.mysql.jdbc.Driver";
	static String userName = "root"; 
	static String password = "root";	
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
			System.out.println("Connected to the database");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}
