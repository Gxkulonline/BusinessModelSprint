package com.sprint.project.business_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.business_management_system.Entity.Payment;
import com.sprint.project.business_management_system.Entity.PaymentId;
import com.sprint.project.business_management_system.repository.PaymentRepository;

@Service
public class PaymentService {

	 @Autowired
	    private PaymentRepository repo;

	    public List<Payment> getAll() {
	        return repo.findAll();
	    }

	    public Payment save(Payment p) {
	        return repo.save(p);
	    }

	    public void delete(PaymentId id) {
	        repo.deleteById(id);
	    }

}
