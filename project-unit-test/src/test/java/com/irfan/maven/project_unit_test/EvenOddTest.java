package com.irfan.maven.project_unit_test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EvenOddTest
{
	EvenOdd checkNumber ;
	@BeforeEach		//BeforAll ---> runs once before all tests
	public void setup() {
		System.out.println("EvenOddTest.setup()");
		checkNumber = new EvenOdd();
	}
	
	@Test
	public void isEvenOddTestWithPositive() {
		System.out.println("EvenOddTest.isEvenOddTestWithPositive()");
		boolean expected = true;
		 boolean actual = checkNumber.isEvenOdd(32);
		 assertEquals(expected, actual);
	}
	
	@Test
	public void isEvenOddTestWithPositive2() {
		System.out.println("EvenOddTest.isEvenOddTestWithPositive()");
		boolean expected = false;
		 boolean actual = checkNumber.isEvenOdd(33);
		 assertEquals(expected, actual);
	}
	
	@Test
	public void isEvenOddTestWithNegative() {
		System.out.println("EvenOddTest.isEvenOddTestWithPositive()");
		boolean expected = false;
		 boolean actual = checkNumber.isEvenOdd(-32);
		 assertEquals(expected, actual);
	}
	
	@Test
	public void isEvenOddTestWithZero() {
		System.out.println("EvenOddTest.isEvenOddTestWithPositive()");
		boolean expected = true;
		 boolean actual = checkNumber.isEvenOdd(0);
		 assertEquals(expected, actual);
	}
}
