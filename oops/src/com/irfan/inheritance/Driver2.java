package com.irfan.inheritance;

//advantage : you need to develop code from scratch if there is not inheritance  

import java.util.Scanner;

class Bank
{
	String bankName = "UNION BANK";
	String ifscCode = "UNI0000148";
	String address = "Kolkata, West Bengal";
	public void loading() {
		System.out.println("WAIT : Your details are being loaded....");
	}
	public void bankInfo() {
		System.out.println(" Bank Name : " + bankName);
		System.out.println(" IFSC Code : " + ifscCode);
		System.out.println(" Bank Address : " + address);
	}
	
}

class Customer extends Bank{
	Bank customer = new Bank();
	
	public Customer(int _accountNo) {
		this.getDetails(_accountNo);
	}
	public void getDetails(int accNumber) {
		customer.loading();
		switch(accNumber) {
		case 1:
			customer.bankInfo();
			System.out.println(" Name : Sharib Khan");
			System.out.println(" Account No. : 0000000001");
			System.out.println(" Occupation : Self-Employed");
			System.out.println(" D.O.B : 05/12/1998");
			System.out.println(" Branch : Kidderpore");
			System.out.println(" A/c Opened on : 25-April-2012");
			break;
		case 2:
			customer.bankInfo();
			System.out.println(" Name : Manoj Kumar");
			System.out.println(" Account No. : 0000000002");
			System.out.println(" Occupation : Businessman");
			System.out.println(" D.O.B : 22/08/1994");
			System.out.println(" Branch : Howrah");
			System.out.println(" A/c Opened on : 28-February-2013");
			break;
		case 3:
			customer.bankInfo();
			System.out.println(" Name : Ankit Sharma");
			System.out.println(" Account No. : 0000000003");
			System.out.println(" Occupation : Student");
			System.out.println(" D.O.B : 10/10/2001");
			System.out.println(" Branch : Park Street");
			System.out.println(" A/c Opened on : 05-June-2014");
			break;
		default:
			System.out.println(accNumber + " >> NO ACCOUNT FOUND");
		}
	}
}

public class Driver2 {

	public static void main(String[] args) {
		Bank bank = new Bank();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Welcome to " + bank.bankName);
		System.out.println("Enter Your Account No. (from 1 to 3) ");
		
		int accNo = scan.nextInt();
		Customer customer1 = new Customer(accNo);
		System.out.println(customer1.bankName);
		
		scan.close();
	}

}
