package com.sprint.project.business_management_system.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.project.business_management_system.entity.Customer;
import com.sprint.project.business_management_system.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	List<Order> findByCustomer(Customer customer);
	  List<Order> findByStatus(String status);
    List<Order> findByCustomerCustomerNumberAndStatus(Integer customerNumber, String status);
    @Query("SELECT o FROM Order o WHERE o.orderDate BETWEEN :start AND :end")
    List<Order> findOrdersBetweenDates(@Param("start") Date start, @Param("end") Date end);
}
