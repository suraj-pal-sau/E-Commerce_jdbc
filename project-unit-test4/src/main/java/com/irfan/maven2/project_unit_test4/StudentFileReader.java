package com.irfan.maven2.project_unit_test4;

import java.io.BufferedReader;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
public class StudentFileReader
{
	public List<Student> readStudents(String filePath){
		List<Student> students = new ArrayList<>();
		
		
		
		try
		{
			BufferedReader fileReader = new BufferedReader(new java.io.FileReader(filePath));
			
			String line;
			while( (line = fileReader.readLine()) != null) {
				String[] data = line.split(",");
				Student student = new Student(
	                    Integer.parseInt(data[0]),
	                    data[1],
	                    Integer.parseInt(data[2]),
	                    data[3],
	                    data[4],
	                    Double.parseDouble(data[5]),
	                    data[6]
	            );
				students.add(student);
				
			}
			
			fileReader.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return students;
	}
}
