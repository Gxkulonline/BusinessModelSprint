package com.sprint.project.business_management_system.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.business_management_system.entity.Customer;
import com.sprint.project.business_management_system.entity.Payment;
import com.sprint.project.business_management_system.entity.PaymentId;
import com.sprint.project.business_management_system.repository.CustomerRepository;
import com.sprint.project.business_management_system.repository.PaymentRepository;
import com.sprint.project.business_management_system.requestDto.PaymentRequestDto;
import com.sprint.project.business_management_system.responseDto.PaymentResponseDto;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // ----------------- MAPPER -----------------
    private PaymentResponseDto mapToDto(Payment payment) {

        PaymentResponseDto dto = new PaymentResponseDto();

        dto.setCustomerNumber(payment.getPaymentId().getCustomerNumber());
        dto.setCheckNumber(payment.getPaymentId().getCheckNumber());

        dto.setPaymentDate(payment.getPaymentDate());
        dto.setAmount(payment.getAmount());

        return dto;
    }

    // ----------------- CREATE PAYMENT -----------------
    @Override
    public PaymentResponseDto createPayment(PaymentRequestDto dto) {

        Customer customer = customerRepository.findById(dto.getCustomerNumber())
                .orElseThrow(() -> new RuntimeException(
                        "Customer NOT FOUND: " + dto.getCustomerNumber()));

        Payment payment = new Payment();

        PaymentId id = new PaymentId();
        id.setCustomerNumber(dto.getCustomerNumber());
        id.setCheckNumber(dto.getCheckNumber());

        payment.setPaymentId(id);
        payment.setCustomer(customer);
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setAmount(dto.getAmount());

        Payment saved = paymentRepository.save(payment);

        return mapToDto(saved);
    }

    // ----------------- GET ALL PAYMENTS -----------------
    @Override
    public List<PaymentResponseDto> getAllPayments() {

        return paymentRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ----------------- GET BY CUSTOMER -----------------
    @Override
    public List<PaymentResponseDto> getPaymentsByCustomer(Integer customerId) {

        return paymentRepository.findAll()
                .stream()
                .filter(p -> p.getPaymentId().getCustomerNumber().equals(customerId))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ----------------- TOTAL AMOUNT -----------------
    @Override
    public BigDecimal getTotalAmountByCustomer(Integer customerId) {

        return paymentRepository.findAll()
                .stream()
                .filter(p -> p.getPaymentId().getCustomerNumber().equals(customerId))
                .map(Payment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}