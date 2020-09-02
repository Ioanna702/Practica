package com.ausy_technologies.employee_management.Repository;

import com.ausy_technologies.employee_management.Model.DAO.JobCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobCategoryRepository extends JpaRepository<JobCategory, Integer> {
}
