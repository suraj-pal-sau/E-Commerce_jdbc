package com.irfan.jdbc3.jdbc3_prepare_statement2_products;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException
    {
    	
        ProductService productService = new ProductService();
        
        //adding products which is in CSV format 
		String filepath = "C:\\Users\\being\\OneDrive\\Academy\\EclipseWorkspace\\day7_oops\\jdbc3-prepare-statement2-products\\src\\main\\resources\\products_100.csv";
//        productService.addProducts(filepath);
//        
//        System.out.println(productService.addProduct(new Product(101, "Dell Laptop Inspiron 3525", 54500, 20, "Dell")));
		
//		productService.displayAllProducts();
		
		//productService.searchProductsByBrand("Dell");
        System.out.println();
        productService.searchProductsByBrand("HP");
        
        productService.searchProductByPriceRange(2000, 4000);
        
    }
}
