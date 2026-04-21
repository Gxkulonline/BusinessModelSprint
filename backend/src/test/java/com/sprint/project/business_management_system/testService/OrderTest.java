package com.sprint.project.business_management_system.testService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.*;
import jakarta.validation.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.sprint.project.business_management_system.Entity.Customer;
import com.sprint.project.business_management_system.Entity.Order;
import com.sprint.project.business_management_system.impl.OrderServiceImpl;
import com.sprint.project.business_management_system.repository.CustomerRepository;
import com.sprint.project.business_management_system.repository.OrderRepository;
import com.sprint.project.business_management_system.requestDto.OrderRequestDto;
import com.sprint.project.business_management_system.requestDto.OrderRequestDto.CustomerDto;
import com.sprint.project.business_management_system.responseDto.OrderResponseDto;

class OrderTest {

    @InjectMocks
    private OrderServiceImpl service;

    @Mock
    private OrderRepository orderRepo;

    @Mock
    private CustomerRepository customerRepo;

    private Order order;
    private Customer customer;
    private OrderRequestDto dto;
    private Validator validator;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        // Sample Customer
        customer = new Customer();
        customer.setCustomerNumber(1);

        // Sample Order
        order = new Order();
        order.setOrderNumber(1001);
        order.setOrderDate(LocalDate.now());
        order.setStatus("Shipped");
        order.setCustomer(customer);

        // DTO setup
        dto = new OrderRequestDto();
        dto.setOrderNumber(1001);
        dto.setOrderDate(LocalDate.now());
        dto.setStatus("Shipped");

        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerNumber(1);
        dto.setCustomer(customerDto);
    }

    // -------- SERVICE TEST CASES --------

    // 1. Create order - success
    @Test
    void testCreateOrder() {
        when(customerRepo.findById(1)).thenReturn(Optional.of(customer));
        when(orderRepo.save(any())).thenReturn(order);

        assertNotNull(service.createOrder(dto));
    }

    // 2. Create order - verify save
    @Test
    void testCreateOrderVerify() {
        when(customerRepo.findById(1)).thenReturn(Optional.of(customer));
        when(orderRepo.save(any())).thenReturn(order);

        service.createOrder(dto);
        verify(orderRepo).save(any());
    }

    // 3. Create order - customer not found
    @Test
    void testCreateOrderCustomerNotFound() {
        when(customerRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class,
                () -> service.createOrder(dto));
    }

    // 4. Get order by ID - success
    @Test
    void testGetOrderById() {
        when(orderRepo.findById(1001)).thenReturn(Optional.of(order));

        assertEquals("Shipped",
                service.getOrderById(1001).getStatus());
    }

    // 5. Get order by ID - not found
    @Test
    void testGetOrderByIdNotFound() {
        when(orderRepo.findById(1001)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class,
                () -> service.getOrderById(1001));
    }

    // 6. Get orders by status - success
    @Test
    void testGetOrdersByStatus() {
        when(orderRepo.findByStatus("Shipped"))
                .thenReturn(List.of(order));

        assertEquals(1,
                service.getOrdersByStatus("Shipped").size());
    }

    // 7. Get orders by status - empty
    @Test
    void testGetOrdersByStatusEmpty() {
        when(orderRepo.findByStatus("Pending"))
                .thenReturn(new ArrayList<>());

        assertTrue(service.getOrdersByStatus("Pending").isEmpty());
    }

    // 8. Verify repository call
    @Test
    void testGetOrdersByStatusVerify() {
        when(orderRepo.findByStatus("Shipped"))
                .thenReturn(List.of(order));

        service.getOrdersByStatus("Shipped");
        verify(orderRepo).findByStatus("Shipped");
    }

    // 9. Response mapping check
    @Test
    void testOrderResponseMapping() {
        when(customerRepo.findById(1)).thenReturn(Optional.of(customer));
        when(orderRepo.save(any())).thenReturn(order);

        OrderResponseDto response = service.createOrder(dto);

        assertEquals(1001, response.getOrderNumber());
        assertEquals("Shipped", response.getStatus());
    }

    // 10. No exception flow
    @Test
    void testCreateOrderNoException() {
        when(customerRepo.findById(1)).thenReturn(Optional.of(customer));
        when(orderRepo.save(any())).thenReturn(order);

        assertDoesNotThrow(() -> service.createOrder(dto));
    }

    // -------- DTO VALIDATION TEST CASES --------

    // 11. Valid DTO
//    @Test
//    void testOrderDtoValid() {
//        assertTrue(validator.validate(dto).isEmpty());
//    }

    // 12. Invalid DTO (missing status)
    @Test
    void testOrderDtoInvalidEmpty() {
        OrderRequestDto invalidDto = new OrderRequestDto();
        invalidDto.setOrderNumber(1001);

        assertFalse(validator.validate(invalidDto).isEmpty());
    }

    // 13. Invalid DTO (null customer)
    @Test
    void testOrderDtoInvalidCustomer() {
        OrderRequestDto invalidDto = new OrderRequestDto();
        invalidDto.setOrderNumber(1001);
        invalidDto.setOrderDate(LocalDate.now());
        invalidDto.setStatus("Shipped");
        invalidDto.setCustomer(null);

        assertFalse(validator.validate(invalidDto).isEmpty());
    }
}