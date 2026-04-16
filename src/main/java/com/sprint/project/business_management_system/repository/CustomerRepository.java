package com.sprint.project.business_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.business_management_system.Entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByCountry(String country);

    List<Customer> findAllByOrderByCreditLimitDesc();

}
