package com.sprint.project.business_management_system.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sprint.project.business_management_system.entity.Payment;
import com.sprint.project.business_management_system.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    
    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }

    // Get all payments
    @GetMapping
    public List<Payment> getAll() {
        return paymentService.getAllPayments();
    }

    // Get payments by customer
    @GetMapping("/customer/{customerId}")
    public List<Payment> getByCustomer(@PathVariable Integer customerId) {
        return paymentService.getPaymentsByCustomer(customerId);
    }

    // Get total payment amount by customer
    @GetMapping("/customer/{customerId}/total")
    public BigDecimal getTotal(@PathVariable Integer customerId) {
        return paymentService.getTotalAmountByCustomer(customerId);
    }
}