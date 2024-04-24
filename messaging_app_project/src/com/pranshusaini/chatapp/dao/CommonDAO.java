package com.pranshusaini.chatapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Resource Bundle
import static com.pranshusaini.chatapp.utils.ConfigReader.getValue;

public interface CommonDAO {
	public static Connection createConnection() throws ClassNotFoundException, SQLException {
//		To download the driver we will go to Maven Repo
		
//		Loading the Driver STEP - 1;
		Class.forName(getValue("DRIVER"));
		
//		Making Connection STEP - 2;
		final String CONNECTION_STRING = getValue("CONNECTION_URL");
		final String USER_ID = getValue("USERID");
		final String PASSWORD = getValue("PASSWORD");
		Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_ID, PASSWORD);
		if(con != null) {
		    System.out.println("Connection Created ...");
//		     con.close(); // Remove or comment out this line
		}
		return con;
	}
	
//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		CommonDAO commonDAO = new CommonDAO();
//		commonDAO.createConnection();
//	}
}