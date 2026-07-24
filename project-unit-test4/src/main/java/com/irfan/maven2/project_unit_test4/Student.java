package com.irfan.maven2.project_unit_test4;

import java.util.Objects;

public class Student
{
	private int id;
	private String name;
	private int age;
	private String gender;
	private String department;
	private double marks;
	private String email;

	public Student()
	{
	}

	public Student(int id, String name, int age, String gender, String department, double marks, String email)
	{
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.department = department;
		this.marks = marks;
		this.email = email;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public String getDepartment()
	{
		return department;
	}

	public void setDepartment(String department)
	{
		this.department = department;
	}

	public double getMarks()
	{
		return marks;
	}

	public void setMarks(double marks)
	{
		this.marks = marks;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@Override
	public String toString()
	{
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", department="
				+ department + ", marks=" + marks + ", email=" + email + "]";
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(age, department, email, gender, id, marks, name);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return age == other.age && Objects.equals(department, other.department) && Objects.equals(email, other.email)
				&& Objects.equals(gender, other.gender) && id == other.id
				&& Double.doubleToLongBits(marks) == Double.doubleToLongBits(other.marks)
				&& Objects.equals(name, other.name);
	}

}
