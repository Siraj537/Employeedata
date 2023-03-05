package com.surnoi.employeeData.service;

import com.surnoi.employeeData.dto.StudentDTO;
import com.surnoi.employeeData.mapper.StudentMapper;
import com.surnoi.employeeData.repositories.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public List<StudentDTO> getAll() {
        return studentMapper.studentToStudentDTO(studentRepository.findAll());
    }

    @Override
    public int saveStudent(StudentDTO student) {
        return (studentRepository.save(studentMapper.studentDTOToStudent(student))).getStudentId();
    }

    @Override
    public StudentDTO getStudent(int studentId) {
        return studentMapper.studentToStudentDTO(studentRepository.getById(studentId));
    }

    @Override
    public String deleteStudent(int studentId) {
        studentRepository.deleteById(studentId);
        return "Student with id : "+ studentId + "Successfully";
    }
}
