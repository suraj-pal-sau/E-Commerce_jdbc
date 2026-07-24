package com.irfan.jdbc.jdbc_student;

public class Student
{
	private int id;
	private String name;
	private int age;
	private String department;
	private double marks;
	private String email;
	
	public Student(int id, String name, int age, String department, double marks, String email)
	{
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.department = department;
		this.marks = marks;
		this.email = email;
	}

	public int getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public int getAge()
	{
		return age;
	}

	public String getDepartment()
	{
		return department;
	}

	public double getMarks()
	{
		return marks;
	}

	public String getEmail()
	{
		return email;
	}

	@Override
	public String toString()
	{
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", department=" + department + ", marks="
				+ marks + ", email=" + email + "]";
	}

	
	
	
	
}
