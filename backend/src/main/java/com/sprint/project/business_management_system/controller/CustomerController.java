//package com.sprint.project.business_management_system.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import com.sprint.project.business_management_system.requestDto.CustomerRequestDto;
//import com.sprint.project.business_management_system.responseDto.CustomerResponseDto;
//import com.sprint.project.business_management_system.service.CustomerService;
//
//import jakarta.validation.Valid;
//
//@RestController
//@RequestMapping("/customers")
//public class CustomerController {
//
//    @Autowired
//    private CustomerService service;
//
//    @GetMapping
//    public List<CustomerResponseDto> getAll() {
//        return service.getAllCustomers();
//    }
//
//    @GetMapping("/{id}")
//    public CustomerResponseDto getById(@PathVariable Integer id) {
//        return service.getCustomerById(id);
//    }
//
//    @PostMapping
//    public CustomerResponseDto save(@Valid @RequestBody CustomerRequestDto dto) {
//        return service.saveCustomer(dto);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Integer id) {
//        service.deleteCustomer(id);
//    }
//}
//package com.sprint.project.business_management_system.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import com.sprint.project.business_management_system.requestDto.CustomerRequestDto;
//import com.sprint.project.business_management_system.responseDto.CustomerResponseDto;
//import com.sprint.project.business_management_system.service.CustomerService;
//
//import jakarta.validation.Valid;
//
//@RestController
//@RequestMapping("/customers")
//public class CustomerController {
//
//    @Autowired
//    private CustomerService service;
//
//    // ✅ GET ALL -> 200 OK
//    @GetMapping
//    public ResponseEntity<List<CustomerResponseDto>> getAll() {
//        return ResponseEntity.ok(service.getAllCustomers());
//    }
//
//    // ✅ GET BY ID -> 200 OK
//    @GetMapping("/{id}")
//    public ResponseEntity<CustomerResponseDto> getById(@PathVariable Integer id) {
//        return ResponseEntity.ok(service.getCustomerById(id));
//    }
//
//    // ✅ POST -> 201 CREATED
//    @PostMapping
//    public ResponseEntity<CustomerResponseDto> save(@Valid @RequestBody CustomerRequestDto dto) {
//        CustomerResponseDto saved = service.saveCustomer(dto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
//    }
//
//    // ✅ DELETE -> 204 NO CONTENT
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Integer id) {
//        service.deleteCustomer(id);
//        return ResponseEntity.noContent().build();
//    }
//}
package com.sprint.project.business_management_system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.sprint.project.business_management_system.requestDto.CustomerRequestDto;
import com.sprint.project.business_management_system.responseDto.CustomerResponseDto;
import com.sprint.project.business_management_system.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    // ✅ GET ALL -> 200 OK
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll() {
        List<CustomerResponseDto> customers = service.getAllCustomers();

        return ResponseEntity.ok(
            Map.of(
                "status", "success",
                "message", "Customers fetched successfully",
                "data", customers
            )
        );
    }

    // ✅ GET BY ID -> 200 OK
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable Integer id) {
        CustomerResponseDto customer = service.getCustomerById(id);

        return ResponseEntity.ok(
            Map.of(
                "status", "success",
                "message", "Customer fetched successfully",
                "data", customer
            )
        );
    }

    // ✅ POST -> 201 CREATED
    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody CustomerRequestDto dto) {
        CustomerResponseDto saved = service.saveCustomer(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(
            Map.of(
                "status", "success",
                "message", "Customer created successfully",
                "data", saved
            )
        );
    }

    // ✅ DELETE -> 200 OK (with JSON)
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Integer id) {
        service.deleteCustomer(id);

        return ResponseEntity.ok(
            Map.of(
                "status", "success",
                "message", "Customer deleted successfully"
            )
        );
    }
}