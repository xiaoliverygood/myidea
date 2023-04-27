package com.example.service;

import com.example.common.BaseResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.entity.User;
import com.example.model.request.UserLogin;
import com.example.model.request.UserRegister;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService extends IService<User> {
    BaseResponse userRegister(UserRegister userRegister);
    BaseResponse login(UserLogin userLogin, HttpServletRequest httpServletRequest);
    BaseResponse updataPassword(HttpServletRequest httpServletRequest,String mail,String newPassword);
    BaseResponse singinActivity(HttpServletRequest httpServletRequest,String SinginCode,int id);
    BaseResponse applyActivity(HttpServletRequest httpServletRequest,int id);
    BaseResponse singoutActivity(HttpServletRequest httpServletRequest,String SingOutCode,int id);
    BaseResponse findMyAllActivity(HttpServletRequest httpServletRequest);
    BaseResponse showMyMessage(HttpServletRequest httpServletRequest);
    BaseResponse logout(HttpServletRequest httpServletRequest);
}
