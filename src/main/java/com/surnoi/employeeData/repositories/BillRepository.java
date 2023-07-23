package com.surnoi.employeeData.repositories;

import com.surnoi.employeeData.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BillRepository extends JpaRepository<Bill, UUID> {

    @Query("SELECT b FROM Bill b JOIN b.student bs WHERE bs.studentId = :id")
    List<Bill> findByStudentId(@Param("id") int studentId);
}
