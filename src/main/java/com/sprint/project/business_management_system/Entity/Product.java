package com.sprint.project.business_management_system.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @Column(name = "productCode")
    private String productCode;

    @Column(name = "productName")
    private String productName;
    
//    @OneToMany(mappedBy = "product")
//    private List<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "productLine", referencedColumnName = "productLine")
    private ProductLine productLine;

    @Column(name = "productScale")
    private String productScale;

    @Column(name = "productVendor")
    private String productVendor;

    @Column(name = "productDescription")
    private String productDesc;

    @Column(name = "quantityInStock")
    private Short quantityInStock;

    @Column(name = "buyPrice")
    private BigDecimal buyPrice;

    @Column(name = "MSRP")
    private BigDecimal  msrp;

}
