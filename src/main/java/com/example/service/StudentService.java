package com.example.service;

import com.example.model.entity.TeacherResult;

import java.util.List;

public interface StudentService {
    public List<TeacherResult> FindeTeacher(int sid);
    public List<String> FindMylesson(int sid);

}
