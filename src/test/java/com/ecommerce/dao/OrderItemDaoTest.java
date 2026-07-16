package com.ecommerce.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.config.DBconfig;
import com.ecommerce.daoimp.OrderItemDaoImp;
import com.ecommerce.model.OrderItem;

@ExtendWith(MockitoExtension.class)
public class OrderItemDaoTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @InjectMocks
    private OrderItemDaoImp orderItemDao;

    private MockedStatic<DBconfig> mockedDbConfig;

    @BeforeEach
    void setUp() throws SQLException {
        // Intercept static calls to DBconfig.getConnection()
        mockedDbConfig = mockStatic(DBconfig.class);
        mockedDbConfig.when(DBconfig::getConnection).thenReturn(connection);
    }

    @AfterEach
    void tearDown() {
        // Safe resource release
        mockedDbConfig.close();
    }

    // =========================================================================
    // ADD ORDER ITEMS (BATCH PROCESSING) TESTS
    // =========================================================================

    @Test
    void testAddOrderItems_Success() throws SQLException {
        // Arrange
        OrderItem item1 = new OrderItem();
        item1.setOrderId(50021L);
        item1.setProductId(1L);
        item1.setQuantity(2);
        item1.setPrice(100.0);
        item1.setSubtotal(200.0);

        OrderItem item2 = new OrderItem();
        item2.setOrderId(50021L);
        item2.setProductId(2L);
        item2.setQuantity(1);
        item2.setPrice(50.0);
        item2.setSubtotal(50.0);

        List<OrderItem> items = List.of(item1, item2);

        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeBatch()).thenReturn(new int[]{1, 1});

        // Act
        boolean result = orderItemDao.addOrderItems(items);

        // Assert
        assertTrue(result);
        
        // Create an InOrder verifier to check the step-by-step execution sequence
        InOrder inOrder = inOrder(mockPreparedStatement);

        // Verify Item 1 is bound and added to batch first
        inOrder.verify(mockPreparedStatement).setLong(1, 50021L);
        inOrder.verify(mockPreparedStatement).setLong(2, 1L);
        inOrder.verify(mockPreparedStatement).setInt(3, 2);
        inOrder.verify(mockPreparedStatement).setDouble(4, 100.0);
        inOrder.verify(mockPreparedStatement).setDouble(5, 200.0);
        inOrder.verify(mockPreparedStatement).addBatch();

        // Verify Item 2 is bound and added to batch second
        inOrder.verify(mockPreparedStatement).setLong(1, 50021L);
        inOrder.verify(mockPreparedStatement).setLong(2, 2L);
        inOrder.verify(mockPreparedStatement).setInt(3, 1);
        inOrder.verify(mockPreparedStatement).setDouble(4, 50.0);
        inOrder.verify(mockPreparedStatement).setDouble(5, 50.0);
        inOrder.verify(mockPreparedStatement).addBatch();

        // Verify the batch execution happens at the very end
        inOrder.verify(mockPreparedStatement).executeBatch();
    }
    @Test
    void testAddOrderItems_ThrowsException_ReturnsFalse() throws SQLException {
        // Arrange
        List<OrderItem> items = List.of(new OrderItem());
        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeBatch()).thenThrow(new SQLException("Batch write failed"));

        // Act
        boolean result = orderItemDao.addOrderItems(items);

        // Assert
        assertFalse(result);
    }

    // =========================================================================
    // GET ORDER ITEMS BY ORDER ID TESTS
    // =========================================================================

    @Test
    void testGetOrderItemsByOrderId_Success() throws SQLException {
        // Arrange
        long orderId = 50021L;
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); // Returns 1 row

        when(mockResultSet.getLong("order_item_id")).thenReturn(10L);
        when(mockResultSet.getLong("order_id")).thenReturn(orderId);
        when(mockResultSet.getLong("product_id")).thenReturn(5L);
        when(mockResultSet.getInt("quantity")).thenReturn(3);
        when(mockResultSet.getDouble("price")).thenReturn(150.0);
        when(mockResultSet.getDouble("subtotal")).thenReturn(450.0);
        when(mockResultSet.getTimestamp("created_at")).thenReturn(timestamp);

        // Act
        List<OrderItem> results = orderItemDao.getOrderItemsByOrderId(orderId);

        // Assert
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals(10L, results.get(0).getOrderItemId());
        assertEquals(orderId, results.get(0).getOrderId());
        assertEquals(5L, results.get(0).getProductId());
        assertEquals(3, results.get(0).getQuantity());
        assertEquals(150.0, results.get(0).getPrice());
        assertEquals(450.0, results.get(0).getSubtotal());
        
        verify(mockPreparedStatement).setLong(1, orderId);
    }

    @Test
    void testGetOrderItemsByOrderId_ThrowsException_ReturnsEmptyList() throws SQLException {
        // Arrange
        long orderId = 50021L;
        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException("Query interrupted"));

        // Act
        List<OrderItem> results = orderItemDao.getOrderItemsByOrderId(orderId);

        // Assert
        assertNotNull(results);
        assertTrue(results.isEmpty());
    }

    // =========================================================================
    // DELETE ORDER ITEMS TESTS
    // =========================================================================

    @Test
    void testDeleteOrderItems_Success() throws SQLException {
        // Arrange
        long orderId = 50021L;
        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(3); // Simulates 3 items removed

        // Act
        boolean result = orderItemDao.deleteOrderItems(orderId);

        // Assert
        assertTrue(result);
        verify(mockPreparedStatement).setLong(1, orderId);
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void testDeleteOrderItems_NoRowsDeleted() throws SQLException {
        // Arrange
        long orderId = 99999L;
        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(0); // No items were deleted

        // Act
        boolean result = orderItemDao.deleteOrderItems(orderId);

        // Assert
        assertFalse(result);
    }

    @Test
    void testDeleteOrderItems_ThrowsException_ReturnsFalse() throws SQLException {
        // Arrange
        long orderId = 50021L;
        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException("Lock wait timeout exceeded"));

        // Act
        boolean result = orderItemDao.deleteOrderItems(orderId);

        // Assert
        assertFalse(result);
    }
}