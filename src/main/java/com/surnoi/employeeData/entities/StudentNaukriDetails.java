package com.surnoi.employeeData.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class StudentNaukriDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int naukriId;

    private String naukriUserName;
    private String naukriPassword;

 /*   @OneToOne(mappedBy = "studentNaukriDetails", cascade = CascadeType.ALL)
    private Student student;*/
}
