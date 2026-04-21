//package com.sprint.project.business_management_system.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import com.sprint.project.business_management_system.Entity.Office;
//import com.sprint.project.business_management_system.requestDto.OfficeRequestDto;
//import com.sprint.project.business_management_system.service.OfficeService;
//
//import jakarta.validation.Valid;
//
//@RestController
//@RequestMapping("/offices")
//public class OfficeController {
//
//    @Autowired
//    private OfficeService service;
//
//    @GetMapping
//    public List<Office> getAll() {
//        return service.getAllOffices();
//    }
//
//    @PostMapping
//    public Office save(@Valid @RequestBody OfficeRequestDto dto) {
//
//        Office office = new Office();
//
//        // ===== MAPPING DTO → ENTITY =====
//        office.setOfficeCode(dto.getOfficeCode());
//        office.setCity(dto.getCity());
//        office.setPhone(dto.getPhone());
//        office.setAddressLine1(dto.getAddressLine1());
//        office.setAddressLine2(dto.getAddressLine2());
//        office.setState(dto.getState());
//        office.setCountry(dto.getCountry());
//        office.setPostalCode(dto.getPostalCode());
//        office.setTerritory(dto.getTerritory());
//
//        return service.saveOffice(office);
//    }
//}
package com.sprint.project.business_management_system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.sprint.project.business_management_system.Entity.Office;
import com.sprint.project.business_management_system.requestDto.OfficeRequestDto;
import com.sprint.project.business_management_system.service.OfficeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/offices")
public class OfficeController {

    @Autowired
    private OfficeService service;

    // ✅ GET ALL -> 200 OK
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll() {
        List<Office> offices = service.getAllOffices();

        return ResponseEntity.ok(
            Map.of(
                "status", "success",
                "message", "Offices fetched successfully",
                "data", offices
            )
        );
    }

    // ✅ POST -> 201 CREATED
    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody OfficeRequestDto dto) {

        Office office = new Office();

        // DTO → Entity mapping
        office.setOfficeCode(dto.getOfficeCode());
        office.setCity(dto.getCity());
        office.setPhone(dto.getPhone());
        office.setAddressLine1(dto.getAddressLine1());
        office.setAddressLine2(dto.getAddressLine2());
        office.setState(dto.getState());
        office.setCountry(dto.getCountry());
        office.setPostalCode(dto.getPostalCode());
        office.setTerritory(dto.getTerritory());

        Office saved = service.saveOffice(office);

        return ResponseEntity.status(HttpStatus.CREATED).body(
            Map.of(
                "status", "success",
                "message", "Office created successfully",
                "data", saved
            )
        );
    }
}