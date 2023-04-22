package com.example.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.model.entity.Activity;
import com.example.service.ActivityService;
import com.example.mapper.ActivityMapper;
import org.springframework.stereotype.Service;

/**
* @author L
* @description 针对表【activity】的数据库操作Service实现
* @createDate 2023-04-22 22:09:43
*/
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity>
    implements ActivityService{

}




