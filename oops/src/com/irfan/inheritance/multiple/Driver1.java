package com.irfan.inheritance.multiple;

class Account {
	public void openAccount(String someDetails) {
		System.out.println("Account.openAccount()");
	}
}

class SavingAccount extends Account{
	public void openAccount(String someDetails) {
		System.out.println("SavingAccount.openAccount()");
	}
}

class CurrentAccount extends Account{
	public void openAccount(String someDetails) {
		System.out.println("CurrentAccount.openAccount()");
	}
}


//multiple inheritance is not allowed by class(one child can't have more than one parent)
//you can look below showing error : Syntax error on tokens, delete these tokens
//allowed by interface
/*
class HybridAccount extends SavingAccount, CurrentAccount{
		public void openAccount(String someDetails) {
			System.out.println("HybridAccount.openAccount()");
		}
}
*/
public class Driver1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
