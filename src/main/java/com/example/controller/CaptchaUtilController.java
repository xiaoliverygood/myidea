package com.example.controller;

import com.example.model.request.RequestCaptcha;
import com.example.common.BaseResponse;
import com.example.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaptchaUtilController {
    @Autowired
    CaptchaUtil captchaUtil;
    @RequestMapping("/Captcha")
    public BaseResponse<String> getCaptcha(@RequestBody RequestCaptcha requestCaptcha){
        captchaUtil.contextLoads(requestCaptcha.getEmailOfCaptcha());
        return BaseResponse.success(CaptchaUtil.code);
    }

}
