package com.irfan.jdbc3.jdbc3_prepare_statement2_products;

public class Product
{
	private int id;
	private String productName;
	private double price;
	private int stock;
	private String brand;

	public Product(int id, String productName, double price, int stock, String brand)
	{
		super();
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.stock = stock;
		this.brand = brand;
	}

	public int getId()
	{
		return id;
	}

	public String getProductName()
	{
		return productName;
	}

	public double getPrice()
	{
		return price;
	}

	public int getStock()
	{
		return stock;
	}

	public String getBrand()
	{
		return brand;
	}

	@Override
	public String toString()
	{
		return "Product [id=" + id + ", productName=" + productName + ", price=" + price + ", stock=" + stock
				+ ", brand=" + brand + "]";
	}
	

}
