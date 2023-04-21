package com.example.controller;

import com.example.common.ResponMessge;
import com.example.model.entity.Admit;
import com.example.mapper.AdmitMapper;
import com.example.common.BaseResponse;
import com.example.service.AdmitService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdmitController {
    @Autowired
    AdmitMapper admitMapper;
    @RequestMapping("/login")
    public BaseResponse<Admit> login(@RequestBody Admit admin, HttpServletRequest httpServletRequest) {
        if(admin.getPassword().equals(admitMapper.getAdimitById(admin.getId()).getPassword())){
            HttpSession Admitsession=httpServletRequest.getSession();
            Admitsession.setAttribute("AdmitUser",admitMapper.getAdimitById(admin.getId()));
           return BaseResponse.success(admitMapper.getAdimitById(admin.getId()));
        }else {
            return BaseResponse.Error(ResponMessge.UserOrPasswordError.name());
        }
    }
    @RequestMapping("/logout")
    public BaseResponse<String> logout(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Admit admit=(Admit) session.getAttribute("AdmitUser");
        if(admit==null){
            return BaseResponse.Error(ResponMessge.NologError.name());
        }else {
            session.removeAttribute("AdmitUser");
            return BaseResponse.success(ResponMessge.Logoutsuccess.name());
        }
    }
    @Autowired
    AdmitService admitService;
    @PostMapping ( "/addLink")
    public BaseResponse<String> addLink(HttpServletRequest request,Integer tid,Integer sid){
        HttpSession session = request.getSession();
        Admit admit=(Admit) session.getAttribute("AdmitUser");
        if(admit==null){
            return BaseResponse.Error(ResponMessge.NologError.name());
        }else {
            admitService.TeacherAddStudent(sid,tid);
            return BaseResponse.success("成功添加");
        }
    }

    @DeleteMapping("/deleteLink")
    public BaseResponse<String> deleteLink(HttpServletRequest request,Integer sid){
        HttpSession session = request.getSession();
        Admit admit=(Admit) session.getAttribute("AdmitUser");
        if(admit==null){
            return BaseResponse.Error("请登陆后再进行访问！");
        }else {
            admitService.TeacherDeleteStudent(sid);
            return BaseResponse.success("成功删除关系");
        }
    }
    @DeleteMapping("/deleteStudent")
    public BaseResponse<String> deleteStudent(HttpServletRequest request,Integer sid){
        HttpSession session = request.getSession();
        Admit admit=(Admit) session.getAttribute("AdmitUser");
        if(admit==null){
            return BaseResponse.Error(ResponMessge.NologError.name());
        }else {
            admitService.deleteStudent(sid);
            admitService.TeacherDeleteStudent(sid);
            return BaseResponse.success("成功删除学生");
        }
    }
    @DeleteMapping("/deleteTeacher")
    public BaseResponse<String> deleteTeacher(HttpServletRequest request,Integer tid){
        HttpSession session = request.getSession();
        Admit admit=(Admit) session.getAttribute("AdmitUser");
        if(admit==null){
            return BaseResponse.Error(ResponMessge.NologError.name());
        }else {
            admitService.deleteTeacher(tid);
            return BaseResponse.success("成功删除老师");
        }
    }
}
