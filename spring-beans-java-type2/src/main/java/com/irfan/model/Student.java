package com.irfan.model;

public class Student
{
	private int roll;
	private String name;

	public Student()
	{
		System.out.println("Student object created...default");
	}

	public Student(int roll, String name)
	{
		super();
		this.roll = roll;
		this.name = name;
		System.out.println("Student object created...");

	}

	public int getRoll()
	{
		return roll;
	}

	public String getName()
	{
		return name;
	}

	public void setRoll(int roll)
	{
		this.roll = roll;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return "Student [roll=" + roll + ", name=" + name + "]";
	}

}
