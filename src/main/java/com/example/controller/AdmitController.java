package com.example.controller;

import com.example.entity.Admit;
import com.example.mapper.AdmitMapper;
import com.example.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdmitController {

    @Autowired
    AdmitMapper admitMapper;
    @RequestMapping("/login")
    public BaseResponse<Admit> login(@RequestBody Admit admin) {
        if(admin.equals(admitMapper.getAdimitById(admin.getId()).getPassword())){
           return BaseResponse.success(admitMapper.getAdimitById(admin.getId()));
        }else {
            return BaseResponse.Error();
        }
    }
}
