package com.sprint.project.business_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.business_management_system.Entity.Payment;
import com.sprint.project.business_management_system.Entity.PaymentId;

public interface PaymentRepository extends JpaRepository<Payment, PaymentId> {

}
