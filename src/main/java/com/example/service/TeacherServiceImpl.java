package com.example.service;

import com.example.entity.Student;
import com.example.entity.StudentResult;
import com.example.entity.TeacherResult;
import com.example.mapper.AdmitMapper;
import com.example.mapper.StudentMapper;
import com.example.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public List<StudentResult> FindeTeacher(int tid) {
        return teacherMapper.FindMyStudentBytid(tid);
    }
}
