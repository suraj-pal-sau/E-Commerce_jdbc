package com.irfan.service;

import com.irfan.daoimplementation.ProductDaoImplementation;
import com.irfan.dao.ProductDao;

public class ProductService
{
	private ProductDao productDao = new ProductDaoImplementation();
	
	public void viewAllAvailableProducts() {
		productDao.viewAllAvailableProducts();
	}
	
	public void searchProductsByCategory(String category) {
		productDao.searchProductsByCategory(category);
	}
	
}
