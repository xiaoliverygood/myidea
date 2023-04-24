package com.example.service;

import com.example.common.BaseResponse;
import com.example.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.request.UserLogin;
import com.example.model.request.UserRegister;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService extends IService<User> {
    BaseResponse userRegister(UserRegister userRegister);
    BaseResponse login(UserLogin userLogin, HttpServletRequest httpServletRequest);
    BaseResponse updataPassword(HttpServletRequest httpServletRequest,String newPassword);
    BaseResponse singinActivity(HttpServletRequest httpServletRequest,String SinginCode,String Name);
    BaseResponse applyActivity(HttpServletRequest httpServletRequest,String nameActivity);
    BaseResponse singoutActivity(HttpServletRequest httpServletRequest,String SingOutCode,String Name);
}
