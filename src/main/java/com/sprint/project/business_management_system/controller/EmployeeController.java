package com.sprint.project.business_management_system.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.project.business_management_system.Entity.Employee;
import com.sprint.project.business_management_system.service.EmployeeService;
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService service;
    @GetMapping
    public List<Employee> getAll() {
        return service.getAll();
    }
    @GetMapping("/{id}")
    public Employee getById(@PathVariable int id) {
        return service.getById(id);
    }
    @PostMapping
    public Employee save(@RequestBody Employee e) {
        return service.save(e);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}