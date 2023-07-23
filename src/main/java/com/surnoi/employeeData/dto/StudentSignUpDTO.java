package com.surnoi.employeeData.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentSignUpDTO {

    @NotBlank
    @NotNull
    private String name;
    private String city;
    @NotBlank
    @NotNull
    private long contactNumber;
    @NotBlank
    @NotNull
    private String email;
    private String roles;
    private String batch;
    @NotBlank
    @NotNull
    private String password;
    private String address;
    private StudentNaukriDetailsDTO studentNaukriDetailsDTO;
}
