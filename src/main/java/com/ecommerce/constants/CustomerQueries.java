package com.ecommerce.constants;

public class CustomerQueries {

    public static final String USER_SIGNUP_QUERY = 
        "INSERT INTO User(username, email, password, role) VALUES(?,?,?,?)";

    public static final String USER_LOGIN_QUERY = 
        "SELECT * FROM User WHERE email = ? AND password = ?";
}