package com.ecommerce.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.exceptions.InsufficientStockException;
import com.ecommerce.model.Product;
import com.ecommerce.serviceimpl.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@Mock
    private ProductDao productDao;

    @InjectMocks
    private ProductServiceImpl productService;

    // ==========================================
    // TEST METHOD
    // ==========================================

    @Test
    void testTest_ExecutesSuccessfully() {
        assertDoesNotThrow(() -> productService.test());
    }

    // ==========================================
    // VIEW AVAILABLE PRODUCTS TESTS
    // ==========================================

    @Test
    void testViewAvailableProducts_ReturnsList() {
        // Arrange
        List<Product> mockProducts = new ArrayList<>();
        Product p1 = new Product();
        p1.setProductId(1L);
        p1.setProductName("Laptop");
        mockProducts.add(p1);

        when(productDao.viewAvailableProducts()).thenReturn(mockProducts);

        // Act
        List<Product> results = productService.viewAvailableProducts();

        // Assert
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("Laptop", results.get(0).getProductName());
        verify(productDao, times(1)).viewAvailableProducts();
    }

    @Test
    void testViewAvailableProducts_ReturnsEmptyList() {
        // Arrange
        when(productDao.viewAvailableProducts()).thenReturn(Collections.emptyList());

        // Act
        List<Product> results = productService.viewAvailableProducts();

        // Assert
        assertNotNull(results);
        assertTrue(results.isEmpty());
    }

    // ==========================================
    // DISPLAY PRODUCT DETAILS TESTS
    // ==========================================

    @Test
    void testDisplayProductDetails_Success() {
        // Arrange
        long productId = 101L;
        Product mockProduct = new Product();
        mockProduct.setProductId(productId);
        mockProduct.setProductName("Smartphone");

        when(productDao.displayProductDetails(productId)).thenReturn(mockProduct);

        // Act
        Product result = productService.displayProductDetails(productId);

        // Assert
        assertNotNull(result);
        assertEquals(productId, result.getProductId());
        assertEquals("Smartphone", result.getProductName());
        verify(productDao, times(1)).displayProductDetails(productId);
    }

    // ==========================================
    // SHOW AVAILABLE STOCK TESTS
    // ==========================================

    @Test
    void testShowAvailableStock_ReturnsStockValue() {
        // Arrange
        long productId = 101L;
        when(productDao.showAvailableStock(productId)).thenReturn(15);

        // Act
        int stock = productService.showAvailableStock(productId);

        // Assert
        assertEquals(15, stock);
        verify(productDao, times(1)).showAvailableStock(productId);
    }

    // ==========================================
    // REDUCE PRODUCT STOCK TESTS
    // ==========================================

    @Test
    void testReduceProductStock_Success() throws InsufficientStockException {
        // Arrange
        long productId = 101L;
        int requestedQuantity = 5;
        int currentStock = 10;

        when(productDao.showAvailableStock(productId)).thenReturn(currentStock);
        when(productDao.reduceProductStock(productId, requestedQuantity)).thenReturn(true);

        // Act
        boolean result = productService.reduceProductStock(productId, requestedQuantity);

        // Assert
        assertTrue(result);
        verify(productDao, times(1)).showAvailableStock(productId);
        verify(productDao, times(1)).reduceProductStock(productId, requestedQuantity);
    }

    @Test
    void testReduceProductStock_ProductNotFound_ThrowsIllegalArgumentException() {
        // Arrange
        long productId = 999L;
        int requestedQuantity = 1;
        // DAO returns -1 when product is not found in database
        when(productDao.showAvailableStock(productId)).thenReturn(-1);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> 
            productService.reduceProductStock(productId, requestedQuantity)
        );

        assertEquals("Product not found.", exception.getMessage());
        // Verify we stop early and never try to update stock
        verify(productDao, never()).reduceProductStock(anyLong(), anyInt());
    }

    @Test
    void testReduceProductStock_InsufficientStock_ThrowsInsufficientStockException() {
        // Arrange
        long productId = 101L;
        int requestedQuantity = 20; // Asking for 20
        int currentStock = 5;       // Only 5 in stock

        when(productDao.showAvailableStock(productId)).thenReturn(currentStock);

        // Act & Assert
        InsufficientStockException exception = assertThrows(InsufficientStockException.class, () -> 
            productService.reduceProductStock(productId, requestedQuantity)
        );

        assertEquals("Only 5 item(s) available in stock.", exception.getMessage());
        // Verify update is never called
        verify(productDao, never()).reduceProductStock(anyLong(), anyInt());
    }
}