package com.irfan.polymorphism;

class Delivery
{
	int x = 33;
	public void doDelivery()
	{
		System.out.println("Delivery.doDelivery()");
	}
}

class EcomDelivery extends Delivery
{
	int x = 34;
	@Override
	public void doDelivery()
	{
		System.out.println("EcomDelivery.doDelivery()");
	}
}
public class Driver1 {

	public static void main(String[] args) {
		Delivery delivery = new EcomDelivery();
		
		delivery.doDelivery(); // compile time it check that doDelivery() method exist in Delivery class type or not
								// it giving error if commented the method
								//runtime it will check which method to call based on actual object
		
		System.out.println(delivery.x);	// it will not give value of Ecomdelivery because data members can't be overridden
										///so runtime polymorphism is achieved by only methods which are overriding
	}

}
