package com.sprint.project.business_management_system.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.business_management_system.entity.Customer;
import com.sprint.project.business_management_system.entity.Employee;
import com.sprint.project.business_management_system.repository.CustomerRepository;
import com.sprint.project.business_management_system.repository.EmployeeRepository;
import com.sprint.project.business_management_system.requestDto.CustomerRequestDto;
import com.sprint.project.business_management_system.responseDto.CustomerResponseDto;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repo;

    @Autowired
    private EmployeeRepository employeeRepo;

    private Customer mapToEntity(CustomerRequestDto dto) {
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
                    .orElseThrow();

            c.setSalesRep(emp);
        }

        return c;
    }

    private CustomerResponseDto mapToDto(Customer c) {
        CustomerResponseDto dto = new CustomerResponseDto();

        dto.setCustomerNumber(c.getCustomerNumber());
        dto.setCustomerName(c.getCustomerName());
        dto.setCountry(c.getCountry());
        dto.setPhone(c.getPhone());
        dto.setCreditLimit(c.getCreditLimit());

        return dto;
    }

    public List<CustomerResponseDto> getAllCustomers() {
        return repo.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public CustomerResponseDto getCustomerById(Integer id) {
        return mapToDto(repo.findById(id).orElseThrow());
    }

    public CustomerResponseDto saveCustomer(CustomerRequestDto dto) {
        return mapToDto(repo.save(mapToEntity(dto)));
    }

    public void deleteCustomer(Integer id) {
        repo.deleteById(id);
    }
}
//package com.sprint.project.business_management_system.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.sprint.project.business_management_system.Entity.Customer;
//import com.sprint.project.business_management_system.Entity.Employee;
//import com.sprint.project.business_management_system.Entity.Order;
//import com.sprint.project.business_management_system.repository.CustomerRepository;
//import com.sprint.project.business_management_system.repository.OrderRepository;
//
//@Service
//public class CustomerServiceImpl implements CustomerService{
//
//    @Autowired
//    private CustomerRepository customerRepo;
//
//    @Autowired
//    private OrderRepository orderRepo;
//
//    // 1. Get customers by country
//    public List<Customer> getCustomersByCountry(String country) {
//
//        if (country == null || country.isBlank()) {
//            throw new RuntimeException("Country cannot be empty");
//        }
//
//        return customerRepo.findByCountry(country);
//    }
//
//    // 2. Get top customers (by credit limit)
//    public List<Customer> getTopCustomers() {
//        return customerRepo.findAllByOrderByCreditLimitDesc();
//    }
//
//    // 3. Get orders by customer
//    public List<Order> getOrdersByCustomer(Integer customerId) {
//
//        Customer customer = customerRepo.findById(customerId)
//                .orElseThrow(() -> new RuntimeException("Customer not found"));
//
//        return orderRepo.findByCustomer(customer);
//    }
//
//    // 4. Get orders by status
//    public List<Order> getOrdersByCustomerIdAndStatus(Integer customerId, String status) {
//
//        customerRepo.findById(customerId)
//                .orElseThrow(() -> new RuntimeException("Customer not found"));
//
//        return orderRepo.findByCustomerCustomerNumberAndStatus(customerId, status);
//    }
//
//    // 5. Get support (sales rep)
//    public Employee getCustomerSupport(Integer customerId) {
//
//        Customer customer = customerRepo.findById(customerId)
//                .orElseThrow(() -> new RuntimeException("Customer not found"));
//
//        Employee emp = customer.getSalesRep();
//
//        if (emp == null) {
//            throw new RuntimeException("No support assigned");
//        }
//
//        return emp;
//    }
//    @Override
//    public List<Customer> getAllCustomers() {
//        return customerRepo.findAll();
//    }
//
//    @Override
//    public Customer getCustomerById(Integer id) {
//        return customerRepo.findById(id)
//                .orElseThrow(() -> new RuntimeException("Customer not found"));
//    }
//
//    @Override
//    public Customer saveCustomer(Customer customer) {
//        return customerRepo.save(customer);
//    }
//
//    @Override
//    public void deleteCustomer(Integer id) {
//        customerRepo.deleteById(id);
//    }
//}