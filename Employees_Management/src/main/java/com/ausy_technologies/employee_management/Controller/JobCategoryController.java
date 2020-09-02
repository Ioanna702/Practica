package com.ausy_technologies.employee_management.Controller;

import com.ausy_technologies.employee_management.Exception.ErrorResponse;
import com.ausy_technologies.employee_management.Model.DAO.JobCategory;
import com.ausy_technologies.employee_management.Service.JobCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobcategories")
public class JobCategoryController {

    @Autowired
    private JobCategoryService jobCategoryService;

    @PostMapping("/addJobCategory")
    public ResponseEntity<JobCategory> addJobCategory(@RequestBody JobCategory jobCategory) {
        JobCategory newJobCategory;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Add new job category");
        try {
            newJobCategory = this.jobCategoryService.addJobCategory(jobCategory);
        } catch (ErrorResponse errorResponse) {
            System.err.println(errorResponse);
            return new ResponseEntity<>(null, headers, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(newJobCategory, headers, HttpStatus.CREATED);
    }

    @GetMapping("/getAllJobCategories")
    public ResponseEntity<List<JobCategory>> findAllJobCategories(){
        List<JobCategory> jobCategoriesFound = this.jobCategoryService.findAllJobCategories();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Get all job categories");
        return new ResponseEntity<>(jobCategoriesFound, headers, HttpStatus.OK);
    }

    @GetMapping("/getJobCategoryById/{id}")
    public ResponseEntity<JobCategory> findJobCategoryById(@PathVariable int id){
        JobCategory jobCategoryFound = this.jobCategoryService.findJobCategoryById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Get employee by id");
        return new ResponseEntity<>(jobCategoryFound, headers, HttpStatus.OK);
    }

    @DeleteMapping("/deleteJobCategory/{id}")
    public ResponseEntity<String> deleteJobCategoryById(@PathVariable int id){
        HttpHeaders headers =  new HttpHeaders();
        headers.add("Custom-Header", "Delete job category by id");
        try{
            this.jobCategoryService.deleteJobCategoryById(id);
        } catch(ErrorResponse errorResponse) {
            System.err.println(errorResponse);
            return new ResponseEntity<>("Job Category not found.", headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Job Category deleted.", headers, HttpStatus.OK);
    }

}
