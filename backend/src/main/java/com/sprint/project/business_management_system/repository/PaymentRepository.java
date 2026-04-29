package com.sprint.project.business_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.business_management_system.entity.Payment;
import com.sprint.project.business_management_system.entity.PaymentId;

public interface PaymentRepository extends JpaRepository<Payment, PaymentId> {
	 List<Payment> findByCustomerCustomerNumber(Integer customerNumber);
}
