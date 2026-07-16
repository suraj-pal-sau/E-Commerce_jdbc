package com.ecommerce.ui;

import java.util.Objects;
import java.util.Scanner;

import com.ecommerce.model.Seller;
import com.ecommerce.service.UserLogin;
import com.ecommerce.service.UserSignUp;
import com.ecommerce.serviceimpl.ProductServiceImpl;

public class AuthConsoleUI {

	private final Scanner scanner;
	Seller seller;

	public AuthConsoleUI(Scanner scanner) {
		this.scanner = scanner;
	}

	/**
	 * Entry gatekeeper loop. Forces user to log in or register before continuing.
	 * @return a valid logged-in customer ID, or -1 if exiting application.
	 */
	public Seller showAuthMenu() {
		while (true) {
			System.out.println("\n--- CUSTOMER PORTAL ---");
			System.out.println("1. Login (Existing Customer)");
			System.out.println("2. Register (New Customer)");
			System.out.println("3. Exit Application");
			System.out.print("Enter your choice: ");

			int choice = getIntInput();

			switch (choice) {
			case 1:
				return handleLogin();
			case 2:
				handleRegistration();
				break;
			case 3:
				System.out.println("Exiting Application. Goodbye!");
				return null;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}
	}

	private Seller handleLogin() {
		System.out.println("\n--- CUSTOMER LOGIN ---");
		scanner.nextLine();

		System.out.print("Enter Email Address: ");
		String email = scanner.nextLine();

		System.out.print("Enter Password: ");
		String password = scanner.nextLine();

		UserLogin userLogin = new UserLogin();
		seller = userLogin.logIn(email, password);

		if (!Objects.isNull(seller)) {
			System.out.println("✔ Login Successful! Welcome back.");
			return seller;
		} else {
			System.out.println("❌ Invalid Customer ID. Please enter a positive number.");
			return null;
		}

	}

	private void handleRegistration() {
		System.out.println("\n--- NEW CUSTOMER REGISTRATION ---");
		scanner.nextLine(); // Clear the scanner buffer line leftover from getIntInput

		System.out.print("Enter Username: ");
		String name = scanner.nextLine();

		System.out.print("Enter Email Address: ");
		String email = scanner.nextLine();

		System.out.print("Enter Password: ");
		String password = scanner.nextLine();

		System.out.print("Enter Role: ");
		String role = scanner.nextLine();

		UserSignUp user = new UserSignUp();
		user.signUp(name, email, password, role);

		System.out.println("\n✔ Account Created Successfully!");
	}

	private int getIntInput() {
		while (!scanner.hasNextInt()) {
			System.out.print("Invalid choice. Please enter a valid number: ");
			scanner.next();
		}
		int value = scanner.nextInt();
		return value;
	}
}