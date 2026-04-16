package com.sprint.project.business_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.business_management_system.Entity.Order;
import com.sprint.project.business_management_system.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepo;

    // Get order by id (with details automatically if mapping is correct)
    public Order getOrderWithDetails(Integer id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    // Get orders by status
    public List<Order> getOrdersByStatus(String status) {
        return orderRepo.findByStatus(status);
    }
}