package com.ecommerce.serviceimpl;

import java.util.List;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.daoimp.ProductDaoImp;
import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;

public class ProductServiceImpl implements ProductService
{

	// Dependency on DAO layer
    private ProductDao productDao = new ProductDaoImp();

    @Override
    public List<Product> viewAvailableProducts() {

        return productDao.viewAvailableProducts();
    }
    
    @Override
    public Product displayProductDetails(long productId) {

        return productDao.displayProductDetails(productId);
    }
    
    @Override
    public int showAvailableStock(long productId) {

        return productDao.showAvailableStock(productId);
    }

}
