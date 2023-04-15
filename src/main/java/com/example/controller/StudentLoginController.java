package com.example.controller;

import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import com.example.request.RequestLoginStudent;
import com.example.request.RequestRegisterStudent;
import com.example.response.BaseResponse;
import com.example.utils.CaptchaUtil;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
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
    public BaseResponse<Student> login(@RequestBody RequestLoginStudent login,HttpServletRequest httpServletRequest){
        if(login.getPassword().equals(studentMapper.getStudentById(login.getSid()).getPassword())){
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("User", studentMapper.getStudentById(login.getSid()));
            return BaseResponse.success(studentMapper.getStudentById(login.getSid()));
        }else {
            return BaseResponse.Error();
        }

    }
    @RequestMapping("/updateName")
    public BaseResponse<Student> updateStudentName(HttpServletRequest httpServletRequest,String newName){
        HttpSession httpSession=httpServletRequest.getSession();
        Student student=(Student) httpSession.getAttribute("User");
        if(student==null){
           return BaseResponse.Error();
        }else {
            studentMapper.UpdateStudentNameById(student.getSid(),newName);
            return BaseResponse.success(studentMapper.getStudentById(student.getSid()));
        }
    }
    @RequestMapping("/updatePassword")
    public BaseResponse<Student> updateStudentPassword(HttpServletRequest httpServletRequest,String newPassword){
        HttpSession httpSession=httpServletRequest.getSession();
        Student student=(Student) httpSession.getAttribute("User");
        if(student==null){
            return BaseResponse.Error();
        }else {
            studentMapper.UpdateStudentPasswordById(student.getSid(),newPassword);
            return BaseResponse.success(studentMapper.getStudentById(student.getSid()));
        }
    }
    @RequestMapping("/inquire")
    public BaseResponse<Student> InquireStudent(HttpServletRequest httpServletRequest){
        HttpSession httpSession=httpServletRequest.getSession();
        Student student=(Student) httpSession.getAttribute("User");
        if(student==null){
            return BaseResponse.Error();
        }else {
            return BaseResponse.success(studentMapper.getStudentById(student.getSid()));
        }
    }
    @RequestMapping("/logout")
    public BaseResponse<String> logout(HttpServletRequest httpServletRequest){
        HttpSession httpSession=httpServletRequest.getSession();
        Student student=(Student) httpSession.getAttribute("User");
        if(student==null){
            return BaseResponse.Error();
        }else {
            httpSession.removeAttribute("User");
            return BaseResponse.success("成功退出登录");
        }
    }
}
