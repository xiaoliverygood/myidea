package com.example.controller;

import com.example.entity.Admit;
import com.example.mapper.AdmitMapper;
import com.example.response.BaseResponse;
import com.example.service.AdmitService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            return BaseResponse.Error();
        }
    }
    @RequestMapping("/logout")
    public BaseResponse<String> logout(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Admit admit=(Admit) session.getAttribute("AdmitUser");
        if(admit==null){
            return BaseResponse.Error();
        }else {
            session.removeAttribute("AdmitUser");
            return BaseResponse.success("成功退出登录");
        }
    }
    @Autowired
    AdmitService admitService;
    @RequestMapping("/addLink")
    public BaseResponse addLink(HttpServletRequest request,int tid,int sid){
        HttpSession session = request.getSession();
        Admit admit=(Admit) session.getAttribute("AdmitUser");
        if(admit==null){
            return BaseResponse.Error();
        }else {
            admitService.TeacherAddStudent(sid,tid);
            return BaseResponse.success("成功添加");
        }
    }

    @RequestMapping("/deleteLink")
    public BaseResponse deleteLink(HttpServletRequest request,int sid){
        HttpSession session = request.getSession();
        Admit admit=(Admit) session.getAttribute("AdmitUser");
        if(admit==null){
            return BaseResponse.Error();
        }else {
            admitService.TeacherDeleteStudent(sid);
            return BaseResponse.success("成功删除关系");
        }
    }
    @RequestMapping("/deleteStudent")
    public BaseResponse deleteStudent(HttpServletRequest request,int sid){
        HttpSession session = request.getSession();
        Admit admit=(Admit) session.getAttribute("AdmitUser");
        if(admit==null){
            return BaseResponse.Error();
        }else {
            admitService.deleteStudent(sid);
            admitService.TeacherDeleteStudent(sid);
            return BaseResponse.success("成功删除学生");
        }
    }
    @RequestMapping("/deleteTeacher")
    public BaseResponse deleteTeacher(HttpServletRequest request,int tid){
        HttpSession session = request.getSession();
        Admit admit=(Admit) session.getAttribute("AdmitUser");
        if(admit==null){
            return BaseResponse.Error();
        }else {
            admitService.deleteTeacher(tid);
            return BaseResponse.success("成功删除老师");
        }
    }
}
