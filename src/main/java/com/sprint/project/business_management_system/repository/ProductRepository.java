//package com.sprint.project.business_management_system.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import com.sprint.project.business_management_system.Entity.Product;
//
//public interface ProductRepository extends JpaRepository<Product, String>  {
//	 List<Product> findByProductLine(String productLine);
//}

package com.sprint.project.business_management_system.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.project.business_management_system.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByProductLine_ProductLine(String productLine);
    @Query("SELECT p FROM Product p WHERE p.buyPrice > :price")
    List<Product> findExpensiveProducts(@Param("price") double price);
}