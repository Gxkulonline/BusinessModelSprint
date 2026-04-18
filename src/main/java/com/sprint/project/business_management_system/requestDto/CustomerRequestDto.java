package com.sprint.project.business_management_system.requestDto;

import java.math.BigDecimal;
import jakarta.validation.constraints.*;

public class CustomerRequestDto {

    @NotNull(message = "Customer number is required")
    private Integer customerNumber;

    @NotBlank(message = "Customer name cannot be empty")
    private String customerName;

    @NotBlank(message = "Contact last name cannot be empty")
    private String contactLastName;

    @NotBlank(message = "Contact first name cannot be empty")
    private String contactFirstName;

    @NotBlank(message = "Phone cannot be empty")
    @Size(min = 10, max = 15, message = "Phone number must be valid")
    private String phone;

    @NotBlank(message = "Address Line 1 cannot be empty")
    private String addressLine1;

    private String addressLine2;

    @NotBlank(message = "City cannot be empty")
    private String city;

    private String state;

    private String postalCode;

    @NotBlank(message = "Country cannot be empty")
    private String country;

    @NotNull(message = "Credit limit is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Credit limit must be positive")
    private BigDecimal creditLimit;

    private SalesRepDto salesRepEmployee;

    // ===== INNER DTO =====
    public static class SalesRepDto {
        private Integer employeeNumber;

        public Integer getEmployeeNumber() {
            return employeeNumber;
        }

        public void setEmployeeNumber(Integer employeeNumber) {
            this.employeeNumber = employeeNumber;
        }
    }

    // ===== GETTERS & SETTERS =====

    public Integer getCustomerNumber() { return customerNumber; }
    public void setCustomerNumber(Integer customerNumber) { this.customerNumber = customerNumber; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getContactLastName() { return contactLastName; }
    public void setContactLastName(String contactLastName) { this.contactLastName = contactLastName; }

    public String getContactFirstName() { return contactFirstName; }
    public void setContactFirstName(String contactFirstName) { this.contactFirstName = contactFirstName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddressLine1() { return addressLine1; }
    public void setAddressLine1(String addressLine1) { this.addressLine1 = addressLine1; }

    public String getAddressLine2() { return addressLine2; }
    public void setAddressLine2(String addressLine2) { this.addressLine2 = addressLine2; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public BigDecimal getCreditLimit() { return creditLimit; }
    public void setCreditLimit(BigDecimal creditLimit) { this.creditLimit = creditLimit; }

    public SalesRepDto getSalesRepEmployee() { return salesRepEmployee; }
    public void setSalesRepEmployee(SalesRepDto salesRepEmployee) { this.salesRepEmployee = salesRepEmployee; }
}