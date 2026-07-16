package com.ecommerce.constants;

public class CartQueries {
	
	private CartQueries () {
	
	}
	
	public final static String ADD_PRODUCT_INTO_CART = 
			"INSERT INTO cart(customer_id, product_id, quantity) VALUES(?, ?, ?)";

	public final static String SEARCH_ALL_CART_PROUDCTS_BY_CUSTOMER_ID = 
			"SELECT c.customer_id, c.product_id, c.quantity, p.product_name, p.description, p.category, p.price "
			+ "FROM cart AS c "
			+ "INNER JOIN product p "
			+ " ON c.product_id = p.product_id " 
			+ "WHERE c.customer_id = ?";        
	
	public final static String REMOVE_PRODUCT_FROM_CART = 
			"DELETE FROM cart "
			+ "WHERE customer_id = ? AND product_id = ?";
	
	public final static String FIND_PROUDCT_FROM_CART_BUY_CUSTOMER_ID = 
			"SELECT * FROM cart WHERE customer_id = ?";
	
	public static final String GET_CART_ITEMS =
            "SELECT * FROM cart WHERE customer_id = ?";
	
	public static final String CLEAR_CART_ITEMS =
            "DELETE FROM cart WHERE customer_id = ?";
	
	public final static String FIND_PRODUCT_FROM_CART = 
			"SELECT * FROM cart WHERE customer_id = ? AND product_id = ?";
	
	public final static String UPDATE_EXISTING_PROUDCT = 
			"UPDATE cart SET quantity = quantity + ? WHERE customer_id = ? AND product_id = ?";
}
