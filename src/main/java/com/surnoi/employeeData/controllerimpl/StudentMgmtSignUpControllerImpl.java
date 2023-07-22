package com.surnoi.employeeData.controllerimpl;

import com.surnoi.employeeData.controller.StudentMgmtSignUpController;
import com.surnoi.employeeData.dto.AuthRequest;
import com.surnoi.employeeData.dto.StudentSignUpDTO;
import com.surnoi.employeeData.service.StudentService;
import com.surnoi.employeeData.utils.StudentUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.surnoi.employeeData.constants.StudentConstants.SOMETHING_WENT_WRONG;

@RestController
public class StudentMgmtSignUpControllerImpl implements StudentMgmtSignUpController {

    private final StudentService studentService;

    public StudentMgmtSignUpControllerImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public ResponseEntity<String> signUp(StudentSignUpDTO studentSignUpDTO) {
        try{
            return studentService.signup(studentSignUpDTO);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return StudentUtils.getResponseEntity(SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        try {
            return studentService.login(authRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
