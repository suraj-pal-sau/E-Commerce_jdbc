package com.irfan.service;

import org.springframework.stereotype.Component;

import com.irfan.student.beans.Student;

@Component
public class StudentService
{
	public void showStudent(Student student) {
		System.out.println(student);
	}
}
