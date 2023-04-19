package com.example.service;

import com.example.model.entity.StudentResult;

import java.util.List;

public interface TeacherService {

    public List<StudentResult> FindeTeacher(int tid);
}
