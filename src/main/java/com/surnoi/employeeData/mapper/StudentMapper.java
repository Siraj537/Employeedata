package com.surnoi.employeeData.mapper;


import com.surnoi.employeeData.dto.StudentDTO;
import com.surnoi.employeeData.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDTO studentToStudentDTO(Student student);
    @Mapping(target = "studentId", ignore = true)
    Student studentDTOToStudent(StudentDTO student);

    List<StudentDTO> studentToStudentDTO(List<Student> student);
    List<Student> studentDTOToStudent(List<StudentDTO> student);

}
