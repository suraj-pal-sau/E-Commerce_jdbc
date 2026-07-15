package com.ecommerce.ui;

import java.util.Scanner;

public class OrderConsoleUI {
    private final Scanner scanner;
    private final int loggedInCustomerId;

    public OrderConsoleUI(Scanner scanner, int customerId) {
        this.scanner = scanner;
        this.loggedInCustomerId = customerId;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n--- ORDER & INVENTORY ---");
            System.out.println("1. Checkout Cart (Place New Order)");
            System.out.println("2. View Order History");
            System.out.println("3. Return to Main Menu");
            System.out.print("Choice: ");

            int choice = scanner.nextInt();
            if (choice == 3) return;

            switch (choice) {
                case 1:
                    System.out.println("[System Logic]: Validating stock for Customer ID " + loggedInCustomerId + "...");
                    System.out.println("Processing inventory reduction... order stored!");
                    break;
                case 2:
                    System.out.println("\n--- PREVIOUS ORDERS FOR CUSTOMER " + loggedInCustomerId + " ---");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
