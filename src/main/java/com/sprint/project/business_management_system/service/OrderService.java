package com.sprint.project.business_management_system.service;

import java.util.List;

import com.sprint.project.business_management_system.Entity.Order;

public interface OrderService {
	 Order getOrderWithDetails(Integer id);
	 Order createOrder(Order order);

	    List<Order> getOrdersByStatus(String status);
}
