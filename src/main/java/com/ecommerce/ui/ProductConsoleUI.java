package com.ecommerce.ui;

import java.util.List;
import java.util.Scanner;
import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;
import com.ecommerce.serviceimpl.ProductServiceImpl;
import com.ecommerce.dao.CartDao;
import com.ecommerce.daoimp.CartDaoImpl;

public class ProductConsoleUI {
    private final Scanner scanner;
    private final ProductService productService = new ProductServiceImpl();
    private final CartDao cartDao = new CartDaoImpl(); // Added to access cart DB actions
    private final int loggedInCustomerId;              // Added to track who is purchasing

    // Updated constructor to accept the logged-in user's ID
    public ProductConsoleUI(Scanner scanner, int customerId) {
        this.scanner = scanner;
        this.loggedInCustomerId = customerId;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n--- PRODUCT MANAGEMENT ---");
            System.out.println("1. View Available Products");
            System.out.println("2. Display Product Details");
            System.out.println("3. Show Available Stock Quantity");
            System.out.println("4. Add Product to Cart 🛒"); // New Option Added Here
            System.out.println("5. Return to Main Menu");
            System.out.print("Choice: ");
            
            int choice = scanner.nextInt();
            if (choice == 5) return;

            switch (choice) {
                case 1:
                    List<Product> products = productService.viewAvailableProducts();
                    if (products.isEmpty()) {
                        System.out.println("No Products Available.");
                    } else {
                        System.out.println("\n---------------- AVAILABLE PRODUCTS ----------------");
                        for (Product product : products) System.out.println(product);
                    }
                    break;
                case 2:
                    System.out.print("Enter Product ID: ");
                    long productId = scanner.nextLong();
                    Product product = productService.displayProductDetails(productId);
                    if (product == null) {
                        System.out.println("Product Not Found.");
                    } else {
                        System.out.println("\n========== PRODUCT DETAILS ==========\n");
                        System.out.println("Product ID      : " + product.getProductId());
                        System.out.println("Product Name    : " + product.getProductName());
                        System.out.println("Price           : ₹" + product.getPrice());
                        System.out.println("Stock Quantity  : " + product.getStockQuantity());
                    }
                    break;
                case 3:
                    System.out.print("Enter Product ID: ");
                    productId = scanner.nextLong();
                    int stock = productService.showAvailableStock(productId);
                    System.out.println(stock == -1 ? "Product Not Found." : "Available Stock: " + stock);
                    break;
                    
                case 4: // New Add to Cart logic inside Product Management
                    System.out.print("Enter Product ID to add: ");
                    int prodId = scanner.nextInt();
                    
                    // Optional validation check: Verify stock before sending to Cart table
                    int availableStock = productService.showAvailableStock(prodId);
                    if (availableStock == -1) {
                        System.out.println("❌ Error: That Product ID does not exist.");
                        break;
                    }
                    
                    System.out.print("Enter Quantity: ");
                    int qty = scanner.nextInt();
                    
                    if (qty > availableStock) {
                        System.out.println("❌ Insufficient Stock! Only " + availableStock + " items available.");
                    } else if (qty <= 0) {
                        System.out.println("❌ Quantity must be greater than 0.");
                    } else {
                        // Executes your database insertion script automatically
                        cartDao.addProductIntoCartDB(loggedInCustomerId, prodId, qty);
                    }
                    break;
                    
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
