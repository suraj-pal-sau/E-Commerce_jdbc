package com.irfan.practice;

//same ambiguity arises 
//java handles it by forcing to override 
/// error when not overriding --->  Duplicate default methods named showBalance with the parameters () and () are inherited from the types ISavingAccount and IAccount
/// giving two suggestions --> 1) override default method in IAccount
/// 							  2) override default method in ISavingAccount
//comment the override method and see the error
/// solution --> override the conflicting methods


public class PremiumAccount implements IAccount, ISavingAccount{

	/// if you want to use method of Interface --> call it by super keyword
//	@Override
//	public void showBalance() {
//		
//		//InterfaceName.super.MethodName()
//		
//		IAccount.super.showBalance();
//		System.out.println("PremiumAccount.showBalance()......providing extra features..... using one interface's method...");
//	}
	
	/// if you want to use both Interface's method using those methods providing extra features
//	@Override
//	public void showBalance() {
//		
//		IAccount.super.showBalance();
//		ISavingAccount.super.showBalance();
//		System.out.println("PremiumAccount.showBalance()......providing extra features....using both interface methods...");
//	}
	
	/// if you want provide your totally new implementations
		@Override
		public void showBalance() {
			
			System.out.println("PremiumAccount.showBalance()......providing own totally new implementations");
		}

}
