package com.ausy_technologies.employee_management.Controller;

import com.ausy_technologies.employee_management.Exception.ErrorResponse;
import com.ausy_technologies.employee_management.Model.DAO.Department;
import com.ausy_technologies.employee_management.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentsService;

    @PostMapping("/addDepartment")
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        Department newDepartment;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Add new department");
        try {
            newDepartment = this.departmentsService.addDepartment(department);
        } catch (ErrorResponse errorResponse) {
            System.err.println(errorResponse);
            return new ResponseEntity<>(null, headers, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newDepartment, headers, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAllDepartments")
    public ResponseEntity<List<Department>> findAllDepartments() {
        List<Department> departmentsFound = this.departmentsService.findAllDepartments();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Get all departments");
        return new ResponseEntity<>(departmentsFound, headers, HttpStatus.OK);
    }

    @GetMapping("/getDepartmentById/{id}")
    public ResponseEntity<Department> findDepartmentById(@PathVariable int id) {
        Department departmentFound = this.departmentsService.findDepartmentById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Get department by id");
        return new ResponseEntity<>(departmentFound, headers, HttpStatus.OK);
    }

    @DeleteMapping("/deleteDepartment/{id}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Delete department by id");
        try {
            this.departmentsService.deleteDepartmentById(id);
        } catch (ErrorResponse errorResponse) {
            System.err.println(errorResponse);
            return new ResponseEntity<>("No value present.", headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Department deleted.", headers, HttpStatus.OK);
    }
}
