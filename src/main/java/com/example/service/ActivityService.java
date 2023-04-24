package com.example.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.entity.Activity;
import com.example.model.request.ActivityRequest;

public interface ActivityService extends IService<Activity> {

    Boolean addActivity(ActivityRequest activityRequest,String admitEmail);
   Boolean removeActivity(Integer idActivity);
}
