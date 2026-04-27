
package com.sprint.project.business_management_system.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.sprint.project.business_management_system.Entity.ProductLine;
import com.sprint.project.business_management_system.repository.ProductLineRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/productlines")
public class ProductLineController {

    @Autowired
    private ProductLineRepository productLineRepository;

    // ✅ POST -> 201 CREATED
    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody ProductLine productLine) {

        ProductLine saved = productLineRepository.save(productLine);

        return ResponseEntity.status(HttpStatus.CREATED).body(
            Map.of(
                "status", "success",
                "message", "Product line created successfully",
                "data", saved
            )
        );
    }

    // ✅ GET ALL -> 200 OK
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll() {

        Iterable<ProductLine> list = productLineRepository.findAll();

        return ResponseEntity.ok(
            Map.of(
                "status", "success",
                "message", "Product lines fetched successfully",
                "data", list
            )
        );
    }
}
