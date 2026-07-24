package com.irfan.inheritance;


public class Payment {
	//payment--connect--options
	private int balance = 5995;
	
	public int getBalance() {
		return balance;
	}
	public void setBalance(int amount) {
		balance = balance - amount;
	}
	
	private final int pin = 1234 ;
	
	///match given with the pin given by user create method 
	
	public void doPayment(int amount, int pin_) {
		boolean check = validatePin(pin_);
		if(check)
		{
			if(amount <= balance)
			{
				setBalance(amount);
				System.out.println("An amount of INR " + amount + " has been DEBITED to your account");
				System.out.println("Total Available balance INR " + getBalance());
			}
			else
			{
				 System.out.println("Insufficient Balance...");
				 System.out.println("Available Balance : RS " + getBalance());
				 return;
			}
		}
		else
		{
			System.out.println("You have entered Wrong PIN....!!");
			 return;
		}
	}
	private boolean validatePin(int _pin) {
		if(_pin == pin)
		{
			return true;
		}
		return false;
	}
	

}
