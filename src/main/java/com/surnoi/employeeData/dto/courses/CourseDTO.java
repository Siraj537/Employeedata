package com.surnoi.employeeData.dto.courses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.surnoi.employeeData.entities.Student;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDTO {

    private int courseId;
    @NotBlank
    @NotNull
    private String courseName;
    @NotBlank
    @NotNull
    private String tutor;
    @NotBlank
    @NotNull
    private double fee;
    @NotBlank
    @NotNull
    private String duration;
    private List<Student> students;
}
