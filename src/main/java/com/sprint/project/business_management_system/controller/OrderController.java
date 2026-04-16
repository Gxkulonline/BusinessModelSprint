package com.sprint.project.business_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sprint.project.business_management_system.Entity.Order;
import com.sprint.project.business_management_system.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;   // ✅ use interface

    // Get order with details by ID
    @GetMapping("/{id}")
    public Order getOrderWithDetails(@PathVariable Integer id) {
        return orderService.getOrderWithDetails(id);
    }

    // Get orders by status
    @GetMapping
    public List<Order> getOrdersByStatus(@RequestParam String status) {
        return orderService.getOrdersByStatus(status);
    }
}
