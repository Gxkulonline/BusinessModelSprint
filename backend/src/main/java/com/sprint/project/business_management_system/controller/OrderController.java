package com.sprint.project.business_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sprint.project.business_management_system.requestDto.OrderRequestDto;
import com.sprint.project.business_management_system.responseDto.OrderResponseDto;
import com.sprint.project.business_management_system.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    public OrderResponseDto create(@Valid @RequestBody OrderRequestDto dto) {
        return service.createOrder(dto);
    }

    @GetMapping("/{id}")
    public OrderResponseDto getById(@PathVariable Integer id) {
        return service.getOrderById(id);
    }

    @GetMapping
    public List<OrderResponseDto> getByStatus(@RequestParam String status) {
        return service.getOrdersByStatus(status);
    }
}