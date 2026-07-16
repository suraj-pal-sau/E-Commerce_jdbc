package com.ecommerce.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
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
import com.ecommerce.daoimp.CartDaoImpl;
import com.ecommerce.model.CartItem;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @InjectMocks
    private CartDaoImpl cartDao;

    private MockedStatic<DBconfig> mockedDbConfig;

    @BeforeEach
    void setUp() throws SQLException {
        mockedDbConfig = mockStatic(DBconfig.class);
        mockedDbConfig.when(DBconfig::getConnection).thenReturn(connection);
    }

    @AfterEach
    void tearDown() {
        mockedDbConfig.close();
    }

    // =========================================================================
    // FIND PRODUCT BY CUSTOMER ID TESTS
    // =========================================================================

    @Test
    void testFindProductFromCartByCustomerId_ReturnsTrue_WhenProductExists() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);

        boolean result = cartDao.findProductFromCartByCustomerId(101);

        assertTrue(result);
        verify(mockPreparedStatement).setInt(1, 101);
    }

    @Test
    void testFindProductFromCartByCustomerId_ReturnsFalse_WhenEmpty() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        boolean result = cartDao.findProductFromCartByCustomerId(101);

        assertFalse(result);
    }

    // =========================================================================
    // ADD PRODUCT INTO CART DB TESTS (TRANSACTIONS)
    // =========================================================================

    @Test
    void testAddProductIntoCartDB_Success() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        assertDoesNotThrow(() -> cartDao.addProductIntoCartDB(101, 5, 2));

        verify(connection).setAutoCommit(false);
        verify(connection, times(1)).commit();
        verify(connection, never()).rollback();
    }

    @Test
    void testAddProductIntoCartDB_Failure_RollsBack() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(0);

        assertDoesNotThrow(() -> cartDao.addProductIntoCartDB(101, 5, 2));

        verify(connection, times(1)).rollback();
        verify(connection, never()).commit();
    }

    @Test
    void testAddProductIntoCartDB_HandlesRollbackFailureGracefully() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException("Execute failed"));
        doThrow(new SQLException("Rollback failed")).when(connection).rollback();

        assertDoesNotThrow(() -> cartDao.addProductIntoCartDB(101, 5, 2));
        verify(connection, times(1)).rollback();
    }

    // =========================================================================
    // REMOVE PRODUCT FROM CART DB TESTS
    // =========================================================================

    @Test
    void testRemoveProductFromCartDB_Success() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        assertDoesNotThrow(() -> cartDao.removeProductFromCartDB(101, 5));

        verify(connection).commit();
        verify(connection, never()).rollback();
    }

    @Test
    void testRemoveProductFromCartDB_NotFound_RollsBack() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(0);

        assertDoesNotThrow(() -> cartDao.removeProductFromCartDB(101, 5));

        verify(connection).rollback();
        verify(connection, never()).commit();
    }

    // =========================================================================
    // SEARCH ALL CART PRODUCTS TESTS (OUTPUT STREAMS / LOGS)
    // =========================================================================

    @Test
    void testSearchAllCartProduct_ProcessesResultSet() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);

        when(mockResultSet.getInt(1)).thenReturn(101);
        when(mockResultSet.getInt(2)).thenReturn(5);
        when(mockResultSet.getInt(3)).thenReturn(2);
        when(mockResultSet.getString(4)).thenReturn("Smartphone");
        when(mockResultSet.getString(5)).thenReturn("A clean flagship phone");
        when(mockResultSet.getString(6)).thenReturn("Electronics");
        when(mockResultSet.getDouble(7)).thenReturn(15000.00);

        assertDoesNotThrow(() -> cartDao.searchAllCartProduct(101));
    }

    // =========================================================================
    // GET CART ITEMS TESTS (LIST RETRIEVAL)
    // =========================================================================

    @Test
    void testGetCartItems_ReturnsPopulatedList() throws SQLException {
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);

        when(mockResultSet.getInt("cart_id")).thenReturn(50);
        when(mockResultSet.getLong("customer_id")).thenReturn(101L);
        when(mockResultSet.getLong("product_id")).thenReturn(5L);
        when(mockResultSet.getInt("quantity")).thenReturn(2);
        when(mockResultSet.getTimestamp("created_at")).thenReturn(timestamp);

        List<CartItem> items = cartDao.getCartItems(101L);

        assertNotNull(items);
        assertEquals(1, items.size());
        assertEquals(50, items.get(0).getCartId());
        assertEquals(101L, items.get(0).getCustomerId());
    }

    // =========================================================================
    // CLEAR CART TESTS
    // =========================================================================

    @Test
    void testClearCart_Success() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        assertDoesNotThrow(() -> cartDao.clearCart(101L));
        verify(mockPreparedStatement).setLong(1, 101L);
    }

    // =========================================================================
    // FIND SPECIFIC PRODUCT IN CART
    // =========================================================================

    @Test
    void testFindProductFromCart_Success() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);

        boolean result = cartDao.findProductFromCart(101, 5);

        assertTrue(result);
        verify(mockPreparedStatement).setInt(1, 101);
        verify(mockPreparedStatement).setInt(2, 5);
    }

    // =========================================================================
    // UPDATE EXISTING PRODUCT IN CART TESTS (CUSTOM EXCEPTION PATHS)
    // =========================================================================

    @Test
    void testUpdateExistingCartProduct_Success() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        assertDoesNotThrow(() -> cartDao.updateExistingCartProduct(101, 5, 10));

        verify(connection).setAutoCommit(false);
        verify(connection).commit();
        verify(connection, never()).rollback();
    }

    @Test
    void testUpdateExistingCartProduct_NotFound_RollsBack() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        // Returns 0 rows updated, triggering ProductNotFoundException inside implementation
        when(mockPreparedStatement.executeUpdate()).thenReturn(0);

        assertDoesNotThrow(() -> cartDao.updateExistingCartProduct(101, 5, 10));

        verify(connection).rollback();
        verify(connection, never()).commit();
    }
}