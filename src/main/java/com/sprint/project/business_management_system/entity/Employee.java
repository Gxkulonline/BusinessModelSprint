package com.sprint.project.business_management_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @NotNull(message = "Employee number is required")
    @Column(name = "employeeNumber")
    private Integer employeeNumber;

    @NotBlank(message = "Last name cannot be empty")
    @Column(name = "lastName")
    private String lastName;

    @NotBlank(message = "First name cannot be empty")
    @Column(name = "firstName")
    private String firstName;

    // Optional
    @Column(name = "extension")
    private String extension;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be empty")
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "salesRep", fetch = FetchType.LAZY)
    private List<Customer> customers;

    @ManyToOne
    @JoinColumn(name = "officeCode", referencedColumnName = "officeCode")
    @NotNull(message = "Office must be assigned")
    private Office office;

    @ManyToOne
    @JoinColumn(name = "reportsTo", referencedColumnName = "employeeNumber")
    private Employee manager; // optional

    @NotBlank(message = "Job title cannot be empty")
    @Column(name = "jobTitle")
    private String jobTitle;

    
//g&s
    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Integer employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}