package com.ausy_technologies.employee_management.Model.DAO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "jobcategories")
public class JobCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jobCategory", cascade = CascadeType.ALL)
    private List<Employee> employeeList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    @Override
    public String toString() {
        return "JobCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
