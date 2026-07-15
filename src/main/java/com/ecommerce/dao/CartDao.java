package com.ecommerce.dao;

public interface CartDao {

	public boolean findCustomerByIdFromCart(int customer_id);
	public void addProductIntoCartDB(int customer_id, int product_id, int quantity);
	public void removeProductFromCartDB(int customer_id, int product_id);
	public void searchAllCartProduct(int customer_id);
}
