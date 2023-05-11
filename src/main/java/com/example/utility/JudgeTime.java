package com.example.utility;

import com.example.mapper.UserMapper;
import com.example.model.entity.MyActivityNoBegin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component//注册为bean
public class JudgeTime {
    @Autowired
    UserMapper userMapper;
    public  Boolean juedge(String email, Date begin, Date end){
        List<MyActivityNoBegin> list=userMapper.myActivityNoBegin(email);
        LocalDateTime date=DateTranslation.DateTranslationLocalDateTime(begin);
        LocalDateTime dete2=DateTranslation.DateTranslationLocalDateTime(end);
        int i=0;
        System.out.println(list.size());
        System.out.println(list.toString());
        if(list.isEmpty()) return true;
        while ((list.size()-1)>=i){
            Boolean result=TimeOverlapExample.isTimeOverlap(date,dete2,DateTranslation.DateTranslationLocalDateTime(list.get(i).getBeginTime()),DateTranslation.DateTranslationLocalDateTime(list.get(i).getLateTime()));
            if (result==false){
                return false;//这就是会冲突的
            }
            i++;
        }
        return true;//不会产生冲突
    }
}
