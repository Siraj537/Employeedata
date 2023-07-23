package com.surnoi.employeeData.mapper;

import com.surnoi.employeeData.dto.courses.CourseDTO;
import com.surnoi.employeeData.entities.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mappings({
            @Mapping(target = "courseId", ignore = true)
    })
    Course courseDTOtoCourse(CourseDTO courseDTO);
    List<Course> courseDTOtoCourse(List<CourseDTO> courseDTO);

    CourseDTO courseToCourseDTO(Course course);
    List<CourseDTO> courseToCourseDTO(List<Course> courseList);
}
