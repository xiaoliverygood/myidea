package com.example.controller;

import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import com.example.request.RequestLoginStudent;
import com.example.request.RequestRegisterStudent;
import com.example.response.BaseResponse;
import com.example.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Student")
public class StudentLoginController {


    @Autowired
    StudentMapper studentMapper;
    @RequestMapping("/register")
    public BaseResponse<Student> register(@RequestBody RequestRegisterStudent register){
            if( CaptchaUtil.code.equals(register.getCode())){

                Student student=new Student(register.getSid(),register.getName(),register.getEmail(),register.getPassword());
                studentMapper.insertStudent(student);
                return BaseResponse.success(student);
            }else{
                return BaseResponse.Error();
            }

    }
    @RequestMapping("/login")
    public BaseResponse<Student> login(@RequestBody RequestLoginStudent login){
        if(login.getPassword().equals(studentMapper.getStudentById(login.getSid()).getPassword())){

            return BaseResponse.success(studentMapper.getStudentById(login.getSid()));
        }else {
            return BaseResponse.Error();
        }

    }
}
