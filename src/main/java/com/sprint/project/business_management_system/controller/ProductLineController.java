package com.sprint.project.business_management_system.controller;


import com.sprint.project.business_management_system.entity.ProductLine;
import com.sprint.project.business_management_system.repository.ProductLineRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productlines")
@CrossOrigin(origins = "*")
public class ProductLineController {

    @Autowired
    private ProductLineRepository productLineRepository;

    // ✅ Save ProductLine
    @PostMapping
    public ProductLine save(@Valid @RequestBody ProductLine productLine) {
        return productLineRepository.save(productLine);
    }

    // ✅ Get all ProductLines (optional but useful)
    @GetMapping
    public Iterable<ProductLine> getAll() {
        return productLineRepository.findAll();
    }
}
