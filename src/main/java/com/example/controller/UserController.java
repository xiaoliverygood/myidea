package com.example.controller;
import com.example.common.BaseResponse;
import com.example.model.request.UserLogin;
import com.example.model.request.UserRegister;
import com.example.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
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
    public BaseResponse login(@RequestBody UserLogin userLogin, HttpServletRequest httpServletRequest){
        return userService.login(userLogin, httpServletRequest);
    }
    @PutMapping("/findPassword")
    public BaseResponse findPassword(String email,String newPassword,String code) {
        return userService.findPassword(email, newPassword, code);
    }
    @PutMapping("/updataPassword")
    public BaseResponse updataPassword(HttpServletRequest httpServletRequest,String email,String newPassword){
        return userService.updataPassword(httpServletRequest, email,newPassword);
    }
    @GetMapping("/applyActivity")
    public BaseResponse applyActivity(HttpServletRequest httpServletRequest,int id){
        return userService.applyActivity(httpServletRequest,id);
    }
    @GetMapping("/singinActivity")
    public BaseResponse singinActivity(HttpServletRequest httpServletRequest,String SinginCode,int id){
        return userService.singinActivity(httpServletRequest,SinginCode,id);
    }
    @GetMapping("/singoutActivity")
    public BaseResponse singoutActivity(HttpServletRequest httpServletRequest,String SingOutCode,int id){
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
