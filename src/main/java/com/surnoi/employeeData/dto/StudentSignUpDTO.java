package com.surnoi.employeeData.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentSignUpDTO {

    @NotBlank
    @NotNull
    private String name;
    private String city;
    @NotBlank
    @NotNull
    private String mobile;
    @NotBlank
    @NotNull
    private String email;
    private String roles;
    private String batch;
    @NotBlank
    @NotNull
    private String password;
    private StudentNaukriDetailsDTO studentNaukriDetailsDTO;
}
