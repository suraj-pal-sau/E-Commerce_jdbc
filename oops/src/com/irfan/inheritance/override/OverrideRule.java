package com.irfan.inheritance.override;

//20.04.26

class PaymentInterface{
	//default access modifier
	void doPayment(String accNo, int amount, String note) {
		System.out.println("PaymentInterface.doPayment()");
	}
	
}

class UPI extends PaymentInterface{
	@Override 	// checking all rules for overriding
	//here public, which is wider than parent method which is default
	//change public to private and see error ->> Cannot reduce the visibility/scope of the inherited method from PaymentInterface
	public void doPayment(String accNo, int amount, String note) {
		System.out.println("UPI.doPayment()");
	}
}
	//override rules
	// The method does override or implement a method declared in a supertype.
	
	//1. access modifier must be same or wider(shouldn't reduce the scope of method) 
		//wider mean if parent method -> public then child method must not be private/or default
		//acceptable wider modifier -- default to public, default to protected, protected to public
	//2. same method name
	//3. number, order and type of arguments must be same
	//4. return type same except covariant
	//5. exception --> same or child class

//protected : It serves as a middle ground between private and public
//  		A protected member is accessible within its own package and 
//			by subclasses (even if those subclasses are in different packages).
public class OverrideRule {

	public static void main(String[] args) {
		UPI pay = new UPI();
		pay.doPayment(null, 0, null);
	}

}
