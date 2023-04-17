package com.example;

import com.example.entity.Student;
import com.example.entity.StudentResult;
import com.example.entity.Teacher;
import com.example.entity.TeacherResult;
import com.example.mapper.AdmitMapper;
import com.example.mapper.StudentMapper;
import com.example.mapper.TeacherMapper;
import com.example.utils.CaptchaUtil;
import com.example.utils.EmailRegularExpression;
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
    @Autowired
    AdmitMapper admitMapper;
    @Test
    void contextLoads() {

    }
    @Test
    void TextMysqlInsert(){
        Student student=new Student(2,"xiaochen","128319@qq.com","8978921");
        studentMapper.insertStudent(student);
    }
    @Test
    void TextMysqlSelect(){
        Student studentSelect=studentMapper.getStudentById(2);
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
        Teacher teacher=new Teacher(2,"小刚","13657@qq.com","Pc","wuiehriweu");
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
//        System.out.println(captchaUtil.getCode());
        captchaUtil.contextLoads("2848762983@qq.com");


        System.out.println(CaptchaUtil.code);


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
    @Test
    public void testTeacherFindStudent(){
        List<StudentResult> list=teacherMapper.FindMyStudentBytid(1);
        System.out.println(list.get(0));
        TeacherResult teacherResult=studentMapper.getTeacherBysid(1);
        System.out.println(teacherResult.getName());
    }
    @Test
    public void testTeacherAddStudent(){
        admitMapper.TeacherAddStudent(1,2);
    }
    @Test
    public void testEmailRe(){
        Boolean a=EmailRegularExpression.RegularEmailPattern("28783878@qq.com");

        System.out.println(a);
    }



}
