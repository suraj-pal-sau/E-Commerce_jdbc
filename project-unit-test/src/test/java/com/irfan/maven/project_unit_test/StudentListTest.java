package com.irfan.maven.project_unit_test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentListTest
{
	StudentList studentList;

	@BeforeEach
	public void setup()
	{
		System.out.println("StudentListTest.setup()...............");
		studentList = new StudentList();
	}

	@Test
	public void addStudentTest()
	{
		System.out.println("StudentListTest.addStudentTest()");

		studentList.addStudent("Irfan");

		boolean expected = true;

		// irfan added or not
		boolean actual = studentList.containsStudent("Irfan");

		assertEquals(expected, actual);
	}

	@Test
	public void removeStudentTest()
	{
		System.out.println("StudentListTest.addStudentTest()");

		studentList.addStudent("Irfan");
		studentList.removeStudent("Irfan");

		boolean expected = false;

		// irfan removed or not
		boolean actual = studentList.containsStudent("Irfan");
		assertEquals(expected, actual);

		// zero student after removing
		int expectedTotal = 0;

		int actualTotal = studentList.getTotalStudents();
		assertEquals(expectedTotal, actualTotal);
	}

	@Test
	public void addStudentTestDuplicatPrevent()
	{
		System.out.println("StudentListTest.addStudentTest()");

		studentList.addStudent("Irfan");
		studentList.addStudent("Irfan");

//		int expected = 1;
//		int actual = studentList.getTotalStudents();

		assertEquals(1, studentList.getTotalStudents());
	}
}
