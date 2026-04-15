package com.sprint.project.business_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.business_management_system.Entity.Order;
import com.sprint.project.business_management_system.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    public List<Order> getAll() {
        return repo.findAll();
    }

    public Order getById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Order save(Order o) {
        return repo.save(o);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}