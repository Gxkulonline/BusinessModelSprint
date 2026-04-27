package com.sprint.project.business_management_system.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import com.sprint.project.business_management_system.Entity.Customer;
import com.sprint.project.business_management_system.Entity.Employee;
import com.sprint.project.business_management_system.exception.BusinessException;
import com.sprint.project.business_management_system.exception.ResourceNotFoundException;
import com.sprint.project.business_management_system.repository.CustomerRepository;
import com.sprint.project.business_management_system.repository.EmployeeRepository;
import com.sprint.project.business_management_system.requestDto.CustomerRequestDto;
import com.sprint.project.business_management_system.responseDto.CustomerResponseDto;
import com.sprint.project.business_management_system.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repo;
    @Autowired
    private EmployeeRepository employeeRepo;
//Converts incoming request data (DTO) into a Customer entity (used for database),“Client language → Database language”
    private Customer mapToEntity(CustomerRequestDto dto) {
        // Business Rule: Credit limit cannot be negative
        if (dto.getCreditLimit() != null && dto.getCreditLimit().compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("Credit limit cannot be negative");
        }

        Customer c = new Customer();

        c.setCustomerNumber(dto.getCustomerNumber());
        c.setCustomerName(dto.getCustomerName());
        c.setContactLastName(dto.getContactLastName());
        c.setContactFirstName(dto.getContactFirstName());
        c.setPhone(dto.getPhone());
        c.setAddressLine1(dto.getAddressLine1());
        c.setAddressLine2(dto.getAddressLine2());
        c.setCity(dto.getCity());
        c.setState(dto.getState());
        c.setPostalCode(dto.getPostalCode());
        c.setCountry(dto.getCountry());
        c.setCreditLimit(dto.getCreditLimit());

        if (dto.getSalesRepEmployee() != null &&
            dto.getSalesRepEmployee().getEmployeeNumber() != null) {

            Employee emp = employeeRepo
                    .findById(dto.getSalesRepEmployee().getEmployeeNumber())
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

            c.setSalesRep(emp);
        }

        return c;
    }
//response dto
    private CustomerResponseDto mapToDto(Customer c) {
        CustomerResponseDto dto = new CustomerResponseDto();

        dto.setCustomerNumber(c.getCustomerNumber());
        dto.setCustomerName(c.getCustomerName());
        dto.setCountry(c.getCountry());
        dto.setPhone(c.getPhone());
        dto.setCreditLimit(c.getCreditLimit());

        return dto;
    }

    //endpoints usage
    public List<CustomerResponseDto> getAllCustomers() {
        return repo.findAll()
                .stream()
//                similar to .map(customer -> mapToDto(customer))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public CustomerResponseDto getCustomerById(Integer id) {
        return mapToDto(repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found")));
    }

    public CustomerResponseDto saveCustomer(CustomerRequestDto dto) {
        return mapToDto(repo.save(mapToEntity(dto)));
    }

    public void deleteCustomer(Integer id) {
        repo.deleteById(id);
    }
}
