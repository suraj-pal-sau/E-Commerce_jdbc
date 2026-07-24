package com.irfan.jdbc3.jdbc3_prepare_statement2_products;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.irfan.data.Data;

public class ProductService
{
	private Data data = new Data();

	Connection connection;
	PreparedStatement ps;
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice", "root",
					data.getPassword());
			connection.setAutoCommit(false);
		} catch (ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addProducts(String filePath) {
		
		try(BufferedReader br= new BufferedReader(new java.io.FileReader(filePath))){
			String query = "INSERT INTO Products VALUES (?, ? , ?, ?, ?)";
			
			ps = connection.prepareStatement(query);
			
			String line;
			br.readLine();	//skip the first line --> column names
			int i=1;
			while((line = br.readLine()) != null) {
				String[] rowsValue = line.split(",");
//				System.out.println(line);
				
				ps.setInt(1, Integer.parseInt(rowsValue[0]));
				ps.setString(2, rowsValue[1]);
				ps.setDouble(3, Double.parseDouble(rowsValue[2]));
				ps.setInt(4, Integer.parseInt(rowsValue[3]));
				ps.setString(5, rowsValue[4]);
				
				ps.addBatch();	//creating batch  and adding queries into the batch
				if(i % 10 == 0) {	//batch of 10 queries
					System.out.println("Executing 10 Queries............." + i);
					ps.executeBatch();
					ps.clearBatch();
				}
				i++;
			}
			System.out.println("committing..........");
			connection.commit();
			
			
		} catch (IOException | SQLException e)
		{
			e.printStackTrace();
		} 
	}
	
	public boolean addProduct(Product p) throws SQLException {	// ? --> placeHolder 
		String query = "INSERT INTO Products VALUES (?, ? , ?, ?, ?)";
		ps = connection.prepareStatement(query);
		
		ps.setInt(1, p.getId());
		ps.setString(2, p.getProductName());
		ps.setDouble(3, p.getPrice());
		ps.setInt(4, p.getStock());
		ps.setString(5, p.getBrand());
		
		int recordsInserted = ps.executeUpdate();
		
		connection.commit();
		
		return recordsInserted > 0;
	}
	
	public void displayAllProducts() throws SQLException {
		String query = "SELECT * FROM Products";
		ps = connection.prepareStatement(query);
		ResultSet rs= ps.executeQuery();
		while(rs.next()) {
			Product product = new Product(
				    rs.getInt("id"),
				    rs.getString("productName"),
				    rs.getDouble("price"),
				    rs.getInt("stock"),
				    rs.getString("brand")
				);
			System.out.println(product);
		}
		
	}
	
	public void searchProductsByBrand(String brand) throws SQLException {
		String query = "SELECT productName, price, brand FROM Products where brand=?";
		ps = connection.prepareStatement(query);
		
		ps.setString(1, brand);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			System.out.println(
				    "Product Name: " + rs.getString("productName") +
				    ", Price: " + rs.getDouble("price") +
				    ", Brand: " + rs.getString("brand")
				);
		}
	}
	
	public void searchProductByPriceRange(double fromPrice, double toPrice) throws SQLException {
		String query = "SELECT productName, brand, price FROM Products WHERE price BETWEEN ? AND ?";
		ps = connection.prepareStatement(query);
		
		ps.setDouble(1, fromPrice);
		ps.setDouble(2, toPrice);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			System.out.println(
				    "ProductName : " + rs.getString("productName") +
				    ", Brand: " + rs.getString("brand") +
				    ", Price: " + rs.getDouble("price") 
				);
		}
	}
}
