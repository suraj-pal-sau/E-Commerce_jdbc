package com.irfan.polymorphism.realWorldExample;

//23.04

/// compile time polymorphism	: resolving which method to call at compile time
/// static binding (early binding)
public class Driver2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculator calculator = new Calculator();
		
		double result1 = calculator.add(1233, 3654.5);
		System.out.println(result1);
		
		double result2 = calculator.add(453.8, 3654.5, 57463);
		System.out.println(result2);
	}

}
