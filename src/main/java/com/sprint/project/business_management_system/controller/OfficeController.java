package com.sprint.project.business_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.project.business_management_system.Entity.Office;
import com.sprint.project.business_management_system.service.OfficeService;
import java.util.List;
@RestController
@RequestMapping("/offices")
public class OfficeController {

    @Autowired
    private OfficeService service;

    @GetMapping
    public List<Office> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Office getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    public Office save(@RequestBody Office o) {
        return service.save(o);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}

