package com.ausy_technologies.employee_management.Controller;

import com.ausy_technologies.employee_management.Exception.ErrorResponse;
import com.ausy_technologies.employee_management.Model.DAO.Employee;
import com.ausy_technologies.employee_management.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/addEmployee/{departmentId}/{jobCategoryId}")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee, @PathVariable int departmentId, @PathVariable int jobCategoryId) {
        Employee newEmployee;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Add new employee");
        try {
            System.out.println(employee.getStartDate());
            System.out.println(employee.getBirthday());
            newEmployee = this.employeeService.addEmployee(employee, departmentId, jobCategoryId);
        } catch (ErrorResponse errorResponse) {
            System.err.println(errorResponse);
            return new ResponseEntity<>(null, headers, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newEmployee, headers, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAllEmployees")
    public ResponseEntity<List<Employee>> findAllEmployees() {
        List<Employee> employeesFound = this.employeeService.findAllEmployees();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Get all employees");
        return new ResponseEntity<>(employeesFound, headers, HttpStatus.OK);
    }

    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable int id) {
        Employee employeeFound;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Get employee by id");
        try {
            employeeFound = this.employeeService.findEmployeeById(id);
        } catch (ErrorResponse errorResponse) {
            System.err.println(errorResponse);
            return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeFound, headers, HttpStatus.FOUND);

    }

    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Delete employee by id");
        try {
            this.employeeService.deleteEmployeeById(id);
        } catch (ErrorResponse errorResponse) {
            System.err.println(errorResponse);
            return new ResponseEntity<>("Not found.", headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Deleted.", headers, HttpStatus.OK);
    }

    @PutMapping("/updateEmployee/{employeeId}/{departmentId}/{jobCategoryId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int employeeId, @PathVariable int departmentId, @PathVariable int jobCategoryId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Update employee");
        try {
            this.employeeService.updateEmployee(employeeId, departmentId, jobCategoryId);

        } catch (ErrorResponse errorResponse) {
            System.out.println(errorResponse);
            return new ResponseEntity<>(null, headers, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(null, headers, HttpStatus.RESET_CONTENT);
    }

    @GetMapping("/getEmployeesByDepartment/{id}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable int id) {
        List<Employee> employeesListFound;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Get employee by department id");
        try {
            employeesListFound = this.employeeService.getEmployeesByDepartment(id);
        } catch (
                ErrorResponse errorResponse) {
            System.out.println(errorResponse);
            return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeesListFound, headers, HttpStatus.OK);
    }
//    @GetMapping("/getEmployeesByJob")
}
