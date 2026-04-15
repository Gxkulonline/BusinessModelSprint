package com.sprint.project.business_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.project.business_management_system.Entity.Order;
import com.sprint.project.business_management_system.repository.OrderRepository;
import com.sprint.project.business_management_system.service.OrderService;


//@RestController
//@RequestMapping("/orders")
//public class OrderController {
//
//    @Autowired
//    private OrderService service;
//
//    @GetMapping
//    public List<Order> getAll() {
//        return service.getAll();
//    }
//
//    @GetMapping("/{id}")
//    public Order getById(@PathVariable int id) {
//        return service.getById(id);
//    }
//
//    @PostMapping
//    public Order save(@RequestBody Order o) {
//        return service.save(o);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable int id) {
//        service.delete(id);
//    }
//}

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository repo;

    @GetMapping
    public List<Order> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable int id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public Order save(@RequestBody Order o) {
        return repo.save(o);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repo.deleteById(id);
    }
}

