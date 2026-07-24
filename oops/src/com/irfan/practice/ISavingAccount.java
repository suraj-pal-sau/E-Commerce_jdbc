package com.irfan.practice;

public interface ISavingAccount {
	default void showBalance() {
		System.out.println("Saving Account Balance...");
	}
}
