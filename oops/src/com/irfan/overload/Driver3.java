package com.irfan.overload;

/// Java resolves ambiguity in method overloading by applying strict compile-time rules: 
/// it first looks for an exact match, then applies type promotion, 
/// and finally chooses the most specific method. 
/// If two methods are equally valid, the compiler throws an ambiguous method call error. 
/// 
/// Why Return Type Alone Doesn't Work
/// Method signature definition: In Java, a method's signature is defined by its name and the parameter list—not by its return type.
/// Ambiguity at compile time: If two methods have the same name and the same parameters but different return types, 
/// the compiler can't determine which method you’re trying to call based on the return type alone.

class Calculator{
	
//	public float add(int a, float b) {
//		System.out.println("Calculator.add(int a, float b)");
//		return a+b;
//	}
	
	protected double add(float a, long b) {
		return a + b;
	}
	
	protected double add(float a, double b) {
		return a + b;
	}
	
	///compile time error -> method is ambiguous
	public void testAmbiguity(String str) {
		System.out.println("String");
	}
	
	public void testAmbiguity(Integer intgr) {
		System.out.println("Integer");
	}
	
}
public class Driver3 {
	
	//for type promotion
	
	static void test(int x) { System.out.println("int :  " + x + " + " +'b' + " = " + (x + 'b') ); }
    static void test(double x) { System.out.println("double " + x); }

	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		//calculator.add(1, 1);	//The method add(int, float) is ambiguous for the type Calculator
		
		//calculator.testAmbiguity(null);	//The method testAmbiguity(String) is ambiguous for the type Calculator
		
		 test(5);     // Exact match → int
	     test(5.5);   // Exact match → double
	     test('a');   // char promoted to int → int --> ASCII value
	     test(5f);   //float promoted to double -> double
	     test(5L);   //long promoted to double -> double
		
	}

}
