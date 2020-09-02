package com.ausy_technologies.employee_management.Service;

import com.ausy_technologies.employee_management.Exception.ErrorResponse;
import com.ausy_technologies.employee_management.Model.DAO.Department;
import com.ausy_technologies.employee_management.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department addDepartment(Department department) {
        if (department.getName().isEmpty()) {
            throw new ErrorResponse("Department name is empty.");

        } else {
            return this.departmentRepository.save(department);
        }

    }

    public List<Department> findAllDepartments() {
        return this.departmentRepository.findAll();
    }

    public Department findDepartmentById(int id) {
        try {
            this.departmentRepository.findById(id).get();
        } catch (NoSuchElementException noSuchElementException) {
           return null;
        }
        return this.departmentRepository.findById(id).get();
    }

    public void deleteDepartmentById(int id) {
        try {
            this.departmentRepository.findById(id).get();
        } catch (NoSuchElementException noSuchElementException) {
            throw new ErrorResponse(noSuchElementException.getMessage());
        }
        this.departmentRepository.deleteById(id);
    }


}
