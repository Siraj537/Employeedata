package com.surnoi.employeeData.mapper;


import com.surnoi.employeeData.dto.StudentNaukriDetailsDTO;
import com.surnoi.employeeData.dto.StudentSelfDTO;
import com.surnoi.employeeData.dto.StudentSignUpDTO;
import com.surnoi.employeeData.entities.Student;
import com.surnoi.employeeData.entities.StudentNaukriDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mappings({
            @Mapping(target = "studentNaukriDetails.naukriPassword", ignore = true),
            @Mapping(target = "password", ignore = true),
            @Mapping(target = "studentNaukriDetailsDTO", source = "studentNaukriDetails")
    })
    StudentSignUpDTO studentToStudentDTO(Student student);

    @Mappings({
            @Mapping(target = "studentNaukriDetails.naukriId", ignore = true),
            @Mapping(target = "studentId", ignore = true),
            @Mapping(target = "studentNaukriDetails", source ="studentNaukriDetailsDTO" )
    })
    Student studentDTOToStudent(StudentSignUpDTO studentSignUpDTO);

    List<StudentSignUpDTO> studentToStudentDTO(List<Student> student);
    List<Student> studentDTOToStudent(List<StudentSignUpDTO> student);

    @Mapping(target = "studentNaukriSelftDetailsDTO", source = "studentNaukriDetails")
    StudentSelfDTO studentToStudentSelfDTO(Student student);
    List<StudentSelfDTO> studentToStudentSelfDTO(List<Student> student);

    StudentNaukriDetails studentSignUpDTOToStudentNaukriDetails(StudentNaukriDetailsDTO studentNaukriDetailsDTO);


}
