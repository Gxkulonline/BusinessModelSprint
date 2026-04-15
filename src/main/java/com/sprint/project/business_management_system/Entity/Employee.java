package com.sprint.project.business_management_system.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employees")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {

    @Id
    private Integer employeeNumber;

    private String firstName;
    private String lastName;
    private String email;
    private String jobTitle;

    @ManyToOne
    @JoinColumn(name = "officeCode")
    private Office office;
}