package com.sprint.project.business_management_system.service;

import java.math.BigDecimal;
import java.util.List;

<<<<<<< HEAD
import com.sprint.project.business_management_system.entity.Payment;
=======
import com.sprint.project.business_management_system.requestDto.PaymentRequestDto;
import com.sprint.project.business_management_system.responseDto.PaymentResponseDto;
>>>>>>> refs/heads/master

public interface PaymentService {

    List<PaymentResponseDto> getAllPayments();

    PaymentResponseDto createPayment(PaymentRequestDto dto);

    List<PaymentResponseDto> getPaymentsByCustomer(Integer customerId);

    BigDecimal getTotalAmountByCustomer(Integer customerId);
}