package com.surnoi.employeeData.serviceimpl;

import com.surnoi.employeeData.dto.ResponseDTO;
import com.surnoi.employeeData.dto.courses.CourseDTO;
import com.surnoi.employeeData.entities.Course;
import com.surnoi.employeeData.entities.Student;
import com.surnoi.employeeData.mapper.CourseMapper;
import com.surnoi.employeeData.repositories.CourseRepository;
import com.surnoi.employeeData.repositories.StudentRepository;
import com.surnoi.employeeData.service.CourseService;
import com.surnoi.employeeData.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final StudentRepository studentRepository;

    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.studentRepository = studentRepository;
    }

    @Override
    public ResponseEntity<ResponseDTO> addCourses(List<CourseDTO> courseDTOList) {
        try {
            if(courseDTOList != null && courseDTOList.size()>0){
                List<Course> courses = courseMapper.courseDTOtoCourse(courseDTOList);
                List<Course> savedCourses = courseRepository.saveAll(courses);
                return ResponseUtils.getResponseForCourses( HttpStatus.OK,"Successfully added new list of courses",courseMapper.courseToCourseDTO(savedCourses));
            }
            else {
                return ResponseUtils.getResponseEntity("Course List is Empty", HttpStatus.OK);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.getResponseEntity(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> getCourses() {
        try {
            List<Course> allCourses = courseRepository.findAll();
            if(allCourses != null && allCourses.size()>0){
                List<CourseDTO> courseDTOS = courseMapper.courseToCourseDTO(allCourses);
                return ResponseUtils.getResponseForCourses( HttpStatus.OK,"Successfully fetched the complete list of courses",courseDTOS);
            }
            else {
                return ResponseUtils.getResponseEntity("Course List is Empty", HttpStatus.OK);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.getResponseEntity(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> getCourse(String courseId) {
        try {
            if(StringUtils.isNotBlank(courseId)){
                Optional<Course> courseOptional = courseRepository.findById(Integer.parseInt(courseId));
                if(courseOptional.isPresent()){
                    CourseDTO courseDTO = courseMapper.courseToCourseDTO(courseOptional.get());
                    return ResponseUtils.getResponseForCourses(HttpStatus.OK,"Successfully found the course",  Arrays.asList(courseDTO));
                }
                else {
                    return ResponseUtils.getResponseEntity("Invalid Course Id or course is removed", HttpStatus.BAD_REQUEST);
                }
            }
            else {
                return ResponseUtils.getResponseEntity("Invalid Course Id", HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.getResponseEntity(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> deleteCourse(String courseId) {
        try {
            if(StringUtils.isNotBlank(courseId)){
                Optional<Course> courseOptional = courseRepository.findById(Integer.parseInt(courseId));
                if(courseOptional.isPresent()){
                    courseRepository.deleteById(Integer.parseInt(courseId));
                    CourseDTO courseDTO = courseMapper.courseToCourseDTO(courseOptional.get());
                    return ResponseUtils.getResponseForCourses(HttpStatus.OK,"Successfully deleted the course",  Arrays.asList(courseDTO));
                }
                else {
                    return ResponseUtils.getResponseEntity("Invalid Course Id or course is removed", HttpStatus.BAD_REQUEST);
                }
            }
            else {
                return ResponseUtils.getResponseEntity("Invalid Course Id", HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.getResponseEntity(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> updateCourse(CourseDTO courseDTO, String courseId) {
        try {
            if(StringUtils.isNotBlank(courseId)){
                Optional<Course> courseOptional = courseRepository.findById(Integer.parseInt(courseId));
                if(courseOptional.isPresent()){
                    Course course = courseMapper.courseDTOtoCourse(courseDTO);
                    Course updatedCourse = courseRepository.save(course);
                    return ResponseUtils.getResponseForCourses(HttpStatus.OK,"Successfully deleted the course",  Arrays.asList(courseDTO));
                }
                else {
                    return ResponseUtils.getResponseEntity("Invalid Course Id or course is removed", HttpStatus.BAD_REQUEST);
                }
            }
            else {
                return ResponseUtils.getResponseEntity("Invalid Course Id", HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.getResponseEntity(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> registerCourse(String studentId, String courseId) {
        try {
            if(StringUtils.isNotBlank(studentId) && StringUtils.isNotBlank(courseId)){
                Optional<Course> courseOptional = courseRepository.findById(Integer.parseInt(courseId));
                Optional<Student> studentOptional = studentRepository.findById(Integer.parseInt(studentId));
                if(courseOptional.isPresent() && studentOptional.isPresent()){
                    Course course = courseOptional.get();
                    Student student = studentOptional.get();
                    student.getCourses().add(course);
                    studentRepository.save(student);
                    return ResponseUtils.getResponseEntity("Successfully registered the course", HttpStatus.OK);
                }
                else {
                    return ResponseUtils.getResponseEntity("Invalid Course Id or course is removed", HttpStatus.BAD_REQUEST);
                }
            }
            else {
                return ResponseUtils.getResponseEntity("CourseId or StudentId is missing", HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.getResponseEntity(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
