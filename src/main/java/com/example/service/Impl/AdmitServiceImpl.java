package com.example.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.BaseResponse;
import com.example.common.ResponMessge;
import com.example.mapper.ActivityMapper;
import com.example.model.LoginAuth;
import com.example.model.entity.Activity;
import com.example.model.entity.Admit;
import com.example.model.request.ActivityRequest;
import com.example.model.request.AdmitRegister;
import com.example.model.request.DeleteActivityRequest;
import com.example.model.respon.ResponEntityType;
import com.example.service.ActivityService;
import com.example.service.AdmitService;
import com.example.mapper.AdmitMapper;
import com.example.utility.CaptchaUtil;
import com.example.utility.EmailRegularExpression;
import com.example.utility.UserHolder;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class AdmitServiceImpl extends ServiceImpl<AdmitMapper, Admit> implements AdmitService {
    @Autowired
    AdmitMapper admitMapper;
    @Autowired
    CaptchaUtil captchaUtil;
    @Autowired
    ActivityMapper activityMapper;
    @Autowired
    ActivityService activityService;

    @Override
    public BaseResponse login(Admit admit, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        if (EmailRegularExpression.RegularEmailPattern(admit.getEmail())) {
            if (admit.getPassword().equals(admitMapper.selectById(admit.getEmail()).getPassword())) {
                LoginAuth loginAuth = new LoginAuth(UUID.randomUUID().toString(),admit);
                UserHolder.saveUser(loginAuth.getToken(),loginAuth);
                ResponEntityType responEntityType = new ResponEntityType( "admit",loginAuth.getToken());
                return BaseResponse.success(responEntityType);
            } else {
                return BaseResponse.Error(ResponMessge.UserOrPasswordError.getMessage());
            }
        } else {
            return BaseResponse.Error(ResponMessge.UserOrPasswordError);
        }

    }
    @Override
    public BaseResponse register(AdmitRegister admitRegister) {
        if (EmailRegularExpression.RegularEmailPattern(admitRegister.getEmail())) {
            if (CaptchaUtil.EmailAndCode.get(admitRegister.getEmail()).equals(admitRegister.getCode())) {
                Admit register = new Admit(admitRegister.getEmail(), admitRegister.getPassword());
                admitMapper.insert(register);
                CaptchaUtil.EmailAndCode.remove(admitRegister.getEmail());
                return BaseResponse.success(register);
            } else {
                return BaseResponse.Error(ResponMessge.CaptchaError.getMessage());
            }
        } else {
            return BaseResponse.Error(ResponMessge.UserOrPasswordError);
        }
    }

    @Override
    public BaseResponse releaseActivity(HttpServletRequest httpServlet, ActivityRequest activityRequest) {
        LoginAuth loginAuth = (LoginAuth) UserHolder.get(httpServlet.getHeader("token"));
        Admit admit = (Admit) loginAuth.getData();
        Boolean addSuccess = activityService.addActivity(activityRequest, admit.getEmail());
        if (addSuccess) {
            captchaUtil.ActivitySinginCode(admit.getEmail(), activityMapper.getActivityIdByName(activityRequest.getName()));
            captchaUtil.ActivitySingOutCode(admit.getEmail(), activityMapper.getActivityIdByName(activityRequest.getName()));
            return BaseResponse.success("成功发布活动");
        } else {
            return BaseResponse.Error("验证码发送失败");
        }
    }
    @Override
    public BaseResponse deleteActivity(HttpServletRequest httpServlet, DeleteActivityRequest deleteActivityRequest) {
        LoginAuth loginAuth = (LoginAuth) UserHolder.get(httpServlet.getHeader("token"));
        Admit admit = (Admit) loginAuth.getData();
        Boolean removeSuccess = activityService.removeActivity(deleteActivityRequest.getId());
        if (removeSuccess) {
            return BaseResponse.success("删除成功！");
        } else {
            return BaseResponse.Error("删除失败");
        }
    }
    @Override
    public BaseResponse updataPassword(HttpServletRequest httpServletRequest, String email, String newPassword) {
        LoginAuth loginAuth = (LoginAuth) UserHolder.get(httpServletRequest.getHeader("token"));
        Admit admit = (Admit) loginAuth.getData();
        admit.setPassword(newPassword);
        admitMapper.updateById(admit);
        return BaseResponse.success(admit);

    }

    @Override
    public BaseResponse findMyActivity(HttpServletRequest httpServlet) {
        LoginAuth loginAuth = (LoginAuth) UserHolder.get(httpServlet.getHeader("token"));
        Admit admit = (Admit) loginAuth.getData();
        QueryWrapper<Activity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("belonging_adimit", admit.getEmail());
        List<Activity> myActivity = activityMapper.selectList(queryWrapper);
        return BaseResponse.success(myActivity);

    }

    @Override
    public BaseResponse findMyActivityUser(HttpServletRequest httpServlet, int id) {
        LoginAuth loginAuth = (LoginAuth) UserHolder.get(httpServlet.getHeader("token"));
        Admit admit = (Admit) loginAuth.getData();
        List<String> myActivity = activityMapper.getActivityUserById(id);
        return BaseResponse.success(myActivity);
    }
    @Override
    public BaseResponse showMyMessage(HttpServletRequest httpServlet) {
        LoginAuth loginAuth = (LoginAuth) UserHolder.get(httpServlet.getHeader("token"));
        Admit admit = (Admit) loginAuth.getData();
        return BaseResponse.success(admit);
    }

    @Override
    public BaseResponse logout(HttpServletRequest httpServlet) {
        HttpSession session = httpServlet.getSession();
        Admit admit = (Admit) session.getAttribute("User-login");
        session.removeAttribute("User-login");
        return BaseResponse.success(ResponMessge.Logoutsuccess);
    }

    @Override
    public BaseResponse findPassword(String email, String newPassword, String code) {
        if (CaptchaUtil.EmailAndCodeFindpassword.get(email).equals(code)) {
            Admit admit = admitMapper.selectById(email);
            admit.setPassword(newPassword);
            admitMapper.updateById(admit);
            return BaseResponse.success(admit);
        } else {
            return BaseResponse.Error(ResponMessge.CaptchaError);
        }
    }
}




