package com.ecommerce.service;

import java.sql.SQLException;

import com.ecommerce.constants.CustomerQueries;
import com.ecommerce.dao.UserDao;

public class UserSignUp {
	public void signUp(String name, String email, String password, String role) throws SQLException {

		try {
			UserDao userDao = new UserDao();
			userDao.getUserConnection();
			userDao.userPreparedStatement.setString(1, name);
			userDao.userPreparedStatement.setString(2, email);
			userDao.userPreparedStatement.setString(3, password);
			userDao.userPreparedStatement.setString(4, role);
			CustomerQueries customerQuery = new CustomerQueries();
			UserDao.userPreparedStatement.executeUpdate(customerQuery.userSignUpQuery);
		} catch (Exception e) {

		}
	}
}
