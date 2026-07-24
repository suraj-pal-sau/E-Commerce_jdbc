package com.irfan.jdbc.jdbc_student;
/**
 * Q: Why is resultSet.next() required before calling getInt() or getString()?

 Answer: Because when a ResultSet is created, its cursor is positioned before the first row.
 		Calling next() moves the cursor to the first row.
 		Without doing that, there is no current row to read from, 
 		and calling methods like getInt() or getString() will throw an exception  --> SQLException: Before start of result set
 */
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.irfan.data.Data;

public class StudentService
{
	private Data data = new Data();
	Connection connection;

	Statement statement;

	// IIB
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice", "root",
					data.getPassword());

			statement = connection.createStatement();

		} catch (ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean addStudent(Student s) throws SQLException
	{
		String query = "INSERT INTO Student VALUES (" + s.getId() + ", '" + s.getName() + "', " + s.getAge() + ", '"
				+ s.getDepartment() + "', " + s.getMarks() + ", '" + s.getEmail() + "')";
		System.out.println(query);

		int rowAdded = statement.executeUpdate(query);
		return rowAdded > 0;
	}

	public List<Student> getAllStudentsList() throws SQLException
	{
		List<Student> students = new ArrayList<>();
		String query = "SELECT * FROM Student";
		System.out.println(query);

		ResultSet rs = statement.executeQuery(query);
		while (rs.next())
		{
			Student student = new Student(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getDouble(5),
					rs.getString(6));
			students.add(student);
		}

		return students;
	}

	public boolean deleteStudent(int id) throws SQLException
	{
		String query = "DELETE FROM Student WHERE id=" + id;
		System.out.println(query);

		int rowDeleted = statement.executeUpdate(query);
		return rowDeleted > 0;
	}

	public void searchStudentById(int id) throws SQLException
	{
		String query = "SELECT * FROM Student WHERE id =" + id;
		System.out.println(query);

		ResultSet rs = statement.executeQuery(query);

		if (rs.next())
		{

			Student emp = new Student(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getDouble(5),
					rs.getString(6));

			System.out.println(emp);

			return;
		}
		System.out.println(" No Student Found with the ID = " + id);
	}

	public void searchStudentsStartsWith(String seq) throws SQLException
	{
		String query = "SELECT * FROM Student WHERE name LIKE '" + seq + "%'";
		System.out.println(query);

		ResultSet rs = statement.executeQuery(query);

		while (rs.next())
		{
			Student student = new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age"),
					rs.getString("department"), rs.getDouble("marks"), rs.getString("email"));
			System.out.println(student);
		}

	}

	public void searchStudentsEndsWith(String seq) throws SQLException
	{
		String query = "SELECT * FROM Student WHERE name LIKE '%" + seq + "'";
		System.out.println(query);

		ResultSet rs = statement.executeQuery(query);

		while (rs.next())
		{
			Student student = new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age"),
					rs.getString("department"), rs.getDouble("marks"), rs.getString("email"));
			System.out.println(student);
		}

	}

	public void searchStudentsByDepartment(String departmnt) throws SQLException
	{
		String query = "SELECT * FROM Student WHERE department IN ('" + departmnt + "')";
		System.out.println(query);

		ResultSet rs = statement.executeQuery(query);
//		System.out.println(rs.getString(1));  // SQLException: Before start of result set	
		while (rs.next())
		{
			Student student = new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age"),
					rs.getString("department"), rs.getDouble("marks"), rs.getString("email"));
			System.out.println(student);
		}
	}
}
