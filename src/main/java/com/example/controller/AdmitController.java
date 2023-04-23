package com.example.controller;
import com.example.common.BaseResponse;
import com.example.model.entity.Admit;
import com.example.model.request.ActivityRequest;
import com.example.model.request.AdmitRegister;
import com.example.service.AdmitService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/Admin")
public class AdmitController {
    @Autowired
    AdmitService admitService;
    @RequestMapping("/register")
    public BaseResponse register(@RequestBody AdmitRegister admitRegister){
       return admitService.register( admitRegister);
    }
    @RequestMapping("/login")
    public BaseResponse login(@RequestBody Admit admit, HttpServletRequest httpServletRequest){
        return admitService.login(admit,httpServletRequest);
    }
    @RequestMapping("/updataPassword")
    public BaseResponse updataPassword(HttpServletRequest httpServletRequest,String newPassword){
        return admitService.updataPassword(httpServletRequest,newPassword);
    }
    @RequestMapping("/releaseActivity")
    public BaseResponse releaseActivity(HttpServletRequest httpServlet, @RequestBody ActivityRequest activityRequest){
        return admitService.releaseActivity(httpServlet,activityRequest);
    }
}
