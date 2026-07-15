package com.ecommerce.service;

import java.util.List;

import com.ecommerce.model.Product;

public interface ProductService {

    List<Product> viewAvailableProducts();

    Product displayProductDetails(long productId);

    int showAvailableStock(long productId);

}