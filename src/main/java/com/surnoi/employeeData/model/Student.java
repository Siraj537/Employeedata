package com.surnoi.employeeData.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Student {

    @Id
    private int studentId;
    private String name;
    private String batch;
    private String phoneNo;
    private String emailId;
    

}
