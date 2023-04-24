package com.example.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.ActivityMapper;
import com.example.model.entity.Activity;
import com.example.model.request.ActivityRequest;
import com.example.service.ActivityService;
import com.example.utility.DateTranslation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService{
    @Autowired
    ActivityMapper activityMapper;

    @Override
    public Boolean addActivity(ActivityRequest activityRequest,String admitEmail) {
        Date BeaginData = DateTranslation.localDateTimeTransformDate(activityRequest.getBeginTime());
        Date EndData = DateTranslation.localDateTimeTransformDate(activityRequest.getLateTime());
        Activity activity=new Activity(null,activityRequest.getName(),activityRequest.getTime(),BeaginData,EndData,activityRequest.getLocation(),activityRequest.getMaxpeople(),admitEmail);
        activityMapper.insert(activity);
        return true;
    }

    @Override
    public Boolean removeActivity(Integer idActivity) {
        activityMapper.deleteById(idActivity);
        return true;
    }
}




