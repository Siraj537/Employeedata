package com.surnoi.employeeData.repositories;

import com.surnoi.employeeData.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Integer> {
}
