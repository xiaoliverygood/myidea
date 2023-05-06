package com.example.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.BaseResponse;
import com.example.common.ResponMessge;
import com.example.mapper.ActivityMapper;
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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;

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
                HttpSession session = httpServletRequest.getSession();
                session.setAttribute("User-login", admit);
                String sessionId = session.getId();
                Cookie cookie = new Cookie("JSESSIONID", sessionId);
                cookie.setMaxAge(24 * 60 * 60);
                cookie.setPath("/*");
                httpServletResponse.addCookie(cookie);
                ResponEntityType responEntityType = new ResponEntityType(admit.getEmail(), admit.getPassword(), "admit");
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
        HttpSession session = httpServlet.getSession();
        Admit admit = (Admit) session.getAttribute("User-login");
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
        HttpSession session = httpServlet.getSession();
        Admit admit = (Admit) session.getAttribute("User-login");
        Boolean removeSuccess = activityService.removeActivity(deleteActivityRequest.getId());
        if (removeSuccess) {
            return BaseResponse.success("删除成功！");
        } else {
            return BaseResponse.Error("删除失败");
        }
    }
    @Override
    public BaseResponse updataPassword(HttpServletRequest httpServletRequest, String email, String newPassword) {
        HttpSession session = httpServletRequest.getSession();
        Admit admit = (Admit) session.getAttribute("User-login");
        admit.setPassword(newPassword);
        admitMapper.updateById(admit);
        return BaseResponse.success(admit);

    }

    @Override
    public BaseResponse findMyActivity(HttpServletRequest httpServlet) {
        HttpSession session = httpServlet.getSession();
        Admit admit = (Admit) session.getAttribute("User-login");
        QueryWrapper<Activity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("belonging_adimit", admit.getEmail());
        List<Activity> myActivity = activityMapper.selectList(queryWrapper);
        return BaseResponse.success(myActivity);

    }

    @Override
    public BaseResponse findMyActivityUser(HttpServletRequest httpServlet, int id) {
        HttpSession session = httpServlet.getSession();
        Admit admit = (Admit) session.getAttribute("User-login");
        List<String> myActivity = activityMapper.getActivityUserById(id);
        return BaseResponse.success(myActivity);
    }
    @Override
    public BaseResponse showMyMessage(HttpServletRequest httpServlet) {
        HttpSession session = httpServlet.getSession();
        Admit admit = (Admit) session.getAttribute("User-login");
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




