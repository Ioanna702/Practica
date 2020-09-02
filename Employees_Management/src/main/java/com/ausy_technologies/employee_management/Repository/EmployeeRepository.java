package com.ausy_technologies.employee_management.Repository;

import com.ausy_technologies.employee_management.Model.DAO.Department;
import com.ausy_technologies.employee_management.Model.DAO.Employee;
import com.ausy_technologies.employee_management.Model.DAO.JobCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

@Modifying
@Transactional
@Query("Update Employee set department= :department , jobCategory= :jobCategory where id= :id")
void updateEmployee(@Param("department") Department department, @Param("jobCategory")JobCategory jobCategory, @Param("id") int id);

}
