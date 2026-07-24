package com.irfan.polymorphism;

/// 22.04

/*
 	 Runtime Polymorphism (Dynamic)
This is achieved through Method Overriding, where a subclass provides its own specific implementation of a method that is already defined in its parent class. 

	*Dynamic Method Dispatch: The Java Virtual Machine (JVM) decides which method to execute at runtime based on the actual object type, not the reference type.
	
	*Upcasting: A parent class reference variable can point to a child class object, allowing the code to handle different objects in a unified way.
 */
class Vehicle
{
	public void engine() {
		System.out.println("Vehicle.engine()");
	}
}

class Car extends Vehicle
{
	@Override
	public void engine() {
		System.out.println("Car.engine()");
	}
}

class ElectricCar extends Car
{
	@Override
	public void engine() {
		System.out.println("ElectricCar.engine()");
	}
}
public class Driver {
	public static void main(String[] args) {
		Vehicle car = new Car();	//auto up casting
		//reference type of car is Vehicle, object type is Car
		
		//at compile time -> it will be checked that engine() is available in parent class(Vehicle) or not
		//at runtime -> jvm decides which method to call based on actual object type,not reference type 
		car.engine();	//deciding which method to call at runtime ->runtime polymorphism
						//dynamic method dispatch : runtime calling method based on actual object type,not reference type 
		
		Car electricCar = new ElectricCar();	///compile time :it will be checked that engine() is available in parent class(Car) or not
		electricCar.engine();
	}
}
