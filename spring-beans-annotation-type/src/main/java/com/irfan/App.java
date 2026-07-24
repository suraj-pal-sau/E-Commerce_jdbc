package com.irfan;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.irfan.service.StudentService;
import com.irfan.springconfig.SpringConfigAnnotation;
import com.irfan.student.beans.Student;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigAnnotation.class);
        
        StudentService service = context.getBean(StudentService.class);
        Student student1 = context.getBean(Student.class);
        
        service.showStudent(student1);
    }
}
