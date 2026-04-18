package com.sprint.project.business_management_system.service;

import java.math.BigDecimal;
import java.util.List;

import com.sprint.project.business_management_system.entity.Payment;

public interface PaymentService {
	List<Payment> getAllPayments();
	Payment createPayment(Payment payment);

    List<Payment> getPaymentsByCustomer(Integer customerId);

    BigDecimal getTotalAmountByCustomer(Integer customerId);
}
