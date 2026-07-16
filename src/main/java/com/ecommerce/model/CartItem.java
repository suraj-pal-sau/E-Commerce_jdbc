package com.ecommerce.model;

import java.time.LocalDateTime;

public class CartItem {

    private int cartId;
    private long customerId;
    private long productId;
    private int quantity;
    private LocalDateTime createdAt;

    public CartItem() {
    }

    public CartItem(long customerId, long productId, int quantity) {
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

	@Override
	public String toString() {
		return "CartItem [cartId=" + cartId + ", customerId=" + customerId + ", productId=" + productId + ", quantity="
				+ quantity + ", createdAt=" + createdAt + "]";
	}
}