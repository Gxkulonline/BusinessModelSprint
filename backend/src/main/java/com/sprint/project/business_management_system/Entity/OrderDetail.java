package com.sprint.project.business_management_system.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "orderdetails")
public class OrderDetail {

    @EmbeddedId
    @NotNull(message = "Composite ID is required")
    private OrderDetailId id;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Column(name = "quantityOrdered", nullable = false)
    private Integer quantityOrdered;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Column(name = "priceEach", nullable = false)
    private BigDecimal priceEach;

    @NotNull(message = "Order line number is required")
    @Column(name = "orderLineNumber", nullable = false)
    private Short orderLineNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderNumber")
    @JoinColumn(name = "orderNumber", referencedColumnName = "orderNumber")
    @NotNull(message = "Order must be provided")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productCode")
    @JoinColumn(name = "productCode", referencedColumnName = "productCode")
    @JsonIgnore 
    private Product product;

    // ===== GETTERS & SETTERS =====

    public OrderDetailId getId() {
        return id;
    }

    public void setId(OrderDetailId id) {
        this.id = id;
    }

    public Integer getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(Integer quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public BigDecimal getPriceEach() {
        return priceEach;
    }

    public void setPriceEach(BigDecimal priceEach) {
        this.priceEach = priceEach;
    }

    public Short getOrderLineNumber() {
        return orderLineNumber;
    }

    public void setOrderLineNumber(Short orderLineNumber) {
        this.orderLineNumber = orderLineNumber;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}