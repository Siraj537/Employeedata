package com.surnoi.employeeData.controllerimpl;

import com.surnoi.employeeData.controller.StudentMgmtSignUpController;
import com.surnoi.employeeData.dto.*;
import com.surnoi.employeeData.service.StudentService;
import com.surnoi.employeeData.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class StudentMgmtSignUpControllerImpl implements StudentMgmtSignUpController {

    private final StudentService studentService;

    public StudentMgmtSignUpControllerImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public ResponseEntity<ResponseDTO> signUp(StudentSignUpDTO studentSignUpDTO) {
        try{
            return studentService.signup(studentSignUpDTO);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.getResponseEntity(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<ResponseDTO> login(@RequestBody AuthRequestDTO authRequestDTO) {
        try {
            return studentService.login(authRequestDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtils.getResponseEntity(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> changePassword(ChangePasswordDTO changePasswordDTO) {
        try{
            return studentService.changePassword(changePasswordDTO);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.getResponseEntity(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> forgotPassword(ForgotPasswordDTO forgotPasswordDTO) {
        try{
            return studentService.forgotPassword(forgotPasswordDTO);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.getResponseEntity(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
