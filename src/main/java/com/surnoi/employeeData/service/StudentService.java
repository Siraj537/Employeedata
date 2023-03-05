package com.surnoi.employeeData.service;

import com.surnoi.employeeData.dto.StudentDTO;
import com.surnoi.employeeData.model.Student;

import java.util.List;

public interface StudentService {
    List<StudentDTO> getAll();
    int saveStudent(StudentDTO student);
    StudentDTO getStudent(int studentId);
    String deleteStudent(int studentId);
}
