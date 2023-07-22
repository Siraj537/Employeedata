package com.surnoi.employeeData.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentSelfDTO {

    private String name;
    private String city;
    private String mobile;
    private String email;
    private String roles;
    private String batch;
    private String password;
    private StudentNaukriSelftDetailsDTO studentNaukriSelftDetailsDTO;
}
