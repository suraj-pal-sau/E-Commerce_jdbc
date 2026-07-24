package com.irfan.inheritance;

public class Notifications {
	String countryName = "India";
	
	public void sendEmail(String email, String text, String attachment) {
		System.out.println("Notifications.sendEmail()");
		System.out.println(text);
	}
	
	public void sendSMS(String mob, String text) {
		System.out.println("Notifications.sendSMS()");
	}
	
	public void sendInvoice(String email, String text, String productName, String orderId) {
		System.out.println("Notifications.sendInvoice()");
		System.out.println(text);
		System.out.println("Email : " + email );
		System.out.println("Product Name : " + productName );
		System.out.println("Order Id : " + orderId );
		
	}

}
