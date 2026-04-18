package com.sprint.project.business_management_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderDetailId implements Serializable {

    @NotNull(message = "Order number is required")
    @Column(name = "orderNumber")
    private Integer orderNumber;

    @NotBlank(message = "Product code cannot be empty")
    @Column(name = "productCode")
    private String productCode;

    // ===== GETTERS & SETTERS =====

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailId that = (OrderDetailId) o;

        return Objects.equals(orderNumber, that.orderNumber) &&
               Objects.equals(productCode, that.productCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber, productCode);
    }
}