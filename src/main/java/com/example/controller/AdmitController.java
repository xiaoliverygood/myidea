package com.example.controller;
import com.example.common.BaseResponse;
import com.example.model.entity.Admit;
import com.example.model.request.ActivityRequest;
import com.example.model.request.AdmitRegister;
import com.example.model.request.DeleteActivityRequest;
import com.example.service.AdmitService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Admin")
public class AdmitController {
    @Autowired
    AdmitService admitService;
    @PostMapping("/register")
    public BaseResponse register(@RequestBody AdmitRegister admitRegister){
       return admitService.register(admitRegister);
    }
    @PutMapping("/findPassword")
    public BaseResponse findPassword(String email,String newPassword,String code){
        return admitService.findPassword(email,newPassword,code);
    }
    @PostMapping("/login")
    public BaseResponse login(@RequestBody Admit admit, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        return admitService.login(admit,httpServletRequest,httpServletResponse);
    }
    @PutMapping("/updataPassword")
    public BaseResponse updataPassword(HttpServletRequest httpServletRequest,String email,String newPassword){
        return admitService.updataPassword(httpServletRequest,email,newPassword);
    }
    @PostMapping("/releaseActivity")
    public BaseResponse releaseActivity(HttpServletRequest httpServlet, @RequestBody ActivityRequest activityRequest){
        return admitService.releaseActivity(httpServlet,activityRequest);
    }
    @DeleteMapping("/deleteActivity")
    public BaseResponse deleteActivity(HttpServletRequest httpServlet, @RequestBody DeleteActivityRequest deleteActivityRequest){
        return admitService.deleteActivity(httpServlet,deleteActivityRequest);
    }
    @GetMapping("/findMyActivity")
    public BaseResponse findMyActivity(HttpServletRequest httpServlet){
        return admitService.findMyActivity(httpServlet);
    }
    @GetMapping("/findMyActivityUser")
    public BaseResponse findMyActivityUser(HttpServletRequest httpServlet,int id){
        return admitService.findMyActivityUser(httpServlet,id);
    }
    @GetMapping("/showMyMessage")
    public BaseResponse showMyMessage(HttpServletRequest httpServlet){
        return admitService.showMyMessage(httpServlet);
    }
    @GetMapping("/logout")
    public BaseResponse logout(HttpServletRequest httpServlet){
        return admitService.logout(httpServlet);
    }
}
