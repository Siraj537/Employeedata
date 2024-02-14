package com.surnoi.employeeData.controller;


import com.surnoi.employeeData.entities.FileEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileTestController {

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> upload(@RequestPart String fileEntity , @RequestPart MultipartFile multipartFile){

        System.out.println("File uploaded");
        return new ResponseEntity<>("File uploaded", HttpStatus.ACCEPTED);

    }
}
