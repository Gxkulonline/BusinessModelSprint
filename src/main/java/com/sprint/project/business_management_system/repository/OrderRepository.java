package com.sprint.project.business_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.business_management_system.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
