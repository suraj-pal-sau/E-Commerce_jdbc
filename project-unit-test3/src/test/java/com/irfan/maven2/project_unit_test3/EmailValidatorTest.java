package com.irfan.maven2.project_unit_test3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple.
 */
public class EmailValidatorTest
{
	private EmailValidator validator;
	
	@BeforeAll	/// once before all tests
	public static void beforeAll() {
		System.out.println("..........beforeAll()...........");
	}
	
	@AfterAll	//once after all tests
	public static void aftereAll() {
		System.out.println("..........afterAll()...........");
	}

	@BeforeEach
	public void before()
	{
		System.out.println("EmailValidatorTest.before()");

		validator = new EmailValidator();
	}

	@AfterEach
	public void after()
	{
		System.out.println("EmailValidatorTest.after()");

		validator = null;
	}

	@Test
	void shouldRejectNullEmail()
	{
		System.out.println("EmailValidatorTest.shouldRejectNullEmail()");

		assertEquals(false, validator.isValidEmail(null));
	}

	@Test
	void shouldRejectWithoutDot()
	{
		System.out.println("EmailValidatorTest.shouldRejectWithoutDot()");

		assertEquals(false, validator.isValidEmail("abc12@gmailcom"));
	}

	//..
	@Test
	void shouldRejectDoubleDots() {
		System.out.println("EmailValidatorTest.shouldRejectDoubleDots()");
		
		assertEquals(false, validator.isValidEmail("abc..12@gmail.com"));
		assertEquals(false, validator.isValidEmail("abc.12.@gmail..com"));
	}

	//ending with .
	@Test
	void shouldRejectEndingDot() {
		System.out.println("EmailValidatorTest.shouldRejectEndingDot()");
		
		assertEquals(false, validator.isValidEmail("abc33@gmail.com."));
	}

	//starting with .
	@Test
	void shouldRejectStartingDot() {
		System.out.println("EmailValidatorTest.shouldRejectStartingDot()");
		
		assertEquals(false, validator.isValidEmail(".abc123@gmail.com"));
	}
	
	// without @
	@Test
	void shouldRejectWithoutAtSymbol()
	{
		System.out.println("EmailValidatorTest.shouldRejectWithoutAtSymbol()");

		assertEquals(false, validator.isValidEmail("xyzgmail.com"));
	}

	// multiple @
	@Test
	void shouldRejectMultipleAtSymbols()
	{
		System.out.println("EmailValidatorTest.shouldRejectMultipleAtSymbols()");

		assertEquals(false, validator.isValidEmail("xyz@@gmail.com"));
		assertEquals(false, validator.isValidEmail("x@yz@gmail.com"));

	}

	// @gmail.com
	@Test
	void shouldRejectEmptyUsername()
	{
		System.out.println("EmailValidatorTest.shouldRejectEmptyUsername()");
		
		assertEquals(false, validator.isValidEmail("@gmail.com"));
		assertEquals(true, validator.isValidEmail("1@gmail.com"));
	}

	@Test
	void shouldRejectEmptyDomain()
	{
		System.out.println("EmailValidatorTest.shouldRejectEmptyDomain()");
		
		assertEquals(false, validator.isValidEmail("john.jh@.com"));
		assertEquals(false, validator.isValidEmail("john.jh@com"));
	}
	
	@Test
	void shouldAcceptValidEmail() {
		System.out.println("EmailValidatorTest.shouldAcceptValidEmail()");
		
		assertEquals(true, validator.isValidEmail("john.jh@gmail.com"));
		assertEquals(true, validator.isValidEmail("123john.jh@gmail.com"));
	}

}