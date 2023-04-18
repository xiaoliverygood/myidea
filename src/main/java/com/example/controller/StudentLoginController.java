package com.example.controller;

import com.example.entity.Student;
import com.example.entity.TeacherResult;
import com.example.mapper.StudentMapper;
import com.example.request.RequestLoginStudent;
import com.example.request.RequestRegisterStudent;
import com.example.response.BaseResponse;
import com.example.service.StudentService;
import com.example.utils.CaptchaUtil;
import com.example.utils.EmailRegularExpression;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Student")
public class StudentLoginController {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    StudentService studentService;
    @PostMapping("/register")
    public BaseResponse<Student> register(@RequestBody RequestRegisterStudent register){
            if( CaptchaUtil.code.equals(register.getCode())){
                if(EmailRegularExpression.RegularEmailPattern(register.getEmail())){
                    Student student=new Student(register.getSid(),register.getName(),register.getEmail(),register.getPassword());
                    studentMapper.insertStudent(student);
                    return BaseResponse.success(student);
                }else {
                    return BaseResponse.Error("邮箱错误");
                }
            }else{
                return BaseResponse.Error("请求信息出错！");
            }
    }
    @RequestMapping("/login")
    public BaseResponse<Student> login(@RequestBody RequestLoginStudent login,HttpServletRequest httpServletRequest){
        if(login.getPassword().equals(studentMapper.getStudentById(login.getSid()).getPassword())){
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("User", studentMapper.getStudentById(login.getSid()));
            return BaseResponse.success(studentMapper.getStudentById(login.getSid()));
        }else {
            return BaseResponse.Error("请登陆后再进行访问！");
        }
    }
    @PutMapping("/updateName")
    public BaseResponse<Student> updateStudentName(HttpServletRequest httpServletRequest,String newName){
        HttpSession httpSession=httpServletRequest.getSession();
        Student student=(Student) httpSession.getAttribute("User");
        if(student==null){
           return BaseResponse.Error("请登陆后再进行访问！");
        }else {
            studentMapper.UpdateStudentNameById(student.getSid(),newName);
            return BaseResponse.success(studentMapper.getStudentById(student.getSid()));
        }
    }
    @PutMapping("/updatePassword")
    public BaseResponse<Student> updateStudentPassword(HttpServletRequest httpServletRequest,String newPassword){
        HttpSession httpSession=httpServletRequest.getSession();
        Student student=(Student) httpSession.getAttribute("User");
        if(student==null){
            return BaseResponse.Error("请登陆后再进行访问！");
        }else {
            studentMapper.UpdateStudentPasswordById(student.getSid(),newPassword);
            return BaseResponse.success(studentMapper.getStudentById(student.getSid()));
        }
    }
    @GetMapping("/inquire")//查找个人信息
    public BaseResponse<Student> InquireStudent(HttpServletRequest httpServletRequest){
        HttpSession httpSession=httpServletRequest.getSession();
        Student student=(Student) httpSession.getAttribute("User");
        if(student==null){
            return BaseResponse.Error("请登陆后再进行访问！");
        }else {
            return BaseResponse.success(studentMapper.getStudentById(student.getSid()));
        }
    }
    @RequestMapping("/logout")
    public BaseResponse<String> logout(HttpServletRequest httpServletRequest){
        HttpSession httpSession=httpServletRequest.getSession();
        Student student=(Student) httpSession.getAttribute("User");
        if(student==null){
            return BaseResponse.Error("请登陆后再进行访问！");
        }else {
            httpSession.removeAttribute("User");
            return BaseResponse.success("成功退出登录");
        }
    }
    @GetMapping("/findTeacher")
    public BaseResponse<List<TeacherResult>> findTeacher(HttpServletRequest httpServletRequest){
        HttpSession httpSession=httpServletRequest.getSession();
        Student student=(Student) httpSession.getAttribute("User");
        if(student==null){
            return BaseResponse.Error("请登陆后再进行访问！");
        }else {
//            List<TeacherResult> listMyTeacher=studentMapper.getTeacherBysid(student.getSid());
           return BaseResponse.success(studentMapper.getTeacherBysid(student.getSid()));
        }
    }
    @GetMapping("/findMylesson")
    public BaseResponse<List<String>> findMyLesson(HttpServletRequest httpServletRequest){
        HttpSession httpSession=httpServletRequest.getSession();
        Student student=(Student) httpSession.getAttribute("User");
        if(student==null){
            return BaseResponse.Error("请登陆后再进行访问！");
        }else {
//            List<TeacherResult> listMyTeacher=studentMapper.getTeacherBysid(student.getSid());
            return BaseResponse.success(studentService.FindMylesson(student.getSid()));
        }
    }
}
