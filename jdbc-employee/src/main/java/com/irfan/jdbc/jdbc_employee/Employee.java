package com.irfan.jdbc.jdbc_employee;

public class Employee
{
    private int id;
    private String name;
    private int age;
    private String department;
    private double salary;
    private String email;

    public Employee(int id, String name, int age, String department, double salary, String email)
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
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

    public double getSalary()
    {
        return salary;
    }

    public String getEmail()
    {
        return email;
    }

	@Override
	public String toString()
	{
		return "Employee [id=" + id + ", name=" + name + ", age=" + age + ", department=" + department + ", salary="
				+ salary + ", email=" + email + "]";
	}
    
    
}
