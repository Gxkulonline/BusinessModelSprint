
package com.sprint.project.business_management_system.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.sprint.project.business_management_system.requestDto.PaymentRequestDto;
import com.sprint.project.business_management_system.responseDto.PaymentResponseDto;
import com.sprint.project.business_management_system.service.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // ✅ POST -> 201 CREATED
    @PostMapping
    public ResponseEntity<Map<String, Object>> createPayment(@Valid @RequestBody PaymentRequestDto payment) {

        PaymentResponseDto saved = paymentService.createPayment(payment);

        return ResponseEntity.status(HttpStatus.CREATED).body(
            Map.of(
                "status", "success",
                "message", "Payment created successfully",
                "data", saved
            )
        );
    }

    // ✅ GET ALL -> 200 OK
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll() {

        List<PaymentResponseDto> list = paymentService.getAllPayments();

        return ResponseEntity.ok(
            Map.of(
                "status", "success",
                "message", "Payments fetched successfully",
                "data", list
            )
        );
    }

    // ✅ GET BY CUSTOMER -> 200 OK
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Map<String, Object>> getByCustomer(@PathVariable Integer customerId) {

        List<PaymentResponseDto> list = paymentService.getPaymentsByCustomer(customerId);

        return ResponseEntity.ok(
            Map.of(
                "status", "success",
                "message", "Payments fetched successfully",
                "data", list
            )
        );
    }

    // ✅ GET TOTAL -> 200 OK
    @GetMapping("/customer/{customerId}/total")
    public ResponseEntity<Map<String, Object>> getTotal(@PathVariable Integer customerId) {

        BigDecimal total = paymentService.getTotalAmountByCustomer(customerId);

        return ResponseEntity.ok(
            Map.of(
                "status", "success",
                "message", "Total payment calculated successfully",
                "data", total
            )
        );
    }
}