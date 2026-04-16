package com.sprint.project.business_management_system.service;
//
//import java.util.List;
//
//import com.sprint.project.business_management_system.Entity.Product;
//
//public interface ProductService {
//	 List<Product> getAllProducts();
//
//	    Product getProductById(String id);
//
//	    Product saveProduct(Product product);
//
//	    List<Product> getProductsByProductLine(String productLine);
//}

import java.util.List;

import com.sprint.project.business_management_system.Entity.Product;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(String id);
    Product saveProduct(Product product);
    List<Product> getProductsByProductLine(String productLine); // ✅ keep same
}
