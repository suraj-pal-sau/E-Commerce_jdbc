package com.irfan.abstraction;

public class LoginService {
	///abstraction
	public void doLogin(String userID, String Password)
	{
		//connect to database
		//do encryption / decryption ->> password is stored in database as encrypted form always
		//business logic to validate users
		
		connectToDB();
		encryption();
		
		///after some time new rule is added that we have to reset password after every 2 months 
		promptPasswordReset();
		validateUser();
		System.out.println("Login Successful.....");
	}
	
	//hiding implementations(private)
	private void connectToDB()
	{
		System.out.println("LoginService.connectToDB()");
	}
	
	private void encryption()
	{
		System.out.println("LoginService.encryption()");
	}
	
	private void validateUser()
	{
		System.out.println("LoginService.validateUser()");
	}
	
	private void promptPasswordReset()
	{
		System.out.println("LoginService.promptPasswordReset()");
	}
}
