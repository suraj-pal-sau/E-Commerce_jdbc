package com.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ecommerce.config.DBconfig;
import com.ecommerce.constants.CustomerQueries;
import com.ecommerce.model.Seller;

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

	public Seller logIn(String email, String password) {

	    try (Connection connection = DBconfig.getConnection();
	         PreparedStatement pstmt =
	             connection.prepareStatement(CustomerQueries.USER_LOGIN_QUERY)) {

	        pstmt.setString(1, email);
	        pstmt.setString(2, password);

	        ResultSet result = pstmt.executeQuery();

	        while (result.next()) {
	        	int user_id = result.getInt(1);
	        	String username = result.getString(2);
	        	String email1 = result.getString(3);
	        	String password1 = result.getString(4);
	        	String role = result.getString(6);
	        	
	        	Seller seller = new Seller(user_id, username, email1, password1, role);
	        	
	        	return seller;
	        }
	        
	    } catch (Exception e) {
	        System.err.println("Error from UserDao: " + e.getMessage());
	        return null;
	    }
	    
	    return null;
	}	
}