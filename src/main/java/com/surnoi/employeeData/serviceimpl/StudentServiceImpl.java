package com.surnoi.employeeData.serviceimpl;

import com.surnoi.employeeData.dto.*;
import com.surnoi.employeeData.email.MailService;
import com.surnoi.employeeData.entities.Student;
import com.surnoi.employeeData.jwt.JwtService;
import com.surnoi.employeeData.mapper.StudentMapper;
import com.surnoi.employeeData.repositories.StudentRepository;
import com.surnoi.employeeData.service.StudentService;
import com.surnoi.employeeData.utils.EncryptUtil;
import com.surnoi.employeeData.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

import static com.surnoi.employeeData.constants.StudentConstants.*;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final MailService mailService;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService, MailService mailService) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.mailService = mailService;
    }

    @Override
    public List<StudentSignUpDTO> getAll() {
        return studentMapper.studentToStudentDTO(studentRepository.findAll());
    }

    @Override
    public int saveStudent(StudentSignUpDTO student) {
        Student oldStudent = studentMapper.studentDTOToStudent(student);
       // oldStudent.setStudentId(student.getStudentId());
        return (studentRepository.save(oldStudent)).getStudentId();
    }

    @Override
    public StudentSelfDTO getStudent(int studentId) {
        Student studentRepositoryById = studentRepository.getById(studentId);
        studentRepositoryById.setPassword(passwordEncoder.encode(studentRepositoryById.getPassword()));
        return studentMapper.studentToStudentSelfDTO(studentRepositoryById);
    }

    @Override
    public String deleteStudent(int studentId) {
        studentRepository.deleteById(studentId);
        return "Student with id : "+ studentId + "Successfully";
    }

    @Override
    public ResponseEntity<ResponseDTO> signup(StudentSignUpDTO studentSignUpDTO) {
        try{
            Student student = studentMapper.studentDTOToStudent(studentSignUpDTO);
            Optional<Student> studentOptional = studentRepository.findByEmail(student.getEmail());
            if(studentOptional.isPresent()){
                return ResponseUtils.getResponseEntity(STUDENT_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
            }
            else {
                String encryptPassword = EncryptUtil.encrypt(student.getPassword());
                student.setPassword(passwordEncoder.encode(student.getPassword()));
                student.setEncryptPassword(encryptPassword);
                studentRepository.save(student);
                return ResponseUtils.getResponseEntity(SUCCESSFULLY_REGISTERED, HttpStatus.OK);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.getResponseEntity(SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<ResponseDTO> login(AuthRequestDTO authRequestDTO) {

        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(), authRequestDTO.getPassword()));
            if (authentication.isAuthenticated()) {
                return ResponseUtils.getResponseEntityForToken(HttpStatus.OK,"Token Generated Successfully",jwtService.generateToken(authRequestDTO.getEmail(), authRequestDTO.getRole()));
            } else {
                throw new UsernameNotFoundException("invalid user request !");
            }
        }
        catch (Exception e){
            log.error(e.getMessage());
        }
        return ResponseUtils.getResponseEntity("Bad Credentials",HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<ResponseDTO> changePassword(ChangePasswordDTO changePasswordDTO) {
        Optional<Student> studentOptional = studentRepository.findByEmail(changePasswordDTO.getEmail());
        if(studentOptional.isPresent()){
            Student student = studentOptional.get();
            if(passwordEncoder.matches(changePasswordDTO.getPassword(),student.getPassword())){
                student.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
                studentRepository.save(student);
                return ResponseUtils.getResponseEntity("Password changed successfully",HttpStatus.OK);
            }
            else {
                return ResponseUtils.getResponseEntity("Old password is wrong",HttpStatus.BAD_REQUEST);
            }
        }
        else {
            return ResponseUtils.getResponseEntity("No user found with given details",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> forgotPassword(ForgotPasswordDTO forgotPasswordDTO) throws MessagingException {
        Optional<Student> studentOptional = studentRepository.findByEmail(forgotPasswordDTO.getEmail());
        if(studentOptional.isPresent()){
            Student student = studentOptional.get();
            String decryptPassword = EncryptUtil.decrypt(student.getEncryptPassword());
            mailService.sendForgotMail(decryptPassword,student.getEmail(),"Password Recovery Mail");
            return ResponseUtils.getResponseEntity("Mail Sent with credentials",HttpStatus.OK);
        }
        else {
            return ResponseUtils.getResponseEntity("No user found with given details",HttpStatus.BAD_REQUEST);
        }
    }
}
