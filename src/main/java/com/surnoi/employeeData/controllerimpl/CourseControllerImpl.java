package com.surnoi.employeeData.controllerimpl;

import com.surnoi.employeeData.controller.CourseController;
import com.surnoi.employeeData.dto.ResponseDTO;
import com.surnoi.employeeData.dto.courses.CourseDTO;
import com.surnoi.employeeData.service.CourseService;
import com.surnoi.employeeData.utils.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseControllerImpl implements CourseController {

    private final CourseService courseService;

    public CourseControllerImpl(CourseService courseService) {
        this.courseService = courseService;
    }

    @PreAuthorize("hasAuthority('admin')")
    @Override
    public ResponseEntity<ResponseDTO> addCourses(List<CourseDTO> courseDTOList) {
        try{
            return courseService.addCourses(courseDTOList);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.getResponseEntity(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> getAllCourses() {
        try{
            return courseService.getCourses();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.getResponseEntity(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> getCourse(String courseId) {
        try{
            return courseService.getCourse(courseId);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.getResponseEntity(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('admin')")
    @Override
    public ResponseEntity<ResponseDTO> deleteCourse(String courseId) {
        try{
            return courseService.deleteCourse(courseId);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.getResponseEntity(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('admin')")
    @Override
    public ResponseEntity<ResponseDTO> updateCourse(CourseDTO courseDTO,String courseId) {
        try{
            return courseService.updateCourse(courseDTO,courseId);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.getResponseEntity(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('user')")
    @Override
    public ResponseEntity<ResponseDTO> registerCourse(String studentId, String courseId) {
        try{
            return courseService.registerCourse(studentId,courseId);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.getResponseEntity(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
