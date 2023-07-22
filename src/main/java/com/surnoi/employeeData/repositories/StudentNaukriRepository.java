package com.surnoi.employeeData.repositories;

import com.surnoi.employeeData.entities.StudentNaukriDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentNaukriRepository extends JpaRepository<StudentNaukriDetails,Integer> {
}
