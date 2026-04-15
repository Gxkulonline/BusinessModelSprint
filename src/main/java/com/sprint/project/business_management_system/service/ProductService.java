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

	    public List<Product> getAll() {
	        return repo.findAll();
	    }

	    public Product getById(String id) {
	        return repo.findById(id).orElse(null);
	    }

	    public Product save(Product p) {
	        return repo.save(p);
	    }

	    public void delete(String id) {
	        repo.deleteById(id);
	    }

}
