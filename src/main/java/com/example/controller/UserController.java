package com.example.controller;
import com.example.common.BaseResponse;
import com.example.model.request.UserLogin;
import com.example.model.request.UserRegister;
import com.example.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/register")
    public BaseResponse register(@RequestBody UserRegister userRegister){return userService.userRegister(userRegister);}
    @PostMapping("/login")
    public BaseResponse login(@RequestBody UserLogin userLogin, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        return userService.login(userLogin, httpServletRequest,httpServletResponse);
    }
    @PutMapping("/findPassword")
    public BaseResponse findPassword(String email,String newPassword,String code) {
        return userService.findPassword(email, newPassword, code);
    }
    @PutMapping("/updataPassword")
    public BaseResponse updataPassword(HttpServletRequest httpServletRequest,String email,String newPassword){
        return userService.updataPassword(httpServletRequest, email,newPassword);
    }
    @PostMapping("/applyActivity")
    public BaseResponse applyActivity(HttpServletRequest httpServletRequest,Integer id){
        return userService.applyActivity(httpServletRequest,id);
    }
    @PostMapping("/singinActivity")
    public BaseResponse singinActivity(HttpServletRequest httpServletRequest,String SinginCode,int id){
        System.out.println("签到");
        System.out.println(id);
        System.out.println(SinginCode);
        return userService.singinActivity(httpServletRequest,SinginCode,id);
    }
    @PostMapping("/singoutActivity")
    public BaseResponse singoutActivity(HttpServletRequest httpServletRequest,String SingOutCode,int id){
        System.out.println("签退");
        System.out.println(id);
        System.out.println(SingOutCode);
        return userService.singoutActivity(httpServletRequest,SingOutCode,id);
    }
    @GetMapping("/findMyAllActivity")
    public BaseResponse findMyAllActivity(HttpServletRequest httpServletRequest){
       return userService.findMyAllActivity(httpServletRequest);
    }
    @GetMapping("/showMyMessage")
    public BaseResponse showMyMessage(HttpServletRequest httpServletRequest){
       return userService.showMyMessage(httpServletRequest);
    }
    @GetMapping("/logout")
    public BaseResponse logout(HttpServletRequest httpServletRequest){
        return userService.logout(httpServletRequest);
    }

}
