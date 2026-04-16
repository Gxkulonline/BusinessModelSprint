package com.sprint.project.business_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.business_management_system.Entity.Employee;
import com.sprint.project.business_management_system.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    public Employee saveEmployee(Employee e) {
        return repo.save(e);
    }

    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    public Employee getEmployeeById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public Employee getManager(Integer id) {
        Employee emp = repo.findById(id).orElseThrow();
        return emp.getManager();
    }

    public List<Employee> getSubordinates(Integer id) {
        return repo.findByManagerEmployeeNumber(id);
    }
}