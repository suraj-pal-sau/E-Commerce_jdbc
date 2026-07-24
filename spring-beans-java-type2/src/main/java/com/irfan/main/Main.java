package com.irfan.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.irfan.configuration.ObjectConfig;
import com.irfan.model.Student;
import com.irfan.service.StudentService;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(ObjectConfig.class);
        
// using class name --> NoUniqueBeanDefinitionException: No qualifying bean of type 'com.irfan.model.Student' available: expected single matching bean but found 2: default-const,argument-const
//        Student student = context.getBean(Student.class);
        StudentService service = context.getBean(StudentService.class);
        
        Student student1 = (Student) context.getBean("default-const");
        student1.setRoll(101);
        student1.setName("Azmat Ali");
        service.displayStudent(student1);
        
        Student student2 = (Student) context.getBean("argument-const");
        service.displayStudent(student2);
    }
}
