package com.irfan.jdbc.jdbc_project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeService
{
	public void getEmployees(String department) throws ClassNotFoundException, SQLException
	{
		// load the driver
		Class.forName("com.mysql.cj.jdbc.Driver"); // checked exception

		// creating connection
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice", "root",
				"irfan@123");

		// create the statement
		Statement statement = connection.createStatement();

		String query = null;
		if (department == null)
		{
			query = "select * from Employee";
		} else
		{
			query = "select * from Employee where department='" + department + "'";
			System.out.println(query);
		}

		// execute query
		ResultSet rs = statement.executeQuery(query);

		while (rs.next())
		{
			int id = rs.getInt(1);
			String name = rs.getString(2);
			int age = rs.getInt(3);
			String departmnt = rs.getString(4);
			int salary = rs.getInt(5);
			String city = rs.getString(6);

			System.out.println("ID         : " + id);
			System.out.println("NAME       : " + name);
			System.out.println("AGE        : " + age);
			System.out.println("DEPARTMENT : " + departmnt);
			System.out.println("SALARY     : " + salary);
			System.out.println("CITY       : " + city);
			System.out.println("----------------------------------------------");
		}

	}

	public void updateEmployeeSalary(int id, int increment) throws ClassNotFoundException, SQLException
	{
		// load the driver
		Class.forName("com.mysql.cj.jdbc.Driver"); // checked exception

		// creating connection
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice", "root",
				"irfan@123");

		// create the statement
		Statement statement = connection.createStatement();
		
		String query = "update Employee set salary = (salary + " + increment + ") where id="+id;
		System.out.println(query);
		
		int numberOfUpdated = statement.executeUpdate(query);
		System.out.println(numberOfUpdated);

		String query1 = "select * from employee where id=" + id;
		ResultSet rs = statement.executeQuery(query1);

		while (rs.next())
		{
			int _id = rs.getInt(1);
			String name = rs.getString(2);
			int age = rs.getInt(3);
			String departmnt = rs.getString(4);
			int salary = rs.getInt(5);
			String city = rs.getString(6);

			System.out.println("ID         : " + _id);
			System.out.println("NAME       : " + name);
			System.out.println("AGE        : " + age);
			System.out.println("DEPARTMENT : " + departmnt);
			System.out.println("SALARY     : " + salary);
			System.out.println("CITY       : " + city);
			System.out.println("----------------------------------------------");
		}
		
	}
}
