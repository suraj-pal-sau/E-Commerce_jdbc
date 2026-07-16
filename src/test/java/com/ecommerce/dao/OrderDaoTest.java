package com.ecommerce.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.config.DBconfig;
import com.ecommerce.daoimp.OrderDaoImp;
import com.ecommerce.model.Order;
import com.ecommerce.model.OrderItem;

@ExtendWith(MockitoExtension.class)
public class OrderDaoTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @Mock
    private ResultSet mockGeneratedKeys;

    @InjectMocks
    private OrderDaoImp orderDao;

    private MockedStatic<DBconfig> mockedDbConfig;

    @BeforeEach
    void setUp() throws SQLException {
        // Intercept static calls to DBconfig
        mockedDbConfig = mockStatic(DBconfig.class);
        mockedDbConfig.when(DBconfig::getConnection).thenReturn(connection);
    }

    @AfterEach
    void tearDown() {
        // Release static mock resource after each execution
        mockedDbConfig.close();
    }

    // ==========================================
    // CREATE ORDER TESTS
    // ==========================================

    @Test
    void testCreateOrder_Success() throws SQLException {
        // Arrange
        Order order = new Order();
        order.setUserId(101L);
        order.setTotalAmount(500.00);

        when(connection.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockGeneratedKeys);
        when(mockGeneratedKeys.next()).thenReturn(true);
        when(mockGeneratedKeys.getLong(1)).thenReturn(50021L);

        // Act
        long orderId = orderDao.createOrder(order);

        // Assert
        assertEquals(50021L, orderId);
        verify(connection).setAutoCommit(false);
        verify(mockPreparedStatement).setLong(1, 101L);
        verify(mockPreparedStatement).setDouble(2, 500.00);
        verify(connection, times(1)).commit();
        verify(connection, never()).rollback();
    }

    @Test
    void testCreateOrder_RollbackOnUpdateFailure() throws SQLException {
        // Arrange
        Order order = new Order();
        order.setUserId(101L);
        order.setTotalAmount(150.00);

        when(connection.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(0); // 0 rows affected indicates failure

        // Act
        long orderId = orderDao.createOrder(order);

        // Assert
        assertEquals(-1, orderId);
        verify(connection, times(1)).rollback();
        verify(connection, times(1)).setAutoCommit(true);
        verify(connection, never()).commit();
    }

    @Test
    void testCreateOrder_HandlesExceptionSafely() throws SQLException {
        // Arrange
        Order order = new Order();
        when(connection.prepareStatement(anyString(), anyInt())).thenThrow(new SQLException("DB crash"));

        // Act & Assert
        assertDoesNotThrow(() -> {
            long orderId = orderDao.createOrder(order);
            assertEquals(-1, orderId);
        });
    }

    // ==========================================
    // CREATE ORDER ITEM TESTS
    // ==========================================

    @Test
    void testCreateOrderItem_Success() throws SQLException {
        // Arrange
        OrderItem item = new OrderItem();
        item.setOrderId(50021L);
        item.setProductId(101L);
        item.setQuantity(2);
        item.setPrice(50.00);
        item.setSubtotal(100.00);

        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Act
        boolean result = orderDao.createOrderItem(item);

        // Assert
        assertTrue(result);
        verify(mockPreparedStatement).setLong(1, 50021L);
        verify(mockPreparedStatement).setLong(2, 101L);
        verify(mockPreparedStatement).setInt(3, 2);
        verify(mockPreparedStatement).setDouble(4, 50.00);
        verify(mockPreparedStatement).setDouble(5, 100.00);
        verify(mockPreparedStatement, times(1)).close();
    }

    @Test
    void testCreateOrderItem_Failure() throws SQLException {
        // Arrange
        OrderItem item = new OrderItem();
        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(0);

        // Act
        boolean result = orderDao.createOrderItem(item);

        // Assert
        assertFalse(result);
        verify(mockPreparedStatement, times(1)).close();
    }

    // ==========================================
    // READ BY ID & QUERY LIST TESTS
    // ==========================================

    @Test
    void testGetOrderById_Success() throws SQLException {
        // Arrange
        long orderId = 50021L;
        Timestamp testTimestamp = Timestamp.valueOf(LocalDateTime.now());

        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        
        // Mock fields from Result Set
        when(mockResultSet.getLong("order_id")).thenReturn(orderId);
        when(mockResultSet.getLong("user_id")).thenReturn(101L);
        when(mockResultSet.getTimestamp("order_date")).thenReturn(testTimestamp);
        when(mockResultSet.getDouble("total_amount")).thenReturn(500.00);
        when(mockResultSet.getString("status")).thenReturn("Confirmed");
        when(mockResultSet.getString("payment_status")).thenReturn("Paid");
        when(mockResultSet.getTimestamp("created_at")).thenReturn(testTimestamp);
        when(mockResultSet.getTimestamp("updated_at")).thenReturn(testTimestamp);

        // Act
        Order order = orderDao.getOrderById(orderId);

        // Assert
        assertNotNull(order);
        assertEquals(orderId, order.getOrderId());
        assertEquals(101L, order.getUserId());
        assertEquals("Confirmed", order.getStatus());
        verify(mockResultSet).close();
        verify(mockPreparedStatement).close();
    }

    @Test
    void testGetOrderById_NotFound() throws SQLException {
        // Arrange
        long orderId = 99999L;
        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        // Act
        Order order = orderDao.getOrderById(orderId);

        // Assert
        assertNull(order);
        verify(mockResultSet).close();
        verify(mockPreparedStatement).close();
    }

    @Test
    void testGetOrdersByUserId_HasMultipleOrders() throws SQLException {
        // Arrange
        long userId = 101L;
        Timestamp testTimestamp = Timestamp.valueOf(LocalDateTime.now());

        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false); // Returns 2 items then finishes
        
        when(mockResultSet.getLong("order_id")).thenReturn(50021L, 49811L);
        when(mockResultSet.getLong("user_id")).thenReturn(userId);
        when(mockResultSet.getTimestamp("order_date")).thenReturn(testTimestamp);
        when(mockResultSet.getDouble("total_amount")).thenReturn(500.00, 45.00);
        when(mockResultSet.getString("status")).thenReturn("Confirmed");
        when(mockResultSet.getString("payment_status")).thenReturn("Paid");
        when(mockResultSet.getTimestamp("created_at")).thenReturn(testTimestamp);
        when(mockResultSet.getTimestamp("updated_at")).thenReturn(testTimestamp);

        // Act
        List<Order> orders = orderDao.getOrdersByUserId(userId);

        // Assert
        assertEquals(2, orders.size());
        assertEquals(50021L, orders.get(0).getOrderId());
        assertEquals(49811L, orders.get(1).getOrderId());
    }

    @Test
    void testGetOrderItems_Success() throws SQLException {
        // Arrange
        long orderId = 50021L;
        Timestamp testTimestamp = Timestamp.valueOf(LocalDateTime.now());

        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); // Returns 1 item

        when(mockResultSet.getLong("order_item_id")).thenReturn(1L);
        when(mockResultSet.getLong("order_id")).thenReturn(orderId);
        when(mockResultSet.getLong("product_id")).thenReturn(101L);
        when(mockResultSet.getInt("quantity")).thenReturn(2);
        when(mockResultSet.getDouble("price")).thenReturn(50.00);
        when(mockResultSet.getDouble("subtotal")).thenReturn(100.00);
        when(mockResultSet.getTimestamp("created_at")).thenReturn(testTimestamp);

        // Act
        List<OrderItem> items = orderDao.getOrderItems(orderId);

        // Assert
        assertEquals(1, items.size());
        assertEquals(101L, items.get(0).getProductId());
        assertEquals(2, items.get(0).getQuantity());
    }

    // ==========================================
    // STATUS MODIFICATION & CANCELLATION TESTS
    // ==========================================

    @Test
    void testUpdateOrderStatus_Success() throws SQLException {
        // Arrange
        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Act
        boolean result = orderDao.updateOrderStatus(50021L, "Shipped");

        // Assert
        assertTrue(result);
        verify(mockPreparedStatement).setString(1, "Shipped");
        verify(mockPreparedStatement).setLong(2, 50021L);
    }

    @Test
    void testCancelOrder_Success() throws SQLException {
        // Arrange
        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Act
        boolean result = orderDao.cancelOrder(50021L, 101L);

        // Assert
        assertTrue(result);
        verify(mockPreparedStatement).setLong(1, 50021L);
        verify(mockPreparedStatement).setLong(2, 101L);
    }

    // ==========================================
    // PRINT VIEW DETAILS TESTS (EDGE CASES)
    // ==========================================

    @Test
    void testViewOrderHistoryDetails_ProcessesCorrectly_WhenOrdersExist() throws SQLException {
        // Arrange
        long userId = 101L;
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false); // Mock 2 records (spanning 1 order)

        // Mock columns queried in the raw result set structure
        when(mockResultSet.getLong("order_id")).thenReturn(50021L);
        when(mockResultSet.getTimestamp("created_at")).thenReturn(timestamp);
        when(mockResultSet.getDouble("total_amount")).thenReturn(949.99);
        when(mockResultSet.getLong("product_id")).thenReturn(101L, 102L);
        when(mockResultSet.getString("product_name")).thenReturn("Laptop", "Mouse");
        when(mockResultSet.getInt("quantity")).thenReturn(1, 2);
        when(mockResultSet.getDouble("single_product_price")).thenReturn(899.99, 25.00);
        when(mockResultSet.getDouble("final_price")).thenReturn(899.99, 50.00);

        // Act & Assert
        assertDoesNotThrow(() -> orderDao.viewOrderHistoryDetails(userId));
        verify(mockPreparedStatement).setLong(1, userId);
    }

    @Test
    void testViewOrderHistoryDetails_NoOrdersFound() throws SQLException {
        // Arrange
        long userId = 101L;
        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        // Act & Assert
        assertDoesNotThrow(() -> orderDao.viewOrderHistoryDetails(userId));
    }
}