package com.irfan.inheritance.override;

public class Driver2 {

	public static void main(String[] args) {
		PaymentSystem supr = new PaymentSystem();
		supr.pay();
		
		PhonePe child1 = new PhonePe();
		child1.pay();
		
		GPay child2 = new GPay();
		child2.pay();
	}

}
