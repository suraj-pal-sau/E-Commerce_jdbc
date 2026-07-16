package com.ecommerce.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.dao.OrderDao;
import com.ecommerce.exceptions.EmptyCartException;
import com.ecommerce.exceptions.InsufficientStockException;
import com.ecommerce.model.CartItem;
import com.ecommerce.model.Order;
import com.ecommerce.model.OrderItem;
import com.ecommerce.model.Product;
import com.ecommerce.serviceimpl.OrderServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderDao orderDao;

    @Mock
    private ProductService productService;

    @Mock
    private CartService cartService;

    @InjectMocks
    private OrderServiceImpl orderService;

    // =========================================================================
    // PLACE ORDER TESTS
    // =========================================================================

    @Test
    void testPlaceOrder_Success() {
        // Arrange
        long userId = 101L;
        long expectedOrderId = 50021L;

        CartItem item1 = new CartItem();
        item1.setProductId(1L);
        item1.setQuantity(2);

        List<CartItem> cartList = List.of(item1);

        Product mockProduct = new Product();
        mockProduct.setProductId(1L);
        mockProduct.setPrice(100.0);
        mockProduct.setStockQuantity(10);
        mockProduct.setProductName("Test Product");

        when(cartService.getCartItems(userId)).thenReturn(cartList);
        when(productService.displayProductDetails(1L)).thenReturn(mockProduct);
        when(orderDao.createOrder(any(Order.class))).thenReturn(expectedOrderId);
        when(orderDao.createOrderItem(any(OrderItem.class))).thenReturn(true);

        // Act
        long orderId = orderService.placeOrder(userId);

        // Assert
        assertEquals(expectedOrderId, orderId);
        verify(productService).reduceProductStock(1L, 2);
        verify(cartService).clearCart(userId);
    }

    @Test
    void testPlaceOrder_EmptyCart_ThrowsException() {
        // Arrange
        long userId = 101L;
        when(cartService.getCartItems(userId)).thenReturn(Collections.emptyList());

        // Act & Assert
        assertThrows(EmptyCartException.class, () -> orderService.placeOrder(userId));
        verify(orderDao, never()).createOrder(any(Order.class));
    }

    @Test
    void testPlaceOrder_ProductNotFound_ThrowsException() {
        // Arrange
        long userId = 101L;
        CartItem item = new CartItem();
        item.setProductId(999L);
        item.setQuantity(1);

        when(cartService.getCartItems(userId)).thenReturn(List.of(item));
        when(productService.displayProductDetails(999L)).thenReturn(null);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> orderService.placeOrder(userId));
        assertEquals("Product not found.", exception.getMessage());
    }

    @Test
    void testPlaceOrder_InsufficientStock_ThrowsException() {
        // Arrange
        long userId = 101L;
        CartItem item = new CartItem();
        item.setProductId(1L);
        item.setQuantity(5); // Requesting 5

        Product mockProduct = new Product();
        mockProduct.setProductId(1L);
        mockProduct.setStockQuantity(3); // Only 3 in stock
        mockProduct.setProductName("Shortage Product");

        when(cartService.getCartItems(userId)).thenReturn(List.of(item));
        when(productService.displayProductDetails(1L)).thenReturn(mockProduct);

        // Act & Assert
        assertThrows(InsufficientStockException.class, () -> orderService.placeOrder(userId));
    }

    @Test
    void testPlaceOrder_OrderItemCreationFails_ThrowsException() {
        // Arrange
        long userId = 101L;
        CartItem item = new CartItem();
        item.setProductId(1L);
        item.setQuantity(1);

        Product mockProduct = new Product();
        mockProduct.setProductId(1L);
        mockProduct.setPrice(10.0);
        mockProduct.setStockQuantity(5);

        when(cartService.getCartItems(userId)).thenReturn(List.of(item));
        when(productService.displayProductDetails(1L)).thenReturn(mockProduct);
        when(orderDao.createOrder(any(Order.class))).thenReturn(50021L);
        // Fail creation of order item row
        when(orderDao.createOrderItem(any(OrderItem.class))).thenReturn(false);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> orderService.placeOrder(userId));
        assertEquals("Unable to create order item.", exception.getMessage());
        verify(productService, never()).reduceProductStock(anyLong(), anyInt());
    }

    // =========================================================================
    // GET ORDER BY ID TESTS
    // =========================================================================

    @Test
    void testGetOrderById_Success() {
        // Arrange
        long orderId = 50021L;
        Order mockOrder = new Order();
        mockOrder.setOrderId(orderId);

        when(orderDao.getOrderById(orderId)).thenReturn(mockOrder);

        // Act
        Order result = orderService.getOrderById(orderId);

        // Assert
        assertNotNull(result);
        assertEquals(orderId, result.getOrderId());
    }

    @Test
    void testGetOrderById_InvalidId_ReturnsNull() {
        // Act
        Order result = orderService.getOrderById(-1L);

        // Assert
        assertNull(result);
        verify(orderDao, never()).getOrderById(anyLong());
    }

    @Test
    void testGetOrderById_NotFound_ReturnsNull() {
        // Arrange
        long orderId = 99999L;
        when(orderDao.getOrderById(orderId)).thenReturn(null);

        // Act
        Order result = orderService.getOrderById(orderId);

        // Assert
        assertNull(result);
    }

    // =========================================================================
    // UPDATE ORDER STATUS TESTS
    // =========================================================================

    @Test
    void testUpdateOrderStatus_Success() {
        // Arrange
        long orderId = 50021L;
        Order mockOrder = new Order();
        mockOrder.setOrderId(orderId);

        when(orderDao.getOrderById(orderId)).thenReturn(mockOrder);
        when(orderDao.updateOrderStatus(orderId, "SHIPPED")).thenReturn(true);

        // Act
        boolean result = orderService.updateOrderStatus(orderId, "shipped");

        // Assert
        assertTrue(result);
    }

    @Test
    void testUpdateOrderStatus_InvalidStatus_ThrowsException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> 
            orderService.updateOrderStatus(50021L, "INVALID_STATUS")
        );
    }

    // =========================================================================
    // CANCEL ORDER TESTS
    // =========================================================================

    @Test
    void testCancelOrder_Success() {
        // Arrange
        long orderId = 50021L;
        long userId = 101L;

        Order mockOrder = new Order();
        mockOrder.setOrderId(orderId);
        mockOrder.setUserId(userId);
        mockOrder.setStatus("CONFIRMED");

        when(orderDao.getOrderById(orderId)).thenReturn(mockOrder);
        when(orderDao.cancelOrder(orderId, userId)).thenReturn(true);

        // Act
        boolean result = orderService.cancelOrder(orderId, userId);

        // Assert
        assertTrue(result);
    }

    @Test
    void testCancelOrder_UnauthorizedUser_ThrowsException() {
        // Arrange
        long orderId = 50021L;
        long ownerId = 101L;
        long hackerId = 404L;

        Order mockOrder = new Order();
        mockOrder.setOrderId(orderId);
        mockOrder.setUserId(ownerId); // Owned by 101

        when(orderDao.getOrderById(orderId)).thenReturn(mockOrder);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> 
            orderService.cancelOrder(orderId, hackerId)
        );
    }

    @Test
    void testCancelOrder_AlreadyDelivered_ThrowsException() {
        // Arrange
        long orderId = 50021L;
        long userId = 101L;

        Order mockOrder = new Order();
        mockOrder.setOrderId(orderId);
        mockOrder.setUserId(userId);
        mockOrder.setStatus("DELIVERED"); // Completed order

        when(orderDao.getOrderById(orderId)).thenReturn(mockOrder);

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> 
            orderService.cancelOrder(orderId, userId)
        );
    }

    // =========================================================================
    // VIEW ORDER HISTORY DETAILS TESTS
    // =========================================================================

    @Test
    void testViewOrderHistory_DelegatesToDao() {
        // Arrange
        long userId = 101L;

        // Act
        assertDoesNotThrow(() -> orderService.viewOrderHistory(userId));

        // Assert
        verify(orderDao, times(1)).viewOrderHistoryDetails(userId);
    }
}