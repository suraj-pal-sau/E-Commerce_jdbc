package com.ecommerce.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.config.DBconfig;
import com.ecommerce.constants.ProductQueries;
import com.ecommerce.dao.ProductDao;
import com.ecommerce.model.Product;

public class ProductDaoImp implements ProductDao {

    @Override
    public List<Product> viewAvailableProducts() {

        // List to store products fetched from database
        List<Product> productList = new ArrayList<>();

        try {

            // Step 1 : Get Database Connection
            Connection connection = DBconfig.getConnection();

            // Step 2 : Prepare SQL Query
            PreparedStatement preparedStatement = connection
                    .prepareStatement(ProductQueries.VIEW_AVAILABLE_PRODUCTS);

            // Step 3 : Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Step 4 : Read ResultSet
            while (resultSet.next()) {

                Product product = new Product();

                product.setProductId(resultSet.getLong("product_id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setCategory(resultSet.getString("category"));
                product.setPrice(resultSet.getDouble("price"));
                product.setStockQuantity(resultSet.getInt("stock_quantity"));

                productList.add(product);
            }

            // Step 5 : Close JDBC Resources
            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public Product displayProductDetails(long productId) {

        Product product = null;

        try {

            Connection connection = DBconfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    ProductQueries.DISPLAY_PRODUCT_DETAILS);

            // Set value for ?
            preparedStatement.setLong(1, productId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                product = new Product();

                product.setProductId(resultSet.getLong("product_id"));
                product.setSellerId(resultSet.getLong("seller_id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setDescription(resultSet.getString("description"));
                product.setCategory(resultSet.getString("category"));
                product.setPrice(resultSet.getDouble("price"));
                product.setStockQuantity(resultSet.getInt("stock_quantity"));
                product.setCreatedAt(resultSet.getTimestamp("created_at"));
                product.setUpdatedAt(resultSet.getTimestamp("updated_at"));
            }

            resultSet.close();
            preparedStatement.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return product;
    }

    @Override
    public int showAvailableStock(long productId) {

        int stockQuantity = -1;

        try {

            Connection connection = DBconfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    ProductQueries.SHOW_AVAILABLE_STOCK);

            preparedStatement.setLong(1, productId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                stockQuantity = resultSet.getInt("stock_quantity");
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return stockQuantity;
    }

}