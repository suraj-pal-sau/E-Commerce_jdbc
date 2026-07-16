package com.ecommerce.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.config.DBconfig;
import com.ecommerce.model.Seller;

@ExtendWith(MockitoExtension.class)
public class UserDaoTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @InjectMocks
    private UserDao userDao;

    private MockedStatic<DBconfig> mockedDbConfig;

    @BeforeEach
    void setUp() throws SQLException {
        // Intercept static calls to DBconfig.getConnection()
        mockedDbConfig = mockStatic(DBconfig.class);
        mockedDbConfig.when(DBconfig::getConnection).thenReturn(connection);
    }

    @AfterEach
    void tearDown() {
        // Release static mock resource after each test
        mockedDbConfig.close();
    }

    // ==========================================
    // SIGN UP TESTS
    // ==========================================

    @Test
    void testSignUp_Success() throws SQLException {
        // Arrange
        String username = "john_doe";
        String email = "john@example.com";
        String password = "securePassword";
        String role = "SELLER";

        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1); // 1 row inserted

        // Act
        boolean result = userDao.signUp(username, email, password, role);

        // Assert
        assertTrue(result);
        verify(mockPreparedStatement).setString(1, username);
        verify(mockPreparedStatement).setString(2, email);
        verify(mockPreparedStatement).setString(3, password);
        verify(mockPreparedStatement).setString(4, role);
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void testSignUp_Failure_NoRowsAffected() throws SQLException {
        // Arrange
        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(0); // 0 rows inserted indicates failure

        // Act
        boolean result = userDao.signUp("user", "email@test.com", "pass", "BUYER");

        // Assert
        assertFalse(result);
    }

    @Test
    void testSignUp_HandlesExceptionSafely() throws SQLException {
        // Arrange
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database connection timed out"));

        // Act
        boolean result = userDao.signUp("user", "email@test.com", "pass", "BUYER");

        // Assert
        assertFalse(result); // Should print trace and return false instead of throwing
    }

    // ==========================================
    // LOG IN TESTS
    // ==========================================

    @Test
    void testLogIn_Success() throws SQLException {
        // Arrange
        String email = "seller@store.com";
        String password = "password123";

        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); // Return true once to parse, then false to stop

        // Map mock ResultSet data to indices parsed by your implementation (1, 2, 3, 4, 6)
        when(mockResultSet.getInt(1)).thenReturn(402);
        when(mockResultSet.getString(2)).thenReturn("SellerName");
        when(mockResultSet.getString(3)).thenReturn(email);
        when(mockResultSet.getString(4)).thenReturn(password);
        when(mockResultSet.getString(6)).thenReturn("SELLER");

        // Act
        Seller seller = userDao.logIn(email, password);

        // Assert
        assertNotNull(seller);
        assertEquals(402, seller.getSellerId()); // Matches expected constructor assignments
        assertEquals("SellerName", seller.getName());
        assertEquals(email, seller.getEmail());
        assertEquals(password, seller.getPassword());
        assertEquals("SELLER", seller.getRole());

        verify(mockPreparedStatement).setString(1, email);
        verify(mockPreparedStatement).setString(2, password);
    }

    @Test
    void testLogIn_UserNotFound() throws SQLException {
        // Arrange
        String email = "unknown@store.com";
        String password = "wrongPassword";

        when(connection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false); // No records found matching credentials

        // Act
        Seller seller = userDao.logIn(email, password);

        // Assert
        assertNull(seller);
    }

    @Test
    void testLogIn_HandlesExceptionSafely() throws SQLException {
        // Arrange
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("DB Connection Broken"));

        // Act
        Seller seller = userDao.logIn("test@test.com", "pass");

        // Assert
        assertNull(seller); // Should catch SQLException, print log, and return null
    }
}