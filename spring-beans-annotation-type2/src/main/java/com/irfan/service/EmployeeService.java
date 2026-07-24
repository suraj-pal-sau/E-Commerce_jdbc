package com.irfan.service;

import org.springframework.stereotype.Component;

import com.irfan.model.Employee;

@Component
public class EmployeeService
{
	public void getEmployee(Employee e)
	{
		System.out.println(e);
	}

	public Employee addEmployee(String name, String department, int salary)
	{

		return new Employee(name, department, salary);

	}
}
