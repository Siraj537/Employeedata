package com.surnoi.employeeData.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int studentId;
    private String name;
    private String city;
    private String mobile;
    private String email;
    private String batch;
    private String password;
    private String roles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "naukri_id",referencedColumnName = "naukriId")
    private StudentNaukriDetails studentNaukriDetails;

}
