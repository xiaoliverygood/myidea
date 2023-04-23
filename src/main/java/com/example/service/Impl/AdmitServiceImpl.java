package com.example.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.BaseResponse;
import com.example.common.ResponMessge;
import com.example.model.entity.Admit;
import com.example.model.request.AdmitRegister;
import com.example.service.AdmitService;
import com.example.mapper.AdmitMapper;
import com.example.utility.CaptchaUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AdmitServiceImpl extends ServiceImpl<AdmitMapper, Admit> implements AdmitService{
    @Autowired
    AdmitMapper admitMapper;

    @Override
    public BaseResponse login(Admit admit, HttpServletRequest httpServletRequest) {
        if(admit.getPassword().equals(admitMapper.selectById(admit.getEmail()).getPassword())){
            HttpSession session=httpServletRequest.getSession();
            session.setAttribute("User-login",admit);
            return BaseResponse.success(admit);
        }else {
            return BaseResponse.Error(ResponMessge.UserOrPasswordError.getMessage());
        }
    }

    @Override
    public BaseResponse register(AdmitRegister admitRegister) {
        if(CaptchaUtil.EmailAndCode.get(admitRegister.getEmail()).equals(admitRegister.getCode())){
            Admit register=new Admit(admitRegister.getEmail(), admitRegister.getPassword());
            admitMapper.insert(register);
            return BaseResponse.success(register);
        }else {
            return BaseResponse.Error(ResponMessge.CaptchaError.getMessage());
        }
    }
}




