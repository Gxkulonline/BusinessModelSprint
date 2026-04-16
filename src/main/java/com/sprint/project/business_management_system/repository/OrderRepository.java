package com.sprint.project.business_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.business_management_system.Entity.Customer;
import com.sprint.project.business_management_system.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	List<Order> findByCustomer(Customer customer);
	  List<Order> findByStatus(String status);
    List<Order> findByCustomerCustomerNumberAndStatus(Integer customerNumber, String status);
}
