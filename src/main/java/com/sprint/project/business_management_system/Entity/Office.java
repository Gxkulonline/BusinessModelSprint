package com.sprint.project.business_management_system.Entity;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "offices")
public class Office {
    @Id
    private String officeCode;
    private String city;
    private String phone;
    private String country;
    @OneToMany(mappedBy = "office")
    private List<Employee> employees;
}
