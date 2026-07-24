package com.irfan.student;

public class Student
{
	private String name;
	private String department;
	private int roll;
	
	
	
	/*
	 * public Student(String name, String department, int roll) { super(); this.name
	 * = name; this.department = department; this.roll = roll; }
	 */

	public void setName(String name)
	{
		this.name = name;
	}



	public void setDepartment(String department)
	{
		this.department = department;
	}



	public void setRoll(int roll)
	{
		this.roll = roll;
	}



	@Override
	public String toString()
	{
		return "Student ==> [name = " + name + ", department = " + department + ", roll = " + roll + "]";
	}
	
	
	
}
