package com.ecommerce.model;

import java.time.LocalDateTime;

import com.ecommerce.constants.OrderStatus;
import com.ecommerce.constants.PaymentStatus;


public class Order
{
    private long orderId;
    private long userId;
    private LocalDateTime orderDate;
    private double totalAmount;
    private OrderStatus status;
    private PaymentStatus paymentStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order()
    {

    }

    public Order(long orderId, long userId, LocalDateTime orderDate,
                 double totalAmount, OrderStatus status,
                 PaymentStatus paymentStatus,
                 LocalDateTime createdAt,
                 LocalDateTime updatedAt)
    {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.paymentStatus = paymentStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getOrderId()
    {
        return orderId;
    }

    public void setOrderId(long orderId)
    {
        this.orderId = orderId;
    }

    public long getUserId()
    {
        return userId;
    }

    public void setUserId(long userId)
    {
        this.userId = userId;
    }

    public LocalDateTime getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate)
    {
        this.orderDate = orderDate;
    }

    public double getTotalAmount()
    {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getStatus()
    {
        return status;
    }

    public void setStatus(OrderStatus status)
    {
        this.status = status;
    }

    public PaymentStatus getPaymentStatus()
    {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus)
    {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt)
    {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString()
    {
        return "Order [orderId=" + orderId
                + ", userId=" + userId
                + ", orderDate=" + orderDate
                + ", totalAmount=" + totalAmount
                + ", status=" + status
                + ", paymentStatus=" + paymentStatus
                + "]";
    }

}
