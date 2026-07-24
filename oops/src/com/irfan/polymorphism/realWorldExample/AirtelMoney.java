package com.irfan.polymorphism.realWorldExample;

public class AirtelMoney extends Payment {
	@Override
	public void doPayment()
	{
		System.out.println("AirtelMoney.doPayment()");
	}

}
