package com.example.service;

import com.example.common.BaseResponse;
import com.example.model.entity.Admit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.request.AdmitRegister;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;

public interface AdmitService extends IService<Admit> {
    BaseResponse register(AdmitRegister admitRegister);
    BaseResponse login(Admit admit, HttpServletRequest httpServletRequest);
    BaseResponse updataPassword(HttpServletRequest httpServletRequest,String newPassword);

}
