package com.sprint.project.business_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.business_management_system.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, String>  {
	 List<Product> findByProductLine(String productLine);
}
