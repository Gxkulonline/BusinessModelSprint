
package com.sprint.project.business_management_system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.sprint.project.business_management_system.requestDto.EmployeeRequestDto;
import com.sprint.project.business_management_system.responseDto.EmployeeResponseDto;
import com.sprint.project.business_management_system.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    // ✅ GET ALL -> 200 OK
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll() {
        List<EmployeeResponseDto> employees = service.getAll();

        return ResponseEntity.ok(
            Map.of(
                "status", "success",
                "message", "Employees fetched successfully",
                "data", employees
            )
        );
    }

    // ✅ GET BY ID -> 200 OK
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable Integer id) {
        EmployeeResponseDto employee = service.getById(id);

        return ResponseEntity.ok(
            Map.of(
                "status", "success",
                "message", "Employee fetched successfully",
                "data", employee
            )
        );
    }

    // ✅ POST -> 201 CREATED
    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody EmployeeRequestDto dto) {
        EmployeeResponseDto saved = service.save(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(
            Map.of(
                "status", "success",
                "message", "Employee created successfully",
                "data", saved
            )
        );
    }

    // ✅ DELETE -> 200 OK
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok(
            Map.of(
                "status", "success",
                "message", "Employee deleted successfully"
            )
        );
    }
}

