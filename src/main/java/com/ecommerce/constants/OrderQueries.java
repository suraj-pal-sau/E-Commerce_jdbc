package com.ecommerce.constants;

public class OrderQueries
{
	public static final String INSERT_ORDER = "INSERT INTO orders VALUES (?, ?, ?, ?)";
	public static final String GET_ORDER_BY_ID = "SELECT * FROM orders WHERE order_id = ?";
	public static final String GET_ORDER_BY_USER_ID = "SELECT * FROM orders WHERE user_id = ? ORDER BY order_date DESC";
	public static final String UPDATE_ORDER_STATUS = "UPDATE orders SET status = ? WHERE order_id = ?";
	public static final String CANCEL_ORDER = "UPDATE orders SET status = ? WHERE order_id = ? AND user_id = ?";
}
