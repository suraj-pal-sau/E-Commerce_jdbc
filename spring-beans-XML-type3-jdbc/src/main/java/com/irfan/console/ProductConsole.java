package com.irfan.console;


import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.irfan.service.ProductService;

public class ProductConsole 
{
	ApplicationContext context = new ClassPathXmlApplicationContext("com/irfan/ioc/bean1.xml");
	
	private Scanner scanner;
//	private Seller seller;
	private ProductService productService = (ProductService) context.getBean("ProductService-object");
//	private final CartService cartService = new CartServiceImpl(); // Added to access cart DB actions
//	private int loggedInCustomerId; // Added to track who is purchasing

	public void setProductService(ProductService productService) {
        this.productService = productService;
    }
	
    public void showMenu()
    {
        
    	while (true) {
			System.out.println("\n--- PRODUCT MANAGEMENT ---");
			System.out.println("1. View Available Products");
			System.out.println("2. Search Products By Category");
			//System.out.println("2. Display Product Details");
			System.out.println("3. Show Available Stock Quantity");
			System.out.println("4. Add Product to Cart 🛒");
			
//			if (seller.getRole().equals("seller")) {
//				System.out.println("5. Insert New Product");
//			}
			
			System.out.println("Return to Main Menu(type 0):");

			System.out.print("Enter Your Choice: ");

			int choice = scanner.nextInt();
			if (choice == 0)
				return;

			switch (choice) {
			case 1:
//				List<Product> products = productService.viewAllAvailableProducts();
				
				System.out.println("\n---------------- AVAILABLE PRODUCTS ----------------");
				productService.viewAllAvailableProducts();
				
				/*
				 * if (products.isEmpty()) { System.out.println("No Products Available."); }
				 * else {
				 * System.out.println("\n---------------- AVAILABLE PRODUCTS ----------------");
				 * for (Product product : products) System.out.println(product); }
				 */
				break;
			case 2:
				System.out.print("Enter Product Category: ");
				String category = scanner.nextLine();
				
				System.out.println("\n----------------PRODUCTS in '" + category + "' Category----------------");
				productService.searchProductsByCategory(category);
				/*
				 * Product product = productService.displayProductDetails(productId); if
				 * (product == null) { System.out.println("Product Not Found."); } else {
				 * System.out.println("\n========== PRODUCT DETAILS ==========\n");
				 * System.out.println("Product ID      : " + product.getProductId());
				 * System.out.println("Product Name    : " + product.getProductName());
				 * System.out.println("Price           : ₹" + product.getPrice());
				 * System.out.println("Stock Quantity  : " + product.getStockQuantity()); }
				 */
				break;
			case 3:
				System.out.println("This feature will be available soon....");
				/*
				 * System.out.print("Enter Product ID: "); productId = scanner.nextLong(); int
				 * stock = productService.showAvailableStock(productId);
				 * System.out.println(stock <= 0 ? "Product Not Found." : "Available Stock: " +
				 * stock);
				 */
				break;

			case 4: // New Add to Cart logic inside Product Management
				System.out.println("This feature will be available soon....");
				/*
				 * System.out.print("Enter Product ID to add: "); int prodId =
				 * scanner.nextInt();
				 * 
				 * // Optional validation check: Verify stock before sending to Cart table int
				 * availableStock = productService.showAvailableStock(prodId); if
				 * (availableStock == -1) {
				 * System.err.println("❌ Error: That Product ID does not exist."); break; }
				 * 
				 * System.out.print("Enter Quantity: "); int qty = scanner.nextInt();
				 * 
				 * if (qty > availableStock) { System.err.println("❌ Insufficient Stock! Only "
				 * + availableStock + " items available."); } else if (qty <= 0) {
				 * System.err.println("❌ Quantity must be greater than 0."); } else { //
				 * Executes your database insertion script automatically
				 * cartService.addProductToCart(loggedInCustomerId, prodId, qty); } break;
				 */

			case 5: // Add Product
				System.out.println("This feature will be available soon....");
				/*
				 * product = new Product();
				 * 
				 * product.setSellerId(seller.getSellerId());
				 * 
				 * System.out.
				 * print("Enter Category ID: (Electronics, Fashion, Grocery, Snacks, Cosmetic,Household)"
				 * ); product.setCategory(scanner.next());
				 * 
				 * System.out.print("Enter Product Name: ");
				 * product.setProductName(scanner.next());
				 * 
				 * System.out.print("Enter Description: ");
				 * product.setDescription(scanner.next());
				 * 
				 * System.out.print("Enter Price: "); product.setPrice(scanner.nextDouble());
				 * 
				 * System.out.print("Enter Stock Quantity: ");
				 * product.setStockQuantity(scanner.nextInt());
				 * 
				 * productService.addProduct(product);
				 * 
				 * System.out.println("✅ Product Added Successfully.");
				 */

				break;

			default:
				System.out.println("Invalid option.");
			}
		}
	
        

    }
}
