package com.example.controller;

import com.example.entity.Teacher;
import com.example.mapper.TeacherMapper;
import com.example.request.RequestLoginStudent;
import com.example.request.RequestLoginTeachger;
import com.example.request.RequestRegisterTeachger;
import com.example.response.BaseResponse;
import com.example.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Teacher")
public class TeacherLoginController {
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    CaptchaUtil captchaUtil;
    @RequestMapping("/register")
    public BaseResponse<Teacher> register(@RequestBody RequestRegisterTeachger register){
        String Code=CaptchaUtil.code;
        if(register.getCode().equals(Code)){
            Teacher teacher=new Teacher(register.getTid(),register.getName(),register.getEmail(),register.getLesson(),register.getPassword());
            teacherMapper.insertTeacher(teacher);
            return BaseResponse.success(teacher);
        }else {
            return BaseResponse.Error();
        }
    }
    @RequestMapping("/login")
    public BaseResponse<Teacher> login(@RequestBody RequestLoginTeachger login){
        if(login.getPassword().equals(teacherMapper.getTeacherById(login.getTid()))){
            return BaseResponse.success(teacherMapper.getTeacherById(login.getTid()));

        }else {
            return BaseResponse.Error();
        }


    }



}
