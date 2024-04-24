package com.pranshusaini.chatapp.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import com.pranshusaini.chatapp.dto.UserDTO;
import com.pranshusaini.chatapp.utils.Encryption;

//Throw early and catch later.

public class UserDAO {
//	perform CRUD operation for User
	
//	As there could be a case where more than 4 fields have to be entered to the database,
//	then using functions like this is not suitable and we have to use a wrapper class to catch all these
//	attributes instead for better functioning and readability.
//	public int add(String userid, String password) {
		
//	}
	
	public boolean isLogin(UserDTO userDTO) throws SQLException, ClassNotFoundException, Exception{
		// Same as the add function we have written.
		Connection con = null;
		
//		Better way to perform the below
//		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "select userid from users where userid = ? and password = ?"; // ? will be replaced in runtime that is why it is called prepared statement.
		try {
			con = CommonDAO.createConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, userDTO.getUserid());
			String encryptedPwd = Encryption.passwordEncrypt(new String(userDTO.getPassword()));
			pstmt.setString(2, encryptedPwd);
			rs = pstmt.executeQuery();
			
//			As rs.next() gives out boolean value as output
			return rs.next();
			
		} 
		finally {
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
		}
	}
	
	// Using DTO
	public int add(UserDTO userDTO) throws ClassNotFoundException, SQLException, Exception {
		System.out.println("Rec: "  + userDTO.getUserid() + " " + userDTO.getPassword());
		
//		Making the Connection;
		Connection connection = null;
		
//		Converting the DTO object to SQL query;
		Statement stmt = null; // Statement object.
		try {
//			Making connection Step 1
			connection = CommonDAO.createConnection();
		
//			Making query Step 2
			stmt = connection.createStatement();
			// insert into users(userid, password) values ('ram', '123456');
			int record = stmt.executeUpdate("insert into users (userid, password) values ('" + userDTO.getUserid() + "','" + Encryption.passwordEncrypt(new String (userDTO.getPassword())) + "');"); // Insert, Delete, Update
			return record;
		}
		finally { // Always executed 
			if(stmt != null) {
				stmt.close();
			}
			if(connection != null) {
				connection.close();
			}
		}
		
	}
	
}
