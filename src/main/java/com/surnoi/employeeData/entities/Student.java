package com.surnoi.employeeData.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int studentId;
    private String name;
    private String city;
    private long contactNumber;
    @Column(unique = true)
    private String email;
    private String batch;
    private String password;
    private String roles;
    private String encryptPassword;
    private String address;
    private boolean isBillGenerated = false;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "naukri_id",referencedColumnName = "naukriId")
    private StudentNaukriDetails studentNaukriDetails;

    @ManyToMany
    private List<Course> courses;

    @OneToMany(mappedBy = "student")
    private List<Bill> bills;

}
