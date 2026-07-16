package com.ecommerce.model;

import java.time.LocalDateTime;

public class OrderItem
{
    private long orderItemId;
    private long orderId;
    private long productId;
    private int quantity;
    private double price;
    private double subtotal;
    private LocalDateTime createdAt;

    public OrderItem()
    {

    }

    public OrderItem(long orderItemId,
                     long orderId,
                     long productId,
                     int quantity,
                     double price,
                     double subtotal,
                     LocalDateTime createdAt)
    {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = subtotal;
        this.createdAt = createdAt;
    }

    public long getOrderItemId()
    {
        return orderItemId;
    }

    public void setOrderItemId(long orderItemId)
    {
        this.orderItemId = orderItemId;
    }

    public long getOrderId()
    {
        return orderId;
    }

    public void setOrderId(long orderId)
    {
        this.orderId = orderId;
    }

    public long getProductId()
    {
        return productId;
    }

    public void setProductId(long productId)
    {
        this.productId = productId;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public double getSubtotal()
    {
        return subtotal;
    }

    public void setSubtotal(double subtotal)
    {
        this.subtotal = subtotal;
    }

    public LocalDateTime getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt)
    {
        this.createdAt = createdAt;
    }

    @Override
    public String toString()
    {
        return "OrderItem [orderItemId=" + orderItemId
                + ", orderId=" + orderId
                + ", productId=" + productId
                + ", quantity=" + quantity
                + ", price=" + price
                + ", subtotal=" + subtotal
                + "]";
    }

}
