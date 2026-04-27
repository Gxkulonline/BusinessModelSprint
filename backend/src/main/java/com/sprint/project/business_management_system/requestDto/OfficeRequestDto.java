package com.sprint.project.business_management_system.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class OfficeRequestDto {

    @NotBlank(message = "Office code cannot be empty")
    @Pattern(regexp = "^[0-9]+$", message = "Office code must contain only numbers")
    private String officeCode;

    @NotBlank(message = "City cannot be empty")
    private String city;

    @NotBlank(message = "Phone cannot be empty")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 digits")
    @Pattern(regexp = "^[0-9]+$", message = "Phone number must contain only numbers")
    private String phone;

    @NotBlank(message = "Address Line 1 cannot be empty")
    private String addressLine1;

    private String addressLine2; // optional
    private String state;        // optional

    @NotBlank(message = "Country cannot be empty")
    private String country;

    private String postalCode;   // optional
    private String territory;    // optional

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
}