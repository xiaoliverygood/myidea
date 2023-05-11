package com.example.controller;

import com.example.common.BaseResponse;
import com.example.utility.showPhotoUtil;
import com.example.utility.uploadActivityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class photoController {
    @Autowired
    uploadActivityUtil upload;
    @Autowired
    showPhotoUtil showPhotoUtil;
    @PostMapping("/upload")
    public BaseResponse upload(@RequestParam("file")MultipartFile file, String name){
        return BaseResponse.success(upload.uploadFile(file,name));
    }
    @PostMapping("/showPhoto")
    public BaseResponse showPhoto(String name){
       return BaseResponse.success(showPhotoUtil.getPhotoByName(name));
    }
}
