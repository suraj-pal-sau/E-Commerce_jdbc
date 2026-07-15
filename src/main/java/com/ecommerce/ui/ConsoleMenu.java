package com.ecommerce.ui;

import java.util.Scanner;

public class ConsoleMenu {

    private final Scanner scanner = new Scanner(System.in);
    private int loggedInCustomerId = -1; // Stores who is logged in

    public void start() {
        System.out.println("======================================");
        System.out.println("    WELCOME TO THE E-COMMERCE APP");
        System.out.println("======================================");
        
        // Force login loop before opening the main store features
        while (loggedInCustomerId == -1) {
            handleLogin();
        }

        // Initialize sub-menus with the verified customer identity
        ProductConsoleUI productUI = new ProductConsoleUI(scanner, loggedInCustomerId);
        CartConsoleUI cartUI = new CartConsoleUI(scanner, loggedInCustomerId);
        OrderConsoleUI orderUI = new OrderConsoleUI(scanner, loggedInCustomerId);

        while (true) {
            System.out.println("\n======================================");
            System.out.println("  E-COMMERCE MAIN MENU (User ID: " + loggedInCustomerId + ")");
            System.out.println("======================================");
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

    private void handleLogin() {
        System.out.println("\n--- CUSTOMER LOGIN ---");
        System.out.print("Enter your Customer ID (Simulated Number Verification): ");
        int inputId = getIntInput();

        // TODO: Later on, you can add a UserService validation check here against database:
        // boolean isValid = userService.validateUserExists(inputId);
        
        if (inputId > 0) {
            System.out.println("✔ Login Successful! Welcome back.");
            this.loggedInCustomerId = inputId;
        } else {
            System.out.println("❌ Invalid Customer ID. Please enter a valid number.");
        }
    }

    private int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid entry. Enter a valid number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
