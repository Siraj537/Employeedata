package com.surnoi.employeeData.controllerimpl;

import com.surnoi.employeeData.controller.BillingController;
import com.surnoi.employeeData.dto.ResponseDTO;
import com.surnoi.employeeData.service.BillingService;
import com.surnoi.employeeData.utils.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class BillingControllerImpl implements BillingController {

    private final BillingService billingService;

    public BillingControllerImpl(BillingService billingService) {
        this.billingService = billingService;
    }

    @Override
    public ResponseEntity<ResponseDTO> getBill(String studentId) {
        try{
            return billingService.generateBill(studentId);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.getResponseEntity(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> downloadBill(UUID billId) {
        try{
            return billingService.downloadBill(billId);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.getResponseEntity(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
