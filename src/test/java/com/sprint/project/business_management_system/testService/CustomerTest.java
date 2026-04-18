package com.sprint.project.business_management_system.testService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.sprint.project.business_management_system.Entity.Customer;
import com.sprint.project.business_management_system.Entity.Employee;
import com.sprint.project.business_management_system.repository.CustomerRepository;
import com.sprint.project.business_management_system.repository.EmployeeRepository;
import com.sprint.project.business_management_system.requestDto.CustomerRequestDto;
import com.sprint.project.business_management_system.requestDto.CustomerRequestDto.SalesRepDto;
import com.sprint.project.business_management_system.service.CustomerServiceImpl;

class CustomerTest {

    @InjectMocks
    private CustomerServiceImpl service;

    @Mock
    private CustomerRepository repo;

    @Mock
    private EmployeeRepository employeeRepo;

    private Customer customer;
    private CustomerRequestDto dto;
    private Employee employee;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        employee = new Employee();
        employee.setEmployeeNumber(101);

        customer = new Customer();
        customer.setCustomerNumber(1);
        customer.setCustomerName("ABC Pvt Ltd");
        customer.setCountry("India");
        customer.setPhone("9999999999");
        customer.setCreditLimit(new BigDecimal("50000"));

        dto = new CustomerRequestDto();
        dto.setCustomerNumber(1);
        dto.setCustomerName("ABC Pvt Ltd");
        dto.setCountry("India");
        dto.setPhone("9999999999");
        dto.setCreditLimit(new BigDecimal("50000"));
    }

    // -------- TOP 10 TEST CASES --------

    // 1. Get all customers - success
    @Test
    void testGetAllCustomers() {
        when(repo.findAll()).thenReturn(List.of(customer));
        assertEquals(1, service.getAllCustomers().size());
    }

    // 2. Get all customers - empty list
    @Test
    void testGetAllCustomersEmpty() {
        when(repo.findAll()).thenReturn(new ArrayList<>());
        assertTrue(service.getAllCustomers().isEmpty());
    }

    // 3. Get customer by ID - success
    @Test
    void testGetCustomerById() {
        when(repo.findById(1)).thenReturn(Optional.of(customer));
        assertEquals("ABC Pvt Ltd", service.getCustomerById(1).getCustomerName());
    }

    // 4. Get customer by ID - not found
    @Test
    void testGetCustomerByIdNotFound() {
        when(repo.findById(1)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> service.getCustomerById(1));
    }

    // 5. Save customer - basic
    @Test
    void testSaveCustomer() {
        when(repo.save(any())).thenReturn(customer);
        assertNotNull(service.saveCustomer(dto));
    }

    // 6. Save customer - verify repository call
    @Test
    void testSaveCustomerVerify() {
        when(repo.save(any())).thenReturn(customer);
        service.saveCustomer(dto);
        verify(repo).save(any());
    }

    // 7. Save customer with SalesRep - success
    @Test
    void testSaveCustomerWithSalesRep() {
        SalesRepDto rep = new SalesRepDto();
        rep.setEmployeeNumber(101);
        dto.setSalesRepEmployee(rep);

        when(employeeRepo.findById(101)).thenReturn(Optional.of(employee));
        when(repo.save(any())).thenReturn(customer);

        assertNotNull(service.saveCustomer(dto));
    }

    // 8. Save customer with SalesRep - employee not found
    @Test
    void testSaveCustomerEmployeeNotFound() {
        SalesRepDto rep = new SalesRepDto();
        rep.setEmployeeNumber(101);
        dto.setSalesRepEmployee(rep);

        when(employeeRepo.findById(101)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> service.saveCustomer(dto));
    }

    // 9. Delete customer - success
    @Test
    void testDeleteCustomer() {
        doNothing().when(repo).deleteById(1);
        service.deleteCustomer(1);
        verify(repo).deleteById(1);
    }

    // 10. Delete customer - no exception
    @Test
    void testDeleteCustomerNoException() {
        doNothing().when(repo).deleteById(1);
        assertDoesNotThrow(() -> service.deleteCustomer(1));
    }
}