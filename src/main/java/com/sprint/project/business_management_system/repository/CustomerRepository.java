package com.sprint.project.business_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprint.project.business_management_system.Entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByCountry(String country);
    @Query("SELECT p FROM Product p WHERE p.buyPrice > :price")
    List<Customer> findAllByOrderByCreditLimitDesc();

}
