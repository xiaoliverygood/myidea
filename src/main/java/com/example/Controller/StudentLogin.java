package com.example.Controller;

import com.example.Request.RequestLoginStudent;
import com.example.Request.RequestRegisterStudent;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Student")
public class StudentLogin {
    @RequestMapping("/register")
    public String register(@RequestBody RequestRegisterStudent register){
        return "hello xiaoli";
    }
    @RequestMapping("/login")
    public String login(@RequestBody RequestLoginStudent login){
        return "xiaoli";


    }
}
