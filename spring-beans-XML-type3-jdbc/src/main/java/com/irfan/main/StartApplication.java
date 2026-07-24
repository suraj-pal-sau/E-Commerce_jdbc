package com.irfan.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.irfan.console.ConsoleMenu;

public class StartApplication
{

	@SuppressWarnings("resource")
	public static void main(String[] args)
	{
		
		ApplicationContext context = new ClassPathXmlApplicationContext("com/irfan/console/bean2.xml");
		ConsoleMenu menu =  (ConsoleMenu) context.getBean("ConsoleMenu-object");
		menu.start();
	}

}
