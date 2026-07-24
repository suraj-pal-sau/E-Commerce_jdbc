package com.irfan.inheritance;

public class Invoice extends Notifications{
	public void generateInvoice() {
		//once invoice is generated, we need to send pdf over email
		
		//sendEmail("user123@gmail.com", "please find attached invoice", "path for attachment");
		String text = "Invoice is generated....";
		sendInvoice("user123@gmail.com", text, "Iphone15", "PHN03857282");
	}
}
