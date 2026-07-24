package com.irfan.maven.project_unit_test;

import java.util.*;

public class StudentList
{
	List<String> students = new ArrayList<>();
	
	public void addStudent(String name) {
		if(!students.contains(name)) {	//duplicate not allowed
			students.add(name);
		}
	}
	
	public void removeStudent(String name) {
		students.remove(name);
	}
	
	public int getTotalStudents() {
		return students.size();
	}
	
	public List<String> getStudents() {
        return students;
    }

	public boolean containsStudent(String name)
	{
		return students.contains(name);
	}
	
}
