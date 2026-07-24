package com.irfan.user;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.irfan.user.beans.User;

public class App 
{
    public static void main( String[] args )
    {
        String filePath = "com/irfan/user/beans.xml";
        
        //create IOC container
        ApplicationContext context = new ClassPathXmlApplicationContext(filePath);
        
        //request for bean
        User user = (User) context.getBean("user-object");
        
        user.setUsername("test-user1");
        user.setLocation("Kolkata");
        
        //using the bean
        user.displayUser();
    }
}
