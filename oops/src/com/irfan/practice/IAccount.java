package com.irfan.practice;

public interface IAccount {
	default void showBalance() {
		System.out.println("Account Balance...");
	}
}
