package com.irfan.maven.project_unit_test;

///  interview question ---> how do you make sure that whatever changes you are doing, will not impact your existing implementation?
//	we do have unit tests ---> developer will write unit test cases

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CalculatorTest
{
	@Test
	public void addTwoNumbersTest()
	{
		Calculator calc = new Calculator();	//it is repeating

		int expected = 30; // you know the result should be

		int actual = calc.addTwoNumber(12, 18);

		assertEquals(expected, actual);
	}

	@Test
	public void addTwoNumbersTestWithNegative()
	{
		Calculator calc = new Calculator(); //it is repeating

		int expected = 6; 

		int actual = calc.addTwoNumber(-12, 18);

		assertEquals(expected, actual);
	}
	
	@Test
	public void addTwoNumbersTestWithZero()
	{
		Calculator calc = new Calculator();

		int expected = 18; 

		int actual = calc.addTwoNumber(0, 18);

		assertEquals(expected, actual);
	}

}
