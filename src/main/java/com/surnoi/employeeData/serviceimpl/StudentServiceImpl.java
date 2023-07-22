package com.surnoi.employeeData.serviceimpl;

import com.surnoi.employeeData.dto.AuthRequest;
import com.surnoi.employeeData.dto.StudentSelfDTO;
import com.surnoi.employeeData.dto.StudentSignUpDTO;
import com.surnoi.employeeData.entities.Student;
import com.surnoi.employeeData.jwt.JwtService;
import com.surnoi.employeeData.mapper.StudentMapper;
import com.surnoi.employeeData.repositories.StudentRepository;
import com.surnoi.employeeData.service.StudentService;
import com.surnoi.employeeData.utils.StudentUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
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
    public ResponseEntity<String> signup(StudentSignUpDTO studentSignUpDTO) {
        try{
            Student student = studentMapper.studentDTOToStudent(studentSignUpDTO);
            Optional<Student> studentOptional = studentRepository.findByEmail(student.getEmail());
            if(studentOptional.isPresent()){
                return StudentUtils.getResponseEntity(STUDENT_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
            }
            else {
                student.setPassword(passwordEncoder.encode(student.getPassword()));
                studentRepository.save(student);
                return StudentUtils.getResponseEntity(SUCCESSFULLY_REGISTERED, HttpStatus.OK);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return StudentUtils.getResponseEntity(SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(AuthRequest authRequest) {

        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                return new ResponseEntity<>("{\"token\":\""+jwtService.generateToken(authRequest.getEmail(),authRequest.getRole())+"\"}",HttpStatus.OK);
            } else {
                throw new UsernameNotFoundException("invalid user request !");
            }
        }
        catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseEntity<>("Bad Credentials",HttpStatus.BAD_REQUEST);
    }
}
