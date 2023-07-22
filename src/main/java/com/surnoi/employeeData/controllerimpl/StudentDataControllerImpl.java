package com.surnoi.employeeData.controllerimpl;

import com.surnoi.employeeData.controller.StudentDataController;
import com.surnoi.employeeData.dto.StudentSelfDTO;
import com.surnoi.employeeData.dto.StudentSignUpDTO;
import com.surnoi.employeeData.jwt.JwtService;
import com.surnoi.employeeData.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class StudentDataControllerImpl implements StudentDataController {

    private final AuthenticationManager authenticationManager;
    private final StudentService studentService;
    private final JwtService jwtService;

    public StudentDataControllerImpl(AuthenticationManager authenticationManager, StudentService studentService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.studentService = studentService;
        this.jwtService = jwtService;
    }

    public ResponseEntity<List<StudentSignUpDTO>> getAllStudent(){
        log.info("get all students");
        return new ResponseEntity<>(studentService.getAll(), HttpStatus.OK);
    }

    public ResponseEntity<Integer> saveStudent(@RequestBody StudentSignUpDTO student){
        log.info("Save the student");
        return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StudentSelfDTO> getStudent(int studentId ){
        log.info("get the student details ");
        return new ResponseEntity<>(studentService.getStudent(studentId), HttpStatus.OK);
    }

    public ResponseEntity<Integer> updateStudent(@RequestBody StudentSignUpDTO student){
        log.info("Update the student details ");
        return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteStudent(@PathVariable(value = "id" , required = true) int studentId){
        log.info("delete the student  ");
        return new ResponseEntity<>(studentService.deleteStudent(studentId), HttpStatus.OK);
    }

}
