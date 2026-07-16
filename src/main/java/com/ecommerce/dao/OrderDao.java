package com.ecommerce.dao;

import java.util.List;

import com.ecommerce.model.Order;
import com.ecommerce.model.OrderItem;

public interface OrderDao {

    // Create a new order and return the generated order_id
    long createOrder(Order order);

    // Store a single product in order_items
    boolean createOrderItem(OrderItem orderItem);

    // Get order by order_id
    Order getOrderById(long orderId);

    // Get all orders of a user
    List<Order> getOrdersByUserId(long userId);

    // Get all items of a particular order
    List<OrderItem> getOrderItems(long orderId);

    // Update order status
    boolean updateOrderStatus(long orderId, String status);

    // Cancel order
    boolean cancelOrder(long orderId, long userId);

    public void viewOrderHistoryDetails(long userId);
}