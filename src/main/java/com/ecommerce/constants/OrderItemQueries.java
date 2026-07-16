package com.ecommerce.constants;

public class OrderItemQueries
{
	public static final String ADD_ORDER_ITEM = "INSERT INTO order_items "
            + "(order_id, product_id, quantity, price, subtotal) "
            + "VALUES (?, ?, ?, ?, ?)";
	public static final String getOrderItemsByOrderId = "SELECT * FROM order_items WHERE order_id = ?";
	public static final String deleteOrderItems = "DELETE FROM order_items WHERE order_id = ?";
}
