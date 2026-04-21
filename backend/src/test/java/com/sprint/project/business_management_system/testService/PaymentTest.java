package com.sprint.project.business_management_system.testService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import jakarta.validation.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.sprint.project.business_management_system.Entity.Customer;
import com.sprint.project.business_management_system.Entity.Payment;
import com.sprint.project.business_management_system.Entity.PaymentId;
import com.sprint.project.business_management_system.impl.PaymentServiceImpl;
import com.sprint.project.business_management_system.repository.CustomerRepository;
import com.sprint.project.business_management_system.repository.PaymentRepository;
import com.sprint.project.business_management_system.requestDto.PaymentRequestDto;
import com.sprint.project.business_management_system.responseDto.PaymentResponseDto;

class PaymentTest {

    @InjectMocks
    private PaymentServiceImpl service;

    @Mock
    private PaymentRepository paymentRepo;

    @Mock
    private CustomerRepository customerRepo;

    private Customer customer;
    private Payment payment;
    private PaymentRequestDto dto;
    private Validator validator;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        // Sample Customer
        customer = new Customer();
        customer.setCustomerNumber(1);

        // Sample PaymentId
        PaymentId id = new PaymentId();
        id.setCustomerNumber(1);
        id.setCheckNumber("CHK123");

        // Sample Payment
        payment = new Payment();
        payment.setPaymentId(id);
        payment.setCustomer(customer);
        payment.setPaymentDate(LocalDate.now());
        payment.setAmount(new BigDecimal("5000"));

        // DTO setup
        dto = new PaymentRequestDto();
        dto.setCustomerNumber(1);
        dto.setCheckNumber("CHK123");
        dto.setPaymentDate(LocalDate.now());
        dto.setAmount(new BigDecimal("5000"));
    }

    // -------- SERVICE TEST CASES --------

    // 1. Create payment - success
    @Test
    void testCreatePayment() {
        when(customerRepo.findById(1)).thenReturn(Optional.of(customer));
        when(paymentRepo.save(any())).thenReturn(payment);

        assertNotNull(service.createPayment(dto));
    }

    // 2. Create payment - verify save call
    @Test
    void testCreatePaymentVerify() {
        when(customerRepo.findById(1)).thenReturn(Optional.of(customer));
        when(paymentRepo.save(any())).thenReturn(payment);

        service.createPayment(dto);
        verify(paymentRepo).save(any());
    }

    // 3. Create payment - customer not found
    @Test
    void testCreatePaymentCustomerNotFound() {
        when(customerRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.createPayment(dto));
    }

    // 4. Get all payments - success
    @Test
    void testGetAllPayments() {
        when(paymentRepo.findAll()).thenReturn(List.of(payment));
        assertEquals(1, service.getAllPayments().size());
    }

    // 5. Get all payments - empty
    @Test
    void testGetAllPaymentsEmpty() {
        when(paymentRepo.findAll()).thenReturn(new ArrayList<>());
        assertTrue(service.getAllPayments().isEmpty());
    }

    // 6. Get payments by customer - success
    @Test
    void testGetPaymentsByCustomer() {
        when(paymentRepo.findAll()).thenReturn(List.of(payment));
        assertEquals(1, service.getPaymentsByCustomer(1).size());
    }

    // 7. Get payments by customer - no match
    @Test
    void testGetPaymentsByCustomerNoMatch() {
        when(paymentRepo.findAll()).thenReturn(List.of(payment));
        assertTrue(service.getPaymentsByCustomer(2).isEmpty());
    }

    // 8. Get total amount by customer - success
    @Test
    void testGetTotalAmount() {
        when(paymentRepo.findAll()).thenReturn(List.of(payment));
        assertEquals(new BigDecimal("5000"), service.getTotalAmountByCustomer(1));
    }

    // 9. Get total amount - no payments
    @Test
    void testGetTotalAmountEmpty() {
        when(paymentRepo.findAll()).thenReturn(new ArrayList<>());
        assertEquals(BigDecimal.ZERO, service.getTotalAmountByCustomer(1));
    }

    // 10. Response mapping validation
    @Test
    void testResponseMapping() {
        when(customerRepo.findById(1)).thenReturn(Optional.of(customer));
        when(paymentRepo.save(any())).thenReturn(payment);

        PaymentResponseDto response = service.createPayment(dto);

        assertEquals("CHK123", response.getCheckNumber());
        assertEquals(1, response.getCustomerNumber());
        assertEquals(new BigDecimal("5000"), response.getAmount());
    }

    // -------- DTO VALIDATION TEST CASES --------

    // 11. Valid DTO
    @Test
    void testPaymentDtoValid() {
        assertTrue(validator.validate(dto).isEmpty());
    }

    // 12. Invalid DTO (negative amount)
    @Test
    void testPaymentDtoInvalidAmount() {
        dto.setAmount(new BigDecimal("-100"));
        assertFalse(validator.validate(dto).isEmpty());
    }

    // 13. Invalid DTO (empty check number)
    @Test
    void testPaymentDtoInvalidCheckNumber() {
        dto.setCheckNumber("");
        assertFalse(validator.validate(dto).isEmpty());
    }
}