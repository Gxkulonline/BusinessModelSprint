package com.sprint.project.business_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<Office> getAll() {
        return service.getAllOffices();
    }

    @PostMapping
    public Office save(@Valid @RequestBody OfficeRequestDto dto) {

        Office office = new Office();

        // ===== MAPPING DTO → ENTITY =====
        office.setOfficeCode(dto.getOfficeCode());
        office.setCity(dto.getCity());
        office.setPhone(dto.getPhone());
        office.setAddressLine1(dto.getAddressLine1());
        office.setAddressLine2(dto.getAddressLine2());
        office.setState(dto.getState());
        office.setCountry(dto.getCountry());
        office.setPostalCode(dto.getPostalCode());
        office.setTerritory(dto.getTerritory());

        return service.saveOffice(office);
    }
}