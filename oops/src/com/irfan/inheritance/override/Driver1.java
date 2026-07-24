package com.irfan.inheritance.override;

//inheritance : IS-A relation (inheritance is required to override)
// override : try to change the default property what your parent(super class) is providing if you(child class) don't want that default functionality 
// change super class functionalities in child class 


class Employee{
	
	//functionalities
	
	public void saySomthing() {
		System.out.println("hello");	//it is overridden
	}
	
	public void cooking() {
		System.out.println("I am going to cook Paratha.");
	}
	
	public void card() {
		System.out.println("Employee.card()");
	}
	
	
}

class Manager extends Employee{		//manager IS-A employee type
	public void saySomthing() {
		System.out.println("hey bro.");	//change in child class
	}
	
	public void cooking() {
		System.out.println("I want Puri.");
	}
}


public class Driver1 {

	public static void main(String[] args) {
		Manager mng = new Manager();
		mng.saySomthing();

		mng.cooking();
		
		mng.card(); //using parent functionalities
	}

}
