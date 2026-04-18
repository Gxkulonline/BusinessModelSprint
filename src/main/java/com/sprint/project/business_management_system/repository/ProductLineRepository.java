package com.sprint.project.business_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.project.business_management_system.entity.ProductLine;

public interface ProductLineRepository extends JpaRepository<ProductLine, String>{
	 @Query("SELECT pl FROM ProductLine pl WHERE pl.productLine LIKE %:name%")
	    List<ProductLine> searchByName(@Param("name") String name);
}
