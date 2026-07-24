package com.irfan.model;

import org.springframework.stereotype.Component;

@Component
public class Employee
{
	private String name;
	private String department;
	private int salary;
	
	public Employee() {
		System.out.println("object ==> default constructor");
	}
	
	public Employee(String name, String department, int salary)
	{
		super();
		this.name = name;
		this.department = department;
		this.salary = salary;
	}

	public String getName()
	{
		return name;
	}

	public String getDepartment()
	{
		return department;
	}

	public int getSalary()
	{
		return salary;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setDepartment(String department)
	{
		this.department = department;
	}

	public void setSalary(int salary)
	{
		this.salary = salary;
	}

	@Override
	public String toString()
	{
		return "Employee [name=" + name + ", department=" + department + ", salary=" + salary + "]";
	}
	
	
	
}
