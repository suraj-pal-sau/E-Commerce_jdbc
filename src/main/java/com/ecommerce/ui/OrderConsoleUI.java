package com.ecommerce.ui;

import java.util.Scanner;

import com.ecommerce.model.Seller;
import com.ecommerce.service.OrderService;
import com.ecommerce.serviceimpl.OrderServiceImpl;

public class OrderConsoleUI {
    private final Scanner scanner;
    private Seller seller;
    private final int loggedInCustomerId;
    OrderService orderService = new OrderServiceImpl();

    public OrderConsoleUI(Scanner scanner, Seller seller) {
        this.scanner = scanner;
        this.seller = seller;
        this.loggedInCustomerId = seller.getSellerId();
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
                	long orderId = orderService.placeOrder(loggedInCustomerId);
                	
                	System.out.println("Order Placed Successfully");
                	System.out.println("Track your Order using OrderID: " + orderId);
                    break;
                case 2:
//                    System.out.println("\n--- PREVIOUS ORDERS FOR CUSTOMER " + loggedInCustomerId + " ---");
                    orderService.viewOrderHistory(loggedInCustomerId);
                	break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
