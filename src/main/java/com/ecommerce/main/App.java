package com.ecommerce.main;

import java.util.List;
import java.util.Scanner;

import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;
import com.ecommerce.serviceimpl.ProductServiceImpl;

public class App
{
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		ProductService productService = new ProductServiceImpl();

		while (true) {

			System.out.println("\n======================================");
			System.out.println("      PRODUCT MANAGEMENT");
			System.out.println("======================================");
			System.out.println("1. View Available Products");
			System.out.println("2. Display Product Details");
			System.out.println("3. Show Available Stock Quantity");
			System.out.println("4. Exit");
			System.out.print("Enter your choice : ");

			int choice = scanner.nextInt();

			switch (choice) {

			case 1:
				List<Product> products = productService.viewAvailableProducts();

				if (products.isEmpty()) {

					System.out.println("No Products Available.");

				} else {

					System.out.println("\n---------------- AVAILABLE PRODUCTS ----------------");

					for (Product product : products) {
						System.out.println(product);
					}
				}

				break;

			case 2:

				System.out.print("Enter Product ID : ");

				long productId = scanner.nextLong();

				Product product = productService.displayProductDetails(productId);

				if (product == null) {

					System.out.println("Product Not Found.");

				} else {

					System.out.println("\n========== PRODUCT DETAILS ==========\n");

					System.out.println("Product ID      : " + product.getProductId());
					System.out.println("Seller ID       : " + product.getSellerId());
					System.out.println("Product Name    : " + product.getProductName());
					System.out.println("Description     : " + product.getDescription());
					System.out.println("Category        : " + product.getCategory());
					System.out.println("Price           : ₹" + product.getPrice());
					System.out.println("Stock Quantity  : " + product.getStockQuantity());
					System.out.println("Created At      : " + product.getCreatedAt());
					System.out.println("Updated At      : " + product.getUpdatedAt());
				}

				break;

			case 3:

				System.out.print("Enter Product ID : ");

				productId = scanner.nextLong();

				int stock = productService.showAvailableStock(productId);

				if (stock == -1) {

					System.out.println("Product Not Found.");

				} else {

					System.out.println("Available Stock : " + stock);
				}

				break;

			case 4:

				System.out.println("Thank You!");

				scanner.close();
				return;

			default:

				System.out.println("Invalid Choice.");

			}

		}

	}
}
