package com.surnoi.employeeData.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.surnoi.employeeData.dto.courses.CourseDTO;
import com.surnoi.employeeData.entities.Bill;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {

    private String message;
    private HttpStatus status;
    private String token;
    private List<CourseDTO> courseDTOList;
    private BillDTO bill;
    private byte[] pdfBytes;

}
