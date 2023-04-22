package com.example.controller;

import com.example.common.BaseResponse;
import com.example.model.entity.Admit;
import com.example.model.entity.User;
import com.example.model.request.AdmitRegister;
import com.example.model.request.UserRegister;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/User")
public class UserController {
    @RequestMapping("/register")
    public BaseResponse<User> register(@RequestBody UserRegister userRegister){

    }
}
