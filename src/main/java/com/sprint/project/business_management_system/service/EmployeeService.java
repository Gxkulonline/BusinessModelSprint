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
    public List<Employee> getAll() {
        return repo.findAll();
    }
    public Employee getById(int id) {
        return repo.findById(id).orElse(null);
    }
    public Employee save(Employee e) {
        return repo.save(e);
    }
    public void delete(int id) {
        repo.deleteById(id);
    }
}