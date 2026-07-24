package com.irfan.inheritance;

import java.util.Scanner;

public class PaymentDriver {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose payment options");
		System.out.println("1. Gpay");
		System.out.println("2. Phonepe");
		System.out.println("3. Debit Card");
		System.out.println("4. Net-Banking");
		
		int option = sc.nextInt();
		PaymentOptions pay = new PaymentOptions();
		pay.showOpt(option);
		
		sc.close();
		
	}
}
