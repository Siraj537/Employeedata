package com.surnoi.employeeData.controller;

import com.surnoi.employeeData.dto.StudentDTO;
import com.surnoi.employeeData.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@Slf4j
public class StudentDataController {

    private final StudentService studentService;

    public StudentDataController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentDTO>> getAllStudent(){
        log.info("get all students");
        return new ResponseEntity<>(studentService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Integer> saveStudent(@RequestBody StudentDTO student){
        log.info("Save the student");
        return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.OK);

    }

    @GetMapping("/student/get/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable(value = "id" , required = true) int studentId ){

        log.info("get the student details ");
        return new ResponseEntity<>(studentService.getStudent(studentId), HttpStatus.OK);
    }

    @PutMapping("/student/update")
    public ResponseEntity<Integer> updateStudent(@RequestBody StudentDTO student){
        log.info("Update the student details ");
        return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.OK);
    }

    @DeleteMapping("/student/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable(value = "id" , required = true) int studentId){
        log.info("delete the student  ");
        return new ResponseEntity<>(studentService.deleteStudent(studentId), HttpStatus.OK);
    }
}
