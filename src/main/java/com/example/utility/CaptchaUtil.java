package com.example.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

@Component


public class CaptchaUtil {
    @Autowired
    JavaMailSender sender;//springboot已经注册为bean了，加入到Ioc容器里面了，不用new一个对象那么麻烦
    public static Map<String,String> EmailAndCode= new HashMap<>();
    public static Map<String,String> EmailAndCodeFindpassword=new HashMap<>();
    public static Map<Integer,String> ActivityAndsigninCode= new HashMap<>();
    public static Map<Integer,String> ActivityAndsignoutCode= new HashMap<>();
    public static String getCode() {
       Random random= new Random();
        int randomCode =random.nextInt(100000,999999);
        String s1 = randomCode+"";//将int转换成字符串
        return s1;
    }

   public String RigisterCode(String aimadress) {
       System.out.println(aimadress);
       //SimpleMailMessage是一个比较简易的邮件封装，支持设置一些比较简单内容
        SimpleMailMessage message = new SimpleMailMessage();
        //设置邮件标题
        message.setSubject("【志愿服务系统】");
        String CodeTemplate=getCode();
        //设置邮件内容
        message.setText("欢迎注册为志愿者，人人为我，我为人人！让我们一起参与志愿服务吧！你的验证码:"+CodeTemplate);
        //设置邮件发送给谁，可以多个，这里就发给你的QQ邮箱
        message.setTo(aimadress);
        //邮件发送者，这里要与配置文件中的保持一致
        message.setFrom("ljz2020comeon@163.com");
        //OK，万事俱备只欠发送
        sender.send(message);
        EmailAndCode.put(aimadress, CodeTemplate);
        return CodeTemplate;
    }
    public String findPasswordCode(String aimadress) {
        //SimpleMailMessage是一个比较简易的邮件封装，支持设置一些比较简单内容
        SimpleMailMessage message = new SimpleMailMessage();
        //设置邮件标题
        message.setSubject("【志愿服务系统】");
        String CodeTemplate=getCode();
        //设置邮件内容
        message.setText("你正在找回密码！你的验证码:"+CodeTemplate);
        //设置邮件发送给谁，可以多个，这里就发给你的QQ邮箱
        message.setTo(aimadress);
        //邮件发送者，这里要与配置文件中的保持一致
        message.setFrom("ljz2020comeon@163.com");
        //OK，万事俱备只欠发送
        sender.send(message);
        EmailAndCodeFindpassword.put(aimadress, CodeTemplate);
        return CodeTemplate;
    }
    public String ActivitySinginCode(String aimadress,Integer id) {
        //SimpleMailMessage是一个比较简易的邮件封装，支持设置一些比较简单内容
        SimpleMailMessage message = new SimpleMailMessage();
        //设置邮件标题
        message.setSubject("【志愿服务系统】");
        String CodeTemplate=getCode();//确保每次都是最新的code
        //设置邮件内容
        message.setText("签到验证码:"+CodeTemplate);
        //设置邮件发送给谁，可以多个，这里就发给你的QQ邮箱
        message.setTo(aimadress);
        //邮件发送者，这里要与配置文件中的保持一致
        message.setFrom("ljz2020comeon@163.com");
        //OK，万事俱备只欠发送
        sender.send(message);
        ActivityAndsigninCode.put(id,CodeTemplate);
        return CodeTemplate;
    }
    public String ActivitySingOutCode(String aimadress,Integer id) {
        //SimpleMailMessage是一个比较简易的邮件封装，支持设置一些比较简单内容
        SimpleMailMessage message = new SimpleMailMessage();
        //设置邮件标题
        message.setSubject("【志愿服务系统】");
        String CodeTemplate=getCode();
        //设置邮件内容
        message.setText("签退验证码:"+ CodeTemplate);
        //设置邮件发送给谁，可以多个，这里就发给你的QQ邮箱
        message.setTo(aimadress);
        //邮件发送者，这里要与配置文件中的保持一致
        message.setFrom("ljz2020comeon@163.com");
        //OK，万事俱备只欠发送
        sender.send(message);
        ActivityAndsignoutCode.put(id, CodeTemplate);
        return CodeTemplate;
    }

}
