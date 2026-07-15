package com.ecommerce.constants;

public class CustomerQueries {

	public String userSignUpQuery = "INSERT INTO User(user_id, username, email, password, created_at, role) values(?,?,?,?,?,?)";

	public String userLoginQuery = "SELECT * FROM User";

}