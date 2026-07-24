package com.irfan.student.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component	//this class object(bean) become spring managed bean(object managed by spring framework)
public class Student
{
	//not used in real world name setting
	@Value("Azmat Ali")	//we can set value here which is static  
	private String name;
	
	@Value("Azmat Ali")
	private String departent;
	
	@Value("1001")
	private int roll;

	@Override
	public String toString()
	{
		return "Student [name=" + name + ", departent=" + departent + ", roll=" + roll + "]";
	}
	
	

	
	
	
}
