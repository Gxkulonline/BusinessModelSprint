package com.sprint.project.business_management_system.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.project.business_management_system.Entity.Payment;
import com.sprint.project.business_management_system.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService service;

    // ✅ Get all payments
    @GetMapping
    public List<Payment> getAll() {
        return service.getAllPayments();
    }

    // ✅ Get payments by customer
    @GetMapping("/customer/{id}")
    public List<Payment> getByCustomer(@PathVariable Integer id) {
        return service.getPaymentsByCustomer(id);
    }

    // ✅ OPTIONAL (simple total amount)
    @GetMapping("/total/{id}")
    public BigDecimal getTotalAmount(@PathVariable Integer id) {
        return service.getTotalAmountByCustomer(id);
    }
}