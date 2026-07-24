package com.irfan.inheritance;

import java.util.Scanner;

public class PaymentNotification extends Payment {
	Scanner sc = new Scanner(System.in);
	int input;
	int pin;
	int amount;
	public void sendSMS(int id) {
		switch(id) {
			case 1:
				System.out.println("SMS : You have requested for payment using Gpay...");
				System.out.println("Enter '1' to complete payment process.");
				System.out.println("if not done by you, Enter '0' ");
				input = sc.nextInt();
				if(input == 1)
				{
					initiateProcess();
				}else if (input == 0)
				{
					System.out.println("OOps -> Payment failed...");
				}else
				{
					System.out.println("Invalid option selected...!!!");
				}
				break;
			case 2:
				System.out.println("SMS : You have requested for payment using PhonePe...");
				System.out.println("Enter '1' to complete payment process.");
				System.out.println("if not done by you, Enter '0' ");
				input = sc.nextInt();
				if(input == 1)
				{
					initiateProcess();
				}else if (input == 0)
				{
					System.out.println("OOps -> Payment failed...");
				}else
				{
					System.out.println("Invalid option selected...!!!");
				}
				break;
			case 3:
				System.out.println("SMS : You have requested for payment using Debit Card...");
				System.out.println("Enter '1' to complete payment process.");
				System.out.println("if not done by you, Enter '0' ");
				input = sc.nextInt();
				if(input == 1)
				{
					initiateProcess();
				}else if (input == 0)
				{
					System.out.println("OOPs -> Payment failed...");
				}else
				{
					System.out.println("Invalid option selected...!!!");
				}
				break;
			case 4:
				System.out.println("SMS : You have requested for payment using Net-Banking...");
				System.out.println("SMS : This option is not available now. Pay using the above available options");
				break;
		}
		
	}
	
	private void initiateProcess()
	{
		System.out.println("SMS : Enter your amount: ");
		amount = sc.nextInt();
		System.out.println("SMS : Enter your 4 digit pin: ");
		pin = sc.nextInt();
		doPayment(amount, pin);
	}
	
}
