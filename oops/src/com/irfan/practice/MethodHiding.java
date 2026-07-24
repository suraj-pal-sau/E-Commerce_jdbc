package com.irfan.practice;

class Parent{
	String name="irfanP";
	
	public static void sendMessage() {
		System.out.println("Parent.sendMessage()");
	}
}

class Child extends Parent{
	String  name="irfanC";

	
	public static void sendMessage() {
		
		System.out.println("Child.sendMessage()");
	}
}

public class MethodHiding {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parent parent = new Parent();	//save time dont create object
		parent.sendMessage(); //it is also applicable but does not need to create object for static access by class name

		Parent.sendMessage();
		
		parent = new Child();
		parent.sendMessage(); //static method dont need object for calling it it need reference type
		
		Child.sendMessage();
		
		System.out.println(parent.name); //variable does not provide polymorphism --> access by reference type not depends on object
		
		Child child = new Child();
		System.out.println(child.name);
	}

}
