//package com.sprint.project.business_management_system.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import com.sprint.project.business_management_system.requestDto.OrderRequestDto;
//import com.sprint.project.business_management_system.responseDto.OrderResponseDto;
//import com.sprint.project.business_management_system.service.OrderService;
//
//import jakarta.validation.Valid;
//
//@RestController
//@RequestMapping("/orders")
//public class OrderController {
//
//    @Autowired
//    private OrderService service;
//
//    @PostMapping
//    public OrderResponseDto create(@Valid @RequestBody OrderRequestDto dto) {
//        return service.createOrder(dto);
//    }
//
//    @GetMapping("/{id}")
//    public OrderResponseDto getById(@PathVariable Integer id) {
//        return service.getOrderById(id);
//    }
//
//    @GetMapping
//    public List<OrderResponseDto> getByStatus(@RequestParam String status) {
//        return service.getOrdersByStatus(status);
//    }
//}
package com.sprint.project.business_management_system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
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

    // ✅ POST -> 201 CREATED
    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody OrderRequestDto dto) {

        OrderResponseDto saved = service.createOrder(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(
            Map.of(
                "status", "success",
                "message", "Order created successfully",
                "data", saved
            )
        );
    }

    // ✅ GET BY ID -> 200 OK
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable Integer id) {

        OrderResponseDto order = service.getOrderById(id);

        return ResponseEntity.ok(
            Map.of(
                "status", "success",
                "message", "Order fetched successfully",
                "data", order
            )
        );
    }

    // ✅ GET BY STATUS -> 200 OK
    @GetMapping
    public ResponseEntity<Map<String, Object>> getByStatus(@RequestParam String status) {

        List<OrderResponseDto> orders = service.getOrdersByStatus(status);

        return ResponseEntity.ok(
            Map.of(
                "status", "success",
                "message", "Orders fetched successfully",
                "data", orders
            )
        );
    }
}