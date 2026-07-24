package com.irfan.console;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ConsoleMenu
{
	private final Scanner scanner = new Scanner(System.in);
	ApplicationContext context2 = new ClassPathXmlApplicationContext("com/irfan/console/bean2.xml");

	ProductConsole productConsole = (ProductConsole) context2.getBean("ProductConsole-object");
	
	public void start()
	{
		System.out.println("======================================");
		System.out.println("=============W-E-L-C-O-M-E============");
		System.out.println("======================================");

		// initialize store system layouts
		
		while (true)
		{
			System.out.println("\n=======================================");
			System.out.println("        E-COMMERCE MAIN MENU");
			System.out.println("========================================");
			System.out.println("1. Product Management");
			System.out.println("2. Cart System");
			System.out.println("3. Order & Inventory Management");
			System.out.println("4. Logout & Exit");
			System.out.print("Enter your choice: ");

			int choice = getIntInput();

			switch (choice)
			{
			case 1:
				productConsole.showMenu();
				break;
			case 2:
				System.out.println("This feature will be available soon....");
				break;
			case 3:
				System.out.println("This feature will be available soon....");
				break;
			case 4:
				// System.out.println("Logged out successfully. Thank you!");
				System.out.println("Exiting........... Thank you for using this App");
				scanner.close();
				return;
			default:
				System.out.println("Invalid Choice. Please try again.");
			}
		}
	}

	private int getIntInput()
	{
		while (!scanner.hasNextInt())
		{
			System.out.print("Invalid entry. Enter a valid number: ");
			scanner.next();
		}
		int value = scanner.nextInt();
		scanner.nextLine(); // Clear scanner buffer trailing newline
		return value;
	}
}
