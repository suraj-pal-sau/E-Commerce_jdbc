package com.irfan.encapsulation;

public class AccountHolder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BankAccount account1 = new BankAccount(1000);	//create account
		
		System.out.println(account1.getBalance());//checking balance
		
		account1.deposit(5000);
		System.out.println(account1.getBalance());
		account1.deposit(-5000);
		
		account1.withdraw(10000);
		account1.withdraw(3500);
		
		System.out.println(account1.getBalance());//checking balance
		
	}

}
