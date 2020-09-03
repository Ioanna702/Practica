package com.ausy_technologies.employee_management.Service;

import com.ausy_technologies.employee_management.Exception.ErrorResponse;
import com.ausy_technologies.employee_management.Model.DAO.Department;
import com.ausy_technologies.employee_management.Model.DAO.Employee;
import com.ausy_technologies.employee_management.Model.DAO.JobCategory;
import com.ausy_technologies.employee_management.Repository.DepartmentRepository;
import com.ausy_technologies.employee_management.Repository.EmployeeRepository;
import com.ausy_technologies.employee_management.Repository.JobCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        if (employee.getEndDate() != null) {
            if (employee.getStartDate().isBefore(employee.getEndDate())) {
                return this.employeeRepository.save(employee);
            } else {
                throw new ErrorResponse("Start date must be before the end date.");
            }
        } else {

            return this.employeeRepository.save(employee);
        }
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

    public Employee updateEmployee(Employee employee, int employeeId, int departmentId, int jobCategoryId) {
        Employee employeeUpdated;
        Department department;
        JobCategory jobCategory;
        try {
            department = this.departmentRepository.findById(departmentId).get();
            jobCategory = this.jobCategoryRepository.findById(jobCategoryId).get();
            employeeUpdated = this.employeeRepository.findById(employeeId).get();
        } catch (NoSuchElementException noSuchElementException) {
            throw new ErrorResponse(noSuchElementException.getMessage());
        }

        employee.setId(employeeId);
        employee.setDepartment(department);
        employee.setJobCategory(jobCategory);
        this.employeeRepository.save(employee);
        return employee;
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

    public List<Employee> getEmployeesByJob(int id) {
        List<Employee> employeesList = this.findAllEmployees();
        List<Employee> employeesListFound = new ArrayList<>();
        JobCategory jobCategory;
        try {
            jobCategory = this.jobCategoryRepository.findById(id).get();
            for (Employee employee : employeesList) {
                if (employee.getJobCategory().getId() == id) {
                    employeesListFound.add(employee);

                }
            }
        } catch (NoSuchElementException noSuchElementException) {
            throw new ErrorResponse(noSuchElementException.getMessage());
        } catch (NullPointerException nullPointerException) {
            throw new ErrorResponse(nullPointerException.getMessage() + ": There is no job category with this id.");
        }

        return employeesListFound;
    }

    public List<Employee> getEmployeesByDepartmentAndJob(int departmentId, int jobCategoryId) {
        List<Employee> employeesList = this.findAllEmployees();
        List<Employee> employeesListFound = new ArrayList<>();
        Department department;
        JobCategory jobCategory;
        try {
            department = this.departmentRepository.findById(departmentId).get();
            jobCategory = this.jobCategoryRepository.findById(jobCategoryId).get();
            for (Employee employee : employeesList) {
                if (employee.getDepartment().getId() == departmentId) {
                    if (employee.getJobCategory().getId() == jobCategoryId) {
                        employeesListFound.add(employee);
                    }
                }
            }
        } catch (NoSuchElementException noSuchElementException) {
            throw new ErrorResponse(noSuchElementException.getMessage());
        } catch (NullPointerException nullPointerException) {
            throw new ErrorResponse(nullPointerException.getMessage() + ": There is no job category with this id.");
        }

        return employeesListFound;
    }

    public void updateEmployeeTelephone(String telephone, int id) {
        try {
            this.employeeRepository.findById(id).get();
        } catch (NoSuchElementException noSuchElementException) {
            throw new ErrorResponse(noSuchElementException.getMessage());
        }
        this.employeeRepository.updateEmployeeTelephone(telephone, id);
    }
}
