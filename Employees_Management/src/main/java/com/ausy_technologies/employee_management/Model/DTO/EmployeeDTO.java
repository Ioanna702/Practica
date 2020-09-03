package com.ausy_technologies.employee_management.Model.DTO;

import java.time.LocalDate;

public class EmployeeDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String departmentName;
    private String jobCategoryName;
    private String telephone;
    private LocalDate startDate;
    private boolean active;
    private boolean isManager;
    private Double salary;

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getJobCategoryName() {
        return jobCategoryName;
    }

    public void setJobCategoryName(String jobCategoryName) {
        this.jobCategoryName = jobCategoryName;
    }
}
