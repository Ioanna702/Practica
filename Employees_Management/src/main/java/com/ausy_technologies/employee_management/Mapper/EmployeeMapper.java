package com.ausy_technologies.employee_management.Mapper;

import com.ausy_technologies.employee_management.Exception.ErrorResponse;
import com.ausy_technologies.employee_management.Model.DAO.Employee;
import com.ausy_technologies.employee_management.Model.DTO.EmployeeDTO;

public class EmployeeMapper {

    public EmployeeDTO employeeToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        if (employee != null) {
            try {
                employeeDTO.setFirstName(employee.getFirstName());
                employeeDTO.setLastName(employee.getLastName());
                employeeDTO.setEmail(employee.getEmail());
                employeeDTO.setDepartmentName(employee.getDepartment().getName());
                employeeDTO.setJobCategoryName(employee.getJobCategory().getName());
                employeeDTO.setTelephone(employee.getTelephone());
                employeeDTO.setStartDate(employee.getStartDate());
                employeeDTO.setActive(employee.isActive());
                employeeDTO.setManager(employee.isManager());
                employeeDTO.setSalary(employee.getSalary());


            } catch (NullPointerException nullPointerException) {
                throw new ErrorResponse(nullPointerException.getMessage() + "department or job category.");
            }

        }
        else {
            throw new ErrorResponse("The conversion from Employee to EmployeeDTO didn't make it.");
        }
        return employeeDTO;
    }
}
