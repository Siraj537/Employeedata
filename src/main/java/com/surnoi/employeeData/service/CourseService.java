package com.surnoi.employeeData.service;

import com.surnoi.employeeData.dto.ResponseDTO;
import com.surnoi.employeeData.dto.courses.CourseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CourseService {

    ResponseEntity<ResponseDTO> addCourses(List<CourseDTO> courseDTOList);

    ResponseEntity<ResponseDTO> getCourses();

    ResponseEntity<ResponseDTO> getCourse(String courseId);

    ResponseEntity<ResponseDTO> deleteCourse(String courseId);

    ResponseEntity<ResponseDTO> updateCourse(CourseDTO courseDTO, String courseId);

    ResponseEntity<ResponseDTO> registerCourse(String studentId, String courseId);
}
