package com.ecommerce.cartserviceimpltest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.dao.CartDao;
import com.ecommerce.serviceimpl.CartServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CartServiceImplTest {

    // 1. Mock the internal interface dependency. 
    // The field name 'cartDaoImpl' matches the exact name inside your CartServiceImpl class.
    @Mock
    private CartDao cartDaoImpl; 

    // 2. InjectMocks bypasses constructors and injects the mock directly into the class field
    @InjectMocks
    private CartServiceImpl cartService;

    @Test
    void testAddProductToCart_ShouldForwardToDao() {
        // Arrange
        int customerId = 101;
        int productId = 12;
        int quantity = 3;

        // Act
        cartService.addProductToCart(customerId, productId, quantity);

        // Assert
        // Verifies that the service properly forwarded the data parameters to the DAO script
        verify(cartDaoImpl, times(1)).addProductIntoCartDB(customerId, productId, quantity);
    }

    @Test
    void testRemoveProductToCart_ShouldForwardToDao() {
        // Arrange
        int customerId = 101;
        int productId = 12;

        // Act
        cartService.removeProductToCart(customerId, productId);

        // Assert
        // Verifies the deletion instruction successfully matches the exact profile fields
        verify(cartDaoImpl, times(1)).removeProductFromCartDB(customerId, productId);
    }

    @Test
    void testViewCartProducts_ShouldForwardToDao() {
        // Arrange
        int customerId = 101;

        // Act
        cartService.viewCartProducts(customerId);

        // Assert
        // Verifies the user lookup targets the intended customer records loop
        verify(cartDaoImpl, times(1)).searchAllCartProduct(customerId);
    }
    
    // ---------------------- EDGE CASE ----------------------
    
    @Test
    void testViewCartProducts_WhenDaoThrowsRuntimeException_ShouldPropagateException() {
        int customerId = 101;
        doThrow(new RuntimeException("Database cluster unresponsive")).when(cartDaoImpl).searchAllCartProduct(customerId);

        assertThrows(RuntimeException.class, () -> cartService.viewCartProducts(customerId));
        verify(cartDaoImpl, times(1)).searchAllCartProduct(customerId);
    }

    @Test
    void testAddProductToCart_WithNegativeQuantity_ShouldStillForwardToDao() {
        int customerId = 101; int productId = 5; int negativeQuantity = -50;
        
        cartService.addProductToCart(customerId, productId, negativeQuantity);
        
        verify(cartDaoImpl, times(1)).addProductIntoCartDB(customerId, productId, negativeQuantity);
    }

    @Test
    void testRemoveProductToCart_WithInvalidCustomerId_ShouldStillForwardToDao() {
        int invalidCustomerId = -1; int productId = 99;
        
        cartService.removeProductToCart(invalidCustomerId, productId);
        
        verify(cartDaoImpl, times(1)).removeProductFromCartDB(invalidCustomerId, productId);
    }
}
