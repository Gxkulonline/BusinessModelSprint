package com.sprint.project.business_management_system.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sprint.project.business_management_system.Entity.Customer;
import com.sprint.project.business_management_system.repository.CustomerRepository;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository repo;

    @GetMapping
    public List<Customer> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable int id) {return repo.findById(id).orElse(null);
    }

    @PostMapping
    public Customer save(@RequestBody Customer c) {
        return repo.save(c);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repo.deleteById(id);
    }
}