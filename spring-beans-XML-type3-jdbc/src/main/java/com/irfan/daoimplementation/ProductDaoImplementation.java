package com.irfan.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.irfan.constant.ProductQueries;
import com.irfan.dao.ProductDao;
import com.irfan.databaseconfig.DatabaseConfig;
import com.irfan.model.Product;

public class ProductDaoImplementation implements ProductDao
{
	Connection connection;
	PreparedStatement preparedStatement;
	ApplicationContext context = new ClassPathXmlApplicationContext("com/irfan/daoimplementation/bean3.xml");
	
	{
		try
		{
			connection = DatabaseConfig.getConnection();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void viewAllAvailableProducts()
	{
		try
		{
			preparedStatement = connection.prepareStatement(ProductQueries.VIEW_ALL_PRODUCTS);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Product product = (Product) context.getBean("Product-object-view");
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setDescription(rs.getString("description"));
				product.setCategory(rs.getString("category"));
				product.setPrice(rs.getDouble("price"));
				product.setStockQuantity(rs.getInt("stock_quantity"));
				
				System.out.println(product);
			}
			
			
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void searchProductsByCategory(String category)
	{
		try
		{
			preparedStatement = connection.prepareStatement(ProductQueries.SEARCH_PRODUCTS_BY_CATEGORY);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Product product = (Product) context.getBean("product-object-view");
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setDescription(rs.getString("description"));
				product.setCategory(rs.getString("category"));
				product.setPrice(rs.getDouble("price"));
				product.setStockQuantity(rs.getInt("stock_quantity"));
				
				System.out.println(product);
			}

		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		
	}

}
