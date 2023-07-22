package com.surnoi.employeeData.controller;

import com.surnoi.employeeData.dto.StudentSelfDTO;
import com.surnoi.employeeData.dto.StudentSignUpDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/students")
public interface StudentDataController {

    @GetMapping("/all")
    ResponseEntity<List<StudentSignUpDTO>> getAllStudent();

    @PostMapping("/save")
    ResponseEntity<Integer> saveStudent(@RequestBody StudentSignUpDTO student);

    @GetMapping("/student/get/{id}")
    ResponseEntity<StudentSelfDTO> getStudent(@PathVariable(value = "id") int studentId);

    @PutMapping("/student/update")
    ResponseEntity<Integer> updateStudent(@RequestBody StudentSignUpDTO student);

    @DeleteMapping("/student/delete/{id}")
    ResponseEntity<String> deleteStudent(@PathVariable(value = "id") int studentId);
}