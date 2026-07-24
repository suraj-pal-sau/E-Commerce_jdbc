package com.irfan.practice;

public class MultipleInheritanceDemo {

	public static void main(String[] args) {
		IAccount preAccount = new PremiumAccount();
		preAccount.showBalance(); // no ambiguity because its overridden

	}

}
