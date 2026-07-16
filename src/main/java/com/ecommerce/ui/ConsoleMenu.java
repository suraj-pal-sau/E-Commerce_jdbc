package com.ecommerce.ui;

import java.util.Scanner;

import com.ecommerce.model.Seller;

public class ConsoleMenu {

    private final Scanner scanner = new Scanner(System.in);
    private Seller seller;
    private int loggedInCustomerId; 

    public void start() {
        System.out.println("======================================");
        System.out.println("    WELCOME TO THE E-COMMERCE APP");
        System.out.println("======================================");
        
        // Instantiate the isolated Auth UI panel
        AuthConsoleUI authUI = new AuthConsoleUI(scanner);
        
        // Block application entry until auth returns a valid customer account ID number
        seller = authUI.showAuthMenu();
        
        // Break operation cleanly if user picked "Exit Application" on auth screen
        if (this.loggedInCustomerId == -1) {
            scanner.close();
            return;
        }

        // Initialize store system layouts with the verified customer identity
        ProductConsoleUI productUI = new ProductConsoleUI(scanner, seller);
        CartConsoleUI cartUI = new CartConsoleUI(scanner, seller);
        OrderConsoleUI orderUI = new OrderConsoleUI(scanner, seller);

        while (true) {
            System.out.println("\n======================================");
            System.out.println("  E-COMMERCE MAIN MENU (" + 
            	    ("customer".equals(seller.getRole()) ? "User ID: " : "Seller ID: ") 
            	    + seller.getSellerId() + ")");            System.out.println("======================================");
            System.out.println("1. Product Management");
            System.out.println("2. Cart System");
            System.out.println("3. Order & Inventory Management");
            System.out.println("4. Logout & Exit");
            System.out.print("Enter your choice: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    productUI.showMenu();
                    break;
                case 2:
                    cartUI.showMenu();
                    break;
                case 3:
                    orderUI.showMenu();
                    break;
                case 4:
                    System.out.println("Logged out successfully. Thank you!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid Choice. Please try again.");
            }
        }
    }

    private int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid entry. Enter a valid number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // Clear scanner buffer trailing newline
        return value;
    }
}