package com.example.service;

import com.example.entity.Student;
import com.example.entity.StudentResult;
import com.example.entity.TeacherResult;

import java.util.List;

public interface TeacherService {

    public List<StudentResult> FindeTeacher(int tid);
}
