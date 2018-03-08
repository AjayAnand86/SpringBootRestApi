package com.ing.springboot.dao;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;

	public class ConnectDB {

	    private static ConnectDB con = new ConnectDB();

	    private ConnectDB() {
	        try{
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }

	    public Connection createCon() {
	        Connection connection = null;
	        try{
	            connection = DriverManager.getConnection("jdbc:oracle:thin:@10.0.0.22:1521:xe","system","root");
	        } catch (SQLException e) {
	            System.out.println("Connection to db could not be done");
	        }
	        return connection;
	    }

	    public static Connection getConection() {
	        return con.createCon();
	    }
	}
