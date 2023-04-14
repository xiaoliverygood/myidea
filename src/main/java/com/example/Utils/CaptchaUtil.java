package com.example.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component


public class CaptchaUtil {
    @Autowired
    JavaMailSender sender;//springboot已经注册为bean了，加入到Ioc容器里面了

   public void contextLoads(String code,String aimadress) {
        //SimpleMailMessage是一个比较简易的邮件封装，支持设置一些比较简单内容
        SimpleMailMessage message = new SimpleMailMessage();
        //设置邮件标题
        message.setSubject("【小李教务系统】");
        //设置邮件内容
        message.setText("验证码:"+code);
        //设置邮件发送给谁，可以多个，这里就发给你的QQ邮箱
        message.setTo(aimadress);
        //邮件发送者，这里要与配置文件中的保持一致
        message.setFrom("ljz2020comeon@163.com");
        //OK，万事俱备只欠发送
        sender.send(message);
    }


}
