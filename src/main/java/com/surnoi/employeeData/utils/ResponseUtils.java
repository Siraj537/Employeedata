package com.surnoi.employeeData.utils;

import com.surnoi.employeeData.dto.BillDTO;
import com.surnoi.employeeData.dto.ResponseDTO;
import com.surnoi.employeeData.dto.courses.CourseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseUtils {

    public static ResponseEntity<ResponseDTO> getResponseEntity(String responseMessage, HttpStatus httpStatus){
        ResponseDTO responseDTO = ResponseDTO.builder()
                .message(responseMessage)
                .status(httpStatus).build();
        return new ResponseEntity<>(responseDTO ,httpStatus);
    }

    public static ResponseEntity<ResponseDTO> getResponseEntityForToken(HttpStatus httpStatus,String responseMessage,String token ){
        ResponseDTO responseDTO = ResponseDTO.builder()
                .message(responseMessage)
                .token(token)
                .status(httpStatus).build();
        return new ResponseEntity<>(responseDTO ,httpStatus);
    }

    public static ResponseEntity<ResponseDTO> getResponseForCourses(HttpStatus httpStatus, String responseMessage, List<CourseDTO> courseDTOList){
        ResponseDTO responseDTO = ResponseDTO.builder()
                .message(responseMessage)
                .courseDTOList(courseDTOList)
                .status(httpStatus).build();
        return new ResponseEntity<>(responseDTO ,httpStatus);
    }

    public static ResponseEntity<ResponseDTO> getResponseForBill(HttpStatus httpStatus, String responseMessage, BillDTO billDTO) {
        ResponseDTO responseDTO = ResponseDTO.builder()
                .message(responseMessage)
                .bill(billDTO)
                .status(httpStatus).build();
        return new ResponseEntity<>(responseDTO ,httpStatus);
    }

    public static ResponseEntity<ResponseDTO> getResponseForBill(HttpStatus httpStatus, String responseMessage, byte[] pdfBytes) {
        ResponseDTO responseDTO = ResponseDTO.builder()
                .message(responseMessage)
                .pdfBytes(pdfBytes)
                .status(httpStatus).build();
        return new ResponseEntity<>(responseDTO ,httpStatus);
    }
}
