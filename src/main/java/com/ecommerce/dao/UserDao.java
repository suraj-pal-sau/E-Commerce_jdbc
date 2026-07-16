package com.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ecommerce.config.DBconfig;
import com.ecommerce.constants.CustomerQueries;

public class UserDao {

	public boolean signUp(String username, String email, String password, String role) {
		String query = CustomerQueries.USER_SIGNUP_QUERY;
        
		try  {
			Connection connection = DBconfig.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, email);
			pstmt.setString(3, password);
			pstmt.setString(4, role);

			int rows = pstmt.executeUpdate();
			return rows > 0;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean logIn(String email, String password) {

	    try (Connection connection = DBconfig.getConnection();
	         PreparedStatement pstmt =
	             connection.prepareStatement(CustomerQueries.USER_LOGIN_QUERY)) {

	        pstmt.setString(1, email);
	        pstmt.setString(2, password);

	        ResultSet result = pstmt.executeQuery();

	        return result.next();

	        
	    } catch (Exception e) {
	        System.err.println("Error from UserDao: " + e.getMessage());
	        return false;
	    }
	}	
}