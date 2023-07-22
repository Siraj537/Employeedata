package com.surnoi.employeeData.controller;

import com.surnoi.employeeData.dto.AuthRequest;
import com.surnoi.employeeData.dto.StudentSignUpDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/students")
public interface StudentMgmtSignUpController {

    @PostMapping("/signup")
    ResponseEntity<String> signUp(@RequestBody StudentSignUpDTO student);

    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody @Valid AuthRequest authRequest);
}
