package com.ecommerce.service;

import com.ecommerce.dao.UserDao;

public class UserSignUp {

	private UserDao userDao = new UserDao();

	public boolean signUp(String username, String email, String password, String role) {
		// Basic validation
		if (username == null || email == null || password == null || role == null) {
			return false;
		}

		return userDao.signUp(username, email, password, role);
	}
}