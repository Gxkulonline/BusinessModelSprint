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
<<<<<<< HEAD

import com.sprint.project.business_management_system.entity.Customer;
=======
import com.sprint.project.business_management_system.Entity.Customer;
import com.sprint.project.business_management_system.requestDto.CustomerRequestDto;
import com.sprint.project.business_management_system.responseDto.CustomerResponseDto;
>>>>>>> refs/heads/master
import com.sprint.project.business_management_system.service.CustomerService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping
    public List<CustomerResponseDto> getAll() {
        return service.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerResponseDto getById(@PathVariable Integer id) {
        return service.getCustomerById(id);
    }

    @PostMapping
    public CustomerResponseDto save(@RequestBody CustomerRequestDto dto) {
        return service.saveCustomer(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.deleteCustomer(id);
    }
}

//@RestController
//@RequestMapping("/customers")
//public class CustomerController {
//
//    @Autowired
//    private CustomerService customerService;
//
//    // 1. Get all customers
//    @GetMapping
//    public List<Customer> getAllCustomers() {
//        return customerService.getAllCustomers();
//    }
//
//    // 2. Get customer by ID
//    @GetMapping("/{id}")
//    public Customer getCustomerById(@PathVariable Integer id) {
//        return customerService.getCustomerById(id);
//    }
//
//    // 3. Save customer
//    @PostMapping
//    public Customer saveCustomer(@Valid @RequestBody Customer customer) {
//        return customerService.saveCustomer(customer);
//    }
//
//    // 4. Delete customer
//    @DeleteMapping("/{id}")
//    public void deleteCustomer(@PathVariable Integer id) {
//        customerService.deleteCustomer(id);
//    }
//}