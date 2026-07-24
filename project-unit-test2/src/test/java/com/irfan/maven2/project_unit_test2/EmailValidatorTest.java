package com.irfan.maven2.project_unit_test2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmailValidatorTest
{
	EmailValidator emailValidator;

	@BeforeEach
	public void before()
	{
		System.out.println("EmailValidatorTest.before()");

		emailValidator = new EmailValidator();
	}

	@Test // for valid
	public void isEmailValidTest()
	{
		System.out.println("EmailValidatorTest.isEmailValidTest()");

		boolean expected = true;
		boolean actual = emailValidator.isEmailValid("abc1d33@gmail.com");
		assertEquals(expected, actual);
	}

	// for missing @
	@Test
	public void isEmailValidTestWithMissing()
	{
		System.out.println("EmailValidatorTest.isEmailValidTestWithMissing()");

		boolean expected = false;

		assertEquals(expected, emailValidator.isEmailValid("abcdgmail.com"));
	}

	// for missing .com
	@Test
	public void isEmailValidTestWithMissingDotCom()
	{
		System.out.println("EmailValidatorTest.isEmailValidTestWithMissingCom()");

		boolean expected = false;

		assertEquals(expected, emailValidator.isEmailValid("abc.d123@gmail"));
	}

	@Test
	public void isEmailValidTestWithEmptyString()
	{
		System.out.println("EmailValidatorTest.isEmailValidTestWithEmptyString()");
		boolean expected = false;

		assertEquals(expected, emailValidator.isEmailValid(""));
	}

	@Test
	public void isEmailValidTestWithNull()
	{
		System.out.println("EmailValidatorTest.isEmailValidTestWithNull()");
		boolean expected = false;

		assertEquals(expected, emailValidator.isEmailValid(null));
	}
	
	@Test
    void testInvalidEmailFormat() {
		System.out.println("EmailValidatorTest.testInvalidEmailFormat()");
		
		boolean expected = false;
		
		
        assertEquals(expected, emailValidator.isEmailValid("john@@gmail.com"));
    }
}
