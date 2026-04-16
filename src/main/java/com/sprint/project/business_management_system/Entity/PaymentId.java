package com.sprint.project.business_management_system.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PaymentId implements Serializable {

    @NotNull(message = "Customer number is required")
    @Column(name = "customerNumber")
    private Integer customerNumber;

    @NotBlank(message = "Check number cannot be empty")
    @Column(name = "checkNumber")
    private String checkNumber;

    // ===== GETTERS & SETTERS =====

    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentId that = (PaymentId) o;

        return Objects.equals(customerNumber, that.customerNumber) &&
               Objects.equals(checkNumber, that.checkNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerNumber, checkNumber);
    }
}