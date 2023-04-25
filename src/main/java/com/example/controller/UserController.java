package com.example.controller;
import com.example.common.BaseResponse;
import com.example.model.request.UserLogin;
import com.example.model.request.UserRegister;
import com.example.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/register")
    public BaseResponse register(@RequestBody UserRegister userRegister){return userService.userRegister(userRegister);}
    @RequestMapping("/login")
    public BaseResponse login(@RequestBody UserLogin userLogin, HttpServletRequest httpServletRequest){
        return userService.login(userLogin, httpServletRequest);
    }
    @RequestMapping("/updataPassword")
    public BaseResponse updataPassword(HttpServletRequest httpServletRequest,String newPassword){
        return userService.updataPassword(httpServletRequest, newPassword);
    }
    @RequestMapping("/applyActivity")
    public BaseResponse applyActivity(HttpServletRequest httpServletRequest,String nameActivity){
        return userService.applyActivity(httpServletRequest,nameActivity);
    }
    @RequestMapping("/singinActivity")
    public BaseResponse singinActivity(HttpServletRequest httpServletRequest,String SinginCode,String Name){
        return userService.singinActivity(httpServletRequest,SinginCode,Name);
    }
    @RequestMapping("/singoutActivity")
    public BaseResponse singoutActivity(HttpServletRequest httpServletRequest,String SingOutCode,String Name){
        return userService.singoutActivity(httpServletRequest,SingOutCode,Name);
    }
    @RequestMapping("/findMyAllActivity")
    public BaseResponse findMyAllActivity(HttpServletRequest httpServletRequest){
       return userService.findMyAllActivity(httpServletRequest);
    }
    @RequestMapping("/showMyMessage")
    public BaseResponse showMyMessage(HttpServletRequest httpServletRequest){
       return userService.showMyMessage(httpServletRequest);
    }
    @RequestMapping("/logout")
    public BaseResponse logout(HttpServletRequest httpServletRequest){
        return userService.logout(httpServletRequest);
    }

}
