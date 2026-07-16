package com.ecommerce.dao;

import java.util.List;

import com.ecommerce.model.CartItem;

public interface CartDao
{

	public boolean findProductFromCartByCustomerId(int customer_id);

	public void addProductIntoCartDB(int customer_id, int product_id, int quantity);

	public void removeProductFromCartDB(int customer_id, int product_id);

	public void searchAllCartProduct(int customer_id);

	List<CartItem> getCartItems(long customerId);

	void clearCart(long userId);
	
	public boolean findProductFromCart(int customer_id, int product_id);
	
	public void updateExistingCartProduct(int customer_id, int product_id, int quantity);
}
