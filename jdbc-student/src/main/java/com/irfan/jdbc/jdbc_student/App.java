package com.irfan.jdbc.jdbc_student;

import java.sql.SQLException;
import java.util.List;


public class App 
{
    public static void main( String[] args ) throws SQLException
    {
//    	Student s1 = new Student(201, "Amit", 20, "CSE", 91.5, "amit@gmail.com");
//    	Student s2 = new Student(202, "Priya", 21, "ECE", 88.0, "priya@gmail.com");
//    	Student s3 = new Student(203, "Rahul", 22, "MECH", 75.5, "rahul@gmail.com");
//    	Student s4 = new Student(204, "Sneha", 20, "CSE", 95.2, "sneha@gmail.com");
//    	Student s5 = new Student(205, "Arjun", 23, "EEE", 67.4, "arjun@gmail.com");
    	
//    	Student s6 = new Student(206, "Neha", 21, "IT", 84.0, "neha@gmail.com");
//    	Student s7 = new Student(207, "Rohan", 22, "CIVIL", 73.6, "rohan@gmail.com");
//    	Student s8 = new Student(208, "Kiran", 20, "CSE", 89.8, "kiran@gmail.com");
//    	Student s9 = new Student(209, "Megha", 21, "ECE", 81.3, "megha@gmail.com");
//    	Student s10 = new Student(210, "Vijay", 22, "IT", 92.7, "vijay@gmail.com");
    	Student s11 = new Student(211, "Anjali", 20, "CSE", 86.5, "anjali@gmail.com");
    	Student s12 = new Student(212, "Karthik", 22, "IT", 79.0, "karthik@gmail.com");
    	Student s13 = new Student(213, "Pooja", 21, "ECE", 91.8, "pooja@gmail.com");
    	Student s14 = new Student(214, "Rakesh", 23, "MECH", 69.4, "rakesh@gmail.com");
    	Student s15 = new Student(215, "Divya", 20, "CIVIL", 88.9, "divya@gmail.com");

    	Student s16 = new Student(216, "Akash", 22, "EEE", 74.6, "akash@gmail.com");
    	Student s17 = new Student(217, "Nisha", 21, "IT", 82.3, "nisha@gmail.com");
    	Student s18 = new Student(218, "Harish", 23, "CSE", 94.1, "harish@gmail.com");
    	Student s19 = new Student(219, "Lavanya", 20, "ECE", 77.8, "lavanya@gmail.com");
    	Student s20 = new Student(220, "Manoj", 22, "MECH", 65.5, "manoj@gmail.com");
    	
    	
    	//add
    	StudentService service = new StudentService();
//    	System.out.println("isAdded? " + service.addStudent(s11));
    	
    	//search
    	//service.searchStudentById(201);
//    	service.searchStudentsStartsWith("a");
//    	service.searchStudentsEndsWith("a");
    	service.searchStudentsByDepartment("CSE");
    	service.searchStudentsByDepartment("IT");
    	
    	//read
//    	List<Student> list = service.getAllStudentsList();
//		for(Student s : list) {
//			System.out.println(s);
//		}
    }
}
