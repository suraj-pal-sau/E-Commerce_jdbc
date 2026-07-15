package com.ecommerce.ui;

import java.util.Scanner;
import com.ecommerce.dao.CartDao;
import com.ecommerce.daoimp.CartDaoImpl;

public class CartConsoleUI {
    private final Scanner scanner;
    private final CartDao cartDao = new CartDaoImpl();
    private final int loggedInCustomerId;

    public CartConsoleUI(Scanner scanner, int customerId) {
        this.scanner = scanner;
        this.loggedInCustomerId = customerId;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n--- CART SYSTEM ---");
            System.out.println("1. View Items In Cart");
            System.out.println("2. Remove Product from Cart");
            System.out.println("3. Return to Main Menu");
            System.out.print("Choice: ");

            int choice = scanner.nextInt();
            if (choice == 3) return;

            switch (choice) {
                case 1:
                    cartDao.searchAllCartProduct(loggedInCustomerId);
                    break;
                case 2:
                    System.out.print("Enter Product ID to Remove from Cart: ");
                    int remProdId = scanner.nextInt();
                    cartDao.removeProductFromCartDB(loggedInCustomerId, remProdId);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
