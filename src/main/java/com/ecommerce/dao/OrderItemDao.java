package com.ecommerce.dao;

import java.util.List;

import com.ecommerce.model.OrderItem;

public interface OrderItemDao
{
	boolean addOrderItems(List<OrderItem> orderItems);

    List<OrderItem> getOrderItemsByOrderId(long orderId);

    boolean deleteOrderItems(long orderId);
}
