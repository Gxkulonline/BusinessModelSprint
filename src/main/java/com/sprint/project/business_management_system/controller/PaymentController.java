package com.sprint.project.business_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.project.business_management_system.Entity.Payment;
import com.sprint.project.business_management_system.Entity.PaymentId;
import com.sprint.project.business_management_system.service.PaymentService;

import com.sprint.project.business_management_system.service.PaymentService;
@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @GetMapping
    public List<Payment> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Payment save(@RequestBody Payment p) {
        return service.save(p);
    }

    @DeleteMapping
    public void delete(@RequestBody PaymentId id) {
        service.delete(id);
    }

}