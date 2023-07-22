package com.surnoi.employeeData.service;

import com.surnoi.employeeData.dto.AuthRequest;
import com.surnoi.employeeData.dto.StudentSelfDTO;
import com.surnoi.employeeData.dto.StudentSignUpDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {
    List<StudentSignUpDTO> getAll();
    int saveStudent(StudentSignUpDTO student);
    StudentSelfDTO getStudent(int studentId);
    String deleteStudent(int studentId);
    ResponseEntity<String> signup(StudentSignUpDTO student);
    ResponseEntity<String> login(AuthRequest authRequest);
}
