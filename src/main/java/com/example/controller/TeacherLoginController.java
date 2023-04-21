package com.example.controller;

import com.example.common.ResponMessge;
import com.example.model.entity.StudentResult;
import com.example.model.entity.Teacher;
import com.example.mapper.TeacherMapper;
import com.example.model.request.RequestLoginTeachger;
import com.example.model.request.RequestRegisterTeachger;
import com.example.common.BaseResponse;
import com.example.utils.CaptchaUtil;
import com.example.utils.EmailRegularExpression;
import com.example.utils.UploadTheFile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
            if(EmailRegularExpression.RegularEmailPattern(register.getEmail())){
                Teacher teacher=new Teacher(register.getTid(),register.getName(),register.getEmail(),register.getLesson(),register.getPassword());
                teacherMapper.insertTeacher(teacher);
                return BaseResponse.success(teacher);
            }else {
                return BaseResponse.Error(ResponMessge.EmailError.name());
            }
        }else {
            return BaseResponse.Error(ResponMessge.CaptchaError.name());
        }
    }
    @RequestMapping("/login")
    public BaseResponse<Teacher> login(@RequestBody RequestLoginTeachger login,HttpServletRequest httpServletRequest){
        if(login.getPassword().equals(teacherMapper.getTeacherById(login.getTid()).getPassword())){
            HttpSession httpSession=httpServletRequest.getSession();
            httpSession.setAttribute("teacherUser",teacherMapper.getTeacherById(login.getTid()));
            return BaseResponse.success(teacherMapper.getTeacherById(login.getTid()));
        }else {
            return BaseResponse.Error(ResponMessge.UserOrPasswordError.name());
        }
    }
    @GetMapping("/Inquire")
    public BaseResponse<Teacher> InquireTeacher(HttpServletRequest httpServletRequest){
        HttpSession httpSession=httpServletRequest.getSession();
        Teacher teacher=(Teacher) httpSession.getAttribute("teacherUser");
        if(teacher==null){
            return BaseResponse.Error(ResponMessge.NologError.name());
        }else {
            return BaseResponse.success(teacherMapper.getTeacherById(teacher.getTid()));
        }
    }
    @PutMapping("/updateName")
    public BaseResponse<Teacher> updateTeacherName(HttpServletRequest httpServletRequest, String newName){
        HttpSession httpSession=httpServletRequest.getSession();
       Teacher teacher=(Teacher) httpSession.getAttribute("teacherUser");
        if(teacher==null){
            return BaseResponse.Error(ResponMessge.NologError.name());
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
            return BaseResponse.Error(ResponMessge.NologError.name());
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
            return BaseResponse.Error(ResponMessge.NologError.name());
        }else {
            httpSession.removeAttribute("teacherUser");
            return BaseResponse.success(ResponMessge.Logoutsuccess.name());
        }
    }
    @GetMapping("/findStudent")
    public BaseResponse<List<StudentResult>> findStudent(HttpServletRequest httpServletRequest){
        HttpSession httpSession=httpServletRequest.getSession();
        Teacher teacher=(Teacher) httpSession.getAttribute("teacherUser");
        if(teacher==null){
            return BaseResponse.Error(ResponMessge.NologError.name());
        }else {
            List<StudentResult> listStudent=teacherMapper.FindMyStudentBytid(teacher.getTid());
           return BaseResponse.success(listStudent);
        }
    }
    @PostMapping("/UploadFile")
    public BaseResponse<String> uploadFile(HttpServletRequest httpServletRequest,@RequestParam("file") MultipartFile file ) throws IOException {
        HttpSession httpSession=httpServletRequest.getSession();
        Teacher teacher=(Teacher) httpSession.getAttribute("teacherUser");
        if(teacher==null){
            return BaseResponse.Error(ResponMessge.NologError.name());
        }else {
            UploadTheFile.TeacherUpload(teacher.getName(),file);
            return BaseResponse.success("上传文件成功");
        }
    }
}
