package com.surnoi.employeeData.repositories;

import com.surnoi.employeeData.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>{

     Optional<Student> findByNameAndEmailAndContactNumber(String name,String email,long mobile);
     Optional<Student> findByEmail(String email);
}
