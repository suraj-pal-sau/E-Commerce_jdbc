package com.ecommerce.cartdaoimpltest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.daoimp.CartDaoImpl;

@ExtendWith(MockitoExtension.class)
public class CartDaoImplTest {
	  // Mockito will inject these mock objects directly into the connection field below
    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    // InjectMocks automatically bypasses the constructor and inserts the mocks into the fields
    @InjectMocks
    private CartDaoImpl cartDao;

    @Test
    void testFindCustomerByIdFromCart_ReturnsTrue_WhenUserExists() throws SQLException {
        // Arrange
        int customerId = 101;
        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true); 

        // Act
        boolean result = cartDao.findCustomerByIdFromCart(customerId);

        // Assert
        assertTrue(result);
        verify(mockPreparedStatement).setInt(1, customerId);
        verify(mockPreparedStatement, times(1)).executeQuery();
    }

    @Test
    void testFindCustomerByIdFromCart_ReturnsFalse_WhenUserDoesNotExist() throws SQLException {
        // Arrange
        int customerId = 999;
        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false); 

        // Act
        boolean result = cartDao.findCustomerByIdFromCart(customerId);

        // Assert
        assertFalse(result);
    }

    @Test
    void testAddProductIntoCartDB_CommitsOnSuccess() throws SQLException {
        // Arrange
        int customerId = 101;
        int productId = 5;
        int quantity = 2;

        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1); // 1 row updated means success

        // Act
        cartDao.addProductIntoCartDB(customerId, productId, quantity);

        // Assert
        verify(connection).setAutoCommit(false);
        verify(mockPreparedStatement).setInt(1, customerId);
        verify(mockPreparedStatement).setInt(2, productId);
        verify(mockPreparedStatement).setInt(3, quantity);
        verify(connection, times(1)).commit(); 
        verify(connection, never()).rollback(); 
    }

    @Test
    void testAddProductIntoCartDB_RollsBackOnFailure() throws SQLException {
        // Arrange
        int customerId = 101;
        int productId = 5;
        int quantity = 2;

        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(0); // 0 rows updated means failure

        // Act
        cartDao.addProductIntoCartDB(customerId, productId, quantity);

        // Assert
        verify(connection, times(1)).rollback(); 
        verify(connection, never()).commit();
    }

    @Test
    void testRemoveProductFromCartDB_CommitsOnSuccess() throws SQLException {
        // Arrange
        int customerId = 101;
        int productId = 5;

        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1); 

        // Act
        cartDao.removeProductFromCartDB(customerId, productId);

        // Assert
        verify(connection).setAutoCommit(false);
        verify(connection, times(1)).commit();
        verify(connection, never()).rollback();
    }

    @Test
    void testSearchAllCartProduct_ProcessesResultSetRows() throws SQLException {
        // Arrange
        int customerId = 101;
        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        
        // Loop runs twice, then returns false to exit
        when(mockResultSet.next()).thenReturn(true, true, false); 
        when(mockResultSet.getInt(1)).thenReturn(customerId);
        when(mockResultSet.getInt(2)).thenReturn(10, 11);
        when(mockResultSet.getString(3)).thenReturn("Product A", "Product B");

        // Act & Assert
        assertDoesNotThrow(() -> cartDao.searchAllCartProduct(customerId));
        verify(mockPreparedStatement).setInt(1, customerId);
        verify(mockResultSet, times(3)).next(); 
    }
    
    
    // Edge Cases
    
    @Test
    void testFindCustomerByIdFromCart_HandlesDatabaseCrash() throws SQLException {
        // Arrange
        int customerId = 101;
        // Simulate the driver breaking when prepareStatement is triggered
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database connection dropped unexpectedly"));

        // Act
        boolean result = cartDao.findCustomerByIdFromCart(customerId);

        // Assert
        assertFalse(result); // Should safely catch exception and return false
    }
    
    
    @Test
    void testAddProductIntoCartDB_HandlesRollbackFailure() throws SQLException {
        // Arrange
        int customerId = 101;
        int productId = 5;
        int quantity = 2;

        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException("Query execution failed"));
        
        // Simulate the rollback failing too because the connection is broken
        doThrow(new SQLException("Network dead, rollback impossible")).when(connection).rollback();

        // Act & Assert
        // This ensures your nested catch block doesn't let the application crash out
        assertDoesNotThrow(() -> cartDao.addProductIntoCartDB(customerId, productId, quantity));
        verify(connection, times(1)).rollback();
    }


    @Test
    void testAddProductIntoCartDB_WhenRowsReturnedIsZero() throws SQLException {
        // Arrange
        int customerId = 101;
        int productId = 5;
        int quantity = -10; // Bad quantity input bound

        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(0); // Database rejected the insert

        // Act
        cartDao.addProductIntoCartDB(customerId, productId, quantity);

        // Assert
        verify(connection, times(1)).rollback(); // Must execute rollback safely
        verify(connection, never()).commit();
    }

    
    @Test
    void testSearchAllCartProduct_WhenCartIsEmpty() throws SQLException {
        // Arrange
        int customerId = 202;
        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        
        // Instantly return false on the very first read attempt
        when(mockResultSet.next()).thenReturn(false); 

        // Act & Assert
        assertDoesNotThrow(() -> cartDao.searchAllCartProduct(customerId));
        verify(mockResultSet, times(1)).next();
        verify(mockResultSet, never()).getInt(anyInt()); // Ensure no processing was attempted
    }

}
