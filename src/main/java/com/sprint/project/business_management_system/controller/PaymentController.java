package com.sprint.project.business_management_system.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public PaymentResponseDto createPayment(@Valid @RequestBody PaymentRequestDto payment) {
        return paymentService.createPayment(payment);
    }

    @GetMapping
    public List<PaymentResponseDto> getAll() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/customer/{customerId}")
    public List<PaymentResponseDto> getByCustomer(@PathVariable Integer customerId) {
        return paymentService.getPaymentsByCustomer(customerId);
    }

    @GetMapping("/customer/{customerId}/total")
    public BigDecimal getTotal(@PathVariable Integer customerId) {
        return paymentService.getTotalAmountByCustomer(customerId);
    }
}