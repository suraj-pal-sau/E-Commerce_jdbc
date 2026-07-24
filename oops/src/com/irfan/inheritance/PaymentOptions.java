package com.irfan.inheritance;

public class PaymentOptions extends PaymentNotification{
	
	public void showOpt(int option) {
		switch(option) {
		case 1:
			System.out.println("Enter your Gpay mobile number :");
			connectBank(1);
			break;
		case 2:
			System.out.println("Enter your PhonePe mobile number :");
			connectBank(2);
			break;
		case 3:
			System.out.println("Enter your card details :");
			connectBank(3);
			break;
		case 4:
			System.out.println("Enter your bank details :");
			connectBank(4);
			break;
		default:
			System.out.println("Invalid Option >> Please Choose Given Options");
		}
		
	}
	
	private void connectBank(int id) {
		System.out.println("Connection successfull...");
		sendSMS(id);
	}
	
}
