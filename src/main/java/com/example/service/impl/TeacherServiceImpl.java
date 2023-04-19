package com.example.service.impl;

import com.example.model.entity.StudentResult;
import com.example.mapper.StudentMapper;
import com.example.mapper.TeacherMapper;
import com.example.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public List<StudentResult> FindeTeacher(int tid) {
        return teacherMapper.FindMyStudentBytid(tid);
    }
}
