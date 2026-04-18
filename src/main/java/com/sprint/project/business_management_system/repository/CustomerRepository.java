package com.sprint.project.business_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.project.business_management_system.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByCountry(String country);

    List<Customer> findAllByOrderByCreditLimitDesc();
    @Query("SELECT c FROM Customer c WHERE c.creditLimit > :limit")
    List<Customer> findHighCreditCustomers(@Param("limit") double limit);

}
