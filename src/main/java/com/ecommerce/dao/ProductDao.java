package com.ecommerce.dao;

import java.util.List;
import com.ecommerce.model.Product;

public interface ProductDao {

    List<Product> viewAvailableProducts();

    Product displayProductDetails(long productId);

    int showAvailableStock(long productId);
    
    boolean reduceProductStock(long productId, int quantity);

}