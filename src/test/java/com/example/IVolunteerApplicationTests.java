package com.example;

import com.example.mapper.ActivityMapper;
import com.example.mapper.AdmitMapper;
import com.example.mapper.UserMapper;
import com.example.model.entity.Admit;
import com.example.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IVolunteerApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Autowired
    AdmitMapper admitMapper;
    @Autowired
    ActivityMapper activityMapper;
    @Test
    void contextLoads() {
        //  private String email;
        //    private String password;
        //    private String sex;
        //    private Integer time;
        //User user = new User("2834897@qq.com","287ewr","男",12);
        //userMapper.insert(user);
        // private String email;
        //    private String password;
       // Admit admit=new Admit("2848762983@qq.com","2718782jbca");
        //admitMapper.insert(admit);
        System.out.println(userMapper.selectById("2834897@qq.com"));

    }



}
