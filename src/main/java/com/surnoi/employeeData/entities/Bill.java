package com.surnoi.employeeData.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Bill implements Serializable {

    @Id
    private UUID bid;
    private double totalAmount;
    private double paidAmount;
    private double totalPending;
    private LocalDateTime billGeneratedTime;
    @ManyToOne
    private Student student;
    @ManyToMany
    private List<Course> courses;
    private long contactNumber;
}
