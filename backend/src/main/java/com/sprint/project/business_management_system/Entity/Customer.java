package com.sprint.project.business_management_system.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;



@Entity
@Table(name = "customers")
  //getters and setters
public class Customer {
    public Integer getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getContactLastName() {
		return contactLastName;
	}
	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}
	public String getContactFirstName() {
		return contactFirstName;
	}
	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Payment> getPayments() {
		return payments;
	}
	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public Employee getSalesRep() {
		return salesRep;
	}

	public void setSalesRep(Employee salesRep) {
		this.salesRep = salesRep;
	}

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	@Id
    @NotNull(message = "Customer number is required")
    @Column(name = "customerNumber")
    private Integer customerNumber;

    @NotBlank(message = "Customer name cannot be empty")
    @Column(name = "customerName")
    private String customerName;

    @NotBlank(message = "Contact last name cannot be empty")
    @Column(name = "contactLastName")
    private String contactLastName;

    @NotBlank(message = "Contact first name cannot be empty")
    @Column(name = "contactFirstName")
    private String contactFirstName;

    @NotBlank(message = "Phone cannot be empty")
    @Size(min = 10, max = 15, message = "Phone number must be valid")
    @Column(name = "phone")
    private String phone;

    @NotBlank(message = "Address Line 1 cannot be empty")
    @Column(name = "addressLine1")
    private String addressLine1;

    @Column(name = "addressLine2")
    private String addressLine2;

    @NotBlank(message = "City cannot be empty")
    @Column(name = "city")
    private String city;

    // Optional
    @Column(name = "state")
    private String state;

    // Optional
    @Column(name = "postalCode")
    private String postalCode;

    @NotBlank(message = "Country cannot be empty")
    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Order> orders;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Payment> payments;

    @ManyToOne
    @JoinColumn(name = "salesRepEmployeeNumber", referencedColumnName = "employeeNumber")
    private Employee salesRep;

    @NotNull(message = "Credit limit is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Credit limit must be positive")
    @Column(name = "creditLimit")
    private BigDecimal creditLimit;
}