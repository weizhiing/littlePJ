<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>

<%
	final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	final String DB_CONNECTION = "jdbc:mysql://localhost:3306/online_course?useSSL=false&serverTimezone=GMT";
	final String DB_USER = "root";
	final String DB_PASSWORD = "589985";	
	
	Connection dbConnection = null;
	try {
		Class.forName(DB_DRIVER);
	} catch(ClassNotFoundException e) {
		System.out.println(e.getMessage());
	}
	
	try {
		dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
		
	} catch(SQLException e) {
		System.out.println("conneciton failed: check output console");
		e.printStackTrace();
		return;
	}
	
	if(dbConnection != null) {
		System.out.println("you made it, take control your database now!");
	} else {
		System.out.println("failed to make connection");
	}
%>

