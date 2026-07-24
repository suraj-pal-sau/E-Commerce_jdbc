package com.irfan.jdbc.jdbc_employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.irfan.data.Data;

public class EmployeeService
{
	private Data data = new Data();

	Connection connection;
	Statement statement;

	/// IIB runs for every object
	{

		try
		{
			// 1) load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2 create connection to the database named --> practice
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice", "root",
					data.getPASSWORD());

			// 3) create a statement
			statement = connection.createStatement();

			// connection.close(); //same exception as below
			// statement.close(); // SQLException --> StatementIsClosedException: No
			// operations allowed after statement closed.

		} catch (ClassNotFoundException | SQLException e) // multi catch
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean addEmployee(Employee e) throws SQLException
	{
		String query = "insert into employee values(" + e.getId() + ", '" + e.getName() + "', " + e.getAge() + ", '"
				+ e.getDepartment() + "', " + e.getSalary() + ", '" + e.getEmail() + "')";
		System.out.println(query);

		int rowAdded = statement.executeUpdate(query);

		return rowAdded > 0;
	}

	public boolean deleteEmployee(int id) throws SQLException
	{
		String query = "delete from employee where id=" + id;
		System.out.println(query);

		int rowDeleted = statement.executeUpdate(query);
		return rowDeleted > 0;
	}

	public List<Employee> getAllEmployeesList() throws SQLException
	{
		List<Employee> employees = new ArrayList<Employee>();
		String query = "select * from employee";
		System.out.println(query);

		ResultSet rs = statement.executeQuery(query);

		while (rs.next())
		{
			Employee emp = new Employee(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5),
					rs.getString(6));

			employees.add(emp);
		}
		return employees;
	}

	public void readAllAttributes(String query) throws SQLException
	{
		ResultSet resultSet = statement.executeQuery(query);

		// Using column names is better because your code won't break if the column
		// order changes.
		while (resultSet.next())
		{
			System.out.println("ID         | " + resultSet.getInt("id"));
			System.out.println("-----------+----------------");

			System.out.println("Name       | " + resultSet.getString("name"));
			System.out.println("-----------+----------------");

			System.out.println("Age        | " + resultSet.getInt("age"));
			System.out.println("-----------+----------------");

			System.out.println("Department | " + resultSet.getString("department"));
			System.out.println("-----------+----------------");

			System.out.println("Salary     | " + resultSet.getDouble("salary"));
			System.out.println("-----------+----------------");

			System.out.println("Email      | " + resultSet.getString("email"));
			System.out.println("____________________________");
		}

	}

	public void updateSalary(int id, double increment) throws SQLException
	{
		String query = "UPDATE Employee SET salary = (salary" + " + " + increment + ") where id=" + id;

		System.out.println();

		System.out.println("Before updating................");
		System.out.println();

		ResultSet resultSet = statement.executeQuery("select id, name, salary from employee where id=" + id);
		if (resultSet.next())	// if because only one row set we are getting --> one employee
		{
			System.out.println("ID         | " + resultSet.getInt("id"));
			System.out.println("-----------+----------------");

			System.out.println("Name       | " + resultSet.getString("name"));
			System.out.println("-----------+----------------");

			System.out.println("Salary     | " + resultSet.getDouble("salary"));
			System.out.println("-----------+----------------");

		}
		System.out.println();

		/// updating salary
		int rowSet = statement.executeUpdate(query);

		System.out.println();

		System.out.println("After updating...............");
		System.out.println();

		ResultSet resultSet2 = statement.executeQuery("select id, name, salary from employee where id=" + id);

		if (resultSet2.next())
		{
			System.out.println("ID         | " + resultSet2.getInt("id"));
			System.out.println("-----------+----------------");

			System.out.println("Name       | " + resultSet2.getString("name"));
			System.out.println("-----------+----------------");

			System.out.println("Salary     | " + resultSet2.getDouble("salary"));
			System.out.println("-----------+----------------");

		}

	}

	public void updateDepartment(int id, String departmnt) throws SQLException
	{
		String query = "UPDATE Employee SET department = '" + departmnt + "' where id=" + id;

		System.out.println();

		System.out.println("Before updating................");
		System.out.println();

		ResultSet resultSet = statement.executeQuery("select id, name, department from employee where id=" + id);
		if (resultSet.next())	
		{
			System.out.println("ID         | " + resultSet.getInt("id"));
			System.out.println("-----------+----------------");

			System.out.println("Name       | " + resultSet.getString("name"));
			System.out.println("-----------+----------------");

			System.out.println("Department | " + resultSet.getString("department"));
			System.out.println("-----------+----------------");

		}
		System.out.println();

		/// updating salary
		int rowSet = statement.executeUpdate(query);

		System.out.println();

		System.out.println("After updating...............");
		System.out.println();

		ResultSet resultSet2 = statement.executeQuery("select id, name, department from employee where id=" + id);

		if (resultSet2.next())
		{
			System.out.println("ID         | " + resultSet2.getInt("id"));
			System.out.println("-----------+----------------");

			System.out.println("Name       | " + resultSet2.getString("name"));
			System.out.println("-----------+----------------");

			System.out.println("Department | " + resultSet2.getString("department"));
			System.out.println("-----------+----------------");

		}

	}

	public void searchEmployeeById(int id) throws SQLException
	{
		String query = "Select * from Employee where id =" + id;

		ResultSet rs = statement.executeQuery(query);

		if (rs.next()) // if because only one row set we are getting --> one employee
		{

			Employee emp = new Employee(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5),
					rs.getString(6));

			System.out.println(emp);

			return;
//			System.out.println("ID         | " + resultSet.getInt("id"));
//			System.out.println("-----------+----------------");
//
//			System.out.println("Name       | " + resultSet.getString("name"));
//			System.out.println("-----------+----------------");
//
//			System.out.println("Age        | " + resultSet.getInt("age"));
//			System.out.println("-----------+----------------");
//
//			System.out.println("Department | " + resultSet.getString("department"));
//			System.out.println("-----------+----------------");
//
//			System.out.println("Salary     | " + resultSet.getDouble("salary"));
//			System.out.println("-----------+----------------");
//
//			System.out.println("Email      | " + resultSet.getString("email"));
//			System.out.println("____________________________");
		}
		System.out.println("Invalid ID   OR   No Employee Found with the ID = " + id);

	}

	public void searchEmployeeByEmail(String email) throws SQLException
	{
		String query = "Select * from Employee where email ='" + email + "'";

		ResultSet rs = statement.executeQuery(query);

		if (rs.next()) // if because only one row set we are getting --> one employee
		{
			Employee emp = new Employee(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5),
					rs.getString(6));

			System.out.println(emp);
			return;
		}
		System.out.println("Invalid Email OR No Employee Found with the email = '" + email + "'");

	}
}
