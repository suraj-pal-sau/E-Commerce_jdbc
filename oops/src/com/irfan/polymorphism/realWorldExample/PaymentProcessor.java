package com.irfan.polymorphism.realWorldExample;

public class PaymentProcessor {
	public void processPayment(Payment payment) {	//Payment payment = new PhonePe();
		
		//Payment payment = new PhonePe();
		payment.doPayment();	//this one line giving runtime polymorphism(auto upcasting)
	}
}
