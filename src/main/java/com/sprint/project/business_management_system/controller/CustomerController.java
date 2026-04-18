package com.sprint.project.business_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sprint.project.business_management_system.requestDto.CustomerRequestDto;
import com.sprint.project.business_management_system.responseDto.CustomerResponseDto;
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
    public CustomerResponseDto save(@Valid @RequestBody CustomerRequestDto dto) {
        return service.saveCustomer(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.deleteCustomer(id);
    }
}