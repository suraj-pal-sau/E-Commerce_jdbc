package com.irfan.polymorphism.realWorldExample;

public class PhonePe extends Payment{
	@Override
	public void doPayment() {
		System.out.println("PhonePe.doPayment()");
	}
}
