package com.irfan.jdbc.jdbc_employee;

import java.sql.SQLException;
import java.util.List;

public class App
{
	public static void main(String[] args) throws SQLException
	{
		Employee emp1 = new Employee(111, "Karan", 27, "IT", 78000, "karan@gmail.com");
		Employee emp2 = new Employee(112, "Pooja", 29, "HR", 52000, "pooja@gmail.com");
		Employee emp3 = new Employee(113, "Arjun", 24, "Finance", 61000, "arjun@gmail.com");
		Employee emp4 = new Employee(114, "Megha", 30, "Marketing", 58000, "megha@gmail.com");
		Employee emp5 = new Employee(115, "Amit", 33, "IT", 105000, "amit@gmail.com");

		Employee emp6 = new Employee(120, "Imran", 26, "IT", 65000, "irfan@gmail.com");
	

		EmployeeService employeeService = new EmployeeService();
		///write
		 System.out.println(" isAdded ? " + employeeService.addEmployee(emp6));

//		System.out.println(" isDeleted ? " + employeeService.deleteEmployee(102));
//		System.out.println(" isDeleted ? " + employeeService.deleteEmployee(105));
		
		
		///read
		List<Employee> list = employeeService.getAllEmployeesList();
		for(Employee emp : list) {
			System.out.println(emp);
		}
		
		System.out.println();
		
		
		//String query = "select * from employee where department in ('IT', 'HR')";
		//String query = "select * from employee where department not in ('Sales', 'HR')";
		//String query = " select * from employee where department <>'hr' and department!='IT'";	// !='hr', <>'hr', NOT department = 'hr'   ---> same
		//String query = " select * from employee where age in (25, 26, 28)";
		//String query = " select * from employee where age BETWEEN 26 AND 28";	//inclusive --> 26 and 28
//		String query = " select * from employee where salary BETWEEN 50000 AND 65000";
		String query = " select * from employee where (salary BETWEEN 50000 AND 65000) AND department IN ('HR', 'IT')";
		
		employeeService.readAllAttributes(query);
		
		///update
		//employeeService.updateSalary(102, 2500);
		employeeService.updateDepartment(103, "IT");
		
		
		///read ---> searching
		employeeService.searchEmployeeById(105);	//valid id
		employeeService.searchEmployeeById(300);	//invalid id
		
		//name@gmail.com
		employeeService.searchEmployeeByEmail("priya@gmail.com");	// valid email
		employeeService.searchEmployeeByEmail("ritesh@gmail.com");	// invalid email

		
		
	}
}
