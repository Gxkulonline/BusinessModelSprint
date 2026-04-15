package com.sprint.project.business_management_system.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orderdetails")
@IdClass(OrderDetailId.class)
public class OrderDetail {

    @Id
    private Integer orderNumber;

    @Id
    private String productCode;

    private Integer quantityOrdered;
    private Double priceEach;

//    @ManyToOne
//    @JoinColumn(name = "orderNumber", insertable = false, updatable = false)
//    private Order order;
//
//    @ManyToOne
//    @JoinColumn(name = "productCode", insertable = false, updatable = false)
//    private Product product;
    
    @ManyToOne
    @JoinColumn(name = "orderNumber", insertable = false, updatable = false)
    @JsonIgnore
    private Order order;

    @ManyToOne
    @JoinColumn(name = "productCode", insertable = false, updatable = false)
    @JsonIgnore
    private Product product;
}