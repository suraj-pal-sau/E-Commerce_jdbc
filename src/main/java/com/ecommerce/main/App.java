package com.ecommerce.main;

import com.ecommerce.service.UserSignUp;

public class App {
    public static void main(String[] args) {

        UserSignUp userSignUp = new UserSignUp();

        boolean isRegistered = userSignUp.signUp(
                "kodewala", 
                "kodewala@gmail.com", 
                "kode@123", 
                "customer"
        );

        if (isRegistered) {
            System.out.println("Signup Successful!");
        } else {
            System.out.println("Signup Failed!");
        }
    }
}