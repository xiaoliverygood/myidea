package com.example.service;

import com.example.common.BaseResponse;
import com.example.model.entity.Admit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.request.ActivityRequest;
import com.example.model.request.AdmitRegister;
import com.example.model.request.DeleteActivityRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;

public interface AdmitService extends IService<Admit> {
    BaseResponse register(AdmitRegister admitRegister);

    BaseResponse login(Admit admit, HttpServletRequest httpServletRequest);

    BaseResponse updataPassword(HttpServletRequest httpServletRequest, String newPassword);

    BaseResponse releaseActivity(HttpServletRequest httpServlet, ActivityRequest activityRequest);

    BaseResponse deleteActivity(HttpServletRequest httpServlet,DeleteActivityRequest deleteActivityRequest);
    BaseResponse findMyActivity(HttpServletRequest httpServlet);
    BaseResponse findMyActivityUser(HttpServletRequest httpServlet,int id);
}

