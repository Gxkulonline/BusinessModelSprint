package com.sprint.project.business_management_system.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productlines")
public class ProductLine {

    @Id
    @NotBlank(message = "Product line cannot be empty")
    @Size(max = 50, message = "Product line must be <= 50 characters")
    @Column(name = "productLine", length = 50, nullable = false)
    private String productLine;

    // Optional but size-limited
    @Size(max = 4000, message = "Text description too long")
    @Column(name = "textDescription", length = 4000)
    private String textDescription;

    // Optional large text
    @Lob
    @Column(name = "htmlDescription")
    private String htmlDescription;

    // Optional binary
    @Lob
    @Column(name = "image")
    private byte[] image;

    @OneToMany(mappedBy = "productLine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // avoid recursion
    private List<Product> products = new ArrayList<>();

    // ===== GETTERS & SETTERS =====

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public String getTextDescription() {
        return textDescription;
    }

    public void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }

    public String getHtmlDescription() {
        return htmlDescription;
    }

    public void setHtmlDescription(String htmlDescription) {
        this.htmlDescription = htmlDescription;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}