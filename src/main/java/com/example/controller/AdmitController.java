package com.example.controller;

import com.example.entity.Admit;
import com.example.mapper.AdmitMapper;
import com.example.response.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public BaseResponse<Admit> login(@RequestBody Admit admin, HttpServletRequest httpServletRequest) {
        if(admin.equals(admitMapper.getAdimitById(admin.getId()).getPassword())){
            HttpSession Admitsession=httpServletRequest.getSession();
            Admitsession.setAttribute("AdmitUser",admitMapper.getAdimitById(admin.getId()));
           return BaseResponse.success(admitMapper.getAdimitById(admin.getId()));
        }else {
            return BaseResponse.Error();
        }
    }
    @RequestMapping("/logout")
    public BaseResponse<String> logout(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Admit admit=(Admit) session.getAttribute("AdmitUser");
        if(admit==null){
            return BaseResponse.Error();
        }else {
            session.removeAttribute("AdmitUser");
            return BaseResponse.success("成功退出登录");
        }
    }
}
