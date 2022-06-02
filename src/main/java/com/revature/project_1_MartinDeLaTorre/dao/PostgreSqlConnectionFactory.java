package com.revature.project_1_MartinDeLaTorre.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgreSqlConnectionFactory {
	private static String USER = System.getenv("P1_USERNAME");
	private static String PASSWORD = System.getenv("P1_PASSWORD");
	private static String ENDPOINT = System.getenv("P1_ENDPOINT");
	
	private static Connection conn = null;
	
	static Connection getConnection() {
		if(conn == null) {
			
			if(ENDPOINT == null) {
				throw new Error("P1_ENDPOINT is not set. please set the environment variable in your run configurations.");
			}
			
			String url = "jdbc:postgresql://" + ENDPOINT + ":5432/project_1";
			Properties props = new Properties();
			
			if(USER == null) {
				throw new Error("P1_USERNAME is not set. please set the environment variable in your run configurations.");
			}
			if(PASSWORD == null) {
				throw new Error("P1_PASSWORD is not set. please set the environment variable in your run configurations");
			}
			
			props.setProperty("user",USER);
			props.setProperty("password",PASSWORD);
	//		props.setProperty("ssl","true");
			try {
				conn = DriverManager.getConnection(url, props);
			} catch (SQLException cause) {
				throw new Error("Could Not make connection to RDS postgress database.", cause);
			}
		}
		
		return conn;
	}
}
