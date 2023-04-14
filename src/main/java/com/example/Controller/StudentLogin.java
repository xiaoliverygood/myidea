package com.example.Controller;

import com.example.Entity.Student;
import com.example.Mapper.StudentMapper;
import com.example.Request.RequestLoginStudent;
import com.example.Request.RequestRegisterStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/Student")
public class StudentLogin {
    @Autowired
    Random random;

    @Autowired
    StudentMapper studentMapper;
    @RequestMapping("/register")
    public String register(@RequestBody RequestRegisterStudent register){




        Student student=new Student(register.getSid(),register.getName(),register.getEmail(),register.getPassword());
        studentMapper.insertStudent(student);
        return "注册成功"+student.getName();
    }
    @RequestMapping("/login")
    public String login(@RequestBody RequestLoginStudent login){
        return "xiaoli";


    }
}
