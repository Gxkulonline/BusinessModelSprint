package com.sprint.project.business_management_system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.sprint.project.business_management_system.requestDto.OfficeRequestDto;
import com.sprint.project.business_management_system.responseDto.OfficeResponseDto;
import com.sprint.project.business_management_system.service.OfficeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/offices")
public class OfficeController {

    @Autowired
    private OfficeService service;

    //  GET ALL -> 200 OK
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll() {
        List<OfficeResponseDto> offices = service.getAllOffices();

        return ResponseEntity.ok(
            Map.of(
                "status", "success",
                "message", "Offices fetched successfully",
                "data", offices
            )
        );
    }

    //  POST -> 201 CREATED
    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody OfficeRequestDto dto) {
        OfficeResponseDto saved = service.saveOffice(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(
            Map.of(
                "status", "success",
                "message", "Office created successfully",
                "data", saved
            )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable String id) {
        service.deleteOffice(id);
        return ResponseEntity.ok(
            Map.of(
                "status", "success",
                "message", "Office deleted successfully"
            )
        );
    }
}