package com.sprint.project.business_management_system.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
public class Payment {

    @EmbeddedId
    @NotNull(message = "Payment ID is required")
    private PaymentId paymentId;

    @ManyToOne
    @MapsId("customerNumber")
    @JoinColumn(name = "customerNumber", referencedColumnName = "customerNumber")
    @NotNull(message = "Customer must be provided")
    private Customer customer;

    @NotNull(message = "Payment date is required")
    @Column(name = "paymentDate", nullable = false)
    private LocalDate paymentDate;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than 0")
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    // ===== GETTERS & SETTERS =====

    public PaymentId getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(PaymentId paymentId) {
        this.paymentId = paymentId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}