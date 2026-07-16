package com.ecommerce.exceptions;

public class OrderNotUpdateException extends RuntimeException {
	public OrderNotUpdateException(String message) {
        super(message);
    }
}
