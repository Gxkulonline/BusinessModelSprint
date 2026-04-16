package com.sprint.project.business_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sprint.project.business_management_system.Entity.Employee;
import com.sprint.project.business_management_system.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;  

    // Create employee
    @PostMapping
    public Employee save(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    // Get all employees
    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAllEmployees();
    }

    // Get employee by ID
    @GetMapping("/{id}")
    public Employee getById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    // Get manager of employee
    @GetMapping("/{id}/manager")
    public Employee getManager(@PathVariable Integer id) {
        return employeeService.getManager(id);
    }

    // Get employees under a manager
    @GetMapping("/{id}/subordinates")
    public List<Employee> getSubordinates(@PathVariable Integer id) {
        return employeeService.getSubordinates(id);
    }
}