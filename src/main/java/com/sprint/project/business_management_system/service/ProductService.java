package com.sprint.project.business_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.business_management_system.Entity.Product;
import com.sprint.project.business_management_system.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProductById(String id) {
        return repo.findById(id).orElse(null);
    }

    public Product saveProduct(Product product) {
        return repo.save(product);
    }

    // 🔥 Based on your schema (productLine column)
    public List<Product> getProductsByProductLine(String productLine) {
        return repo.findByProductLine(productLine);
    }
}
