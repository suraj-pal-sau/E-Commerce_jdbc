package com.ecommerce.serviceimpl;

import com.ecommerce.dao.CartDao;
import com.ecommerce.daoimp.CartDaoImpl;
import com.ecommerce.service.CartService;

public class CartServiceImpl implements CartService {
	
	CartDao cartDaoImpl = new CartDaoImpl();
	
	@Override
	public void addProductToCart(int customer_id, int product_id, int quantity) {
		cartDaoImpl.addProductIntoCartDB(customer_id, product_id, quantity);
	}
	
	@Override
	public void removeProductToCart(int customer_id, int product_id) {
		cartDaoImpl.removeProductFromCartDB(customer_id, product_id);
	}
	
	@Override
	public void viewCartProducts(int customer_id) {
		cartDaoImpl.searchAllCartProduct(customer_id);
	}
}
