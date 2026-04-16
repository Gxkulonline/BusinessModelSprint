package com.sprint.project.business_management_system.service;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sprint.project.business_management_system.Entity.Payment;
import com.sprint.project.business_management_system.repository.CustomerRepository;
import com.sprint.project.business_management_system.repository.PaymentRepository;
@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepository repo;
    @Autowired
    private CustomerRepository customerRepo;
    public List<Payment> getAllPayments() {
        return repo.findAll();
    }
    
    public Payment createPayment(Payment payment) {
        return repo.save(payment);
    }
    
    public List<Payment> getPaymentsByCustomer(Integer customerId) {
        return repo.findByCustomerCustomerNumber(customerId);
    }
    public BigDecimal getTotalAmountByCustomer(Integer customerId) {
        List<Payment> payments = repo.findByCustomerCustomerNumber(customerId);
        BigDecimal total = BigDecimal.ZERO;
        for (Payment p : payments) {
            total = total.add(p.getAmount());
        }
        return total;
    }
}
