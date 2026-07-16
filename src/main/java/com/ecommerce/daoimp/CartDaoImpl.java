package com.ecommerce.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.config.DBconfig;
import com.ecommerce.constants.CartQueries;
import com.ecommerce.dao.CartDao;
import com.ecommerce.exceptions.ProductNotFoundException;
import com.ecommerce.model.CartItem;

public class CartDaoImpl implements CartDao
{

	@Override
	public boolean findProductFromCartByCustomerId(int customer_id) {

		try {
			Connection connection = DBconfig.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(CartQueries.FIND_PROUDCT_FROM_CART_BUY_CUSTOMER_ID);
			preparedStatement.setInt(1, customer_id);

			ResultSet result = preparedStatement.executeQuery();

			return result.next();
		} catch (SQLException e) {
			System.err.println("Error from findCustomerByIdFromCart DAO: " + e.getMessage());
		}

		return false;
	}

	@Override
	public void addProductIntoCartDB(int customer_id, int product_id, int quantity) {
	    Connection connection = null; // Declare outside to access in catch
	    try {
	        connection = DBconfig.getConnection();
	        connection.setAutoCommit(false);

	        PreparedStatement preparedStatement = connection.prepareStatement(CartQueries.ADD_PRODUCT_INTO_CART);
	        preparedStatement.setInt(1, customer_id);
	        preparedStatement.setInt(2, product_id);
	        preparedStatement.setInt(3, quantity);

	        int recordUpdated = preparedStatement.executeUpdate();

	        if (recordUpdated > 0) {
	            System.out.println("Product Added Successfully in Cart");
	            connection.commit();
	        } else {
	            System.err.println("Failed to add product to Cart. Please try again.");
	            connection.rollback();
	        }

	    } catch (SQLException e) {
	        System.err.println("Error from addProductIntoCartDB: " + e.getMessage());
	        // FIX: Roll back the transaction if an exception occurs!
	        if (connection != null) {
	            try {
	                connection.rollback();
	            } catch (SQLException rollbackEx) {
	                System.err.println("Rollback failed: " + rollbackEx.getMessage());
	            }
	        }
	    }
	}

	@Override
	public void removeProductFromCartDB(int customer_id, int product_id) {

		try {
			Connection connection = DBconfig.getConnection();
			connection.setAutoCommit(false);

			PreparedStatement preparedStatement = connection.prepareStatement(CartQueries.REMOVE_PRODUCT_FROM_CART);
			preparedStatement.setInt(1, customer_id);
			preparedStatement.setInt(2, product_id);

			int recordUpdated = preparedStatement.executeUpdate();

			if (recordUpdated > 0) {
				System.out.println("Product Removed Successfully from Cart");
				connection.commit();
			} else {
				System.err.println("Product not found in your Cart. Nothing was removed");
				connection.rollback();
			}

		} catch (SQLException e) {
			System.err.println("Error from removeProductFromCartDB: " + e.getMessage());
		}
	}

	@Override
	public void searchAllCartProduct(int customer_id) {

		try {
			Connection connection = DBconfig.getConnection();
			PreparedStatement preparedStatment = connection
					.prepareStatement(CartQueries.SEARCH_ALL_CART_PROUDCTS_BY_CUSTOMER_ID);
			preparedStatment.setInt(1, customer_id);

			ResultSet result = preparedStatment.executeQuery();

			System.out.println("\n========== CART PRODUCT DETAILS ==========\n");
			
			while (result.next()) {

				int cust_id = result.getInt(1);
				int product_id = result.getInt(2);
				int quantity = result.getInt(3);
				String product_name = result.getString(4);
				String product_desc = result.getString(5);
				String product_category = result.getString(6);
				double product_price = result.getDouble(7);

				System.out.println("Cust ID      	: " + cust_id);
				System.out.println("Product ID      : " + product_id);
				System.out.println("Product Name    : " + product_name);
				System.out.println("Description     : " + product_desc);
				System.out.println("Category        : " + product_category);
				System.out.println("Product Price   : ₹" + product_price);
				System.out.println("Quantity 	: ₹" + quantity);
				System.out.println("--------------------------------");
				System.out.println("Total Price 	: " + product_price * quantity);
				System.out.println("--------------------------------");

			}
		} catch (SQLException e) {
			System.err.println("Error from searchAllCartProduct: " + e.getMessage());
		}
	}

	@Override
    public List<CartItem> getCartItems(long customerId) {

        List<CartItem> cartItems = new ArrayList<>();

        try {
        	Connection connection = DBconfig.getConnection();
			PreparedStatement ps = connection
					.prepareStatement(CartQueries.GET_CART_ITEMS);

            ps.setLong(1, customerId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                CartItem item = new CartItem();

                item.setCartId(rs.getInt("cart_id"));
                item.setCustomerId(rs.getLong("customer_id"));
                item.setProductId(rs.getLong("product_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

                cartItems.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cartItems;
    }
	
	@Override
	public void clearCart(long userId) {

	    try {
	    	
	    	Connection connection = DBconfig.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(CartQueries.CLEAR_CART_ITEMS);

	        preparedStatement.setLong(1, userId);

	        int rowsAffected = preparedStatement.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Cart cleared successfully.");
	        } else {
	            System.out.println("Cart is already empty.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public boolean findProductFromCart(int customer_id, int product_id) {
	    try (Connection connection = DBconfig.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(CartQueries.FIND_PRODUCT_FROM_CART)) {
	        
	        preparedStatement.setInt(1, customer_id);
	        preparedStatement.setInt(2, product_id);
	        
	        try (ResultSet rs = preparedStatement.executeQuery()) {
	            return rs.next();
	        }
	        
	    } catch(SQLException e) {
	        System.err.println("Error finding product: " + e.getMessage());
	    }
	    
	    return false;
	}
	
	public void updateExistingCartProduct(int customer_id, int product_id, int quantity) {
		
		try {
			
			Connection connection = DBconfig.getConnection();
			
			connection.setAutoCommit(false);
			
			PreparedStatement preparedStatment = connection.prepareStatement(CartQueries.UPDATE_EXISTING_PROUDCT);
			
			preparedStatment.setInt(1, quantity);
			preparedStatment.setInt(2, customer_id);
			preparedStatment.setInt(3, product_id);
			
			int rowsUpdated = preparedStatment.executeUpdate();
			
			if (rowsUpdated > 0) {
				System.out.println("Cart Product Updated Successfully...");
				connection.commit();
			} else {
				connection.rollback();
				throw new ProductNotFoundException("Product Not Found!");
			}
			
		} catch(SQLException | ProductNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}
