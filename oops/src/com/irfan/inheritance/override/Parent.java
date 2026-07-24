package com.irfan.inheritance.override;
	
//covariant return type 

public class Parent {
	Parent(String message){
		super();
		System.out.println("Name :" + message);
	}
}

class Child extends Parent{
	Child(String message, String code){
		super(message);
		System.out.println(code);
	}
}
