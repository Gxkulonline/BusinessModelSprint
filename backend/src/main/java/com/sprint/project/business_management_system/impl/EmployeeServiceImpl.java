package com.sprint.project.business_management_system.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.business_management_system.Entity.Employee;
import com.sprint.project.business_management_system.Entity.Office;
import com.sprint.project.business_management_system.exception.ResourceNotFoundException;
import com.sprint.project.business_management_system.repository.EmployeeRepository;
import com.sprint.project.business_management_system.repository.OfficeRepository;
import com.sprint.project.business_management_system.requestDto.EmployeeRequestDto;
import com.sprint.project.business_management_system.responseDto.EmployeeResponseDto;
import com.sprint.project.business_management_system.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private OfficeRepository officeRepo;
//req
    private Employee mapToEntity(EmployeeRequestDto dto) {
        Employee e = new Employee();

        e.setEmployeeNumber(dto.getEmployeeNumber());
        e.setFirstName(dto.getFirstName());
        e.setLastName(dto.getLastName());
        e.setExtension(dto.getExtension());
        e.setEmail(dto.getEmail());
        e.setJobTitle(dto.getJobTitle());

        // Office mapping
        Office office = officeRepo.findById(dto.getOffice().getOfficeCode())
                .orElseThrow(() -> new ResourceNotFoundException("office not found"));
        e.setOffice(office);

        // Manager mapping
        if (dto.getReportsTo() != null) {
            Employee manager = employeeRepo.findById(dto.getReportsTo())
                    .orElseThrow(() -> new ResourceNotFoundException("Employee Manager not found"));
            e.setManager(manager);
        }

        return e;
    }
//response
    private EmployeeResponseDto mapToDto(Employee e) {
        EmployeeResponseDto dto = new EmployeeResponseDto();

        dto.setEmployeeNumber(e.getEmployeeNumber());
        dto.setFirstName(e.getFirstName());
        dto.setLastName(e.getLastName());
        dto.setJobTitle(e.getJobTitle());

        return dto;
    }

    public List<EmployeeResponseDto> getAll() {
        return employeeRepo.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public EmployeeResponseDto getById(Integer id) {
        Employee e = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee Manager not found"));
        return mapToDto(e);
    }

    public EmployeeResponseDto save(EmployeeRequestDto dto) {
        Employee saved = employeeRepo.save(mapToEntity(dto));
        return mapToDto(saved);
    }

    public void delete(Integer id) {
        if (!employeeRepo.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found with ID: " + id);
        }
        employeeRepo.deleteById(id);
    }
}
