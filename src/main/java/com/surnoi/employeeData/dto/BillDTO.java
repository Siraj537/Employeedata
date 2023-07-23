package com.surnoi.employeeData.dto;

import com.surnoi.employeeData.dto.courses.CourseDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class BillDTO {

    private UUID bid;
    private double totalAmount;
    private double paidAmount;
    private double totalPending;
    private LocalDateTime localDateTime;
    private StudentDTO studentDTO;
    private List<CourseDTO> courses;

}
