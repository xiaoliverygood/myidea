package com.example.controller;

import com.example.common.BaseResponse;
import com.example.common.ResponMessage;
import com.example.model.entity.Admit;
import com.example.model.request.AdmitRegister;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Admin")
public class AdmitController {
    @RequestMapping("/register")
    public BaseResponse<Admit> register(@RequestBody AdmitRegister admitRegister){


    }
}
