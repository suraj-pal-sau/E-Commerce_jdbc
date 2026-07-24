package com.irfan.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.irfan.model.Student;
import com.irfan.service.StudentService;

@Configuration
public class ObjectConfig
{
	@Bean("default-const")
	public Student getStudentObject() {
		return new Student();
	}
	
	@Bean("argument-const")
	public Student getArgumentObject() {
		return new Student(102, "Ahmed Raza");
	}
	
	@Bean("service")
	public StudentService getServiceObject() {
		return new StudentService();
	}
}
