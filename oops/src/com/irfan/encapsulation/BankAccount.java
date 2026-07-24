package com.irfan.encapsulation;

public class BankAccount {
	private double balance;

	//to set initial balance
	public BankAccount(double balance) {
		this.balance = balance;
	}
	
	//read-only
	public double getBalance() {
		System.out.print("Available Balance : INR ");
		return balance;
	}
	
	public void deposit(double amount) {
		//modifying in controlled way
		//authentication(account holder, account number verification)
		//...
		if(amount > 0) {
			balance += amount;
			System.out.println("Deposited : " + amount);
		}else {
			System.out.println("Invalid amount");
		}
	}
	
	public void withdraw(double amount) {
		//checking / matching details
		if(amount <= balance && amount > 0) {
			balance -= amount;
			System.out.println("Withdrawn : " + amount);
		}else {
			System.out.println("Insufficient amount or Invalid amount");
		}
	}
}
