package com.ecommerce.service;

import java.util.List;

import com.ecommerce.model.Order;
import com.ecommerce.model.OrderItem;

public interface OrderService {

    long placeOrder(long userId);

    Order getOrderById(long orderId);

    List<Order> getOrdersByUserId(long userId);

    List<OrderItem> getOrderItems(long orderId);

    boolean updateOrderStatus(long orderId, String status);

    boolean cancelOrder(long orderId, long userId);

    public void viewOrderHistory( long userId);
}