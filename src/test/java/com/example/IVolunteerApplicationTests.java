package com.example;

import com.example.mapper.ActivityMapper;
import com.example.mapper.AdmitMapper;
import com.example.mapper.UserMapper;
import com.example.model.entity.Activity;
import com.example.model.entity.MyActivityNoBegin;
import com.example.model.entity.User;
import com.example.utility.DateTranslation;
import com.example.utility.TimeOverlapExample;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

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
        //System.out.println(userMapper.selectById("2834897@qq.com"));

    }

    @Test
    public void test3(){


      Instant instant =LocalDateTime.of(2023,5,20,12,55).toInstant(ZoneOffset.ofHours(8));

      Date date =new Date();
        Activity activity=new Activity(null,"6666",3,Date.from(instant),Date.from(instant),"gz",10,"282748@qq.com");

        activityMapper.insert(activity);
//        System.out.println(activityMapper.selectById(1));

    }
    @Test
    public void test5(){
        System.out.println(activityMapper.getActivityIdByName("6666"));
    }

    @Test
    public void test6(){
        System.out.println(DateTranslation.localDateTimeTransformDate(LocalDateTime.of(2022, 11, 29, 21, 34, 11)));
        Date date=new Date();
        date.setTime(20019);
        System.out.println(date.getTime());
        System.out.println(DateTranslation.DateTranslationLocalDateTime(date));
        System.out.println(LocalDateTime.of(2028, 11, 29, 21, 34, 11));
    }
    @Test
    public void test7(){
        Date date=new Date();
        userMapper.UserSingIn(date,1,"jqid");
    }
    @Test
    public void test8(){
        User user = new User("2834897@qq.com",null,null,null);
        user.setTime(9098L);
        userMapper.updateById(user);
    }
    @Test
    public void test9(){
        //测试时间重叠部分
        LocalDateTime localDateTime = LocalDateTime.of(2022,05,20,13,14);
        LocalDateTime localDateTime2 = LocalDateTime.of(2022,05,20,16,14);
        LocalDateTime localDateTime3 = LocalDateTime.of(2022,05,20,14,14);
        LocalDateTime localDateTime4 = LocalDateTime.of(2022,05,20,15,14);
        Boolean i=TimeOverlapExample.isTimeOverlap(localDateTime,localDateTime2,localDateTime3,localDateTime4);
        System.out.println(i);//返回true这代表时间冲突
        List<MyActivityNoBegin> list=userMapper.myActivityNoBegin("12345@qq.com");
        System.out.println(list.get(0).getLateTime());
        System.out.println(list.size());

    }

}
