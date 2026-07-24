package com.irfan.overload;

// Methods can share the same name if their parameter lists differ.
// Cannot overload by return type alone; parameters must differ.

//rule : same method name + different(order of args or number of args or type of args)

//// you can change access modifier and return type but 
/// if you change only return type then it is not overloading
/// for overloading you must follow the above rule

// adv : not neeed to call method again and again for different user or a single user, just change the argument
// 		giving Flexibility to users

class LoginService
{
	
	protected void doLogin(String mob, int otp) {
		System.out.println("LoginService.doLogin(String mob, int otp)");
	}
		
		//order of args different
	 void doLogin(int otp,String mob) {
		System.out.println("LoginService.doLogin(int otp,String mob)");
	}
	
	/// number of args different
	public void doLogin(String aadhar, int otp, String pan) {
		System.out.println("LoginService.doLogin(String aadhar, int otp, String pan)");
	}
	//type of args different
	public void doLogin(String pan, String passport) {
		System.out.println("LoginService.doLogin(String pan, String passport)");
	}
}

public class Driver1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoginService loginService = new LoginService();
		
		loginService.doLogin("8743512414", 4556);
	}

}
