package com.irfan.abstraction;

public class Driver1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoginService user1 = new LoginService();
		
		//user requirement is to login in the system
		user1.doLogin("abss@gmail.com", "agh@1229");
		
		/// if implementations were public, user can call that directly
		/// user1.connectToDB;
		/// user1.encryption;	and so on 
	}

}
