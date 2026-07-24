package com.irfan.encapsulation;

public class Account2 {
	//initial balance
	private int balance = 1000; //data

	public int getBalance() {
		return balance;
	}
	//setter
	public void deposit(int amount) {
		//modification in the controlled way
		if (amount > 0) {
			balance = balance + amount;
		}else
		{
			System.out.println("Invalid Amount");
		}
		
	}
	
}
