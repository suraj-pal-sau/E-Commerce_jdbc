package com.irfan.inheritance;

public class Driver3 {

	public static void main(String[] args) {
		Order order = new Order();
		order.placeOrder("abc@123", "chfhFh12@", "Iphone15");
		
		Invoice inv = new Invoice();
		inv.generateInvoice();
	}

}
