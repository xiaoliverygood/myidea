package com.example.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.BaseResponse;
import com.example.mapper.ActivityMapper;
import com.example.model.entity.Activity;
import com.example.model.request.ActivityRequest;
import com.example.service.VolunteerSquareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VolunteerSquareServiceImpl implements VolunteerSquareService {
    @Autowired
    ActivityMapper activityMapper;

    @Override
    public BaseResponse searchByname(String name) {
        QueryWrapper<Activity> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",name);
        return BaseResponse.success(activityMapper.selectOne(queryWrapper));
    }

    @Override
    public BaseResponse<List<Activity>> showAllActivity() {
        QueryWrapper<Activity> queryWrapper=new QueryWrapper<>();
        queryWrapper.isNotNull("name");
       return BaseResponse.success(activityMapper.selectList(queryWrapper));
    }

    @Override
    public BaseResponse searchLike(String nameLike) {
        List<Activity> activityList = activityMapper.selectResult(nameLike);
        return BaseResponse.success(activityList);
    }
}
