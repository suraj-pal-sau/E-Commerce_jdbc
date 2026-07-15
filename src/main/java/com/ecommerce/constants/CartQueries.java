package com.ecommerce.constants;

public class CartQueries {
	
	private CartQueries () {
	
	}
	
	public final static String ADD_PRODUCT_INTO_CART = 
			"INSERT INTO cart(customer_id, product_id, quantity) VALUES(?, ?, ?)";

	public final static String SEARCH_ALL_CART_PROUDCTS_BY_CUSTOMER_ID = 
			"SELECT c.customer_id, c.product_id, p.product_name, p.description, p.category, p.price "
			+ "FROM cart AS c "
			+ "INNER JOIN product p "
			+ " ON c.product_id = p.product_id " 
			+ "WHERE c.customer_id = ?";        
	
	public final static String REMOVE_PRODUCT_FROM_CART = 
			"DELETE FROM cart "
			+ "WHERE customer_id = ? AND product_id = ?";
	
	public final static String FIND_CUSTOMER_ID_IN_CART = 
			"SELECT * FROM cart WHERE customer_id = ?";
	
}
