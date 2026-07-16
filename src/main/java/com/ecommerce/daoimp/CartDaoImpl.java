package com.ecommerce.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecommerce.config.DBconfig;
import com.ecommerce.constants.CartQueries;
import com.ecommerce.dao.CartDao;

public class CartDaoImpl implements CartDao {

	DBconfig dbConfig = new DBconfig();

	Connection connection = dbConfig.getConnection();

	@Override
	public boolean findCustomerByIdFromCart(int customer_id) {

		try {

			PreparedStatement preparedStatement = connection.prepareStatement(CartQueries.FIND_CUSTOMER_ID_IN_CART);
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

		try {

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
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.err.println("Error from addProductIntoCartDB for Rollback: " + e.getMessage());
			}
		}
	}

	@Override
	public void removeProductFromCartDB(int customer_id, int product_id) {

		try {
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
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.err.println("Error from removeProductFromCartDB for Rollback: " + e.getMessage());
			}
		}
	}

	@Override
	public void searchAllCartProduct(int customer_id) {

		try {

			PreparedStatement preparedStatment = connection
					.prepareStatement(CartQueries.SEARCH_ALL_CART_PROUDCTS_BY_CUSTOMER_ID);
			preparedStatment.setInt(1, customer_id);

			ResultSet result = preparedStatment.executeQuery();

			while (result.next()) {

				int cust_id = result.getInt(1);
				int product_id = result.getInt(2);
				String product_name = result.getString(3);
				String product_desc = result.getString(4);
				String product_category = result.getString(5);
				double product_price = result.getDouble(6);

				System.out.println("\n========== CART PRODUCT DETAILS ==========\n");

				System.out.println("Cust ID      : " + cust_id);
				System.out.println("Product ID       : " + product_id);
				System.out.println("Product Name    : " + product_name);
				System.out.println("Description     : " + product_desc);
				System.out.println("Category        : " + product_category);
				System.out.println("Price           : ₹" + product_price);
			}
		} catch (SQLException e) {
			System.err.println("Error from searchAllCartProduct: " + e.getMessage());
		}
	}
}
