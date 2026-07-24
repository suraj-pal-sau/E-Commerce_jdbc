package com.irfan.encapsulation;

// 14/4

public class Driver1 {

	public static void main(String[] args) {
		Account1 account = new Account1();
		
		account.balance = 1000000; 	//anyone can "directly modify" balance(balance is public in Account class) 
									//thats not fare
		account.balance = -1000000;		
		    //anyone can put -ve value also
			//it is not acceptable
			//thats why we need to make balance private
		
		//account.balance1 = 100000;	//error
		
		account.setBalance1(-1000);
		System.out.println(account.getBalance1()); //it will not print -ve values
		

	}

}
