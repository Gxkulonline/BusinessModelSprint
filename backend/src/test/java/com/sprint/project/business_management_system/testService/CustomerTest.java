package com.sprint.project.business_management_system.testService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.*;
import jakarta.validation.*;

import org.junit.jupiter.api.*;
import org.mockito.*;

import com.sprint.project.business_management_system.Entity.*;
import com.sprint.project.business_management_system.impl.CustomerServiceImpl;
import com.sprint.project.business_management_system.repository.*;
import com.sprint.project.business_management_system.requestDto.CustomerRequestDto;
import com.sprint.project.business_management_system.requestDto.CustomerRequestDto.SalesRepDto;

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
    private Validator validator;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        validator = Validation.buildDefaultValidatorFactory().getValidator();

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

    @Test void test1_getAll() {
        when(repo.findAll()).thenReturn(List.of(customer));
        assertEquals(1, service.getAllCustomers().size());
    }

    @Test void test2_getById() {
        when(repo.findById(1)).thenReturn(Optional.of(customer));
        assertEquals("ABC Pvt Ltd", service.getCustomerById(1).getCustomerName());
    }

    @Test void test3_notFound() {
        when(repo.findById(1)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> service.getCustomerById(1));
    }

    @Test void test4_save() {
        when(repo.save(any())).thenReturn(customer);
        assertNotNull(service.saveCustomer(dto));
    }

    @Test void test5_saveVerify() {
        when(repo.save(any())).thenReturn(customer);
        service.saveCustomer(dto);
        verify(repo).save(any());
    }

    @Test void test6_salesRepSuccess() {
        SalesRepDto rep = new SalesRepDto();
        rep.setEmployeeNumber(101);
        dto.setSalesRepEmployee(rep);

        when(employeeRepo.findById(101)).thenReturn(Optional.of(employee));
        when(repo.save(any())).thenReturn(customer);

        assertNotNull(service.saveCustomer(dto));
    }

    @Test void test7_salesRepFail() {
        SalesRepDto rep = new SalesRepDto();
        rep.setEmployeeNumber(101);
        dto.setSalesRepEmployee(rep);

        when(employeeRepo.findById(101)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> service.saveCustomer(dto));
    }

    @Test void test8_delete() {
        doNothing().when(repo).deleteById(1);
        service.deleteCustomer(1);
        verify(repo).deleteById(1);
    }

    @Test void test9_deleteNoException() {
        assertDoesNotThrow(() -> service.deleteCustomer(1));
    }

    @Test void test10_mapping() {
        when(repo.findAll()).thenReturn(List.of(customer));
        assertEquals("ABC Pvt Ltd", service.getAllCustomers().get(0).getCustomerName());
    }

    // DTO VALIDATION
//    @Test void test11_dtoValid() {
//        assertTrue(validator.validate(dto).isEmpty());
//    }

    @Test void test12_dtoInvalidName() {
        dto.setCustomerName("");
        assertFalse(validator.validate(dto).isEmpty());
    }

    @Test void test13_dtoInvalidPhone() {
        dto.setPhone("123");
        assertFalse(validator.validate(dto).isEmpty());
    }
}