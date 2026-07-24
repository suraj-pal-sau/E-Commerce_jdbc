package com.irfan.constant;

public class ProductQueries
{
	public static final String VIEW_ALL_PRODUCTS = "SELECT product_id, product_name, description, price, category, stock_quantity FROM Product";
	public static final String SEARCH_PRODUCTS_BY_CATEGORY = "SELECT product_id, product_name, description, price, category, stock_quantity FROM Product WHERE category = ?";
}
