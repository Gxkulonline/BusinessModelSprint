package com.sprint.project.business_management_system.requestDto;

import java.time.LocalDate;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public class OrderRequestDto {

    @NotNull(message = "Order number is required")
    private Integer orderNumber;

    @NotNull(message = "Order date is required")
    private LocalDate orderDate;

    @NotNull(message = "Required date is required")
    private LocalDate requiredDate;

    // Optional
    private LocalDate shippedDate;

    @NotBlank(message = "Status cannot be empty")
    private String status;

    // Optional
    private String comments;

    @NotNull(message = "Customer is required")
    @Valid
    private CustomerDto customer;

    // ===== INNER DTO =====
    public static class CustomerDto {

        @NotNull(message = "Customer number is required")
        private Integer customerNumber;

        public Integer getCustomerNumber() {
            return customerNumber;
        }

        public void setCustomerNumber(Integer customerNumber) {
            this.customerNumber = customerNumber;
        }
    }

    // ===== GETTERS & SETTERS =====

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(LocalDate requiredDate) {
        this.requiredDate = requiredDate;
    }

    public LocalDate getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(LocalDate shippedDate) {
        this.shippedDate = shippedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }
}