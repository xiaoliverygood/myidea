package com.example.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.BaseResponse;
import com.example.common.ResponMessge;
import com.example.model.entity.User;
import com.example.model.request.UserLogin;
import com.example.model.request.UserRegister;
import com.example.service.UserService;
import com.example.mapper.UserMapper;
import com.example.utility.CaptchaUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
    @Autowired
    UserMapper userMapper;

    @Override
    public BaseResponse login(UserLogin userLogin, HttpServletRequest httpServletRequest) {
        if(userLogin.getPassword().equals(userMapper.selectById(userLogin.getEmail()).getPassword())){
            User user = userMapper.selectById(userLogin.getEmail());
            HttpSession session=httpServletRequest.getSession();
            session.setAttribute("User-login",user);
            return BaseResponse.success(user);
        }else {
            return BaseResponse.Error(ResponMessge.UserOrPasswordError);
        }
    }

    @Override
    public BaseResponse userRegister(UserRegister userRegister) {
        if(CaptchaUtil.EmailAndCode.get(userRegister.getEmail()).equals(userRegister.getCode())){
            User register=new User(userRegister.getEmail(), userRegister.getPassword(), userRegister.getSex(), userRegister.getTime());
            userMapper.insert(register);
            return BaseResponse.success(register);
        }else {
            return BaseResponse.Error(ResponMessge.CaptchaError.getMessage());
        }
    }

    @Override
    public BaseResponse updataPassword(HttpServletRequest httpServletRequest, String newPassword) {

        HttpSession session=httpServletRequest.getSession();
        User user=(User) session.getAttribute("User-login");
        if(user == null){
            return BaseResponse.Error(ResponMessge.NologError.getMessage());
        }else {
            user.setPassword(newPassword);
            userMapper.updateById(user);
            return BaseResponse.success(user);
        }

    }
}




