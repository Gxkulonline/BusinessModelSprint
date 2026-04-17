package com.sprint.project.business_management_system.requestDto;

public class EmployeeRequestDto {

    private Integer employeeNumber;
    private String firstName;
    private String lastName;
    private String extension;
    private String email;
    private String jobTitle;

    private OfficeDto office;
    private Integer reportsTo;

    // ===== INNER DTO =====
    public static class OfficeDto {
        private String officeCode;

        public String getOfficeCode() {
            return officeCode;
        }

        public void setOfficeCode(String officeCode) {
            this.officeCode = officeCode;
        }
    }

    // ===== GETTERS & SETTERS =====

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

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public OfficeDto getOffice() {
        return office;
    }

    public void setOffice(OfficeDto office) {
        this.office = office;
    }

    public Integer getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(Integer reportsTo) {
        this.reportsTo = reportsTo;
    }
}