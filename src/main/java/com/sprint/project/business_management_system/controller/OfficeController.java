package com.sprint.project.business_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sprint.project.business_management_system.Entity.Office;
import com.sprint.project.business_management_system.service.OfficeService;

@RestController
@RequestMapping("/offices")
public class OfficeController {
	

    @Autowired
    private OfficeService service;   // ✅ interface injection

    @GetMapping
    public List<Office> getAll() {
        return service.getAllOffices();
    }

    @PostMapping
    public Office save(@RequestBody Office office) {
        return service.saveOffice(office);
    }
}

