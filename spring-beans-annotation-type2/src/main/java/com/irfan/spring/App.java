package com.irfan.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.irfan.config.SpringBeanConfig;
import com.irfan.model.Employee;
import com.irfan.service.EmployeeService;

public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringBeanConfig.class);
        
        EmployeeService employeeService = context.getBean(EmployeeService.class);
        
        Employee employee = context.getBean(Employee.class);
        employee.setName("Md Irfan");
        employee.setDepartment("IT");
        employee.setSalary(100000);
        
        employeeService.getEmployee(employee);
        
        Employee employeeNew = employeeService.addEmployee("Azmat Ali", "HR", 120000);
        employeeService.getEmployee(employeeNew);
    }
}
