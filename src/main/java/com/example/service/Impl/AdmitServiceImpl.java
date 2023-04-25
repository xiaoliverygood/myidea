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
import com.example.service.ActivityService;
import com.example.service.AdmitService;
import com.example.mapper.AdmitMapper;
import com.example.utility.CaptchaUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdmitServiceImpl extends ServiceImpl<AdmitMapper, Admit> implements AdmitService{
    @Autowired
    AdmitMapper admitMapper;
    @Autowired
    CaptchaUtil captchaUtil;
    @Autowired
    ActivityMapper activityMapper;
    @Autowired
    ActivityService activityService;
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
            CaptchaUtil.EmailAndCode.remove(admitRegister.getEmail());
            return BaseResponse.success(register);
        }else {
            return BaseResponse.Error(ResponMessge.CaptchaError.getMessage());
        }
    }
    @Override
    public BaseResponse releaseActivity(HttpServletRequest httpServlet, ActivityRequest activityRequest) {
        HttpSession session=httpServlet.getSession();
        Admit admit=(Admit) session.getAttribute("User-login");
       if(admit==null){
           return BaseResponse.Error(ResponMessge.NologError.getMessage());
       }else{
          Boolean addSuccess= activityService.addActivity(activityRequest,admit.getEmail());
          if(addSuccess){
              captchaUtil.ActivitySinginCode(admit.getEmail(),activityMapper.getActivityIdByName(activityRequest.getName()));
              captchaUtil.ActivitySingOutCode(admit.getEmail(),activityMapper.getActivityIdByName(activityRequest.getName()));
              return BaseResponse.success("成功发布活动");
          }else {
              return BaseResponse.Error("验证码发送失败");
          }
       }
    }

    @Override
    public BaseResponse deleteActivity(HttpServletRequest httpServlet, DeleteActivityRequest deleteActivityRequest) {
       HttpSession session=httpServlet.getSession();
       Admit admit=(Admit) session.getAttribute("User-login");
       if(admit==null){
           return BaseResponse.Error(ResponMessge.NologError.getMessage());
       }else{
           Boolean removeSuccess=activityService.removeActivity(activityMapper.getActivityIdByName(deleteActivityRequest.getActivityName()));
           if(removeSuccess){
               return BaseResponse.success("删除成功！");
           }else {
               return BaseResponse.Error("删除失败");
           }
       }
    }

    @Override
    public BaseResponse updataPassword(HttpServletRequest httpServletRequest, String newPassword) {
       HttpSession session=httpServletRequest.getSession();
      Admit admit=(Admit) session.getAttribute("User-login");
      if(admit==null){
          return BaseResponse.Error(ResponMessge.NologError.getMessage());
      }else {
          admit.setPassword(newPassword);
          admitMapper.updateById(admit);
          return BaseResponse.success(admit);
      }
    }

    @Override
    public BaseResponse findMyActivity(HttpServletRequest httpServlet) {
        HttpSession session=httpServlet.getSession();
        Admit admit=(Admit) session.getAttribute("User-login");
        if(admit==null){
            return BaseResponse.Error(ResponMessge.NologError);
        }else {
            QueryWrapper<Activity> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("belonging_adimit",admit.getEmail());
            List<Activity> myActivity=activityMapper.selectList(queryWrapper);
            return BaseResponse.success(myActivity);
        }
    }

    @Override
    public BaseResponse findMyActivityUser(HttpServletRequest httpServlet, int id) {
        HttpSession session=httpServlet.getSession();
        Admit admit=(Admit) session.getAttribute("User-login");
        if(admit==null){
            return BaseResponse.Error(ResponMessge.NologError);
        }else {
           List<String> myActivity= activityMapper.getActivityUserById(id);
           return BaseResponse.success(myActivity);
        }
    }

    @Override
    public BaseResponse showMyMessage(HttpServletRequest httpServlet) {
        HttpSession session=httpServlet.getSession();
        Admit admit=(Admit) session.getAttribute("User-login");
        if(admit==null){
            return BaseResponse.Error(ResponMessge.NologError);
        }else{
            return BaseResponse.success(admit);
        }
    }

    @Override
    public BaseResponse logout(HttpServletRequest httpServlet) {
        HttpSession session=httpServlet.getSession();
        Admit admit=(Admit) session.getAttribute("User-login");
        if(admit==null){
            return BaseResponse.Error(ResponMessge.NologError);
        }else{
            session.removeAttribute("User-login");
            return BaseResponse.success(ResponMessge.Logoutsuccess);
        }
    }
}




