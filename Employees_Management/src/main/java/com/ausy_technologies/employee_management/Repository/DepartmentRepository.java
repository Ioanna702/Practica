package com.ausy_technologies.employee_management.Repository;

import com.ausy_technologies.employee_management.Model.DAO.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
