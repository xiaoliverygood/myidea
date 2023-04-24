package com.example.controller;

import com.example.common.BaseResponse;
import com.example.utility.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaptchaController {
    @Autowired
    CaptchaUtil captchaUtil;
    @RequestMapping("/captcha")
    public BaseResponse<String> getCaptcha(String emailAddress){
       return BaseResponse.success(captchaUtil.RigisterCode(emailAddress));
    }

}
