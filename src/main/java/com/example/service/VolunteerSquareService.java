package com.example.service;

import com.example.common.BaseResponse;
import com.example.model.entity.Activity;

import java.util.List;

public interface VolunteerSquareService {
    BaseResponse<List<Activity>> showAllActivity();
    BaseResponse searchByname(String name);
    BaseResponse searchLike(String nameLike);
}
