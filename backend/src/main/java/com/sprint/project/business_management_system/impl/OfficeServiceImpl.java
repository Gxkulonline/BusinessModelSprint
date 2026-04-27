package com.sprint.project.business_management_system.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.business_management_system.Entity.Employee;
import com.sprint.project.business_management_system.Entity.Office;
import com.sprint.project.business_management_system.repository.EmployeeRepository;
import com.sprint.project.business_management_system.repository.OfficeRepository;
import com.sprint.project.business_management_system.service.OfficeService;
import com.sprint.project.business_management_system.exception.ResourceNotFoundException;
import com.sprint.project.business_management_system.requestDto.OfficeRequestDto;
import com.sprint.project.business_management_system.responseDto.OfficeResponseDto;

@Service
public class OfficeServiceImpl implements OfficeService {
    @Autowired
    private OfficeRepository officeRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    private Office mapToEntity(OfficeRequestDto dto) {
        Office o = new Office();
        o.setOfficeCode(dto.getOfficeCode());
        o.setCity(dto.getCity());
        o.setPhone(dto.getPhone());
        o.setAddressLine1(dto.getAddressLine1());
        o.setAddressLine2(dto.getAddressLine2());
        o.setState(dto.getState());
        o.setCountry(dto.getCountry());
        o.setPostalCode(dto.getPostalCode());
        o.setTerritory(dto.getTerritory());
        return o;
    }

    private OfficeResponseDto mapToDto(Office o) {
        OfficeResponseDto dto = new OfficeResponseDto();
        dto.setOfficeCode(o.getOfficeCode());
        dto.setCity(o.getCity());
        dto.setCountry(o.getCountry());
        return dto;
    }

    public OfficeResponseDto saveOffice(OfficeRequestDto officeDto) {
        Office office = mapToEntity(officeDto);
        return mapToDto(officeRepo.save(office));
    }

    public List<OfficeResponseDto> getAllOffices() {
        return officeRepo.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public OfficeResponseDto getOfficeById(String id) {
        Office office = officeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Office not found"));
        return mapToDto(office);
    }

    public List<Employee> getEmployeesInOffice(String officeCode) {
        return employeeRepo.findByOfficeOfficeCode(officeCode);
    }

    public void deleteOffice(String id) {
        if (!officeRepo.existsById(id)) {
            throw new ResourceNotFoundException("Office not found with code: " + id);
        }
        officeRepo.deleteById(id);
    }
}
