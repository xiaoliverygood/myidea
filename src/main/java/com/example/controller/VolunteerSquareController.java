package com.example.controller;

import com.example.common.BaseResponse;
import com.example.model.entity.Activity;
import com.example.service.VolunteerSquareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Activity")
public class VolunteerSquareController {
    @Autowired
    VolunteerSquareService volunteerSquareService;
    @GetMapping("/search")
    public BaseResponse<List<Activity>> search(){
        return volunteerSquareService.showAllActivity();
    }
    @GetMapping("/searchByname")
    public BaseResponse searchByname(String name){
       return volunteerSquareService.searchByname(name);
    }
    @GetMapping("/searchLike")
    public BaseResponse searchLike(String nameLike){
        return volunteerSquareService.searchLike(nameLike);
    }
}
