package com.sprint.project.business_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.business_management_system.Entity.Employee;
import com.sprint.project.business_management_system.Entity.Office;
import com.sprint.project.business_management_system.repository.EmployeeRepository;
import com.sprint.project.business_management_system.repository.OfficeRepository;

@Service
public class OfficeService {

    @Autowired
    private OfficeRepository officeRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    public Office saveOffice(Office office) {
        return officeRepo.save(office);
    }

    public List<Office> getAllOffices() {
        return officeRepo.findAll();
    }

    public Office getOfficeById(String id) {
        return officeRepo.findById(id).orElse(null);
    }

    public List<Employee> getEmployeesInOffice(String officeCode) {
        return employeeRepo.findByOfficeOfficeCode(officeCode);
    }
}
