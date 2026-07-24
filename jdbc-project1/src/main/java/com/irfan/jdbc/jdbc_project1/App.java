package com.irfan.jdbc.jdbc_project1;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        EmployeeService employeeService = new EmployeeService();
        try
		{
			//employeeService.getEmployees(null);
			employeeService.getEmployees("IT");
			employeeService.updateEmployeeSalary(104, 5000);

		} catch (ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
