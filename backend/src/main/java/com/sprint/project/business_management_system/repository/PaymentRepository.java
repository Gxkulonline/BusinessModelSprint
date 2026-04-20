package com.sprint.project.business_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.business_management_system.Entity.Payment;
import com.sprint.project.business_management_system.Entity.PaymentId;

public interface PaymentRepository extends JpaRepository<Payment, PaymentId> {
	 List<Payment> findByCustomerCustomerNumber(Integer customerNumber);
}
