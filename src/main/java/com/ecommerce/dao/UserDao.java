package com.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ecommerce.config.DBconfig;
import com.ecommerce.constants.CustomerQueries;

public class UserDao {
	public static PreparedStatement userPreparedStatement;
	static CustomerQueries customerQueries = new CustomerQueries();

	public void getUserConnection() throws SQLException {
		DBconfig dbConfig = new DBconfig();
		Connection connection = dbConfig.getConnection();

		userPreparedStatement = connection.prepareStatement(customerQueries.userSignUpQuery);
	}
}
