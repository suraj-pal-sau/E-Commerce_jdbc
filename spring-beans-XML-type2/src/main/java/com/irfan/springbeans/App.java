package com.irfan.springbeans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.irfan.service.StudentService;
import com.irfan.student.Student;

public class App 
{
    public static void main( String[] args )
    {
        String path = "com/irfan/resources/bean.xml";
        
        //create IOC container
        ApplicationContext context = new ClassPathXmlApplicationContext(path);
        
        Student student = (Student) context.getBean("student-object");
        student.setName("Saqib Iqbal");
        student.setDepartment("CIVIL");
        student.setRoll(1005);
        
        
        Student student2 = (Student) context.getBean("student2-object");
        student2.setName("Nadeem Mukhtar");
        student2.setDepartment("CSE");
        student2.setRoll(1001);

        
        System.out.println();
        
        StudentService service = (StudentService) context.getBean("service-object");
       
        service.getStudent(student);
        service.getStudent(student2);
    }
}
