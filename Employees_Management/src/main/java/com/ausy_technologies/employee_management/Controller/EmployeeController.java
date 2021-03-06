package com.ausy_technologies.employee_management.Controller;

import com.ausy_technologies.employee_management.Exception.ErrorResponse;
import com.ausy_technologies.employee_management.Model.DAO.Employee;
import com.ausy_technologies.employee_management.Model.DTO.EmployeeDTO;
import com.ausy_technologies.employee_management.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ausy_technologies.employee_management.Mapper.EmployeeMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        return new ResponseEntity<>(employeeFound, headers, HttpStatus.OK);

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
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable int employeeId, @PathVariable int departmentId, @PathVariable int jobCategoryId) {
        Employee employeeUpdated;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Update employee");
        try {
            employeeUpdated = this.employeeService.updateEmployee(employee, employeeId, departmentId, jobCategoryId);

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

    @GetMapping("/getEmployeesByJob/{id}")
    public ResponseEntity<List<Employee>> getEmployeesByJob(@PathVariable int id) {
        List<Employee> employeesListFound;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Get employee by job category id");
        try {
            employeesListFound = this.employeeService.getEmployeesByJob(id);
        } catch (
                ErrorResponse errorResponse) {
            System.out.println(errorResponse);
            return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeesListFound, headers, HttpStatus.OK);
    }

    @GetMapping("/getEmployeesByDepartmentAndJob/{departmentId}/{jobCategoryId}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartmentAndJob(@PathVariable int departmentId, @PathVariable int jobCategoryId) {
        List<Employee> employeesListFound;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Get employee by department id and job category id");
        try {
            employeesListFound = this.employeeService.getEmployeesByDepartmentAndJob(departmentId, jobCategoryId);
        } catch (
                ErrorResponse errorResponse) {
            System.out.println(errorResponse);
            return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeesListFound, headers, HttpStatus.OK);
    }

    @PutMapping("/updateEmployeeTelephone/{id}")
    public ResponseEntity<Employee> updateUserPassword(@RequestBody Map<String, String> map, @PathVariable int id) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Update telephone by employee id");
        try {
            this.employeeService.updateEmployeeTelephone(map.get("telephone"), id);
        } catch (
                ErrorResponse errorResponse) {
            System.out.println(errorResponse);
            return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, headers, HttpStatus.RESET_CONTENT);
    }

    /////////EmployeesDTO

    @GetMapping("/getAllEmployeesDTO")
    public ResponseEntity<List<EmployeeDTO>> findAllEmployeesDTO() {
        List<EmployeeDTO> employeesDTOFound = new ArrayList<>();
        EmployeeMapper employeeMapper = new EmployeeMapper();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Get all employees DTO");
        try{
            List<Employee> employeesList = employeeService.findAllEmployees();
            for(Employee employee: employeesList){
                employeesDTOFound.add(employeeMapper.employeeToEmployeeDTO(employee));
            }
        } catch (ErrorResponse errorResponse){
            System.err.println(errorResponse);
        }

        return new ResponseEntity<>(employeesDTOFound, headers, HttpStatus.OK);
    }

    @GetMapping("/getEmployeesDTOByDepartment/{id}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesDTOByDepartment(@PathVariable int id) {
        List<EmployeeDTO> employeesDTOFound = new ArrayList<>();
        EmployeeMapper employeeMapper = new EmployeeMapper();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Get employee DTO by department id");
        try {
            List<Employee> employeesListFound = this.employeeService.getEmployeesByDepartment(id);
            for(Employee employee: employeesListFound){
                employeesDTOFound.add(employeeMapper.employeeToEmployeeDTO(employee));
            }
        } catch (
                ErrorResponse errorResponse) {
            System.out.println(errorResponse);
            return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeesDTOFound, headers, HttpStatus.OK);
    }

    @GetMapping("/getEmployeesDTOByJob/{id}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesDTOByJob(@PathVariable int id) {
        List<EmployeeDTO> employeesDTOFound = new ArrayList<>();
        EmployeeMapper employeeMapper = new EmployeeMapper();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Get employee DTO by job category id");
        try {
            List<Employee> employeesListFound = this.employeeService.getEmployeesByJob(id);
            for(Employee employee: employeesListFound){
                employeesDTOFound.add(employeeMapper.employeeToEmployeeDTO(employee));
            }
        } catch (
                ErrorResponse errorResponse) {
            System.out.println(errorResponse);
            return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeesDTOFound, headers, HttpStatus.OK);
    }

    @GetMapping("/getEmployeesDTOByDepartmentAndJob/{departmentId}/{jobCategoryId}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesDTOByDepartment(@PathVariable int departmentId, @PathVariable int jobCategoryId) {
        List<EmployeeDTO> employeesDTOFound = new ArrayList<>();
        EmployeeMapper employeeMapper = new EmployeeMapper();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Get employee DTO by department and job id");
        try {
            List<Employee> employeesListFound = this.employeeService.getEmployeesByDepartmentAndJob(departmentId, jobCategoryId);
            for(Employee employee: employeesListFound){
                employeesDTOFound.add(employeeMapper.employeeToEmployeeDTO(employee));
            }
        } catch (
                ErrorResponse errorResponse) {
            System.out.println(errorResponse);
            return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeesDTOFound, headers, HttpStatus.OK);
    }

}
