package com.surnoi.employeeData.service;

import com.surnoi.employeeData.dto.*;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.util.List;

public interface StudentService {
    List<StudentSignUpDTO> getAll();

    int saveStudent(StudentSignUpDTO student);

    StudentSelfDTO getStudent(int studentId);

    String deleteStudent(int studentId);

    ResponseEntity<ResponseDTO> signup(StudentSignUpDTO student);

    ResponseEntity<ResponseDTO> login(AuthRequestDTO authRequestDTO);

    ResponseEntity<ResponseDTO> changePassword(ChangePasswordDTO changePasswordDTO);

    ResponseEntity<ResponseDTO> forgotPassword(ForgotPasswordDTO forgotPasswordDTO) throws MessagingException;
}
