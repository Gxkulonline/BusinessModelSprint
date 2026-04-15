package com.sprint.project.business_management_system.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productlines")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductLine {

    @Id
    @Column(name = "productLine", length = 50, nullable = false)
    private String productLine;

    @Column(name = "textDescription", length = 4000)
    private String textDescription;

    @Lob
    @Column(name = "htmlDescription")
    private String htmlDescription;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @OneToMany(mappedBy = "productLine", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Product> products=new ArrayList<>();

}
