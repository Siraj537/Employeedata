package com.surnoi.employeeData.service;

import com.itextpdf.text.DocumentException;
import com.surnoi.employeeData.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

import javax.xml.transform.TransformerConfigurationException;
import java.util.UUID;

public interface BillingService {
    ResponseEntity<ResponseDTO> generateBill(String studentId);

    ResponseEntity<ResponseDTO> downloadBill(UUID billId) throws DocumentException, TransformerConfigurationException;
}
