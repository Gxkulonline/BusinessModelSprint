package com.sprint.project.business_management_system.service;

import java.util.List;
import com.sprint.project.business_management_system.requestDto.CustomerRequestDto;
import com.sprint.project.business_management_system.responseDto.CustomerResponseDto;

public interface CustomerService {

    List<CustomerResponseDto> getAllCustomers();

    CustomerResponseDto getCustomerById(Integer id);

    CustomerResponseDto saveCustomer(CustomerRequestDto dto);

    void deleteCustomer(Integer id);
}
