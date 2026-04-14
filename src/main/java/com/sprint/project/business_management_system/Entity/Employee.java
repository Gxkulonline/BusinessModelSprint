package com.sprint.project.business_management_system.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    private Integer employeeNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String jobTitle;
    // Getters & Setters
    public Integer getEmployeeNumber() {
        return employeeNumber;
    }
    public void setEmployeeNumber(Integer employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
//    @ManyToOne
//    @JoinColumn(name = "officeCode")
//    private Office office;
//
//    // SELF RELATION
//    @ManyToOne
//    @JoinColumn(name = "reportsTo")
//    private Employee manager;
//
//    @OneToMany(mappedBy = "manager")	
//    private List<Employee> subordinates;
//
//    @OneToMany(mappedBy = "salesRep")
//    private List<Customer> customers;


