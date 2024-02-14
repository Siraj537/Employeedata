package com.surnoi.employeeData.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileEntity {

    private String fileName;
    private MultipartFile multipartFile;

}
