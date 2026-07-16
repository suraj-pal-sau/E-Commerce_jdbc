package com.ecommerce.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.dao.UserDao;
import com.ecommerce.model.Seller;

@ExtendWith(MockitoExtension.class)
public class UserLoginTest {

    @Mock
    private UserDao userDao; // Mocking the corrected UserDao dependency

    @InjectMocks
    private UserLogin userLogin;

    // ==========================================
    // VALIDATION TESTS (INPUT EDGE CASES)
    // ==========================================

    @Test
    void testLogIn_NullEmail_ReturnsNullImmediately() {
        // Act
        Seller result = userLogin.logIn(null, "securePassword");

        // Assert
        assertNull(result);
        // Verify we never called the database layer because validation caught it first
        verify(userDao, never()).logIn(anyString(), anyString());
    }

    @Test
    void testLogIn_NullPassword_ReturnsNullImmediately() {
        // Act
        Seller result = userLogin.logIn("user@example.com", null);

        // Assert
        assertNull(result);
        verify(userDao, never()).logIn(anyString(), anyString());
    }

    @Test
    void testLogIn_BothParametersNull_ReturnsNullImmediately() {
        // Act
        Seller result = userLogin.logIn(null, null);

        // Assert
        assertNull(result);
        verify(userDao, never()).logIn(anyString(), anyString());
    }

    // ==========================================
    // DAO INTEGRATION TESTS (LOGIC PATHS)
    // ==========================================

    @Test
    void testLogIn_Success() {
        // Arrange
        String email = "merchant@ecommerce.com";
        String password = "correctPassword";
        Seller mockSeller = new Seller(101, "MerchantJoe", email, password, "SELLER");

        when(userDao.logIn(email, password)).thenReturn(mockSeller);

        // Act
        Seller result = userLogin.logIn(email, password);

        // Assert
        assertNotNull(result);
        assertEquals(101, result.getSellerId());
        assertEquals("MerchantJoe", result.getName());
        assertEquals(email, result.getEmail());
        assertEquals("SELLER", result.getRole());
        
        verify(userDao, times(1)).logIn(email, password);
    }

    @Test
    void testLogIn_InvalidCredentials_ReturnsNull() {
        // Arrange
        String email = "user@example.com";
        String password = "wrongPassword";

        // Mock DAO returning null for invalid credentials
        when(userDao.logIn(email, password)).thenReturn(null);

        // Act
        Seller result = userLogin.logIn(email, password);

        // Assert
        assertNull(result);
        verify(userDao, times(1)).logIn(email, password);
    }
}