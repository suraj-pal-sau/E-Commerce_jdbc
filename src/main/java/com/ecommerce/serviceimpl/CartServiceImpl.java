package com.ecommerce.serviceimpl;

import java.util.List;

import com.ecommerce.dao.CartDao;
import com.ecommerce.daoimp.CartDaoImpl;
import com.ecommerce.model.CartItem;
import com.ecommerce.service.CartService;

public class CartServiceImpl implements CartService
{

	CartDao cartDaoImpl = new CartDaoImpl();

	@Override
	public void addProductToCart(int customer_id, int product_id, int quantity) {
		
		// check product already exist or not 
		boolean isProductExist = cartDaoImpl.findProductFromCart(customer_id, product_id);
		
		if (isProductExist) {
			cartDaoImpl.updateExistingCartProduct(customer_id, product_id, quantity);
		}
		else {
			cartDaoImpl.addProductIntoCartDB(customer_id, product_id, quantity);			
		}
	}

	@Override
	public void removeProductToCart(int customer_id, int product_id) {
		
		cartDaoImpl.removeProductFromCartDB(customer_id, product_id);
	}

	@Override
	public void viewCartProducts(int customer_id) {
		cartDaoImpl.searchAllCartProduct(customer_id);
	}

	@Override
	public List<CartItem> getCartItems(long customerId) {
		return cartDaoImpl.getCartItems(customerId);
	}

	@Override
	public void clearCart(long userId) {
		cartDaoImpl.clearCart(userId);
	}
}
