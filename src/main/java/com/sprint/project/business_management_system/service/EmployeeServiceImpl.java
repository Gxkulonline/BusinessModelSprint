package com.sprint.project.business_management_system.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.business_management_system.entity.Employee;
import com.sprint.project.business_management_system.entity.Office;
import com.sprint.project.business_management_system.repository.EmployeeRepository;
import com.sprint.project.business_management_system.repository.OfficeRepository;
import com.sprint.project.business_management_system.requestDto.EmployeeRequestDto;
import com.sprint.project.business_management_system.responseDto.EmployeeResponseDto;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private OfficeRepository officeRepo;

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
                .orElseThrow();
        e.setOffice(office);

        // Manager mapping
        if (dto.getReportsTo() != null) {
            Employee manager = employeeRepo.findById(dto.getReportsTo())
                    .orElseThrow();
            e.setManager(manager);
        }

        return e;
    }

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
        Employee e = employeeRepo.findById(id).orElseThrow();
        return mapToDto(e);
    }

    public EmployeeResponseDto save(EmployeeRequestDto dto) {
        Employee saved = employeeRepo.save(mapToEntity(dto));
        return mapToDto(saved);
    }
}
//@Service
//public class EmployeeServiceImpl implements EmployeeService {
//    @Autowired
//    private EmployeeRepository repo;
//    public Employee saveEmployee(Employee e) {
//        return repo.save(e);
//    }
//    public List<Employee> getAllEmployees() {
//        return repo.findAll();
//    }
//    public Employee getEmployeeById(Integer id) {
//        return repo.findById(id).orElse(null);
//    }
//    public Employee getManager(Integer id) {
//        Employee emp = repo.findById(id).orElseThrow();
//        return emp.getManager();
//    }
//    public List<Employee> getSubordinates(Integer id) {
//        return repo.findByManagerEmployeeNumber(id);
//    }
//}