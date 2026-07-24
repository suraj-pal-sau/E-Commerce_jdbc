package com.irfan.encapsulation;
	
	//encapsulation comes from capsule
	//balance1 is invisible means it is not accessible or can't be changed by other classes. 

public class Account1 {
	public int balance;		//any other class can modify balance directly
	private int balance1;	// data or field should be private so that any class can't change data directly
	
		//to read
	public int getBalance1() {
		return balance1;
	}
		
		//to update
		//balance1 will be modified based on some condition 
		//but not directly
	public void setBalance1(int balance1) {
		//data is modified in authorized way
		if(balance1 > 0) {	//check here balance is legal or not
			this.balance1 = balance1;
		}
		
	}
}
