package com.ecommerce.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ecommerce.dao.OrderDao;
import com.ecommerce.daoimp.OrderDaoImp;
import com.ecommerce.exceptions.EmptyCartException;
import com.ecommerce.exceptions.InsufficientStockException;
import com.ecommerce.exceptions.OrderNotFoundException;
import com.ecommerce.model.CartItem;
import com.ecommerce.model.Order;
import com.ecommerce.model.OrderItem;
import com.ecommerce.model.Product;
import com.ecommerce.service.CartService;
import com.ecommerce.service.OrderService;
import com.ecommerce.service.ProductService;

public class OrderServiceImpl implements OrderService {

	private OrderDao orderDao = new OrderDaoImp();

	private ProductService productService = new ProductServiceImpl();

	private CartService cartService = new CartServiceImpl();

	@Override
	public long placeOrder(long userId) {

		// Step 1: Get Cart Items
		List<CartItem> cartItems = cartService.getCartItems(userId);

		// Step 2: Check Empty Cart
		if (cartItems.isEmpty()) {
			throw new EmptyCartException("Cart is empty. Cannot place order.");
		}

		double totalAmount = 0.0;

		Map<Long, Product> productMap = new HashMap<>();

		// Step 3: Validate Stock & Calculate Total
		for (CartItem cartItem : cartItems) {

			Product product = productService.displayProductDetails(cartItem.getProductId());

			if (product == null) {
				throw new RuntimeException("Product not found.");
			}

			productMap.put(product.getProductId(), product);

			if (product.getStockQuantity() < cartItem.getQuantity()) {
				throw new InsufficientStockException(
						"Insufficient stock for " + product.getProductName());
			}

			// Calculate subtotal
			double subtotal = product.getPrice() * cartItem.getQuantity();

			// Add to total amount
			totalAmount += subtotal;
		}

		// Step 4: Create Order
		Order order = new Order();

		order.setUserId(userId);
		order.setTotalAmount(totalAmount);

		long orderId = orderDao.createOrder(order);

		if (orderId == -1) {
			throw new RuntimeException("Unable to create order.");
		}

		// Step 5: Create Order Items & Reduce Stock
		for (CartItem cartItem : cartItems) {

			Product product = productMap.get(cartItem.getProductId());

			OrderItem orderItem = new OrderItem();

			orderItem.setOrderId(orderId);
			orderItem.setProductId(cartItem.getProductId());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setPrice(product.getPrice());

			// Calculate subtotal
			double subtotal = product.getPrice() * cartItem.getQuantity();

			orderItem.setSubtotal(subtotal);
			
			System.out.println(orderItem);

			boolean itemCreated = orderDao.createOrderItem(orderItem);

			if (!itemCreated) {
				throw new RuntimeException("Unable to create order item.");
			}

			// Reduce Product Stock
			productService.reduceProductStock(
					cartItem.getProductId(),
					cartItem.getQuantity());
		}

		// Step 6: Clear Cart
		cartService.clearCart(userId);

		// Step 7: Return Order Id
		return orderId;
	}

	@Override
	public Order getOrderById(long orderId) {

		try {

			if (orderId <= 0) {
				throw new IllegalArgumentException("Invalid Order ID.");
			}

			Order order = orderDao.getOrderById(orderId);

			if (order == null) {
				throw new OrderNotFoundException(
						"Order not found with ID : " + orderId);
			}


			return order;
		} catch(IllegalArgumentException | OrderNotFoundException e) {
			System.err.println(e.getMessage());
		}

		return null;
	}

	@Override
	public List<Order> getOrdersByUserId(long userId) {

		try {

			if (userId <= 0) {
				throw new IllegalArgumentException("Invalid User ID.");
			}

			List<Order> orders = orderDao.getOrdersByUserId(userId);

			return orders;
		} catch(IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}

		return null;
	}

	@Override
	public List<OrderItem> getOrderItems(long orderId) {

		// Step 1 : Validate Order ID
		if (orderId <= 0) {
			throw new IllegalArgumentException("Invalid Order ID.");
		}

		// Step 2 : Check Order Exists
		Order order = orderDao.getOrderById(orderId);

		if (order == null) {
			throw new OrderNotFoundException(
					"Order not found with ID : " + orderId);
		}

		// Step 3 : Fetch Order Items
		List<OrderItem> orderItems = orderDao.getOrderItems(orderId);

		return orderItems;
	}

	@Override
	public boolean updateOrderStatus(long orderId, String status) {

		// Step 1 : Validate Order ID
		if (orderId <= 0) {
			throw new IllegalArgumentException("Invalid Order ID.");
		}

		// Step 2 : Validate Status
		if (status == null || status.trim().isEmpty()) {
			throw new IllegalArgumentException("Order status cannot be empty.");
		}

		status = status.toUpperCase();

		if (!status.equals("PLACED")
				&& !status.equals("CONFIRMED")
				&& !status.equals("SHIPPED")
				&& !status.equals("DELIVERED")
				&& !status.equals("CANCELLED")) {

			throw new IllegalArgumentException("Invalid Order Status.");
		}

		// Step 3 : Check Order Exists
		Order order = orderDao.getOrderById(orderId);

		if (order == null) {
			throw new OrderNotFoundException(
					"Order not found with ID : " + orderId);
		}

		// Step 4 : Update Status
		return orderDao.updateOrderStatus(orderId, status);
	}

	@Override
	public boolean cancelOrder(long orderId, long userId) {

		// Step 1 : Validate Order ID
		if (orderId <= 0) {
			throw new IllegalArgumentException("Invalid Order ID.");
		}

		// Step 2 : Validate User ID
		if (userId <= 0) {
			throw new IllegalArgumentException("Invalid User ID.");
		}

		// Step 3 : Check Order Exists
		Order order = orderDao.getOrderById(orderId);

		if (order == null) {
			throw new OrderNotFoundException(
					"Order not found with ID : " + orderId);
		}

		// Step 4 : Verify Order Owner
		if (order.getUserId() != userId) {
			throw new IllegalArgumentException(
					"You are not authorized to cancel this order.");
		}

		// Step 5 : Validate Status
		if (order.getStatus().equalsIgnoreCase("DELIVERED")) {
			throw new IllegalStateException(
					"Delivered orders cannot be cancelled.");
		}

		if (order.getStatus().equalsIgnoreCase("CANCELLED")) {
			throw new IllegalStateException(
					"Order is already cancelled.");
		}

		// Step 6 : Cancel Order
		return orderDao.cancelOrder(orderId, userId);
	}
	
	@Override
	public void viewOrderHistory( long userId) {
		
		orderDao.viewOrderHistoryDetails(userId);
		
		
	}

}