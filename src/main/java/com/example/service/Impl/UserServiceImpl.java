package com.example.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.BaseResponse;
import com.example.common.ResponMessge;
import com.example.mapper.ActivityMapper;
import com.example.model.entity.Activity;
import com.example.model.entity.User;
import com.example.model.request.UserLogin;
import com.example.model.request.UserRegister;
import com.example.service.UserService;
import com.example.mapper.UserMapper;
import com.example.utility.CaptchaUtil;
import com.example.utility.DateTranslation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
    @Autowired
    UserMapper userMapper;
    @Autowired
    ActivityMapper activityMapper;

    @Override
    public BaseResponse applyActivity(HttpServletRequest httpServletRequest, String nameActivity) {
        HttpSession session=httpServletRequest.getSession();
        User user=(User) session.getAttribute("User-login");
        if(user==null){
            return BaseResponse.Error(ResponMessge.NologError.getMessage());
        }else{
            Date currentDate = new Date();
            Activity activity= activityMapper.selectById(activityMapper.getActivityIdByName(nameActivity));
            int flag=activity.getBeginTime().compareTo(currentDate);
            if(flag>=0){//这样子开始时间比现在迟才能报名，也就是begin时间-现在时间大于零
                userMapper.UserLinkActivity(activity.getId(),user.getEmail(),"No",null,null);
                return BaseResponse.success("报名成功");
            }else{
                return BaseResponse.Error("活动已经开始或或者结束了，报名失败！");
            }

        }
    }

    @Override//为完成的功能
    public BaseResponse singinActivity(HttpServletRequest httpServletRequest, String SinginCode,String Name) {
       HttpSession session=httpServletRequest.getSession();
        User user=(User) session.getAttribute("User-login");
        if(user==null){
            return BaseResponse.Error(ResponMessge.NologError.getMessage());
        }else{
            String correctCode=CaptchaUtil.ActivityAndsigninCode.get(activityMapper.getActivityIdByName(Name));
            if(correctCode.equals(SinginCode)){
                Date date=new Date();
                userMapper.UserSingIn(date, activityMapper.getActivityIdByName(Name),user.getEmail() );
                return BaseResponse.success("签到成功！");
            }else {
                return BaseResponse.Error("签到码错误");
            }
        }
    }

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

    @Override
    public BaseResponse singoutActivity(HttpServletRequest httpServletRequest, String SingOutCode, String Name) {
        HttpSession session=httpServletRequest.getSession();
        User user=(User) session.getAttribute("User-login");
        if(user==null){
            return BaseResponse.Error(ResponMessge.NologError);
        }else {
            String correctCode=CaptchaUtil.ActivityAndsignoutCode.get(activityMapper.getActivityIdByName(Name));
            if(correctCode.equals(SingOutCode)){
                Date date=new Date();
                userMapper.UserSingOut(date,activityMapper.getActivityIdByName(Name),user.getEmail());
                Date dateSingIn=userMapper.SignInTimeByUserEmailandId(activityMapper.getActivityIdByName(Name),user.getEmail());
                LocalDateTime BeginTime= DateTranslation.DateTranslationLocalDateTime(date);
                LocalDateTime EndTime= DateTranslation.DateTranslationLocalDateTime(dateSingIn);
                Duration duration = Duration.between(BeginTime,EndTime);
                long TimeDuration=duration.toHours();
                long Timetotal=user.getTime()+TimeDuration;
                user.setTime(Timetotal);
                userMapper.updateById(user);
                return BaseResponse.success("签退成功！");
            }else {
                return BaseResponse.Error("签退失败，签退码错误！");
            }
        }

    }

    @Override
    public BaseResponse findMyAllActivity(HttpServletRequest httpServletRequest) {
        HttpSession session=httpServletRequest.getSession();
        User user=(User) session.getAttribute("User-login");
        if(user==null){
            return BaseResponse.Error(ResponMessge.NologError);
        }else{
           return BaseResponse.success(userMapper.findMyAllActivity(user.getEmail()));
        }

    }
}




