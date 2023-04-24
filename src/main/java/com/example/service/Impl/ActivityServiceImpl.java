package com.example.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.ActivityMapper;
import com.example.model.entity.Activity;
import com.example.model.request.ActivityRequest;
import com.example.service.ActivityService;
import com.example.utility.CaptchaUtil;
import com.example.utility.DateTranslation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.Date;


@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService{
    @Autowired
    ActivityMapper activityMapper;

    @Override
    public void addActivity(ActivityRequest activityRequest) {
        Date BeaginData = DateTranslation.localDateTimeTransformDate(activityRequest.getBeginTime());
        Date EndData = DateTranslation.localDateTimeTransformDate(activityRequest.getLateTime());
        Activity activity=new Activity(null,activityRequest.getName(),activityRequest.getTime(),BeaginData,EndData,activityRequest.getLocation(),activityRequest.getMaxpeople());
        activityMapper.insert(activity);
    }
}




