package com.irfan.polymorphism.realWorldExample;

/// 22.04

//runtime polymorphism 
//  only method will give you overriding and method overriding gives you runtime polymorphism.
//not variables
///dynamic binding (late binding): resolving at runtime

public class Driver {

	public static void main(String[] args) {
		PaymentProcessor process = new PaymentProcessor();
		
		//CardPayment cardPay = new CardPayment();
		UPIpayment upi = new UPIpayment();
		process.processPayment(upi);
		
		AirtelMoney airtelMoney = new AirtelMoney();
		process.processPayment(airtelMoney);
		
		//after some time new features added not need to change the existing codes just write the codes of new features
		PhonePe phonepe = new PhonePe();
		process.processPayment(phonepe);

	}

}
