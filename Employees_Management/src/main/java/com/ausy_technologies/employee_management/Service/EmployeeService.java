package com.ausy_technologies.employee_management.Service;

import com.ausy_technologies.employee_management.Exception.DateError;
import com.ausy_technologies.employee_management.Exception.ErrorResponse;
import com.ausy_technologies.employee_management.Model.DAO.Department;
import com.ausy_technologies.employee_management.Model.DAO.Employee;
import com.ausy_technologies.employee_management.Model.DAO.JobCategory;
import com.ausy_technologies.employee_management.Repository.DepartmentRepository;
import com.ausy_technologies.employee_management.Repository.EmployeeRepository;
import com.ausy_technologies.employee_management.Repository.JobCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private JobCategoryRepository jobCategoryRepository;

    public Employee addEmployee(Employee employee, int departmentId, int jobCategoryId) {

        Department department;
        JobCategory jobCategory;
        try {
            department = this.departmentRepository.findById(departmentId).get();
            jobCategory = this.jobCategoryRepository.findById(jobCategoryId).get();

        } catch (NoSuchElementException noSuchElementException) {
            throw new ErrorResponse(noSuchElementException.getMessage() + " for department id or job category id.");
        }
        employee.setDepartment(department);
        employee.setJobCategory(jobCategory);
        return this.employeeRepository.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return this.employeeRepository.findAll();
    }

    public Employee findEmployeeById(int id) {
        try {
            this.employeeRepository.findById(id).get();
        } catch (NoSuchElementException noSuchElementException) {
            throw new ErrorResponse(noSuchElementException.getMessage());
        }
        return this.employeeRepository.findById(id).get();
    }

    public void deleteEmployeeById(int id) {
        try {
            this.employeeRepository.findById(id).get();
        } catch (NoSuchElementException noSuchElementException) {
            throw new ErrorResponse(noSuchElementException.getMessage());
        }
        this.employeeRepository.deleteById(id);
    }

    public void updateEmployee(int employeeId, int departmentId, int jobCategoryId) {
        Department department;
        JobCategory jobCategory;
        try {
            department = this.departmentRepository.findById(departmentId).get();
            jobCategory = this.jobCategoryRepository.findById(jobCategoryId).get();
            this.employeeRepository.findById(employeeId).get();
        } catch (NoSuchElementException noSuchElementException) {
            throw new ErrorResponse(noSuchElementException.getMessage());
        }

        this.employeeRepository.updateEmployee(department, jobCategory, employeeId);
    }

    public List<Employee> getEmployeesByDepartment(int id) {
        List<Employee> employeesList = this.findAllEmployees();
        List<Employee> employeesListFound = new ArrayList<>();
        Department department;
        try {
            department = this.departmentRepository.findById(id).get();
            for (Employee employee : employeesList) {
                if (employee.getDepartment().getId() == id) {
                    employeesListFound.add(employee);

                }
            }
        } catch (NoSuchElementException noSuchElementException) {
            throw new ErrorResponse(noSuchElementException.getMessage());
        } catch (NullPointerException nullPointerException) {
            throw new ErrorResponse(nullPointerException.getMessage() + ": There is no department with this id.");
        }

        return employeesListFound;
    }


//    public boolean validateDate(Date date) {
//        String dateString = date + "";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        return (dateString.equals(simpleDateFormat.format(date)));
//    }
//            //check for a valid birthday
//            if (!validateDate(employee.getBirthday())) {
//                throw new DateError("Birthday format is not ok. Must be dd-MM-yyyy");
//            }
//
//            if (!validateDate(employee.getStartDate())) {
//                throw new DateError("Start date format is not ok. Must be dd-MM-yyyy");
//            }
//
//            if (employee.getEndDate() != null && !validateDate(employee.getEndDate())) {
//
//                throw new DateError("End date format is not ok. Must be dd-MM-yyyy");
//
//            }
//
//            //check for start date < end date
//            if (!employee.getStartDate().before(employee.getEndDate())) {
//                throw new DateError("End date must be after start date.");
//            }
}
