package com.example.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.BaseResponse;
import com.example.model.entity.Activity;
import com.example.model.request.ActivityRequest;

public interface ActivityService extends IService<Activity> {

    void addActivity(ActivityRequest activityRequest);
}
