package com.irfan.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.irfan.beans.account.Account;
import com.irfan.springconfig.SpringConfig;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	// create ioc container
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        
        Account account = (Account) context.getBean("createAccount");	//name taken as method name
        
        account.showAccountHolder();
        
       // Account account2 = (Account) context.getBean("acc");	///NoSuchBeanDefinitionException: No bean named 'acc' available
        Account account2 = (Account) context.getBean("acc2");
        account2.showAccountHolder();
    }
}
