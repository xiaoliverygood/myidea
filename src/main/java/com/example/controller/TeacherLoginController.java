package com.example.controller;

import com.example.entity.Student;
import com.example.entity.StudentResult;
import com.example.entity.Teacher;
import com.example.mapper.TeacherMapper;
import com.example.request.RequestLoginStudent;
import com.example.request.RequestLoginTeachger;
import com.example.request.RequestRegisterTeachger;
import com.example.response.BaseResponse;
import com.example.utils.CaptchaUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Teacher")
public class TeacherLoginController {
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    CaptchaUtil captchaUtil;
    @PostMapping("/register")
    public BaseResponse<Teacher> register(@RequestBody RequestRegisterTeachger register){
        String Code=CaptchaUtil.code;
        if(register.getCode().equals(Code)){
            Teacher teacher=new Teacher(register.getTid(),register.getName(),register.getEmail(),register.getLesson(),register.getPassword());
            teacherMapper.insertTeacher(teacher);
            return BaseResponse.success(teacher);
        }else {
            return BaseResponse.Error("请求信息出错");
        }
    }
    @RequestMapping("/login")
    public BaseResponse<Teacher> login(@RequestBody RequestLoginTeachger login,HttpServletRequest httpServletRequest){
        if(login.getPassword().equals(teacherMapper.getTeacherById(login.getTid()).getPassword())){
            HttpSession httpSession=httpServletRequest.getSession();
            httpSession.setAttribute("teacherUser",teacherMapper.getTeacherById(login.getTid()));
            return BaseResponse.success(teacherMapper.getTeacherById(login.getTid()));
        }else {
            return BaseResponse.Error("登录失败，请检查id号，密码，验证码");
        }
    }
    @GetMapping("/Inquire")
    public BaseResponse<Teacher> InquireTeacher(HttpServletRequest httpServletRequest){
        HttpSession httpSession=httpServletRequest.getSession();
        Teacher teacher=(Teacher) httpSession.getAttribute("teacherUser");
        if(teacher==null){
            return BaseResponse.Error("请登陆后再进行访问！");
        }else {
            return BaseResponse.success(teacherMapper.getTeacherById(teacher.getTid()));
        }
    }
    @PutMapping("/updateName")
    public BaseResponse<Teacher> updateTeacherName(HttpServletRequest httpServletRequest, String newName){
        HttpSession httpSession=httpServletRequest.getSession();
       Teacher teacher=(Teacher) httpSession.getAttribute("teacherUser");
        if(teacher==null){
            return BaseResponse.Error("请登陆后再进行访问！");
        }else {
           teacherMapper.UpdateTeacherNameById(teacher.getTid(),newName);
            return BaseResponse.success(teacherMapper.getTeacherById(teacher.getTid()));
        }
    }
    @PutMapping("/updatePassword")
    public BaseResponse<Teacher> updateStudentPassword(HttpServletRequest httpServletRequest,String newPassword){
        HttpSession httpSession=httpServletRequest.getSession();
        Teacher teacher=(Teacher) httpSession.getAttribute("teacherUser");
        if(teacher==null){
            return BaseResponse.Error("请登陆后再进行访问！");
        }else {
            teacherMapper.UpdateTeacherPasswordById(teacher.getTid(),newPassword);
            return BaseResponse.success(teacherMapper.getTeacherById(teacher.getTid()));
        }
    }
    @RequestMapping("/logout")
    public BaseResponse<String> logout(HttpServletRequest httpServletRequest){
        HttpSession httpSession=httpServletRequest.getSession();
       Teacher teacher=(Teacher) httpSession.getAttribute("teacherUser");
        if(teacher==null){
            return BaseResponse.Error("请登陆后再进行访问！");
        }else {
            httpSession.removeAttribute("teacherUser");
            return BaseResponse.success("成功退出登录");
        }
    }
    @GetMapping("/findStudent")
    public BaseResponse<List<StudentResult>> findStudent(HttpServletRequest httpServletRequest){
        HttpSession httpSession=httpServletRequest.getSession();
        Teacher teacher=(Teacher) httpSession.getAttribute("teacherUser");
        if(teacher==null){
            return BaseResponse.Error("请登陆后再进行访问！");
        }else {
            List<StudentResult> listStudent=teacherMapper.FindMyStudentBytid(teacher.getTid());
           return BaseResponse.success(listStudent);
        }
    }



}
