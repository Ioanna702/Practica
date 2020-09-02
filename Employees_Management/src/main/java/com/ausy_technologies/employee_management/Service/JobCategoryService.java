package com.ausy_technologies.employee_management.Service;

import com.ausy_technologies.employee_management.Exception.ErrorResponse;
import com.ausy_technologies.employee_management.Model.DAO.JobCategory;
import com.ausy_technologies.employee_management.Repository.JobCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class JobCategoryService {

    @Autowired
    private JobCategoryRepository jobCategoryRepository;

    public JobCategory addJobCategory(JobCategory jobCategory) {
        if (jobCategory.getName().isEmpty()) {
            throw new ErrorResponse("Job category name is empty.");
        } else {
            return this.jobCategoryRepository.save(jobCategory);
        }
    }

    public List<JobCategory> findAllJobCategories() {
        return this.jobCategoryRepository.findAll();
    }

    public JobCategory findJobCategoryById(int id) {
        try {
            this.jobCategoryRepository.findById(id).get();
        } catch (NoSuchElementException noSuchElementException) {
            return null;
        }
        return this.jobCategoryRepository.findById(id).get();
    }

    public void deleteJobCategoryById(int id) {
        try {
            this.jobCategoryRepository.findById(id).get();
        } catch (NoSuchElementException noSuchElementException) {
            throw new ErrorResponse(noSuchElementException.getMessage());
        }
        this.jobCategoryRepository.deleteById(id);
    }
}
