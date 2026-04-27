package com.sprint.project.business_management_system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.sprint.project.business_management_system.requestDto.ProductRequestDto;
import com.sprint.project.business_management_system.responseDto.ProductResponseDto;
import com.sprint.project.business_management_system.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    // ✅ GET ALL -> 200 OK
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll() {
        List<ProductResponseDto> products = service.getAllProducts();

        return ResponseEntity.ok(
            Map.of(
                "status", "success",
                "message", "Products fetched successfully",
                "data", products
            )
        );
    }

    // ✅ GET BY ID -> 200 OK
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable String id) {
        ProductResponseDto product = service.getProductById(id);

        return ResponseEntity.ok(
            Map.of(
                "status", "success",
                "message", "Product fetched successfully",
                "data", product
            )
        );
    }

    // ✅ POST -> 201 CREATED
    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody ProductRequestDto productDto) {
        ProductResponseDto saved = service.saveProduct(productDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(
            Map.of(
                "status", "success",
                "message", "Product created successfully",
                "data", saved
            )
        );
    }

    // ✅ DELETE -> 200 OK
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable String id) {
        service.deleteProduct(id);
        return ResponseEntity.ok(
            Map.of(
                "status", "success",
                "message", "Product deleted successfully"
            )
        );
    }
}
