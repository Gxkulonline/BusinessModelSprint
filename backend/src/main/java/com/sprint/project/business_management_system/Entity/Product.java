package com.sprint.project.business_management_system.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @NotBlank(message = "Product code cannot be empty")
    @Column(name = "productCode")
    private String productCode;

    @NotBlank(message = "Product name cannot be empty")
    @Column(name = "productName")
    private String productName;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "productLine", referencedColumnName = "productLine")
    @NotNull(message = "Product line must be provided")
    private ProductLine productLine;

    @NotBlank(message = "Product scale cannot be empty")
    @Column(name = "productScale")
    private String productScale;

    @NotBlank(message = "Product vendor cannot be empty")
    @Column(name = "productVendor")
    private String productVendor;

    @NotBlank(message = "Product description cannot be empty")
    @Column(name = "productDescription")
    private String productDesc;

    @NotNull(message = "Quantity in stock is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    @Column(name = "quantityInStock")
    private Short quantityInStock;

    @NotNull(message = "Buy price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Buy price must be greater than 0")
    @Column(name = "buyPrice")
    private BigDecimal buyPrice;

    @NotNull(message = "MSRP is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "MSRP must be greater than 0")
    @Column(name = "MSRP")
    private BigDecimal msrp;

    // ===== GETTERS & SETTERS =====

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public ProductLine getProductLine() {
        return productLine;
    }

    public void setProductLine(ProductLine productLine) {
        this.productLine = productLine;
    }

    public String getProductScale() {
        return productScale;
    }

    public void setProductScale(String productScale) {
        this.productScale = productScale;
    }

    public String getProductVendor() {
        return productVendor;
    }

    public void setProductVendor(String productVendor) {
        this.productVendor = productVendor;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Short getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Short quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public BigDecimal getMsrp() {
        return msrp;
    }

    public void setMsrp(BigDecimal msrp) {
        this.msrp = msrp;
    }
}