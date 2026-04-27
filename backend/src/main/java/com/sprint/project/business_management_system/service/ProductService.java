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
import com.sprint.project.business_management_system.requestDto.ProductRequestDto;
import com.sprint.project.business_management_system.responseDto.ProductResponseDto;

public interface ProductService {
    List<ProductResponseDto> getAllProducts();
    ProductResponseDto getProductById(String id);
    ProductResponseDto saveProduct(ProductRequestDto productDto);
    List<ProductResponseDto> getProductsByProductLine(String productLine);
    void deleteProduct(String id);
}
