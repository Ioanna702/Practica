package com.ausy_technologies.employee_management.Model.DTO;

import java.util.Date;

public class EmployeesDTO {
    private int id;
    private String firstName;
    private String lastName;
    private int jobCategoryId;
    private int departmentId;
    private Date startDate;
    private Date endDate;
    private boolean isManager;
    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getJobCategoryId() {
        return jobCategoryId;
    }

    public void setJobCategoryId(int jobCategoryId) {
        this.jobCategoryId = jobCategoryId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", jobCategoryId=" + jobCategoryId +
                ", departmentId=" + departmentId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isManager=" + isManager +
                ", active=" + active +
                '}';
    }
}
