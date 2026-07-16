package com.ecommerce.constants;

public class OrderQueries {

    private OrderQueries() {}

    // Orders
    public static final String INSERT_ORDER =
            "INSERT INTO orders (user_id, total_amount) VALUES (?, ?)";

    public static final String GET_ORDER_BY_ID =
            "SELECT * FROM orders WHERE order_id = ?";

    public static final String GET_ORDER_BY_USER_ID =
            "SELECT * FROM orders WHERE user_id = ? ORDER BY order_date DESC";

    public static final String UPDATE_ORDER_STATUS =
            "UPDATE orders SET status = ? WHERE order_id = ?";

    public static final String CANCEL_ORDER =
            "UPDATE orders SET status = 'CANCELLED' WHERE order_id = ? AND user_id = ?";

    // Order Items
    public static final String INSERT_ORDER_ITEM =
            "INSERT INTO order_items (order_id, product_id, quantity, price, subtotal) VALUES (?, ?, ?, ?, ?)";

    public static final String GET_ORDER_ITEMS =
            "SELECT * FROM order_items WHERE order_id = ?";
    
    public static final String VIEW_ORDER_HISTORY = 
    	    "SELECT " +
    	    "    o.order_id, " +
    	    "    p.product_id, " +
    	    "    p.product_name, " +
    	    "    oi.quantity,  " +
    	    "    p.price AS single_product_price, " +
    	    "    oi.subtotal AS final_price, " +
    	    "    o.total_amount," +
    	    "    o.created_at " +
    	    "FROM orders AS o " +
    	    "INNER JOIN order_items AS oi ON o.order_id = oi.order_id " +
    	    "INNER JOIN product AS p     ON oi.product_id = p.product_id " +
    	    "WHERE o.user_id = ? " +
    	    "ORDER BY o.created_at DESC;";
}
