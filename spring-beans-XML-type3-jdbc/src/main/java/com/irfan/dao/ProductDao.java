package com.irfan.dao;

public interface ProductDao
{
	
	/**
	 * get details of all products stored in database
	 * @author Md Irfan
	 */
	void viewAllAvailableProducts();
	
	/**
	 *  get all product details of a given category 
	 *  For Example "Electronics", "Bags", "Footwear", "Clothing","Home Appliances", "Books" etc.
	 * @author Md Irfan
	 */
	void searchProductsByCategory(String category);
}
