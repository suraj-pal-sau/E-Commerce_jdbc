package com.irfan.maven.project_unit_test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

///code duplication removal
public class CalculatorTest1
{
	Calculator calculator;
	
	@BeforeEach		// before every method it will run
	void before() {
		System.out.println("CalculatorTest1.before()");
		calculator = new Calculator();
	}
	
	@Test 
	public void addTwoNumbersTest()
	{
		System.out.println("CalculatorTest1.addTwoNumbersTest()");
		//Calculator calc = new Calculator();	//it is repeating

		int expected = 30; // you know the result should be

		int actual = calculator.addTwoNumber(12, 18);

		assertEquals(expected, actual);
	}

	@Test
	public void addTwoNumbersTestWithNegative()
	{
		System.out.println("CalculatorTest1.addTwoNumbersTestWithNegative()");
//		Calculator calc = new Calculator(); //it is repeating

		int expected = 6; 

		int actual = calculator.addTwoNumber(-12, 18);

		assertEquals(expected, actual);
	}
	
	@Test
	public void addTwoNumbersTestWithZero()
	{
		System.out.println("CalculatorTest1.addTwoNumbersTestWithZero()");
//		Calculator calc = new Calculator();

		int expected = 18; 

		int actual = calculator.addTwoNumber(0, 18);

		assertEquals(expected, actual);
	}
}
