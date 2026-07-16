package com.ecommerce.model;

import java.sql.Timestamp;

public class Seller {

	private int sellerId;
	private String name;
	private String email;
	private String password;
	private String role;
	
	public Seller() {
		
	}

	public Seller(int sellerId, String name, String email, String password, String role) {

		this.sellerId = sellerId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public int getSellerId() {
		return sellerId;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Seller [sellerId=" + sellerId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", role=" + role + "]";
	}
}
