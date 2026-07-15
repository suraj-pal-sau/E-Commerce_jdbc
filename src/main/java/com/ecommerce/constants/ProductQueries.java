package com.ecommerce.constants;

public class ProductQueries {

    private ProductQueries() {
        // Prevent object creation
    }

    // 1. View all available products
    public static final String VIEW_AVAILABLE_PRODUCTS =
            "SELECT product_id, product_name, category, price FROM product WHERE stock_quantity > 0";

    // 2. Display complete product details
    public static final String DISPLAY_PRODUCT_DETAILS =
            "SELECT * FROM product WHERE product_id = ?";

    // 3. Show stock quantity of a product
    public static final String SHOW_AVAILABLE_STOCK =
            "SELECT stock_quantity FROM product WHERE product_id = ?";

}