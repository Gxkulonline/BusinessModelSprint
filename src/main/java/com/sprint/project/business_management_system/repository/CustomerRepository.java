package com.sprint.project.business_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.business_management_system.Entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {}
