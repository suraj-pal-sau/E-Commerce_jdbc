package com.irfan.inheritance.override;
//covariant return type given by java5

class Vehicle1 {
	
	Parent getDetails(String name, double price, String engine) {
		System.out.println("Vehicle......getDetails()");
		return new Parent("I want " + name + " details.");
	}
}

class Car1 extends Vehicle{
	
	Child getDetails(String name, double price, String engine) {
		System.out.println("Car......getDetails()");
		return new Child(name, engine);	//covariant return type
		//a child class can override a method and return a subtype of the parent class’s return type.
	}
}

public class CovariantReturnType3 {
	public static void main(String[] args) {
		Car1 car = new Car1();
		car.getDetails("BMW", 1.5, "Electric engine");
	}
}
