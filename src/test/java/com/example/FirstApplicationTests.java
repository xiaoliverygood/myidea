package com.example;

import com.example.Entity.Student;
import com.example.Entity.Teacher;
import com.example.Mapper.StudentMapper;
import com.example.Mapper.TeacherMapper;
import com.example.Utils.CaptchaUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.List;

@SpringBootTest
class FirstApplicationTests {

    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Test
    void contextLoads() {

    }
    @Test
    void TextMysqlInsert(){
        Student student=new Student(2,"xiaochen","128319@qq.com");
        studentMapper.insertStudent(student);
    }
    @Test
    void TextMysqlSelect(){
        Student studentSelect=studentMapper.getStudentById(1);
        System.out.println(studentSelect.getName());
    }
    @Test
     void TextMysqlDelete(){
        studentMapper.deleteStudentBySid(2);
    }
    @Test
    void testStudentUpdateStudent(){
        studentMapper.UpdateStudentNameById(1,"xiaoli");

    }
    @Test
    void TextMysqlInserTeacher(){
        Teacher teacher=new Teacher(2,"小刚","13657@qq.com","Pc");
        teacherMapper.insertTeacher(teacher);
    }
    @Test
    void TextMysqlSelectList(){
        List<Teacher> List =teacherMapper.getTeacherByLesson("Pc");
        System.out.println(List.get(0).getName());
        System.out.println(List.get(1).getName());
    }

    @Autowired
    CaptchaUtil captchaUtil;
    @Test
    void  textCapthchaUtils(){

    captchaUtil.contextLoads("200231","2848762983@qq.com");



    }


    @Autowired
    JavaMailSender sender;//springboot已经注册为bean了，加入到Ioc容器里面了
    @Test
    public void contextLo() {
        //SimpleMailMessage是一个比较简易的邮件封装，支持设置一些比较简单内容
        SimpleMailMessage message = new SimpleMailMessage();
        //设置邮件标题
        message.setSubject("【小李教务系统】");
        //设置邮件内容
        message.setText("验证码:"+"129779");
        //设置邮件发送给谁，可以多个，这里就发给你的QQ邮箱
        message.setTo("2848762983@qq.com");
        //邮件发送者，这里要与配置文件中的保持一致
        message.setFrom("ljz2020comeon@163.com");
        //OK，万事俱备只欠发送
        sender.send(message);
    }


}
