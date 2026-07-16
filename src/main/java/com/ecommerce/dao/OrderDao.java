package com.ecommerce.dao;

import java.util.List;

import com.ecommerce.constants.OrderStatus;
import com.ecommerce.model.Order;

public interface OrderDao
{
	long createOrder(Order order);

    Order getOrderById(long orderId);

    List<Order> getOrdersByUserId(long userId);

    boolean updateOrderStatus(long orderId, OrderStatus status);

    boolean cancelOrder(long orderId, long userId);
}
