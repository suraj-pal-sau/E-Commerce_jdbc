package com.irfan.inheritance;

// 15/4

class AccountMngmt
{
	String ifsc = "CNRB0000122";
	public void someMethod() {
		System.out.println("AccountMngmt.someMethod()........1000 lines");
	}
	
}

class Account extends AccountMngmt
{
	public void pay() {
		Account acc = new Account();
		acc.someMethod();
		System.out.println(acc.ifsc);
	}
}

public class Driver1 {

	public static void main(String[] args) {
		Account acc = new Account();
		acc.pay();
	}

}
