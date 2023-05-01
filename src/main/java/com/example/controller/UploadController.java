package com.example.controller;

import com.example.utility.uploadActivityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {
    @Autowired
    uploadActivityUtil upload;
    @RequestMapping("upload")
    public String upload(@RequestParam("file")MultipartFile file){
        return upload.uploadFile(file);
    }
}
