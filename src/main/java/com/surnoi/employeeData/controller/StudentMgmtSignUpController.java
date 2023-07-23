package com.surnoi.employeeData.controller;

import com.surnoi.employeeData.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/students/credentials")
public interface StudentMgmtSignUpController {

    @PostMapping("/signup")
    ResponseEntity<ResponseDTO> signUp(@RequestBody StudentSignUpDTO student);

    @PostMapping("/login")
    ResponseEntity<ResponseDTO> login(@RequestBody @Valid AuthRequestDTO authRequestDTO);

    @PostMapping("/changepassword")
    ResponseEntity<ResponseDTO> changePassword(@RequestBody @Valid ChangePasswordDTO changePasswordDTO);

    @PostMapping("/forgotpassword")
    ResponseEntity<ResponseDTO> forgotPassword(@RequestBody @Valid ForgotPasswordDTO forgotPasswordDTO);
}
