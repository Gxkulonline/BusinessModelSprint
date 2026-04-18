package com.sprint.project.business_management_system.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "offices")
public class Office {

    @Id
    @NotBlank(message = "Office code cannot be empty")
    @Column(name = "officeCode")
    private String officeCode;

    @NotBlank(message = "City cannot be empty")
    @Column(name = "city")
    private String city;

    @NotBlank(message = "Phone cannot be empty")
    @Size(min = 10, max = 15, message = "Phone number must be valid")
    @Column(name = "phone")
    private String phone;

    @NotBlank(message = "Address Line 1 cannot be empty")
    @Column(name = "addressLine1")
    private String addressLine1;

    // Optional
    @Column(name = "addressLine2")
    private String addressLine2;

    // Optional
    @Column(name = "state")
    private String state;

    @NotBlank(message = "Country cannot be empty")
    @Column(name = "country")
    private String country;

    // Optional
    @Column(name = "postalCode")
    private String postalCode;

    // Optional
    @Column(name = "territory")
    private String territory;

    @OneToMany(mappedBy = "office", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Employee> employees;

    // ===== GETTERS & SETTERS =====

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}