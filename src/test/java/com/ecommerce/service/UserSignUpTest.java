package com.ecommerce.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import com.ecommerce.service.UserSignUp;

@ExtendWith(MockitoExtension.class)
public class UserSignUpTest {

    @Mock
    private UserDao userDao; // Mocking the corrected UserDao dependency

    @InjectMocks
    private UserSignUp userSignUp;

    // ==========================================
    // VALIDATION TESTS (INPUT EDGE CASES)
    // ==========================================

    @Test
    void testSignUp_NullUsername_ReturnsFalseImmediately() {
        // Act
        boolean result = userSignUp.signUp(null, "user@test.com", "securePass", "BUYER");

        // Assert
        assertFalse(result);
        // Verify database is never hit because validation caught the null value
        verify(userDao, never()).signUp(anyString(), anyString(), anyString(), anyString());
    }

    @Test
    void testSignUp_NullEmail_ReturnsFalseImmediately() {
        // Act
        boolean result = userSignUp.signUp("john_doe", null, "securePass", "BUYER");

        // Assert
        assertFalse(result);
        verify(userDao, never()).signUp(anyString(), anyString(), anyString(), anyString());
    }

    @Test
    void testSignUp_NullPassword_ReturnsFalseImmediately() {
        // Act
        boolean result = userSignUp.signUp("john_doe", "user@test.com", null, "BUYER");

        // Assert
        assertFalse(result);
        verify(userDao, never()).signUp(anyString(), anyString(), anyString(), anyString());
    }

    @Test
    void testSignUp_NullRole_ReturnsFalseImmediately() {
        // Act
        boolean result = userSignUp.signUp("john_doe", "user@test.com", "securePass", null);

        // Assert
        assertFalse(result);
        verify(userDao, never()).signUp(anyString(), anyString(), anyString(), anyString());
    }

    // ==========================================
    // DAO INTEGRATION TESTS (SUCCESS / FAILURE)
    // ==========================================

    @Test
    void testSignUp_Success() {
        // Arrange
        String username = "john_doe";
        String email = "john@example.com";
        String password = "password123";
        String role = "CUSTOMER";

        // Mock DAO behavior for successful registration
        when(userDao.signUp(username, email, password, role)).thenReturn(true);

        // Act
        boolean result = userSignUp.signUp(username, email, password, role);

        // Assert
        assertTrue(result);
        verify(userDao, times(1)).signUp(username, email, password, role);
    }

    @Test
    void testSignUp_DaoFailure_ReturnsFalse() {
        // Arrange
        String username = "john_doe";
        String email = "john@example.com";
        String password = "password123";
        String role = "CUSTOMER";

        // Mock DAO returning false (e.g., if duplicate email violation happens in DB)
        when(userDao.signUp(username, email, password, role)).thenReturn(false);

        // Act
        boolean result = userSignUp.signUp(username, email, password, role);

        // Assert
        assertFalse(result);
        verify(userDao, times(1)).signUp(username, email, password, role);
    }
}