package com.surnoi.employeeData.controller;

import com.surnoi.employeeData.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/billing")
public interface BillingController {

    @GetMapping("/generate/{studentId}")
    ResponseEntity<ResponseDTO> getBill(@PathVariable(value = "studentId") String studentId);

    @GetMapping("/download/{billId}")
    ResponseEntity<ResponseDTO> downloadBill(@PathVariable(value = "billId") UUID billId);
}
