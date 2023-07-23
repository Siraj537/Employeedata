package com.surnoi.employeeData.controller;


import com.surnoi.employeeData.dto.ResponseDTO;
import com.surnoi.employeeData.dto.courses.CourseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RequestMapping("/courses")
public interface CourseController {

    @PostMapping
    ResponseEntity<ResponseDTO> addCourses(@RequestBody @Valid List<CourseDTO> courseDTO);
    @GetMapping
    ResponseEntity<ResponseDTO> getAllCourses();
    @GetMapping("/{courseId}")
    ResponseEntity<ResponseDTO> getCourse(@PathVariable(value = "courseId") String courseId);
    @DeleteMapping("/{courseId}")
    ResponseEntity<ResponseDTO> deleteCourse(@PathVariable(value = "courseId") String courseId);
    @PutMapping("/{courseId}")
    ResponseEntity<ResponseDTO> updateCourse(@RequestBody CourseDTO courseDTO,@PathVariable(value = "courseId") String courseId);

    @PutMapping("/register/{sid}/{cid}")
    ResponseEntity<ResponseDTO> registerCourse(@PathVariable(value = "sid") String studentId,@PathVariable(value = "cid") String courseId);

}
