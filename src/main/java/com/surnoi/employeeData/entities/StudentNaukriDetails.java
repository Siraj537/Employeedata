package com.surnoi.employeeData.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class StudentNaukriDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int naukriId;

    private String naukriUserName;
    private String naukriPassword;

 /*   @OneToOne(mappedBy = "studentNaukriDetails", cascade = CascadeType.ALL)
    private Student student;*/
}
