package com.irfan.encapsulation;

//encapsulation ->> declaring data(fields/attribute) as private and if someone(other classes)
//want to modify/read data, then they will use public setter to modify/set the values and getter to read/get the values. 

public class Driver2 {

	public static void main(String[] args) {
		Account2 account = new Account2();
		account.deposit(2000);
		System.out.println(account.getBalance());
		
		//invalid amount
		account.deposit(-322333);
		System.out.println(account.getBalance());	//not updated
	
	}

}
