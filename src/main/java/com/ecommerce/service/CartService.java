package com.ecommerce.service;

public interface CartService {
	
	public void addProductToCart(int customer_id, int product_id, int quantity);
	
	public void removeProductToCart(int customer_id, int product_id);
	
	public void viewCartProducts(int customer_id);
}
