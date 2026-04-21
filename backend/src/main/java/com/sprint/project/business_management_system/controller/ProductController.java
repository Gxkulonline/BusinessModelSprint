//package com.sprint.project.business_management_system.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.sprint.project.business_management_system.Entity.Product;
//import com.sprint.project.business_management_system.service.ProductServiceImpl;
//
//@RestController
//@RequestMapping("/products")
//public class ProductController {
//
//    @Autowired
//    private ProductServiceImpl service;
//
//    @GetMapping
//    public List<Product> getAll() {
//        return service.getAllProducts();
//    }
//
//    @GetMapping("/{id}")
//    public Product getById(@PathVariable String id) {
//        return service.getProductById(id);
//    }
//
//    @PostMapping
//    public Product save(@RequestBody Product product) {
//        return service.saveProduct(product);
//    }
//}
package com.sprint.project.business_management_system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.sprint.project.business_management_system.Entity.Product;
import com.sprint.project.business_management_system.impl.ProductServiceImpl;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl service;

    // ✅ GET ALL -> 200 OK
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll() {

        List<Product> products = service.getAllProducts();

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

        Product product = service.getProductById(id);

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
    public ResponseEntity<Map<String, Object>> save(@RequestBody Product product) {

        Product saved = service.saveProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(
            Map.of(
                "status", "success",
                "message", "Product created successfully",
                "data", saved
            )
        );
    }
}
