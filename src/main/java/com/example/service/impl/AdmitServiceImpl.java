package com.example.service.impl;

import com.example.mapper.AdmitMapper;
import com.example.mapper.StudentMapper;
import com.example.mapper.TeacherMapper;
import com.example.service.AdmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdmitServiceImpl implements AdmitService {

    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    AdmitMapper admitMapper;

    @Override
    public void deleteStudent(int sId) {
        studentMapper.deleteStudentBySid(sId);
    }

    @Override
    public void TeacherAddStudent(int sId, int tId) {
        admitMapper.TeacherAddStudent(tId,sId);
    }

    @Override
    public void TeacherDeleteStudent(int sId) {
        admitMapper.TeacherdeleteStudentBySid(sId);
    }

    @Override
    public void deleteTeacher(int tId) {
        teacherMapper.deleteTeacherBySid(tId);
    }
}
