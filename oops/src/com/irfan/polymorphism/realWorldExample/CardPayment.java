package com.irfan.polymorphism.realWorldExample;

public class CardPayment extends Payment {
	@Override
	public void doPayment() {
		System.out.println("CardPayment.doPayment()");
	}
}
